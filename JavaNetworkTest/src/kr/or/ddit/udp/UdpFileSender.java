package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpFileSender {
	
	public static void main(String[] args) {
		String serverIp = "127.0.0.1";
		int port = 8888;
		
		File file = new File("d:/D_Other/Tulips.jpg");
		
		DatagramSocket socket = null;
		
		if(!file.exists()) {
			System.out.println("파일이 존재하지 않습니다.");
			System.exit(0);
		}
		
		long fileSize = file.length();
		//byte타입 file은 length()로 읽을 수 있다.
		
		long totalReadBytes = 0;
		double startTime = 0;
		
		try {
			socket = new DatagramSocket();
			InetAddress serverAddr = InetAddress.getByName(serverIp); //얘는 만들때마다 new를 안 찍네
			
			startTime = System.currentTimeMillis();
			String str = "start"; //전송시작을 알려주기 위한 문자열
			//클라이언트가 제일 먼저 받을 메세지
			//그러면 클라이언트는 이제 통신 시작이구나 하고 알 수 있다
			
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);
			socket.send(dp);
			//전송 1 - start 문자열 전송
			
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1000];
			
			str = String.valueOf(fileSize); // 총 파일 사이즈 정보
			//아까 읽었던 파일객체.length()
			
			//str값이 변경되었으므로, new DatagramPacket을 통해 바뀐 str값을 dp에 대입
			//str값이 변경되지 않았으면, socket.send(dp)만 수행하면 됐었을 거다.
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);
			socket.send(dp);
			//전송 2 - 이번에는 파일 사이즈 정보 전송
			
			while(true) {
				Thread.sleep(10); // 패킷 전송 간의 시간 간격 주기
				int readBytes = fis.read(buffer, 0, buffer.length);
				
				//버퍼를 read()할 수 있는데, 버퍼를 읽을 때는 파라미터3개를 사용해야 한다
				//쓰레기값을 받지 않기 위해, 파라미터3개 읽은 사이즈(length)만큼만 read를 처리하도록
				
				//파라미터 3개 read() : T04_ByteArrayIOTest_3_Overload
				//read(byte배열, 시작idx, (앞의 byte배열에서)읽을 길이) 
				
				if(readBytes == -1) {
					break;
				}
				
				//readBytes가 -1이 아니라면 아래 수행 
				
				// 읽어온 파일 내용 패킷에 담기
				dp = new DatagramPacket(buffer, readBytes, serverAddr, port);
				socket.send(dp); //전송 3 - 실제 바이트 배열
				
				//상대방한테 읽어온 버퍼를 계속 전송
				//전송 3 - 실제 바이트 배열
				//3번째부터는 실제 바이트 배열 데이터를 패킷에 담아 던지고 있다.
				//1000바이트 이상은 못 읽고, 1000바이트씩 끊어서 전송하려는 구조
				//읽은 바이트를 total...변수와 +=로 누적
				totalReadBytes += readBytes;
				System.out.println("진행 상태 : " + totalReadBytes + "/" + fileSize 
						+ "Bytes (" + (totalReadBytes * 100 / fileSize) + " %)");
			}
			
			double endTime = System.currentTimeMillis();
			double diffTime = (endTime - startTime) / 1000;
			double transferSpeed = (fileSize / 1000) / diffTime; //단위가 Kb라서, 1000으로 나눠줬다
			
			System.out.println("걸린 시간 : " + diffTime + "(초)");
			System.out.println("평균 전송 속도 : " + transferSpeed + "KB/s");
			
			System.out.println("전송 완료...");
			
			fis.close();
			socket.close();			
			
			//sender가 하는 역할
			/*
			 * 1. start를 보낸다
			 * 2. 파일의 총 사이즈, 크기 - ex.이 파일은 10Mbyte 짜리다
			 * 3. 바이트 배열로 읽고 상대방에게 write, send
			 * 	  1000바이트씩, 1000바이트씩 읽어낸다
			 * 
			 */
			
		}catch(Exception ex) { //이번에는 IOException이 아니다 ??
			ex.printStackTrace();
		}		
	}
	
}
