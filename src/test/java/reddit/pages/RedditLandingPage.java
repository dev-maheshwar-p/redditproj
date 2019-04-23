package reddit.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.hamcrest.MatcherAssert.*;

import static org.hamcrest.text.IsEqualIgnoringCase.*;

import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;


@DefaultUrl("https://www.reddit.com/")
public class RedditLandingPage extends PageObject {

	Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	Environment environment;

	@FindBy(xpath = "//a[contains(@href, 'https://www.reddit.com/login')]")
	private WebElement loginButton;

	@FindBy(xpath = "//iframe[contains(@src, 'https://www.reddit.com/login')]")
	private WebElement credentialsFrame;

	@FindBy(id = "loginUsername")
	private WebElement userNameInput;

	@FindBy(id = "loginPassword")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement signInButton;

	public void getUrl(String url){
		getDriver().navigate().to(url);
	}

	public void forceNavigate(String url){
		getDriver().navigate().to(url);
	}


	public void clickOnLoginButton(){
		waitFor(ExpectedConditions.elementToBeClickable(loginButton));
		element(loginButton).click();
	}

	public void verifyTitle(String title){
		assertThat(getDriver().getTitle(), containsString(title));
	}

	public void verifyLandingPage(String landingPageUrl) {
		waitFor(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		assertThat(getDriver().getCurrentUrl(), equalToIgnoringCase(landingPageUrl));

	}

	public void switchToCredentialsFrame() {
		getDriver().switchTo().frame(credentialsFrame);
	}

	public void enterUserName(String userName) {
		waitFor(ExpectedConditions.elementToBeClickable(userNameInput));
		element(userNameInput).click();
		element(userNameInput).sendKeys(userName);
	}

	public void enterPassword(String password) {
		waitFor(ExpectedConditions.elementToBeClickable(passwordInput));
		element(passwordInput).click();
		element(passwordInput).sendKeys(password);
	}

	public void clickTheSignInButton() {
		waitFor(ExpectedConditions.elementToBeClickable(signInButton));
		element(signInButton).click();
		switchToPage(RedditHomePage.class);
	}
}
