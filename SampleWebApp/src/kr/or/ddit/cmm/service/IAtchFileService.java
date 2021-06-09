package kr.or.ddit.cmm.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.cmm.vo.AtchFileVO;

public interface IAtchFileService {
	
	/**
	 * 첨부파일 목록을 저장하기 위한 메서드
	 * @param fileItemMap
	 * @return
	 * @throws Exception
	 */
	public AtchFileVO saveAtchFileList(Map<String, Object> fileItemMap) throws Exception;
	
	/**
	 * 첨부파일 목록 조회하기
	 * @param atchFileVO
	 * @return
	 * @throws Exception
	 */
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) throws Exception;
	
	/**
	 * 첨부파일 세부정보 조회하기
	 * @param atchFileVO
	 * @return
	 * @throws Exception
	 */
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) throws Exception;
	
}
