package com.green.Board2.membercontroller;

import com.green.Board2.VO.BoardVO;
import com.green.Board2.VO.MemberVO;
import com.green.Board2.service.BoardService;
import com.green.Board2.service.BoardServiceImpl;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Resource(name = "boardService")
    private BoardServiceImpl boardService;
    //목록조회, 글쓰기, 상세조회, 수정, 삭제

    //게시글 목록 페이지 및 조회
    @GetMapping("/list")
    public String boardList(Model model){
        List<BoardVO> list = boardService.boardSelect();
        model.addAttribute("boardSelect", list);
        return "board_list";
    }

    //게시글 등록 페이지이동
    @GetMapping("/writeBoard")
    public String writeBoard(){
        return "board_Write";
    }

    //게시글 추가 및 첫페이지 이동
    @PostMapping("/addBoard")
    public String addBoard(BoardVO boardVO){
        boardService.boardInsert(boardVO);
        return "redirect:/board/list";
    }

    //상세페이지조회
    @GetMapping("/boardDetail")
    public String boardDetail(BoardVO boardVO, Model model){
        BoardVO board  = boardService.selectDetail(boardVO.getBoardNum());
        model.addAttribute("boardDetail", board);
        boardService.updateCnt(boardVO.getBoardNum());
        return "board_detail";
    }

    //삭제
    @GetMapping("/boardDelete")
    public String boardDelete(BoardVO boardVO){
        boardService.delete(boardVO.getBoardNum());
        return "redirect:/board/list";
    }
    //수정
    @GetMapping("/boardUpdate")
    public String boardUpdate(BoardVO boardVO){
       boardService.update(boardVO);
       return "update_form";
    }



}
