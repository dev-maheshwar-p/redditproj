package framework.baserunner;

import cucumber.runtime.junit.FeatureRunner;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import framework.spring.SpringTestConfiguration;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/*
    This is a custom serenity runner that extends CucumberWithSerenity that is used to take advantage
    of some of the life-cycle methods of Serenity.
 */

@WebAppConfiguration
@ContextConfiguration(classes = SpringTestConfiguration.class)
public class CustomSerenityRunner extends CucumberWithSerenity {

    /*
        This is called @ the start of the tests from the child runner, we use this to set the active profile.
     */
    public CustomSerenityRunner(Class clazz) throws InitializationError, IOException {
        super(clazz);

        String profile = System.getProperty("project");
        System.setProperty("log4j.configurationClass","CustomConfigurationFactory");
        System.setProperty("spring.profiles.active", profile);

    }


    /*
        This is executed before tests and can be used to dynamically filter/(add to) the feature files that get executed.
     */
    @Override
    public List<FeatureRunner> getChildren() {

        List<FeatureRunner> children = super.getChildren();

        Iterator<FeatureRunner> iterator = children.iterator();

        while(iterator.hasNext()){
            FeatureRunner fr = iterator.next();
            if(fr.getName().contains("Pattern matching feature file name for processing")){
                iterator.remove();
            }
        }

        System.out.println("Name of the features to be executed are : ");

        for (FeatureRunner fr:children) {
            System.out.println(fr.getName());
        }

        return children;
    }


    /*
    This line executes after every feature: Note that there is no hook for AfterFeature. " +
                "This can be used as an alternative to tagged @Ater if needed
     */
    @Override
    protected void runChild(FeatureRunner child, RunNotifier notifier) {
        super.runChild(child, notifier);
        System.out.println("This line executes after every feature: Note that there is no hook for AfterFeature. " +
                "This can be used as an alternative to tagged @Ater if needed");
    }

}
