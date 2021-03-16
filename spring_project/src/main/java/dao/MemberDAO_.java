package dao;

import java.util.List;

import vo.MemberVO;

public interface MemberDAO_ {
	public List<MemberVO> memberList();
	public int insertMember(MemberVO vo);
	public MemberVO viewMember(String id);
	public int deleteMember(String id);
	public int updateMember(MemberVO vo);
	public int checkPw(String id, String pw);
	
}
