package com.ericsson.tgf.kyzen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "BUILD_METRIC")
public class JenkinsDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metricId;

    private int numOfExecutions;

    private int numOfFailures;

    private int numOfSuccess;

    private int totalUnitTests;

    private int numUnitTestsPassing;

    private int totalIntegrationTests;

    private int numIntegrationTestsPassing;

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

    public int getNumOfExecutions() {
        return numOfExecutions;
    }

    public void setNumOfExecutions(int numOfExecutions) {
        this.numOfExecutions = numOfExecutions;
    }

    public int getNumOfFailures() {
        return numOfFailures;
    }

    public void setNumOfFailures(int numOfFailures) {
        this.numOfFailures = numOfFailures;
    }

    public int getNumOfSuccess() {
        return numOfSuccess;
    }

    public void setNumOfSuccess(int numOfSuccess) {
        this.numOfSuccess = numOfSuccess;
    }

    public int getTotalUnitTests() {
        return totalUnitTests;
    }

    public void setTotalUnitTests(int totalUnitTests) {
        this.totalUnitTests = totalUnitTests;
    }

    public int getNumUnitTestsPassing() {
        return numUnitTestsPassing;
    }

    public void setNumUnitTestsPassing(int numUnitTestsPassing) {
        this.numUnitTestsPassing = numUnitTestsPassing;
    }

    public int getTotalIntegrationTests() {
        return totalIntegrationTests;
    }

    public void setTotalIntegrationTests(int totalIntegrationTests) {
        this.totalIntegrationTests = totalIntegrationTests;
    }

    public int getNumIntegrationTestsPassing() {
        return numIntegrationTestsPassing;
    }

    public void setNumIntegrationTestsPassing(int numIntegrationTestsPassing) {
        this.numIntegrationTestsPassing = numIntegrationTestsPassing;
    }

    public TeamDAO getTeam() {
        return team;
    }

    public void setTeam(TeamDAO team) {
        this.team = team;
    }
}
