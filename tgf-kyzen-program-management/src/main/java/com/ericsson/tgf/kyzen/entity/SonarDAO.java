package com.ericsson.tgf.kyzen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


@Entity
@Table(name="sonar_metric")
public class SonarDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metricId;

    private String securityRating;

    private String codeSmells;

    private String duplications;

    private String projectName;

    private String metrics;

    private String coverage;

    private String bugs;

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

    public String getSecurityRating() {
        return securityRating;
    }

    public void setSecurityRating(String securityRating) {
        this.securityRating = securityRating;
    }

    public String getCodeSmells() {
        return codeSmells;
    }

    public void setCodeSmells(String codeSmells) {
        this.codeSmells = codeSmells;
    }

    public String getDuplications() {
        return duplications;
    }

    public void setDuplications(String duplications) {
        this.duplications = duplications;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMetrics() {
        return metrics;
    }

    public void setMetrics(String metrics) {
        this.metrics = metrics;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public TeamDAO getTeam() {
        return team;
    }

    public void setTeam(TeamDAO team) {
        this.team = team;
    }

    public String getBugs() {
        return bugs;
    }

    public void setBugs(String bugs) {
        this.bugs = bugs;
    }
}
