package com.green.DbTest.service;

import com.green.DbTest.vo.StudentVO;

import java.util.List;

//인터페이스 : 기능정의(메소드)
public interface StudentService {

    //학생 한 명을 저장하는 기능
    public void InsertStudent(); //public 생략가능

    //학생 한 명을 삭제하는 기능
    void deleteStudent();

    //입력받은 값으로 학생 삭제
    void delete(int stuNo);

    //학번이 1번인 학생 이름조회
    String selectName();

    //학번이 1번인 학생 점수조회
    int selectScore();

    //학번이 1번인 학생을 조회
    StudentVO selectStu();

    //모든 학생 정보 조회
    List<StudentVO> selectStuList();



}
