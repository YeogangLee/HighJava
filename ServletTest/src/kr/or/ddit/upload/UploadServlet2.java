package kr.or.ddit.upload;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 자카르타 프로젝트의 fileupload 모듈을 이용한 파일 업로드 예제
 * @author PC-14
 *
 */
@WebServlet("/upload2")
public class UploadServlet2 extends HttpServlet {
	
	private static final String UPLOAD_DIRECTORY = "upload_files";
	
	//메모리 임계 크기(이 크기가 넘어가면 레파지토리 위치에 임시 파일로 저장됨)
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; //3MB
	// 파일 1개당 최대 크기
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 40;
	// 요청 파일 최대 크기
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 50;
	
	//THRESHOLD 쓰레즈홀드
	//무슨 뜻일까? 임계값
	//기본적으로 서버의 메모리 상에서 처리를 한다
	/*
	 서버: 1대 다 처리, 메모리가 널널하지 않으므로 메모리에 한계를 준다
	 메모리 대신 임시 파일로 저장, 메모리 한계 이후에 관리를 파일로 할지에 대한 값 
	 
	 1 * 1024 = 1KB
	 1 * 1024 * 1024 = 1MB
	 
	 파일 1개 단위에 제한 : MAX_FILE_SIZE 
	 파일을 1개 보낼 수 있지만 여러개도 보낼 수 있다
	 그럴 때 용량 제한 : MAX_REQUEST_SIZE
	 
	 */
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("전송자 : " + req.getParameter("sender"));
		//콘솔> 전송자 : null
		
		
		//ServletFileUpload: 톰캣 패키지 말고, 우리가 들고온 jar파일에 있는 commons 사용
		//멀티 파트인 경우...
		//서블릿 입장에서는 멀티파트일 수도 있고, 아닐 수도 있다
		//우리는 예제를 하고 있으니까 전부 멀티파트라고 가정을 하지만.
		if(ServletFileUpload.isMultipartContent(req)) {
			Map<String, String> formMap = new HashMap<String, String>();
			//우리가 임의로 만든 것
			/*
			 멀티파트로 온 경우
			 */
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			System.out.println(System.getProperty("java.io.tmpdir"));
			//버추얼 머신의 설정값
			//=> C:\Users\PC-14\AppData\Local\Temp\
			//운영체제마다 임시 공간이 다르다, windows에서는 저 공간이 임시공간, 맥에서는 또 다르다.  
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE); //파일 크기 설정
			
			// 웹 애플리케이션 루트 디렉토리 기준 업로드 경로 설정하기
			// getRealPath("")에 공백을 넣는 것은, 기본 컨텍스트 루트 위치를 알려주는 것
			// 이 메서드 쓰고 싶어서 서블릿 컨텍스트 객체 불러냈어요.
			String uploadPath = getServletContext().getRealPath("") //파일 저장을 위해 실제 경로를 알아야 한다, 파일 output스트림은 파일 경로를 알아야 한다
								+ File.separator + UPLOAD_DIRECTORY;
			//upload_directory는 우리가 "upload_files"로 지정해둔 값
			//루트 위치에 저 upload_files 폴더를 만들고, 파일을 업로드하고 싶은 것
			//플러그인 기능에서의 루트 폴더는 우리가 아는 WebContent폴더와는 다르다.
			
			//플로그인을 사용할 때의 루트폴더
			//=> 바로가기 tmp0/wtpwebapps/ServletTest
			
			
			//getServletContext().getRealPath("")
			/*
			 	서비스 폴더를 기준으로 경로를 잡는 것
			 	장점: 다른 곳으로 옮겨도 첨부파일까지 그대로 딸려간다,
			 	왜냐하면 다같이  WebContent 안에 담겨있기 때문에
			 	
			 */
		
			/*
				 파일 경로를 구분해주는 문자: File.separator
				 윈도우에서는 \, 리눅스에서는 다른 문자... 운영체제에 맞게 변경시켜준다
				 문자열로 박으면 하드코딩, 다른 운영체제에서는 적용 불가능
			 */
			
			
			//파일 객체에 uploadPath변수로, 파일의 디렉터리를 알려주고,
			//해당 파일이 해당 경로에 존재하지 않을시,
			//mkDir()메서드로 파일과, 파일경로(폴더)를 동시에 만들어 준다.
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			try {
				
				//멀티파트 파싱 시작
				//위의 코드들은 upload를 만들기 위한 작업들
				//멀티파트로 날라왔기 때문에 원하는 대로 파싱을 해줘야 한다
				//List를 리턴하도록 메서드가 설계, FileItem객체를 담고있는 리스트
				//FileItem은 파싱된 각각의 파일들
				//이게 폼데이터인지 첨부파일인지 구분하는 작업을 밑에서 한다.
				List<FileItem> formItems = upload.parseRequest(req);
				
				//file일수도, formdata일 수도 있다... 뭔지 모르지만, 존재하는 파일
				if(formItems != null && formItems.size() > 0) {
					for(FileItem item : formItems) {
						//파일인 경우...
						if(!item.isFormField()) {
							// 파일명만 추출하기
							String fileName = new File(item.getName()).getName();
							String filePath = uploadPath + File.separator + fileName;
							File storeFile = new File(filePath);
							item.write(storeFile); //업로드 파일 저장.
							
							//파일 객체에 item.getName()메서드 사용, 파일명만 들고 온다
							//item객체.write(파일)하면 편하게 파일 write 가능
							
						}else {
							//폼 필드인 경우...
							//폼 필드의 값이 한글인 경우에는 해당 문자열을 적절히 변환해 주어야 한다.
							formMap.put(item.getFieldName(), item.getString("UTF-8"));
							//input태그에 name속성 준 것, getFieldName()메서드 사용해서 꺼내기
							//각각 sender(name속성 값), 홍길동(텍스트상자에 입력한 값) 들고온 후 put()으로 Map에 넣기
							
							/*
							 Map에 추가하는 데이터
							 = put(key, value) 
							 = put(item.getFieldName(), item.getString("UTF-8"))
							
							 item.getFieldName()로 읽어오는 값은
							 input태그의 name속성
							 item.getString("UTF-8")로 읽어오는 값은
							 input태그로 입력받은 text값
							  
							  그래서 getString()메서드에만 "UTF-8"을 설정해준다.
							  일종의 jsp 파일에서 사용했던, 메서드의 개념 (getParameter())
							 
							 - html시간 intro2.jsp파일
							 request.setCharacterEncoding("UTF-8");
							 String userNameP = request.getParameter("name");
							 
							  리퀘스트 객체에 접근해서
							  키(input의 name속성)가 name인 값을 읽어오려는 것
 							 => input의 name속성이 name인 곳에 입력받은 데이터를 읽어오는 것 
							 
							   
							 
							 */
						}
					}
				}
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
			
			for(Entry<String, String> entry : formMap.entrySet()) {
				System.out.println("파라미터명 :" + entry.getKey());
				System.out.println("파라미터값 :" + entry.getValue());
			}
			
			resp.setContentType("text/html");
			resp.getWriter().print("업로드 완료!");
			
		}//if문		
		
	}//doPost()메서드
}
