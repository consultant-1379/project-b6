package com.ericsson.gerrit;

import com.ericsson.gerrit.commits.Commit;
import com.ericsson.gerrit.commits.CommitRepository;
import com.ericsson.gerrit.commits.CommitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest
class CommitsApiApplicationTests {

	@MockBean
	private CommitRepository mockCommitRepo;

	@Autowired
	private CommitService commitService;

	@Test
	void test_Get_All_Commits() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		Date date1 = sdf.parse("31-08-2018");
		Date date2 = sdf.parse("31-08-2019");

		Commit commit1 = new Commit("02f18dd07c6e5ada13f20f666ddd697fe07da369","empjtae",date1,true,
				"[SONP-37098][CM-Service] Remove %s from physicalTopologies.json in cm-topology test resources https://jira-oss.seli.wh.rnd.internal.ericsson.com/browse/SONP-37098 Change-Id: Iae6fc80b868d5119087893271024dcb6a5351f98",
				"cm-service.git","volt",1,1);
		Commit commit2 = new Commit("046afcab418201691357e979eb8f3b031a228629","edeamai",date2,true,"No JIRA Change enm-test-library-bom to version 1.0.442 Change-Id: Ib0f7408f1214bdf324d401ae1938012eb6a33445",
				"asr-testware.git","volt",2,2);
		List<Commit> commits = new ArrayList<Commit>();
		commits.add(commit1);
		commits.add(commit2);

		when(mockCommitRepo.findAllCommits()).thenReturn(commits);
		commits = commitService.getAllCommits();
		assertEquals("046afcab418201691357e979eb8f3b031a228629",commits.get(1).getC_hash());
	}

	@Test
	void test_Get_All_Commits_By_Name() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		Date date1 = sdf.parse("31-08-2018");
		Date date2 = sdf.parse("31-08-2019");

		Commit commit1 = new Commit("02f18dd07c6e5ada13f20f666ddd697fe07da369","empjtae",date1,true,
				"[SONP-37098][CM-Service] Remove %s from physicalTopologies.json in cm-topology test resources https://jira-oss.seli.wh.rnd.internal.ericsson.com/browse/SONP-37098 Change-Id: Iae6fc80b868d5119087893271024dcb6a5351f98",
				"cm-service.git","volt",1,1);
		Commit commit2 = new Commit("046afcab418201691357e979eb8f3b031a228629","edeamai",date2,true,"No JIRA Change enm-test-library-bom to version 1.0.442 Change-Id: Ib0f7408f1214bdf324d401ae1938012eb6a33445",
				"asr-testware.git","volt",2,2);
		List<Commit> commits = new ArrayList<Commit>();
		commits.add(commit1);
		commits.add(commit2);

		when(mockCommitRepo.findAllCommitsByAuthor(anyString())).thenReturn(commits);
		commits=commitService.getAllCommitsByName("edeamai");
		assertEquals(commits.get(1).getC_hash(), "046afcab418201691357e979eb8f3b031a228629");
		assertEquals(commits.get(1).getC_project_name(), "asr-testware.git");

		assertEquals("No JIRA Change enm-test-library-bom to version 1.0.442 Change-Id: Ib0f7408f1214bdf324d401ae1938012eb6a33445",commits.get(1).getC_message());
	}
	@Test
	void test_Get_All_Commits_In_Date_Range() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		Date date1 = sdf.parse("31-08-2018");
		Date date2 = sdf.parse("31-08-2019");

		Commit commit1 = new Commit("02f18dd07c6e5ada13f20f666ddd697fe07da369","empjtae",date1,true,
				"[SONP-37098][CM-Service] Remove %s from physicalTopologies.json in cm-topology test resources https://jira-oss.seli.wh.rnd.internal.ericsson.com/browse/SONP-37098 Change-Id: Iae6fc80b868d5119087893271024dcb6a5351f98",
				"cm-service.git","volt",1,1);
		Commit commit2 = new Commit("046afcab418201691357e979eb8f3b031a228629","edeamai",date2,true,"No JIRA Change enm-test-library-bom to version 1.0.442 Change-Id: Ib0f7408f1214bdf324d401ae1938012eb6a33445",
				"asr-testware.git","volt",2,2);
		List<Commit> commits = new ArrayList<Commit>();
		commits.add(commit1);
		commits.add(commit2);

		when(mockCommitRepo.findAllCommitsInDateRange(any(Date.class),any(Date.class))).thenReturn(commits);
		commits=commitService.getAllCommitsInDateRange(date1,date2);
		assertEquals("046afcab418201691357e979eb8f3b031a228629",commits.get(1).getC_hash());
		assertEquals("edeamai", commits.get(1).getC_author());
	}

	@Test
	void test_Get_All_Commits_In_Date_Range_By_Name() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		Date date1 = sdf.parse("31-08-2018");
		Date date2 = sdf.parse("31-08-2019");

		Commit commit1 = new Commit("02f18dd07c6e5ada13f20f666ddd697fe07da369","empjtae",date1,true,
				"[SONP-37098][CM-Service] Remove %s from physicalTopologies.json in cm-topology test resources https://jira-oss.seli.wh.rnd.internal.ericsson.com/browse/SONP-37098 Change-Id: Iae6fc80b868d5119087893271024dcb6a5351f98",
				"cm-service.git","volt",1,1);
		Commit commit2 = new Commit("046afcab418201691357e979eb8f3b031a228629","edeamai",date2,true,"No JIRA Change enm-test-library-bom to version 1.0.442 Change-Id: Ib0f7408f1214bdf324d401ae1938012eb6a33445",
				"asr-testware.git","volt",2,2);
		List<Commit> commits = new ArrayList<Commit>();
		commits.add(commit1);
		commits.add(commit2);

		when(mockCommitRepo.findAllCommitsWithinDateRangeByTeamName(anyString(),any(Date.class),any(Date.class))).thenReturn(commits);
		commits=commitService.getAllCommitsInDateRangeByTeamName("volt",date1,date2);
		assertEquals(commits.get(1).getC_hash(), "046afcab418201691357e979eb8f3b031a228629");
		assertEquals(commits.get(1).getC_date(), date2);
		assertEquals( true,commits.get(1).getC_in_main_branch());
	}

	@Test
	void test_Get_Number_Of_Commits_Per_Day() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		Date day = sdf.parse("31-08-2018");

		Long commits = Long.parseLong(""+20);

		when(mockCommitRepo.findAllCommitsPerDayByTeam(anyString(),any(Date.class))).thenReturn(commits);
		Long size =commitService.getCommitsPerDayByTeam("volt",day);
		assertEquals(20,size);
	}

	@Test
	void test_Get_Commit_Lines_Added_Per_Day() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		Date day = sdf.parse("31-08-2018");

		Long commits = Long.parseLong(""+25);

		when(mockCommitRepo.findCommitLinesAddedPerDayByTeam(anyString(),any(Date.class))).thenReturn(commits);
		Long size =commitService.getCommitLinesAddedPerDayByTeam("volt",day);
		assertEquals(25,size);
	}

	@Test
	void test_Get_Commit_Lines_Deleted_Per_Day() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		Date day = sdf.parse("31-08-2018");

		Long commits = Long.parseLong(""+30);

		when(mockCommitRepo.findCommitLinesDeletedPerDayByTeam(anyString(),any(Date.class))).thenReturn(commits);
		Long size = commitService.getCommitLinesDeletedPerDayByTeam("volt",day);
		assertEquals(30,size);
	}

	@Test
	void test_Get_Active_Days_In_A_Week() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		Date start = sdf.parse("31-08-2018");
		Date end = sdf.parse("06-09-2019");

		when(mockCommitRepo.findActiveDaysInAWeek(anyString(),any(Date.class),any(Date.class))).thenReturn(new ArrayList<Date>());
		Long c_size = commitService.getActiveDaysInAWeek("volt",start,end);
		assertEquals(0,c_size);
	}

	@Test
	void test_Get_All_Commits_By_TeamName() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		Date date1 = sdf.parse("31-08-2018");
		Date date2 = sdf.parse("31-08-2019");

		Commit commit1 = new Commit("02f18dd07c6e5ada13f20f666ddd697fe07da369", "empjtae", date1, true,
				"[SONP-37098][CM-Service] Remove %s from physicalTopologies.json in cm-topology test resources https://jira-oss.seli.wh.rnd.internal.ericsson.com/browse/SONP-37098 Change-Id: Iae6fc80b868d5119087893271024dcb6a5351f98",
				"cm-service.git", "volt", 1, 1);
		Commit commit2 = new Commit("046afcab418201691357e979eb8f3b031a228629", "edeamai", date2, true, "No JIRA Change enm-test-library-bom to version 1.0.442 Change-Id: Ib0f7408f1214bdf324d401ae1938012eb6a33445",
				"asr-testware.git", "volt", 2, 2);
		List<Commit> commits = new ArrayList<Commit>();
		commits.add(commit1);
		commits.add(commit2);
		when(mockCommitRepo.findAllCommitsByTeamName(anyString())).thenReturn(commits);
		commits = commitService.getAllCommitsByTeamName("volt");
		assertEquals(commits.get(1).getC_hash(), "046afcab418201691357e979eb8f3b031a228629");
		assertEquals(commits.get(1).getC_author(), "edeamai");
	}

}



