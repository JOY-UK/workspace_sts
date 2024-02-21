package com.green.Board2.membercontroller;

import com.green.Board2.VO.MemberVO;
import com.green.Board2.service.MemberService;
import com.green.Board2.service.MemberServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//HTML에서 CONTROLLER로 페이지 이동
//a태그 : <a href="/aa" -> @GetMapping("/aa")
// - a는 url 이동 적은양 이동
//form태그 action="/aa""-> @GetMapping("/aa")
// - form 은 id,pw,emial 등 여러가지 자료를 넘김
//onclick="locaton.href='/aa'" -> @GetMapping("/aa")
// - on은 버튼클릭해서 넘길때

@Controller
@RequestMapping("/member")
public class MemberController {

    @Resource(name = "MemberService")
    private MemberServiceImpl memberService;

    //로그인 페이지로 이동
    @GetMapping("/loginForm")
    public String loginForm(){
        return "login";
    }

    //회원가입 페이지 이동
    @GetMapping("/memberadd")
    public String memberadd (){
        return "join";
    }

    //회원등록 후 원위치
    @PostMapping("/memberadd")
    public String memberadd(MemberVO memberVO) {
        memberService.insert(memberVO);
        return "redirect:/member/loginForm";
    }

    //로그인
    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session) {
        MemberVO loginInfo = memberService.login(memberVO);
        System.out.println(loginInfo);
        //로그인 정보가 조회가 됐으면
        if(loginInfo != null){
            //세션에 로그인 정보를 저장
            session.setAttribute("loginInfo", loginInfo);
        }
        return "login_result";
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout (HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/board/list";
    }
}
