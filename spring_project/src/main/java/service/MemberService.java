package service;

import java.util.List;

import dao.MemberDAO;
import vo.MemberVO;

public class MemberService implements MemberService_ {

	private MemberDAO memberDao;

	public void setMemberDao(MemberDAO memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public List<MemberVO> memberList() {
		return memberDao.memberList();
	}
	
	@Override
	public int insertMember(MemberVO vo) {
		boolean result = memberDao.viewMember(vo.getId()) == null ? true : false;
		return result ? memberDao.insertMember(vo) : -1;
	}

	@Override
	public int loginMember(MemberVO vo) {
		boolean result = memberDao.viewMember(vo.getId()) == null ? false : true;
		if(result) {
			return memberDao.checkPw(vo.getId(), vo.getPw()) == 0 ? 0 : 1;
		}
		return -1;
	}

	@Override
	public MemberVO viewMember(String id) {
		return memberDao.viewMember(id);
	}

	@Override
	public MemberVO viewMypage(String id, String pw) {
		return memberDao.checkPw(id, pw) == 0 ? null : memberDao.viewMember(id);
	}

	@Override
	public int deleteMember(String id, String pw) {
		boolean result = memberDao.viewMember(id) == null ? false : true;
		if(result) {
			return memberDao.checkPw(id, pw) == 0 ? -1 : memberDao.deleteMember(id);
		}else {
			return -2;
		}
	}
}
