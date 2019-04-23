
import cucumber.runtime.junit.FeatureRunner;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import spring.SpringTestConfiguration;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class,classes = SpringTestConfiguration.class)
public class CustomSerenityRunner extends CucumberWithSerenity {

    public CustomSerenityRunner(Class clazz) throws InitializationError, IOException {
        super(clazz);

        String profile = System.getProperty("project");
        System.setProperty("log4j.configurationClass","CustomConfigurationFactory");
        System.setProperty("spring.profiles.active", profile);

    }

    @Override
    public List<FeatureRunner> getChildren() {


        List<FeatureRunner> children = super.getChildren();

        Iterator<FeatureRunner> iterator = children.iterator();

        while(iterator.hasNext()){
            FeatureRunner fr = iterator.next();
            if(fr.getName().contains("This is a blank")){
                iterator.remove();
            }
        }

        for (FeatureRunner fr:children) {
            System.out.println("***************" + fr.getName() + "***************");
        }

        return children;
    }


    @Override
    protected void runChild(FeatureRunner child, RunNotifier notifier) {
        super.runChild(child, notifier);
        System.out.println("This is after feature");
    }

}
