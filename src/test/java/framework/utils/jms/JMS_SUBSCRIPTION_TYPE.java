package framework.utils.jms;

public enum JMS_SUBSCRIPTION_TYPE {

    QUEUE("QUEUE"),TOPIC("TOPIC");

    String jmsType;

    JMS_SUBSCRIPTION_TYPE(String jmsType){
        this.jmsType = jmsType;
    }
}
