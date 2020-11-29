import java.util.*;

class ConsoleEx1 {
      public static void main(String[] args) {
            
    	  while(true) {
	          String prompt = ">> ";
	          System.out.print(prompt);
	          
	          // 다음과 같은 내용의 코드를 넣으세요.                  
	
	          // 1. 화면으로부터 라인단위로 입력받는다.  - java.util.Scanner클래스 사용
	          Scanner scanner = new Scanner(System.in);
	          // 2. q 또는 Q를 입력하면 실행종료한다.
	          String userInput = scanner.nextLine();
	          
	          if (userInput.equalsIgnoreCase("q"))
	        	  break;

          } // while(true)
      } // main
} // class
