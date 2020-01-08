package instance;

public class Review {
	int id;
	String comment;
	
	public Review()
	{
	}
	public Review(int id, String comment)
	{
		this.id = id;
		this.comment = comment;
	}
	public int getid()
	{
		return id;
	}
	public void setid(int id)
	{
		this.id = id;
	}	
	public String getcomment()
	{
		return comment;
	}
	public void setcomment(String comment)
	{
		this.comment = comment;
	}
}
