package com.example.practice.service;

import com.example.practice.model.Board;
import com.example.practice.model.Member;
import com.example.practice.repository.BoardRepository;
import com.example.practice.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    public Board save(String username, Board board) {
        Member member = memberRepository.findByUsername(username);
        board.setMember(member);
        return boardRepository.save(board);
    }

}
