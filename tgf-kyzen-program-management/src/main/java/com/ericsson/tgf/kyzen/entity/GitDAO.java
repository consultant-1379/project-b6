package com.ericsson.tgf.kyzen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "CODE_METRIC")
public class GitDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metricId;

    private int numCommits;

    private int numLinesAdded;

    private int numLinesDeleted;

    private int numActiveDays;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="team_id", nullable = false)
    private TeamDAO team;


    public Long getMetricId() {
        return metricId;
    }

    public void setMetricId(Long metricId) {
        this.metricId = metricId;
    }

    public int getNumCommits() {
        return numCommits;
    }

    public void setNumCommits(int numCommits) {
        this.numCommits = numCommits;
    }

    public int getNumLinesAdded() {
        return numLinesAdded;
    }

    public void setNumLinesAdded(int numLinesAdded) {
        this.numLinesAdded = numLinesAdded;
    }

    public int getNumLinesDeleted() {
        return numLinesDeleted;
    }

    public void setNumLinesDeleted(int numLinesDeleted) {
        this.numLinesDeleted = numLinesDeleted;
    }

    public int getNumActiveDays() {
        return numActiveDays;
    }

    public void setNumActiveDays(int numActiveDays) {
        this.numActiveDays = numActiveDays;
    }

    public TeamDAO getTeam() {
        return team;
    }

    public void setTeam(TeamDAO team) {
        this.team = team;
    }
}
