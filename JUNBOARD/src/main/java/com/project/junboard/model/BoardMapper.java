package com.project.junboard.model;

import com.project.junboard.dto.BoardResponseDto;
import com.project.junboard.paging.CommonParams;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


// MyBatis는 @Mapper가 선언 된 인터페이서와 연결된 XML Mapper에서
// 메서드 명과 동일한 SQL을 찾아 쿼리를 실행한다.
@Mapper
public interface BoardMapper {

    /** 게시글 수 조회 */
    int count(final CommonParams params);

    /** 게시글 리스트 조회 */
    List<BoardResponseDto> findAll(final CommonParams params);
}
