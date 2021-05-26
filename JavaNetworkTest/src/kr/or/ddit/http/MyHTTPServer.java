package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 간단한 웹서버 예제
 * @author macween
 *
 */
public class MyHTTPServer {

	private final int port = 80;
	private final String encoding = "UTF-8";

	/**
	 * 응답헤더 생성하기
	 * @param contentLength 응답내용크기
	 * @param mimeType 마임타입
	 * @return 바이트배열
	 */
	private byte[] makeResponseHeader(int contentLength, String mimeType) {
		String header = "HTTP/1.1 200 OK\r\n" //r: carrige return, n: line return, 잘 모를때는 r,n 둘 다 사용
			+ "Server: MyHTTPServer 1.0\r\n"
			+ "Content-length: " + contentLength + "\r\n"
			+ "Content-type: " + mimeType + "; charset=" + this.encoding + "\r\n\r\n"; //empty line을 위한 r n r n
		return header.getBytes();
	}

	/**
	 * 응답내용 생성하기
	 * @param filePath 응답으로 사용할 파일경로
	 * @return 바이트배열 데이터
	 */
	private byte[] makeResponseBody(String filePath) {

		FileInputStream fis = null;
		byte[] data = null;
		try {
			File file = new File(filePath);
			data = new byte[(int) file.length()];

			fis = new FileInputStream(file);
			fis.read(data);

		}catch(IOException ex) {
			System.err.println("입출력 오류!!!");
			ex.printStackTrace();
		}finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return data;
	}

	/**
	 * Http서버 시작
	 */
	public void start() {
		//ExecutorService pool = Executors.newFixedThreadPool(100);
		System.out.println("HTTP 서버가 시작되었습니다.");
		try (ServerSocket server = new ServerSocket(this.port)) {

			while(true) {
				try {
					Socket socket = server.accept();
					//pool.submit(new HttpHandler(socket));
					HttpHandler handler = new HttpHandler(socket);
					new Thread(handler).start(); // 요청처리 시작

				} catch(IOException ex) {
					System.err.println("커넥션 오류!!!");
					ex.printStackTrace();
				}catch(RuntimeException ex) {
					System.err.println("알수없는 오류!!!");
					ex.printStackTrace();
				}
			}
		}catch(IOException ex) {
			System.err.println("서버 시작 오류!!!");
			ex.printStackTrace();
		}
	}

	/**
	 * HTTP 요청 처리를 위한 Runnable 객체
	 * @author macween
	 *
	 */
	private class HttpHandler implements Runnable {
		private final Socket socket;

		public HttpHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			OutputStream out = null;
			BufferedReader br = null;
			try {
				
				out = new BufferedOutputStream(socket.getOutputStream());
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//문자 기반 스트림
				
				// 요청헤더 정보 파싱하기
				StringBuilder request = new StringBuilder();
				while (true) {
					String str = br.readLine();
					
					//readLine()은 읽을 게 없으면 null을 반환한다
					//주의! - ""는 null이 아니다. Oracle은 ''을 null로 인식
					//readLine() 메서드는 한 줄씩 읽어들인다 - 처음 사용: T12_BufferedIOTest
					if (str.equals("")) break; // empty line 체크

					//한 줄씩 읽을 뿐, 줄바꿈을 포함하지는 않는다,
					//그래서 readLine()메서드 이용 후 문자열을 출력할 때는 읽은 데이터 + \n을 해준다.
					request.append(str + "\n");
					//스트링 빌더에 1줄씩 추가
				}
				//브라우저: http프로토콜에 입각해서 만든 프로그램
				//http 프로토콜 메세지 형식으로 만들어져서,브라우저에게 보낸다

				System.out.println("요청헤더:\n" + request.toString());
				System.out.println("-------------------------------------");

				String reqPath = "";

				// 요청 페이지 정보 가져오기
				//요청 페이지 정보는 request line에 들어간다
				//여기에 포함되는 정보 : 메서드 이름, GET이냐, POST냐, 내가 요청할 때 사용한 프로토콜의 버전
				StringTokenizer st = new StringTokenizer(request.toString());
				while(st.hasMoreTokens()) {
					String token = st.nextToken();
					if(token.startsWith("/")) { //startsWith()메서드로 시작 문자 비교 가능 - 논리형 반환
						reqPath = token;
					}
				}

				String fileName = "./WebContent" + reqPath; // 상대경로(프로젝트 폴더 기준) 설정

				// 해당 파일이름을 이용하여	Content-type 정보 추출하기
				String contentType = URLConnection.getFileNameMap().getContentTypeFor(fileName);

				// css파일인 경우 인식이 안되서 추가함.
				if(contentType == null && fileName.endsWith(".css")) contentType = "text/css";
				//파일명이 .css로 끝나면, MIME 부여

				System.out.println("contentType => " + contentType);

				File file = new File(fileName);
				if(!file.exists()) {
					//응답메세지의 statusLine을 만들어주고 있다.
					makeErrorPage(out, 404, "Not Found");
					return;
				}

				byte[] body = makeResponseBody(fileName);

				byte[] header = makeResponseHeader(body.length, contentType);

				// 요청헤더가 HTTP/1.0 이거나 그 이후의 버전을 지원할 경우 MIME 헤더를 전송한다.
				// text/html : MIME 타입이라고 한다, 미리 정해져있는 것을 사용하는 방식
				
				//HTTP/를 포함하지 않으면(= 다른 프로토콜), -1을 반환할테니
				//-1이 아니어야  MIME header 전송 가능
				//옛날에는 이런 프로토콜 형식이 아니었을 거다 ...
				if (request.toString().indexOf("HTTP/") != -1) {
					out.write(header); // 응답헤더 보내기
				}

				System.out.println("응답헤더:\n" + new String(header));
				System.out.println("-------------------------------------");

				out.write(body); // 응답내용 보내기
				out.flush();	 // 남아있는 버퍼값들 비우기, close()를 호출하면 자동 호출 된다

			}catch (IOException ex) {
				System.err.println("입출력 오류!!!");

			}finally {
				try {
					socket.close(); // 소켓 닫기(연결 끊기)
				} catch (IOException e) {
					e.printStackTrace();
				}
			}/*
			 * http라는 스레드 객체를 하나 만들어서 .. 
			 * 
			 */
		}

		/**
		 * 에러페이지 생성
		 * @param out
		 * @param statusCode
		 * @param errMsg
		 */
		private void makeErrorPage(OutputStream out, int statusCode, String errMsg) {
			String statusLine = "HTTP/1.1" + " " + statusCode + " " + errMsg;
			try {
				out.write(statusLine.getBytes());
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		MyHTTPServer server = new MyHTTPServer();
		server.start();
	}


}
