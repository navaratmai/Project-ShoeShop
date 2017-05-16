package M;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;

import common.GlobalData;

public class CompanyInfoManager
{
	public static CompanyInfoDB getCompanyinfo()
	{
		try
		{
			// create our mysql database connection
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_NAME+"?useUnicode=true&characterEncoding=utf-8";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of
			// using "*"
			String query = "SELECT * FROM company_info";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next())
			{
				CompanyInfoDB cc = new CompanyInfoDB();
				cc.id = rs.getInt("id");//ในฟันหนูต้องชื่อเหมือนใน ดาต้าเบสนะ
				cc.company_name = rs.getString("company_name");
				cc.addressno = rs.getString("addressno");
				cc.road = rs.getString("road");
				cc.kwang = rs.getString("kwang");
				cc.ket = rs.getString("ket");
				cc.province = rs.getString("province");
				cc.zipcode = rs.getString("zipcode");
				cc.phone = rs.getString("phone");
				cc.email = rs.getString("email");
				
				return cc;
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public static void edit(CompanyInfoDB x)
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
		
			
			String query = "UPDATE company_info SET company_name = '"+x.company_name
					+"', addressno = '"+x.addressno
					+"', road    	= '"+x.road
					+"', kwang   	= '"+x.kwang
					+"', ket   	 	= '"+x.ket
					+"', province   = '"+x.province
					+"', zipcode  	= '"+x.zipcode
					+"', phone   	= '"+x.phone
					+"', email   	= '"+x.email
					+"'WHERE id  	= " + x.id + "";//เครื่องหมาย 2 ขีดเป็นของจาวา 1 ขีดเป็นของคำสั่ง sql
	
			Statement st = conn.createStatement();

			st.executeUpdate(query);//เปลี่ยนจากเดิม query เป็น update
			
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
}
