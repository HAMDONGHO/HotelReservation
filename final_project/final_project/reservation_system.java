package final_project;
import java.util.Scanner; //입력받기 위하여 scanner 클래스 import했습니다.

public class reservation_system {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		@SuppressWarnings("resource") // scan 경고를 무시하고 싶어서 넣었습니다.
		Scanner scan = new Scanner(System.in);
		User uDao = new User();
		
		System.out.println("\\n             사용자를 선택하시오");
		System.out.println("===================================================");
		System.out.println("1. 회원(client)                  ");
		System.out.println("===================================================");
		System.out.print("입력 :");
		
		int menu = scan.nextInt();
		
		switch(menu)
		{
		case 1:
		{
			uDao.login_client(); //로그인을 위함(첫 시작은 로그인으로)
			break;
		}

		}
		
	}

}
