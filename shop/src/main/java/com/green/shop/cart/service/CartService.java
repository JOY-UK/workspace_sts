package com.green.shop.cart.service;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;

import java.util.List;

public interface CartService {

    //장바구니에 상품 등록
    void insertCart(CartVO cartVO);

    //장바구니 목록 조회
    List<CartViewVO> selectCartList(String memberId);

    //장바구니 상품 하나 삭제
    void deleteCart(int cartCode);

    //장바구니 상품 수량 변경
    void updateCartCnt(CartVO cartVO);

    //장바구니 상품들 삭제
    void deleteCarts(CartVO cartVO);

    //장바구니에 담긴 상품 구매를 위한 장바구니 목록 조회
    List<CartViewVO> selectCartListForBuy(CartVO cartVO);

}
