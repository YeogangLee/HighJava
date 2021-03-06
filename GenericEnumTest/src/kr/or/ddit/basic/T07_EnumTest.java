package kr.or.ddit.basic;

public class T07_EnumTest {
/**
 * 열거형 => 상수값들을 선언하는 방법
 * 
 * static final int A = 0;
 * static final int B = 1;
 * static final int C = 2;
 * static final int D = 3;
 * 
 * enum Data (A, B, C, D);
 * 
 * 열거형 선언하는 방법  //- class와 비슷하다.
 * enum 열거형 이름 { 상수값1, 상수값2, ... , 상수값n };
 */
	
	//City 열거형 객체 선언(기본값을 이용하는 열거형)
	public enum City { 서울, 부산, 대구, 광주, 대전 }; //문자열인데 따옴표를 안써!!
	public enum City2 { 서울, 부산, 대구, 광주, 대전 }; //문자열인데 따옴표를 안써!!
	//City타입이라는 enum타입 객체들 ... 상수로 쓰이지만 결국 객체
	
	//데이터 값을 임의로 지정한 열거형 객체 선언
	//데이터값을 정해줄 경우에는 생성자를 만들어서 괄호 속의 값이 변수에 저장되도록 해야 한다.
	public enum Season {
		봄("3월부터 5월까지"), 여름("6월부터 8월까지"), 가을("9월부터 11월까지"), 겨울("12월부터 2월까지");
		
		//괄호 속의 값이 저장될 변수 선언
		private String str;
		
		//
		//생성자 만들기(열거형의 생성자는 제어자가 묵시적으로 'private'이다.) //일반 class와 가장 큰 차이
		// ==> 여러분들이 객체를 만들 수 있어요? 없어요? -> 없다.
		//싱글톤, 생성자를 private으로 하고 외부에서 가져갈 수 있도록 getInstance() 만들어놓죠
		//enum 상수를 사용하는 시점에 객체가 알아서 만들어져요. 여러분들이 객체를 마음대로 못만들게 하려고 생성자 private해놨다.
//		private Season(String data) { //아래 코드와 동일
		Season(String data) {
		//이 괄호는 각 계절뒤에 붙어있는 ()와 동일, 저 안에 문자열이 data로 들어오는 것 
			this.str = data;
		}
		
		//값을 반환하는 메서드
		public String getStr() {
			return str;
		}
	}
	
	public static void main(String[] args) {
		/**
		 * 열거형에서 사용되는 메서드
		 * 1.name() : 열거형 상수의 이름을 문자열로 반환한다.
		 * 2.ordinal() : 열거형 상수가 정의된 순서값을 반환한다.(기본적으로 0부터 시작)
		 * 3.valueOf("열거형상수이름")
		 *  : 지정된 열거형에서 "열거형상수이름"과 일치하는 열거형 상수를 반환한다.
		 *  
		 * 1번과 3번은 일종의 반대 개념
		 */
		
		City myCity1; //열거형 객체변수 선언
		City myCity2;
		
		//열거형 객체변수에 값 저장하기
		myCity2 = City.서울;
		myCity1 = City.valueOf("서울"); //City enum 에서 '서울'데이터 가져오기
		//return값 : enum 객체 -> 값이 "서울"인 열거형 상수 객체가 리턴이 되는 것
		
		//현재 myCity2, myCity1 둘 다 enum 객체가 저장되어있다.
		
		System.out.println("myCity1 : " + myCity1.name());
		System.out.println("myCity1의 ordinal : " + myCity1.ordinal());
		System.out.println();
		
		System.out.println("myCity2 : " + myCity2.name());
		System.out.println("myCity2의 ordinal : " + myCity2.ordinal());
		System.out.println("---------------------------------------");
		
		Season ss = Season.valueOf("여름");
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("get메서드 : " + ss.getStr()); //우리가 아까 선언했던 getter
		System.out.println("---------------------------------------");
		
		/*
		 * 아까 계절 객체가 4개 있었죠, 그러면 생성자가 4번 호출된 거에요
		 * 그러면 객체가 만들어질 때 그 객체가 가지고 있던 문자열 값은 괄호 안에 값이 들어가게 되는 거에요.
		 * 
		 */
		
		//Map할때 봤었죠? values()
		//열거형이름.values() => 데이터를 배열로 가져오기
		Season[] enumArr = Season.values();
		//배열도 iterable 하기 때문에 향상된 for문 사용 가능해요. 여기서는 그냥 일반 for문 사용했지만.
		for(int i = 0; i<enumArr.length; i++) {
			System.out.println(enumArr[i].name() + " : " + enumArr[i].getStr());
		}
		System.out.println();
		
		//이터러블하니까 사용 가능
		for(City city : City.values()) {
			System.out.println(city + " : " + city.ordinal());
		}
		
		City city = City.대구; //대구라는 상수
		
		System.out.println(city == City.대전); //false
//		System.out.println(city == City.대구); //true
//		System.out.println(city == City2.대구); //에러, City, City2  타입 자체가 다르다.
											   //컴파일러가 바로 막아주죠, 이런 게 장점 -> 타입 안전한 코딩 가능
		
		//enum 타입의 객체라면 compareTo()메서드를 가지고 있다. => enum객체들은 comparable을 구현하고 있다.
		//compareTo() : 오름차순으로 비교하죠
		//여기서 ordinal() 값으로 비교하고 있어요, 그래서 밑에 출력값 0,2,-2
		System.out.println("대구 : " + city.compareTo(City.대구)); //0
		System.out.println("서울 : " + city.compareTo(City.서울)); //2
		System.out.println("대전 : " + city.compareTo(City.대전)); //-2
		
		//enum
		//사용이유, 목적, 장점
		//: 상수는 상수인데 타입까지 체크해주는, 타입 안전한 코딩이 가능
		//  상수값에 타입까지 구현이 되어있는 enum 
	}
	
}
