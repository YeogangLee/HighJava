package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04_ListSortTest_delAnno {

	public static void main(String[] args) {
		
		List<Student> stuList = new ArrayList<Student>();

		stuList.add(new Student("19990619", "신장마", 90, 60, 100));
		stuList.add(new Student("20190712", "이어른", 29, 68, 23));
		stuList.add(new Student("19990830", "이가을", 30, 80, 100));
		stuList.add(new Student("20181117", "김겨울", 11, 100, 70));
		stuList.add(new Student("20170678", "이여름", 8, 77, 11));
		stuList.add(new Student("20211102", "백미키", 2, 98, 20));
		//랭킹 이중for문, 위에 i==j continue, 밑에 조건문 stuList.get(j).rank();
		
//		for(int i = 0; i < stuList.size(); i++){
//			for(int j = 0; j < stuList.size(); j++){
//				if(i==j) {
//					continue;
//				}
//				if(stuList.get(i).getTotal()>stuList.get(j).getTotal()) {
//					stuList.get(j).downRank();
//				}
//			}
//		}
		
		
		System.out.println("정렬 전");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("-----------------------------");
		
		Collections.sort(stuList); //정렬
		System.out.println("학번의 오름차순으로 정렬 후...");
		
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("-----------------------------");
		
		
//		GetIndexOf idx = new GetIndexOf();
//		List<Integer> idxList = new ArrayList<Integer>();
//		idxList = idx.getIndexOf(stuList);
//		System.out.println("rank 확인");
//		for(Integer num : idxList) {
//			System.out.println(num);
//		}
//		System.out.println("-----------------------------");

		
		//외부 정렬 기준을 이용한 정렬하기
//		Collections.sort(stuList, new SortTotalDesc());

		//내가 지금 만든 객체 자체(Member)에 정렬 기능 부여 - Comparable 컴? 페어러블
		System.out.println("총점의 내림차순으로 정렬 후...");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("-----------------------------");
		
		
	}

}

/*
 * 정렬 기준의 외부 선언을 위해서는 Comparator 인터페이스를 구현하면 된다.
 * Member의 번호(num)의 내림차순으로 정렬하기
 */

//class SortTotalDesc implements Comparator<Student>{
//
//	@Override
//	public int compare(Student stu1, Student stu2) {
//		if(stu1.getTotal() > stu2.getTotal()) {
//			return -1;
//		}else if(stu1.getTotal() == stu2.getTotal()) {
//			if(stu1.getStuNo() > stu2.getStuNo()) {
//				return -1;
//			}
//			return 0;
//		}else {
//			return 1;
//		}
//	}
//}

class GetIndexOf{
	
	List<Integer> getIndexOf(List<Student> stuList) {
		List<Integer> idx = new ArrayList<Integer>();
		//모르겠다
		return idx;
	}
}

class Student implements Comparable<Student> {

	private String stuNo;	//학번
	private String name; 	//이름
	private int kor;		//국어점수
	private int eng;		//영어점수
	private int math;		//수학점수
	private int total;		//총점
	private int rank = 1;	//등수
	
	//우클릭 > Source > Generate Constructor using Fields(멤버변수를 이용해 생성자 선언)
	public Student(String stuNo, String name, int kor, int eng, int math) {
		super();
		this.stuNo = stuNo;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total = kor + eng + math;	
	}
	public void downRank() {
		this.rank = this.rank+1;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
//	public int getRank(ArrayList<Student> stuList) {
//		return rank;
//	}
	public int getRank(int rank) {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}

	

	//이름을 기준으로 오름차순 정렬이 되도록 설정한다.
	@Override
	public int compareTo(Student stu) {
//		return this.getStuNo().compareTo(stu.getStuNo()); //this 생략 가능
//		return this.getEng().compareTo(Integer.parseInt(String.valueOf(stu.getEng())));
		return new Integer(stu.getEng()).compareTo(stu.getEng()) * -1;
		//getName이 String이라서 compareTo 사용 가능
		//* -1 해주면 오름차순에서 내림차순
	}

	@Override
	public String toString() {
		return "Student [stuNo=" + stuNo + " | name=" + name + " | kor=" + kor + " | eng=" + eng + " | math=" + math
				+ " | total=" + total + " | rank=" + rank + "]";
	}
	
}