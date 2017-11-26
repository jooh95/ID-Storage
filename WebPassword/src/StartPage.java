import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class StartPage implements ActionListener{
	private JFrame frame;
	private ImageIcon image;
	private JButton icon, firstConform, secondConform, summitHint;
	private JLabel message, firstMessage, secondMessage, hintMessage;
	private JTextField hint;
	private JPanel bottomPanel;
	private JPasswordField firstPassword, secondPassword;
	private File userDB;
	private MainPage mainPage;
	
	StartPage(){         //���� ������ ���� �ʱ�ȭ
		this.image = new ImageIcon("img/icon.png"); 
		this.icon = new JButton(image);
		this.message = new JLabel(" ");
		this.firstMessage = new JLabel("��й�ȣ ����");
		this.secondMessage = new JLabel("��й�ȣ Ȯ��");
		this.hintMessage = new JLabel("��Ʈ ����");
		this.hint = new JTextField();
		this.bottomPanel = new JPanel();
		this.firstPassword = new JPasswordField();
		this.secondPassword = new JPasswordField();
		this.firstConform = new JButton("Ȯ��");
		this.secondConform = new JButton("Ȯ��");
		this.summitHint = new JButton("Ȯ��");
		this.mainPage = new MainPage();
	}
	
	
	public void createStartPage(){      //�ʱ� ȭ�� ����
		frame = new JFrame("WebPassword");
		frame.setSize(400,500);
		frame.setLayout(new BorderLayout());
		
		frame.add(message,BorderLayout.NORTH);
		frame.add(icon,BorderLayout.CENTER);
		
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
	public void actionPerformed(ActionEvent e) {        //��ư Ŭ���� ���� �Լ� ȣ��
		// TODO Auto-generated method stub
		if(e.getSource() == firstConform){        //ù��° Ȯ�� ��ư
			firstMessage.setVisible(false);
			firstPassword.setVisible(false);
			firstConform.setVisible(false);
			bottomPanel.add(secondMessage, BorderLayout.WEST);
			bottomPanel.add(secondPassword,BorderLayout.CENTER);
			bottomPanel.add(secondConform,BorderLayout.EAST);
		}
		else if(e.getSource() == secondConform){   //�ι�° Ȯ�� ��ư
			if(firstPassword.getText().toString().equals(secondPassword.getText().toString())){
				secondMessage.setVisible(false);
				secondPassword.setVisible(false);
				secondConform.setVisible(false);
				bottomPanel.add(hintMessage, BorderLayout.WEST);
				bottomPanel.add(hint,BorderLayout.CENTER);
				bottomPanel.add(summitHint,BorderLayout.EAST);
				message.setText(" ");
			}else{
				message.setText("��й�ȣ�� Ʋ���ϴ�. �ٽ� �Է��ϼ���.");
			}
			
		}
		else if(e.getSource() == summitHint){   //������ Ȯ�� ��ư
			try {
				userDB = new File("userDB.db");
				FileWriter writer = new FileWriter(userDB);
				String tmpDB = new String();
				tmpDB = firstPassword.getText().toString() + '\n' + hint.getText(); 
				writer.write(tmpDB);
				writer.close();
				
				mainPage.createMainPage();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
