package final_project;
import java.util.Scanner; //�Է¹ޱ� ���Ͽ� scanner Ŭ���� import�߽��ϴ�.

public class reservation_system {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		@SuppressWarnings("resource") // scan ����� �����ϰ� �; �־����ϴ�.
		Scanner scan = new Scanner(System.in);
		User uDao = new User();
		
		System.out.println("\\n             ����ڸ� �����Ͻÿ�");
		System.out.println("===================================================");
		System.out.println("1. ȸ��(client)                  ");
		System.out.println("===================================================");
		System.out.print("�Է� :");
		
		int menu = scan.nextInt();
		
		switch(menu)
		{
		case 1:
		{
			uDao.login_client(); //�α����� ����(ù ������ �α�������)
			break;
		}

		}
		
	}

}