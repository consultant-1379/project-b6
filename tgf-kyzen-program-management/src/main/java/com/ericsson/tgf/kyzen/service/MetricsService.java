package com.ericsson.tgf.kyzen.service;


import com.ericsson.tgf.kyzen.entity.*;
import com.ericsson.tgf.kyzen.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MetricsService {

    @Autowired
    private TeamDAORepository teamDAORepository;


    public TeamDAO getTeamDAOById(Long id){

        Optional<TeamDAO> teamDAOOptional = teamDAORepository.findById(id);

        if (teamDAOOptional.isEmpty()) return null;

        return teamDAOOptional.get();


    }

    public TeamDAO getAllDetailsForTeamById(Long id){
        TeamDAO teamDAO = getTeamDAOById(id);

        if (teamDAO == null) return null;

        teamDAO.getMembers();
        teamDAO.getCommitMetrics();
        teamDAO.getJenkinsMetrics();
        return teamDAO;
    }





}
