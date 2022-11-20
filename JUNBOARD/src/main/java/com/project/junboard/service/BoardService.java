package com.project.junboard.service;


import com.project.junboard.dto.BoardRequestDto;
import com.project.junboard.dto.BoardResponseDto;
import com.project.junboard.entity.Board;
import com.project.junboard.exception.CustomException;
import com.project.junboard.exception.ErrorCode;
import com.project.junboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시글 생성
    @Transactional
    public Long save(final BoardRequestDto params) {
        // Entity는 요청(Request)에 사용되어선 안되기 때문에
        // BoardRequestDto의 toEntity 메서드를 이용해 save 메서드를 실행한다.
        Board entity = boardRepository.save(params.toEntity());
        // save() 메서드가 실행된 후 entity 객체에는 생성된 게시글 정보가 담기고
        // 메서드가 종료되면 생성된 게시글의 id(PK)를 리턴한다.
        return entity.getId();
    }

    // 게시글 리스트 조회
    public List<BoardResponseDto> findAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id","createdDate");
        List<Board> list = boardRepository.findAll(sort);

        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
        // list 변수에는 게시글 Entity가 담겨있고,
        // 각각의 Entity를 BoardResponseDto 타입으로 변경(생성)해서 리턴해 준다고 생각하자.
    }

    // 게시글 수정
    @Transactional
    // 서비스 클래스에서 필수로 사용되어야 하는 어노테이션
    // 실행(begin), 종료(commit), 예외(rollback)를 자동으로 처리
    public Long update(final Long id, final BoardRequestDto params) {
           Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        // Optional -> 반복적인 NULL 처리를 피하기 위해 사용되는 클래스
        // orElseThrows() -> Optional 클래스에 포함 된 메서드
        //                   Entity 조회, 예외 처리를 한 줄로 처리할 수 있는 메서드
        entity.update(params.getTitle(), params.getContent(), params.getWriter());
        return id;
        // 해당 메서드의 실행이 종료(commit)되면 update 쿼리가 자동으로 실행된다.
        // (영속성 컨텍스트)
        // Entity를 영구히 저장하는 환경
        // Entity를 조회하면 해당 Entity는 영속성 컨텍스트에 보관(포함)이 되고
        // 영속성 컨텍스트에 포함된 Entity 객체의 값이 변경되면
        // Transaction이 종료(commit)되는 시점에 update 쿼리를 자동 실행한다.
        // 그래서 update 메서드에는 update 쿼리를 실행하는 로직이 없다.
        /* 영속성 컨텍스트에 의해 더티 체킹이 가능해진다.*/

    }
}
