package dao;

import java.util.List;

import vo.BoardVO;

public interface BoardDAO_ {
	public List<BoardVO> selectList();
	public List<BoardVO> selectList(int start, int end);
	public BoardVO selectOne(int idx);
	public int boardCount();
	public int insertBoard(BoardVO vo);
	public int upHit(int idx);
	public int deleteBoard(int idx);
	public int updateBoard(BoardVO vo);
	public int updateBoardnoPw(BoardVO vo);
	public String selectFileName(int idx);
}
