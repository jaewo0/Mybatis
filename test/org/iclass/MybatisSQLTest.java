package org.iclass;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.iclass.dao.NewMemberMybatisDao;
import org.iclass.vo.NewMember;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import mybatis.SqlSessionBean;

class MybatisSQLTest {
	private NewMemberMybatisDao dao = NewMemberMybatisDao.getInstance();

	@DisplayName("SQLSession 연결 테스트")
	@Test
	void session() {
		SqlSession mapper = SqlSessionBean.getSession();
		assertNotEquals(mapper, null);
		mapper.close();
	}

	@DisplayName("select 전체 조회 테스트 - 현재 예상 값 = 2")
	@Test
	void selectList() {
		assertEquals(dao.selectAll().size(), 2);

	}

	@DisplayName("insert 테스트 - id : momo로 추가")
	@Test
	void insert() {
		int result = dao.insert(NewMember.builder().id("momo").name("이모모").password("1212").email("momo@naver.com")
				.age(23).gender("2").hobbies("달리기").build());
		assertEquals(result, 1);
	}

	void selectOne() {
		NewMemberMybatisDao dao = NewMemberMybatisDao.getInstance();
		List<NewMember> vo = dao.selectOne("momo");
		assertEquals(((NewMember) vo).getId(), "momo");
	}
}