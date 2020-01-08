package instance;

public class client {
	
	
	private String user_id;
    private String name;
    private String password;
    private String passport;
    private String phone;
    
    public client(String  user_id, String name, String password, String passport, String phone) {
 
    	 this.user_id = user_id;
        this.name = name;
        this.password = password;
        this.passport = passport;
        this.phone = phone;
    }
    
    public client(){};
    
    public client(client client)
    {
   	 this.user_id = client.getUser_Id();
     this.name = client.getName();
     this.password = client.getpassword();
     this.passport = client.getpassport();
     this.phone = client.getphone();
    };
    

    public String getUser_Id() {
        return user_id;
    }
    public void setUser_Id(String user_id) {
        this.user_id = user_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getpassword() {
        return password;
    }
    public void setpassword(String password) {
        this.password = password;
    }
    public String getpassport() {
        return passport;
    }
    public void setpassport(String passport) {
        this.passport = passport;
    }
    public String getphone() {
        return phone;
    }
    public void setphone(String phone) {
        this.phone = phone;
    }
    
    public void setclient(String id, String name, String password, String passport, String phone) {
      	 this.user_id = id;
         this.name = name;
         this.password = password;
         this.passport = passport;
         this.phone = phone;
 	}

    
    public void Print_UInfo()
    {
    	System.out.printf("id : %s ", user_id);
    	System.out.printf("¿Ã∏ß : %s  ",name);
    	System.out.printf("password : %s  ", password);
    	System.out.printf("passport : %s  ", passport);
    	System.out.printf("phone : %s  ", phone);
    	
    }
    

}
