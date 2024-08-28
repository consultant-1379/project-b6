package com.tgfkyzen.sonarqube.sonarqube;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SonarQubeRepository extends JpaRepository<SonarQubeDOA,String> {
    @Query(value="select * from QualityGate g where g.Proj_Name = ?1", nativeQuery = true)
    SonarQubeDOA findProjectById(String projectName);

    @Query(value = "Select * from QualityGate",nativeQuery = true)
    List<SonarQubeDOA> getAllSonarData();

    @Query(value = "Select g.Proj_Name from QualityGate g",nativeQuery = true)
    List<String> findAllProjects();
}
