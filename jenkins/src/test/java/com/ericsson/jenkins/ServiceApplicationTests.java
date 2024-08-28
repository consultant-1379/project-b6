package com.ericsson.jenkins;

import com.ericsson.jenkins.Model.BuildResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.ArgumentMatchers.anyString;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ServiceApplicationTests {


	@MockBean
	JenkinsServiceImpl jenkinsServiceImpl;
	
	@Test
	void test_get_test_data()throws IOException, URISyntaxException {
		HashMap<String, Integer> dict = new HashMap<String,Integer>();
		dict.put("Passed Tests", 1);
		dict.put("Failed Tests", 2);
		dict.put("Skip Tests", 3);
		dict.put("Total Tests", 4);
		dict.put("Builds Never Ran", 5);
		dict.put("Builds Without Tests", 6);
		when(jenkinsServiceImpl.getAllTestData(anyString())).thenReturn(dict);
		HashMap<String,Integer> dictResult = jenkinsServiceImpl.getAllTestData("asrl-correlation_PCR");
		assertEquals(dictResult.get("Passed Tests"), 1);

	}
	@Test
	void test_get_build_data()throws IOException, URISyntaxException {
		HashMap<String, Integer> dict = new HashMap<String,Integer>();
		dict.put("Not Built Count", 1);
		dict.put("Fail Count", 2);
		dict.put("SuccessCount", 3);
		dict.put("Total", 4);

		when(jenkinsServiceImpl.getBuildData(anyString())).thenReturn(dict);
		HashMap<String,Integer> dictResult = jenkinsServiceImpl.getBuildData("asrl-correlation_PCR");
		assertEquals(dictResult.get("Total"), 4);

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
