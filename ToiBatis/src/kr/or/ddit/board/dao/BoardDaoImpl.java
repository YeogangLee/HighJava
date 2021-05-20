package kr.or.ddit.board.dao;

//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	
//	private Statement stmt;
//	private PreparedStatement pstmt;
//	private ResultSet rs;
	
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
	public int insertBoard(SqlMapClient smc, BoardVO bv) throws SQLException {
		
		int cnt = 0;
		
		Object obj = smc.insert("board.insertBoard", bv);
		
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(SqlMapClient smc, int board_no) throws SQLException {
		
		int cnt = smc.delete("board.deleteBoard", board_no);
		
		return cnt;
	}

	@Override
	public int updateBoard(SqlMapClient smc, BoardVO bv) throws SQLException {
		
		int cnt = smc.update("board.updateBoard", bv);
		
		return cnt;
	}

	@Override
	public List<BoardVO> getSearchBoard(SqlMapClient smc, BoardVO bv) throws SQLException {

		List<BoardVO> boardList =
				smc.queryForList("board.getSearchBoard", bv);
				
		return boardList;
	}

	@Override
	public List<BoardVO> getAllBoardList(SqlMapClient smc) throws SQLException {
				
		List<BoardVO> boardList = 
				smc.queryForList("board.getBoardAll"); 
		
		return boardList;
	}

	@Override
	public boolean getBoard(SqlMapClient smc, int board_no) throws SQLException {
		
		boolean check = false;
		
		int count = (int) smc.queryForObject("board.getBoard", board_no);
		
		
		if(count > 0) {
			check = true;
		}
			
		return check;
	}

}
