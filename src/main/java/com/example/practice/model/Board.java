package com.example.practice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Board {
    @Id
    @SequenceGenerator(name = "board_seq_generator", sequenceName = "board_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_generator")
    private long id;

    @NotNull
	@Size(min=5, max=30, message = "제목은 2자이상 30자 이하입니다")
    private String title;
    private String content;

}
