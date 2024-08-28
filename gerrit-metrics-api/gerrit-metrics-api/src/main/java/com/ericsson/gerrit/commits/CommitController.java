package com.ericsson.gerrit.commits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;


@CrossOrigin
@RestController
public class
CommitController {

    @Autowired
    CommitService commitService;

    @GetMapping("/allCommits")
    public List<Commit> getAllCommits() {
        return commitService.getAllCommits();

    }

    @GetMapping("/allCommits/{teamName}")
    public List<Commit> getAllCommitsByTeamName(@PathVariable String teamName) {
        return commitService.getAllCommitsByTeamName(teamName);

    }

    @GetMapping("/commits/{author}")
    public List<Commit> getCommitsByAuthorName(@PathVariable String author){
        return commitService.getAllCommitsByName(author);

    }

    @GetMapping("/commits/range")
    public List<Commit> getCommitsByDateRange(@RequestParam Date start, @RequestParam Date end){
        return commitService.getAllCommitsInDateRange(start,end);

    }

    @GetMapping("/commits/commitsPerDay")
    public Long getCommitsPerDayByTeam(@RequestParam String team, @RequestParam Date day){
        return commitService.getCommitsPerDayByTeam(team, day);

    }
    @GetMapping("/commits/commitLinesDeleted")
    public Long getNumberOfCommitLinesDeletedPerDayByTeam(@RequestParam String team, @RequestParam Date day){
        return commitService.getCommitLinesDeletedPerDayByTeam(team, day);

    }

    @GetMapping("/commits/commitLinesAdded")
    public Long getNumberOfCommitLinesAddedBPerDayByTeam(@RequestParam String team, @RequestParam Date day){
    return commitService.getCommitLinesAddedPerDayByTeam(team, day);

    }

    @GetMapping("/commits/commitsDateTeam")
    public List<Commit> getCommitsByDateRangeByTeam(@RequestParam String team, @RequestParam Date start, @RequestParam Date end){
        return commitService.getAllCommitsInDateRangeByTeamName(team, start,end);

    }

    @GetMapping("/commits/activeDaysInAWeek")
    public Long getActiveDaysInAWeek(@RequestParam String team, @RequestParam Date start, @RequestParam Date end){
        return commitService.getActiveDaysInAWeek(team, start,end);

    }


}
