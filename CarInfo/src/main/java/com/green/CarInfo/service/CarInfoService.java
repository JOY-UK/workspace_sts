package com.green.CarInfo.service;

import com.green.CarInfo.vo.CarVO;
import com.green.CarInfo.vo.SalesVO;

import java.util.List;

public interface CarInfoService {
    //차량등록
    void carInsert (CarVO carVO);
    //차량조회
    List<CarVO> carSelect (CarVO carVO);
    //정보등록
    void insertInfo (SalesVO salesVO);
    //차량목록
    List<SalesVO> carList (SalesVO salesVO);
}
