package com.tgfkyzen.sonarqube.sonarqube;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="qualitygate")
public class SonarQubeDOA {
    @Id
    private String Proj_Name;
    private String Metrics;
    private String Bugs;
    private String CodeSmells;
    private String Coverage;
    private String Duplications;
    private String security_rating;

    public SonarQubeDOA(){}

    public SonarQubeDOA(String project_name, String metrics, String Bugs, String codeSmells, String coverage, String duplications, String security_rating) {
        this.Proj_Name = project_name;
        this.Metrics = metrics;
        this.Bugs = Bugs;
        this.CodeSmells = codeSmells;
        this.Coverage = coverage;
        this.Duplications = duplications;
        this.security_rating = security_rating;
    }

    public String getProject_name() {
        return Proj_Name;
    }

    public String getMetrics() {
        return Metrics;
    }

    public String getBugs() {
        return Bugs;
    }

    public String getCodeSmells() {
        return CodeSmells;
    }

    public String getCoverage() {
        return Coverage;
    }

    public String getDuplications() {
        return Duplications;
    }

    public String getSecurity_rating() {
        return security_rating;
    }

    public void setProject_name(String project_name) {
        this.Proj_Name = project_name;
    }

    public void setMetrics(String metrics) {
        Metrics = metrics;
    }

    public void setBugs(String bugs) {
        Bugs = bugs;
    }

    public void setCodeSmells(String codeSmells) {
        CodeSmells = codeSmells;
    }

    public void setCoverage(String coverage) {
        Coverage = coverage;
    }

    public void setDuplications(String duplications) {
        Duplications = duplications;
    }

    public void setSecurity_rating(String security_rating) {
        this.security_rating = security_rating;
    }

}
