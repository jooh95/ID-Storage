import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InsertPage{         //�߰� ��ư Ŭ�� �� �Է� ȭ���� ���� Ŭ����
	private JFrame frame;
	public JTextField id, password, website;
	public JLabel idLabel, passwordLabel, websiteLabel;
	public boolean firstClick;
	
	InsertPage(){    //���� �ʱ�ȭ
		id = new JTextField(15);
		password = new JTextField(15);
		website = new JTextField(15);
		idLabel = new JLabel(" �ƾƵ�: ");
		passwordLabel = new JLabel("��й�ȣ:");
		websiteLabel = new JLabel("������Ʈ:");
		firstClick = true;
	}
	
	public void createInsertPage(){      //GUI�� �����
		frame = new JFrame("�߰�");
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
	
	public void hideInsert(){   //GUI ����
		frame.setVisible(false);
	}
}
