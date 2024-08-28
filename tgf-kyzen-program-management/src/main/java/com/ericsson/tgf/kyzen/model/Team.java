package com.ericsson.tgf.kyzen.model;

import com.ericsson.tgf.kyzen.entity.GitDAO;
import com.ericsson.tgf.kyzen.entity.JenkinsDAO;
import com.ericsson.tgf.kyzen.entity.RepoDAO;

import java.util.ArrayList;
import java.util.List;


public class Team {


    private Long teamId;

    private String name;


    private List<Member> members = new ArrayList<>();


    private List<RepoDAO> repositories;

    private List<GitDAO> commitMetrics;

    private List<JenkinsDAO> jenkinsMetrics;


    public Team() { //default
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<RepoDAO> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<RepoDAO> repositories) {
        this.repositories = repositories;
    }

    public List<GitDAO> getCommitMetrics() {
        return commitMetrics;
    }

    public void setCommitMetrics(List<GitDAO> commitMetrics) {
        this.commitMetrics = commitMetrics;
    }

    public List<JenkinsDAO> getJenkinsMetrics() {
        return jenkinsMetrics;
    }

    public void setJenkinsMetrics(List<JenkinsDAO> jenkinsMetrics) {
        this.jenkinsMetrics = jenkinsMetrics;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", name='" + name + '\'' +
                '}';
    }



}
