package com.ericsson.tgf.kyzen.controller;

import com.ericsson.tgf.kyzen.model.Member;
import com.ericsson.tgf.kyzen.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @ParameterizedTest
    @ValueSource(strings = {"NOTEAM", ""})
    void verify_getfreemembers_for_team(String team) throws Exception {

        Member member = new Member();
        List<Member> members = new ArrayList<>();
        members.add(member);

        when(memberService.getAllFreeMembers()).thenReturn(members);

        mockMvc.perform(get("/members?team="+team)).andExpect(status().isOk());
    }



    @Test
    void verify_getmembers() throws Exception {

        Member member = new Member();
        member.setMemberId(1l);
        List<Member> members = new ArrayList<>();
        members.add(member);

        when(memberService.getAllFreeMembers()).thenReturn(members);

        mockMvc.perform(get("/members")).andExpect(status().isOk());
    }
}