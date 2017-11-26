import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InsertPage{         //추가 버튼 클릭 시 입력 화면을 위한 클래스
	private JFrame frame;
	public JTextField id, password, website;
	public JLabel idLabel, passwordLabel, websiteLabel;
	public boolean firstClick;
	
	InsertPage(){    //변수 초기화
		id = new JTextField(15);
		password = new JTextField(15);
		website = new JTextField(15);
		idLabel = new JLabel(" 아아디: ");
		passwordLabel = new JLabel("비밀번호:");
		websiteLabel = new JLabel("웹사이트:");
		firstClick = true;
	}
	
	public void createInsertPage(){      //GUI를 띄어줌
		frame = new JFrame("추가");
		frame.setSize(290, 130);
		frame.setLocation(400,0);
		frame.setLayout(new FlowLayout());
		frame.add(idLabel);
		frame.add(id);
		frame.add(passwordLabel);
		frame.add(password);
		frame.add(websiteLabel);
		frame.add(website);

		frame.setVisible(true);
	}
	
	public void hideInsert(){   //GUI 가림
		frame.setVisible(false);
	}
}
