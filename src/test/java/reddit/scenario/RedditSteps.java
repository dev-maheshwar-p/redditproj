package reddit.scenario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import spring.SpringTestConfiguration;
import java.util.HashMap;

@ContextConfiguration(classes = SpringTestConfiguration.class)
public class RedditSteps {

	@Autowired
	Environment environment;

	@Autowired
	ThreadLocal<HashMap<String, Object>> tl;

	public void printThreadLocalValues(){
		System.out.println("The thread local value is : " + tl.get().get("tl").toString());
	}


}
