package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImpl implements IMemberDao { //MemberDao의 실제 구현 클래스라는 의미

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public int insertMember(Connection conn, MemberVO mv) throws SQLException {
		
		conn = JDBCUtil3.getConnection();
		
		String sql = "insert into mymember "
				+ " (mem_id, mem_name, mem_tel, mem_addr) "
				+ " values(?, ?, ?, ?) ";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, mv.getMemId());
		pstmt.setString(2, mv.getMemName());
		pstmt.setString(3, mv.getMemTel());
		pstmt.setString(4, mv.getMemAddr());
		
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	@Override
	public boolean getMember(Connection conn, String memId) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MemberVO> getAllMemberList(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMember(Connection conn, MemberVO mv) throws SQLException {
		conn = JDBCUtil3.getConnection();
		
		String sql = "update mymember " + 
				"        set mem_name = ? " + 
				"            ,mem_tel = ? " + 
				"            ,mem_addr = ? " + 
				"      where mem_id = ? ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mv.getMemName());
		pstmt.setString(2, mv.getMemTel());
		pstmt.setString(3, mv.getMemAddr());
		pstmt.setString(4, mv.getMemId());
		
		//update라서 update
		int cnt = pstmt.executeUpdate();
		
		if(cnt > 0) {
			System.out.println(mv.getMemName() + "회원의 정보를 수정했습니다.");
		}else {
			System.out.println(mv.getMemName() + "회원 정보 수정 실패!!!");
		}
		
		return 0;
	}

	@Override
	public int deleteMember(Connection conn, String memId) throws SQLException {
		conn = JDBCUtil3.getConnection();
		
		String sql = "delete from mymember where mem_id = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	} 
	
}
