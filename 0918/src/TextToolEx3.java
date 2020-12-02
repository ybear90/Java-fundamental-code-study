import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TextToolEx3 extends Frame implements WindowListener {

	TextArea ta;
	TextField tfParam1, tfParam2;
	Panel pNorth, pSouth;
	Label lb1, lb2;
	
	String[] btnName = {
		"Undo",     // 작업이전 상태로 되돌림
		"짝수줄삭제", // btn[0] - 짝수줄을 삭제하는 기능
		"문자삭제"   // param1에 지정된 문자들을 삭제하는 기능
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
				String tfParam1Str = tfParam1.getText();
				Scanner sc = new Scanner(curText);
				// 2. 반복문을 이용해서 curText를 한글자씩 읽어서 Param1에서 가져온 문자열에 포함되어 있는지 확인
				for (int i = 0; sc.hasNextLine(); i++) {
					String line = sc.nextLine();
					
					for (char letter : line.toCharArray()) {
						// 2.1 만일 미포함되어 있으면 sb에 저장하고
						if (tfParam1Str.indexOf(letter) == -1) {
							sb.append(letter);
						}
						// 2.2 만일 포함되어 있지 않으면 sb에 저장하지 않는다.
					}
					sb.append(CR_LF);
				}
				// 3. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.(setText() 사용)
				ta.setText(sb.toString());
			}
		});
	} // end of registerEventHandler()

	public static void main(String[] args) {
		TextToolEx3 win = new TextToolEx3("Text Tool");
		win.show();
	}
	
	public TextToolEx3(String title) {
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