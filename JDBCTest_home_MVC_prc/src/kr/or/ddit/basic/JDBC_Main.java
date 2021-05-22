package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil3;

/*
위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
 
------------------------------------------------------------

게시판 테이블 구조 및 시퀀스

create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   -- 작성날짜
    board_content clob,     -- 내용
    constraint pk_jdbc_board primary key (board_no)
);
create sequence board_seq
    start with 1   -- 시작번호
    increment by 1; -- 증가값
		
----------------------------------------------------------

// 시퀀스의 다음 값 구하기
//  시퀀스이름.nextVal 
 
 
*/

public class JDBC_Main {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 전체 목록 출력");
		System.out.println("  2. 새 게시글 작성");
		System.out.println("  3. 게시글 수정");
		System.out.println("  4. 게시글 삭제");
		System.out.println("  5. 게시글 검색");
		System.out.println("  6. 작업 끝");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 전체 자료 출력
					displayAll();
					break;
				case 2 :  // 새 게시글 작성
					insertBoard();				
					break;
				case 3 :  // 게시글 수정
					updateBoard();
					break;
				case 4 :  // 게시글 삭제
					deleteBoard();
					break;
				case 5 :  // 게시글 검색
					searchBoard();
					break;
				case 6 :  // 작업 끝
					System.out.println("== 작업 종료 ==");
					JDBCUtil.disConnect(conn, stmt, pstmt, rs);
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요");
			}
		}while(choice!=6);
	}	
	
	private void displayAll() {
		
		List<Map<String, Object>> boardList = new ArrayList<>();
		
		ResultSetMetaData metaData = null;
		
		System.out.println("------------------------------------------------------------------------");
		System.out.println("번호\t\t제목\t\t작성자\t\t작성일");
		System.out.println("------------------------------------------------------------------------");
		
		try {
			
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from jdbc_board order by board_no";
			
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery(sql);
			
//			ResultSetMetaData metaData = rs.getMetaData();
			metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			String[] arrColumn = new String[columnCount];
			while(rs.next()){
				HashMap<String, Object> row = new HashMap<>();
				for(int i = 1; i <= columnCount; i++){
					String key = metaData.getColumnName(i);
					Object value = rs.getObject(i);
					row.put(key, value);
					
					arrColumn[i-1] = metaData.getColumnName(i);
				}
				boardList.add(row);
			}
			
			for(Map<String, Object> board : boardList){
				System.out.println(board.get("BOARD_NO")
						+ "\t\t" + board.get("BOARD_TITLE")
						+ "\t\t" + board.get("BOARD_WRITER")
						+ "\t\t" + board.get("BOARD_DATE"));
			}
			
			System.out.println("------------------------------------------------------------------------");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}

	}


	/**
	 * 새로운 글 작성 메서드
	 * @throws IOException 
	 */
	private void insertBoard() {
		
		scan.nextLine(); //입력버퍼 비우기
		
		System.out.print("글 제목 >> ");
		String board_title = scan.nextLine();
		
		System.out.print("작성자 >> ");
		String board_writer = scan.next();
		
		scan.nextLine();		
		
		System.out.print("글 내용 >> ");
		String board_content = scan.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "insert into jdbc_board "
					+ " (board_no, board_title, board_writer, board_date, board_content) "
					+ " values(board_seq.nextVal, ?, ?, SYSDATE, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board_title);
			pstmt.setString(2, board_writer);
			pstmt.setString(3, board_content);
			
			/* 
			 * 값을 String으로 받더라도 Oracle.CLOB로 저장될 수 있어서,
			 * 여기서 별도의 형변환 기능을 하는 read, write처리를 해주지 않아도 코드가 에러없이 실행되었다.
			 */

			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("[" + board_title + "] 글 작성 성공!");
			}else {
				System.out.println("[" + board_title + "] 글 작성 실패!!!");
			}
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		
	}
	
	private void updateBoard() {
		
		System.out.print("수정 대상 글 번호 >> ");
		int board_no = scan.nextInt();
		
		scan.nextLine(); //입력버퍼 비우기
		
		System.out.print("수정할 글 제목 >> ");
		String board_title = scan.nextLine();
		
		scan.nextLine();		
		
		System.out.print("수정할 글 내용 >> ");
		String board_content = scan.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "UPDATE jdbc_board " + 
					     "   SET board_title = ?, board_content = ? " + 
					     " WHERE board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board_title);
			pstmt.setString(2, board_content);
			pstmt.setInt(3, board_no);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("글 수정 성공!");
			}else {
				System.out.println("글 수정 실패!!!");
			}
								
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
	}
	
	private void deleteBoard() {
		
		System.out.print("삭제 대상 글 번호 >> ");
		int board_no = scan.nextInt();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "DELETE FROM jdbc_board WHERE board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board_no);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("글 삭제 성공!");
			}else {
				System.out.println("글 삭제 실패!!!");
			}
								
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
	}
	
	private void searchBoard() {
		
		HashMap<String, Object> row = new HashMap<>();
		String contents = "";
		
		System.out.print("검색할 글 번호 >> ");
		int board_no = scan.nextInt();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board_no);
			
			rs = pstmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			while(rs.next()){
				for(int i = 1; i <= columnCount; i++){
					String key = metaData.getColumnName(i);
					Object value = rs.getObject(i);
					row.put(key, value);
				}
				
				//SQL에 저장된 CLOB를 String으로 읽어오기
				StringBuffer output = new StringBuffer();
		        Reader input = rs.getCharacterStream("BOARD_CONTENT"); //CLOB가 저장된 컬럼명
		        char[] buffer = new char[1024];
		        int byteRead = 0;
		        while((byteRead=input.read(buffer,0,1024))!=-1){
		             output.append(buffer,0,byteRead);
		        }
		         
		        // contents -> CLOB 데이터가 저장될 String
		        contents = output.toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		
		System.out.println("------------------------------------");
		System.out.println(" 번호\t| " + row.get("BOARD_NO")
				+ "\n 제목\t| " + row.get("BOARD_TITLE")
				+ "\n 작성자\t| " + row.get("BOARD_WRITER")
				+ "\n 내용\t| " + contents 
				+ "\n 작성일\t| " + row.get("BOARD_DATE"));
		System.out.println("------------------------------------");
	}

	public static void main(String[] args) {
		JDBC_Main jdbcObj = new JDBC_Main();
		jdbcObj.start();
	}
}
