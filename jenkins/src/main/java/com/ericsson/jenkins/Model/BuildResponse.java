package com.ericsson.jenkins.Model;

import java.util.HashMap;

public class BuildResponse {
    HashMap<String,HashMap<String,Integer>> buildDataDictionary;

    public BuildResponse(){};
    public BuildResponse(HashMap<String, HashMap<String, Integer>> buildDataDictionary) {
        this.buildDataDictionary = buildDataDictionary;
    }

    public HashMap<String,HashMap<String,Integer>> getBuildDataDictionary() {
        return buildDataDictionary;
    }
}
