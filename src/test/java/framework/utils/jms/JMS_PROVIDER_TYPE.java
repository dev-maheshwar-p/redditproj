package framework.utils.jms;

public enum JMS_PROVIDER_TYPE {

    AMQ("AMQ"),IMQ("IMQ");

    String jmsProviderType;

    JMS_PROVIDER_TYPE(String jmsProviderType){
        this.jmsProviderType = jmsProviderType;
    }
}
