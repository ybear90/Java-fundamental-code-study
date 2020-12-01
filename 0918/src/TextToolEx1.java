/*
 * [문제1] 짝수 줄을 삭제하는 버튼의 기능을 구현하세요.
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TextToolEx1 extends Frame implements WindowListener {

	TextArea ta;
	TextField tfParam1, tfParam2;
	Panel pNorth, pSouth;
	Label lb1, lb2;
	
	String[] btnName = {
		"짝수줄삭제", // btn[0] - 짝수줄을 삭제하는 기능
	};
	
	Button[] btn = new Button[btnName.length];
	
	private final String CR_LF = System.getProperty("line.separator"); // 개행문자(줄바꿈문자)
	
	private void registerEventHandler() {
		addWindowListener(this);
		
		btn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				// 1. TextArea ta의 텍스트를 가져온다(getText() 사용)
				String text = ta.getText();
				// 2. 작업의 결과를 저장할 StringBuffer sb를 생성한다
				StringBuffer sb = new StringBuffer();
				// 3. Scanner 클래스와 반복문을 이용해서 1에서 가져온 텍스트를 라인단위로 읽는다.
				// (Scanner클래스의 hasNextLine(), nextLine()을 사용)
				Scanner sc = new Scanner(text);
				int rowIdx = 1;
				
				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					// 4. 조건문을 사용해서 홀수줄인 경우에만 sb에 담는다.
					if (rowIdx % 2 != 0) {
						sb.append(line);
						sb.append("\n");
					}
					rowIdx++;
				}
				// 5. 작업이 끝난 후에 sb에 담긴 내용을 ta에 보여준다.(setText()사용)
				ta.setText(sb.toString());
			}
		});
	} // end of registerEventHandler()

	public static void main(String[] args) {
		TextToolEx1 win = new TextToolEx1("Text Tool");
		win.show();
	}
	
	public TextToolEx1(String title) {
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

} // end of class
