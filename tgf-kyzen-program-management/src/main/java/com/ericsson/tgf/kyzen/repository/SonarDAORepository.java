package com.ericsson.tgf.kyzen.repository;

import com.ericsson.tgf.kyzen.entity.SonarDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SonarDAORepository extends JpaRepository<SonarDAO,Long> {
}
