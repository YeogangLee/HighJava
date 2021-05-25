package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MyUdpClient {	
	
	public static void main(String[] args) throws IOException {
		new MyUdpClient().start();
	}
	
	//시작
	public void start() throws IOException {
		DatagramSocket socket = new DatagramSocket(); // 소켓 생성
		InetAddress serverAddress = InetAddress.getByName("192.168.42.149");
		
		//데이터가 저장될 공간으로 byte배열을 생성한다. (패킷 수신용)
		byte[] msg = new byte[100];
		
		DatagramPacket outPacket = new DatagramPacket(msg, 1, serverAddress, 8888);		
		DatagramPacket inPacket = new DatagramPacket(msg, msg.length);
		
		socket.send(outPacket);		// 전송
		socket.receive(inPacket);	// 수신, inPacket에 데이터가 들어올 때까지 block
		
		System.out.println("현재 서버 시각 => " + new String(inPacket.getData()));
		
		socket.close(); //소켓 종료
		
		
		//이해가 안 되는 부분
		/*
		 * 송신과 수신에서 같은 메서드를 쓰는데, ip주소를
		 * 자신의 ip주소, 도착지의 ip주소
		 * 이렇게 구분할 수가 있나?
		 * 
		 * 당연히 구분할 수 없다 ~~~~~~
		 * 그리고 DatagramPacket의 ip파라미터는 목적지를 의미한다.
		 * 
		 * 클라이언트측에서 outPacket에 담은 ip주소와
		 * 서버측에서 outPacket에 담은 ip주소는 다르다.
		 * 값이 다르면 당연히 구분할 수 있겠죠?
		 * 
		 * 클라이언트측
		 * DatagramPacket outPacket = new DatagramPacket(msg, 1, serverAddress, 8888);
		 * 
		 * 서버측
		 * //client정보: IP주소, port번호
		 * InetAddress address = inPacket.getAddress();
		 * int port = inPacket.getPort();
		 * 
		 * => 여기서 얻어온 ip와 port는 클라이언트측에서 파라미터로 보낸 severAddress와 8888이 아니다.
		 *    (통신을 해야 하니 port번호는 동일한 게 맞다. 실제로 서버측, 클라이언트측, 모두 코드 내에 8888이라고 기입.
		 *     getPort()가 파라미터를 읽은 건지, 통신을 통해 DatagramPacket에 담긴 내부 정보를 읽은 건지는 모르겠지만.)
		 *     
		 * !! 클라이언트의 ip주소와 port번호를 가져온 것, 파라미터가 아니라.
		 *    꼭 파라미터로 넘겨주지 않더라도, 기본적으로 제공하는 DatagramPacket에 담겨진 정보로 client측의 정보를 알 수 있다. 
		 * 
		 * address에는 정상적으로 클라이언트의 ip가 담겨 보내진다.
		 * outPacket = new DatagramPacket(outMsg, outMsg.length, address, port);
		 * 
		 */
		
	}
}
