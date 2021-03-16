package dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.MemberVO;

public class MemberDAO implements MemberDAO_ {
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<MemberVO> memberList() {
		return sqlSession.selectList("member.selectMembers");
	}

	@Override
	public int insertMember(MemberVO vo) {
		return sqlSession.insert( "member.insertMember", vo );
	}

	@Override
	public MemberVO viewMember(String id) {
		return sqlSession.selectOne("member.selectOne", id);
	}

	@Override
	public int deleteMember(String id) {
		return sqlSession.delete("member.deleteMember", id);
	}

	@Override
	public int updateMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int checkPw(String id, String pw) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		
		return sqlSession.selectOne("member.checkPw", map) == null ? 0 : 1;
	}
	
}
