package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 파일 읽기 예제
 * @author PC-14
 *
 */
public class T05_FileStreamTest_Input {
	public static void main(String[] args) {
		//FileInputStream 객체를 이용한 파일 내용 읽기
		FileInputStream fis = null; //선언
		
		/*
		 * 인풋과 아웃풋의 구분
		 * 
		 * 데이터를 가져오고 싶으면 InputStream이 필요
		 * 어떤 대상에 데이터를 보내고 싶다 OutputStream
		 */
		try {
			//방법1 (파일 정보를 문자열로 지정하기)
			fis = new FileInputStream("d:/D_Other/test2.txt");
			
			//방법2 (파일 정보를 File객체를 이용하여 지정하기)
//			File file = new File("d:/D_Other/test2.txt"); //읽기 위해 파일 객체 생성
//			fis = new FileInputStream(file);			  //read하면 1byte씩 읽는다
			
			int c; //읽어온 데이터를 저장할 변수
			
			//읽어온 값이 -1이면 파일의 끝까지 읽었다는 의미
			while((c = fis.read()) != -1) {
				//읽어온 자료 출력하기
				System.out.print((char)c);
				//문자열로 보여주기 위해 캐릭터로 형변환-캐스팅 진행 중
			}
			
			fis.close(); //작업 완료 후 스트림 닫기
						 //finally에 넣으면 더 좋다. 선생님은 그냥 간단하게 ..
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		
	}
}
