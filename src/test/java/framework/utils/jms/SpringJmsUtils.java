package framework.utils.jms;

import framework.spring.SpringTestConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import reddit.constants.PROJECT_TYPE;

import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.Session;

@ContextConfiguration(classes = {SpringTestConfiguration.class})
//@RunWith(SpringRunner.class)
public class SpringJmsUtils {

    @Autowired
    @Qualifier("redditAmqConnectionFactory")
    ConnectionFactory redditAmqConnectionFactory;

    @Autowired
    @Qualifier("redditImqConnectionFactory")
    ConnectionFactory redditImqConnectionFactory;

    @Autowired
    @Qualifier("redditDownStreamAmqConnectionFactory")
    ConnectionFactory redditDownStreamAmqConnectionFactory;

    @Autowired
    @Qualifier("redditDownStreamImqConnectionFactory")
    ConnectionFactory redditDownStreamImqConnectionFactory;

    @Autowired
    @Qualifier("jmsTemplate")
    JmsTemplate jmsTemplate;


    Logger logger = LogManager.getLogger(this.getClass());

    public void sendMessage(JMS_SUBSCRIPTION_TYPE jst, JMS_PROVIDER_TYPE jpt, PROJECT_TYPE dct, String destQueueOrTopicName, String message) {

        JmsTemplate jmsTemplate = configureJmsTemplate(jst, jpt, dct);
        jmsTemplate.send(destQueueOrTopicName, (Session session)-> session.createTextMessage(message));
    }

    public Message receiveMessages(JMS_SUBSCRIPTION_TYPE jst, JMS_PROVIDER_TYPE jpt, PROJECT_TYPE dct, String destQueueOrTopicName) {

        JmsTemplate jmsTemplate = configureJmsTemplate(jst, jpt, dct);
        Message message = jmsTemplate.receive(destQueueOrTopicName);

        return message;
    }

    private JmsTemplate configureJmsTemplate(JMS_SUBSCRIPTION_TYPE jst, JMS_PROVIDER_TYPE jpt, PROJECT_TYPE dct){

        if(jpt == JMS_PROVIDER_TYPE.IMQ){
            if(dct == PROJECT_TYPE.REDDIT){
                jmsTemplate.setConnectionFactory(redditImqConnectionFactory);
            }else if(dct == PROJECT_TYPE.REDDIT_DOWN_STREAM){
                jmsTemplate.setConnectionFactory(redditDownStreamImqConnectionFactory);
            }
        }else if(jpt == JMS_PROVIDER_TYPE.AMQ){
            if(dct == PROJECT_TYPE.REDDIT){
                jmsTemplate.setConnectionFactory(redditAmqConnectionFactory);
            }else if(dct == PROJECT_TYPE.REDDIT_DOWN_STREAM){
                jmsTemplate.setConnectionFactory(redditDownStreamAmqConnectionFactory);
            }
        }

        if(jst == JMS_SUBSCRIPTION_TYPE.TOPIC){
            jmsTemplate.setPubSubDomain(true);
        }else{
            jmsTemplate.setPubSubDomain(false);
        }

        return jmsTemplate;
    }


}

