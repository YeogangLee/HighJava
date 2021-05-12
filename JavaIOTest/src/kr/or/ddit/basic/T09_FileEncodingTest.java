package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09_FileEncodingTest {
/*
 * 인코딩 방식
 * 
 * 한글 인코딩 방식은 크게 UTF-8과 EUC-KR 두 방식으로 나뉜다.
 * 원래 한글 윈도우는 CP949방식을 사용했는데, 윈도우를 개발한 마이크로소프트에서
 * EUC-KR 방식에서 확장하였기 때문에 MS949라고도 부른다.
 * 
 * 한글 Windows의 메모장에서 이야기하는 ANSI 인코딩이란, CP949(Code Page 949)를 말한다.
 * ANSI: American National Standards Institute, ANSI
 * 
 * - MS949    => 윈도우의 기본 한글 인코딩 방식(ANSI계열) 
 * - UTF-8 	  => 유니코드 UTF-8 인코딩 방식(영문자 및 숫자: 1byte, 한글: 3bytes) => 가변적
 * - US-ASCII => 영문 전용 인코딩 방식
 * 
 * 
 * | 유니코드(Unicode) |
 *  서로 다른 문자 인코딩을 사용하는 컴퓨터 간의 문서 교환에 어려움을 겪게 되고,
 *  이런 문제점을 해결하기 위해 전 세계의 모든 문자를 하나의 통일된 문자 집합(Charset)으로 표현함.
 *  
 *  유닉스, 리눅스 등은 여전히 EUC-KR 방식을 많이 사용한다
 *  코드표가 있는 내부에서는 문제가 되지 않지만, 코드표가 없는 외부에서는 문제가 발생한다, 관심도 없고.
 *  안되겠다, 세상에 있는 모든 문자, 기호, 코드를 모아서 하나로 표현하자! -> 유니코드
 *  
 *  EUC-KR에 CP949, MS949들이 포괄적으로 포함된다.
 */
	
	public static void main(String[] args) {
		//파일 인코딩을 이용하여 읽어오기
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
		/*
		 * FileInputStream 객체를 생성한 후
		 * 이 객체를 매개변수로 받는 InputStreamReader객체를 생성한다.
		 */
			fis = new FileInputStream("d:/D_Other/test_utf8.txt");
			//인코딩이 어떻게 되어 있을까? 고민을 한번 해봐야 한다... 한글을 사용하는 이상. 숫자, 영어는 깨지지 않는다.
			isr = new InputStreamReader(fis, "UTF-8"); //ANSI나 CP949로 했을 때, "MS949", "EUC-KR"로 해도 문제X
			//InputStreamReader: 바이트 기반 InputStream을 문자로 변환하도록 도와준다.
			//바이트로 읽어오는 기능은 없고, 2바이트를 가져다 변환해주는 기능만 한다.
			
			
			int c;
			while((c=isr.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println();
			System.out.println("출력 끝...");
					
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				isr.close(); //보조 스트림만 닫아도, 기반 스트림이 자동으로 닫힌다.
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
