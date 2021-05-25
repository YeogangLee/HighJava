package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	
	private DatagramSocket socket;

	public static void main(String[] args) throws IOException {
		new UdpServer().start();
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
			inPacket = new DatagramPacket(inMsg, inMsg.length);
			
			//inMsg와 길이를 같이 생성자로 보내주는데, inMsg는 크기가 1이다.
			//왜 이런 일을 할까?
			//클라이언트가 나중에 정보를 줄건데, 그 정보를 알기 위해서는
			//상대방이 나한테 던진 패킷 객체를 이용해 ip정보를 알 수 있다
			//상대방의 패킷을 받아낼 때 사용하는 용도로, 의미는 없지만 1바이트 짜리를 만들었다.
			
			System.out.println("패킷 수신 대기중...");
			
			//패킷을 통해 데이터를 수신(Receive)한다.
			socket.receive(inPacket);
			//여기서 block
			//언제까지?
			/*
			 * 상대방이 나한테 패킷을 던져줄때까지
			 * 나한테 데이터가 전달되는 게 있으면
			 * 상대방이 보낸 데이터를 inPacket에다가 담고 그러면 block이 풀린다. 
			 */			
			
			System.out.println("패킷 수신 완료");
			
			//수신한 패킷으로부터 client의 IP주소와 port번호를 얻는다.
			InetAddress address = inPacket.getAddress();
			int port = inPacket.getPort();
			
			//누구의 ip주소와 port번호? >> 클라이언트
			//inPacket객체 안에 정보가 담길건데, 여기서 정보를 추출하면,
			//상대방의 ip주소와 port번호를 알 수 있다
			//왜? 나중에 데이터를 보내주려고.
			
			//서버의 현재 시간을 시분초 형태로 반환한다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			outMsg = time.getBytes(); // 시간 문자열을 byte배열로 변환
			//문자열을 byte배열로 변환해서 넣어줬다. 왜? packet이 원하는 형태가 byte배열이니까
			
			//패킷을 생성해서 client에게 전송(send)한다.
			outPacket = new DatagramPacket(outMsg, outMsg.length, address, port);
			
			socket.send(outPacket); // 전송 시작
			//보낼 때 : DatagramSocket 이용			
			//받을 때 : DatagramSocket 이용
			
			//받은 데이터 : DatagramPacket 이용
		}
	}
		
}
