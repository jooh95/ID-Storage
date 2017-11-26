import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private static File userDB;
	private static Scanner read;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StartPage startPage = new StartPage();
		MainPage mainPage = new MainPage();
		if(fileConsist()){                       //����� ���� �ִٸ� ���� �������� ���ٸ� ���� �������� �����
			mainPage.createMainPage();
		}else{
			startPage.createStartPage();
		}
	}
	 
	private static boolean fileConsist(){      //����� ������ �����ϴ� ���� �Ǵ���
		try {
			userDB = new File("userDB.db");       
			read = new Scanner(userDB);
			return true;
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			return false;
			//e1.printStackTrace();
		}
	}

}
