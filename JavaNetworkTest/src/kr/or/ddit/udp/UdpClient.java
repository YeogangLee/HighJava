package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {	
	
	public static void main(String[] args) throws IOException {
		new UdpClient().start();
	}
	
	//시작
	public void start() throws IOException {
		DatagramSocket socket = new DatagramSocket(); // 소켓 생성
		InetAddress serverAddress = InetAddress.getByName("192.168.42.149");
		
		//데이터가 저장될 공간으로 byte배열을 생성한다. (패킷 수신용)
		byte[] msg = new byte[100];
		
		DatagramPacket outPacket = new DatagramPacket(msg, 1, serverAddress, 8888);
		//msg: 100바이트짜리 byte배열, 크기는 100이지만 보낼 데이터는 1이다.
		//안에 있는 데이터가 중요한 게 아니고, 패킷을 보내는 게 중요한 거다
		//패킷 정보를 이용해서, ip주소, port번호를 꺼낼 거다, 그래서 패킷을 보내는 게 중요
		
		DatagramPacket inPacket = new DatagramPacket(msg, msg.length);
		
		socket.send(outPacket);		// 전송
		socket.receive(inPacket);	// 수신
		//여기서 클라이언트는 block이 된다
		//언제까지? 서버가 클라이언트로 데이터를 보내줄 때까지
		//서버시간을 보내는 시점에, 서버 시간을 inPacket에 담고 block이 풀린다
		
		//byte배열로 리턴해주고, 그것을 String으로 보여주기 위해, 아래처럼 파라미터로 byte배열을 넣어줬다.
		//String의 생성자는 byte배열을 넣어주면 알아서 문자열로 변환시켜준다. 그런 기능이 있어요.
		
		System.out.println("현재 서버 시각 => " + new String(inPacket.getData()));
		
		socket.close(); //소켓 종료
		
	}
}
