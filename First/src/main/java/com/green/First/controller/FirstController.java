package com.green.First.controller;

import com.green.First.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//anotation
@Controller
public class FirstController {

    //페이지이동
    //GetMappin() 주소창에 localhost:8081/ 뒤 글자와 소괄호 안에 글자와 동일 해야함
    // (앞에 Get 방식으로 전송 Post면 포스트방식으로 전송
    @GetMapping("main")
    public String main(){
        return "first"; //first html을 불러오는거
    }


//    @RequestParam html에서 넘어오는 데이터를 받을 때 사용
//    name : 넘어오는 데이터의 이름을 의미
    @GetMapping("second")
    public String second(@RequestParam(name = "name") String name, //개별로 전송
                         @RequestParam(name = "age") int age){
        System.out.println("!!!!!!!!!!!!" + name);
        System.out.println("!!!!!!!!!!!!" + age);
        return "abc";
    }

//    넘어오는 데이터의 이름과 동일한 변수를 가진 클래스 객체로 데이터를 받을수 있다
    @GetMapping("third")
    public String third(MemberVO memberVO, Model model){
        //클래스VO 만들어서 한번에 전송 Member VO 는 데이터가져오기 Model은 데이터전송
        System.out.println(memberVO);



        //데이터를 html로 전달 (컨트롤러에 없는 데이터, (String name,age등등) 새로운 데이터를 전송할때 사용)
        model.addAttribute("score", 80);

        return "abc";
    }
}

