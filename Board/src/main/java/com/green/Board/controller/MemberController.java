package com.green.Board.controller;

import com.green.Board.service.BoardServiceImpl;
import com.green.Board.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @Resource(name="boardService")
    private BoardServiceImpl boardService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    //회원가입 페이지 이동
    @GetMapping("/joinForm")
    public String joinform(){
        return "join";
    }

    //회원가입
    @PostMapping("/join")
    public String join(MemberVO memberVO){
        boardService.join(memberVO);

        //비밀번호 암호화
        String encodePw = encoder.encode(memberVO.getMemberPw());
        memberVO.setMemberPw(encodePw);

        return "redirect:/loginForm";
    }

    //로그인 페이지 이동
    @GetMapping("/loginForm")
    public String loginForm(){
        return "login";
    }



}
