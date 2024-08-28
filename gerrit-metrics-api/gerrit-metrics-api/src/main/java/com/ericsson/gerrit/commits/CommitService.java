package com.ericsson.gerrit.commits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommitService{

    @Autowired
    CommitRepository commitRepository;

    public List<Commit> getAllCommits() {
        return commitRepository.findAllCommits();
    }

    public List<Commit> getAllCommitsByName(String author) {
        return commitRepository.findAllCommitsByAuthor(author);

    }

    public List<Commit> getAllCommitsInDateRange(Date start, Date end) {
        return commitRepository.findAllCommitsInDateRange(start, end);
    }

    public List<Commit> getAllCommitsInDateRangeByTeamName(String tName,Date start, Date end) {
        return commitRepository.findAllCommitsWithinDateRangeByTeamName(tName,start,end);
    }


    public Long getCommitsPerDayByTeam(String team, Date day) {
        return commitRepository.findAllCommitsPerDayByTeam(team, day);
    }

    public Long getCommitLinesAddedPerDayByTeam(String team, Date day) {
        return commitRepository.findCommitLinesAddedPerDayByTeam(team,day);
    }

    public Long getCommitLinesDeletedPerDayByTeam(String team, Date day) {
        return commitRepository.findCommitLinesDeletedPerDayByTeam(team,day);
    }

    public Long getActiveDaysInAWeek(String team, Date start, Date end) {
        List<Date> c_dates = commitRepository.findActiveDaysInAWeek(team,start,end);
        return Long.parseLong(""+c_dates.size());
    }

    public List<Commit> getAllCommitsByTeamName(String teamName) {
        return commitRepository.findAllCommitsByTeamName(teamName);
    }
}
