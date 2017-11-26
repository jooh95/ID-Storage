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

public class SettingPage implements ActionListener{    //ȯ�漳�� Ŭ�� �� �Է��� ���� Ŭ����
	private JFrame frame;
	private JButton firstConform, secondConform, summitHint;
	private JLabel message, firstMessage, secondMessage, hintMessage;
	private JTextField hint;
	private JPanel bottomPanel;
	private JPasswordField firstPassword, secondPassword;
	private File userDB;
	
	SettingPage(){             //���� �ʱ�ȭ
		this.message = new JLabel(" ");
		this.firstMessage = new JLabel("��й�ȣ �缳��");
		this.secondMessage = new JLabel("��й�ȣ Ȯ��");
		this.hintMessage = new JLabel("��Ʈ �缳��");
		this.hint = new JTextField();
		this.bottomPanel = new JPanel();
		this.firstPassword = new JPasswordField();
		this.secondPassword = new JPasswordField();
		this.firstConform = new JButton("Ȯ��");
		this.secondConform = new JButton("Ȯ��");
		this.summitHint = new JButton("Ȯ��");
	}
	
	
	public void createSettingPage(){      //GUI ����
		frame = new JFrame("ȯ�漳��");
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
	public void actionPerformed(ActionEvent e) {  //��ư Ŭ���� ���� ó��
		// TODO Auto-generated method stub
		if(e.getSource() == firstConform){   //ù��° Ȯ�� ��ư
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
		else if(e.getSource() == secondConform){   //�ι�° Ȯ�� ��ư
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
				
				frame.setVisible(false);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
