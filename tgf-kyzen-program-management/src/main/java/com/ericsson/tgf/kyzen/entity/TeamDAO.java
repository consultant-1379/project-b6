package com.ericsson.tgf.kyzen.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TEAM")
public class TeamDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "team")
    private List<MemberDAO> members;

    @JsonManagedReference
    @OneToMany(mappedBy = "team")
    private List<GitDAO> commitMetrics;

    @JsonManagedReference
    @OneToMany(mappedBy = "team")
    private List<JenkinsDAO> jenkinsMetrics;

    @JsonManagedReference
    @OneToMany(mappedBy = "team")
    private List<SonarDAO> sonarMetrics;


    @JsonManagedReference
    @OneToMany(mappedBy = "team")
    private List<RepoDAO> repositories;

    public List<RepoDAO> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<RepoDAO> repositories) {
        this.repositories = repositories;
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

    public List<MemberDAO> getMembers() {
        return members;
    }

    public void setMembers(List<MemberDAO> members) {
        this.members = members;
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

    public List<SonarDAO> getSonarMetrics() {
        return sonarMetrics;
    }

    public void setSonarMetrics(List<SonarDAO> sonarMetrics) {
        this.sonarMetrics = sonarMetrics;
    }
}
