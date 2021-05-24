package kr.or.ddit.homework;

public class indexOf_string_prc {
	public static void main(String[] args) {
		
		String str = "/무지개 안녕";
		
		int blank = str.indexOf(" ");
		
		str.substring(blank);
		
		System.out.println("blank: " + blank + "\n" + "메세지:" + str.substring(blank+1) + "\n");
		System.out.println("blank: " + blank + "\n" + "메세지:" + str.substring(0, blank));
		
		System.out.println("str.indexOf('/')" + str.indexOf('/'));
		System.out.println("str.indexOf(0): " + str.indexOf(0));
		System.out.println("String.valueOf(str.indexOf('/')): " + String.valueOf(str.indexOf('/')));		
		System.out.println("String.valueOf(str.indexOf(0)): " + String.valueOf(str.indexOf(0)));		
		
//		if(String.valueOf(str.indexOf(0)).equals("/")) {
		if(str.indexOf('/') == 0) {
			System.out.println("잠와");
		}else {
			System.out.println("다른 문자열!!");
		}
	}
}
