package com.ericsson.tgf.kyzen.service;

import com.ericsson.tgf.kyzen.entity.MemberDAO;
import com.ericsson.tgf.kyzen.entity.RepoDAO;
import com.ericsson.tgf.kyzen.entity.TeamDAO;
import com.ericsson.tgf.kyzen.model.Team;
import com.ericsson.tgf.kyzen.repository.MemberDAORepository;
import com.ericsson.tgf.kyzen.repository.RepoDAORepository;
import com.ericsson.tgf.kyzen.repository.TeamDAORepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TeamDAORepository teamDAORepository;

    @Autowired
    private MemberDAORepository memberDAORepository;

    @Autowired
    private RepoDAORepository repoDAORepository;

    public Team addNewTeam(Team team){
        TeamDAO teamDAO =  modelMapper.map(team,TeamDAO.class);
        teamDAO = teamDAORepository.save(teamDAO);

        for (MemberDAO memberDAO : teamDAO.getMembers()){
            memberDAO.setTeam(teamDAO);
        }
        memberDAORepository.saveAll(teamDAO.getMembers());

        for (RepoDAO repoDAO : teamDAO.getRepositories()){
            repoDAO.setTeam(teamDAO);
        }
        repoDAORepository.saveAll(teamDAO.getRepositories());

        return modelMapper.map(teamDAO, Team.class);

    }



}
