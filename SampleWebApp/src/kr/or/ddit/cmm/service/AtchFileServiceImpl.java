package kr.or.ddit.cmm.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.fileupload.FileItem;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.cmm.dao.AtchFileDaoImpl;
import kr.or.ddit.cmm.dao.IAtchFileDao;
import kr.or.ddit.cmm.filter.FileUploadRequestWrapper;
import kr.or.ddit.cmm.vo.AtchFileVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class AtchFileServiceImpl implements IAtchFileService {
	
	private static IAtchFileService fileService;
	private IAtchFileDao fileDao;
	private SqlMapClient smc;
	
	public AtchFileServiceImpl() {
		fileDao = AtchFileDaoImpl.getInstance();
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IAtchFileService getInstance() {
		if(fileService == null) {
			fileService = new AtchFileServiceImpl();
		}
		
		return fileService; 
	}

	@Override
	public AtchFileVO saveAtchFileList(Map<String, Object> fileItemMap) throws Exception {
		
		// 파일 기본정보 저장하기
		AtchFileVO atchFileVO = new AtchFileVO();
		
		fileDao.insertAtchFile(smc, atchFileVO);
		//깔끔한 atchFileVO
		/*
		 하지만 상관이 없는 게 ATCH_FILE.xml의
		 얻어온 id를 넣어서, insert가능
		 sysdate, y하드코딩값
		 그래서 특별히 더 할 게 없다
		 id가 키값이니까, 소중한 거죠 -> vo에 담아주기
		 
		 기본적으로 -1이었겠지만, 시퀀스가 실행되는 순간
		 메이저 테이블의 id값이 들어온다
		 ibatis가 해줘요, selectKey 엘리먼트를 이용해서
		 
		  잘 insert가 됐다면 1건이 insert됐을 거고
		  그 다음 for문
		 여러개를 받을 경우를 대비해서 Map으로 받았었다.
		 */
		
		for(Object obj : fileItemMap.values()) {
			
			FileItem item = (FileItem) obj;
			
			File uploadDir = new File(FileUploadRequestWrapper.UPLOAD_DIRECTORY);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			// 전체 경로 중 파일명만 추출하기
			String originFileName = new File(item.getName()).getName();
			//
			//파일 이름만 들고 오려고, 굳이 파일 객체로 만들었다가, 그 파일 객체가 제공하는 메서드인 getName()으로
			//파일명만 얻어오기
			
			long fileSize = item.getSize();
			String storeFileName = "";
			String filePath = "";
			File storeFile = null;
			
			do {
				//랜덤으로 생성된 id 중간중간에 - 보기 싫어서, replace 이용해서 없애기
				storeFileName = UUID.randomUUID().toString().replace("-", "");
				
				//아웃풋 스트림을 이용하기 위해, 파일 경로 생성, 여기 바로 저장하면 돼요
				filePath = FileUploadRequestWrapper.UPLOAD_DIRECTORY
						+ File.separator + storeFileName;
				
				storeFile = new File(filePath);
				//파일 객체를 만들어 준 이유는
				/*
				 순전히 아래의 exists()메서드 이용 위해
				 랜덤을 만들었는데, 중복되었을까봐
				 존재한다면, 또 do를 써서 새로운 랜덤값 받아오기 
				=> 결국, 중복되지 않을 때까지 do-while을 반복하는 것
				 */
				
			}while(storeFile.exists()); // 파일명이 중복되지 않을 때까지... do-while문 실행
			
			// 파일 확장명 추출
			String fileExtension = originFileName.lastIndexOf(".") < 0 ? "" : originFileName.substring(originFileName.lastIndexOf(".") + 1);
			//0보다 작으면 확장자가 없는 것 -> 파일이 없는 것
			
			// 업로드 파일 저장
			item.write(storeFile);
			//실제 우리가 랜덤하게 만든 숫자들이 나와요, 128비트짜리
			//예전에 할 때는 여기서 끝났는데,
			//다운로드 할 수 있도록 insert 때려줘야 해
			
			//vo에 세팅 
			atchFileVO.setStreFileNm(storeFileName);
			atchFileVO.setFileSize(fileSize);
			atchFileVO.setOrignlFileNm(originFileName);
			atchFileVO.setFileStreCours(filePath);
			atchFileVO.setFileExtsn(fileExtension);
			
			//이제 insert하면 되죠?
			
			// 파일 세부정보 저장
			fileDao.insertAtchFileDetail(smc, atchFileVO);

			// 임시 업로드 파일 삭제하기
			item.delete();
			// 기존에 업로드 된, 임시저장 파일이 있으면, 용량을 너무 많이 차지하니까, 삭제하는 메서드
			
		}
		
		return atchFileVO;
		
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
