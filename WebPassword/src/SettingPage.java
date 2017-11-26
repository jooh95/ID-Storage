import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SettingPage implements ActionListener{    //환경설정 클릭 시 입력을 위한 클래스
	private JFrame frame;
	private JButton firstConform, secondConform, summitHint;
	private JLabel message, firstMessage, secondMessage, hintMessage;
	private JTextField hint;
	private JPanel bottomPanel;
	private JPasswordField firstPassword, secondPassword;
	private File userDB;
	
	SettingPage(){             //변수 초기화
		this.message = new JLabel(" ");
		this.firstMessage = new JLabel("비밀번호 재설정");
		this.secondMessage = new JLabel("비밀번호 확인");
		this.hintMessage = new JLabel("힌트 재설정");
		this.hint = new JTextField();
		this.bottomPanel = new JPanel();
		this.firstPassword = new JPasswordField();
		this.secondPassword = new JPasswordField();
		this.firstConform = new JButton("확인");
		this.secondConform = new JButton("확인");
		this.summitHint = new JButton("확인");
	}
	
	
	public void createSettingPage(){      //GUI 구성
		frame = new JFrame("환경설정");
		frame.setSize(400,70);
		frame.setLocation(400,0);
		frame.setLayout(new BorderLayout());
		
		firstMessage.setVisible(true);
		firstPassword.setVisible(true);
		firstConform.setVisible(true);
		secondMessage.setVisible(false);
		secondPassword.setVisible(false);
		secondConform.setVisible(false);
		hint.setVisible(false);
		hintMessage.setVisible(false);
		summitHint.setVisible(false);
		
		
		frame.add(message,BorderLayout.NORTH);
		
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(firstMessage, BorderLayout.WEST);
		bottomPanel.add(firstPassword,BorderLayout.CENTER);
		bottomPanel.add(firstConform,BorderLayout.EAST);
		
		firstConform.addActionListener(this);
		secondConform.addActionListener(this);
		summitHint.addActionListener(this);
		
		frame.add(bottomPanel,BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	@Override
	public void actionPerformed(ActionEvent e) {  //버튼 클릭에 따른 처리
		// TODO Auto-generated method stub
		if(e.getSource() == firstConform){   //첫번째 확인 버튼
			firstMessage.setVisible(false);
			firstPassword.setVisible(false);
			firstConform.setVisible(false);
			secondMessage.setVisible(true);
			secondPassword.setVisible(true);
			secondConform.setVisible(true);

			bottomPanel.add(secondMessage, BorderLayout.WEST);
			bottomPanel.add(secondPassword,BorderLayout.CENTER);
			bottomPanel.add(secondConform,BorderLayout.EAST);
		}
		else if(e.getSource() == secondConform){   //두번째 확인 버튼
			if(firstPassword.getText().toString().equals(secondPassword.getText().toString())){
				secondMessage.setVisible(false);
				secondPassword.setVisible(false);
				secondConform.setVisible(false);
				hintMessage.setVisible(true);
				hint.setVisible(true);
				summitHint.setVisible(true);
				
				bottomPanel.add(hintMessage, BorderLayout.WEST);
				bottomPanel.add(hint,BorderLayout.CENTER);
				bottomPanel.add(summitHint,BorderLayout.EAST);
				message.setText(" ");
			}else{
				message.setText("비밀번호가 틀립니다. 다시 입력하세요.");
			}
			
		}
		else if(e.getSource() == summitHint){   //마지막 확인 버튼
			try {
				userDB = new File("userDB.db");
				FileWriter writer = new FileWriter(userDB);
				String tmpDB = new String();
				tmpDB = firstPassword.getText().toString() + '\n' + hint.getText(); 
				writer.write(tmpDB);
				writer.close();
				
				frame.setVisible(false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
