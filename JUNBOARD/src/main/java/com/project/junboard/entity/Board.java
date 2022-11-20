package com.project.junboard.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
// 해당 클래스의 기본 생성자를 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;       // PK

    private String title;  // 제목

    private String content; // 내용

    private String writer; // 작성자

    private int hits;      // 조회 수

    private char deleteYn; // 삭제 여부

    private LocalDateTime createdDate = LocalDateTime.now(); // 생성 일

    private LocalDateTime modifiedDate; // 수정일

    
    // 생성자 대신 이용하는 패턴
    @Builder
    public Board(String title, String content, String writer, int hits, char deleteYn){
        this.title=title;
        this.content=content;
        this.writer=writer;
        this.hits=hits;
        this.deleteYn=deleteYn;
    }

    public void update(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.modifiedDate = LocalDateTime.now();
    }
}
