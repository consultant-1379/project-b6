package com.ericsson.jenkins.Model;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class TestResponse {
    private HashMap<String, HashMap<String,Integer>> testDict;
    public TestResponse(){}

    @Autowired
    public TestResponse(HashMap<String,HashMap<String,Integer>> testDict){
        this.testDict = testDict;
    }

    public HashMap<String,HashMap<String,Integer>> getTestDict() {
        return testDict;
    }
}
