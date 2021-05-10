package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 문자 기반의 Buffered 스트림 예제
 * @author PC-14
 *
 */
public class T12_BufferedIOTest {
	public static void main(String[] args) throws IOException {
		
		//이클립스에서 만든 자바 프로그램이 실행되는 기본 위치는
		//해당 '프로젝트폴더'가 기본 위치가 된다.
		FileReader fr = new FileReader("src/kr/or/ddit/basic/T11_BufferedIOTest.java"); //src앞에 ./이 생략된 형태
//		FileReader fr = new FileReader("./src/kr/or/ddit/basic/T11_BufferedIOTest.java");
		//Reader를 통해 1캐릭터씩 읽겠다.
		
		/*
		int c;
		while((c=fr.read()) != -1) {
			System.out.print((char) c);
		}
		
		fr.close();
		*/
		
		//한 줄씩 읽을 수 있도록 해주는 readLine()을 이용하기 위해서
		//BufferedReader 사용
		BufferedReader br = new BufferedReader(fr);
		String temp = "";
		
		//readLine(): 읽을 게 없으면, read()처럼 -1이 아닌, null을 반환한다. 그래서 null이 아닐 때까지 반복문 수행
		for(int i=1; (temp = br.readLine()) != null; i++) {
			System.out.printf("%4d : %s\n", i, temp);
		}
		//출력 결과를 보면, 아까 java코드에 행 번호가 같이 출력되어 있다.
		//readLine()은 한 줄씩 읽는 함수이기 때문에, 줄 번호 부여가 가능 
		
		br.close();
	}
}
