import java.util.Scanner;

public class SimpleQuizEx3 {

	public static void main(String[] args) {
		String[] data = {
                "다음 중 키워드가 아닌 것은?`2`final`True`if`public",
                "다음 중 자바의 연산자가 아닌 것은?`6`&`|`++`!=`/`^",
                "다음 중 메서드의 반환값이 없음을 의미하는 키워드는?`1`void`null`false",
        };
		
		// 정답갯수
		Scanner scanner = new Scanner(System.in);
		int ansCnt = 0;
		
		shuffle(data); // 문제를 섞는다.
		
		for (int i = 0; i < data.length; i++) {
			String[] dataArr = data[i].split("`", 3);
			
			String question = dataArr[0];
			String answer = dataArr[1];
			String[] choices = dataArr[2].split("`");
            
			// shuffle이 되기 때문에 단순 선택지 번호보단 선택한 답을 직접 비교
			// answer를 실제 답으로 백업
			answer = choices[Integer.parseInt(answer) - 1];
			
			// 선택지를 섞는다
			shuffle(choices);
			
			System.out.println("["+(i+1)+"] "+question);
			
            // 4.반복문을 이용해서 선택지를 출력하세요.
			for (int j = 0; j < choices.length; j++) {
				System.out.print((j+1)+"."+choices[j]+"\t");
			}
			System.out.println();

			// 사용자에게 답을 받는 부분
			System.out.print("[답]");
			String userAns = scanner.nextLine();
			
			// 실제 답하고 선택한 선택지의 답하고 문자열이 같으면 -> 정답.
			if (answer.equals(choices[Integer.parseInt(userAns) - 1]))
				ansCnt++;
			
			System.out.println();
			System.out.println();
		}
		
		System.out.println();
		System.out.println("정답개수/전체문항수 : " + ansCnt+"/"+data.length);
		
	} // main
	
	public static void shuffle(String[] data) {
		// 코드를 완성하세요.
		// 1. 배열 data의 갯수가 0보다 같거나 적으면 메서드 전체를 빠져나간다.
		if (data.length <= 0)
			return;
		// 2. 선택지 순서를 뒤바꾼다. 반복문과 Math.random() 사용.
		for (int i = 0; i < data.length; i++) {
			int randIdx = (int)(Math.random() * data.length);
			
			// swap
			String tmp = data[i];
			data[i] = data[randIdx];
			data[randIdx] = tmp;
		}
	} // shuffle()

}
