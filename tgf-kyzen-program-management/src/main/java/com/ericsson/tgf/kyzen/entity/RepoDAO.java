package com.ericsson.tgf.kyzen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="TEAM_REPO")
public class RepoDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repoId;

    private String repoName;

    private String url;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="team_id", nullable = false)
    private TeamDAO team;



    public Long getRepoId() {
        return repoId;
    }

    public void setRepoId(Long repoId) {
        this.repoId = repoId;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TeamDAO getTeam() {
        return team;
    }

    public void setTeam(TeamDAO team) {
        this.team = team;
    }
}
