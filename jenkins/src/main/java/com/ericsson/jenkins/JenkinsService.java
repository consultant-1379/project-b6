package com.ericsson.jenkins;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

public interface JenkinsService {
    String initialize()  throws IOException, URISyntaxException;
    HashMap<String,Integer> getBuildData(String myJob) throws IOException, URISyntaxException;    /*String getAllBuilds()throws IOException, URISyntaxException;*/
    HashMap<String,Integer> getAllTestData(String job)throws IOException, URISyntaxException;

}

//https://github.com/jenkinsci/java-client-api

