package com.project.junboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardPageController {

    /* 게시글 리스트 페이지 */
    @GetMapping("/list")
    public String BoardList() {
        return "board/list";
    }

    /* 게시글 등록 페이지 */
    @GetMapping("/write")
    public String BoardWrite() {
        return "board/write";
    }
}
