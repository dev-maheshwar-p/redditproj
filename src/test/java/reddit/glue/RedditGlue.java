package reddit.glue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import reddit.scenario.RedditSteps;
import spring.SpringTestConfiguration;

import java.util.HashMap;

@ContextConfiguration(classes = SpringTestConfiguration.class)
public class RedditGlue {

	@Steps
	RedditSteps redditSteps;

	@Autowired
	ThreadLocal<HashMap<String, Object>> tl;

	@Given("^The user lands on the \"([^\"]*)\"\\.$")
	public void verifyThatUserLandsOnTheRedditHomePage(String homePageUrl){
		System.out.println("The home page url is : " + homePageUrl);
	}

	@Given("^The user tries to login with the \"([^\"]*)\" and \"([^\"]*)\" as credentials\\.$")
	public void userTriesToLogin(String userName, String password) {
		System.out.println("The user name is : " + userName + " and the password is : " + password);
	}

	@Then("^The user should land on the home page\\.$")
	public void verifyThatTheUserLandsOnTheHomePage(){
		System.out.println("In the verification of the home page");
		tl.get().put("tl","****************** tl *************");

		redditSteps.printThreadLocalValues();

	}

}
