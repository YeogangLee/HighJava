package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 프린터 기능 제공 보조 스트림 예제
 * @author PC-14
 *
 */
public class T14_PrintStreamTest {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = 	new FileOutputStream("d:/D_Other/print.txt");
	
	/*
	 * PrintStream은 모든 자료형을 출력할 수 있는 기능을 제공하는 OutputStream의 서브 클래스이다.
	 * PrintStream은 IOException을 발생시키지 않는다.
	 * println 및  print 등 메서드 호출시마다 autoflush 기능 제공됨.
	 */
		
		//콘솔 출력 스트림으로 사용됨.
//		PrintStream out = new PrintStream(System.out);
		PrintStream out = new PrintStream(fos);
		//f3으로 out을 따라가 보면 PrintStream이 나온다.
		//out의 역할은 뭐든지 출력... 보조 스트림이 뭔지에 따라
		
		out.print("안녕하세요 PrintStream입니다.\n");
		out.println("안녕하세요 PrintStream입니다2.");
		out.println("안녕하세요 PrintStream입니다3.");
		out.print(out);  //객체 출력
		out.print(3.14);
		
		out.close();
		
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/print2.txt");
		
		//나중에 서블릿 프로그램하며 더 보게 될 것
		//보조 스트림 안에 보조 스트림을 넣어줘도 상관이 없다
		//보조 스트림이란 게, 기반 스트림을 감싸고 있는 구조이므로.
		//디자인패턴에서 데코레이터라고 불러요. 데코레이터 패턴.
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos2, "UTF-8"));
		
		/*
		 * PrintStream은 데이터를 문자로 출력하는 기능을 수행함
		 * 향상된 기능의 PrintWriter가 추가되었지만 계속 사용됨
		 * 
		 * PrintWriter가 PrintStream보다 다양한 언어의 문자를 처리하는 데 적합하다.
		 * 
		 * 둘의 차이점: PrintWriter는 융통성이 있고, 보조 스트림 안에 보조 스트림을 넣을 수도 있지만
		 * 			 PrintStream은 정해진 대로 사용해야 한다. + PrintStream은 IOException을 발생시키지 않는다.
		 * 
		 * 문자열 처리는 문자 기반 스트림으로 처리, 이름에 Reader, Writer을 포함
		 * 보조 기능으로 Print기능을 가지고 있다는 뜻으로 PrintWriter라는 이름으로 지은 것 같아요.
		 * 그런데 PrintStream을 계속 써왔으므로, 같이 쓰고 있다.
		 * 
		 * 공통점: 출력을 수행, 기반 스트림을 필요로 한다.
		 * 
		 * 나중에 서블릿 프로그래밍 할 때 PrintWriter 또 사용할 거에요.
		 * 내일이면 IO 끝나고 테스트, 저번에 말한 호텔 관리 프로그램 + 저장 기능
		 * 한 사람씩 나와서 선생님께 소스 설명 + 실행, 본인이 코드를 잘 이해하고 있는지를 확인 겸 테스트
		 * 내일은 오브젝트 저장 기능 배우고 나면, 여러분들이 할 수 있으니까...
		 * 1대1로 할거에요, 너무 긴장하지 말고 ... 내일 상황 봐서 목요일, 수요일 시험일 결정하고 ...
		 * 
		 */
		
		pw.print("안녕 PrintWriter입니다.\n");
		pw.println("안녕 PrintWriter입니다2.");
		pw.println("안녕 PrintWriter입니다3.");
		
		pw.close();
	}
	
}
