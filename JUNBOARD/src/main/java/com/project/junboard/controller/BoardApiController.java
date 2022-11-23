package com.project.junboard.controller;


import com.project.junboard.dto.BoardRequestDto;
import com.project.junboard.dto.BoardResponseDto;
import com.project.junboard.model.BoardService;
import com.project.junboard.paging.CommonParams;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/* Rest API 기반의 비동기 방식을 게시판을 구현하기 위해
   즉, 페이지를 처리하는 Controller와 API를 처리하는 Controller를 따로 구성하게 된다.
*/
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

//    // 예외 핸들링 테스트
//    @GetMapping("/test")
//    public String test() {
//        throw new CustomException(ErrorCode.POSTS_NOT_FOUND);
//    }

    /** 게시글 생성 */
    @PostMapping("/boards")
    public Long save(@RequestBody final BoardRequestDto params){
        return boardService.save(params);
    }

    /** 게시글 상세정보 조회 */
    @GetMapping("/boards/{id}")
    public BoardResponseDto findById(@PathVariable final Long id) {
        return boardService.findById(id);
    }

    /** 게시글 리스트 조회 */
    @GetMapping("/boards")
    public Map<String, Object> findAll(final CommonParams params) {
        return boardService.findAll(params);
    }


    /** 게시글 수정 */
    @PatchMapping("/boards/{id}")
    public Long save(@PathVariable final Long id, @RequestBody final BoardRequestDto params) {
        return boardService.update(id, params);
    }

    /** 게시글 삭제 */
    @DeleteMapping("/boards/{id}")
    public Long delete(@PathVariable final Long id) {
        return boardService.delete(id);
    }






}
