package kr.or.ddit.board.vo;

/**
 * DB 테이블에 있는 컬럼을 기준으로 데이터를 객체화한 클래스
 * @author PC-14
 * 
 * <p>
 *     DB테이블의 '컬럼'이 이 클래스의 '멤버변수'가 된다.<br>
 *     DB테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행한다.<br> 
 * </p>
 * 
 *  VO: Value Object 값을 저장하기 위한 오브젝트
 */

public class BoardVO {
	private int board_no;
	private String board_title;
	private String board_writer;
	private String board_content;
	
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	
	@Override
	public String toString() {
		return "BoardVO [board_no=" + board_no + ", board_title=" + board_title + ", board_writer=" + board_writer
				+ ", board_content=" + board_content + "]";
	}
	
	
}
