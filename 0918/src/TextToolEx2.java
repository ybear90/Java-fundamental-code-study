/*
 * [문제2] 작업 이전 상태로 되돌리는 Undo기능을 구현하세요. 예를 들어 짝수줄삭제버튼을 누른 다음에 Undo버튼을 누르면
 * TextArea의 내용이 짝수줄삭제버튼을 누르기 이전의 상태로 되돌아가야 합니다.
 */
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TextToolEx2 extends Frame implements WindowListener {

	TextArea ta;
	TextField tfParam1, tfParam2;
	Panel pNorth, pSouth;
	Label lb1, lb2;
	
	String[] btnName = {
		"Undo",     // 작업이전 상태로 되돌림
		"짝수줄삭제", // btn[0] - 짝수줄을 삭제하는 기능
	};
	
	Button[] btn = new Button[btnName.length];
	
	private final String CR_LF = System.getProperty("line.separator"); // 개행문자(줄바꿈문자)
	
	private String prevText = ""; // TextArea ta의 내용을 저장하기 위한 변수.
	
	private void registerEventHandler() {
		addWindowListener(this);
		
		btn[0].addActionListener(new ActionListener() { // Undo - 작업 이전 상태로 되돌림.
			public void actionPerformed(ActionEvent ae) {
				// 다음의 코드를 완성하시오
				// 현재 TextArea 데이터를 가져온다
				String curText = ta.getText();
				ta.setText(prevText);
				// 새로운 prevText는 UNDO 이전의 Text
				prevText = curText;
			}
		});
		
		btn[1].addActionListener(new ActionListener() {
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
	} // end of registerEventHandler()

	public static void main(String[] args) {
		TextToolEx2 win = new TextToolEx2("Text Tool");
		win.show();
	}
	
	public TextToolEx2(String title) {
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
