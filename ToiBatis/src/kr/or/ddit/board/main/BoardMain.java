package kr.or.ddit.board.main;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

public class BoardMain {
	
	private static IBoardService boardService;
	
	private BoardMain() {
		boardService = BoardServiceImpl.getInstance();
	}
	
	private Scanner scan = new Scanner(System.in); 
	
	public static void main(String[] args) {
		BoardMain jdbcObj = new BoardMain();
		jdbcObj.start();
	}
	
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 전체 목록 출력");
		System.out.println("  2. 새 게시글 작성");
		System.out.println("  3. 게시글 수정");
		System.out.println("  4. 게시글 삭제");
		System.out.println("  5. 게시글 검색");
		System.out.println("  6. 작업 끝");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 전체 자료 출력
					displayAll();
					break;
				case 2 :  // 새 게시글 작성
					insertBoard();				
					break;
				case 3 :  // 게시글 수정
					updateBoard();
					break;
				case 4 :  // 게시글 삭제
					deleteBoard();
					break;
				case 5 :  // 게시글 검색
					searchBoard();
					break;
				case 6 :  // 작업 끝
					System.out.println("== 작업 종료 ==");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요");
			}
		}while(choice!=6);
	}	
	
	private void displayAll() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		String wdate = "";
		
		System.out.println("-----------------------------------------------");
		System.out.println("번호\t제목\t작성자\t작성일");
		System.out.println("-----------------------------------------------");
		
			List<BoardVO> boardList = boardService.getAllBoardList();
			
			if(boardList.size()==0) {
				System.out.println("게시글 정보가 존재하지 않습니다.");
			}else {
				for(BoardVO bv : boardList) {
					wdate = sdf.format(bv.getBoard_date());
					
					System.out.println(bv.getBoard_no() + "\t" + bv.getBoard_title() + "\t" 
									+ bv.getBoard_writer() + "\t" + wdate);			
				}
			}
			System.out.println("-----------------------------------------------");
			
	}

	private void insertBoard() {
		
		scan.nextLine();
	
		System.out.print("글 제목 >> ");
		String board_title = scan.nextLine();
		
		System.out.print("작성자 >> ");
		String board_writer = scan.next();
		
		scan.nextLine();		
		
		System.out.print("글 내용 >> ");
		String board_content = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoard_content(board_content);
		bv.setBoard_title(board_title);
		bv.setBoard_writer(board_writer);

		int cnt = boardService.insertBoard(bv);
		
		if(cnt > 0) {
			System.out.println("[" + board_title + "] 글 작성 성공!");
		}else {
			System.out.println("[" + board_title + "] 글 작성 실패!!!");
		}
			
	}
	
	private void updateBoard() {
		boolean check = false;
		int board_no = 0;
		
		do {
			System.out.print("수정 대상 글 번호 >> ");
			board_no = scan.nextInt();
			
			check = boardService.checkBoard(board_no);
			
			if(check == false) {
				System.out.println("게시글 번호가 " + board_no + "인 게시글은 존재하지 않습니다."); 
				System.out.println("글 번호를 다시 입력하세요.");
			}
		}while(check == false);
		
		scan.nextLine();
		
		System.out.print("수정할 글 제목 >> ");
		String board_title = scan.nextLine();
		
		System.out.print("수정할 글 내용 >> ");
		String board_content = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoard_no(board_no);
		bv.setBoard_title(board_title);
		bv.setBoard_content(board_content);
				
		int cnt = boardService.updateBoard(bv);
		
		if(cnt > 0) {
			System.out.println("글 수정 성공!");
		}else {
			System.out.println("글 수정 실패!!!");
		}
			
	}
	
	private void deleteBoard() {
		
		System.out.print("삭제 대상 글 번호 >> ");
		int board_no = scan.nextInt();
		
		int cnt = boardService.deleteBoard(board_no);
		
		if(cnt > 0) {
			System.out.println("글 삭제 성공!");
		}else {
			System.out.println("글 삭제 실패!!!");
		}
			
	}
	
	private void searchBoard() {
		
		System.out.println();
		System.out.println("검색할 게시글 정보를 입력하세요.");
		System.out.print("게시글 번호 (없을시 0 입력) >> ");
		int board_no = scan.nextInt();
		
		scan.nextLine();
		System.out.print("게시글 제목 (없을시 enter키 입력) >> ");
		String board_title = scan.nextLine().trim();
		
		System.out.print("게시글 작성자 (없을시 enter키 입력) >> ");
		String board_writer = scan.nextLine().trim();
		
		System.out.print("게시글 내용 (없을시 enter키 입력) >> ");
		String board_content = scan.nextLine().trim();
			    
	    BoardVO bv = new BoardVO();
	    
		bv.setBoard_content(board_content);
		bv.setBoard_no(board_no);
		bv.setBoard_title(board_title);
		bv.setBoard_writer(board_writer);			
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		String wdate = "";
		
		List<BoardVO> boardList = boardService.getSearchBoard(bv);
		if(boardList.size() == 0) {
			System.out.println("검색한 회원정보가 존재하지 않습니다.");
		}else {
			for(BoardVO bv2 : boardList) {
				wdate = sdf.format(bv2.getBoard_date());
				System.out.println("-----------------------------------------------");
				System.out.println("번호\t제목\t작성자\t작성일");
				System.out.println(bv2.getBoard_no() + "\t"
								+ bv2.getBoard_title() + "\t"
								+ bv2.getBoard_writer() + "\t"
								+ wdate + "\n"
								+ "-----------------------------------------------\n"
								+ bv2.getBoard_content() + "\n\n");			
			}
		}		
		System.out.println("-----------------------------------------------");
	}
	
}
