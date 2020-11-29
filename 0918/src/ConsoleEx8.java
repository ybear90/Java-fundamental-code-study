/*
 * [문제8] 문제7에서 구현한 find에 패턴을 지원하도록 기능을 확장한 find2명령어를 구현하라.
 * find는 하나의 파일에 대해서만 찾기가 가능했지만, find2는 'find2 if *.java'와 같이
 * 패턴을 이용해서 여러파일에 대한 찾기가 가능해야한다.(실행결과 참고)
 * [출처] [Java1000제] 콘솔 만들기 8 - 도스창 따라하기(find2) (남궁성의 코드초보스터디(자바 java, c언어, javascript, python) | 작성자 남궁성
 */
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class ConsoleEx8 {
	
	static String[] argArr; // 입력한 매개변수를 담기위한 문자열 배
	static LinkedList q = new LinkedList(); // 사용자가 입력한 내용을 저장할 큐(Queue)
	static final int MAX_SIZE = 5;
	
	static File curDir;
	
	static {
		try {
			curDir = new File(System.getProperty("user.dir"));
		} catch (Exception e) {}
	}
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in); // 한 번만 생성해서 재사용하면 되므로 반복문 밖으로 이동
		
		while (true) {
			try {
				String prompt = curDir.getCanonicalPath() + " >> ";
				System.out.print(prompt);
				
				// 화면으로부터 라인단위로 입력받는다.
				String input = s.nextLine();
				
				save(input);
				
				input = input.trim(); // 입력받은 값에서 불필요한 앞뒤 공백을 제거한다.
				argArr = input.split(" +");
				
				String command = argArr[0].trim();
				
				if ("".equals(command)) continue;
				
				command = command.toLowerCase(); // 명령어를 소문자로 바꾼다.
				
				if (command.equals("q")) { // q 또는 Q를 입력하면 실행종료한다.
					
					System.exit(0);
					
				} else if (command.equals("history")) {
					
					history();
					
				} else if (command.equals("dir")) {
					
					dir();
					
				} else if (command.equals("type")) {
					
					type();
					
				} else if (command.equals("find")) {
				
					find();
					
				} else if (command.equals("find2")) {
					
					find2();
					
				} else {
					
					for (int i = 0; i < argArr.length; i++) {
						
						System.out.println(argArr[i]);
						
					}

				}
				
				
			} catch (Exception e) {}
		}

	}

	public static void save(String input) {
		
		if (input == null || "".equals(input)) return;
		
		q.offer(input); // queue에 저장한다.
		
		// queue의 최대크기를 넣으면 제일 오래된 저장값을 삭제한다.
		if (((LinkedList)q).size() > MAX_SIZE) {
			
			q.remove();
			
		}
		
	}
	
	public static void history() {
		
		int i = 0;
		
		// LinkedList의 내용을 보여준다.
		LinkedList tmp = (LinkedList)q;
		ListIterator it = tmp.listIterator();
		
		while (it.hasNext()) {
			
			System.out.println(++i+"."+it.next());
			
		}
			
	}
	
	public static void dir() {
		String pattern = "";
		
		switch (argArr.length) {
		
		case 1: // dir만 입력한 경우 현재 디렉토리의 모든 파일과 디렉토리를 보여준다.
			
			for (File f : curDir.listFiles()) {
				String fileName = f.getName();
				System.out.println(f.isDirectory() ? "[" + fileName + "]" : fileName);
			}
	        
			break;
			
		case 2: // dir과 패턴을 같이 입력한 경우, 예를 들면 dir *.class
			pattern = argArr[1];
			pattern = pattern.toUpperCase(); // 패턴에서 대소문자를 구별하지 않도록 대문자로 변경한다
			// regex
			pattern = pattern.replace(".", "\\.");
			pattern = pattern.replace("*", ".*");
			pattern = pattern.replace("?", ".{1}");
			
			// regex pattern save
			Pattern pat = Pattern.compile(pattern);
			
			// 반복문을 이용해서 현재 디렉토리 중, 입력된 패턴과 일치하는 것들만 출력된다.
			// 이때, 조건문을 같이 사용해서 디렉토리(폴더)인 경우, 이름의 앞뒤에 '[' 와 ']'를 붙여서 출력한다.
			for (File f : curDir.listFiles()) {
				// 파일, 디렉토리의 이름을 얻어온다
				String fileName = f.getName();
				String upperFileName = fileName.toUpperCase();
				Matcher matcher = pat.matcher(upperFileName);
				
				// 패턴과 일치하는 것만 출력한다
				if (matcher.matches()) {
					System.out.println(f.isDirectory() ? "[" + fileName + "]" : fileName);
				}
					
			}// end for
			
			break;
			
		default:
			System.out.println("USAGE: dir [FILENAME]");
		
		}// switch
	}// dir()

	public static void type() throws IOException {
		
		if (argArr.length != 2) {
			System.out.println("Usage : type FILE_NAME");
			return;
		}
		
		String fileName = argArr[1];
		
		File tmp = new File(fileName);
		/*	 
			다음의 코드를 완성하시오
			
			1. 파일(tmp)가 존재하는지 확인하고,
				1.1. 존재하면, 파일의 내용을 화면에 출력한다(FileReader, BufferedReader를 사용)
				1.2. 존재하지 않으면, 존재하지 않는 파일이라고 출력한다.
		*/
		// 파일(tmp)가 존재하는지 확인
		if (tmp.exists()) {
			// 존재하면, 파일의 내용을 화면에 출력한다(FileReader, BufferedReader를 사용)
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			
			// line by line으로 읽으면서 파일의 내용을 출력
			String line = "";
			
			for (int i = 1; (line = br.readLine()) != null; i++) {
				
				// ";" 포함 라인을 출력한다.
				System.out.println(line);
			}
			
			br.close();
			
		} else {
			// 존재하지 않으면, 존재하지 않는 파일이라고 출력한다.
			System.out.println(fileName + " 존재하지 않는 파일입니다.");
			return;
		}
		
	}
	
	public static void find() throws IOException {
		
		if (argArr.length != 3) {
			System.out.println("USAGE: find KEYWORD FILE_NAME");
			return;
		}
		
		String keyword = argArr[1];
		String fileName = argArr[2];
		
		File tmp = new File(fileName);
		/*
		 * 다음의 코드를 완성하세오.
		 * 1. 파일(tmp)가 존재하면,
		 * 1.1 반복문을 이용해서 라인별로 읽어서 keyword가 포함되었는지 확인한다.
		 * (BufferedReader의 readLine()사용)
		 * 1.2 keyword가 포함된 라인을 발견하면, 라인번호와 함께 해당 라인을 화면에 출력한다.
		 */
		// 파일(tmp)가 존재하는지 확인
		if (tmp.exists()) {
			
			// BufferedReader를 이용하여 파일 내용을 화면을 출력한다
			// 단, 조건에 맞는 키워드가 있는 라인만 출력한다
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			// line by line
			String line = "";
			
			for (int i = 1; (line = br.readLine()) != null; i++) {
				
				if (line.indexOf(keyword) != -1)
					System.out.println(line);
			}
			
			br.close();
			
		} else {
			// 존재하지 않으면, 존재하지 않는 파일이라고 출력한다.
			System.out.println(fileName + " 존재하지 않는 파일입니다.");
			return;
		}
	}
	
	public static void find2() throws IOException {
		
		if (argArr.length != 3) {
			System.out.println("USAGE : find2 KEYWORD FILE_NAME");
			return;
		}
		
		String keyword = argArr[1];
		String pattern = argArr[2];
		
		// 1. 입력된 패턴(pattern)을 정규식 표현(Regular Expression)에 알맞게 치환한다.
		// String클래스의 String replace(CharSequence target, CharSequence replacement)를 사용하자.
		// 예를 들면, pattern = pattern.replace("A","AA")는 pattern의 "A"를 "AA"로 치환한다.
		pattern = pattern.toUpperCase();
		
		pattern = pattern.replace(".", "\\.");
		pattern = pattern.replace("*", ".*");
		pattern = pattern.replace("?", ".{1}");
		
		// regex pattern save
		Pattern pat = Pattern.compile(pattern);
		
		for (File f : curDir.listFiles()) {
			
			String fileName = f.getName();
			String upperFileName = fileName.toUpperCase();
			Matcher matcher = pat.matcher(upperFileName);
			
			// 2. 반복문을 이용해서 현재 디렉토리 중, 입력된 패턴과 일치하는 것들에 대해서,
			if (matcher.matches()) {
				System.out.println("----------------" + fileName);
				// 2.1 반복문을 이용해서 라인별로 읽어서 keyword가 포함되었는지 확인한다.
				// (BufferedReader의 readLine()사용)
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				
				// line by line
				String line = "";
				
				// 2.2 keyword가 포함된 라인을 발견하면, 라인번호와 함께 해당 라인을 화면에 출력한다.
				for (int i = 1; (line = br.readLine()) != null; i++) {
					// System.out.println("raw line: " + line);
					if (line.indexOf(keyword) != -1) {
						System.out.println(i + ":" + line);
					}
				}
			}
			
		}
		
	}
	
}
/*
 * [실행결과] - 현재 작업중인 폴더가 C:\java1000\work\Console일 경우
C:\java1000\work\Console>>find2 util *.java
----------------ConsoleEx.java
14:import java.util.*;
15:import java.util.regex.*;
----------------ConsoleEx1.java
16:import java.util.*;
----------------ConsoleEx2.java
8:import java.util.*;
----------------ConsoleEx3.java
9:import java.util.*;
----------------ConsoleEx4.java
6:import java.util.*;
----------------ConsoleEx5.java
11:import java.util.*;
12:import java.util.regex.*;
----------------ConsoleEx6.java
10:import java.util.*;
11:import java.util.regex.*;
167:import java.util.*;
----------------ConsoleEx7.java
11:import java.util.*;
12:import java.util.regex.*;
----------------ConsoleEx8.java
8:import java.util.*;
9:import java.util.regex.*;
C:\java1000\work\Console>>q
[출처] [Java1000제] 콘솔 만들기 8 - 도스창 따라하기(find2) (남궁성의 코드초보스터디(자바 java, c언어, javascript, python) | 작성자 남궁성
*/
