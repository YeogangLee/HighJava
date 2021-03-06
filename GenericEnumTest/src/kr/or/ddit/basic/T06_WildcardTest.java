package kr.or.ddit.basic;

import java.util.Arrays;

public class T06_WildcardTest {
	
	/**
	 * 모든 과정 수강정보 조회
	 * @param course 모든 과정
	 */
	//와일드카드를 쓰지 않으면, 이 메서드를 제너릭 메서드로 만들어줘야 한다.
	//<?> : 제너릭 T로 올 수 있는 애들은 다 오겠다.
	public static void listCourseInfo(Course<?> course) {
		System.out.println(course.getName()
				+ "수강생: "
				+ Arrays.toString(course.getStudents()));
	}
	
	/**
	 * 학생 과정 수강정보 조회
	 * @param course 학생
	 */
	//extends Student: 학생, 학생 class를 물려받은 고등학생, 상한을 학생으로 잡은 것
	public static void listStudentCourseInfo(Course<? extends Student> course) {
		System.out.println(course.getName()
				+ "수강생: "
				+ Arrays.toString(course.getStudents()));
	}
	
	/**
	 * 직장인 과정 수강정보 조회
	 * @param course 직장인과 일반인
	 */
	//super Worker : Worker를 포함하여 Worker의 부모, 조상까지, 하한을 Workder로 잡은 것
	public static void listWorkerCourseInfo(Course<? super Worker> course) {
		System.out.println(course.getName()
				+ "수강생: "
				+ Arrays.toString(course.getStudents()));
	}
	
	public static void main(String[] args) {
		
		//코스 객체 먼저 생성
		Course<Person> personCourse = new Course<>("일반인과정", 5);
		personCourse.add(new Person("일반인1"));
		personCourse.add(new Worker("직장인1"));
		personCourse.add(new Student("학생1"));
		personCourse.add(new HighStudent("고등학생1"));
		
		Course<Worker> workerCourse = new Course("직장인과정", 5);
		workerCourse.add(new Worker("직장인1"));
		
		Course<Student> studentCourse = new Course("학생과정", 5);
		studentCourse.add(new Student("학생1"));
		studentCourse.add(new HighStudent("고등학생1"));
		
		Course<HighStudent> highStudentCourse = new Course("고등학생과정", 5);
		highStudentCourse.add(new HighStudent("고등학생1"));
		
		//static 메서드를 불러오면 이렇게 italic체로 보이는 것 같다.
		//모든 과정
		listCourseInfo(personCourse);
		listCourseInfo(workerCourse);
		listCourseInfo(studentCourse);
		listCourseInfo(highStudentCourse);
		System.out.println("----------------------------------------------");
		
		//학생
//		listStudentCourseInfo(personCourse);
//		listStudentCourseInfo(workerCourse);
		listStudentCourseInfo(studentCourse);
		listStudentCourseInfo(highStudentCourse);
		System.out.println("----------------------------------------------");
		
		//직장인
		listWorkerCourseInfo(personCourse);
		listWorkerCourseInfo(workerCourse);
//		listWorkerCourseInfo(studentCourse);
//		listWorkerCourseInfo(highStudentCourse);
		
		//모르면 계속 반복하고, 영어공부하는것처럼, 그리고 문법공부 열심히 하세요.
		//문법에서 자유롭지를 못하면, 응용이 어려워요.
	}
}

class Person {
	String name; //이름
	public Person(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "이름" + name;
	}
}

class Worker extends Person {
	public Worker(String name) {
		super(name);
	}
}

class Student extends Person {
	public Student(String name) {
		super(name);
	}
}

class HighStudent extends Student {
	public HighStudent(String name) {
		super(name);
	}
}

/**
 * 수강과정
 * @author PC-14
 *
 * @param <T>
 */
class Course<T> {
	private String name;	//과정명
	private T[] students;	//수강생(제너릭 배열)
	
	public Course(String name, int capacity) {
		this.name = name;
		//타입 파라미터로 배열을 생성시 오브젝트 배열을 생성 후,
		//타입 파라미터 배열로 캐스팅 처리 해야 함.
		
		//제너릭 배열 생성 실패(new연산자는 생성할 객체의 타입이 정확히 정의되어야 가능)
//		students = new T[capacity]; //new는 T를 모른다, T 자료형을
		students = (T[])(new Object[capacity]);	//T타입 캐스팅(형변환)
	}

	public String getName() {
		return name;
	}

	public T[] getStudents() {
		return students;
	}
	
	/**
	 * 수강생 등록
	 * @param t
	 */
	public void add(T t) {
		for(int i = 0; i < students.length; i++) {
			if(students[i] == null) {//아직 등록되지 않은(빈) 자리인지 확인
				students[i] = t;
				break;
			}
		}
	}
}