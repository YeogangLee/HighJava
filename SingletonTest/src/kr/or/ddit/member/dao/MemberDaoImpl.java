package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;

public class MemberDaoImpl implements IMemberDao { //MemberDao의 실제 구현 클래스라는 의미

//	private Connection conn;
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
		
		JDBCUtil.disConnect(null, stmt, pstmt, rs);
		//처음부터 커넥션을 여기서 만들지 않고
		//서비스에서 파라미터로 넘겨주는 이유?
		//여기 프로젝트에서는 이렇게 필요없지만 ...
		//트랜잭션을 사용할시
		//트랜잭션 : 논리적인 작업 단위
		//회원 insert하고 끝날 수도 있겠지만
		/*
		 * 입출금 과정  - 비즈니스 로직
		 * 계좌에서 돈 인출, 잔고 줄이기, 계좌에 추가, 잔고 늘리기
		 * 중간에 에러 발생!
		 * 
		 * 출금 계좌에서만 돈이 사라지는 문제
		 * -> 하나의 묶음으로 처리
		 * 그래서 하나의 트랜잭션을 잘 묶어야 한다
		 * 중간에 에러 발생시 Rollback
		 * 트랜잭션 - ALL or Nothing
		 * 
		 * ibatis에서 startTransaction()등의 메서드 ...
		 * 
		 * 그래서 커넥션이 중요한 의미를 가진다
		 * 트랜잭션이 길어질 수 있다
		 * conn이 close()되기 전까지 하나의 트랜잭션으로 묶을 수 있다.
		 * ex.
		 * try안에서
		 * cnt = memDao.insert(conn,mv)...
		 * cnt = memDao.update(conn,mv)...
		 * mailDao.어쩌구(conn)
		 * => 모두 같은 Connection으로 연결되어 있다
		 * 
		 * //자바의 오토커밋 해제 방법
		 * conn.setAutoCommit(false);
		 * 
		 * //커밋 방법
		 * conn.commit();
		 * try안에서 commit이 일어나므로 위에 코드 수행하다가 에러 발생하면 commit() 수행X
		 * -> 에러 발생 이후 catch블럭로 넘어가게 된다
		 * 
		 * catch 블럭에서 conn.rollback();
		 * 
		 * conn을 자꾸 매개변수로 넘기니
		 * 심플하지 않은 코드가 되었지만, 추후의 트랜잭션 작업을 생각하면 이게 맞다
		 */
		
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










