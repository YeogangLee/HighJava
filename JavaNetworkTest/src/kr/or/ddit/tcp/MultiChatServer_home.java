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

public class MultiChatServer_home {
	
	//대화명, 클라이언트 Socket을 저장하기 위한 Map 변수 선언
	Map<String, Socket> clients;
	
	public MultiChatServer_home() {	
		//동기화 처리가 가능하도록 Map객체 생성
		clients = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	public static void main(String[] args) {
		new MultiChatServer_home().serverStart();
	}
	
	//시작 메서드
	public void serverStart() {
	
		Socket socket = null;
		
		try(ServerSocket serverSocket = new ServerSocket(7777)) {
			System.out.println("서버가 시작되었습니다.");
		
			while(true) {
				//클라이언트의 접속을 대기한다.
				socket = serverSocket.accept();
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속하였습니다.");
				
				//메시지 전송 처리를 하는 스레드 생성 및 실행
				ServerReceiver receiver = new ServerReceiver(socket);
				receiver.start();
				
			}
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
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
		
		@Override
		public void run() {
			try {
				name = dis.readUTF();
				
				sendMessage("#" + name + "님이 입장했습니다.");
			
				clients.put(name, socket); //put()으로 HashMap에 데이터 추가
				
				System.out.println("현재 서버 접속자 수는 " + clients.size() + "명 입니다.");
				
				while(dis != null) {
					sendMessage(dis.readUTF(), name);
				}
				
			}catch(IOException ex) {
//				ex.printStackTrace();
			}finally {
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
				DataOutputStream dos = new DataOutputStream(clients.get(name).getOutputStream());
				dos.writeUTF(msg);
				
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
		if(msg.indexOf('/') == 0) {
			int blank = msg.indexOf(" ");
			
			String earName = msg.substring(1, blank);
			String earMsg = msg.substring(blank+1);
			
			//Map에 저장된 유저의 대화명 리스트 추출(key값 구하기)
			Iterator<String> it = clients.keySet().iterator();
			boolean flag = false;
			while(it.hasNext()) {
				try {
//					NoSuchElementException이란 쉽게 말해서 공간이 없다는 것이다.
//					즉 비어있는, 없는 공간의 값을 꺼내려고 하면 발생한다.
					//Exception in thread "Thread-0" java.util.NoSuchElementException
//					System.out.print(" " + it.next());
					if(it.next().equals(earName)) {
						DataOutputStream dos = new DataOutputStream(clients.get(earName).getOutputStream());
						dos.writeUTF("["+from+"]님이 보낸 귓속말: "+earMsg);
						flag = true;
					}
					//없는 대화명을 입력했을 때 출력 문구
					if(flag==false) {
						DataOutputStream dos = new DataOutputStream(clients.get(from).getOutputStream());
						dos.writeUTF("[" + earName + "]님은 현재 접속 상태가 아닙니다.");
					}
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}					
		}else {
			sendMessage("[" + from + "] " + msg);
		}
	}
	
}
