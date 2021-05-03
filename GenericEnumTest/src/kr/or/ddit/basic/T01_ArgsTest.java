package kr.or.ddit.basic;

public class T01_ArgsTest {
	/*
	 * 가변형 인수 => 메서드의 매개변수의 개수가 실행될 때마다 다를 때 사용한다.
	 * 
	 * - 가변형 인수는 메서드 안에서는 배열로 처리된다.
	 * - 가변형 인수는 한 가지 자료형만 사용할 수 있다.
	 * 
	 * 인수는 argument, 매개변수 파라미터는 parameter
	 * 메서드를 정의할 때 파라미터를 정의하지 인수를 정의하지는 않는다
	 * argument는 실제 메서드를 사용할 때, 파라미터를 통해 실제 사용하는 값
	 * 
	 */
	
	//배열을 이용한 메서드
	//매개변수로 받은 정수들의 합계를 구하는 메서드 (이 정수들의 개수는 상황에 따라 다르다.)
	public int sumArr(int[] data) {
		int sum = 0;
		for(int i = 0; i<data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	//가변형 인수를 이용한 메서드
	public int sumArg(int...data) {
		int sum = 0;
		for(int i = 0; i<data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	//가변형 인수와 일반적인 인수를 같이 사용할 경우에는
	//가변형 인수를 제일 뒤쪽에 배치해야 한다.
	//배열에 접근하는 것과 같다.
	public String sumArg2(String name, int...data) {
		int sum = 0;
		for(int i = 0; i<data.length; i++) {
			sum += data[i];
		}
		return name+"씨 점수 : " + sum;
	}
	
	public static void main(String[] args) {
		T01_ArgsTest at = new T01_ArgsTest();
		
		int[] nums = {100, 200, 300};
		System.out.println(at.sumArr(nums));
		System.out.println(at.sumArr(new int[] {1,2,3,4,5}));
		System.out.println();
		
		System.out.println(at.sumArg(100, 200, 300));
		System.out.println(at.sumArg(1, 2, 3, 4, 5));
		System.out.println();
		
		System.out.println(at.sumArg2("홍길동", 1,2,3,4,5,6,7,8));
	}

}
