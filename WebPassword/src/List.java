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

public class List implements ActionListener{        //���α׷��� �ֿ����� ����ϴ� Ŭ����
	private JFrame listFrame;
	private JPanel menu, centerPanel, upperMenu, tablePanel, newUpperMenu;
	private JButton insert, delete, list, deleteList, recommend, setting, insertStart;
	private JTable passwordTable;
	public Object[][] rowData;
	private String[] columnName = {"���̵�", "��й�ȣ", "������Ʈ"};
	private File passwordDB;
	private Scanner readFile;
	private InsertPage insertPage;
	private SettingPage settingPage;
	private ImageIcon imageList, imageSetting, imageRecommend, imageDeleteList;
	
	
	List(){     //���� �ʱ�ȭ �κ�
		this.imageList = new ImageIcon("img/list.png");
		this.imageSetting = new ImageIcon("img/setting.png");
		this.imageDeleteList = new ImageIcon("img/deleteList.png");
		this.imageRecommend = new ImageIcon("img/recommend.png");
		this.tablePanel = new JPanel();
		this.menu = new JPanel();
		this.upperMenu = new JPanel();
		this.centerPanel = new JPanel();
		this.insert = new JButton("�߰�");
		this.delete = new JButton("����");
		this.list = new JButton(imageList);
		this.deleteList = new JButton(imageDeleteList);
		this.recommend = new JButton(imageRecommend);
		this.setting = new JButton(imageSetting);
		this.rowData = new Object[100][3];
		this.passwordTable = new JTable(rowData,columnName);
		this.insertStart = new JButton("�߰�");
		this.newUpperMenu = new JPanel();
		this.insertPage = new InsertPage();
		this.settingPage = new SettingPage();
	}
	
	public void showList(){       //GUI ����
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
		        if (me.getClickCount() == 2) {   //���� Ŭ�� �� ���������� ����
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
	
	private void list(){           //����Ʈ ��� ����
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
	
	private void insert(){    //�߰� ��� ����
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
	
	private void deleteList(){     //������� ��� ���� 
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
	
	private void delete(){         //���� ��� ����
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
	public void actionPerformed(ActionEvent e) {         //��ư Ŭ�������� �Լ� ȣ��
		// TODO Auto-generated method stub
		if(e.getSource() == insert){   //�߰� �޴��� ȭ�� ����� ���� ��ư
			upperMenu.add(delete);
			upperMenu.setVisible(true);
			newUpperMenu.setVisible(false);
			centerPanel.add(upperMenu, BorderLayout.NORTH);
			insertPage.hideInsert();
			insert();
		}
		else if(e.getSource() == insertStart){   //�������� �� �Է��� ���� ��ư
			newUpperMenu.setLayout(new GridLayout());
			newUpperMenu.add(insert);
			newUpperMenu.add(delete);
			upperMenu.setVisible(false);
			newUpperMenu.setVisible(true);
			centerPanel.add(newUpperMenu, BorderLayout.NORTH);
			insertPage.createInsertPage();
		}
		else if(e.getSource() == list){    //����Ʈ ��ư
			list();
		}
		else if(e.getSource() == delete){   //���� ��ư
			delete();
		}
		else if(e.getSource() == deleteList){  //������� ��ư
			deleteList();
		}
		else if(e.getSource() == setting){   //ȯ�漳�� ��ư
			settingPage.createSettingPage();
		}
		else if(e.getSource() == recommend){   //��ι�ȣ ��õ ��ư
			//�̱���
		}
	}
}
