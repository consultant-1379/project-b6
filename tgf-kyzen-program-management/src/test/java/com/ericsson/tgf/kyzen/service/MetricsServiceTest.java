package com.ericsson.tgf.kyzen.service;

import com.ericsson.tgf.kyzen.entity.TeamDAO;
import com.ericsson.tgf.kyzen.repository.TeamDAORepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class MetricsServiceTest {


    public static final Long VALID_ID = 1l;
    @Autowired
    private MetricsService metricsService;

    @MockBean
    private TeamDAORepository mockRepository;

    @Autowired
    private ModelMapper modelMapper;

    private TeamDAO teamDAO;

    @BeforeEach
    void setUp() {

        teamDAO = new TeamDAO();
        teamDAO.setTeamId(VALID_ID);

        when(mockRepository.findById(VALID_ID)).thenReturn(Optional.of(teamDAO));


    }

    @Test
    void verify_getTeamDAOById_returns_null_for_invalid_id() {
        when(mockRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThat(metricsService.getTeamDAOById(2l),is(nullValue()));
    }

    @Test
    void verify_getTeamDAOById_returns() {
        TeamDAO teamDAO = metricsService.getTeamDAOById(VALID_ID);
        assertThat(teamDAO, is(not(nullValue())));

    }

    @Test
    void verify_getAllDetailsForTeamById() {
        TeamDAO teamDAO = metricsService.getAllDetailsForTeamById(VALID_ID);
        assertThat(teamDAO, is(not(nullValue())));
    }
}