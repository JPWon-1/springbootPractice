package com.example.practice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Member {
    @Id
    @SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
    private Long id;
    private String username;
    private String password;
    private int enabled;

    @ManyToMany
    @JoinTable(
        name = "member_role", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
        
   
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL , orphanRemoval = false)
    //orphanRemoval = true로 줄 경우 나머지들 다 삭제...?
    private List<Board> boards = new ArrayList<>();
}
