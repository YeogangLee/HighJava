package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 기본타입 입출력 보조 스트림 예제
 * @author PC-14
 *
 */
public class T13_DataIOStreamTest {
	public static void main(String[] args) throws IOException {
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/test.dat");

		//DataOutputStream은 출력용 데이터를 자료형에 맞게 출력해 준다.
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.writeUTF("홍길동");		// 문자열 데이터 출력, readUTF()도 있다
		dos.writeInt(17);			// 정수형으로 데이터 출력
		dos.writeFloat(3.14f);		// 실수형Float으로 출력
		dos.writeDouble(3.14);		// 실수형Double으로 출력
		dos.writeBoolean(true);		// 논리형으로 출력
		System.out.println("출력 완료...");
		
		dos.close();
		//===============================================
		//출력한 자료 읽어오기 - 바이트 단위로 ...
		
		FileInputStream fis = new FileInputStream("d:/D_Other/test.dat");
		DataInputStream dis = new DataInputStream(fis);
		System.out.println("문자열 자료 : " + dis.readUTF());	//여기 readUTF()
		System.out.println("정수형 자료 : " + dis.readInt());
		System.out.println("실수형 자료(Float) : " + dis.readFloat());
		System.out.println("실수형 자료(Double) : " + dis.readDouble());
		System.out.println("논리형 자료 : " + dis.readBoolean());
		
		//보조 스트림을 이용해 데이터를 저장했으면,
		//읽을 때도 같은 보조 스트림을 사용해 읽어온다.
		//프로젝트 할 때 채팅 프로그램에 사용될 수도 있으므로, 한 번 봐두세요, 문자열 자료.
		
		dis.close();
	
	}
}
