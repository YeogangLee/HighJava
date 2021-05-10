package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 입출력의 성능 향상을 위해서
 * 버퍼를 이용하는 보조 스트림
 * @author PC-14
 *
 */
public class T11_BufferedIOTest {
	public static void main(String[] args) {
		
		//입출력의 성능 향상을 위해서 버퍼를 이용하는 보조 스트림
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		//바이트 기반 특징: 처리를 한 바이트씩 읽어오며 처리 ...
		//100byte크기, 작업 수행 몇번? 100번
		
		//사실 IO기능은 운영체제에서 하는거지, 자바에서 하는 게 아니다.
		//운영체제가 가지고 있는 드라이버 등을 통해 자바에서 하도록 허락해주는 것
		//자바는 일반 CPU의 작업 속도에 비해 느리다.
		//작업 자체가 CPU가 일하는, 나노세컨드 단위에서 바라보면 자바는 느려요.
		//그래서 IO작업을 하게 되면 CPU는 놀게 돼요. CPU 관점에서는 시간이 많이 남는 거에요.
		
		//CPU입장에서 이런 느린 작업은, 되게 오랫동안 기다리고 비효율적이게 돼요.
		//그래서 IO를 여러번 수행하면 성능이 나빠져요.
		//그래서 IO는 한번에 모아서 수행하는 게 좋다.
		
		//근데 이렇게 하지 않고 버퍼를 이용하면 성능이 많이 향상이 돼요.
		//하지만 버퍼 크기가 너무 커도, ram 소모가 크다.
		
		
		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			
			//버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가
			//8192byte(8Kb)로 설정된다.
			
			//버퍼 크기가 5인 스트림 생성
			bos = new BufferedOutputStream(fos, 5); //보조 스트림이라서, 기반 스트림인 fos가 반드시 필요
			for(int i='1'; i<='9'; i++) {
				System.out.println("for문 " + i);
				bos.write(i);
				//실질적으로 write하는 시점은? -> buffer가 다 찼을 때
				//for문 돌면서 5까지는~ 그냥 쌓다가, 5가 되면 write하고 buffer는 비워진다.
				
				//현재 하드디스크에는 5까지만 저장되어 있다.
				//1~5는 저장되고, 6~9는 5가 안 돼서 저장이 안됐다 -> 파일이 깨진 상태 
				
			}//숫자가 아닌 문자 1에서 9, 여기 해당하는 코드를 write하는 것 
			
			//flush: 물 내리다, 방출하다
			bos.flush(); //작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력시킨다.
						 //(close시 자동으로 호출됨.) 예전에는 일일이 신경썼어야 했는데, 이제는 자동 호출
			//이런 메서드가 존재하는 이유는, 버퍼크기가 다 채워지지 않더라도 강제적으로 비워주는 기능이 필요하기 때문.
			System.out.println("작업 끝...");
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally {			
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
