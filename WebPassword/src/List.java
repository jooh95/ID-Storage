import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class List implements ActionListener{        //프로그램의 주요기능을 담당하는 클래스
	private JFrame listFrame;
	private JPanel menu, centerPanel, upperMenu, tablePanel, newUpperMenu;
	private JButton insert, delete, list, deleteList, recommend, setting, insertStart;
	private JTable passwordTable;
	public Object[][] rowData;
	private String[] columnName = {"아이디", "비밀번호", "웹사이트"};
	private File passwordDB;
	private Scanner readFile;
	private InsertPage insertPage;
	private SettingPage settingPage;
	private ImageIcon imageList, imageSetting, imageRecommend, imageDeleteList;
	
	
	List(){     //변수 초기화 부분
		this.imageList = new ImageIcon("img/list.png");
		this.imageSetting = new ImageIcon("img/setting.png");
		this.imageDeleteList = new ImageIcon("img/deleteList.png");
		this.imageRecommend = new ImageIcon("img/recommend.png");
		this.tablePanel = new JPanel();
		this.menu = new JPanel();
		this.upperMenu = new JPanel();
		this.centerPanel = new JPanel();
		this.insert = new JButton("추가");
		this.delete = new JButton("삭제");
		this.list = new JButton(imageList);
		this.deleteList = new JButton(imageDeleteList);
		this.recommend = new JButton(imageRecommend);
		this.setting = new JButton(imageSetting);
		this.rowData = new Object[100][3];
		this.passwordTable = new JTable(rowData,columnName);
		this.insertStart = new JButton("추가");
		this.newUpperMenu = new JPanel();
		this.insertPage = new InsertPage();
		this.settingPage = new SettingPage();
	}
	
	public void showList(){       //GUI 구성
		listFrame = new JFrame("Web Password");
		listFrame.setSize(400, 500);
		listFrame.setLayout(new BorderLayout());
		menu.setLayout(new GridLayout(4,1));
		menu.add(list);
		menu.add(deleteList);
		menu.add(recommend);
		menu.add(setting);
		
		centerPanel.setLayout(new BorderLayout());
		upperMenu.setLayout(new GridLayout());
		upperMenu.add(insertStart);
		upperMenu.add(delete);
		
		JScrollPane scrollPane = new JScrollPane(passwordTable);
		passwordTable.setFillsViewportHeight(true);
		tablePanel.setLayout(new BorderLayout());
		tablePanel.add(passwordTable.getTableHeader(), BorderLayout.PAGE_START);
		tablePanel.add(passwordTable, BorderLayout.CENTER);
		
		centerPanel.add(upperMenu, BorderLayout.NORTH);
		centerPanel.add(tablePanel, BorderLayout.CENTER);
		
		list.addActionListener(this);
		insert.addActionListener(this);
		insertStart.addActionListener(this);
		delete.addActionListener(this);
		deleteList.addActionListener(this);
		setting.addActionListener(this);
		recommend.addActionListener(this);
		
		passwordTable.addMouseListener(new MouseAdapter() {   
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        if (me.getClickCount() == 2) {   //더블 클릭 시 웹페이지로 접속
					String url = new String("http://" + (String)rowData[row][2]);
					try {
						java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
		    }
		});
		
		
		listFrame.add(menu, BorderLayout.WEST);
		listFrame.add(centerPanel,BorderLayout.CENTER);
		listFrame.setVisible(true);
		listFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void list(){           //리스트 기능 구현
		for(int i=0; i<100; i++){
			for(int j=0; j<3; j++){
				rowData[i][j] = "";
			}
		}
		try {
			int i = 0;
			File passwordDB = new File("passwordDB.db");
			Scanner readFile = new Scanner(passwordDB);
			
			String[] token = new String[3];
			
			while(readFile.hasNext()){
				token = readFile.nextLine().split("/");
				for(int j=0; j<3; j++){
					rowData[i][j] = token[j];
				}
				i++;
			}
			
			readFile.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		passwordTable.setFillsViewportHeight(true);
		tablePanel.add(passwordTable.getTableHeader(), BorderLayout.PAGE_START);
		tablePanel.add(passwordTable, BorderLayout.CENTER);
		readFile.close();
	}
	
	private void insert(){    //추가 기능 구혀
		try {
			passwordDB = new File("passwordDB.db");
			FileWriter writer = new FileWriter(passwordDB, true);
			
			writer.write(insertPage.id.getText() + "/" + insertPage.password.getText() + "/" + insertPage.website.getText() + "\n");
			writer.close();
			
			
			list();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deleteList(){     //삭제목록 기능 구현 
		for(int i=0; i<100; i++){
			for(int j=0; j<3; j++){
				rowData[i][j] = "";
			}
		}
		try {
			int i = 0;
			File deleteDB = new File("deleteDB.db");
			readFile = new Scanner(deleteDB);
			
			String[] token = new String[3];
			
			while(readFile.hasNext()){
				token = readFile.nextLine().split("/");
				for(int j=0; j<3; j++){
					rowData[i][j] = token[j];
				}
				i++;
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		passwordTable.setFillsViewportHeight(true);
		tablePanel.add(passwordTable.getTableHeader(), BorderLayout.PAGE_START);
		tablePanel.add(passwordTable, BorderLayout.CENTER);
		readFile.close();
	}
	
	private void delete(){         //삭제 기능 구현
		int index = passwordTable.getSelectedRow();
		String deleteObject = new String();
		String tmpString = new String("");
		deleteObject = (String) rowData[index][0] + "/" + rowData[index][1] + "/" + rowData[index][2];	

		try {
			File deleteDB = new File("deleteDB.db");
			FileWriter deleteWrite = new FileWriter(deleteDB, true);
			deleteWrite.write(deleteObject + "\n");
			deleteWrite.close();
			
			File passwordDB = new File("passwordDB.db");
			File tmpDB = new File("tmpDB.db");
			FileWriter writer = new FileWriter(tmpDB);
			Scanner read = new Scanner(passwordDB);
			
			while(read.hasNext()){
				tmpString = read.nextLine();
				if(!deleteObject.equals(tmpString)){
					writer.write(tmpString + "\n");
				}
			}
			writer.close();
			read.close();
			
			tmpDB.renameTo(passwordDB);
			list();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {         //버튼 클릭에따른 함수 호출
		// TODO Auto-generated method stub
		if(e.getSource() == insert){   //추가 메뉴의 화면 출력을 위한 버튼
			upperMenu.add(delete);
			upperMenu.setVisible(true);
			newUpperMenu.setVisible(false);
			centerPanel.add(upperMenu, BorderLayout.NORTH);
			insertPage.hideInsert();
			insert();
		}
		else if(e.getSource() == insertStart){   //실질적인 값 입력을 위한 버튼
			newUpperMenu.setLayout(new GridLayout());
			newUpperMenu.add(insert);
			newUpperMenu.add(delete);
			upperMenu.setVisible(false);
			newUpperMenu.setVisible(true);
			centerPanel.add(newUpperMenu, BorderLayout.NORTH);
			insertPage.createInsertPage();
		}
		else if(e.getSource() == list){    //리스트 버튼
			list();
		}
		else if(e.getSource() == delete){   //삭제 버튼
			delete();
		}
		else if(e.getSource() == deleteList){  //삭제목록 버튼
			deleteList();
		}
		else if(e.getSource() == setting){   //환경설정 버튼
			settingPage.createSettingPage();
		}
		else if(e.getSource() == recommend){   //비민번호 추천 버튼
			//미구현
		}
	}
}
