package com.kh.mybatis.member.model.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.kh.mybatis.member.model.vo.Member;

@DisplayName("Memeber 테스트")
@TestMethodOrder(OrderAnnotation.class)
class MemberServiceTest {	
	private MemberService service;
	
	// 아래의 테스트 메소드들이 실행되기 전에 무조건 실행되는 메소드
	@BeforeEach
	void setup() {
		service = new MemberService();
	}
	
	@Test
	@Disabled
	void nothing() {
		// 이 테스트를 통해서 현재 프로젝트가 테스트 가능한 환경인지 확인한다.
	}
	
	@Test
	@Disabled
	void create() {
		assertThat(service).isNotNull();
	}
	
	@Test
	@Order(1)
	void memberCount() {
		int count = service.getMemberCount();
		
		assertThat(count).isGreaterThan(0);
	}
	
	@Test
	void findMemberAll() {
		List<Member> list = null;
		
		list = service.findMemberAll();
		
		assertThat(list).isNotNull();
	}	
	
	@ParameterizedTest
	@ValueSource(strings = {"ADMIN", "dlcksgh"})
	@Order(2)
	void findMember(String id) {
		Member member = service.findMemberById(id);
				
		assertThat(member).isNotNull();
		assertThat(member.getUserId()).isEqualTo(id);
	}	
	
	@ParameterizedTest
	@CsvSource({
		"test01, 1234, 김민규",
		"test02, 1234, 백장미"
	})
	@Order(3)
	void saveMember(String id, String pwd, String name) {
		int result = 0;
		Member member = new Member(id, pwd, name);
		
		System.out.println(member);
		
		result = service.saveMember(member);
		
		// 실제로 DB에 Member가 저장되었는지 확인하기 위해서 해당 id로 다시 Member를 조회
		member = service.findMemberById(id);
		
		assertThat(result).isGreaterThan(0);	
		assertThat(member).isNotNull();
		assertThat(member.getUserId()).isNotNull().isEqualTo(id);
	}
	
	@ParameterizedTest
	@CsvSource({
		"test01, 4567, 김승현",
		"test02, 4567, 정희재"
	})
	@Order(4)
	void updateMember(String id, String pwd, String name) {
		int result = 0;
		Member member = service.findMemberById(id);
		
		member.setUserPwd(pwd);
		member.setUserName(name);
		
		result = service.saveMember(member);
		
		// 실제로 DB에서 수정이 되었는지 확인하기 위해서 해당 id로 다시 Member를 조회
		member = service.findMemberById(id);
				
		assertThat(result).isGreaterThan(0);
		assertThat(member).isNotNull();
		assertThat(member.getUserPwd()).isNotNull().isEqualTo(pwd);
		assertThat(member.getUserName()).isNotNull().isEqualTo(name);
	}
		
	@ParameterizedTest
	@ValueSource(strings = {"test01", "test02"})
	@Order(5)
	void deleteMember(String id) {
		int result = 0;
		Member member = service.findMemberById(id);
		
		result = service.deleteMember(member.getUserNo());
		
		// 실제로 DB에서 지워졌는지 확인하기 위해서 해당 id로 다시 Member를 조회
		member = service.findMemberById(id);
		
		assertThat(result).isGreaterThan(0);
		assertThat(member).isNull();
	}
}
