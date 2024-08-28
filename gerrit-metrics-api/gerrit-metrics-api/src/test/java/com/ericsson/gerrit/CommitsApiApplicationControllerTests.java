package com.ericsson.gerrit;

import com.ericsson.gerrit.commits.Commit;
import com.ericsson.gerrit.commits.CommitRepository;
import com.ericsson.gerrit.commits.CommitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommitsApiApplicationControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;
	@Test
	public void test_Get_All_Commits() {
		ResponseEntity<List<Commit>> responseEntity = restTemplate.exchange(
				"/allCommits",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Commit>>() {});
		List<Commit> responseBody = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void test_Get_All_Commits_By_Author() {
		ResponseEntity<List<Commit>> responseEntity = restTemplate.exchange(
				"/commits/{author}",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Commit>>() {},"empjtae");
		List<Commit> responseBody = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	@Test
	public void test_Get_All_Commits_By_Date_Range() throws ParseException {

		Date date1=Date.valueOf("2018-08-31");
		Date date2 = Date.valueOf("2019-08-31");
		ResponseEntity<List<Commit>> responseEntity = restTemplate.exchange(
				"/commits/range?start={start}&end={end}",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Commit>>() {},date1,date2);

		List<Commit> responseBody = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}


	@Test
	public void test_Get_Active_Days_In_A_Week() throws ParseException {
		Date date1=Date.valueOf("2018-11-25");
		Date date2 = Date.valueOf("2018-12-01");
		ResponseEntity<Integer> responseEntity = restTemplate.exchange(
				"/commits/activeDaysInAWeek?team={team}&start={start}&end={end}",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Integer>() {},"volt",date1,date2);
		Integer responseBody = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void test_Get_Commit_Lines_Added_Per_Day_By_Team() throws ParseException {
		Date day=Date.valueOf("2018-11-26");
		ResponseEntity<Integer> responseEntity = restTemplate.exchange(
				"/commits/commitLinesAdded?team={team}&day={day}",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Integer>() {},"volt",day);
		Integer responseBody = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void test_Get_Commit_Lines_Deleted_Per_Day_By_Team() throws ParseException {
		Date day=Date.valueOf("2018-11-26");
		ResponseEntity<Integer> responseEntity = restTemplate.exchange(
				"/commits/commitLinesDeleted?team={team}&day={day}",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Integer>() {},"volt",day);
		Integer responseBody = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void test_Get_Commits_Per_Day_By_Team() throws ParseException {
		Date day=Date.valueOf("2018-11-26");
		ResponseEntity<Long> responseEntity = restTemplate.exchange(
				"/commits/commitsPerDay?team={team}&day={day}",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Long>() {},"volt",day);
		Long responseBody = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void test_Get_Commits_By_Date_Range_By_Team() throws ParseException {

		Date date1=Date.valueOf("2018-08-31");
		Date date2 = Date.valueOf("2019-08-31");

		ResponseEntity<List<Commit>> responseEntity = restTemplate.exchange(
				"/commits/commitsDateTeam?team={team}&start={start}&end={end}",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Commit>>() {},"volt",date1,date2);
		List<Commit> responseBody = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void test_Get_Commits_By_Team() throws ParseException {

		ResponseEntity<List<Commit>> responseEntity = restTemplate.exchange(
				"/allCommits/{teamName}",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Commit>>() {},"volt");
		List<Commit> responseBody = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

}



