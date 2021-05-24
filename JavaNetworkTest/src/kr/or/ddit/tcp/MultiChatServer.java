package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MultiChatServer {
	//서버측에서 여러 명의 사용자를 관리할 컬렉션이 필요
	//서버단에서 관리를 해줘야 여러 명이 채팅 가능
	
	//사용자 1명 1명이 의미있으려면, socket이 존재해야 한다
	//기본적으로 socket은 1쌍이 만들어진다
	//21명의 학생이 통신을 하려면, 서버 입장에서는 21개의 소켓을 가지고 있어야 한다
	//소켓을 꺼내기 위해서는, key값을 역할을 하는 상대방의 대화명, 대화명을 꺼내서 대화를 할 수 있다
	//둘만의 소켓이 만들어지면 그걸 Map에 집어넣고, 서버에서는 또 accept()하고 기다리고,
	//서버에서는 주구장창 accept()만 하고 기다린다. 만들어진 socket은 Map에 저장하고
	//서버에서 보낼 메세지가 있다면, Map에서 뒤져서 Socket을 통해 메시지를 전송한다.
	
	//ex.지수씨가 안녕하세요 하고 서버로 전송
	//서버는 받은 메세지를 for문 돌며, Map의 모든 소켓에 해당 메세지를 전송한다
	//그러면 사용자 입장에서는 누가 어떤 메세지를 보냈는지 알게 되고, 멀티챗처럼 느껴지게 된다.
	
		
	//대화명, 클라이언트 Socket을 저장하기 위한 Map 변수 선언
	Map<String, Socket> clients;
	
	public MultiChatServer() {
		//HashMap은 ArrayList처럼 동기화처리가 안되어있다.
		//동기화처리를 위해 Collections.sync어쩌구에서 Map선택
		
		//동기화 처리가 가능하도록 Map객체 생성
		clients = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	public static void main(String[] args) {
		new MultiChatServer().serverStart();
	}
	
	//시작 메서드
	public void serverStart() {
	
		Socket socket = null;
		
		//try resource with라는 문법
		//알아서 리소스를 반환해준다, finally없어도 close까지 다 시켜주는 문법
		//jdk1.7인가 부터 지원한다. 더 궁금하면 인터넷에서 찾아보기 ...
		try(ServerSocket serverSocket = new ServerSocket(7777)) {
			System.out.println("서버가 시작되었습니다.");
			
			//메인스레드는 계속 이 일만 한다 : 대기 -> 접속 발생 -> Map에 소켓 넣기 -> 다시 대기
			//이전 예제와 눈에 띄는 차이점은, accept()를 계속 하고 있다는 점
			while(true) {
				//클라이언트의 접속을 대기한다.
				socket = serverSocket.accept();
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속하였습니다.");
				
				//메시지 전송 처리를 하는 스레드 생성 및 실행
				ServerReceiver receiver = new ServerReceiver(socket);
				receiver.start();
				//accpet()하는만큼 만들어진다, 10명이 접속하면 스레드 10개가 만들어진다.
				
			}
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	//서버에서 클라이언트로 메세지를 전송할 Thread를 Inner클래스로 정의
	//Inner클래스에서는 부모 클래스의 멤버들을 직접 사용할 수 있다. - Inner클래스의 장점
	//ex. sendMessage같은거 바로 호출, 또? 인터페이스 할 때 잠깐 얘기했던 ...
	//Map이라는 인터페이스에 <K, V> -> 엔트리
	//Map안에 내부 인터페이스로 정의했었던 엔트리
	//다른 용도로 쓸 일이 없으니까, 외부에 이 클래스 자체를 노출 시킬 필요가 없다. //Inner클래스의 장점
	
	class ServerReceiver extends Thread {
		private Socket socket;
		private DataInputStream dis;
		private String name;
		
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				//수신용
				//readUTF()사용을 위한 보조스트림 
				dis = new DataInputStream(socket.getInputStream());
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		
		//접속자 수만큼 스레드가 만들어질 거다.
		@Override
		public void run() {
			try {
				//서버에서는 클라이언트가 보내는 최초의 메시지 즉, 대화명을 수신해야 한다.
				name = dis.readUTF();
				//언제 이름을 보내줬지? - 나중에 Client측(아직 코딩X)에서 보내줄 거라는 전제 하에 코딩
				
				//대화명을 받아서 다른 모든 클라이언트에게 대화방 참여 메세지를 보낸다.
				sendMessage("#" + name + "님이 입장했습니다.");
				//이 사람은 처음 들어온 것
				//관리하고 있는 Map에 없다 -> clients HashMap에 아래에서 저장
				
				//대화명과 소켓 정보를 Map에 저장한다.
				clients.put(name, socket); //put()으로 데이터 삽입
				
				System.out.println("현재 서버 접속자 수는 " + clients.size() + "명 입니다.");
				
				//이 후의 메시지 처리는 반복문으로 처리한다.
				//한 클라이언트가 보낸 메시지를 다른 모든 클라이언트에게 보내준다.
				while(dis != null) {
					sendMessage(dis.readUTF(), name);
					//모든 사용자에게 메세지를 뿌리는 메서드, 아직 구현 안 함
					//name이 없는 파라미터1개 메서드 sendMessage는 이름 없이 단순히 메세지를 뿌리는 역할만 한다.
				}
				
			}catch(IOException ex) {
//				ex.printStackTrace();
			}finally {
				//이 finally영역이 실행된다는 것은 클라이언트의 접속이 종료되었다는 의미이다.
				// + 예외가 발생한 것, finally를 탔다는 것은 비정상적으로 종료되었다는 것, 여기서는.
				
				sendMessage(name + "님이 나가셨습니다.");
				
				//Map에서 해당 대화명을 찾아 삭제한다.
				clients.remove(name);
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속을 종료했습니다.");
				System.out.println("현재 접속자 수는 " + clients.size() + "명 입니다.");
				
			}
			
		}

		
		
	}
	
	/**
	 * 대화방 즉, Map에 저장된 전체 유저에게 안내메시지를 전송하는 메서드
	 * @param msg
	 */
	private void sendMessage(String msg) {
		//Map에 저장된 유저의 대화명 리스트 추출(key값 구하기)
		Iterator<String> it = clients.keySet().iterator();
		while(it.hasNext()) {
			try {
				String name = it.next(); //대화명(key) 구하기
				
				//대화명에 해당하는 Socket의 OutputStream 객체 구하기
															//= Socket객체.getOutputStream()
				DataOutputStream dos = new DataOutputStream(clients.get(name).getOutputStream());
				dos.writeUTF(msg); //메시지 보내기, 어디로? 방금 꺼낸 Socket에 해당하는 곳으로 보낸다
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * 대화방 즉, Map에 저장된 전체 유저에게 대화 메시지를 전송하는 메서드
	 * @param msg	메시지
	 * @param from	보낸 사람
	 */
	//메서드를 분리한 이유는, 누가 보냈는지 알려주기 위해
	private void sendMessage(String msg, String from) {
		sendMessage("[" + from + "] " + msg);
	}
	
}
