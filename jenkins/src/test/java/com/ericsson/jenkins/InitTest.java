package com.ericsson.jenkins;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InitTest {
    @Autowired
    JenkinsService jenkinsService;


    @Test
    void test_initialization() throws IOException, URISyntaxException {

        String response = jenkinsService.initialize();
        assertEquals("Initialization Complete",response);
    }
}
