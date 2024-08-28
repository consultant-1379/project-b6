package com.ericsson.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class JenkinsServiceImpl implements JenkinsService {

    @Value("${jenkins-url}")
    private String url;

    // @Value("${jenkins-username}")
    private String username;


    //@Value("${jenkins-password}")
    private String password;

    private Map<String, Job> jobs;
    private HashMap<String, HashMap<String, Integer>> cache = new HashMap<String, HashMap<String, Integer>>();
    private JenkinsServer jenkinsServer;
    private HashMap<String, Integer> dict  =new HashMap<>();
    private Map<String, Job> jobMap = new HashMap<String, Job>();
    private ArrayList<String> teamJobs = new ArrayList<String>();
    private HashMap<String, JobWithDetails> relaventJobs = new HashMap<String, JobWithDetails>();
    private HashMap<String, BuildWithDetails> releventBuilds = new HashMap<String, BuildWithDetails>();


    @Override
    public String initialize() throws IOException, URISyntaxException {
        jenkinsServer = new JenkinsServer(new URI("https://fem26s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins"), "EGILCAO", "Leaffon3y1");
        jobMap = jenkinsServer.getJobs();

        System.out.println(jobMap.keySet());
        teamJobs.add("asrl-correlation_PCR");
        teamJobs.add("asrl-rest-api_PCR");
        teamJobs.add("asr-forwarder_PCR");
        teamJobs.add("asrl-service-def_sg_PCR");
        teamJobs.add("asrl-record-model_PCR");
        teamJobs.add("asrl-configuration-model_PCR");

        RestTemplate rt = new RestTemplate();
        String URL = "http://localhost:8080/team/repos/";
        int id = 2;


        for (String s : teamJobs) {
            if (jobMap.containsKey(s)) {
                relaventJobs.put(s, jobMap.get(s).details());
                List<Build> jobBuilds = relaventJobs.get(s).getAllBuilds();
                for (Build b : jobBuilds) {
                    releventBuilds.put(s + "_Build_No_" + b.getNumber(), b.details());
                    System.out.println(s + "_Build_No_" + b.getNumber() + ": " + b.details());
                }
            }
        }



        System.out.println("Initialization Complete");

        return "Initialization Complete";
    }

    @Override
    public HashMap<String, Integer> getBuildData(String myJob) throws IOException, URISyntaxException {

        int successCount = 0;
        int failCount = 0;
        int notBuiltCount = 0;
        //JenkinsServer jenkinsServer = new JenkinsServer(new URI("https://fem26s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins"), "EGILCAO", "Leaffon3y1");
        //JobWithDetails job = jenkinsServer.getJob(myJob);
        if (relaventJobs.containsKey(myJob)) {
            JobWithDetails job = relaventJobs.get(myJob);
            List<Build> buildList = job.getAllBuilds();

            for (Build build : buildList) {
                int buildNumber = build.getNumber();
                BuildWithDetails buildWithDetails = job.getBuildByNumber(buildNumber).details();
                String result = buildWithDetails.getResult().toString();
                if (result.equals("SUCCESS")) {
                    successCount++;
                } else if (result.equals("FAILURE")) {
                    failCount++;
                } else if (result.equals("NOT_BUILT")) {
                    notBuiltCount++;
                }

            }
            HashMap<String, Integer> buildDataDictionary = new HashMap<String, Integer>();
            buildDataDictionary.put("Success Count", successCount);
            buildDataDictionary.put("Fail Count", failCount);
            buildDataDictionary.put("Not Built Count", notBuiltCount);
            buildDataDictionary.put("Total", (successCount + failCount + notBuiltCount));
            return buildDataDictionary;
        } else return null;

    }
    @Override
    public HashMap<String, Integer> getAllTestData(String myJob) throws IOException, URISyntaxException{

        if (cache.containsKey(myJob))
            return cache.get(myJob);
        else{

            int passCount = 0;
            int failCount = 0;
            int skipCount = 0;
            int totalCount = 0;
            int buildNeverRanCount = 0;
            int buildsWithoutTests = 0;
            //JenkinsServer jenkinsServer = new JenkinsServer(new URI("https://fem26s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins"), "EGILCAO", "Leaffon3y1");
            JobWithDetails job = relaventJobs.get(myJob);
            List<Build> buildList = job.getAllBuilds();
            System.out.println("Loading Test Data");
            for(Build build: buildList){

                if(Build.BUILD_HAS_NEVER_RUN.equals(build)){
                    System.out.println("BUILD NEVER RAN");
                    buildNeverRanCount += 1;
                }
                else {
                    try {
                        passCount += build.details().getTestResult().getPassCount();
                        failCount += build.details().getTestResult().getFailCount();
                        skipCount += build.details().getTestResult().getFailCount();
                        totalCount += passCount + failCount + skipCount;
                    }
                    catch(Exception e){
                        buildsWithoutTests += 1;
                    }
                }
            }
            dict.put("Passed Tests", passCount);
            dict.put("Failed Tests", failCount);
            dict.put("Skip Tests", skipCount);
            dict.put("Total Tests", totalCount);
            dict.put("Builds Never Ran", buildNeverRanCount);
            dict.put("Builds Without Tests", buildsWithoutTests);
            cache.put(myJob,dict);
            System.out.println("Complete");

            return dict;
        }
    }
}










    /*@Override
    public HashMap<String, Integer> getAllTestData(String myJob) throws IOException, URISyntaxException {

        if (relaventJobs.containsKey(myJob)) {
            JobWithDetails job = relaventJobs.get(myJob);
            List<Build> buildList = job.getAllBuilds();
            if (cache.containsKey(myJob))
                return cache.get(myJob);
            else {

                int passCount = 0;
                int failCount = 0;
                int skipCount = 0;
                int totalCount = 0;
                int buildNeverRanCount = 0;
                int buildsWithoutTests = 0;
                //JenkinsServer jenkinsServer = new JenkinsServer(new URI("https://fem26s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins"), "EGILCAO", "Leaffon3y1");
                //JobWithDetails job = jenkinsServer.getJob(myJob)


                System.out.println("Loading Test Data");
                for (Build build : buildList) {

                    if (Build.BUILD_HAS_NEVER_RUN.equals(build)) {
                        System.out.println("BUILD NEVER RAN");
                        buildNeverRanCount += 1;
                    }
                    else {
                        try {
                            passCount += build.details().getTestResult().getPassCount();
                            failCount += build.details().getTestResult().getFailCount();
                            skipCount += build.details().getTestResult().getFailCount();
                            totalCount += passCount + failCount + skipCount;
                        } catch (Exception e) {
                            buildsWithoutTests += 1;
                        }
                    }
                }
                HashMap<String, Integer> dict = new HashMap<>();
                dict.put("Passed Tests", passCount);
                dict.put("Failed Tests", failCount);
                dict.put("Skip Tests", skipCount);
                dict.put("Total Tests", totalCount);
                dict.put("Builds Never Ran", buildNeverRanCount);
                dict.put("Builds Without Tests", buildsWithoutTests);
                cache.put(myJob, dict);
                System.out.println("Complete");

                return null;
            }
        }
    }*/






