package reddit.glue;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import reddit.steps.RedditSteps;
import framework.spring.SpringTestConfiguration;

import java.util.HashMap;

@ContextConfiguration(classes = SpringTestConfiguration.class)
public class RedditGlue {

	@Steps
	RedditSteps redditSteps;

	@Autowired
	ThreadLocal<HashMap<String, Object>> tl;

	@Given("^The user lands on \"([^\"]*)\"\\.$")
	public void verifyThatUserLandsOnThe(String landingPageUrl){
		redditSteps.navigateToRedditLandingPage();
	    redditSteps.verifyLandingPage(landingPageUrl);
	}

	@Given("^The user tries to login with credentials for the user \"([^\"]*)\"\\.$")
	public void userTriesToLoginWithUserCredentials(String userName) {
		redditSteps.loginUsingCredentialsOfUser(userName);
	}

	@Then("^The user should land on the home page\\.$")
	public void verifyThatTheUserLandsOnTheHomePage(){
		redditSteps.navigateToRedditLandingPage();
	}

	@Then("^The user verifies that the subscribed subreddits is displayed\\.$")
	public void verifyThatTheUsersListOfSubscribedRedditsIsDisplayed(){
		redditSteps.clickSubscriptionFilterButton();
		redditSteps.verifyCommunitiesSectionsHasExpectedSubReddit();
	}

	@Then("^The user verifies he is able to view any random subreddit that is displayed\\.$")
	public void verifyViewingRandomSubReddit(){
		redditSteps.verifyAccessToRandomSubReddit();
	}

	@Then("^The user provides a comment for the post\\.$")
	public void provideCommentForPost(){
		redditSteps.provideComment();
	}

	@Then("^The user up votes the post\\.$")
	public void userUpVotesPost(){
		redditSteps.upVotePost();
	}

	@Then("^The user down votes the post\\.$")
	public void userDownVotesPost(){
		redditSteps.downVotePost();
	}

	@After
	public void logOut(){
		redditSteps.logOut();
	}
}
