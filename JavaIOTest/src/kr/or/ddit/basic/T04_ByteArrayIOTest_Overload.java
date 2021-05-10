package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04_ByteArrayIOTest_Overload {
	
	/**
	 * 클래스명이 스트림으로 끝난다 -> 대부분 byte기반
	 * byte기반을 핸들링 할 일이 많기 때문에 사용법을 알고 있어야 한다.
	 */
	
	public static void main(String[] args) {
		byte[] inSrc = { 0,1,2,3,4,5,6,7,8,9 };
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];	//자료 읽을 때 사용할 배열
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			//available() => 읽어올 수 있는 byte수를 반환
			while(bais.available() > 0) { //0보다 크다는 건 읽을 게 아직 남았다는 뜻
				
				//배열의 크기인 4만큼 읽어 들어오므로,
				//남아있는 버퍼값인 쓰레기값도 같이 들어오는 방법
//				//temp배열 크기만큼 읽어와 temp배열에 저장한다.
				bais.read(temp); 
//				
//				//temp 내용을 출력한다
				baos.write(temp);
				
				//실제 읽어온 byte수를 반환한다
//				int len = bais.read(temp);
//				
//				//temp배열의 내용 중에서 0번째부터 len개수만큼 출력한다
//				baos.write(temp, 0, len); //size만큼만 write하도록, 파라미터 3개 메서드 존재 이유
//				
				System.out.println("temp : " + Arrays.toString(temp));
			}//while
			
			System.out.println();
			outSrc = baos.toByteArray();
			System.out.println("inSrc => " + Arrays.toString(inSrc));
			System.out.println("outSrc => " + Arrays.toString(outSrc));
			
			bais.close();
			baos.close();
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
