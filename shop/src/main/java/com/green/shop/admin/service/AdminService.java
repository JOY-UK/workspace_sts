package com.green.shop.admin.service;

import com.green.shop.admin.vo.SearchBuyVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;

import java.util.List;

public interface AdminService {

    //상품 등록 + 이미지 등록
    void insertItem(ItemVO itemVO);

    //다음에 들어갈 ITEM_CODE 조회
    int selectNextItemCode();

    //구매이력조회(관리자용)
    List<BuyVO> selectHistory (SearchBuyVO searchBuyVO);

    //구매상세조회
    BuyVO buyDetail(int buyCode);

    //상품정보변경
    List<ItemVO> itemInfoChange ();

    //상품구매정보 상세조회
    ItemVO itemInfo(int itemCode);

    //상품수정
    void updateItem(ItemVO itemVO);


}
