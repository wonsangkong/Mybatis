package com.kh.mybatis.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;

import static com.kh.mybatis.common.template.SqlSessionTemplate.*;

import java.util.ArrayList;
import java.util.List;

public class MemberService {
	private MemberDao memberDao = new MemberDao();
	
	public int getMemberCount() {
		int count = 0;
		SqlSession session = getSession();
		
		count = memberDao.getMemberCount(session);
		
		session.close();
		
		return count;
	}
	
	public List<Member> findMemberAll() {
		List<Member> list = null;
		SqlSession session = getSession();
		
		list = memberDao.findMemberAll(session);	
		
		session.close();
		
		return list;
	}

	public Member findMemberById(String id) {
		Member member = null;
		SqlSession session = getSession();
		
		member = memberDao.findMemberById(session, id);
		
		session.close();
		
		return member;
	}

	public int saveMember(Member member) {
		int result = 0;
		SqlSession session = getSession();
		
		if(member.getUserNo() != 0) {
			result = memberDao.updateMember(session, member);
		} else {
			result = memberDao.insertMember(session, member);
		}
		
		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		return result;
	}

	public int deleteMember(int userNo) {
		int result = 0;
		SqlSession session = getSession();
		
		result = memberDao.deleteMember(session, userNo);
		
		if(result > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		
		return result;
	}
}
