package com.ericsson.jenkins;

import com.ericsson.jenkins.Model.BuildResponse;
import com.ericsson.jenkins.Model.TestResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class JenkinsApplicationTests {


	@Autowired
	TestRestTemplate testRestTemplate;




	/*@Autowired
	BuildResponse buildResponse;

	@Autowired
	TestResponse testResponse;*/
	RestTemplateBuilder builder = new RestTemplateBuilder().setReadTimeout(Duration.ofSeconds(60000));




	@Test
	void test_get_jobs_build_data(){

		TestRestTemplate testRestTemplateWait = new TestRestTemplate(builder);
		ResponseEntity<BuildResponse> responseEntity = testRestTemplateWait.exchange("http://localhost:8085/jenkins/build/{job}",
				HttpMethod.GET,
				null,
				BuildResponse.class,"asrl-correlation_PCR");
		BuildResponse responseBody = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
}
//
//	@Test
//    void test_get_jobs_test_data(){
//		System.out.println("entered test2");
//		TestRestTemplate testRestTemplateWait = new TestRestTemplate(builder);
//        ResponseEntity<TestResponse> responseEntity = testRestTemplateWait.exchange("http://localhost:8085/jenkins/test/{job}",
//                HttpMethod.GET,
//                null,
//                TestResponse.class,"asrl-correlation_PCR");
//		TestResponse responseBody = responseEntity.getBody();
//		System.out.println(responseBody);
//		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    }


}
