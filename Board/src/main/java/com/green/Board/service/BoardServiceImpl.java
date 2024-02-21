package com.green.Board.service;

import com.green.Board.vo.BoardVo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//BoardServiceImpl boardService = new BoardServiceImpl();
@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int insert(BoardVo boardVo) {
        return sqlSession.insert("boardMapper.insert", boardVo);
    }

    @Override
    public List<BoardVo> select() {
        return sqlSession.selectList("boardMapper.select");
    }

    @Override
    public BoardVo selectDetail(int boardNum) {
        return sqlSession.selectOne("boardMapper.selectDetail",boardNum);
    }

    @Override
    public int delete(int boardNum) {
        return sqlSession.delete("boardMapper.delete", boardNum);
    }

    @Override
    public int updateCnt(int boardNum) {
        return sqlSession.update("boardMapper.updateCnt", boardNum);
    }

    @Override
    public int updateBoard(BoardVo boardVo) {
        return sqlSession.update("boardMapper.updateBoard", boardVo);
    }
}
