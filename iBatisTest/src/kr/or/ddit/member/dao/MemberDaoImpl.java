package kr.or.ddit.member.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;

public class MemberDaoImpl implements IMemberDao { //MemberDao의 실제 구현 클래스라는 의미

//	private SqlMapClient smc;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//Singleton으로 바꾸기
	private static IMemberDao memDao; //나 자신(인터페이스 타입)을 private으로 선언
	
	//생성자를 private으로 생성, 외부에서 new키워드로 객체를 만들 수 없게 하기 위해
	private MemberDaoImpl() {
		
	}
	
	//외부에서 메서드는 사용해야 하니까 public
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}

	@Override
	public int insertMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		
		int cnt = 0;
		
		Object obj = smc.insert("member.insertMember", mv);
		
		if(obj == null) {
			cnt = 1;
		}//else는 어차피 0 넘어가니까 작성x
		
		return cnt;
	}

	@Override
	public boolean getMember(SqlMapClient smc, String memId) throws SQLException {
		
		boolean check = false;
		
		int count = (int) smc.queryForObject("member.getMember", memId);
		
		if(count > 0) {
			check = true;
		}
		
		return check;
	}

	@Override
	public List<MemberVO> getAllMemberList(SqlMapClient smc) throws SQLException {
		
		List<MemberVO> memList = 
				smc.queryForList("member.getMemberAll"); 
				
		return memList;
	}

	@Override
	public int updateMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		
		int cnt = smc.update("member.updateMember", mv);
				
		return cnt;
	}

	@Override
	public int deleteMember(SqlMapClient smc, String memId) throws SQLException {

		int cnt = smc.delete("member.deleteMember", memId);
		
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(SqlMapClient smc, MemberVO mv) throws SQLException {
		
		List<MemberVO> memList =
				smc.queryForList("member.getSearchMember", mv);
		
		return memList;
	}
	
}










