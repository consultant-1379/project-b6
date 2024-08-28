package com.tgfkyzen.sonarqube.sonarqube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SonarQubeService {
    @Autowired
    SonarQubeRepository repository;


    public SonarQubeDOA findProjectById(String projectname) {
        return repository.findProjectById(projectname);
    }

    public List<String> findAllProjects() {
        return repository.findAllProjects();
    }

    public List<SonarQubeDOA> getAllSonarData() {
        return repository.getAllSonarData();
    }
}
