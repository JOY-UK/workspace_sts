package com.green.CarInfo.service;

import com.green.CarInfo.vo.CarVO;
import com.green.CarInfo.vo.SalesVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carInfo")
public class CarInfoServiceImpl implements CarInfoService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void carInsert(CarVO carVO) {
       sqlSession.insert("carInfoMapper.carInsert", carVO);
    }

    @Override
    public List<CarVO> carSelect(CarVO carVO) {
        return sqlSession.selectList("carInfoMapper.carSelect", carVO);
    }

    @Override
    public void insertInfo(SalesVO salesVO) {
        sqlSession.insert("carInfoMapper.insertInfo", salesVO);
    }

    @Override
    public List<SalesVO> carList(SalesVO salesVO) {
        return sqlSession.selectList("carInfoMapper.carList", salesVO);
    }
}
