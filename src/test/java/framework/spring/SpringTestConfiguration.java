package framework.spring;

import com.ibm.mq.jms.MQConnectionFactory;
import framework.utils.java.JavaUtils;
import framework.utils.json.JsonUtils;
import framework.utils.restclient.SpringRestClient;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

import javax.jms.JMSException;
import java.util.HashMap;

@SpringBootApplication
@EnableRetry
@PropertySource(value = {"classpath:${project}/properties/app.properties"})
public class SpringTestConfiguration {

    static final Logger logger = LoggerFactory.getLogger(SpringTestConfiguration.class);

    @Configuration
    @Profile("reddit")
    @PropertySource(value = {"classpath:${project}/properties/${env}/reddit.properties"})
    public static class RedditConfig {

        @Autowired
        Environment environment;

        @Bean("threadLocal")
        public ThreadLocal<HashMap<String, Object>> threadLocal() {
            HashMap<String, Object> hm = new HashMap<>();

            ThreadLocal<HashMap<String, Object>> tl = new ThreadLocal();
            tl.set(hm);

            return tl;
        }

        @Bean("javaUtils")
        public JavaUtils javaUtils() {
            return new JavaUtils();
        }

//        @Bean(name = "jmsTemplate")
//        public JmsTemplate jmsTemplate() {
//
//            String defaultOutQueue = environment.getProperty("default_out_queue");
//
//            JmsTemplate jmsTemplate = null;
//            try {
//                jmsTemplate = new JmsTemplate();
//                jmsTemplate.setDefaultDestinationName(defaultOutQueue);
//                jmsTemplate.setSessionTransacted(true);
//                jmsTemplate.setConnectionFactory(redditAmqConnectionFactory());
//            } catch (Exception e) {
//                logger.error("Error occured, while creating JMS template: {}", e.getMessage());
//                throw e;
//            }
//            logger.info("JMS template {}", jmsTemplate);
//            return jmsTemplate;
//        }
//
//        @Bean
//        public RedeliveryPolicy redeliveryPolicy() {
//
//            int msgRedeliveryDelay = Integer.parseInt(environment.getProperty("msg_redelivery_delay"));
//            int msgRedeliveryCount = Integer.parseInt(environment.getProperty("msg_redelivery_count"));
//            int msgRedeliveryMaxDelay = Integer.parseInt(environment.getProperty("msg_redelivery_max_delay"));
//            int backoffMultiplier = Integer.parseInt(environment.getProperty("msg_redelivery_backoff_multiplier"));
//
//            RedeliveryPolicy redeliveryPolicy = null;
//
//            try {
//                redeliveryPolicy = new RedeliveryPolicy();
//                redeliveryPolicy.setInitialRedeliveryDelay(10);
//                redeliveryPolicy.setRedeliveryDelay(msgRedeliveryDelay);
//                redeliveryPolicy.setMaximumRedeliveries(msgRedeliveryCount);
//                redeliveryPolicy.setMaximumRedeliveryDelay(msgRedeliveryMaxDelay);
//                redeliveryPolicy.setUseExponentialBackOff(true);
//                redeliveryPolicy.setBackOffMultiplier(backoffMultiplier);
//                redeliveryPolicy.setQueue("*");
//            } catch (Exception e) {
//                logger.error("Error while creating re-delivery policy: {}", e.getMessage());
//                throw e;
//            }
//            return redeliveryPolicy;
//        }
//
//        @Bean(name = "redditAmqConnectionFactory")
//        @Primary
//        public ActiveMQConnectionFactory redditAmqConnectionFactory() {
//
//            String redditbrokerUrl = environment.getProperty("reddit_amq_broker_url");
//
//            logger.info("AMQ host name: {}", redditbrokerUrl);
//            ActiveMQConnectionFactory activeMQConnectionFactory = null;
//            try {
//                activeMQConnectionFactory = new ActiveMQConnectionFactory();
//                activeMQConnectionFactory.setBrokerURL(redditbrokerUrl);
//                activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy());
//                activeMQConnectionFactory.setUserName("admin");
//                activeMQConnectionFactory.setPassword("admin");
//            } catch (Exception e) {
//                logger.error("Error while creating connection factory: {}", e.getMessage());
//                throw e;
//            }
//
//            logger.info("AMQ connection factory created: {}", activeMQConnectionFactory);
//            return activeMQConnectionFactory;
//        }
//
//
//        @Bean
//        public MQConnectionFactory redditImqConnectionFactory() throws JMSException {
//
//            String redditHostName = environment.getProperty("reddit_imq_host_name");
//            String redditQueueMgr = environment.getProperty("reddit_imq_queue_mgr");
//            String redditChannel = environment.getProperty("reddit_imq_channel");
//            int redditTransportType = Integer.parseInt(environment.getProperty("reddit_imq_transport_type"));
//            int redditPort = Integer.parseInt(environment.getProperty("reddit_imq_port"));
//
//            logger.info("IMQ host name: {}, Queue Manager: {}, Channel: {}, Transport Type: {}", redditHostName, redditQueueMgr,
//                    redditQueueMgr, redditTransportType);
//
//            MQConnectionFactory connectionFactory = null;
//            try {
//                connectionFactory = new MQConnectionFactory();
//                connectionFactory.setHostName(redditHostName);
//                connectionFactory.setPort(redditPort);
//                connectionFactory.setQueueManager(redditQueueMgr);
//                connectionFactory.setTransportType(redditTransportType);
//                connectionFactory.setChannel(redditChannel);
//            } catch (Exception e) {
//                logger.error("Error occured in creating MQConnectionFactory ", e.getMessage());
//                throw e;
//            }
//            logger.info("IMQ connection factory created: {}", connectionFactory);
//            return connectionFactory;
//        }
//
//        @Bean(name = "redditImqConnectionFactory")
//        public UserCredentialsConnectionFactoryAdapter redditImqfactory() throws JMSException {
//
//            String redditImqUname = environment.getProperty("reddit_imq_uname");
//            String redditImqPwd = environment.getProperty("reddit_imq_pwd");
//
//            UserCredentialsConnectionFactoryAdapter credentialsConnectionFactoryAdapter = null;
//            try {
//                credentialsConnectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
//                credentialsConnectionFactoryAdapter.setTargetConnectionFactory(redditImqConnectionFactory());
//                credentialsConnectionFactoryAdapter.setUsername(redditImqUname);
//                credentialsConnectionFactoryAdapter.setPassword(redditImqPwd);
//            } catch (Exception e) {
//                logger.error("Error occured, while creating UserCredentialsConnectionFactoryAdapter: {}", e.getMessage());
//                throw e;
//            }
//
//            return credentialsConnectionFactoryAdapter;
//        }
//
//
//        @Bean(name = "redditDownStreamAmqConnectionFactory")
//        @Primary
//        public ActiveMQConnectionFactory redditDownStreamAmqConnectionFactory() {
//
//            String redditDownStreambrokerUrl = environment.getProperty("reddit_down_stream__amq_broker_url");
//
//            logger.info("AMQ host name: {}", redditDownStreambrokerUrl);
//            ActiveMQConnectionFactory activeMQConnectionFactory = null;
//            try {
//                activeMQConnectionFactory = new ActiveMQConnectionFactory();
//                activeMQConnectionFactory.setBrokerURL(redditDownStreambrokerUrl);
//                activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy());
//                activeMQConnectionFactory.setUserName("admin");
//                activeMQConnectionFactory.setPassword("admin");
//            } catch (Exception e) {
//                logger.error("Error while creating connection factory: {}", e.getMessage());
//                throw e;
//            }
//
//            logger.info("AMQ connection factory created: {}", activeMQConnectionFactory);
//            return activeMQConnectionFactory;
//        }
//
//
//        @Bean
//        public MQConnectionFactory redditDownStreamImqConnectionFactory() throws JMSException {
//
//
//            String redditDownStreamHostName = environment.getProperty("reddit_down_stream_imq_host_name");
//            String redditDownStreamQueueMgr = environment.getProperty("reddit_down_stream_imq_queue_mgr");
//            String redditDownStreamChannel = environment.getProperty("reddit_down_stream_imq_channel");
//            int redditDownStreamTransportType = Integer.parseInt(environment.getProperty("reddit_down_stream_imq_transport_type"));
//            int redditDownStreamPort = Integer.parseInt(environment.getProperty("reddit_down_stream_imq_port"));
//
//            logger.info("IMQ host name: {}, Queue Manager: {}, Channel: {}, Transport Type: {}", redditDownStreamHostName, redditDownStreamQueueMgr,
//                    redditDownStreamQueueMgr, redditDownStreamTransportType);
//
//            MQConnectionFactory connectionFactory = null;
//            try {
//                connectionFactory = new MQConnectionFactory();
//                connectionFactory.setHostName(redditDownStreamHostName);
//                connectionFactory.setPort(redditDownStreamPort);
//                connectionFactory.setQueueManager(redditDownStreamQueueMgr);
//                connectionFactory.setTransportType(redditDownStreamTransportType);
//                connectionFactory.setChannel(redditDownStreamChannel);
//            } catch (Exception e) {
//                logger.error("Error occured in creating MQConnectionFactory ", e.getMessage());
//                throw e;
//            }
//            logger.info("IMQ connection factory created: {}", connectionFactory);
//            return connectionFactory;
//        }
//
//        @Bean(name = "redditDownStreamImqConnectionFactory")
//        public UserCredentialsConnectionFactoryAdapter redditDownStreamImqfactory() throws JMSException {
//
//            String redditDownStreamImqUname = environment.getProperty("reddit_down_stream_imq_uname");
//            String redditDownStreamImqPwd = environment.getProperty("reddit_down_stream_imq_pwd");
//
//            UserCredentialsConnectionFactoryAdapter credentialsConnectionFactoryAdapter = null;
//
//            try {
//                credentialsConnectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
//                credentialsConnectionFactoryAdapter.setTargetConnectionFactory(redditDownStreamImqConnectionFactory());
//                credentialsConnectionFactoryAdapter.setUsername(redditDownStreamImqUname);
//                credentialsConnectionFactoryAdapter.setPassword(redditDownStreamImqPwd);
//            } catch (Exception e) {
//                logger.error("Error occured, while creating UserCredentialsConnectionFactoryAdapter: {}", e.getMessage());
//                throw e;
//            }
//
//            return credentialsConnectionFactoryAdapter;
//        }
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public SpringRestClient springRestClient() {
        return new SpringRestClient();
    }

    @Bean
    public JsonUtils jsonUtils() {
        return new JsonUtils();
    }
}