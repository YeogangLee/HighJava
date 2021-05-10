package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.IOException;

public class T08_FileReaderTest {
	public static void main(String[] args) throws IOException {
		//문자 기반의 스트림을 이용한 파일 내용 읽기
		FileReader fr = null;
		
		//문자 단위의 입력을 담당하는 Reader형 객체 생성
		fr = new FileReader("d:/D_Other/testChar.txt");
		//다른 Stream 클래스도 많은데 왜 FileReader를 썼을까...
		//-> 파일을 읽기 위해, + 텍스트 파일 내용이 한글 같은 문자 기반일 경우를 위해
		
		//캐릭터 단위를 사용하지 않는 다른 Stream 클래스를 사용하면,
		//파일을 제대로 읽어오지 못한다. 깨진 한글 출력
		
		//인코딩
		//당연히 캐릭터 단위 변환 + 적절한 인코딩 타입!
		//아무리 캐릭터 단위로 처리를 했어도, 적절한 인코딩이 아닐 경우 문자열 복원이 안될 수 있다.
		
		int c;
		
		while((c=fr.read()) != -1) {
			System.out.print((char) c);
		}
		
		System.out.println("작업 끝");
		
		fr.close();
	}
}
