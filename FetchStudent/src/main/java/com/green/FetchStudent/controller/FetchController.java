package com.green.FetchStudent.controller;


//비동기 통신 학습용 컨트롤러
// javascript -> fetch
// jquery -> ajax, 사용문법 : $()
// react -> axios

import com.green.FetchStudent.vo.StuVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/study")
public class FetchController {

    @GetMapping("/t1")
    public String t1(){
        return "fetch_test";
    }

    //ResponseBody : 해당 메소드는 비동기 통신을 사용하기 때문에 메소드의 마지막 return
    //으로 페이지 전환 하지 않겠다를 스프링에게 알려주는 코드
    @ResponseBody
    @PostMapping("/t2")
    public String t2(@RequestParam(name = "name")String name,
                     @RequestParam(name = "age")int age){
        System.out.println("t1메소드 실행");
        System.out.println("이름 : " + name);
        System.out.println("나이 : " + age);

    return "hello";
    }

    @ResponseBody
    @PostMapping("/t3")
    public StuVO t3(StuVO vo){
        System.out.println(vo);


        System.out.println("t3 메소드실행~");
        StuVO stuVO = new StuVO();

        stuVO.setStuNum(1);
        stuVO.setStuName("김자바");
        stuVO.setClassCode(1);
        stuVO.setClassName("자바반");

        return stuVO;

    }

    @ResponseBody
    @PostMapping("/t4")
    public List<StuVO> t4(){
        System.out.println("t4메소드실행");
        List<StuVO> list = new ArrayList<>();

        int sum = 0;
        for(int i = 1; i < 6; i++){
            StuVO stuVO = new StuVO();
            stuVO.setStuNum(i);
            stuVO.setStuName("자바_" + i);
            stuVO.setClassCode(1 + i);
            stuVO.setClassName("자바반_" + i);
            list.add(stuVO);

        }

        return list;
    }


}
