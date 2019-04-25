package reddit.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.WebElementFacadeImpl;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

import org.apache.commons.lang3.RandomUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import reddit.JavaUtils;

import java.util.List;


import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;


@DefaultUrl("https://www.reddit.com/")
public class RedditHomePage extends PageObject {

	Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	Environment environment;

	@FindBy(xpath = "//button[@aria-label='Start typing to filter your communities or use up and down to select.']")
	private WebElement filterCommunitiesButton;

	@FindBy(xpath = "//div[contains(@class,'scroller') and not(contains(*,'promoted'))]//child::*[@data-click-id='body']/div/span/a")
	private List<WebElement> listOfSubRedditsThatAreNotPromotions;

	@FindBy( xpath = "//div[@role='textbox']")
	private WebElement commentBox;

	@FindBy(id="USER_DROPDOWN_ID")
	private WebElement userDropDownMenu;

	@FindBy(xpath = "//div[text()='Log Out']")
	private WebElement logoutButton;

	@FindBy(xpath = "//button[text()='comment']")
	private WebElement submitCommentButton;

	@FindBy(xpath = "//div[@data-redditstyle='true']//button[contains(@data-click-id,'upvote')]")
	private WebElement upvoteButton;

	@FindBy(xpath = "//div[@data-redditstyle='true']//button[contains(@data-click-id,'downvote')]")
	private WebElement downvoteButton;

	private static final String expectedCommunity = "//div[text()='my communities']/following-sibling::a/span";


	public void clickOnFilterSubscriptionButton() {
		waitFor(ExpectedConditions.elementToBeClickable(filterCommunitiesButton));
		element(filterCommunitiesButton).click();
	}

	public void verifySubscribedReddit() {
		WebElementFacadeImpl expectedCommunityElement = element(expectedCommunity + "[contains(text(),'science')]");
		assertThat("The expected reddit is not present in the subscriptions",expectedCommunityElement.isPresent());
	}

	public void clickOnAnyOfSubscribedReddits(){
		waitFor(ExpectedConditions.visibilityOfAllElements(listOfSubRedditsThatAreNotPromotions));
		WebElement randomSubReddit = listOfSubRedditsThatAreNotPromotions.get(RandomUtils.nextInt(0, 5));
		waitFor(ExpectedConditions.elementToBeClickable(randomSubReddit));
		element(randomSubReddit).click();
	}

	public void enterStandardCommentInCommentsSection(){
		JavaUtils ju = new JavaUtils();
		String currentDateAndTime = ju.getCurrentDateAndTime("dd MMM YYYY K:ma");
		element(commentBox).sendKeys(currentDateAndTime + " AnonymousF AnonymousL");
	}

	public void clickLogOut() {
		waitFor(ExpectedConditions.elementToBeClickable(logoutButton));
		element(logoutButton).click();
		getDriver().manage().deleteAllCookies();
		switchToPage(RedditLandingPage.class);
	}

	public void clickLogOutMenu() {
		waitFor(ExpectedConditions.elementToBeClickable(userDropDownMenu));
		element(userDropDownMenu).click();
	}

	public void clickOnSubmitCommentButton() {
		waitFor(ExpectedConditions.elementToBeClickable(submitCommentButton));
		element(submitCommentButton).click();
	}

	public void upVotePost() {
		waitFor(ExpectedConditions.elementToBeClickable(upvoteButton));
		element(upvoteButton).click();
	}

	public void downVotePost() {
		waitFor(ExpectedConditions.elementToBeClickable(downvoteButton));
		element(downvoteButton).click();
	}
}
