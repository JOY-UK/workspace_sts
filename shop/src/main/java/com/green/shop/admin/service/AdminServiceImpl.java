package com.green.shop.admin.service;

import com.green.shop.admin.vo.SearchBuyVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    //Transactional 어노테이션이 붙어있는
    //메소드 내부의 쿼리 실행은
    //모든 쿼리가 실행 성공 시 커밋!
    //쿼리 중 하나라서 실패 시 롤백!
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertItem(ItemVO itemVO) {
        sqlSession.insert("adminMapper.insertItem", itemVO);
        sqlSession.insert("adminMapper.insertImgs", itemVO);
    }

    @Override
    public int selectNextItemCode() {
        return sqlSession.selectOne("adminMapper.selectNextItemCode");
    }

    @Override
    public List<BuyVO> selectHistory(SearchBuyVO searchBuyVO) {
        return sqlSession.selectList("adminMapper.selectHistory", searchBuyVO);
    }

    @Override
    public BuyVO buyDetail(int buyCode) {
        return sqlSession.selectOne("adminMapper.buyDetail", buyCode);
    }

    @Override
    public List<ItemVO> itemInfoChange() {
        return sqlSession.selectList("adminMapper.itemInfoChange");
    }

    @Override
    public ItemVO itemInfo(int itemCode) {
        return sqlSession.selectOne("adminMapper.itemInfo", itemCode);
    }

    @Override
    public void updateItem(ItemVO itemVO) {
        sqlSession.update("adminMapper.updateItem", itemVO);
    }


}
