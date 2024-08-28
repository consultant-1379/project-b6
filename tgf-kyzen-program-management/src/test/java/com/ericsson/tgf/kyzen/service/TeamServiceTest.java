package com.ericsson.tgf.kyzen.service;

import com.ericsson.tgf.kyzen.entity.MemberDAO;
import com.ericsson.tgf.kyzen.entity.RepoDAO;
import com.ericsson.tgf.kyzen.entity.TeamDAO;
import com.ericsson.tgf.kyzen.model.Member;
import com.ericsson.tgf.kyzen.model.Team;
import com.ericsson.tgf.kyzen.repository.MemberDAORepository;
import com.ericsson.tgf.kyzen.repository.RepoDAORepository;
import com.ericsson.tgf.kyzen.repository.TeamDAORepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TeamServiceTest {

    public static final Long VALID_ID = 1l;

    @Autowired
    private TeamService teamService;

    @MockBean
    private TeamDAORepository teamDAORepository;

    @MockBean
    private MemberDAORepository memberDAORepository;

    @MockBean
    private RepoDAORepository repoDAORepository;


    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void addNewTeam() {
        TeamDAO teamDAO = new TeamDAO();
        List<MemberDAO> memberDAOList = new ArrayList<>();
        List<RepoDAO> repoDAOList = new ArrayList<>();
        MemberDAO memberDAO = new MemberDAO();
        memberDAO.setTeam(teamDAO);
        memberDAOList.add(memberDAO);

        RepoDAO repoDAO = new RepoDAO();
        repoDAO.setTeam(teamDAO);
        repoDAOList.add(repoDAO);

        teamDAO.setRepositories(repoDAOList);
        teamDAO.setMembers(memberDAOList);

        when(memberDAORepository.saveAll(any()))
                .thenReturn(memberDAOList);
        when(repoDAORepository.saveAll(any()))
                .thenReturn(repoDAOList);

        when(teamDAORepository.save(any())).thenReturn(teamDAO);

        Team team = teamService.addNewTeam(modelMapper.map(teamDAO, Team.class));

        assertThat(team, is(not(nullValue())));
    }
}