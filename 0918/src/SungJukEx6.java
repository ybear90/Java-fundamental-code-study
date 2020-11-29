
/*
 * [문제6] 데이터를 적절한 크기에 맞춰 정렬하는 format메서드를 완성하고,  이 메서드를 이용해서
 * 화면에 데이터를 실행결과와 같이 줄맞춰 출력하세요.
 * String format(String str, int length, int alignment) - 주어진 문자열(str)을 지정된 길이(length)에 맞게
 * 정렬(alignment)한다.
 * 예를 들어 format("이름", 6, CENTER)의 결과는 "  이름  "이 된다.
 */
import java.util.*;

public class SungJukEx6 {

	public static void main(String[] args) {
		ArrayList<Student> list = new ArrayList<Student>();
		
		// 이름, 반 번호, 국어, 수학, 영어
		list.add(new Student("남궁성", 3, 2, 100, 100, 100));
		list.add(new Student("왕자바", 3, 1, 90, 100, 80));
		list.add(new Student("자바왕", 3, 3, 70, 100, 100));
		list.add(new Student("킹왕짱", 1, 2, 100, 60, 90));
		list.add(new Student("자바짱", 1, 1, 100, 100, 100));
		list.add(new Student("최고수", 1, 3, 100, 80, 60));
		list.add(new Student("홍길동", 2, 1, 50, 80, 100));
		list.add(new Student("일지매", 2, 3, 70, 80, 100));
		list.add(new Student("변강쇠", 2, 4, 80, 80, 85));
		list.add(new Student("이원구", 2, 2, 90, 90, 90));
		
		calculateSchoolRank(list); // 전교등수를 계산한다.
		calculateClassRank(list); // 반 등수를 계산한다.
		printList(list);
	}
	
	public static void printList(List<Student> list) {
		System.out.println("이름\t반\t번호\t국어\t수학\t영어\t총점\t전교등수\t반등수");
		System.out.println("================================================================");
		
		for (Student s : list) {
			System.out.println(s);
		}
		
		System.out.println("================================================================");
		
	}
	
	// 남궁성 강사님 모범 답안을 기준으로 내 클래스 사정에 맞게 변경되어 있음.
	public static void calculateSchoolRank(List<Student> list) {
		Collections.sort(list);
		
		int prevRank = -1;
		int prevTotal = -1;
		int length = list.size();
		
		for (int i = 0; i < length; i++) {
			Student s = list.get(i);
			
			if (s.getTotalScore() == prevTotal) {
				s.setSchoolRank(prevRank);
			} else {
				s.setSchoolRank(i+1);
			}
			
			prevRank = s.getSchoolRank();
			prevTotal = s.getTotalScore();
		}
	} // public static void calculateSchoolRank(List<Student> list)
	
	public static void calculateClassRank(List<Student> list) {
		Collections.sort(list, new ClassTotalComparator());
		
		int prevClassNo = -1;
		int prevRank = -1;
		int prevTotal = -1;
		int length = list.size();
		
		for (int i = 0, n = 0; i < length; i++, n++) {
			Student s = list.get(i);
			
			if (s.getClassNo() == prevClassNo) {
				prevRank = -1;
				prevTotal = -1;
				n = 0;
			}
			
			if (s.getTotalScore() == prevTotal) {
				s.setClassRank(prevRank);
			} else {
				s.setClassRank(n + 1);
			}
			prevClassNo = s.getClassNo();
			prevRank = s.getClassRank();
			prevTotal = s.getTotalScore();
		}
		
	} // public static void calculateClassRank(List<Student> list)

}
