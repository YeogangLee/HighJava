package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class T01_FileTest {
	public static void main(String[] args) throws IOException {
		//File객체 만들기 연습
		
		//1. new File(String 파일또는경로명)
		//   =>디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분 문자는 '\' 또는 '/'를 사용
		File file = new File("d:\\D_Other\\test.txt"); //생성자
		System.out.println("파일명 : " + file.getName());
		System.out.println("파일 여부 : " + file.isFile());
		System.out.println("디렉토리(폴더) 여부 : " + file.isDirectory()); //이게 폴더인지 아닌지 묻는 것
		System.out.println("---------------------------------------");
		
		File file2 = new File("d:\\D_Other");
		System.out.print(file2.getName() + "은 ");
		if(file2.isFile()) {
			System.out.println("파일");
		}else {
			System.out.println("디렉토리(폴더)");
		}
		System.out.println("---------------------------------------");
		
		//2. new File(File parent, String child)
		//	 => 'parent' 디렉토리 안에 있는 'child'파일 또는 디렉토리를 갖는다.
		File file3 = new File(file2, "test.txt"); //생성자 오버로드
		System.out.println(file3.getName() + "의 용량 크기 : " + file3.length() + "bytes"); //파일객체.length(): byte단위
		
		//3. new File(String parent, String child)
		File file4 = new File("d:/D_Other/", "test.txt");
//		File file4 = new File("/D_Other/test/..", "test.txt"); //getCanonicalPath() 시험해보고 싶을 때
		System.out.println("절대 경로 : " + file4.getAbsolutePath());
		System.out.println("경로 : " + file4.getPath()); //내가 적은 것을 그대로 들고오고 싶을 때
		System.out.println("표준 경로 : " + file4.getCanonicalPath()); //계산된 결과 -> 실제 경로를 계산해서 보여준다  
		
		//차이점
		/*
		 * 절대 경로: 루트부터 전체 경로를 알려주는 것
		 * 상대 경로: 현재 위치를 기준으로 경로를 알려주는 것
		 * 
		 * - 상대 경로의 장점: 길이가 짧아진다
		 *		...
		 * 보통 프로젝트 단위로 개발을 한다
		 * 그 프로젝트 폴더를 기준으로 상대경로를 설정
		 * 프로젝트 폴더를 다른 사람에게 줬을 때, 폴더만 주더라도 원래 경로에 영향X
		 * 절대경로로 작성했다면, 경로를 일일이 바꿔줘야 한다.
		 * 
		 * 절대경로의 장점: 가독성이 좋다.
		 */
		
		/**
		 * 디렉토리(폴더) 만들기
		 * 1. mkdir() => File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		 * 				  중간의 경로가 모두 미리 만들어져 있어야 한다.
		 * 				 (이미 존재하는 경로여야 의미가 있다.)
		 * 					
		 * 
		 * 2. mkdirs() => 중간의 경로가 없으면 중간의 경로도 새롭게 만든 후 
		 *                마지막 위치의 디렉토리를 만들어 준다.
		 *                (-> 애시당초 처음부터 경로를 만들어야 할 때는 mkdirs()를 써라)
		 *
		 * => 위 두 메서드 모두 만들기를 성공하면 true, 실패하면 false 반환
		 */
		
		File file5 = new File("d:/D_Other/연습용");
		if(file5.mkdir()) {
			System.out.println(file5.getName() + " 만들기 성공!");
		}else {
			System.out.println(file5.getName() + " 만들기 실패!!!");
		}
		System.out.println();
		
		//test, java가 없는 상태에서 만드려니, 만들기 실패 출력
		File file6 = new File("d:/D_Other/test/java/src");
//		if(file6.mkdir()) {  //mkdir() : test, java가 없는 상태에서 만드려니, 만들기 실패 출력
		if(file6.mkdirs()) { //mkdirs() : 만들기 성공
			System.out.println(file6.getName() + " 만들기 성공!"); 
		}else {
			System.out.println(file6.getName() + " 만들기 실패!!!");
		}
	}
}
