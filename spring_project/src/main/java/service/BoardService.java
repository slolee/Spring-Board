package service;

import java.util.List;

import dao.BoardDAO;
import vo.BoardVO;

public class BoardService implements BoardService_ {
	private BoardDAO boardDao;
	public void setBoardDao(BoardDAO boardDao) {
		this.boardDao = boardDao;
	}
	
	@Override
	public List<BoardVO> selectList() {
		return boardDao.selectList();
	}
	
	@Override
	public List<BoardVO> selectList(int start, int end) {
		return boardDao.selectList(start, end);
	}

	@Override
	public BoardVO selectOne(int idx) {
		return boardDao.selectOne(idx);
	}
	
	@Override
	public int boardCount() {
		return boardDao.boardCount();
	}

	@Override
	public int insertBoard(BoardVO vo) {
		return boardDao.insertBoard(vo);
	}

	@Override
	public int upHit(int idx) {
		return boardDao.upHit(idx);
	}

	@Override
	public int deleteBoard(int idx) {
		return boardDao.deleteBoard(idx);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		if(vo.getWrite_pw() != null)
			return boardDao.updateBoard(vo);
		return boardDao.updateBoardnoPw(vo);
	}

	@Override
	public String selectFileName(int idx) {
		return boardDao.selectFileName(idx);
	}	
}
