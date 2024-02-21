package com.green.StudentManager.service;

import com.green.StudentManager.vo.StuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StuServiceImpl implements StuService{
    @Autowired
    private SqlSessionTemplate sqlSession; //고정값(무조건)

    @Override
    public int insertStu(StuVO stuVO) {
        int result = sqlSession.insert("insertStu", stuVO);
        return result;
    }

    @Override
    public List<StuVO> selectStuList() {
      return sqlSession.selectList("selectStuList");
    }

    @Override
    public StuVO selectStuDetail(int stuNo) {
        return sqlSession.selectOne("selectStuDetail", stuNo);
    }

    @Override
    public int deleteStu(int stuNo) {
        return sqlSession.delete("deleteStu", stuNo);
    }
}
