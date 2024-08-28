package com.tgfkyzen.sonarqube.sonarqube;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SonarQubeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void getSonarQubeMetrics() {
        ResponseEntity<SonarQubeDOA> responseEntity = restTemplate.exchange(
                "/sonarqubemetrics/{projectname}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<SonarQubeDOA>() {},"asr-topology-service");
        SonarQubeDOA responseBody = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void getAllSonarQubeData() {
        ResponseEntity<List<SonarQubeDOA>> responseEntity = restTemplate.exchange(
                "/sonarqubemetrics",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SonarQubeDOA>>() {});
        List<SonarQubeDOA> responseBody = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}