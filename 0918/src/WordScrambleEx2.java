import java.util.*;

class WordScrambleEx2 {
      public static void main(String[] args) {
            String[] strArr = { "CHANGE", "LOVE", "HOPE", "VIEW"};

            String answer = getAnswer(strArr);
            String question = getScrambledWord(answer);

            while (true) {
                  System.out.println("Question :" + question);
                  System.out.print("Your answer is :"); 
                  

                  // 1. 화면을 통해 사용자의 입력을 받는다.(Scanner클래스 사용)
                  Scanner scanner = new Scanner(System.in);
                  String myAnswer = scanner.nextLine();
                  
                  // 2. 사용자가 q 또는 Q를 입력하면 프로그램을 종료한다.
                  if (myAnswer.equals("q") || myAnswer.equals("Q"))
                	  System.exit(0);

                  // 3. 사용자가 정답을 맞출때까지 반복하다.
                  //     사용자가 정답을 맞추면, while문을 빠져나간다.
                  if (myAnswer.equals(answer)) {
                	  System.out.println("정답입니다.");
                	  break;
                  }
                  
                  System.out.println(myAnswer+"은/는 정답이 아닙니다. 다시 시도해보세요.");
                  
                  
            } // while

      } // main

      public static String getAnswer(String[] strArr) {
            int idx = (int)(Math.random()*strArr.length);
            return strArr[idx];
      }
     
      public static String getScrambledWord(String str) {
            char[] chArr = str.toCharArray();

            for(int i=0;i<str.length();i++) {
                   int idx = (int)(Math.random()*str.length());
                 
                  char tmp = chArr[i];
                  chArr[i] = chArr[idx];
                  chArr[idx] = tmp;
            }

            return new String(chArr);
      } // scramble(String str)
}
