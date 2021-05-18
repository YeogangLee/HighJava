package kr.or.ddit.member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil3;

public class MemberServiceImpl implements IMemberService {

	private IMemberDao memDao;
	
	public MemberServiceImpl() {
		memDao = new MemberDaoImpl();
	}	
	
	@Override
	public int insertMember(MemberVO mv) {
		Connection conn = JDBCUtil3.getConnection();
		
		int cnt = 0; 
		
		try {
			cnt = memDao.insertMember(conn , mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//넘겨줄 게 없을 때는 null 넘기기
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			cnt = memDao.deleteMember(conn, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//전부 다 static메서드라 위에선 3을 쓰고 여기선 3이 없어도 괜찮다. 
			JDBCUtil.disConnect(conn, null, null, null);
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO mv) {
		
		int cnt = 0;
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			cnt = memDao.updateMember(conn, mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			memList = memDao.getAllMemberList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return memList;
	}

	@Override
	public boolean checkMember(String memId) {
		
		boolean chk = false;
		
		Connection conn = JDBCUtil3.getConnection();
		
		try {
			chk = memDao.getMember(conn, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		
		return chk;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<>(); //생성자 부분에 <MemberVO> 해주고 싶다 ...
													//하면 안 된다~~~ 생성자 사용할 때는 그냥 <>만 쓰는 게 맞다, 사용한다해도 문제는 없지만
		
		Connection conn = JDBCUtil.getConnection();
		
		try {
			memList = memDao.getSearchMember(conn, mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, null, null, null);
		}
		
		return memList;
	}
	
}
