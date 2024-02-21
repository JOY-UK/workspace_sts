package com.green.FetchStudent.controller;

import com.green.FetchStudent.service.StuService;
import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.DetailVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//동기 통신 - DB작업 발생 시 페이지 전환 발생
//비동기 통신 - DB작업 발생해도 페이지 전환 X

@Controller
public class StuController {

    @Resource(name = "stuService")
    private StuService stuService;

    @GetMapping("/")
    public String selectList(StuVO stuVO, Model model){
        List<StuVO> stuList = stuService.selectStu(stuVO);
        model.addAttribute("stuList", stuList);

        List<ClassVO> classList = stuService.selectCls();
        model.addAttribute("classList", classList);

        return "stu_manage";
    }
    @ResponseBody
    @PostMapping("/selectStu")
    public DetailVO selectScore (StuVO stuVO, ScoreVO scoreVO){
        //학생의 상세 정보 조회
        StuVO stuInfo = stuService.selectStuDetail(stuVO);

        //학생의 점수 정보 조회
        ScoreVO scoreInfo = stuService.selectScoreInfo(scoreVO);

        //조회한 데이터 자바스크립트 이동

        DetailVO result = new DetailVO();
        result.setStuVO(stuInfo);
        result.setScoreVO(scoreInfo);

        return result; //리턴값 데이터 전달
    }



    @ResponseBody
    @PostMapping("/fetchSelect")
    public List<StuVO> fetchSelect(StuVO stuVO){
        List<StuVO> stuList = stuService.selectStu(stuVO);
    return stuList;
    }

    @ResponseBody
    @PostMapping("/insertScore")
    public void insertScore(ScoreVO scoreVO){
        stuService.insertScore(scoreVO);
    }


}
