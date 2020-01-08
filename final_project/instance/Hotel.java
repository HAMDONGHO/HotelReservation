package instance;

public class Hotel {

	int id;
	String name;
	String address;
	String phone;
	float b_price;
	room H_room;
	
	public Hotel(int id , String name, String address, String phone, float b_price, room room)
	{
		this.id = id;
		this.name = name;
		this.address = address; 
		this.phone = phone;
		this.H_room = new room(room);
	}
	public Hotel()
	{}
	
	public int getid()
	{
		return id;
	}
	public void setid(int id)
	{
		this.id = id;
	}
	public String getname()
	{
		return name;
	}
	public void setname(String name)
	{
		this.name = name;
	}
	public String getaddress()
	{
		return address;
	}
	public void setaddress(String address)
	{
		this.address = address;
	}

	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public float getb_price()
	{
		return b_price;
	}
	public void setb_price(float b_price)
	{
		this.b_price = b_price;
	}
	
	public room getroom()
	{
		return H_room;
	}
	public void setroom(room room)
	{
		this.H_room = room;
	}
	
	public String toString()
	{
		return " *id = " + id + ", name = " + name + ", address = "+ address + ", phone = " + phone + " *";
	}
}
