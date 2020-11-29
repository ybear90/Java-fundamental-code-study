import java.util.Scanner;

class ConsoleEx2 {
      public static void main(String[] args) {
            
    	  while(true) {
	          String prompt = ">> ";
	          System.out.print(prompt);
	          
	          // 다음과 같은 내용의 코드를 넣으세요.                  
	
	          // 1. 화면으로부터 라인단위로 입력받는다.  - java.util.Scanner클래스 사용
	          Scanner scanner = new Scanner(System.in);
	          String userInput = scanner.nextLine();
	          
	          // 다음의 코드를 완성하세요.

              // 1. 입력받은 값에서 앞뒤 공백을 제거한다. (String클래스의 trim()사용)
	          userInput = userInput.trim();
              // 2. 입력받은 명령라인의 내용을 공백을 구분자로 해서 나눠서 argArr에 담는다.
              // String클래스의 split(String regex)를 사용 - 공백이 하나 이상인 경우에도 하나의 공백으로 간주해야함
	          String[] argArr = userInput.split(" +");
	          
	          // 2. q 또는 Q를 입력하면 실행종료한다.
	          if (userInput.equalsIgnoreCase("q")) {
	        	  System.exit(0);
	          } else {
	        	  for (int i = 0; i < argArr.length; i++) {
	        		  System.out.println(argArr[i]);
	        	  }
	          }
	        	  

          } // while(true)
      } // main
} // class
