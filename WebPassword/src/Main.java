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
		if(fileConsist()){                       //사용자 정보 있다면 시작 페이지로 없다면 메인 페이지를 띄어줌
			mainPage.createMainPage();
		}else{
			startPage.createStartPage();
		}
	}
	 
	private static boolean fileConsist(){      //사용자 정보가 존재하는 지를 판단함
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
