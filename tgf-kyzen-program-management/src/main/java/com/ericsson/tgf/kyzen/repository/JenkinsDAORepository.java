package com.ericsson.tgf.kyzen.repository;

import com.ericsson.tgf.kyzen.entity.JenkinsDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JenkinsDAORepository extends JpaRepository<JenkinsDAO,Long> {
}
