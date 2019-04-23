package reddit;

import net.jodah.failsafe.RetryPolicy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class JavaUtils {

    private Random rnd = new Random();

    public int randonNumberGenerator(int noOfDigits) {
        int m = (int) Math.pow(10, (noOfDigits - 1));
        return m + rnd.nextInt(9 * m);

    }

	public String getUUID() {
		return UUID.randomUUID().toString();
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

    public String getCurrentDateAndTime() {
        return getCurrentDateAndTime("yyyy-MM-dd HH:mm:ss");
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

    /*
     * This method returns a random number from the specified range
     */
    public int randomNrGeneratorRange(int min, int max) {

        return rnd.nextInt((max - min) + 1) + min;

    }
    public String format(String text, String... val2) {
		for (int occurence = 0; occurence < val2.length; occurence++) {
			text = text.replace("#" + occurence + "#", val2[occurence]);
		}
		return text;
	}

}
