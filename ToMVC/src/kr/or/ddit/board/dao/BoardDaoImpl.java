package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.JDBCUtil3;

public class BoardDaoImpl implements IBoardDao {
	
//	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
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
		String sql = "insert into jdbc_board "
				+ " (board_no, board_title, board_writer, board_date, board_content) "
				+ " values(board_seq.nextVal, ?, ?, SYSDATE, ?) ";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, bv.getBoard_title());
		pstmt.setString(2, bv.getBoard_writer());
		pstmt.setString(3, bv.getBoard_content());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date board_date = null;
		board_date = format.parse(board_str2);
		
		
		
		int cnt = pstmt.executeUpdate();
		
		JDBCUtil3.disConnect(null, stmt, pstmt, rs);
		
		return cnt;
	}

	@Override
	public int deleteBoard(Connection conn, int board_no) throws SQLException {
		
		String sql = "DELETE FROM jdbc_board WHERE board_no = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, board_no);
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	@Override
	public int updateBoard(Connection conn, BoardVO bv) throws SQLException {
		
		String sql = "UPDATE jdbc_board " + 
				     "   SET board_title = ?"
				        + ", board_content = ? " 
				     + " WHERE board_no = ?";

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, bv.getBoard_title());
		pstmt.setString(2, bv.getBoard_content());
		pstmt.setInt(3, bv.getBoard_no());
		
		int cnt = pstmt.executeUpdate();
				
		return cnt;
	}

	@Override
	public List<BoardVO> getSearchBoard(Connection conn, BoardVO bv) throws SQLException {
		List<BoardVO> boardList= new ArrayList<>();
		
		String sql = "select * from jdbc_board where 1=1 "; 
		
		if(bv.getBoard_no() != 0) {
			sql += " and board_no = ? ";
		}
		
		if(bv.getBoard_title() != null && !bv.getBoard_title().equals("")) {
			sql += " and board_title = ? ";
		}
		
		if(bv.getBoard_content() != null && !bv.getBoard_content().equals("")) {
			sql += " and board_content = ? ";
		}
		
		if(bv.getBoard_writer() != null && !bv.getBoard_writer().equals("")) {
			sql += " and board_writer = ? ";
		}
		
		if(bv.getBoard_date() != null && !bv.getBoard_date().equals("")) {
			sql += " and board_date = ? ";
		}
		
		pstmt = conn.prepareStatement(sql);
		
		int index = 1;
		if(bv.getBoard_no() != 0) {
			pstmt.setInt(index++, bv.getBoard_no());
		}
		
		if(bv.getBoard_title() != null && !bv.getBoard_title().equals("")) {
			pstmt.setString(index++, bv.getBoard_title());
		}
		
		if(bv.getBoard_content() != null && !bv.getBoard_content().equals("")) {
			pstmt.setString(index++, bv.getBoard_content());
		}
		
		if(bv.getBoard_writer() != null && !bv.getBoard_writer().equals("")) {
			pstmt.setString(index++, bv.getBoard_writer());
		}
		
		if(bv.getBoard_date() != null && !bv.getBoard_date().equals("")) {
			java.sql.Date sqlDate = new java.sql.Date(bv.getBoard_date().getTime());
			
			
			
			
			pstmt.setDate(index++, sqlDate);
		}
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			BoardVO bv2 = new BoardVO();
			
			bv2.setBoard_content(rs.getString("board_content"));
			bv2.setBoard_date(rs.getDate("board_date"));
			bv2.setBoard_no(rs.getInt("board_no"));
			bv2.setBoard_title(rs.getString("board_title"));
			bv2.setBoard_writer(rs.getString("board_writer"));
			
			boardList.add(bv2);
		}
		
		return boardList;
	}

	@Override
	public List<BoardVO> getAllBoardList(Connection conn) throws SQLException {
		
		List<BoardVO> boardList = new ArrayList<>(); 
		
		String sql = "select * from jdbc_board order by board_no";
		
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			BoardVO bv = new BoardVO();
			
			bv.setBoard_content(rs.getString("board_content"));
			bv.setBoard_date(rs.getDate("board_date"));
			bv.setBoard_no(rs.getInt("board_no"));
			bv.setBoard_title(rs.getString("board_title"));
			bv.setBoard_writer(rs.getString("board_writer"));
			
			boardList.add(bv);
		}
		
		return boardList;
	}

	@Override
	public boolean getBoard(Connection conn, int board_no) throws SQLException {
		boolean check = false;
		
		String sql = "select count(*) as cnt from jdbc_board where board_no = ? ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, board_no);
		
		rs = pstmt.executeQuery();
		
		int count = 0;
		
		if(rs.next()) {
			count = rs.getInt("cnt");
		}
		
		if(count > 0) {
			check = true;
		}
			
		return check;
	}

}
