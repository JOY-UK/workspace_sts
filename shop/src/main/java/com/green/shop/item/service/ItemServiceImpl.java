package com.green.shop.item.service;

import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<CategoryVO> selectCategoryList() {
        return sqlSession.selectList("itemMapper.selectCategoryList");
    }

    @Override
    public List<ItemVO> selectItemList() {
        return sqlSession.selectList("itemMapper.selectItemList");
    }

    @Override
    public ItemVO selectItemDetail(int itemCode) {
        return sqlSession.selectOne("itemMapper.selectItemDetail", itemCode);
    }
}
