/*
 * [문제6] TextArea의 각 라인의 앞에는 param1에 입력된 문자열을, 뒤에는 param2에 입력된 문자열을 제거 기능의
 * substring'버튼을 구현하세요.
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TextToolEx6 extends Frame implements WindowListener {

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
				String prefix = tfParam1.getText();
				String suffix = tfParam2.getText();
				
				// 2. Scanner클래스와 반복문을 이용해서 curText를 라인단위로 읽는다.
				Scanner sc = new Scanner(curText);
				for (int i = 0; sc.hasNextLine(); i++) {
					// 3. 읽어온 라인의 앞뒤에 param1과 param2의 값을 붙여서 sb에 담는다.
					String line = sc.nextLine();
					
					sb.append(line.substring(prefix.length(), line.length() - suffix.length())).append(CR_LF);
					
				}
				
				// 4. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.(setText()사용)
				ta.setText(sb.toString());
				
			}
		});
	} // end of registerEventHandler()

	public static void main(String[] args) {
		TextToolEx6 win = new TextToolEx6("Text Tool");
		win.show();
	}
	
	public TextToolEx6(String title) {
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
