package com.green.DbTest.controller;

import com.green.DbTest.service.StudentService;
import com.green.DbTest.service.StudentServiceImpl;
import com.green.DbTest.vo.StudentVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    @Resource(name = "stuService")
    private StudentService studentService;

    //localhost:8081
    @GetMapping("/")
    public String insertTest(){
        //학생 한 명 등록
//        studentService.InsertStudent();

        //학생 한 명 삭제
//        studentService.deleteStudent();

//        String name = studentService.selectName();
//        int score = studentService.selectScore();
//        System.out.println("name = " + name);
//        System.out.println("score = " + score);

//        StudentVO stu = studentService.selectStu();
//        System.out.println(stu);

          List<StudentVO> stuList = studentService.selectStuList();
          for(int i = 0; i < stuList.size(); i++){
          System.out.println(stuList.get(i));
          }
        return "insert";
    }

    @GetMapping("/delete")
    public String delete(){
        return "delete";
    }

    @PostMapping("deleteResult")
    private String deleteResult(@RequestParam(name = "stuNo") int stuNo){
        //학생 삭제 기능
        studentService.delete(stuNo);
        return "delete_result";
    }
}

// 1. 이동하고자 하는 html로 갈때 쿼리 기능이 필요한지 판단 / 필요 x 순수 페이지 이동
// 2. 쿼리기능이 필요할 때 무조건 "쿼리(mapper.xml)" 작성
// 3. 작성한 쿼리를 실행시킬 메소드를 인터페이스에서 정의
// 4. 인터페이스를 구현한 클래스에서 메소드를 구현
// 5. controller의 메소드에서 쿼리 실행을 위해 만든 메소드를 호출.