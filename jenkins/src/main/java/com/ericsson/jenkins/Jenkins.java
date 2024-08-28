package com.ericsson.jenkins;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;

@SpringBootApplication
public class Jenkins {
    static final int TIMEOUT = 60000;



    public static void main(String[] args) throws IOException, URISyntaxException {

        ApplicationContext context = SpringApplication.run(Jenkins.class, args);


        JenkinsServiceImpl service = context.getBean(JenkinsServiceImpl.class);
        service.initialize();
        //service.getAllTestData("asrl-correlation_PCR");

    }



}
