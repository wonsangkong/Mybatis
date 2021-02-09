package com.kh.mybatis.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int boardNo;
	
	private String userId;	
	
	private String boardTitle;
	
	private String boardContent;
	
	private int boardReadCount;
	
	private String status;
	
	private Date boardCreateDate;
}
