package kr.or.ddit.basic;

public class T07_EnumTest_prc {
	
	//City 열거형 객체(기본값)
	public enum City { 서울, 부산, 대구, 광주, 대전 };
	
	//Season 열거형 객체(임의의 데이터값)
	public enum Season {
		봄("3월부터 5월까지"), 여름("6월부터 8월까지"), 가을("9월부터 11월까지"), 겨울("12월부터 2월까지");
		
		private String str;
		
		Season(String data){
			this.str = data;
		}

		public String getStr() {
			return str;
		}
	}
	
	public static void main(String[] args) {
		City myCity1;
		City myCity2;
		
		myCity1 = City.서울;
		myCity2 = City.valueOf("서울");
		
		System.out.println("myCity1는 " + myCity1.name());
		System.out.println("myCity1의 ordinal : " + myCity1.ordinal());
		System.out.println();
		
		System.out.println("myCity2는 " + myCity2.name());
		System.out.println("myCity2의 ordinal : " + myCity2.ordinal());
		System.out.println("---------------------------------------");
		
		Season ss = Season.valueOf("여름");
//		System.out.println("name : " + ss.name());
//		System.out.println("ordinal : " + ss.ordinal());
//		System.out.println("get메서드 : " + ss.getStr());
		//
		System.out.println("name : " + Season.valueOf("여름").name());
		System.out.println("ordinal : " + Season.valueOf("여름").ordinal());
		System.out.println("get메서드 : " + Season.valueOf("여름").getStr());
		System.out.println("---------------------------------------");
		
		//열거형이름.values()
		Season[] enumArr = Season.values();
		for(Season season : Season.values()) {
			System.out.println(season + " : " + season.ordinal());
		}
		
		Season season = Season.valueOf("봄");
		Season season2 = Season.여름;
		System.out.println("1. "+Season.valueOf("봄"));
		System.out.println("2. "+Season.여름);
		System.out.println("3. "+City.대전);
		
		System.out.println("봄 : " + season.compareTo(Season.겨울));
	}
}
