import java.util.*;
/*
 * [문제4] 실행결과를 잘 보고, 다음의 예제의 getHint메서드를 완성하세요.
 * String getHint(String answer, char[] hint) - 문제의 답의 일부를 보여주는 메서드
 * 한번 틀릴 때마다 한글자씩 더 보여준다.
 */
public class WordScrambleEx4 {
	
    public static void main(String[] args) {
    	
        String[] strArr = { "CHANGE", "LOVE", "HOPE", "VIEW"};
        
        String answer;
        String question;
        
        Scanner scanner = new Scanner(System.in); // 한 번 생성해 놓고 재사용 하면 되므로 밖으로 이동
        
        // 코드를 넣어 완성하세요.

        // hint : while문을 중첩해서(2개의 while문) 작성하세요.
        while (true) {
        	// 1. 문제를 반복해서 가져온다.
        	// 같은 문제를 가져오더라도 Scramble 결과가 다를 수 있기 때문에 다른 문제가 될 수 있다.
        	// (이전 풀이처럼 처음부터 문제에 제시되지 않은 제한 조건을 달아 시작부터 복잡하게 생각할 필요가 없다.)
        	answer = getAnswer(strArr);
        	question = getScrambledWord(answer);
        	char[] hint = new char[answer.length()]; // 문제에 대한 힌트를 주기 위한 배열 정의.
        	
        	// hint를 '_'로 초기화 한다. 정답이 LOVE라면 hint는 "____"이 된다.
            for (int i = 0; i < hint.length; i++) {
                  hint[i] = '_';
            }
 	
        	// 2. 문제를 맞출 때 까지 반복적으로 물어본다.
        	// 맞추면 다음 문제를 가져오고
        	// 맞추지 못하면 기존 문제를 다시 물어본다.
        	while (true) {
        		
        		System.out.println("Question :" + question);
                System.out.print("Your answer is :"); 
                

                // 2-1. 화면을 통해 사용자의 입력을 받는다.(Scanner클래스 사용)
                String myAnswer = scanner.nextLine();
                
                // 2-2. 사용자가 q 또는 Q를 입력하면 프로그램을 종료한다.
                if (myAnswer.equalsIgnoreCase("q"))
                	System.exit(0);

                // 2-3. 사용자가 정답을 맞출때까지 반복한다.
                // 정답을 맞추면 다음 문제를 얻어오고
                if (myAnswer.equalsIgnoreCase(answer)) {
                	System.out.println("정답입니다.");
                	break; // 나가서 다음 문제로
                }
                // 정답을 맞추지 못하면 다시 돌아가서 재질문 한다
                System.out.println(myAnswer+"은/는 정답이 아닙니다. 다시 시도해보세요.");
                System.out.println("Hint:"+getHint(answer, hint));

        	} // inner while
        } // outer while

    } // main
    
    public static String getAnswer(String[] strArr) {
        int idx = (int)(Math.random()*strArr.length);
        return strArr[idx];
    }
 
    public static String getScrambledWord(String str) {
        char[] chArr = str.toCharArray();

        for (int i = 0; i < str.length(); i++) {
        	int idx = (int)(Math.random()*str.length());
        	
        	char tmp = chArr[i];
        	chArr[i] = chArr[idx];
        	chArr[idx] = tmp;
        }

        return new String(chArr);
    } // scramble(String str)
    
    public static String getHint(String answer, char[] hint) {
    	
    	int count = 0; // 힌트에 포함된 '_'의 개수
    	char[] ansArr = answer.toCharArray(); // answer의 데이터를 조작하기 위해 char형 배열 정의.
    	
    	// 1. 반복문을 이용해서 hint에 포함된 '_'의 개수를 센다.
    	for (int i = 0; i < hint.length; i++) {
    		if (hint[i] == '_')
    			count++;
    	}
    	
    	// 2. count의 값이 2보다 클 때만 정답의 한 글자를 hint에 넣는다.
		// 정답을 다 알려주는 상황이 되지 않게 하기 위함.
    	// [주의] 반드시 이전 힌트 보다 한글자를 더 보여줘야함.
    	// 예를 들어 정답이 "LOVE"이고 이전 힌트가 "L___"이었다면
    	// 그 다음 힌트는 "L__E"또는 "L_V_" 와 같은 식이어야 한다.
    	// Math.random()을 사용해서 정답의 한 글자를 골라서 힌트에 넣으면 된다.
    	if (count > 2) {
    		// 2-1. random 한 index 위치 선정
    		// 해당 위치에 힌트 글자가 열려 있을 수 있으므로 loop를 돌면서 글자 전환이 실제 일어날 위치를 찾을 때 까지 반복.
    		while (true) {
    			// hint 글자를 바꿀 위치 선정.
    			int randIdx = (int)(Math.random() * ansArr.length);
        		
        		// 그 위치의 hint 글자를 실제 답 배열 글자로 전환.
    			// 해당 글자가 '_'아니라면 바꿈 아니면 다른 위치를 다시 찾는다.
    			if (hint[randIdx] == '_') {
    				hint[randIdx] = ansArr[randIdx];
    				break;
    			}
    		}
    		
    	}
    	
    	// hint array를 String으로 바꿔 리턴.
    	return String.valueOf(hint);
    } // getHint()    
}