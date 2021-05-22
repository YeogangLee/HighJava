package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	
	private static IBoardDao boardDao;
	
	private BoardDaoImpl() {
		
	}
	
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}

	@Override
	public int insertBoard(Connection conn, BoardVO bv) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(Connection conn, String boardNo) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoard(Connection conn, BoardVO bv) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVO> getSearchBoard(Connection conn, BoardVO bv) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> getAllBoardList(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getBoard(Connection conn, String boardNo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
