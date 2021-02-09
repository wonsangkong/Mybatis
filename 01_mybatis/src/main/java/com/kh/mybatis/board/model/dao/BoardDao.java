package com.kh.mybatis.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;


public class BoardDao {

	public List<Board> getBoardList(SqlSession session, String writer, String title, String content) {
		Map<String, String> map = new HashMap<>();
		
		map.put("writer", writer);
		map.put("title", title);
		map.put("content", content);		
		
		return session.selectList("boardMapper.selectBoardList", map);
	}
	
	public int getBoardCount(SqlSession session, List<String> filters) {
		Map<String, List<String>> map = new HashMap<>();
		
		map.put("filters", filters);
		
		return session.selectOne("boardMapper.selectBoardCountByFilters", map);
	}

	public List<Board> getBoardList(SqlSession session, PageInfo info, List<String> filters) {
		/*
		 * List 타입이나 Array 타입의 데이터를 파라미터로 전달하면 내부적으로 Map으로 타입이 변환되어서 저장되기 때문에
		 * Mapper에서는 list나 array라는 이름으로 사용해야한다.
		 * 
		 * 만약에 filters라는 이름을 Mapper에서 사용하고 싶으면 map에 담아서 파라미터로 전달한다.
		 * 
		 */
		Map<String, List<String>> map = new HashMap<>();
		
		map.put("filters", filters);
		
		/*
		 * JSP /Servlet에서는 쿼리문에서 RowNum을 조건을 통해서 페이징 처리를 하였다.
		 * 하지만 마이바티스에서는 페이징 처리를 위해 RowBounds라는 클래스를 제공한다.
		 * 
		 * RowBounds => offset과 Limit값을 넘겨 받아서 페이징 처리를 쉽게 구현
		 * 				(offset만큼 건너띄고 limit 만큼 가져온다.)
		 * 
		 * offset - 몇 개의 게시글을 건너뛰고 조회를 할지에 대해서 계산한다.
		 * 
		 * offset = 0, limit = 10
		 *  - 0개를 건너띄고 10개를 가져온다. 1 ~ 10
		 * offset = 10, limit = 10
		 *  - 10개를 건너띄고 10개를 가져온다. 11 ~ 20
		 * offset = 20, limit = 10
		 *  - 20개를 건너띄고 10개를 가져온다. 21 ~ 30
		 */
				
		int offset = (info.getCurrentPage() - 1) * info.getListLimit();
		RowBounds rowBounds = new RowBounds(offset, info.getListLimit());
		
		return session.selectList("boardMapper.selectBoardListByFilters", map, rowBounds);
//		return session.selectList("boardMapper.selectBoardListByFilters", filters);
	}
}
