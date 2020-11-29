import java.util.Scanner;

/*
 * [문제3] 문제2의 예제를 변경해서, 문제를 맞추더라도 프로그램이 종료되지 않고 다음문제를 보여주도록 하세요.
 */
public class WordScrambleEx3_re {
    public static void main(String[] args) {
        String[] strArr = { "CHANGE", "LOVE", "HOPE", "VIEW"};
        
        String answer;
        String question;
        
        // 코드를 넣어 완성하세요.

        // hint : while문을 중첩해서(2개의 while문) 작성하세요.
        while (true) {
        	// 1. 문제를 반복해서 가져온다.
        	// 같은 문제를 가져오더라도 Scramble 결과가 다를 수 있기 때문에 다른 문제가 될 수 있다.
        	// 이전 풀이처럼 처음부터 문제에 제시되지 않은 제한 조건을 달아 시작부터 복잡하게 생각할 필요가 없다
        	answer = getAnswer(strArr);
        	question = getScrambledWord(answer);
        	        	
        	// 2. 문제를 맞출 때 까지 반복적으로 물어본다.
        	// 맞추면 다음 문제를 가져오고
        	// 맞추지 못하면 기존 문제를 다시 물어본다.
        	while (true) {
        		
        		System.out.println("Question :" + question);
                System.out.print("Your answer is :"); 
                

                // 2-1. 화면을 통해 사용자의 입력을 받는다.(Scanner클래스 사용)
                Scanner scanner = new Scanner(System.in);
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

        	}
        }

    } // main
    
    public static String getAnswer(String[] strArr) {
        int idx = (int)(Math.random()*strArr.length);
        return strArr[idx];
    }
 
    public static String getScrambledWord(String str) {
        char[] chArr = str.toCharArray();

        for(int i=0;i < str.length();i++) {
        	int idx = (int)(Math.random()*str.length());
        	
        	char tmp = chArr[i];
        	chArr[i] = chArr[idx];
        	chArr[idx] = tmp;
        }

        return new String(chArr);
    } // scramble(String str)
    
}
