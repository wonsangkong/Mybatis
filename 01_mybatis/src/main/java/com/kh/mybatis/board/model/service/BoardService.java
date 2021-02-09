package com.kh.mybatis.board.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.dao.BoardDao;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;

import static com.kh.mybatis.common.template.SqlSessionTemplate.*;

public class BoardService {
	private BoardDao boardDao = new BoardDao();

	public List<Board> getBoardList(String writer, String title, String content) {
		List<Board> list = null;
		SqlSession session = getSession();
				
		list = boardDao.getBoardList(session, writer, title, content);
				
		session.close();
		
		return list;
	}

	public int getBoardCount(List<String> filters) {
		int result = 0;
		SqlSession session = getSession();
		
		result = boardDao.getBoardCount(session, filters);
		
		session.close();
		
		return result;
	}
	
	public List<Board> getBoardList(PageInfo info, List<String> filters) {
		List<Board> list = null;
		SqlSession session = getSession();
		
		list = boardDao.getBoardList(session, info, filters);
		
		session.close();		
		
		return list;
	}
}
