import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class MainPage implements ActionListener{
	private JFrame frame;
	private JButton icon, logIn;
	private JPasswordField password;
	private JPanel bottomPanel;
	private JLabel message;
	private ImageIcon imageIcon, loginImage;
	private List list;
	private File userDB;
	private Scanner readFile, readHint;
	private JButton hint;
	private JPanel upperPanel;
	
	MainPage(){            //메인 페이지에 사용되는 변수 초기화
		this.imageIcon = new ImageIcon("img/icon.png");
		this.loginImage = new ImageIcon("img/login.png");
		this.icon = new JButton(imageIcon);
		this.password = new JPasswordField(250);
		this.bottomPanel = new JPanel();
		this.message = new JLabel("     ");
		this.logIn = new JButton(loginImage);
		this.hint = new JButton("힌트 보기");
		this.list = new List();
		this.upperPanel = new JPanel();
	}
	
	
	public void createMainPage(){       //메인 페이지 구성
		frame = new JFrame("WebPassword");
		frame.setSize(400,500);
		frame.setLayout(new BorderLayout());
		
		
		upperPanel.setLayout(new BorderLayout());
		upperPanel.add(message,BorderLayout.CENTER);
		upperPanel.add(hint, BorderLayout.EAST);
		frame.add(upperPanel,BorderLayout.NORTH);
		
		frame.add(icon,BorderLayout.CENTER);
		
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(password,BorderLayout.CENTER);
		bottomPanel.add(logIn,BorderLayout.EAST);
		frame.add(bottomPanel,BorderLayout.SOUTH);
		
		logIn.addActionListener(this);
		hint.addActionListener(this);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private boolean loadDB(){          //사용자 정보를 불러옴
		try {
			userDB = new File("userDB.db");
			readFile = new Scanner(userDB);
			
			String tmpPassword = readFile.nextLine();
			if(password.getText().equals(tmpPassword)){
				return true;
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			return false;
			//e1.printStackTrace();
		}
		return false;
	}

	private void showHint(){       //설정한 힌트를 보여줌
		try {
			userDB = new File("userDB.db");
			readHint = new Scanner(userDB);
			
			readHint.nextLine();
			message.setText(readHint.nextLine());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {       //버튼에 클릭에 따른 함수 호출
		// TODO Auto-generated method stub
		if(e.getSource() == logIn){         //로그인 버튼
			if(loadDB()){
				frame.setVisible(false);
				list.showList();	
			}else{
				message.setText("비밀번호가 틀립니다.");
			}
		}
		else if(e.getSource() == hint){   //힌트보기 버튼
			showHint();
		}
	}
}
