package com.ericsson.tgf.kyzen.service;

import com.ericsson.tgf.kyzen.entity.MemberDAO;
import com.ericsson.tgf.kyzen.model.Member;
import com.ericsson.tgf.kyzen.repository.MemberDAORepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @MockBean
    private MemberDAORepository memberDAORepository;

    @Autowired
    private ModelMapper modelMapper;

    private Member member = new Member();
    private List<MemberDAO> memberDAOS;
    private List<Member> members;

    @BeforeEach
    public void setUp(){
        member = new Member();
        member.setName("CK");
        member.setMemberId(2l);
        member.setSignum("cKakakakak");
        member.setEmail("red@shift.com");

        members = new ArrayList<>();
        members.add(member);

        memberDAOS = new ArrayList<>();
        memberDAOS.add(modelMapper.map(member,MemberDAO.class));


        when(memberDAORepository.findByNameContainingIgnoreCase(member.getName()))
                .thenReturn(memberDAOS);

        when(memberDAORepository.findById(member.getMemberId()))
                .thenReturn(Optional.ofNullable(memberDAOS.get(0)));

        when(memberDAORepository.findAllFreeMembers())
                .thenReturn(memberDAOS);

    }

    @Test
    void verify_get_member_by_name() {
        List<Member> membersList = memberService.getMemberByName(member.getName());
        assertThat(membersList.size(), Matchers.is(1));


    }

    @Test
    void verify_get_member_by_id() {
        Long MEMBER_ID = 2l;
        Optional<Member> memberOptional =  memberService.getMemberById(MEMBER_ID);
        Member memberServiceResp = memberOptional.get();
        assertThat(memberServiceResp.getMemberId(), Matchers.is(MEMBER_ID));

    }

    @Test
    void verify_get_all_free_members() {

        List<Member> membersList = memberService.getAllFreeMembers();
        assertThat(membersList.size(), Matchers.is(1));

    }
}