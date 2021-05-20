package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {
	public int insertBoard(BoardVO bv);
	public int deleteBoard(int board_no);
	public int updateBoard(BoardVO bv);
	public List<BoardVO> getSearchBoard(BoardVO bv);
	public List<BoardVO> getAllBoardList();
	public boolean checkBoard(int board_no);
}
