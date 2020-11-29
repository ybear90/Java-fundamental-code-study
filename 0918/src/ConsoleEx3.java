import java.util.*;
/*
 * 사용자가 입력한 명령라인의 내용을 저장하는 save(String input)메서드와 사용자가 입력한
 * 명령라인의 이력을 보여주는 history()메서드를 완성하세요.
 */
class ConsoleEx3 {
	static String[] argArr; // 입력한 매개변수를 담기위한 문자열 배열
	static LinkedList q = new LinkedList(); // 사용자가 입력한 내용을 저장핳 큐(Queue)
	static final int MAX_SIZE = 5; // q(큐)에 저장될 수 있는 값의 갯수.
	
	public static void main(String[] args) {
	  Scanner scanner = new Scanner(System.in); // 한번만 생성해서 재사용하면 되므로 반복문 밖으로 이동.
	  
	  while(true) {
          String prompt = ">> ";
          System.out.print(prompt);
          
          // 다음과 같은 내용의 코드를 넣으세요.                  

          // 1. 화면으로부터 라인단위로 입력받는다.  - java.util.Scanner클래스 사용
         
          String userInput = scanner.nextLine();
          
          save(userInput);
          
          // 다음의 코드를 완성하세요.

          // 1. 입력받은 값에서 앞뒤 공백을 제거한다. (String클래스의 trim()사용)
          userInput = userInput.trim();
          // 2. 입력받은 명령라인의 내용을 공백을 구분자로 해서 나눠서 argArr에 담는다.
          // String클래스의 split(String regex)를 사용 - 공백이 하나 이상인 경우에도 하나의 공백으로 간주해야함
          argArr = userInput.split(" +");
          
          String command = argArr[0].trim();
          
          // 명령어가 빈문자열이면 저장하지 않는다
          if ("".equals(command)) continue;
          
          command = command.toLowerCase(); // 명령어를 소문자로 바꾼다.
          
          // 2. q 또는 Q를 입력하면 실행종료한다.
          if (command.equals("q")) { // q 또는 Q를 입력하면 실행종료한다.
        	  System.exit(0);
          } else if (command.equals("history")) {
        	  // equalsIgnoreCase 대신 equals 사용.
        	  // history 명령어가 queue에 포함이 되지 않는 문제 발생
        	  history();
          } else {
        	  for (int i = 0; i < argArr.length; i++) {
        		  System.out.println(argArr[i]);
        	  }
          }
        	  

      } // while(true)
    } // main
	
	public static void save(String input) {
		
		if (input == null || "".equals(input)) return;
		
		// 다음의 코드를 완성하세요.
		// 1. queue에 저장한다.
		// push -> 앞에서 부터 넣는다, add -> 뒤에서 부터 넣는다.
		q.add(input);
		// 2. queue의 최대크기(MAX_SIZE)를 넣으면 제일 오래된 저장값을 삭제한다.
		if (q.size() > MAX_SIZE)
			q.remove(0);
	}
	
	public static void history() {
		int i = 0;
		
		// 다음의 코드를 완성하세요.
		// 1. LinkedList에 저장된 내용(최근 MAX_SIZE개의 명령어)를 보여준다.
		for (i = 0; i < q.size(); i++) {
			System.out.println((i+1)+"."+q.get(i));
		}
	}
	
} // class
