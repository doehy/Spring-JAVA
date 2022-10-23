package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity //DB가 객체를 인식 가능하게 된다.(해당 클래스를 테이블로 만든다.)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {

    @Id //대표값을 지정 like 주민번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성 예를들어 1,2,3,......
    private Long id;

    @Column
    private String title;

    @Column
    private String content;


    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }
}
