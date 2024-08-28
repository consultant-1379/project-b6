package com.ericsson.tgf.kyzen.controller;

import com.ericsson.tgf.kyzen.model.Team;
import com.ericsson.tgf.kyzen.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping
    public ResponseEntity<Void> createNewTeam(@RequestBody Team team){

        Team savedTeam = teamService.addNewTeam(team);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTeam.getTeamId()).toUri();

        return ResponseEntity.created(location).build();


    }
}
