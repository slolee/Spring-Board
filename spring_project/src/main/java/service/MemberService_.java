package service;

import java.util.List;

import vo.MemberVO;

public interface MemberService_ {
	public List<MemberVO> memberList();
	public int insertMember(MemberVO vo);
	public int loginMember(MemberVO vo);
	public MemberVO viewMember(String id);
	public MemberVO viewMypage(String id, String pw);
	public int deleteMember(String id, String pw);
	
}
