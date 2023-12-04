package com.example.firstproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity // DB가 해당 객체를 인식 가능
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
public class Article {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1, 2, 3, 자동 생성 어노테이션 , strategy = GenerationType.SEQUENCE -> db가 id를 자동 생성 어노테이션
    private Long id;

    @Column
    private String title;


    @Column
    private String content;

}

