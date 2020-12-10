/*
 * [문제11] TextArea의 데이터를 라인별로 읽어서 param1에 입력된 형식에서 데이터를 뽑아내서 보여주는 '패턴제거'버튼을 구현하세요.
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.*; // Pattern, Matcher 클래스를 사용하기 위해 추가
import java.text.*;

public class TextToolEx11 extends Frame implements WindowListener {

	TextArea ta;
	TextField tfParam1, tfParam2;
	Panel pNorth, pSouth;
	Label lb1, lb2;
	
	String[] btnName = {
		"Undo",     // 작업이전 상태로 되돌림
		"짝수줄삭제",  // btn[0] - 짝수줄을 삭제하는 기능
		"문자삭제",   // param1에 지정된 문자들을 삭제하는 기능
		"trim",     // 라인의 앞뒤 공백을 제거
		"빈줄삭제",   // 빈 줄 삭제
		"접두사추가",  // Param1과 Param2의 문자열을 각 라인의 앞뒤에 붙이는 기능
		"substring", // Param1과 Param2에 지정된 문자열을각 라인에서 제거하는 기능
		"substring2", // Param1과 Param2에 지정된 문자열로 둘러싸인 부분을 남기고 제거하는 기능
		"distinct",  // 중복값제거한 후 정렬해서 보여주기
		"distinct2", // 중복값제거한 후 정렬해서 보여주기 - 중복카운트 포함
		"패턴적용",    // 데이터에 지정된 패턴 적용하기
		"패턴제거",    // 데이터에 적용된 패턴 제거하기
	};
	
	Button[] btn = new Button[btnName.length];
	
	private final String CR_LF = System.getProperty("line.separator"); // 개행문자(줄바꿈문자)
	
	private String prevText = ""; // TextArea ta의 내용을 저장하기 위한 변수.
	
	private void registerEventHandler() {
		addWindowListener(this);
		
		int n = 0; // 버튼순서
		
		btn[n++].addActionListener(new ActionListener() { // Undo - 작업 이전 상태로 되돌림.
			public void actionPerformed(ActionEvent ae) {
				// 다음의 코드를 완성하시오
				// 현재 TextArea 데이터를 가져온다
				// Q2A.
				String curText = ta.getText();
				
				if (!prevText.equals("")) {
					ta.setText(prevText);
				}
				
				// 새로운 prevText는 UNDO 이전의 Text
				prevText = curText;
			}
		});
		
		btn[n++].addActionListener(new ActionListener() { // 짝수줄삭제 - 짝수줄을 삭제하는 기능
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText; // prevText에 curText를 백업.
				
				Scanner sc = new Scanner(curText);
				
				for (int i = 0; sc.hasNextLine(); i++) {
					String line = sc.nextLine();
					
					if (i % 2 == 0) {
						sb.append(line).append(CR_LF);
					}
				}
				
				ta.setText(sb.toString());
			}
		});
		
		btn[n++].addActionListener(new ActionListener() { // 문자삭제 - Param1에 지정된 문자를 삭제하는 기능
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				/*
				 * 다음의 코드르 완성하시오
				 * 1. TextField Param1의 값을 가져온다.(getText() 사용)
				 * 2. 반복문을 이용해서 curText를 한글자씩 읽어서 Param1에서 가져온 문자열에 포함되어 있는지 확인
				 * 	2.1 만일 포함되어 있으면 sb에 저장하고
				 * 	2.2 만일 포함되어 있지 않으면 sb에 저장하지 않는다.
				 * 3. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.(setText() 사용) 
				 */
				
				// 1. TextField Param1의 값을 가져온다.(getText() 사용)
				String delText = tfParam1.getText();
				
				// delText가 없으면 함수 종료
				if ("".equals(delText)) return;
				
				for (int i = 0; i < curText.length(); i++) {
					char ch = curText.charAt(i);
					
					// delText에 없는 글자만 붙인다.
					if (delText.indexOf(ch) == -1)
						sb.append(ch);
				}
							
				// 3. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.(setText() 사용)
				ta.setText(sb.toString());
			}
		});
		
		btn[n++].addActionListener(new ActionListener() { // trim - 라인의 좌우공백을 제거하는 기
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				/*
				 * 다음의 코드를 완성하세요.
				 * 1. Scanner클래스와 반복문을 이용해서 curText를 라인단위로 읽는다.
				 * (Scanner클래스의 hasNextLine(), nextLine()사용)
				 * 2. 읽어온 라인의 왼쪽공백과 오른쪽 공백을 제거한다.(String 클래스의 trim()사용)
				 * 3. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.(setText() 사용)
				 */
				
				// 1. Scanner클래스와 반복문을 이용해서 curText를 라인단위로 읽는다.
				Scanner sc = new Scanner(curText);
				
				for (int i = 0; sc.hasNextLine(); i++) { // trim - 라인의 좌우공백을 제거하는 기능
					// 2. 읽어온 라인의 왼쪽공백과 오른쪽 공백을 제거한다.(String 클래스의 trim()사용)
					String line = sc.nextLine().trim();
					
					sb.append(line).append(CR_LF);
					
				}
				// 3. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.(setText() 사용)
				ta.setText(sb.toString());
			}
		});
		
		btn[n++].addActionListener(new ActionListener() { // 빈줄삭제 - 빈 줄 삭제
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				/*
				 * 다음의 코드를 완성하세요.
				 * 1. Scanner클래스와 반복문을 이용해서 curText를 라인단위로 읽는다.
				 * (Scabber쿨랴수우ㅏ hasNextLine(), nextLine()을 사용)
				 * 2. 읽어온 라인이 내용이 없는 빈 라인이면 sb에 저장하지 않는다.
				 * 3. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.
				 */
				// 1. Scanner클래스와 반복문을 이용해서 curText를 라인단위로 읽는다.
				Scanner sc = new Scanner(curText);
				
				for (int i = 0; sc.hasNextLine(); i++) {
					String line = sc.nextLine();
					
					// 2. 읽어온 라인이 내용이 없는 빈 라인이면 sb에 저장하지 않는다.
					if (line.trim().length() > 0)
						sb.append(line).append(CR_LF);
					
				}
				// 3. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.
				ta.setText(sb.toString());
			}
		});
		
		btn[n++].addActionListener(new ActionListener() { // 접두사, 접미사 - 각 라인에 접두사, 접미사 붙이기
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				/*
				 * 다음의 코드를 완성하세요.
				 * 1. param1과 param2의 값을 가져온다.(getText()사용)
				 * 2. Scanner클래스와 반복문을 이용해서 curText를 라인단위로 읽는다.
				 * (Scanner클래스의 hasNextLine(), nextLine()사용)
				 * 3. 읽어온 라인의 앞뒤에 param1과 param2의 값을 붙여서 sb에 담는다.
				 * 4. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.(setText()사용)
				 */
				// 1. param1과 param2의 값을 가져온다.(getText()사용)
				String prefix = tfParam1.getText();
				String suffix = tfParam2.getText();
				
				// 2. Scanner클래스와 반복문을 이용해서 curText를 라인단위로 읽는다.
				Scanner sc = new Scanner(curText);
				
				for (int i = 0; sc.hasNextLine(); i++) {
					// 3. 읽어온 라인의 앞뒤에 param1과 param2의 값을 붙여서 sb에 담는다.
					String line = sc.nextLine();
					
					//sb.append(prefix+line+suffix).append(CR_LF);
					sb.append(prefix);
					sb.append(line);
					sb.append(suffix);
					sb.append(CR_LF);
				}
				
				// 4. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.(setText()사용)
				ta.setText(sb.toString());
				
			}
		});
		
		btn[n++].addActionListener(new ActionListener() { // substring - 문자열 자르기
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				/*
				 * 다음의 코드를 완성하세요.
				 * 1. param1과 param2의 값을 가져온다.(getText()사용)
				 * 2. Scanner클래스와 반복문을 이용해서 curText를 라인단위로 읽는다.
				 * (Scanner클래스의 hasNextLine(), nextLine()사용)
				 * 3. 읽어온 라인을 substring으로 자른다. - param1과 param2의 내용에 관계없이 길이만큼 자른다.
				 * (param1의 문자열길이와 param2의 문자열 길이를 이용)
				 * 4. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.(setText()사용)
				 */
				
				// 1. param1과 param2의 값을 가져온다.(getText()사용)
				// String prefix = tfParam1.getText();
				// String suffix = tfParam2.getText();
				
				int from = tfParam1.getText().length();
				int to = tfParam2.getText().length();
				
				// 2. Scanner클래스와 반복문을 이용해서 curText를 라인단위로 읽는다.
				Scanner sc = new Scanner(curText);
				for (int i = 0; sc.hasNextLine(); i++) {
					// 3. 읽어온 라인의 앞뒤에 param1과 param2의 값을 붙여서 sb에 담는다.
					String line = sc.nextLine();
					
					// 유효성 검사 : 지우기엔 너무 적은 길이(접두사 또는 접미사가 붙지 않은 경우)
					if (line.length() < from + to) return;
					
					// sb.append(line.substring(prefix.length(), line.length() - suffix.length())).append(CR_LF);
					sb.append(line.substring(from, line.length() - to));
					sb.append(CR_LF);
					
				}
				
				// 4. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.(setText()사용)
				ta.setText(sb.toString());
				
			}
		});
		
		btn[n++].addActionListener(new ActionListener() { // substring2 - 지정된 문자를 찾아서 그 위치까지 잘라내기
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				String param1 = tfParam1.getText();
				String param2 = tfParam2.getText();
				
				Scanner s = new Scanner(curText);
				
				for (int i = 0; s.hasNextLine(); i++) {
					String line = s.nextLine();
					
					int from = line.indexOf(param1);
					int to = line.lastIndexOf(param2);
					
					// Highlight point
					from = (from == -1) ? 0 : from + param1.length();
					to = (to == -1) ? line.length() : to;
					
					if (from > to) return;
					
					sb.append(line.substring(from, to));
					sb.append(CR_LF);
				}
				
				ta.setText(sb.toString());
				
			}
				
		});
		
		btn[n++].addActionListener(new ActionListener() { // distinct - 중복 라인 제거
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				/*
				 *  다음의 코드를 완성하시오
				 *  1. Scanner 클래스와 반복문을 이용해서 curText를 라인단위로 읽어서 HashSet에 넣는다
				 *  2. HashSet의 내용을 ArrayList로 옮긴다음 정렬한다.(Collections의 sort() 사용)
				 *  3. 정렬된 ArrayList의 내용을 sb에 저장한다.
				 *  4. sb에 저장된 내용을 TextArea에 보여준다.
				 */
				// 1. Scanner 클래스와 반복문을 이용해서 curText를 라인단위로 읽어서 HashSet에 넣는다
				Scanner s = new Scanner(curText);
				HashSet set = new HashSet();
				
				for (int i = 0; s.hasNextLine(); i++) {
					String line = s.nextLine();
					set.add(line);
				}
				
				// 2. HashSet의 내용을 ArrayList로 옮긴다음 정렬한다.(Collections의 sort() 사용)
				ArrayList list = new ArrayList(set);
				Collections.sort(list);
				
				// 3. 정렬된 ArrayList의 내용을 sb에 저장한다.
				int size = list.size();
				
				for (int i = 0; i < size; i++) {
					sb.append(list.get(i));
					sb.append(CR_LF);
				}
				
				// 4. sb에 저장된 내용을 TextArea에 보여준다.
				ta.setText(sb.toString());
			}
		});
		
		btn[n++].addActionListener(new ActionListener() { // distinct2 - 중복 라인 제거 + 카운트
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				/*
				 *  다음의 코드를 완성하시오
				 *  
				 *  1. Scanner클래스와 반복문을 이용해서 curText를 라인단위로 읽어서 TreeMap에 담는다.
				 *  	1.1 TreeMap에 담을 때, 각 라인을 키로 저장하고 값으로는 중복횟수를 저장한다.
				 *  	1.2 TreeMap에 담을 때, 이미 같은 내용의 값이 저장되어 있는지를 확인한다.
				 *  		1.1.1 이미 같은 내용이 저장되어 있으면, 해당 키의 값을 읽어서 1 증가시키고
				 *  		1.1.2 새로운 키 값이면 1을 값으로 저장한다.
				 *  2. param1에 지정된 문자열이 있으면, 그 문자열을 키와 값의 구분자로 사용하고 없으면, ','를 구분자로 지정한다.
				 *  없으면 ','를 구분자로 지정한다.
				 *  3. Iterator를 이용해서 TreeMap에 저장된 키와 값을 구분자와 함께 sb에 저장한다.
				 *  (TreeMap을 사용했기 때문에, 자동적으로 키값을 기준으로 오름차순 정렬된다.)
				 *  4. sb에 저장된 내용을 TextArea에 보여준다.
				 */
				Scanner s = new Scanner(curText);
				TreeMap map = new TreeMap();
				
				String delimiter = tfParam1.getText();
				
				if (delimiter.length() == 0) delimiter = ",";
				
				for (int i = 0; s.hasNextLine(); i++) {
					String line = s.nextLine();
					
					if (map.containsKey(line)) {
						Integer value = (Integer) map.get(line);
						map.put(line, new Integer(value.intValue() + 1));
					} else {
						map.put(line, new Integer(1));
					}
				}
				
				Iterator it = map.entrySet().iterator();
				
				while (it.hasNext()) {
					Map.Entry entry = (Map.Entry) it.next();
					
					int value = ((Integer)entry.getValue()).intValue();
					
					sb.append(entry.getKey());
					sb.append(delimiter);
					sb.append(value);
					sb.append(CR_LF);
				}
				
				ta.setText(sb.toString());
			}
			
		});
		
		btn[n++].addActionListener(new ActionListener() { // 패턴적용
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				String pattern = tfParam1.getText();
				String delimiter = tfParam2.getText();
				
				if (delimiter.length() == 0) delimiter = ",";
				
				/*
				 * 다음의 코드를 완성하세요.
				 * 
				 * 1. Scanner 클래스와 반복문을 이용해서 curText를 라인단위로 읽는다.
				 * 2. 라인을 구분자(delimiter)로 나누어 문자열 배열에 저장한다.(String클래스의 split() 사용)
				 * 3. param1로부터 입력받은 pattern을 각 라인에 적용해 sb에 저장한다.
				 * (MessageFormat클래스의 format() 사용)
				 * 4. sb의 내용을 TextArea에 보여준다.
				 * 
				 */
				// 1. Scanner 클래스와 반복문을 이용해서 curText를 라인단위로 읽는다.
				Scanner sc = new Scanner(curText);
				
				for (int i = 0; sc.hasNextLine(); i++) {
					String line = sc.nextLine();
					
					String[] tokens = line.split(delimiter);
					
					sb.append(MessageFormat.format(pattern, tokens));
					sb.append(CR_LF);
					
				}
				// 4. sb의 내용을 TextArea에 보여준다.
				ta.setText(sb.toString());

			}
		});
		
		btn[n++].addActionListener(new ActionListener() { // 패턴제거
			public void actionPerformed(ActionEvent ae) {
				String curText = ta.getText();
				StringBuffer sb = new StringBuffer(curText.length());
				
				prevText = curText;
				
				String pattern = tfParam1.getText();
				String delimiter = tfParam2.getText();
				
				Pattern p = Pattern.compile(pattern);
				
				if (delimiter.length() == 0) delimiter = ",";
				
				/*
				 *  다음의 코드를 완성하세요.
				 *  1. Scanner클래스와 반복문을 이용해서 curText를 라인단위로 읽는다.
				 *  2. 각 라인을 pattern에 맞게 매칭시킨다.(Pattern클래스의 matcher() 사용)
				 *  3. pattern에 매칭되는 데이터를 구분자와 함께 sb에 저장한다.
				 *  4. sb의 내용을 TextArea에 저장한다.
				 */
			}
		});
	} // end of registerEventHandler()
	
	private boolean isExisted(String origin, String comp) {
		return origin.indexOf(comp) != -1;
	}
	
	private boolean isNotEmpty(String str) {
		return !("".equals(str));
	}

	public static void main(String[] args) {
		TextToolEx11 win = new TextToolEx11("Text Tool");
		win.show();
	}
	
	public TextToolEx11(String title) {
		super(title);
		lb1 = new Label("param1:", Label.RIGHT);
		lb2 = new Label("param2:", Label.RIGHT);
		tfParam1 = new TextField(15);
		tfParam2 = new TextField(15);
		
		ta = new TextArea();
		pNorth = new Panel();
		pSouth = new Panel();
		
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new Button(btnName[i]);
		}
		
		pNorth.setLayout(new FlowLayout());
		pNorth.add(lb1);
		pNorth.add(tfParam1);
		pNorth.add(lb2);
		pNorth.add(tfParam2);
		
		pSouth.setLayout(new GridLayout(2, 10));
		
		for (int i = 0; i < btn.length; i++) { // 버튼배열을 하단 Panel에 넣는다
			pSouth.add(btn[i]);
		}
		
		add(pNorth, "North");
		add(ta, "Center");
		add(pSouth, "South");
		
		setBounds(100, 100, 600, 300);
		ta.requestFocus();
		registerEventHandler();
		setVisible(true);
	} // public TextToolEx1(String title)
	

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}

}
