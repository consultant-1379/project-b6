package com.tgfkyzen.sonarqube.sonarqube;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class SonarQubeController {
    @Autowired
    SonarQubeService sonarQubeService;


    @GetMapping("/sonarqubemetrics/{projectname}")
   SonarQubeDOA getSonarQubeMetrics(@PathVariable String projectname)
    {
      return sonarQubeService.findProjectById(projectname);

    }
    @GetMapping("/sonarqubemetrics")
    List<SonarQubeDOA> getAllSonarQubeData()
    {
        return sonarQubeService.getAllSonarData();
    }

    @GetMapping("/allProjects")
    List<String> getAllProjects()
    {
        return sonarQubeService.findAllProjects();

    }



}
