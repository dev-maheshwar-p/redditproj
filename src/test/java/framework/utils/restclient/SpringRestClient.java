package framework.utils.restclient;

import framework.spring.SpringTestConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@ContextConfiguration(classes = SpringTestConfiguration.class)
//@RunWith(SpringRunner.class)
public class SpringRestClient {

     Logger logger = LoggerFactory.getLogger(SpringRestClient.class);

    @Autowired
    RestTemplate restTemplate;

    private ResponseEntity doHttpCallToResource(HttpMethod httpMethod, String endPoint, HttpHeaders headers, String payload, Class clazz){

        String uuid = UUID.randomUUID().toString();

        if(headers!=null){
            headers.setContentType(MediaType.APPLICATION_JSON);
        }

        HttpEntity<String> entity = new HttpEntity<>(payload, headers);

        ResponseEntity<?> responseEntity = restTemplate.exchange(endPoint, httpMethod, entity, clazz, uuid);

        logger.info(httpMethod.toString() + " call to " + endPoint);

        return responseEntity;
    }

    public ResponseEntity getResource(String ep, Class clazz){
        return doHttpCallToResource(HttpMethod.GET, ep, null, null, clazz);
    }

    public ResponseEntity getResource(String ep, HttpHeaders headers, Class clazz){
        return doHttpCallToResource(HttpMethod.GET, ep, headers, null, clazz);
    }

    public ResponseEntity postResource(String ep, String payload, Class clazz){
        return doHttpCallToResource(HttpMethod.POST, ep, null, payload, clazz);
    }

    public ResponseEntity postResource(String ep, HttpHeaders headers, String payload, Class clazz){
        return doHttpCallToResource(HttpMethod.POST, ep, headers, payload, clazz);
    }

    public ResponseEntity putResource(String ep, String payload, Class clazz){
        return doHttpCallToResource(HttpMethod.PUT, ep, null, payload, clazz);
    }

    public ResponseEntity putResource(String ep, HttpHeaders headers, String payload, Class clazz){
        return doHttpCallToResource(HttpMethod.PUT, ep, headers, payload, clazz);
    }

    public ResponseEntity deleteResource(String ep, HttpHeaders headers, String payload, Class clazz){
        return doHttpCallToResource(HttpMethod.PUT, ep, headers, payload, clazz);
    }

    public ResponseEntity deleteResource(String ep, String payload, Class clazz){
        return doHttpCallToResource(HttpMethod.PUT, ep, null, payload, clazz);
    }

}
