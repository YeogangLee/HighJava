//package kr.or.ddit.member.main;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Scanner;
//
//import kr.or.ddit.util.JDBCUtil3;
//
///*
//	회원정보를 관리하는 프로그램을 작성하는데 
//	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
//	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
//	
//	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
//	
//	예시메뉴)
//	----------------------
//		== 작업 선택 ==
//		1. 자료 입력			---> insert
//		2. 자료 삭제			---> delete
//		3. 자료 수정			---> update
//		4. 전체 자료 출력	---> select
//		5. 작업 끝.
//	----------------------
//	 
//	   
//// 회원관리 프로그램 테이블 생성 스크립트 
//create table mymember(
//    mem_id varchar2(8) not null,  -- 회원ID
//    mem_name varchar2(100) not null, -- 이름
//    mem_tel varchar2(50) not null, -- 전화번호
//    mem_addr varchar2(128),    -- 주소
//    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
//);
//
//*/
//public class MemberMain2 {
//	
//	private Connection conn;
//	private Statement stmt;
//	private PreparedStatement pstmt;
//	private ResultSet rs;
//	
//	private Scanner scan = new Scanner(System.in); 
//	
//	/**
//	 * 메뉴를 출력하는 메서드
//	 */
//	public void displayMenu(){
//		System.out.println();
//		System.out.println("----------------------");
//		System.out.println("  === 작 업 선 택 ===");
//		System.out.println("  1. 자료 입력");
//		System.out.println("  2. 자료 삭제");
//		System.out.println("  3. 자료 수정");
//		System.out.println("  4. 전체 자료 출력");
//		System.out.println("  5. 작업 끝.");
//		System.out.println("----------------------");
//		System.out.print("원하는 작업 선택 >> ");
//	}
//	
//	/**
//	 * 프로그램 시작메서드
//	 */
//	public void start(){
//		int choice;
//		do{
//			displayMenu(); //메뉴 출력
//			choice = scan.nextInt(); // 메뉴번호 입력받기
//			switch(choice){
//				case 1 :  // 자료 입력
//					insertMember();
//					break;
//				case 2 :  // 자료 삭제
//					deleteMember();
//					break;
//				case 3 :  // 자료 수정
//					updateMember();
//					break;
//				case 4 :  // 전체 자료 출력
//					displayMemberAll();
//					break;
//				case 5 :  // 작업 끝
//					System.out.println("작업을 마칩니다.");
//					break;
//				default :
//					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
//			}
//		}while(choice!=5);
//	}	
//	
//	/**
//	 * 회원 정보를 삭제하는 메서드
//	 * (입력받은 회원id를 이용하여 삭제한다.)
//	 */
//	private void deleteMember() {
//		System.out.println();
//		System.out.println("삭제할 회원 정보를 입력하세요.");
//		System.out.print("회원 ID >> ");
//		
//		String memId = scan.next();
//		
//		try {
//			conn = JDBCUtil3.getConnection();
//			
//			String sql = "delete from mymember where mem_id = ?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, memId);
//			int cnt = pstmt.executeUpdate();
//			
//			if(cnt > 0) {
//				System.out.println(memId + "회원 삭제 성공!");
//			}else {				
//				System.out.println(memId + "회원 삭제 실패!!!");
//			}
//			
//		}catch(SQLException ex) {
//			System.out.println(memId + "회원 삭제 실패!!!");
//			ex.printStackTrace();
//		}finally {
//			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
//		}
//	}
//
//	/**
//	 * 회원 정보를 수정하는 메서드
//	 */
//	private void updateMember() {
//		boolean chk = false;
//		String memId = "";
//		
//		do {
//			System.out.println();
//			System.out.println("수정할 회원 정보를 입력하세요.");
//			System.out.print("회원 ID >> ");
//			memId = scan.next();
//			
//			chk = checkMmeber(memId);		
//			
//			//회원이 존재해야 수정의 의미가 있으므로
//			if(chk == false) {
//				System.out.println("회원 ID가 " + memId + "인 회원은 없는 회원입니다."); 
//				System.out.println("ID를 다시 입력하세요.");
//			}
//			
//		}while(chk == false);
//		
//		System.out.print("회원 이름 >> ");
//		String memName = scan.next();
//		
//		System.out.print("전화번호 >> ");
//		String memTel = scan.next();
//		
//		scan.nextLine(); //입력버퍼 비우기
//		
//		System.out.print("회원 주소 >> ");
//		String memAddr = scan.nextLine();
//		
//		try {
//			conn = JDBCUtil3.getConnection();
//			
//			String sql = "update mymember " + 
//					"        set mem_name = ? " + 
//					"            ,mem_tel = ? " + 
//					"            ,mem_addr = ? " + 
//					"      where mem_id = ? ";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, memName);
//			pstmt.setString(2, memTel);
//			pstmt.setString(3, memAddr);
//			pstmt.setString(4, memId);
//			
//			//update라서 update
//			int cnt = pstmt.executeUpdate();
//			
//			if(cnt > 0) {
//				System.out.println(memId + "회원의 정보를 수정했습니다.");
//			}else {
//				System.out.println(memId + "회원 정보 수정 실패!!!");
//			}
//			
//		}catch(SQLException ex) {
//			ex.printStackTrace();
//		}finally {
//			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);			
//		}
//		
//	}
//
//	/**
//	 * 전체 회원을 출력하는 메서드
//	 */
//	private void displayMemberAll() {
//		System.out.println();		
//		System.out.println("-------------------------------------------");
//		System.out.println("ID\t이 름\t전화번호\t\t주    소\t");
//		System.out.println("-------------------------------------------");
//		
//		//DB를 들고올 거니까, 미리 만들어둔 커넥션 들고오기
//		try {
//			conn = JDBCUtil3.getConnection();
//			
//			String sql = "select * from mymember";
//			
//			stmt = conn.createStatement();
//			
//			//select라서 update아니고 query
//			rs = stmt.executeQuery(sql);
//			
//			while(rs.next()) {
//				String memId = rs.getString("mem_id");
//				String memName = rs.getString("mem_name");
//				String memTel = rs.getString("mem_tel");
//				String memAddr = rs.getString("mem_addr");
//				
//				System.out.println(memId + "\t" + memName + "\t" + memTel + "\t" + memAddr);
//				System.out.println("-------------------------------------------");
//			}
//			System.out.println("전체 회원 출력 작업 끝...");
//			
//		}catch(SQLException ex) {
//			System.out.println("회원 자료 가져오기 실패!!!");
//			ex.printStackTrace();
//		}finally {
//			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
//		}
//	}
//
//	/**
//	 * 회원 추가 메서드
//	 */
//	private void insertMember() {
//		boolean chk = false;
//		String memId = "";
//		
//		do {
//			//화면단
//			System.out.println();
//			System.out.println("추가할 회원 정보를 입력하세요.");
//			System.out.print("회원 ID >> ");
//			memId = scan.next();
//			
//			chk = checkMmeber(memId);		
//			
//			if(chk == true) {
//				System.out.println("회원 ID가 " + memId + "인 회원은 이미 존재합니다."); 
//				System.out.println("ID를 다시 입력하세요.");
//			}
//			
//		}while(chk == true);
//		
//		System.out.print("회원 이름 >> ");
//		String memName = scan.next();
//		
//		System.out.print("전화번호 >> ");
//		String memTel = scan.next();
//		
//		scan.nextLine(); //입력버퍼 비우기
//		
//		System.out.print("회원 주소 >> ");
//		String memAddr = scan.nextLine();
//		
//		try {
//			conn = JDBCUtil3.getConnection();
//			
//			String sql = "insert into mymember "
//					+ " (mem_id, mem_name, mem_tel, mem_addr) "
//					+ " values(?, ?, ?, ?) ";
//			
//			pstmt = conn.prepareStatement(sql);
//			//바로 쿼리를 날려주면 돼요 안돼요?
//			//안돼요 -> 물음표 값 지정
//			
//			pstmt.setString(1, memId);
//			pstmt.setString(2, memName);
//			pstmt.setString(3, memTel);
//			pstmt.setString(4, memAddr);
//			
//			//executeQuery말고 Update 해야 한다, 파라미터 없는 거 실행하는 거니까
//			//파라미터가 있는데?? 
//			int cnt = pstmt.executeUpdate();
//			
//			if(cnt > 0) {
//				System.out.println(memId + "회원 추가 작업 성공!");
//			}else {
//				System.out.println(memId + "회원 추가 작업 실패!!!");
//			}
//								
//		}catch (SQLException ex) {
//			ex.printStackTrace();
//		}	
//		
//		
//	}
//	
//
//	/**
//	 * 회원 ID를 이용하여 회원이 있는지 알려주는 메서드
//	 * @param memId 체크할 회원ID
//	 * @return 존재하면 true, 아니면 false
//	 */
//	private boolean checkMmeber(String memId) {
//		
//		boolean check = false;
//		
//		try {
//			conn = JDBCUtil3.getConnection();
//			
//			String sql = "select count(*) as cnt from mymember "
//					+ " where mem_id = ? "; //물음표 > PreparedStatement이용
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, memId);
//			
//			rs = pstmt.executeQuery();
//			
//			int count = 0;
//			
//			if(rs.next()) {
//				count = rs.getInt("cnt");
//			}
//			
//			if(count > 0) {
//				check = true;
//			}
//			
//		}catch(SQLException ex) {
//			ex.printStackTrace();
//			check = false;
//		}finally {
//			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
//		}
//		
//		return check;
//	}
//
//	public static void main(String[] args) {
//		MemberMain2 memObj = new MemberMain2();
//		memObj.start();
//	}
//
//}
