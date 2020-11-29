import java.util.*;

public class SimpleQuizEx2 {

	public static void main(String[] args) {
		String[] data = {
                "다음 중 키워드가 아닌 것은?`2`final`True`if`public",
                "다음 중 자바의 연산자가 아닌 것은?`6`&`|`++`!=`/`^",
                "다음 중 메서드의 반환값이 없음을 의미하는 키워드는?`1`void`null`false",
        };
		
		// 정답갯수
		int ansCnt = 0;
		
		for (int i = 0; i < data.length; i++) {
			String[] dataArr = data[i].split("`", 3);
			
			String question = dataArr[0];
			String answer = dataArr[1];
			String[] choices = dataArr[2].split("`");
            
			System.out.println("["+(i+1)+"]"+question);
			
            // 4.반복문을 이용해서 선택지를 출력하세요.
			for (int j = 0; j < choices.length; j++) {
				System.out.print((j+1)+"."+choices[j]+ "\t");
			}
			System.out.println();
			System.out.println(answer);
			// 사용자에게 답을 받는 부분
			Scanner scanner = new Scanner(System.in);
			System.out.print("[답]");
			String userAns = scanner.nextLine();
			
			if (userAns.equals(answer))
				ansCnt++;
			
			System.out.println();
			System.out.println();
		}
		System.out.println();
		System.out.println("정답개수/전체문항수 : " + ansCnt+"/"+data.length);
		
	}

}
