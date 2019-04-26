package reddit.steps;


import net.thucydides.core.steps.ScenarioSteps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import reddit.pages.RedditHomePage;
import reddit.pages.RedditLandingPage;
import framework.spring.SpringTestConfiguration;
import java.util.HashMap;

@ContextConfiguration(classes = SpringTestConfiguration.class)
public class RedditSteps extends ScenarioSteps {

	@Autowired
	Environment environment;

	RedditLandingPage redditLandingPage;
	RedditHomePage redditHomePage;

	@Autowired
	ThreadLocal<HashMap<String, Object>> tl;

	public void printThreadLocalValues(){
		System.out.println("The thread local value is : " + tl.toString());
	}

	public void verifyPageTitleContainsExpectedString(String title){
		redditLandingPage.verifyTitle(title);
	}

	public void verifyLandingPage(String landingPageUrl){
		redditLandingPage.verifyLandingPage(landingPageUrl);
	}

	public void navigateToRedditLandingPage() {
		redditLandingPage.getUrl("http://www.reddit.com/");
		redditLandingPage.open();
	}

	public void loginUsingCredentialsOfUser(String userName) {

		String password = environment.getProperty(userName + "_password");

		redditLandingPage.clickOnLoginButton();
		redditLandingPage.switchToCredentialsFrame();

		redditLandingPage.enterUserName(userName);
		redditLandingPage.enterPassword(password);

		redditLandingPage.clickTheSignInButton();
	}

	public void clickSubscriptionFilterButton() {
		redditHomePage.clickOnFilterSubscriptionButton();
	}

	public void verifyCommunitiesSectionsHasExpectedSubReddit() {
		redditHomePage.verifySubscribedReddit();
		closeFilterSubscriptionPopUpMenu();
	}

	/*
	 *	We click on the filter button again because we need to close the pop-up drop down
	 *	 so that the focus is available to the usr dropdown when clicked.
	 */
	private void closeFilterSubscriptionPopUpMenu(){
		clickSubscriptionFilterButton();
	}

	public void verifyAccessToRandomSubReddit() {
		redditHomePage.clickOnAnyOfSubscribedReddits();
	}

	public void logOut() {
		redditHomePage.clickLogOutMenu();
		redditHomePage.clickLogOut();
	}

	public void provideComment() {
		redditHomePage.enterStandardCommentInCommentsSection();
		redditHomePage.clickOnSubmitCommentButton();
	}

	public void upVotePost() {
		redditHomePage.upVotePost();
	}

	public void downVotePost() {
		redditHomePage.downVotePost();
	}
}
