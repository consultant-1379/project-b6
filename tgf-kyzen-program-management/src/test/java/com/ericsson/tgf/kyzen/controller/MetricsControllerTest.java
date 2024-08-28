package com.ericsson.tgf.kyzen.controller;

import com.ericsson.tgf.kyzen.entity.*;
import com.ericsson.tgf.kyzen.model.Member;
import com.ericsson.tgf.kyzen.service.MetricsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class MetricsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MetricsService mockService;


    @Test
    void verify_404_on_getTeamMetrics_invalid_id() throws Exception {

        when(mockService.getAllDetailsForTeamById(any())).thenReturn(null);

        mockMvc.perform(get("/metrics/team/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    void verify_ok_on_getTeamMetrics_valid_id() throws Exception{
        TeamDAO teamDAO = new TeamDAO();
        JenkinsDAO jenkinsDAO = initJenkins();
        GitDAO gitDAO = initGit();
        SonarDAO sonarDAO = initSonar();
        RepoDAO repoDAO = initRepo();
        MemberDAO memberDAO = new MemberDAO();

        jenkinsDAO.setTeam(teamDAO);
        repoDAO.setTeam(teamDAO);
        memberDAO.setTeam(teamDAO);
        gitDAO.setTeam(teamDAO);
        sonarDAO.setTeam(teamDAO);


        List<JenkinsDAO> jenkinsDAOS = new ArrayList<>();
        List<GitDAO> gitDAOS = new ArrayList<>();
        List<SonarDAO> sonarDAOS = new ArrayList<>();
        List<RepoDAO> repoDAOS = new ArrayList<>();
        List<MemberDAO> memberDAOS = new ArrayList<>();

        jenkinsDAOS.add(jenkinsDAO);
        gitDAOS.add(gitDAO);
        sonarDAOS.add(sonarDAO);
        repoDAOS.add(repoDAO);
        memberDAOS.add(memberDAO);


        teamDAO.setMembers(memberDAOS);
        teamDAO.setRepositories(repoDAOS);
        teamDAO.setCommitMetrics(gitDAOS);
        teamDAO.setJenkinsMetrics(jenkinsDAOS);



        when(mockService.getAllDetailsForTeamById(any())).thenReturn(teamDAO);
        mockMvc.perform(get("/metrics/team/1"))
                .andExpect(status().isOk());

    }

    private RepoDAO initRepo() {
        RepoDAO dao = new RepoDAO();
        dao.setRepoId(1l);
        dao.setRepoName("");
        dao.setUrl("");
        return dao;
    }

    private SonarDAO initSonar() {
        SonarDAO dao = new SonarDAO();
        dao.setMetricId(1l);
        dao.setBugs("");
        dao.setCodeSmells("");
        dao.setCoverage("");
        dao.setDuplications("");
        dao.setMetrics("");
        dao.setProjectName("");
        dao.setSecurityRating("");

        return dao;
    }

    private GitDAO initGit() {
        GitDAO dao = new GitDAO();
        dao.setMetricId(1l);
        dao.setNumActiveDays(1);
        dao.setNumCommits(1);
        dao.setNumLinesAdded(1);
        dao.setNumLinesDeleted(1);


        return  dao;
    }

    private JenkinsDAO initJenkins() {
        JenkinsDAO dao = new JenkinsDAO();
        dao.setMetricId(1l);
        dao.setNumIntegrationTestsPassing(1);
        dao.setNumOfExecutions(1);
        dao.setNumOfFailures(1);
        dao.setNumOfSuccess(1);
        dao.setNumUnitTestsPassing(1);
        dao.setTotalIntegrationTests(1);
        dao.setTotalUnitTests(1);

        return dao;

    }
}