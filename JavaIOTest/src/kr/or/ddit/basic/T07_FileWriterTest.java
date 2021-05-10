package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T07_FileWriterTest {
	public static void main(String[] args) {
		/*
		 * 사용자가 입력한 내용을 그대로 파일로 저장하기
		 * 
		 * 콘솔(표준 입출력장치)과 연결된 입력용 문자 스트림 생성
		 * InputStreamReader => 바이트 기반 스트림을 문자 기반 스트림으로 변환해 주는 보조 스트림이다. 
		 * 
		 * InputStream까지만 보면 바이트 기반, Reader를 보니 문자 기반 => 변환 보조 스트림
		 * 
		 * 기본 스트림을 보조해주는 스트림 : 보조 스트림  => 기본 스트림이 없으면 안된다, 의미없다.
		 * 
		 */
		InputStreamReader isr = new InputStreamReader(System.in);
		//System.in이 InputStream의 역할(기본 스트림)
		//InputStream은 Byte기반, 보조 스트림을 이용해 문자 기반으로 바꾸겠다
		
		//왜?
		//문자 기반으로 쓰는 이유는 단 하나, 문자 처리를 위해.
		//byte기반으로 처리할 수 없는 문자들은, 문자 기반으로 처리해줘야 한다.
		
		//1바이트씩 실제 읽는 작업은 System.in이 하고, 보조 스트림이 2바이트씩 모아 문자로 변환해준다.
		//기능을 보강하는 구조로 동작을 하고 있다.
		
		FileWriter fw = null;
		
		try {
			//파일 출력용 문자 스트림 객체 생성
			fw = new FileWriter("d:/D_Other/testChar.txt");
			//문자 기반 출력용
			//어디에 write? 이름에 있죠, File, File에 write할거니까 FileWriter
			//
			
			int c;
			
			System.out.println("아무거나 입력하세요."); 
			
			//콘솔에서 입력할 때 입력의 끝 표시는 Ctrl + z키를 누른다
			while((c=isr.read()) != -1) {
			//isr.read()에서 보조 스트림이 보조기능을 수행 
				fw.write(c);  //콘솔에서 입력받은 값을 파일에 출력하기
			}
			
			System.out.println("작업 끝...");
			
			isr.close();
			fw.close();
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
