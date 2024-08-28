package com.ericsson.tgf.kyzen.repository;


import com.ericsson.tgf.kyzen.entity.MemberDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDAORepository  extends JpaRepository<MemberDAO,Long> {
    Iterable<MemberDAO> findByNameContainingIgnoreCase(String name);

    @Query(value = "Select * from member m where m.team_id = 1",nativeQuery = true)
    List<MemberDAO> findAllFreeMembers();
}
