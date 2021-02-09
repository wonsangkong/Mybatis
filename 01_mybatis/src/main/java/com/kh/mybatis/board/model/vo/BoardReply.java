package com.kh.mybatis.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardReply {
	private int replyNO;
	
	private int boardNO;
	
	private int replyWriterNo;
	
	private String userId;
	
	private String replyContent;	
	
	private Date replyCreateDate;
	
	private Date replyModifyDate;
}



