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

public class MemberDaoImpl implements IMemberDao { //MemberDao의 실제 구현 클래스라는 의미

//	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

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
				+ " where mem_id = ? "; //물음표 > PreparedStatement이용
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
				
		return cnt;
	}

	@Override
	public int deleteMember(Connection conn, String memId) throws SQLException {

		String sql = "delete from mymember where mem_id = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(Connection conn, MemberVO mv) throws SQLException {
		
		List<MemberVO> memList= new ArrayList<MemberVO>();
		
		//쿼리 작성
		//생각해야 할 부분: 쿼리를 동적으로 만들어야 하는 시점 -> Dynamic Query
		//ex.정보를 1개만 넣었을 때, 모든 정보(4개)를 넣었을 때 ... 모두 경우를 처리할 수 있도록 
		
		String sql = "select * from mymember where 1=1 "; //where 1=1은 나중에 필요를 알게 될 것
		
		if(mv.getMemId() != null && !mv.getMemId().equals("")) { //아이디가 존재해야 if문 블럭 수행
			sql += " and mem_id = ? "; 
		}
		if(mv.getMemName() != null && !mv.getMemName().equals("")) {
			sql += " and mem_name = ? "; 
		}
		if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
			sql += " and mem_tel = ? "; 
		}
		if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
			sql += " and mem_addr like '%' || ? || '%' "; //주소는 LIKE 검색으로 변경 
		}
		
		//물음표 세팅
		//물음표의 위치가 다이나믹 해졌다.
		pstmt = conn.prepareStatement(sql);
		
		int index = 1; //물음표 시작값이 1이기 때문에 초기화 값은 1
		if(mv.getMemId() != null && !mv.getMemId().equals("")) { //아이디가 존재해야 if문 블럭 수행
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
		
		return memList;
	} 
	
}










