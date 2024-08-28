package com.ericsson.tgf.kyzen.repository;

import com.ericsson.tgf.kyzen.entity.RepoDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoDAORepository extends JpaRepository<RepoDAO,Long> {
}
