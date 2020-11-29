import java.util.Comparator;

public class Student implements Comparable<Student> {
	/*
	    Q1) 코드를 완성하세요.
	    
	    1.  이름(name), 반(classNo), 번호(studentNo),
	        국어(Korean), 수학(Math), 영어(English), 총점(Total)을
	        인스턴스변수로 선언한다.
	        
	    2. 이름, 반, 번호, 국어, 수학, 영어를 입력받아서 각 인스턴스변수에 저장하는
	       생성자를 선언한다.
	       
	    3. Object클래스의 toString()을 오버라이딩해서 실행결과와 같이,
	        이름, 반, 번호, 국어, 수학, 영어, 총점이 화면에 출력되도록 한다.
    */
	
	// Q6. 추 : 자리맞춰 출력하기 formatting
	final static int LEFT = 0;
	final static int CENTER = 1;
	final static int RIGHT = 2;
	
	private String name = "";
	private int classNo = 0;
	private int studentNo = 0;
	private int koreanScore = 0;
	private int mathScore = 0;
	private int englishScore = 0;
	private int totalScore = 0;
	
	// Q4 추가 전교등수
	private int schoolRank = 0; // 전교등수
	
	// Q5. 추가 반등수
	private int classRank = 0; // 반등수
	
	public Student() {
		this("가나다", 1, 1, 100, 100, 100);
	}

	public Student(String name, int classNo, int studentNo, int koreanScore, int mathScore, int englishScore) {
		super();
		this.name = name;
		this.classNo = classNo;
		this.studentNo = studentNo;
		this.koreanScore = koreanScore;
		this.mathScore = mathScore;
		this.englishScore = englishScore;
		this.totalScore = koreanScore + mathScore + englishScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClassNo() {
		return classNo;
	}

	public void setClassNo(int classNo) {
		this.classNo = classNo;
	}

	public int getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}

	public int getKoreanScore() {
		return koreanScore;
	}

	public void setKoreanScore(int koreanScore) {
		this.koreanScore = koreanScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getEnglishScore() {
		return englishScore;
	}

	public void setEnglishScore(int englishScore) {
		this.englishScore = englishScore;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
	public int getSchoolRank() {
		return schoolRank;
	}

	public void setSchoolRank(int schoolRank) {
		this.schoolRank = schoolRank;
	}
	
	public int getClassRank() {
		return classRank;
	}

	public void setClassRank(int classRank) {
		this.classRank = classRank;
	}


	@Override
	public String toString() {
//		return name + "\t"
//			+ classNo + "\t"
//			+ studentNo + "\t"
//			+ koreanScore + "\t"
//			+ mathScore + "\t"
//			+ englishScore + "\t"
//			+ totalScore + "\t"
//			+ schoolRank + "\t"
//			+ classRank + "\t";
		return format(name, 5, LEFT)
			+ format(""+classNo, 4, RIGHT)
			+ format(""+studentNo, 4, RIGHT)
			+ format(""+koreanScore, 6, RIGHT)
			+ format(""+mathScore, 6, RIGHT)
			+ format(""+englishScore, 6, RIGHT)
			+ format(""+totalScore, 8, RIGHT)
			+ format(""+schoolRank, 8, RIGHT)
			+ format(""+classRank, 8, RIGHT);
	}

	@Override
	public int compareTo(Student o) {
		// [문제2] Student클래스가 Comparable인터페이스를 구현해서, list를 총점(total) 내림차순으로 정렬되도록 하는 예제입니다.
		int thisTotalScore = this.totalScore;
		int anotherTotalScore = o.totalScore;
		
		return (thisTotalScore < anotherTotalScore ? 1 : (thisTotalScore == anotherTotalScore ? 0 : -1));
		
		// 모범 답안 코드 :
		// return o.totalScore - this.totalScore;
	}
	
	// Q6. 자리맟춰출력하기 새로 만든 메서드 format()
	public static String format(String str, int length, int alignment) {
		if (str == null) str = "";
		int diff = length - str.length();
		
		// 주어진 문자열(str)의 길이보다 length의 값이 작으면 str를 length만큼만 남기고 잘라낸다
		// 예를 들어, str이 "012345"이고, length가 3이면 결과는 "012"가 된
		if (diff < 0) return str.substring(0, length);
		
		char[] source = str.toCharArray();
		char[] result = new char[length];
		
		// 배열 result를 공백으로 채운다
		for (int i = 0; i < result.length; i++) {
			result[i] = ' '; // 공백문자다 ' ' <- 띄어쓰기가 되어 있어야 한다.
		}
		
		// 다음의 코드를 완성하시오
		// 1. 조건물을 이용해서 alignment의 값에 따라 다르게 처리한다.
		// (배열간의 복사에는 System.arraycopy()를 사용한다)
		switch(alignment) {
		// 1.1 alignment의 값이 CENTER일 때,
		case CENTER:
		// source의 내용이 result의 가운데 복사되도록 한다.
			// System.arraycopy(source, 0, result, diff/2, source.length);
			System.arraycopy(source, 0, result, source.length/2, source.length);
			break;
		// 1.2 alignment의 값이 RIGHT일 때,
		case RIGHT:
		// source의 내용의 끝이 result의 오른쪽 끝에 붙게 복사되도록 한다
			// System.arraycopy(source, 0, result, diff, source.length);
			System.arraycopy(source, 0, result, length - source.length, source.length);
			break;
		// 1.3 alignment의 값이 LEFT 또는 그 밖의 값 일 때
		case LEFT:
		default:
		// source의 내용을 result의 왼쪽 끝부터 복사되도록 한다.
			System.arraycopy(source, 0, result, 0, source.length);
			break;
		}
		
		return new String(result);
	}
	
}// end of class Student

// Question 3.

// [문제3] list를 다양한 기준으로 정렬하기 위해 Comaprator를 구현한 클래스를 구현하세요.
// ClassTotalComparator - 반별로 총점이 높은 순에서 낮은 순으로 정렬(반은 오름차순, 총점은 내림차순)
// ClassStudentNo - 반, 번호 순으로 내림차순 정렬
class ClassTotalComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		// 반은 오름차순으로 정렬
		
		// (반이 같다면) 총점이 높은 순에서 낮은 순으로 정렬
		if (o1.getClassNo() == o2.getClassNo()) {
			return o1.compareTo(o2);
		}
		
		
		return o1.getClassNo() - o2.getClassNo();
	}
	
}

class ClassStudentNoComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		
		// 반, 번호 순으로 내림차순 정렬
		if (o1.getClassNo() == o2.getClassNo()) {
			return o1.getStudentNo() - o2.getStudentNo();
		}
		
		
		return o1.getClassNo() - o2.getClassNo();
	}
	
}