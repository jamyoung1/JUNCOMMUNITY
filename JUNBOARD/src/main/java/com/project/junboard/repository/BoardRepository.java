package com.project.junboard.repository;

import com.project.junboard.entity.Board;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    /* 게시글 리스트 조회(삭제 여부 기준) */
    /* 삭제를 해도 리스트에 보여지게 된다. 삭제되지 않는 데이터만 조회하는 기능이 필요*/
    List<Board> findAllByDeleteYn(final char deleteYn, final Sort sort);

}
