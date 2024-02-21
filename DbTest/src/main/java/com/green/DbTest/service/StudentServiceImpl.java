package com.green.DbTest.service;

import com.green.DbTest.vo.StudentVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StudentServiceImpl implements StudentService{
//    Mybatis에서 제공하는 데이터베이스 쿼리 기능이 정의되어있는 객체

    @Autowired //객체생성 (=같은말 ( sqlSession = new s
    private SqlSessionTemplate sqlSession;

    @Override
    //학생 한명을 저장하는 기능
    //DB작업 : 조회, 삽입, 삭제, 수정
    // 삽입 : sqlSession.insert("실행할 쿼리 id", [쿼리에서 빈 값을 채울 데이터]) [대괄호 생략가능] 실행할 쿼리 id->mapprer id
    // 삭제 : sqlSession.delete("실행할 쿼리 id", [쿼리에서 빈 값을 채울 데이터])
    // 수정 : sqlSession.update("실행할 쿼리 id", [쿼리에서 빈 값을 채울 데이터])
    // 조회 : sqlSession.selectOne("실행할 쿼리 id", [쿼리에서 빈 값을 채울 데이터])
//            - 조회결과 데이터가 0행 혹은 1행일 경우.
    //      : sqlSession.selectList("실행할 쿼리 id", [쿼리에서 빈 값을 채울 데이터])
//            - 조회할때 마다 조회 되는 행의 개수가 매번 다른경우.
    public void InsertStudent() {
        sqlSession.insert("insertStudent");
    }

    @Override
    public void deleteStudent() {
        sqlSession.delete("deleteStudent");
    }

    @Override
    public void delete(int stuNo) { //매개변수값 인터페이스랑 동일하게 맞춰야함
        sqlSession.delete("delete", stuNo); //괄호에 쿼리아이디(맵퍼에서 작성한)
    }

    @Override
    public String selectName() {
       String name = sqlSession.selectOne("selectName");
        return name;
    }

    @Override
    public int selectScore() {
        int score = sqlSession.selectOne("selectScore");
        return score;
    }

    @Override
    public StudentVO selectStu() {
        StudentVO stu = sqlSession.selectOne("selectStu");
        return stu;
    }

    @Override
    public List<StudentVO> selectStuList() {
       List<StudentVO> stuList = sqlSession.selectList("selectStuList");
        return stuList;
    }

}
