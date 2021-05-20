package kr.or.ddit.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.JDBCUtil3;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao boardDao;
	
	private static IBoardService boardService;
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}

	@Override
	public int insertBoard(BoardVO bv) {
		Connection conn = JDBCUtil3.getConnection();
		
		int cnt = 0; 
		
		try {
			cnt = boardDao.insertBoard(conn, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int board_no) {
		int cnt = 0;
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			cnt = boardDao.deleteBoard(conn, board_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally { 
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			cnt = boardDao.updateBoard(conn, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		List<BoardVO> memList = new ArrayList<>();
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			memList = boardDao.getSearchBoard(conn, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return memList;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		List<BoardVO> memList = new ArrayList<>();
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			memList = boardDao.getAllBoardList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return memList;
	}

	@Override
	public boolean checkBoard(int board_no) {
		boolean check = false;
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			check = boardDao.getBoard(conn, board_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return check;
	}

}
