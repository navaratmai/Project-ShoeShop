package M;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.GlobalData;

public class InvoiceManager
{
	public static void saveInvoice(CustomerDB cust, ArrayList<InvoiceDetail> details)
	{
		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME+"?useUnicode=true&characterEncoding=utf-8";
			Class.forName(myDriver);
			
			Connection conn = DriverManager.getConnection(myUrl , GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);//สร้างคอนเนทชั่น
/////////////////////1. insert invoice
			String query = "INSERT INTO invoice VALUES (0, NOW() , '"+ cust.id +"', 'NORMAL')";//เครื่องหมาย 2 ขีดเป็นของจาวา 1 ขีดเป็นของคำสั่ง sql
			Statement st = conn.createStatement();
			st.executeUpdate(query);//เปลี่ยนจากเดิม query เป็น update
////////////////////2. get max ว่าที่ insert เข้าไปเมื่อกี้ คืออะไร		
			query = "SELECT MAX(invoice_id) FROM invoice";
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			int id_max = 0;
			while (rs.next())
			{
				id_max = rs.getInt(1);
			}
///////////////////3. มา insert invoice_detail อีกครั้งนึง	
			for(int i = 0 ; i < details.size(); i++)
			{
				query = "INSERT INTO invoice_detail VALUES (0, '"+id_max+"', '"
						+details.get(i).product.product_id+"','"+details.get(i).qty+"')";
				st = conn.createStatement();
				st.executeUpdate(query);
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
}
