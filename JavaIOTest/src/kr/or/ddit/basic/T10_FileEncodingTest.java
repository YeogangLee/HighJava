package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class T10_FileEncodingTest {
/*
 * OutputStreamWriter
 * : OutputStream(바이트 기반의 출력용 객체)을
 *  Writer(문자 기반의 출력용 객체)로 변화하는 객체
 *  => 이 객체도 출력할 때 '인코딩 방식'을 지정해서 출력할 수 있다.
 */
	
	//방금 예제와 거꾸로 되는 예제, 일종의.
	//파일로 뭐를 저장하고 싶으면 뭐가 필요? FileOutputStream이 일단 생각나야해요.
	//
	/*
	 * 복사를 하기 위해 IO작업을 수행할 때
	 * 파일을 읽기 위해 필요한 Stream이? -> FileInputStream
	 * 근데 문자열로 저장되어 있다.
	 * 
	 * byte단위를 byte단위로 읽어올 때는 문제가 없죠
	 * 문제가 되는 건, 0101 연속적인 데이터를 읽어 문자열로 복원시켜 의미있는 메세지를 보고자 할 때
	 * 파일 복사의 개념에서 byte는 문제가 되지 않는다.
	 * 1바이트, 2바이트, 점점 ... 이미지든 텍파, 동영상 뭐든 그냥 복사하면 끝이에요.
	 * 
	 * 어떤 코드표로 바뀌어 저장되어야할 지가 문제가 되는 거죠
	 * OutputStreamWriter
	 * OutputStream은 byte기반, Writer는 문자 기반
	 * 출력용, 바이트 기반 스트림을 쓰려고 하지만, 저장할 시점에 저장할 데이터는 문자열인 것
	 * 
	 * 바이트, 바이트를 의미있는 단위로 저장을 해야 나중에 꺼내도 문제가 없이 잘 복원이 되겠죠.
	 */
	
	public static void main(String[] args) throws IOException {
		//키보드로 입력한 내용을 파일로 저장하는데,
		//out_utf8.txt 파일은 'utf-8'인코딩 방식으로
		//out_ansi.txt 파일은 'ms949'인코딩 방식으로 저장한다.
		
		InputStreamReader isr = new InputStreamReader(System.in);
		
		//파일 출력용 - 보조 스트림의 기반이 되는, 기반 스트림 FileOutputStream
		FileOutputStream fos1 = new FileOutputStream("d:/D_Other/out_utf8.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/out_ansi.txt");
		
		//OutputStreamWriter은 바이트 출력 스트림에 연결되어
		//문자 출력 스트림인 Writer로 변환시키는 보조 스트림.
		OutputStreamWriter osw1 = new OutputStreamWriter(fos1, "utf-8");
		OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "ms949");
		
		int c;
		System.out.println("아무거나 입력하세요.");
		
		while((c=isr.read()) != -1) {
			osw1.write(c);
			osw2.write(c);
		}
		
		System.out.println("작업 완료...");
		
		isr.close();
		osw1.close();
		osw2.close();
		
		/*
		 * 보조 스트림: 일반 스트림을 보조
		 * 일반 스트림이 없는 보조 스트림은 없다.
		 * 보조 스트림으로 살펴본게, InputStreamReader, OutputStreamWriter
		 */
	}
}
