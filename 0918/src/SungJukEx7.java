/*
 * [문제7] 파일(score_data.txt)로 부터 데이터를 읽어서 list에 저장한 다음에 전교등수와 반등수를 계산해서 화면에 출력하라.
 */
import java.io.File;
import java.util.*;


public class SungJukEx7 {

	public static void main(String[] args) {
		ArrayList<Student> list = new ArrayList<Student>();
		
		Scanner sc = null;
		
		try {
			sc = new Scanner(new File(args[0]));
		} catch(Exception e) {
			System.out.println(args[0] + "-지정하신 파일을 찾을 수 없습니다.");
			System.exit(0);
		}
		
		/*
        다음의 코드를 완성하세요.
        1. 파일로부터 데이터를 라인별로 읽어서(반복문과 Scanner클래스의 nextLine()사용)
		1.1 읽어온 라인을 다시 Scanner를 이용해서 ','를 구분자로 해서 나눈다.
		(Scanner클래스의 useDelimiter(String pattern)사용)
		1.2 나누어진 데이터로 Student객체를 생성해서 list에 담는다.
	    */
		
		// 1. 파일로부터 데이터를 라인별로 읽어서(반복문과 Scanner클래스의 nextLine()사용)
		while (sc.hasNextLine()) {
			// 1.1 읽어온 라인을 다시 Scanner를 이용해서 ','를 구분자로 해서 나눈다.
			String line = sc.nextLine();
			Scanner sc2 = new Scanner(line).useDelimiter(",");
			
			System.out.println("line: " + line);
		
			// 여기서부터 에러가 걸린다 왜 걸리는지 모르겠다.
			// 리스트에 라인 데이터 넣기
			list.add(new Student(sc2.nextLine(), 
					sc2.nextInt(), 
					sc2.nextInt(), 
					sc2.nextInt(), 
					sc2.nextInt(), 
					sc2.nextInt()));
		}
	
		
		
		calculateSchoolRank(list); // 전교등수를 계산한다.
        calculateClassRank(list);  // 반등수를 계산한다.
        printList(list);

	}
	
	public static void printList(List<Student> list) {
        System.out.println("이름     반  번호  국어  수학  영어   총점  전교등수  반등수");
        System.out.println("=============================================================");

        for(Student s : list) {
        	System.out.println(s);
        }

        System.out.println("=============================================================");
	}
	
	public static void calculateSchoolRank(List<Student> list) {
        Collections.sort(list);

        int prevRank = -1;
        int prevTotal = -1;
        int length = list.size();

        for(int i=0;i < length; i++) {
			Student s = list.get(i);
			
			if(s.getTotalScore()==prevTotal) {
				s.setClassRank(prevRank);
			} else {	 
				s.setClassRank(i + 1);
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
