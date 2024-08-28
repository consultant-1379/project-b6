package com.ericsson.jenkins;

import com.ericsson.jenkins.Model.BuildResponse;
import com.ericsson.jenkins.Model.TestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

@RestController
public class JenkinsController {

    @Autowired
    JenkinsService jenkinsService;

    @GetMapping("/jenkins/build/{job}")
    public ResponseEntity<BuildResponse> getData(@PathVariable String job) throws IOException, URISyntaxException {

        HashMap<String,HashMap<String, Integer>> responseDict = new HashMap<String, HashMap<String, Integer>>();
        //for(String s: req){
        System.out.println(job);
        HashMap<String, Integer> data = jenkinsService.getBuildData(job);
        responseDict.put(job,data);
        //}
        BuildResponse resp = new BuildResponse(responseDict);
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/jenkins/test/{job}")
    public ResponseEntity<TestResponse> getTestData(@PathVariable String job) throws IOException, URISyntaxException {
        HashMap<String, HashMap<String, Integer>> responseDict = new HashMap<>();

        //for(String s: req){
        System.out.println("S: " +job);
        HashMap<String,Integer> testData = jenkinsService.getAllTestData(job);
        responseDict.put(job,testData);
        //}
        TestResponse resp = new TestResponse(responseDict);
        return ResponseEntity.ok().body(resp);
    }

}
