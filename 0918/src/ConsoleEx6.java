/*
 *  [문제6] 지정된 파일의 내용을 보여주는 type명령을 구현하라. type명령의 형식은 'type FILE_NAME'이며,
 *  FILE_NAME으로 지정된 파일을 찾아서 그 내용을 화면에 보여줘야한다.
 *  단, FILE_NAME은 현재 디렉토리에 포함된 파일이어야 하며, 해당 파일이 존재하지 않으면
 *  존재하지 않는 파일이라고 화면에 출력한다.
 *  [출처] [Java1000제] 콘솔 만들기 6 - 도스창 따라하기(type) (남궁성의 코드초보스터디(자바 java, c언어, javascript, python) | 작성자 남궁성
 */
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class ConsoleEx6 {
	
	static String[] argArr;	// 입력한 매개변수를 담기위한 문자열 배열
	static LinkedList q = new LinkedList(); // 사용자가 입력한 내용을 저장할 큐(Queue)
	static final int MAX_SIZE = 5; // Queue에 최대 5개까지만 저장되도록 한다.
	
	static File curDir; // 현재 디렉토리
	
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
					
				} else {
					
					for (int i = 0; i < argArr.length; i++) {
						
						System.out.println(argArr[i]);
						
					}

				}
				
				
			} catch (Exception e) {
				
			}
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
	
}// class
