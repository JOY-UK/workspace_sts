package com.green.StudentManager.controller;

import com.green.StudentManager.service.StuServiceImpl;
import com.green.StudentManager.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {

    @Resource(name = "stuService")
    private StuServiceImpl stuService;

    //학생 목록페이지 이동
    @GetMapping("/")
    public String stuList(Model model){
        //학생 목록 조회
        List<StuVO> list = stuService.selectStuList();

        //조회한 목록을 html로 전달
        model.addAttribute("stuList", list);
        return "stu_list";
    }

    @GetMapping("/regStu")
    public String regStu(){
        return "reg_student";
    }

    @PostMapping("/regStu")
    public String reg(StuVO stuVO){
        //학생 등록
        stuService.insertStu(stuVO);
        return "redirect:/";        //redirect : 되돌릴때 사용
    }
    @GetMapping("/stuDetail")
    public String stuDetail(@RequestParam(name = "stuNo") int stuNo, Model model){
        //학생의 상세정보 조회
        StuVO stu = stuService.selectStuDetail(stuNo);
        //조회한 데이터 html로 전달
        model.addAttribute("stuInfo", stu);

        return "stu_detail";
    }

    @GetMapping("/deleteStu")
    public String del(@RequestParam(name = "stuNo") int stuNo){
        //학생삭제
        stuService.deleteStu(stuNo);
        return "redirect:/"; // /<-로 이동
    }
}

//좋은 개발 코드 결합도는 낮고 응집도는 높은 코드가 좋은 코드

// - 결합도를 낮추기 위해 적용한 개념
//  1) IOC(Inversion Of Control)            2) DI(Dependency Injection)
//    - 제어의 역전(객체를 만든느 주체가 역전)      - 의존성 주입

// 어떻게 스프링이 객체를 만들게 코드를 구현하는가?
// 1. 스프링이 객체를 알아서 생성하려면 객체 생성이 필요한 클래스는 반드시 프로젝트의 "기본 패키지 안에 생성!!!"
// 2. 기본패키지 안에 생성된 클래스들을 확인하면서 특정 어노테이션이 붙어있는 클래스들의 객체를 자동으로 생성

//    - 클래스 위에 선언되어서 객체를 자동으로 생성하는 어노테이션
//      1)@Component    *(자주 사용하지 않음)
//      2)@Controller   3)@Service  4)@Repository

//    예시)
//    @Controller
//    public class Stucontroller{}
//    =StuController stuController = newStuController();

//    예시)
//    @Service
//    public StuServiceImpl{}
//    =StuServiceImpl stuServiceImpl = new StuServiceImpl();

//    예시)
//    @Service("stuService")
//    public StuServiceImpl{}
//    =StuServiceImpl sutService = new StuServiceImpl();

// 3. 스프링이 자동으로 만들어준 객체를 우리가 어떻게 사용할 수 있는가?(DI)
//  - 스프링이 자동으로 만들어준 객체를 사용할 때도 어노테이션으로 사용 만들어진 객체를 사용하는 어노테이션
//  - 1)@Autowired : 자료형을 기준으로 객체에 의존성 주입
//  - 2)@Resource  : 객체명을 기준으로 객체에 의존성 주입

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
//       EX) 1.
//      @Component
//      class Aaa{} -> Aaa aaa = new Aaa();
//      @Component
//      class Bbb{} -> Bbb bbb = new Bbb();
//
//      class Test{
//        @Autowired
//        private Aaa myAaa; -> private Aaa my Aaa = aaa;
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
//       EX) 2.
//
//      class Aaa{}                 -> *객체안만들어짐

//      @Component
//      class Bbb extends Aaa{}     -> Bbb bbb = new Bbb();

//      @Component
//      class Ccc extends Aaa{}     -> Ccc ccc = new Ccc();

//      @Component
//      class Ddd {}                -> Ddd ddd = new Ddd();

//      class Eee{
//      @Aurowired                  -> 자료형 2개 들어올수있기에 사용불가
//      private Aaa aaa;            -> = bbb; / = ccc; 둘다 들어올수 있기에 "오류"

//      class Eee{
//      @Resource(name = 'bbb')     -> bbb라는 이름의 객체로 의존성 주입
//      private Aaa aaa;            -> private Aaa aaa = bbb;
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ


// - 응집도를 높이기 위해 적용한 개념
//  1) AOP(Aspected-Oriented Programming)   2)
//      - 관점 지향 프로그래밍