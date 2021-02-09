package com.kh.mybatis.member.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDao {

	public int getMemberCount(SqlSession session) {				
		/*
		 * 마이바티스를 적용했기때문에 SqlSession 객체가 제공하는 메소드를 통해서 SQL 실행 시킨다.
		 * 
		 * 객체 한 개를 조회하기 위해서 SqlSession의 selectOne()메소드를 사용했다.
		 *   - 첫번째 매개값은 쿼리문이 존재하는 "매퍼의네임스페이스.쿼리문아이디" 
		 *     (mybatis-config.xml에 등록된 mapper의 경로대로 탐색한다.)
		 *   - 두번쨰 매개값은 쿼리문에서 사용될 파라미터 객체
		 */
		
		return session.selectOne("memberMapper.selectCount");
	}
	
	public List<Member> findMemberAll(SqlSession session) {
		
		return session.selectList("memberMapper.selectMemberAll");
	}

	public Member findMemberById(SqlSession session, String id) {
		
		return session.selectOne("memberMapper.selectMember", id);
	}

	public int insertMember(SqlSession session, Member member) {		
		
		return session.insert("memberMapper.insertMember", member);
	}

	public int updateMember(SqlSession session, Member member) {
		
		return session.update("memberMapper.updateMember", member);
	}

	public int deleteMember(SqlSession session, int userNo) {
		
		return session.delete("memberMapper.deleteMember", userNo);
	}
}
