package com.green.Board.controller;

import com.green.Board.service.BoardService;
import com.green.Board.service.BoardServiceImpl;
import com.green.Board.vo.BoardVo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {

    @Resource(name = "boardService")
    private BoardServiceImpl boardService; //ㅜ같은말
    //BoardServiceImpl boardService = new BoardServiceImpl();

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/")
    public String boardList(Model model) {
        //게시판목록
       List<BoardVo> list = boardService.select();
        //목록 html전달
       model.addAttribute("boardList",list);

       //암호화 예제
       encoder.encode("java");
        String s1 = encoder.encode("java");
        String s2 = encoder.encode("java");
        System.out.println(s1);
        System.out.println(s2);


        //암호화 데이터를 기준 데이터
        boolean b1 = encoder.matches("java", s1);
        System.out.println(b1);
        return "board_list";
    }


    //페이지이동
    @GetMapping("/goboard")
    public String goboard(){
        return "board_write_form";
    }

    //데이터삽입(글등록)
    @PostMapping("/boardadd")
    public String goboard(BoardVo boardVo){
        boardService.insert(boardVo);
        return "redirect:/";
    }

    //게시글 상세보기 페이지이동 및 데이터전달
    @GetMapping("/boardDetail")
    public String boarddetail(BoardVo boardVo, Model model){
        //상세정보조회
        BoardVo board = boardService.selectDetail(boardVo.getBoardNum());
        //조회수 증가
        boardService.updateCnt(boardVo.getBoardNum());
        //조회 html전달
        model.addAttribute("selectDetail", board);


        return "board_detail";
    }

    //삭제
    @GetMapping("boardDelete")
    public String delete(BoardVo boardVo){
        boardService.delete(boardVo.getBoardNum());
        return "redirect:/";
    }

    //뒤로가기
    @GetMapping("/backSpace")
    public String backspace(){
        return "redirect:/";
    }

    //수정페이지이동
    @GetMapping("/update")
    public String update(BoardVo boardVo, Model model){
        //수정하고자 하는 게시글의 데이터를 조회 후 html전달
        BoardVo board = boardService.selectDetail(boardVo.getBoardNum());
        model.addAttribute("board", board);
        return "update_form";
    }
    //게시글수정
    @PostMapping("/updateboard")
    public String updateBoard(BoardVo boardVo){
        //게시글 수정
        boardService.updateBoard(boardVo);
        return "redirect:/boardDetail?boardNum=" + boardVo.getBoardNum();
    }


}
