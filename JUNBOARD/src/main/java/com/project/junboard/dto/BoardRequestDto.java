package com.project.junboard.dto;

import com.project.junboard.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
// Entity는 Request나 Response 에 사용되어서는 안되기 때문에
// 반드시 따로 구분 해 주어야 한다.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    private String title;       // 제목
    private String content;     // 내용
    private String writer;      // 작성자
    private char deleteYn;      // 삭제 여부

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .hits(0)
                .deleteYn(deleteYn)
                .build();
    }
}
