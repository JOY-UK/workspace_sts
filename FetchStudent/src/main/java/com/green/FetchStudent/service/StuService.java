package com.green.FetchStudent.service;

import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;

import java.util.List;

public interface StuService {
    //학생목록
    List<StuVO> selectStu(StuVO stuVO);
    //학급목록
    List<ClassVO> selectCls();
    //점수정보
    ScoreVO selectScoreInfo(ScoreVO scoreVO);
    //학생정보디테일
    StuVO selectStuDetail(StuVO stuVO);
    //점수등록
    void insertScore(ScoreVO scoreVO);

}
