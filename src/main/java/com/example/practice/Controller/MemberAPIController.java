package com.example.practice.controller;

import java.util.List;

import com.example.practice.model.Board;
import com.example.practice.model.Member;
import com.example.practice.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemberAPIController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/member")
    List<Member> all() {
        return memberRepository.findAll();
    }

    @PostMapping("/member")
    Member newMember(@RequestBody Member newMember) {
        return memberRepository.save(newMember);
    }

    // Single item

    @GetMapping("/member/{id}")
    Member one(@PathVariable Long id) {

        return memberRepository.findById(id).orElse(null);
    }

    @PutMapping("/member/{id}")
    Member replaceMember(@RequestBody Member newMember, @PathVariable Long id) {

        return memberRepository.findById(id).map(member -> {
            // member.setTitle(newMember.getTitle());
            // member.setContent(newMember.getContent());
            member.setBoards(newMember.getBoards());
            // member.getBoards().clear(); 모두 삭제 할 때 쓰자!
            // member.getBoards().addAll(newMember.getBoards());
            for (Board board : member.getBoards()) {
                board.setMember(member);
            }
            return memberRepository.save(member);
        }).orElseGet(() -> {
            newMember.setId(id);
            return memberRepository.save(newMember);
        });
    }

    @DeleteMapping("/member/{id}")
    void deleteMember(@PathVariable Long id) {
        memberRepository.deleteById(id);
    }
}