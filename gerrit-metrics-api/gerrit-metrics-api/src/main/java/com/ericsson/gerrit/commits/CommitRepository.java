package com.ericsson.gerrit.commits;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommitRepository extends JpaRepository<Commit,String> {
	
	@Query(value="select * from commits order by c_date", nativeQuery = true)
	List<Commit> findAllCommits();

	@Query(value="select c from Commit c where c.c_author = ?1")
	List<Commit> findAllCommitsByAuthor(String author);

	@Query(value="select c from Commit c where c.c_date between  ?1 and ?2")
	List<Commit> findAllCommitsInDateRange(Date start, Date end);

	@Query(value="select * from all_details d  where d.c_date between  ?2 and ?3 and d.t_name=?1",nativeQuery = true )
	List<Commit> findAllCommitsWithinDateRangeByTeamName(String tName,Date start, Date end);


	@Query(value="select count(d.c_hash) from all_details d  where d.c_date = ?2 and d.t_name=?1",nativeQuery = true )
	Long findAllCommitsPerDayByTeam(String tName,Date day);


	@Query(value="select sum(d.c_lines_added) from all_details d  where d.c_date = ?2 and d.t_name=?1",nativeQuery = true )
	Long findCommitLinesAddedPerDayByTeam(String team, Date day);

	@Query(value="select sum(d.c_lines_deleted) from all_details d  where d.c_date = ?2 and d.t_name=?1",nativeQuery = true )
	Long findCommitLinesDeletedPerDayByTeam(String team, Date day);

	@Query(value="select distinct d.c_date from all_details d  where d.c_date between  ?2 and ?3 and d.t_name=?1",nativeQuery = true )
	List<Date> findActiveDaysInAWeek(String team, Date start, Date end);

	@Query(value="select * from all_details d  where d.t_name=?1 order by d.c_date",nativeQuery = true )
	List<Commit> findAllCommitsByTeamName(String teamName);
}
