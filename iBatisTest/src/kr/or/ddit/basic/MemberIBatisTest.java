package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MemberIBatisTest {
	public static void main(String[] args) {
		
		// iBatis를 이용하여 DB자료를 처리하는 작업 순서
		// 1. iBatis의 환경설정파일을 읽어와 실행시킨다.
		try {
			// 1-1. xml문서 읽어오기
			Charset charset = Charset.forName("UTF-8"); //설정 파일의 인코딩 설정
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); //Reader 객체 닫기
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
			
			// 2-1. insert작업 연습
			System.out.println("insert 작업 시작...");
			
			// 1) 저장할 데이터를 VO에 담는다.
			MemberVO mv = new MemberVO();
			mv.setMemId("d001");
			mv.setMemName("강감찬");
			mv.setMemTel("010-1111-1111");
			mv.setMemAddr("경상남도 진주시");
			
			// 2) SqlMapClient 객체 변수를 이용하여 해당 쿼리문을 실행한다.
			// 형식) smc.insert("namespace값.id값", 파라미터클래스);
			//		반환값 : 성공하면 null이 반환 -> 성공하면 아무 결과도 없다는 뜻
//			Object obj = smc.insert("memberTest.insertMember", mv);
			
			//namespace를 사용하면 insert가 id로서 중복될 걱정 안 해도 된다.
//			Object obj = smc.insert("memberTest.insert", mv);
			
			//namespace를 사용하지 않을시, .앞에 부분이 사라진다 
//			Object obj = smc.insert("insert", mv);
//			if(obj == null) {
//				System.out.println("insert 작업 성공!");
//			}else {
//				System.out.println("insert 작업 실패!!!");
//			}
//			System.out.println("---------------------------------");
//			
			// 2-2. update 작업 연습
			System.out.println("update 작업 시작...");
			
			MemberVO mv2 = new MemberVO();
			mv2.setMemId("d001");
			mv2.setMemName("이순신");
			mv2.setMemTel("010-3333-3333");
			mv2.setMemAddr("서울시 영등포구");
			
			// update()메서드의 반환값은 성공한 레코드 수이다.
			int cnt = smc.update("memberTest.updateMember", mv2);
			//There is no statement named memberTest.updateMember in this SqlMap.
			//이 에러만큼은 선생님을 부르지 말아주세요 ...
			//스펠링 다 맞았는데 그래도 틀리면, 환경 설정 파일의 경로라던가 ...
			//iBatis의 작업 범위 - 환경 설정 파일 SqlMapConfig.xml
			/*
			 * 쿼리 검색은 쿼리를 환경 설정 파일에 등록을 해야 가능
			 * 환경 설정 파일에 등록 안 해놓으면 실행 안 된다.
			 * 지금 쿼리를 쓰는 게 memberTest.xml이니까,
			 * 쿼리가 들어있는 xml파일과 환경 설정 파일인 SqlMapConfig.xml을 잘 연결시켜줘야 한다.
			 * (SqlMapConfig 제일 하단에서 쿼리가 담긴 xml파일 경로 설정)
			 */
			
			if(cnt > 0) {
				System.out.println("update 작업 성공!");
			}else {
				System.out.println("update 작업 실패!!!");
			}
			System.out.println("---------------------------------");
			/*
			 * insert와 차이점
			 * insert는 null값 Object 반환, update는 int값 반환
			 */
			
			// 2-3. delete 연습
			System.out.println("delete 작업 시작...");
			
			// delete메서드의 반환값 : 성공한 레코드 수
			
			int cnt2 = smc.delete("memberTest.deleteMember","d001");
			
			if(cnt2 > 0) {
				System.out.println("delete 작업 성공!");
			}else {				
				System.out.println("delete 작업 실패!!!");
			}
			
			/*
			 * 데이터가 1건인데도 List에 넣어서 받고 싶을 수 있고,
			 * 객체 자체를, Object단위로 받고 싶을 수 있다.
			 * 그런 경우를 위해 구분해놨다.
			 * 
			 */
			// 2-4. select 연습
			// 1) 응답의 결과가 여러 개일 경우
			System.out.println("select 연습 시작(결과가 여러 개일 경우...)");
			
			
			//응답 결과가 여러 개일 경우에는 queryForList메서드를 사용한다.
			//이 메서드는 여러 개의 레코드를 VO에 담은 후 이 VO데이터를 List에
			//추가해주는 작업을 자동으로 수행한다.
//			List<MemberVO> memList = smc.queryForList("memberTest.getMemberAll");
													//전체를 출력하는 거라 파라미터 필요X
//			for(MemberVO vo : memList) {
//				System.out.println("ID : " + vo.getMemId());
//				System.out.println("이름 : " + vo.getMemName());
//				System.out.println("전화 : " + vo.getMemTel());
//				System.out.println("주소 : " + vo.getMemAddr());
//				System.out.println("========================");
//			}
//			System.out.println("출력 끝...");
			
			//데이터 한 건에 집중
			//2) 응답이 1개일 경우
			System.out.println("select 연습 시작(결과가 1개일 경우...");
			
			//응답 결과가 1개가 확실할 경우에는 queryForObject 메서드를 사용한다.
			MemberVO mv3 = (MemberVO) smc.queryForObject("memberTest.getMember", "h001");
						
				System.out.println("ID : " + mv3.getMemId());
				System.out.println("이름 : " + mv3.getMemName());
				System.out.println("전화 : " + mv3.getMemTel());
				System.out.println("주소 : " + mv3.getMemAddr());
				System.out.println("========================");
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
}
