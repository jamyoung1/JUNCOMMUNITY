package com.project.junboard.paging;

import lombok.Getter;

@Getter
public class Pagination {

    private int totalRecordCount;   // 전체 데이터 수
    private int totalPageCount;     // 전체 페이지 수
    private int startPage;          // 첫 페이지 번호
    private int endPage;            // 끝 페이지 번호
    private int limitStart;         // LIMIT 시작 위치
    private boolean existPrevPage;  // 이전 페이지 존재 여부
    private boolean existNextPage;  // 다음 페이지 존재 여부

    public Pagination(int totalRecordCount, CommonParams params) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            this.calculation(params);
        }
    }

    private void calculation(CommonParams params) {

        // 전체 페이지 수 계산
        totalPageCount = ((totalRecordCount - 1) / params.getRecordPerPage()) + 1;

        // 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
        if (params.getPage() > totalPageCount) {
            params.setPage(totalPageCount);
        }

        // 첫 페이지 번호 계산
        startPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;

        // 끝 페이지 번호 계산
        endPage = startPage + params.getPageSize() - 1;

        // 끝 페이지가 전체 페이지 수보다 큰 경우, 끝 페이지 전체 페이지 수 저장
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        // LIMIT 시작 위치 계산
        limitStart = (params.getPage() - 1) * params.getRecordPerPage();

        // 이전 페이지 존재 여부 확인
        existPrevPage = startPage != 1;

        // 다음 페이지 존재 여부 확인
        existNextPage = (endPage * params.getRecordPerPage()) < totalRecordCount;

        /* totalRecodeCount -> 전체 개시글 개수
           검색 조건이 없는 경우, 전체 데이터 개수가 되고
           검색 조건이 있는 경우, 조건에 해당되는 데이터 개수가 된다. */

        /* totalPageCount -> 페이지 하단에 출력 할 전체 페이지 개수
           1000개의 Recode가 있고, 페이지당 출력 할 데이터 개수가 1개면 (1000/10) = 100이 된다. */

        /*  startPage -> 페이지 하단에 출력 할 페이지 수(PageSize)가 10이고,
            현재 페이지 번호(page)가 5라고 가정 했을 때 1을 의미
            페이지 번호가 15라면, startPage는 11이 된다. */

        /*  endPage -> 페이지 하단에 출력 할 페이지 수(PageSize)가 10이고,
            현재 페이지 번호(page)가 5일 때, 10을 의미
            페이지 번호가 15라면, endPage는 20
        */

        /* limitStart -> MySQL의 LIMIT 구문에 사용되는 멤버 변수
           LIMIT의 첫 번째 파라미터에는 시작 위치, 즉 몇 번째 데이터부터 조회할 지를 정한다.
           두 번째 파라미터에는 시작 limitStart를 기준으로 조회 할 데이터의 개수를 지정한다. */


    }

}
