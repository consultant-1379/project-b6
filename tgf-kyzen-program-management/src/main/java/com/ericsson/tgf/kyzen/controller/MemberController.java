package com.ericsson.tgf.kyzen.controller;

import com.ericsson.tgf.kyzen.model.Member;
import com.ericsson.tgf.kyzen.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;


    @GetMapping
    public ResponseEntity<List<Member>> getMembers(@RequestParam(required = false) String team ){
        List<Member> respList= new ArrayList<>();
        if (team != null && !team.isEmpty() && team.equals("NOTEAM") ){



            respList = memberService.getAllFreeMembers();


        }
        return ResponseEntity.ok().body(respList);

    }


}
