package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUdpServer {
	
	private DatagramSocket socket;

	public static void main(String[] args) throws IOException {
		new MyUdpServer().start();
	}
	
	//프로그램 시작
	public void start() throws IOException {
		
		//포트 8888번을 사용하는 소켓을 생성한다.
		socket = new DatagramSocket(8888);
		
		//패킷 송수신을 위한 객체 변수 선언
		DatagramPacket inPacket, outPacket;
		
		byte[] inMsg = new byte[1]; // 패킷 수신을 위한 바이트 배열
		byte[] outMsg;				// 패킷 송신을 위한 바이트 배열
		
		while(true) {
			//데이터를 수신하기 위한 패킷을 생성한다.
			inPacket = new DatagramPacket(inMsg, inMsg.length); //길이가 1인 byte배열을 보내주는 이유
			
			System.out.println("패킷 수신 대기중...");
			
			socket.receive(inPacket); //inPacket에 데이터를 받을 때까지 block
			
			System.out.println("패킷 수신 완료");
			
			//client정보: IP주소, port번호
			InetAddress address = inPacket.getAddress();
			int port = inPacket.getPort();
			
			//서버의 현재 시간을 시분초 형태로 반환한다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			outMsg = time.getBytes(); // 시간 문자열을 byte배열로 변환 - 패킷에 담기 위해
			
			outPacket = new DatagramPacket(outMsg, outMsg.length, address, port);
			
			socket.send(outPacket); // 전송 시작
			
			//보낼 때 : DatagramSocket 이용			
			//받을 때 : DatagramSocket 이용
			//받은 데이터 : DatagramPacket 이용
		}
	}
		
}
