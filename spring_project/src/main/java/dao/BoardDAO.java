package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.BoardVO;

public class BoardDAO implements BoardDAO_ {
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<BoardVO> selectList() {
		return sqlSession.selectList("board.boardList");
	}
	
	@Override
	public List<BoardVO> selectList(int start, int end) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> board_list = sqlSession.selectList("board.boardList_condition", map);
		return board_list;
	}

	@Override
	public BoardVO selectOne(int idx) {
		return sqlSession.selectOne("board.boardRead", idx);
	}
	
	@Override
	public int boardCount() {
		return sqlSession.selectOne("board.board_count");
	}

	@Override
	public int insertBoard(BoardVO vo) {
		int result = 0;
		if(vo.getWrite_pw() == null)
			result = sqlSession.insert("board.board_insert_nopw", vo);
		else
			result = sqlSession.insert("board.board_insert", vo);
		return result;
	}

	@Override
	public int upHit(int idx) {
		return sqlSession.update("board.upHit", idx);
	}

	@Override
	public int deleteBoard(int idx) {
		return sqlSession.delete("board.board_deleteOne", idx);
	}

	@Override
	public int updateBoard(BoardVO vo) {
		return sqlSession.update("board.board_updateOne", vo);
	}

	@Override
	public int updateBoardnoPw(BoardVO vo) {
		return sqlSession.update("board.board_updateOne_noPw", vo);
	}

	@Override
	public String selectFileName(int idx) {
		return sqlSession.selectOne("board.board_selectFileName", idx);
	}
}
