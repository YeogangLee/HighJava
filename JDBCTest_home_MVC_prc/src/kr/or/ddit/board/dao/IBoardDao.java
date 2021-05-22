package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	public int insertBoard(Connection conn, BoardVO bv) throws SQLException;
	public int deleteBoard(Connection conn, String boardNo) throws SQLException;
	public int updateBoard(Connection conn, BoardVO bv) throws SQLException;
	public List<BoardVO> getSearchBoard(Connection conn, BoardVO bv) throws SQLException;
	public List<BoardVO> getAllBoardList(Connection conn) throws SQLException;
	public boolean getBoard(Connection conn, String boardNo) throws SQLException;
}
