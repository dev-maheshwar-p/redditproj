package framework.utils.java;

import framework.spring.SpringTestConfiguration;
import net.jodah.failsafe.RetryPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@ContextConfiguration(classes = SpringTestConfiguration.class)
public class JavaUtils {

    @Autowired
    Environment environment;

	public String getUUID() {
		return UUID.randomUUID().toString();
	}

    public String getProperty(String propertyName){
	    return environment.getProperty(propertyName);
    }

    public String getCurrentDateAndTime(String format, String timeZone) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        if (!timeZone.equals(""))
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = new Date();
        return dateFormat.format(date);
    }


    public String getCurrentDateAndTime(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static RetryPolicy getRetryPolicy(TimeUnit timeUnit, long delay, int retryCount) {

        return new RetryPolicy()
                .retryOn(Throwable.class)
                .withDelay(delay, timeUnit)
                .withMaxRetries(retryCount);

    }

    public static RetryPolicy getRetryPolicy(long delay, int retryCount) {

        return getRetryPolicy(TimeUnit.SECONDS, delay, retryCount);
    }


    /**
     * This utility method sorts the input map based on the values, lexicographically and returns a sorted map.
     *
     * @param map The input map to sort based on the values.
     * @return Map sorted lexicographically on the values of the input map.
     */
    public Map<String, Integer> getSortedMapOnMapValue(Map<String, Integer> map) {

       return
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));


    }


    /**
     * This utility method sorts the input map based on the keys, lexicographically and returns a sorted map.
     *
     * @param map The input map to sort based on the keys.
     * @return Map sorted lexicographically on the keys of the input map.
     */
    public Map<Integer, Integer> getSortedMapOnKeyValue(Map<Integer, Integer> map) {

       return
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));

    }

}
