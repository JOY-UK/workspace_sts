package com.green.shop.cart.service;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public void insertCart(CartVO cartVO) {
        //현재 내 장바구니에 지금 추가하려는 상품이 있는지 확인
        int cnt = sqlSession.selectOne("cartMapper.getCntOfCart", cartVO);

        //존재하지 않으면 장바구니에 추가 (insert)
        if(cnt == 0){
            sqlSession.insert("cartMapper.insertCart", cartVO);
        }
        //존재하면 수량만 변경(update)
        else{
            sqlSession.update("cartMapper.plusCartCnt", cartVO);
        }
    }

    @Override
    public List<CartViewVO> selectCartList(String memberId) {
        return sqlSession.selectList("cartMapper.selectCartList", memberId);
    }

    @Override
    public void deleteCart(int cartCode) {
        sqlSession.delete("cartMapper.deleteCart", cartCode);
    }

    @Override
    public void updateCartCnt(CartVO cartVO) {
        sqlSession.update("cartMapper.updateCartCnt", cartVO);
    }

    @Override
    public void deleteCarts(CartVO cartVO) {
        sqlSession.delete("cartMapper.deleteCarts", cartVO);
    }

    @Override
    public List<CartViewVO> selectCartListForBuy(CartVO cartVO) {
        return sqlSession.selectList("cartMapper.selectCartListForBuy", cartVO);
    }
}
