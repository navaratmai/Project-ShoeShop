package M;

public class CustomerDB//แทนด้วย 1 rowใน database
{
	public int id;
	public String name;
	public String surname;
	public String phone;
	
	public CustomerDB()
	{
		
	}
	public CustomerDB(int xid, String xname, String xsurname, String xphone)
	{
		id = xid;
		name = xname;
		surname = xsurname;
		phone = xphone;
	}
}
