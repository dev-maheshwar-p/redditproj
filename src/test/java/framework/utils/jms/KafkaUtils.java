package framework.utils.jms;

import net.minidev.json.JSONArray;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;

import java.util.Properties;

public class KafkaUtils {

    String topicName = "topic_us_1_reddits";

    Logger logger = LogManager.getLogger(KafkaUtils.class);

    public Properties getProducerProperties() {
        // create instance for properties to access producer configs
        Properties props = new Properties();
        props.put("bootstrap.servers", "kafkav10.stg.ow-kafka0.reddit.com:9092");
        // Set acknowledgements for producer requests.
        props.put("acks", "all");
        // If the request fails, the producer can automatically retry,
        props.put("retries", 0);
        // Specify buffer size in config
        props.put("batch.size", 16384);
        // Reduce the no of requests less than 0
        props.put("linger.ms", 1);
        // The buffer.memory controls the total amount of memory available to
        // the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;

    }


    public void publish(JSONArray ja) throws JSONException {
        Producer<String, String> producer = new KafkaProducer<String, String>(getProducerProperties());

        for (int i = 0; i < ja.size(); i++) {
            producer.send(new ProducerRecord<String, String>(topicName, String.valueOf(i), ja.get(i).toString()));
        }
        logger.info("Publishing sub - reddits : " + ja.toJSONString());
        producer.close();
    }
}
