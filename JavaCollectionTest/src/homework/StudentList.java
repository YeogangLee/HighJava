package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentList {

	
	public static void main(String[] args) {		
		List<Student> StudentList = new ArrayList<>();	
		
		StudentList.add(new Student("3587", "김모씨", 50, 70, 70));
		StudentList.add(new Student("3235", "이모씨", 80, 80, 50));
		StudentList.add(new Student("3112", "송모씨", 70, 90, 55));
		StudentList.add(new Student("2353", "최모씨", 40, 45, 95));
		StudentList.add(new Student("6894", "하모씨", 55, 60, 90));
		
		for( Student student : StudentList) {
			System.out.println(student);
		}		
		System.out.println("----------------------정렬전후------------------------");
		Collections.sort(StudentList);
		for( Student student : StudentList) {
			System.out.println(student);
		}
		
		
		for(int i=0; i <StudentList.size(); i++) {
			int total = StudentList.get(i).getKor() + StudentList.get(i).getEng()+StudentList.get(i).getMath();
			StudentList.get(i).setSum(total);
		}
		
		for(int i=0; i<StudentList.size();i++) {
			int rank = 1;
			int sum = StudentList.get(i).getSum();
			for(int j=0; j<StudentList.size(); j++) {
				if(sum < StudentList.get(j).getSum()) {
					rank++;
				}
			}
			StudentList.get(i).setRank(rank);
		}
		System.out.println("-------------------------모든결과----------------------------");
		for( Student student : StudentList) {
			System.out.println(student);
		}
		
		Collections.sort(StudentList, new sortTotalDesc());
		
		System.out.println("----------------------총점역순------------------------------");
		for( Student student : StudentList) {
			System.out.println(student);
		}
		
		
	}
		
}


class sortTotalDesc implements Comparator<Student>{	
	@Override
	public int compare(Student stu1, Student stu2) {
		int index;
		if(stu1.getSum() == stu2.getSum()) {
			index = stu1.getStu_no().compareTo(stu2.getStu_no())*-1;
		}else {
			index = Integer.compare(stu1.getSum(), stu2.getSum())*-1;
		}
		return index;
	}
	
	
}


class Student implements Comparable<Student>{
	
	
	String stu_no;
	String name;
	int kor;
	int eng;
	int math;
	int sum;
	int rank;	
		
	@Override
	public String toString() {
		return "Student [stu_no=" + stu_no + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", sum=" + sum + ", rank=" + rank + "]";
	}

	public Student(String stu_no, String name, int kor, int eng, int math) {
		super();
		this.stu_no = stu_no;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	public String getStu_no() {
		return stu_no;
	}

	public void setStu_no(String stu_no) {
		this.stu_no = stu_no;
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

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(Student student) {		
		return this.getStu_no().compareTo(student.getStu_no());
	}
		
}
