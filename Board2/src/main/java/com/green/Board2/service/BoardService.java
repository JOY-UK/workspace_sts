package com.green.Board2.service;

import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.SearchVO;

import java.util.List;

public interface BoardService {
    //게시글 목록 조회
    List<BoardVO> selectBoardList(SearchVO searchVO);

    //게시글 상세
    BoardVO selectBoardDetail(int boardNum);

    //게시글 등록
    void insertBoard(BoardVO boardVO);

    //삭제
    void deleteBoard(int boardNum);

    //수정
    void updateBoard(BoardVO boardVO);

    //조회수 증가
    void updateReadCnt(int boardNum);

    //게시글 수 조회
    int selectBoardCnt();
}
