import java.util.*;
/*
 * [문제3] 문제2의 예제를 변경해서, 문제를 맞추더라도 프로그램이 종료되지 않고 다음문제를 보여주도록 하세요.
 */
class WordScrambleEx3 {
      public static void main(String[] args) {
            String[] strArr = { "CHANGE", "LOVE", "HOPE", "VIEW"}; 
            
            String answer;
            String question;
            String[] ansArr = new String[strArr.length]; // 맞춘 문제를 저장하는 문자열 배열
            
            int correctNum = 0; // 맞춘 문제 체크
            
            // hint : while문을 중첩해서(2개의 while문) 작성하세요.
            while (true) {
            	
            	  // (다음) 문제를 가져온다. (중복이 안될 때 까지 반복적으로)
	          	  while (true) {
	          		  answer = getAnswer(strArr);
	          		  
	          		  // 예비 답을 가져온 것을 맞춘 정답 리스트와 비교한다
	          		  // 중복이면 다시 가져오고 
	          		  if (isDuplicated(answer, ansArr))
	          			  continue;
	          		  
	          		  // 중복이 아니라면 문제를 만든다.
	          		  question = getScrambledWord(answer); 
	          		  
	          		  break;
	          	  }
	          	  
                  System.out.println("Question :" + question);
                  System.out.print("Your answer is :"); 
                  

                  // 1. 화면을 통해 사용자의 입력을 받는다.(Scanner클래스 사용)
                  Scanner scanner = new Scanner(System.in);
                  String myAnswer = scanner.nextLine();
                  
                  // 2. 사용자가 q 또는 Q를 입력하면 프로그램을 종료한다.
                  if (myAnswer.equalsIgnoreCase("q"))
                	  System.exit(0);
                  
                  // 3. 사용자가 정답을 맞출때까지 반복하다.
                  // 사용자가 정답을 맞추면, 그 다음 문제를 제시한다.
                  if (myAnswer.equalsIgnoreCase(answer)) {
                	  System.out.println("정답입니다.");
                	  
                	  // 다음 문제를 제시, 이전 문제와 겹쳐서는 안된다.
                	  // 맞춘 문제를 백업한다.
                	  ansArr[correctNum++] = myAnswer;
                	  
                  } else {
                	  // 사용자가 문제를 맞추지 못하면 다시 물어본다.
                	  System.out.println(myAnswer+"은/는 정답이 아닙니다. 다시 시도해보세요.");
                	  continue;
                  }
                  
                  // 문제를 모두 맞췄다면 반복을 마친다.
                  if (correctNum == strArr.length)
                	  break;
                  
            } // while
            System.out.println("모든 정답을 맞추셨습니다. 축하합니다!!");
      } // main
      
      public static boolean isDuplicated(String answer, String[] ansArr) {
    	  for (int i = 0; i < ansArr.length; i++) {
    		  if (answer.equalsIgnoreCase(ansArr[i]))
    			  return true;
    	  }
    	  return false;
      }

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