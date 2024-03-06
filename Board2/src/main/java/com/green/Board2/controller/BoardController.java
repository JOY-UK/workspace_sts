package com.green.Board2.controller;

import com.green.Board2.service.BoardServiceImpl;
import com.green.Board2.service.ReplyServiceImpl;
import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.MemberVO;
import com.green.Board2.vo.ReplyVO;
import com.green.Board2.vo.SearchVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Resource(name = "boardService")
    private BoardServiceImpl boardService;
    @Resource(name = "ReplyService")
    private ReplyServiceImpl replyService;

    //목록조회, 글쓰기, 상세조회, 수정, 삭제

    //게시글 목록 페이지
    @RequestMapping("/list")
    public String boardList(SearchVO searchVO, Model model){
        //전체 데이터 수
        int totalDataCnt = boardService.selectBoardCnt();
        searchVO.setTotalDataCnt(totalDataCnt);

        //페이지 정보 세팅
        searchVO.setPageInfo();

        System.out.println(searchVO);
        model.addAttribute("boardList", boardService.selectBoardList(searchVO));
        return "list";
    }

    //글쓰기 페이지로 이동
    @GetMapping("/writeForm")
    public String writeForm(){
        return "write_form";
    }

    @PostMapping("/write")
    public String write(BoardVO boardVO, HttpSession session){
        //세션에 저장된 로그인한 유저의 아이디로 조회
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        //boardVO에 작성자 데이터 저장
        boardVO.setWriter(loginInfo.getMemberId());
        boardService.insertBoard(boardVO);

        return "redirect:/board/list";
    }

    //상세보기
    @GetMapping("/boardDetail")
    public String boardDetail(@RequestParam(name = "boardNum")int boardNum, Model model){
        //조회수증가
        boardService.updateReadCnt(boardNum);
        //상세조회
        BoardVO vo = boardService.selectBoardDetail(boardNum);
        model.addAttribute("board", vo);
        //댓글목록
        List<ReplyVO> replyList = replyService.selectRelpy(boardNum);
        model.addAttribute("replyList", replyList);
        return "board_detail";
    }

    //삭제
    @GetMapping("/delete")
    public String boardDelete(@RequestParam(name = "boardNum")int boardNum){
        boardService.deleteBoard(boardNum);
        return "redirect:/board/list";
    }
    //댓 삭제
    @GetMapping("/replyDelete")
    public String replyDelete(ReplyVO replyVO){
        System.out.println(replyVO);
        replyService.deleteReply(replyVO.getReplyNum());
        return "redirect:/board/boardDetail?boardNum=" + replyVO.getBoardNum();
    }

}