package com.ericsson.tgf.kyzen.repository;

import com.ericsson.tgf.kyzen.entity.TeamDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDAORepository extends JpaRepository<TeamDAO,Long> {
    Iterable<TeamDAO> findByNameContainingIgnoreCase(String name);
}
