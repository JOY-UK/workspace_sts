package com.green.shop.item.service;

import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ItemVO;

import java.util.List;

public interface ItemService {
    //카테고리 목록 조회
    List<CategoryVO> selectCategoryList();

    //상품 목록 조회
    List<ItemVO> selectItemList();

    //상품 상세 정보 조회
    ItemVO selectItemDetail(int itemCode);


}
