package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImpl implements IMemberDao { //IMemberDao의 실제 구현(implements) 클래스라는 의미

//	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//Singleton으로 바꾸기
	private static IMemberDao memDao;
	
	private MemberDaoImpl() {
		
	}
	
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}

	@Override
	public int insertMember(Connection conn, MemberVO mv) throws SQLException {
		
		String sql = "insert into mymember "
				+ " (mem_id, mem_name, mem_tel, mem_addr) "
				+ " values(?, ?, ?, ?) ";
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, mv.getMemId());
		pstmt.setString(2, mv.getMemName());
		pstmt.setString(3, mv.getMemTel());
		pstmt.setString(4, mv.getMemAddr());
		
		int cnt = pstmt.executeUpdate();
		
		JDBCUtil3.disConnect(null, stmt, pstmt, rs);
		
		return cnt;
	}

	@Override
	public boolean getMember(Connection conn, String memId) throws SQLException {
		
		boolean check = false;
				
		String sql = "select count(*) as cnt from mymember "
				+ " where mem_id = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		rs = pstmt.executeQuery();
		
		int count = 0;
		
		if(rs.next()) {
			count = rs.getInt("cnt");
		}
		
		if(count > 0) {
			check = true;
		}
		
		JDBCUtil3.disConnect(null, stmt, pstmt, rs);
			
		return check;
	}

	@Override
	public List<MemberVO> getAllMemberList(Connection conn) throws SQLException {
		
		List<MemberVO> memList = new ArrayList<MemberVO>(); 
				
		String sql = "select * from mymember";
		
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			MemberVO mv = new MemberVO();
			
			//MemberVO객체 mv의 값 설정
			mv.setMemName(rs.getString("mem_name"));
			mv.setMemId(rs.getString("mem_id"));
			mv.setMemTel(rs.getString("mem_tel"));
			mv.setMemAddr(rs.getString("mem_addr"));
			
			//memList에 MemberVO객체 mv 담기
			memList.add(mv);
		}
		
		JDBCUtil3.disConnect(null, stmt, pstmt, rs);
		
		return memList;
	}

	@Override
	public int updateMember(Connection conn, MemberVO mv) throws SQLException {
		
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
		
		int cnt = pstmt.executeUpdate();
		
		JDBCUtil3.disConnect(null, stmt, pstmt, rs);
				
		return cnt;
	}

	@Override
	public int deleteMember(Connection conn, String memId) throws SQLException {

		String sql = "delete from mymember where mem_id = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		int cnt = pstmt.executeUpdate();
		
		JDBCUtil3.disConnect(null, stmt, pstmt, rs);
		
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(Connection conn, MemberVO mv) throws SQLException {
		
		List<MemberVO> memList= new ArrayList<MemberVO>();
		
		String sql = "select * from mymember where 1=1 "; 
		
		if(mv.getMemId() != null && !mv.getMemId().equals("")) { 
			sql += " and mem_id = ? "; 
		}
		if(mv.getMemName() != null && !mv.getMemName().equals("")) {
			sql += " and mem_name = ? "; 
		}
		if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
			sql += " and mem_tel = ? "; 
		}
		if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
			sql += " and mem_addr like '%' || ? || '%' "; 
		}

		//pstmt에 작성한 쿼리 넣기
		pstmt = conn.prepareStatement(sql);
		
		//물음표 값 세팅
		int index = 1; //물음표 시작값이 1이기 때문에 초기화 값은 1
		if(mv.getMemId() != null && !mv.getMemId().equals("")) {
			pstmt.setString(index++, mv.getMemId());
		}
		if(mv.getMemName() != null && !mv.getMemName().equals("")) {
			pstmt.setString(index++, mv.getMemName());
		}
		if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
			pstmt.setString(index++, mv.getMemTel());
		}
		if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
			pstmt.setString(index++, mv.getMemAddr());
		}
		
		//완전한 쿼리(pstmt + 물음표) 넘겨주기
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			//위에서 선언한 MemberVO객체(mv)와 다른 객체 mv2 선언 
			MemberVO mv2 = new MemberVO();
			
			//MemberVO객체 mv2의 값 설정
			mv2.setMemName(rs.getString("mem_name"));
			mv2.setMemId(rs.getString("mem_id"));
			mv2.setMemTel(rs.getString("mem_tel"));
			mv2.setMemAddr(rs.getString("mem_addr"));
			
			//memList에 MemberVO객체 mv 담기
			memList.add(mv2);
		}
		
		JDBCUtil3.disConnect(null, stmt, pstmt, rs);
		
		return memList;
	} 
	
}