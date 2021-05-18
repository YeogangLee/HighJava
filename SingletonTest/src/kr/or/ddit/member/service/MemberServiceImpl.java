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
	
	//기존 생성자
//	public MemberServiceImpl() {
////		memDao = new MemberDaoImpl();		//싱글톤 방식 사용X - 외부에서 new로 객체 생성
//		memDao = MemberDaoImpl.getInstance();	//싱글톤 방식 - public static 메서드를 이용해서, MemberDaoImpl에서 만들어진 객체 들고오기
//	}
	
	//Singleton으로 바꾸기 - private static
	private static IMemberService memService;

	//new로 못 만들게 - private
	private MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
	}
	
	//외부에서 메서드를 이용해 객체를 사용할 수 있게 - public static
	public static IMemberService getInstance() {
		if(memService == null) {
			memService = new MemberServiceImpl(); //외부에서는 생성자를 호출할 수 없지만 나는 호출 가능
		}
		return memService;
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
