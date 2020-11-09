package com.example.practice.service;


import com.example.practice.model.Member;
import com.example.practice.model.Role;
import com.example.practice.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Member save(Member member){
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        member.setEnabled(1);

        Role role = new Role();
        role.setId(1l);
        member.getRoles().add(role);
        return memberRepository.save(member);
    }

}
