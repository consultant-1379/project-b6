package com.ericsson.tgf.kyzen.model;


public class Member {


    private Long memberId;

    private String name;
    
    private String signum;

    private String email;


    public Member() { //default
    }

    public Member(Long id, String name) {
        this.memberId = id;
        this.signum = name;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long signum) {
        this.memberId = signum;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

