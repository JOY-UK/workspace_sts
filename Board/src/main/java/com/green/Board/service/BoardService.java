package com.green.Board.service;

import com.green.Board.vo.BoardVo;

import java.util.List;

public interface BoardService {

    //게시판등록
    int insert (BoardVo boardVo);

    //게시판조회
    List<BoardVo> select();
//    매개변수()는 빈공간 채워주는 역할 조회는 빈공간X

    //게시판상세조회
    BoardVo selectDetail(int boardNum);

    //게시글삭제
    int delete (int boardNum);

    //조회수증가
    int updateCnt (int boardNum);

    //수정
    int updateBoard(BoardVo boardVo);



}
