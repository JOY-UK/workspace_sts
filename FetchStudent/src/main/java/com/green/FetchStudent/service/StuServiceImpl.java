package com.green.FetchStudent.service;

import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StuServiceImpl implements StuService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<StuVO> selectStu(StuVO stuVO) {
        return sqlSession.selectList("stuMapper.selectStu", stuVO);
    }

    @Override
    public List<ClassVO> selectCls() {
        return sqlSession.selectList("stuMapper.selectClass");
    }

    @Override
    public ScoreVO selectScoreInfo(ScoreVO scoreVO) {
        return sqlSession.selectOne("stuMapper.selectScoreInfo", scoreVO);
    }

    @Override
    public StuVO selectStuDetail(StuVO stuVO) {
        return sqlSession.selectOne("stuMapper.selectStuDetail", stuVO);
    }

    @Override
    public void insertScore(ScoreVO scoreVO) {
        sqlSession.insert("stuMapper.insertScore", scoreVO);
    }


}
