package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	
	public int insertBoard(SqlMapClient smc, BoardVO bv) throws SQLException;
	
	public int deleteBoard(SqlMapClient smc, int board_no) throws SQLException;
	
	public int updateBoard(SqlMapClient smc, BoardVO bv) throws SQLException;
	
	public List<BoardVO> getSearchBoard(SqlMapClient smc, BoardVO bv) throws SQLException;
	
	public List<BoardVO> getAllBoardList(SqlMapClient smc) throws SQLException;
	
	public boolean getBoard(SqlMapClient smc, int board_no) throws SQLException;
}
