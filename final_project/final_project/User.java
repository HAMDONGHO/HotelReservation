package final_project;
import instance.*;

import java.sql.*;
import java.util.*;

 
// �� Ŭ������ ���·ν� ������ ���� ��ü�� ������
// ���ᰴü�� ����ؼ� �����͸� 
// insert, delete, update, select.. �ϴ� ����� ��������
 
public class User {
 
    // connection��ü�� �����ؼ� ��� �����ϴ� �κ�!
    private Connection conn;
    //mySGL�� ������ ���� ID, password, URL
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ehdgh*7958";
    private static final String URL = "jdbc:mysql://localhost:3306/reservation_system?serverTimezone=UTC";

    
    public Scanner scan = new Scanner(System.in);
    
    public User() {
 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
 
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
 
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Ŭ������ ���縦 ����!!");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("mysql�� ���� ����!!");
        }
    }

    // ȸ�������� ���� ȸ���� ������ �Է��ϴ� �Լ��κ�!
    public void insertclient(client Client) {
        String sql = "insert into Client values(?,?,?,?,?);"; //������� id, name, password, passport, phone�� �޾� insert�ϴ� ������
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, Client.getUser_Id());
            pstmt.setString(2, Client.getName());
            pstmt.setString(3, Client.getpassword());
            pstmt.setString(4, Client.getpassport());
            pstmt.setString(5, Client.getphone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
 
    // ȸ������ ������ ���� �Լ� �κ�!
    public void updateClient(client Client) {
        String sql = "UPDATE Client SET name=?, password=?, passport=?, phone=? where user_id = ?;"; //update�� ���!
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Client.getName());
            pstmt.setString(2, Client.getpassword());
            pstmt.setString(3, Client.getpassport());
            pstmt.setString(4, Client.getphone());
            pstmt.setString(5, Client.getUser_Id());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    //ȸ������ ������ ���� �Լ� �κ��Դϴ�.
    public void deleteClient(String id) {
        String sql = "delete from Client where user_id = ?;"; //�Է¹��� id�� �ش��ϴ� �κ� �����ϱ� ���� ������
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    //�α����� ���� ȸ�������� �������� �Լ� �κ�!
    public client selectOne(String user_id, String user_password) {
    	
    	 String sql = "select * from Client where user_id = ? AND password = ?;"; //id�� password�� �˸��� ȸ����ü�� �ҷ����� ������
        PreparedStatement pstmt = null;
        client re = new client();
        
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user_id);
            pstmt.setString(2, user_password);
            ResultSet rs = pstmt.executeQuery();
 
            if (rs.next()) {
                re.setUser_Id(rs.getString("user_id"));
                re.setName(rs.getString("name"));
                re.setpassword(rs.getString("password"));
                re.setpassport(rs.getString("passport"));
                re.setphone(rs.getString("phone"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
        	e.printStackTrace();
        	

        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) 
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
            	e.printStackTrace();

            }
        }
        return re;
    }
 
    // ��� ���� ������ �������� �Լ��κ��Դϴ�.(user�� ���������� Ȯ���� �� �ֽ��ϴ�. �迭�� ���� toSring�Ϸ���)
    public List<Reserve> slectAllReserve(client client) {
        String sql = "select id,H_name,r_id,check_in,check_out,total from Reserve as Re left join Hotel as Ho on Re.H_id = Ho.H_id where c_id = ?;";
        //�������̺��� ȣ���� ���� ������ ���� �Է¹��� user�� id�߿��� ���� ȣ�� id�� ȣ���� id�� ���� �κп��� id�� ȣ���̸�, ����id üũ��, üũ�ƿ�, ��ü������ �������� �������Դϴ�. 
        PreparedStatement pstmt = null;
 
        List<Reserve> list = new ArrayList<Reserve>();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, client.getUser_Id());
            ResultSet re = pstmt.executeQuery();
 
            while (re.next()) {
            	  Reserve R = new Reserve();
                R.setid(re.getInt("id"));
                R.setHotel_name(re.getString("H_name"));
                R.setroom_id(re.getInt("r_id"));
                R.setcheck_in(re.getString("check_in"));
                R.setcheck_out(re.getString("check_out"));
                R.settotal(re.getFloat("total"));
                list.add(R);
            }
 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }
    
    
    // ��� ȸ���� ������ �������� �Լ��Դϴ�.(�����ڰ� ȸ�������� Ȯ���ϱ� ����!)
    public List<client> slectAll() {
        String sql = "select * from Client;";
        PreparedStatement pstmt = null;
 
        List<client> list = new ArrayList<client>();
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet re = pstmt.executeQuery();
 
            while (re.next()) {
            	  client C = new client();
                C.setUser_Id(re.getString("user_id"));
                C.setName(re.getString("name"));
                C.setpassword(re.getString("password"));
                C.setpassport(re.getString("passprot"));
                C.setpassword(re.getString("phone"));
                list.add(C);
            }
 
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return list;
    }
    
    //���� �κ�! ���並 insert�ϴ� �Լ�
    public void InsertReview( int id ,String comment) {
        String sql = "insert into Review values(?,?);";
        PreparedStatement pstmt = null;
       
        try {
            pstmt = conn.prepareStatement(sql);
        
            pstmt.setInt(1, id);
            pstmt.setString(2, comment);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed())
                    pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// ��� //////////////////////////////////////////
    
    // ȸ���� ���α���� ���� �޴�����Լ�
    private void Main_PrintMenu()
    {
		System.out.println("\n             �޴��� �����Ͻÿ�");
		System.out.println("=============================================");
		System.out.println("1. ���� 2. ��������Ȯ�� 3. ȸ������ ���� 4. ����  99. ȸ��Ż��");
		System.out.println("=============================================");
    }

    // ȸ���� ���α���� ����ϴ� �Լ�
    private void main_Cmenu(client c_client)
    {
    	reservation reserv = new reservation();
    	
    	while(true)
    	{
    		Main_PrintMenu();
    		System.out.print(" �Է� :");
    		int menu = scan.nextShort();
    		scan.nextLine();
    		
    		switch(menu)
    		{
    		case 1:
    		{
    			
    			System.out.println("\n�����ϰ��� �ϴ� ������ �Է��Ͻÿ�");
    			System.out.print("�Է� :");
    			String land = scan.nextLine();
    			reserv.Reserve_Hotel(land, c_client);
    			break;
    		}
    		case 2://����� ������ ����ϱ� ���� �κ�!
    		{
    			
    			
    			List<Reserve> Re_list = slectAllReserve(c_client); 
				System.out.print("\n===========================================================================================\n");
				System.out.print("    id               ȣ���̸�                           ȣ��                              check_in                  check_out               total    \n");
			 
				for(int i=0; i < Re_list.size(); i++)
				{
					System.out.print(Re_list.get(i));
					System.out.print("\n");
				}
				System.out.print("\n===========================================================================================\n");	
    			
				System.out.print("\n ���並 ����ðڽ��ϱ�? \n");	
				System.out.print("\n 1.��         2. �ƴϿ� \n");
				int re_V = scan.nextInt();
				
				if( re_V == 1)
				{
					System.out.print("\n ���並 ����� ���̵� �Է����ּ��� \n");	
					System.out.print("�Է� : ");
					int id = scan.nextInt();
					scan.nextLine();
					
					System.out.print("\n ���並 50�� ���� �����ּ���! \n");	
					System.out.print("�Է� : ");
					String comment = scan.nextLine();
					InsertReview(id,comment);
				}
    			
    			break;
    		}
    		case 3: //ȸ������ ����
    		{
    			c_client.Print_UInfo();
    			
    			
    			String id = c_client.getUser_Id();
    			System.out.print("\nȸ�������� �����Ͻÿ�\n");
    			System.out.print("\n�̸� :");
    			String name = scan.nextLine();
    			System.out.print("\n��й�ȣ :");
    			String password = scan.nextLine();
    			System.out.print("\n���ǹ�ȣ :");
    			String passport = scan.nextLine();
    			System.out.print("\n��ȭ��ȣ :");
    			String phone = scan.nextLine();
    			
    			c_client.setclient(id,name,password,passport,phone);
    			updateClient(c_client);
    			break;
    		}
    		case 4:
    			System.exit(0);
    			break;
    			
    		case 99:
    			deleteClient(c_client.getUser_Id());
    			return;
    		}
    	}
    }
    
 
    // �α����� ���� �޴�����Լ�
	private void login_PrintMenu()
    {
		System.out.println("\n\n ȯ���մϴ�. ȸ������ �α��� �� ȸ�� ������ �Ͻñ� �ٶ��ϴ�.");
		System.out.println("==================================================");
		System.out.println("1. login                  2. ȸ������");
		System.out.println("==================================================");
    }
	
	// �α����� ����� ����ϴ� �Լ�
    public void login_client()
    {
    	client c_client = new client(); 
    	
    	while(true) {
    	login_PrintMenu();
    	System.out.print(" �Է� :");
    	int menu = scan.nextInt();
    	scan.nextLine();
    	switch(menu)
    	{
    	
    	// �α���
    	case 1:
    		System.out.println("\n�α����� ���Ͽ� ���̵�� ��й�ȣ�� �Է��Ͻÿ�");
    		System.out.println("\n���̵� :");
    		String c_id = scan.nextLine();
    		System.out.println("\n��й�ȣ :");
    		String c_Ps = scan.nextLine();
    		
    		c_client = selectOne(c_id, c_Ps); //�α����� ���� �κ�(�Է¹��� id�� �н����� �Է�)
    		
    		if(c_client.getUser_Id() != null) 
    			main_Cmenu(c_client);
    		else
    			System.out.print("���̵� ��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �Է��Ͻñ�ٶ��ϴ�.");
			break;
		
			
			
		// ȸ������
    	case 2:
			System.out.print("\nȸ�������� �Է��Ͻÿ�\n");
			System.out.print("\n���̵� :");
			String user_id = scan.nextLine();
			System.out.print("\n�̸� :");
			String name = scan.nextLine();
			System.out.print("\n��й�ȣ :");
			String password = scan.nextLine();
			System.out.print("\n���ǹ�ȣ :");
			String passport = scan.nextLine();
			System.out.print("\n��ȭ��ȣ :");
			String phone = scan.nextLine();
			client New_join = new client(user_id,name,password,passport,phone);
    		insertclient(New_join);
    	}
    	}
    	
    }
}