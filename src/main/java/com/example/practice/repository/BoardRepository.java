package com.example.practice.repository;

import java.util.List;

import com.example.practice.model.Board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long>{
    List<Board> findByTitle(String title);
    List<Board> findByTitleOrContent(String title,String content);
}
