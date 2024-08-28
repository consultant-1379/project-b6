package com.ericsson.tgf.kyzen.controller;

import com.ericsson.tgf.kyzen.entity.RepoDAO;
import com.ericsson.tgf.kyzen.model.Member;
import com.ericsson.tgf.kyzen.model.Team;
import com.ericsson.tgf.kyzen.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;


    @Test
    void verify_createNewTeam() throws Exception {
        RepoDAO repoDAO = new RepoDAO();
        Member member = new Member();

        List<RepoDAO> repoDAOS = new ArrayList<>();
        List<Member> members = new ArrayList<>();
        members.add(member);
        repoDAOS.add(repoDAO);

        Team team = new Team();
        team.setMembers(members);
        team.setRepositories(repoDAOS);

        when(teamService.addNewTeam(ArgumentMatchers.any()))
                .thenReturn(team);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        String jsonContent = mapper.writer().writeValueAsString(member);

        mockMvc.perform(post("/team")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isCreated());

    }
}