package kr.or.ddit.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpFileReceiver {
	
	public static void main(String[] args) throws IOException {
		int port = 8888;
		
		long fileSize = 0;
		long totalReadBytes = 0;
		
		byte[] buffer = new byte[1000];
		int readBytes = 0;
		
		System.out.println("파일 수신 대기중...");
		
		DatagramSocket socket = new DatagramSocket(port);
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/aaa.png");
		
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		//송신용? 수신용?
		/*
		 * 상대방이 나한테 데이터를 보내줘도,
		 * 내가 데이터를 보내주려고 해도,
		 * DatagramPacket을 사용
		 * 
		 * 구분하는 방법: 파라미터 개수
		 * 
		 * 송신용은 파라미터 3,4개가 있다
		 * ip주소랑 port번호까지 담아서 보내야 한다. 
		 * 그리고 일단 여기는 수신용이죠, receiver에서 쓰고 있으니까
		 */
		
		socket.receive(dp); //1번째 receive - 문자열 start 수신
		//block이 됐다가, dp받으면 block 풀린다
		String str = new String(dp.getData()).trim();
		
		//start를 보낼 거란 걸 아니까, 아래처럼 비교
		if(str.equals("start")) { //sender에서 전송을 시작한 경우...
			dp = new DatagramPacket(buffer, buffer.length);
			socket.receive(dp);
			//2번째 receive - 파일 사이즈
			str = new String(dp.getData()).trim();
			fileSize = Long.parseLong(str);
			
			double startTime = System.currentTimeMillis();
			
			while(true) {
				//3번째 receive - 1000바이트씩, 1000바이트씩 읽어오기
				socket.receive(dp);
//				str = new String(dp.getData()).trim(); //필요없는 데이터, 삭제, trim()해주려고 적은 것 같다.
				readBytes = dp.getLength();
				
				fos.write(dp.getData(), 0, readBytes); //dp.getData()가 str자리가 아니었을까
				totalReadBytes += readBytes;
				
				System.out.println("진행 상태 : " + totalReadBytes + "/" + fileSize 
				+ "Bytes (" + (totalReadBytes * 100 / fileSize) + " %)");
				
				if(totalReadBytes >= fileSize) { //파일을 읽은 누적 사이즈가 파일 사이즈보다 크다면 파일을 다 읽은 것
					break;
				}
			}
			
			double endTime = System.currentTimeMillis();
			double diffTime = (endTime - startTime) / 1000;
			double transferSpeed = (fileSize / 1000) / diffTime; //단위가 Kb라서, 1000으로 나눠줬다
			
			System.out.println("걸린 시간 : " + diffTime + "(초)");
			System.out.println("평균 전송 속도 : " + transferSpeed + "KB/s");
			
			System.out.println("수신 완료...");
			
			fos.close();
			socket.close();
			
		}else {
			System.out.println("비정상 데이터 발견!!!");
			fos.close();
			socket.close();
		}
	}
	
}
