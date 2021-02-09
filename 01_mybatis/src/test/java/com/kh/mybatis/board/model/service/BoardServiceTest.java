package com.kh.mybatis.board.model.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.common.util.PageInfo;


@DisplayName("Board 테스트")
class BoardServiceTest {
	private BoardService service;
	
	@BeforeEach
	void setUp() {
		service = new BoardService();
	}

	@Test
	void nothing() {
	}
	
	@Test
	void create() {
		assertThat(service).isNotNull();
	}
	
	@ParameterizedTest
	@CsvSource(
		value = {
			"ADMIN, NIL, NIL",
			" NIL, 안녕, NIL",
			" NIL, NIL, 하이",
			"NIL, NIL, NIL"
		}, 
		nullValues = "NIL")
	void getBoardList(String writer, String title, String content) {
		List<Board> list = null;
		
		list = service.getBoardList(writer, title, content);
		
		assertThat(list).isNotNull();
		assertThat(list.size()).isGreaterThan(0);
	}

	@ParameterizedTest
	@MethodSource("listProvider")
	void getBoardList(int currentPage, List<String> filters) {
		List<Board> list = null;
		int listCount = service.getBoardCount(filters);
		
		PageInfo info = new PageInfo(currentPage, 10, listCount, 10);
		
		list = service.getBoardList(info, filters);
		
//		System.out.println(list);
//		System.out.println(list.size());
//		System.out.println(info);	
		
		assertThat(list).isNotNull();
		assertThat(list.size()).isPositive().isLessThanOrEqualTo(10);
	}
	
	static Stream<Arguments> listProvider() {
		return Stream.of(
			Arguments.arguments(1, Arrays.asList("B1")),
			Arguments.arguments(2, Arrays.asList("B1")),
			Arguments.arguments(3, Arrays.asList("B1")),
			Arguments.arguments(4, Arrays.asList("B1")),
			Arguments.arguments(1, Arrays.asList("B2", "B3"))
		);
	}
}
