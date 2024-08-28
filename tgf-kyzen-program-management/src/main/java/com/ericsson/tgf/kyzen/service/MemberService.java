package com.ericsson.tgf.kyzen.service;

import com.ericsson.tgf.kyzen.entity.MemberDAO;
import com.ericsson.tgf.kyzen.model.Member;
import com.ericsson.tgf.kyzen.repository.MemberDAORepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MemberService {

    @Autowired
    private MemberDAORepository memberDAORepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Member> getMemberByName(String name){
        Iterable<MemberDAO> memberDAOS = memberDAORepository.findByNameContainingIgnoreCase(name);
        return StreamSupport.stream(memberDAOS.spliterator(),false)
                .map(memberDAO -> modelMapper.map(memberDAO,Member.class))
                .collect(Collectors.toList());
    }


    public Optional<Member> getMemberById(Long id){
        Optional<MemberDAO> memberOptional = memberDAORepository.findById(id);
        return memberOptional.isPresent() ? Optional.of(modelMapper.map(memberOptional.get(),Member.class)) : Optional.empty();

    }

    public List<Member> getAllFreeMembers(){
        return memberDAORepository.findAllFreeMembers()
                .stream()
                .map(memberDAO -> modelMapper.map(memberDAO,Member.class))
                .collect(Collectors.toList());

    }

}
