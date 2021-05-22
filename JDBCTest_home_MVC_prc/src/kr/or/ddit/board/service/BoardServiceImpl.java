package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao boardDao;
	
	private static IBoardService boardService;
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}

	@Override
	public int insertBoard(BoardVO bv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(String boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkBoard(String boardNo) {
		// TODO Auto-generated method stub
		return false;
	}

}
