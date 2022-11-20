package com.project.junboard.board;


import com.project.junboard.entity.Board;
import com.project.junboard.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class BoardTests {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void save() {
        // 1. 게시글 파라미터 생성
        // 빌더 패턴을 쓰면 가독성이 높아진다. 어떤 멤버에 어떤 값을 세팅하는지 직관적
        Board params = Board.builder()
                .title("title1")
                .content("content1")
                .writer("junyoung")
                .hits(0)
                .deleteYn('N')
                .build();

        // 2. 게시글 저장
        boardRepository.save(params);

        // 3. 게시글 정보 조회
        Board entity = boardRepository.findById((long) 1).get();
        assertThat(entity.getTitle()).isEqualTo("title1");
        assertThat(entity.getContent()).isEqualTo("content1");
        assertThat(entity.getWriter()).isEqualTo("junyoung");
    }

    @Test
    void findAll() {

        // 1. 전체 게시글 수 조회
        long boardsCount = boardRepository.count();

        // 2. 전체 게시글 리스트 조회
        List<Board> boards = boardRepository.findAll();
    }

    @Test
    void delete() {

        // 1. 게시글 조회
        Board entity = boardRepository.findById((long) 2).get();

        // 2. 게시글 삭제
        boardRepository.delete(entity);
    }

}
