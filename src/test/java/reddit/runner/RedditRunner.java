package reddit.runner;

import cucumber.api.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import reddit.constants.FeatureFileNames;
import framework.baserunner.CustomSerenityRunner;

@RunWith(CustomSerenityRunner.class)
@CucumberOptions(features = {
    FeatureFileNames.REDDIT_SANITY
}, monochrome = true, glue = {"reddit"})
public class RedditRunner {

    @BeforeClass
    public static void executeBeforeAllTests() {
        System.out.println("This will run before all the test features triggered by this runner");
    }


    @AfterClass
    public static void executeAfterAllTests() {
        System.out.println("This will run after all the test features triggered by this runner");
    }

}