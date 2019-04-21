package spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.retry.annotation.EnableRetry;

import java.util.HashMap;

@SpringBootApplication
@EnableRetry
@PropertySource(value = {"classpath:${project}/test.properties"})
public class SpringTestConfiguration {

    static final Logger logger = LoggerFactory.getLogger(SpringTestConfiguration.class);

    @Configuration
    @Profile("e2e")
    @PropertySource(value = {"classpath:${project}/test.properties"})
    public static class E2eAppConfig {

        @Autowired
        Environment environment;

        @Bean("threadLocal")
        public ThreadLocal<HashMap<String, Object>> threadLocal() {
            HashMap<String, Object> hm = new HashMap<>();

            ThreadLocal<HashMap<String, Object>> tl = new ThreadLocal();
            tl.set(hm);

            return tl;
        }
    }

}