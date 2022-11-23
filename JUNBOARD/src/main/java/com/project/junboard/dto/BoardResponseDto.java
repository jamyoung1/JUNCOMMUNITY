package com.project.junboard.dto;

import com.project.junboard.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private int hits;
    private char deleteYn;
    private LocalDateTime createdDate;  // 생성일
    private LocalDateTime modifiedDate; // 수정일

    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.writer = entity.getWriter();
        this.hits = entity.getHits();
        this.deleteYn = entity.getDeleteYn();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
