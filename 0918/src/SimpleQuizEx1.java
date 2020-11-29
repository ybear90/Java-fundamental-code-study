
public class SimpleQuizEx1 {

	public static void main(String[] args) {
		String[] data = {
                "다음 중 키워드가 아닌 것은?`2`final`True`if`public",
                "다음 중 자바의 연산자가 아닌 것은?`5`&`|`++`!=`/`^",
                "다음 중 메서드의 반환값이 없음을 의미하는 키워드는?`1`void`null`false",
        };
		
		for (int i = 0; i < data.length; i++) {
            // 1. String클래스의 String[] split(String regex, int limit)를 사용해서 문제와 선택지를 나누세요.
			String[] dataArr = data[i].split("`", 3);
            // 2. 문제를 출력하세요.
			System.out.println("["+(i+1)+"]"+dataArr[0]);

			// 3. 선택지를 나누기 위해 String[] split(String regex)를 사용하세요.
			String[] ansArr = dataArr[2].split("`", 4);
			
            // 4.반복문을 이용해서 선택지를 출력하세요.
			for (int j = 0; j < ansArr.length; j++) {
				System.out.print((j+1)+"."+ansArr[j]+ " ");
			}
			System.out.println("\n");
		}
		
	}

}
