package com.ericsson.tgf.kyzen.repository;

import com.ericsson.tgf.kyzen.entity.GitDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitDAORepository extends JpaRepository<GitDAO,Long> {
}
