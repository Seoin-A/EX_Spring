package com.example.firstproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity // DB가 해당 객체를 인식 가능
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 1, 2, 3, 자동 생성 어노테이션 , strategy = GenerationType.SEQUENCE -> db가 id를 자동 생성 어노테이션
    private Long id;

    @Column
    private String title;


    @Column
    private String content;

    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null) {
            this.content = article.content;
        }
    }

}

