package com.ericsson.tgf.kyzen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="MEMBER")
public class MemberDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String name;

    private String signum;

    private String email;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="team_id", nullable = false)
    private TeamDAO team;





    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignum() {
        return signum;
    }

    public void setSignum(String signum) {
        this.signum = signum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TeamDAO getTeam() {
        return team;
    }

    public void setTeam(TeamDAO team) {
        this.team = team;
    }
}
