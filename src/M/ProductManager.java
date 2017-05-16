package M;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import java.util.*;
import java.sql.PreparedStatement;

import javax.imageio.ImageIO;

//import com.mysql.jdbc.PreparedStatement;

import common.GlobalData;

public class ProductManager
{
	public static ArrayList<ProductDB> getAllProduct()
	{
		ArrayList<ProductDB> list = new ArrayList<ProductDB>();

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
			String query = "SELECT * FROM products";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next())
			{
				int id = rs.getInt("product_id");
				String pName = rs.getString("product_name");
				double price = rs.getDouble("price_per_unit");
				String dresc = rs.getString("product_description");
				byte[] img_byte =  rs.getBytes("product_image"); //เก็บรูปภาพเป็นแบบอาเรย์ byte
				BufferedImage bufferedimg = null;
				if(img_byte == null || img_byte.length == 0)
				{
					
				}
				else
				{
					
					ByteArrayInputStream bais = new ByteArrayInputStream(img_byte);
					bufferedimg = ImageIO.read(bais);
					bais.close();
				}
				ProductDB cc = new ProductDB(id,pName,price,dresc,bufferedimg);
				list.add(cc);
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	
	}
	
	public static void saveProduct(ProductDB x)
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
		
			
			String query = "INSERT INTO products VALUES (?,  ?, ? , ? , ? )";//เครื่องหมาย 2 ขีดเป็นของจาวา 1 ขีดเป็นของคำสั่ง sql
			//เครื่องหมาย ? เอามา set ด้านล่าง
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, 0);
			st.setString(2, x.product_name);
			st.setDouble(3, x.price_per_unit);
			st.setString(4, x.product_description);
			
			if(x.product_image != null)
			{
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				ImageIO.write(x.product_image, "png", outStream);
				byte[] buffer = outStream.toByteArray();
				st.setBytes(5, buffer);
				outStream.close();
			}
			else
			{
				byte[] buffer = new byte[0];
				st.setBytes(5, buffer);
			}
			st.executeUpdate();//เปลี่ยนจากเดิม query เป็น update
			
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static void editProduct(ProductDB x)
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
		
			
			String query = "UPDATE products SET product_name = '"+x.product_name
					+"', price_per_unit = '"+x.price_per_unit
					+"', product_description   = '"+x.product_description
					+"', product_image   = '"+x.product_image
					+"'WHERE product_id  = " + x.product_id + "";//เครื่องหมาย 2 ขีดเป็นของจาวา 1 ขีดเป็นของคำสั่ง sql
	
			Statement st = conn.createStatement();

			st.executeUpdate(query);//เปลี่ยนจากเดิม query เป็น update
			
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static void deleteProduct(ProductDB x)
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
		
			
			String query = "DELETE FROM products WHERE product_id  = " + x.product_id + "";
	
			Statement st = conn.createStatement();

			st.executeUpdate(query);//เปลี่ยนจากเดิม query เป็น update
			
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public static ArrayList<ProductDB> searchProduct(String s)
	{
		ArrayList<ProductDB> list = new ArrayList<ProductDB>();

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
			String query = "SELECT * FROM products WHERE product_name LIKE '"+s+"' OR product_description LIKE '"+s+"' ";

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next())
			{
				int id = rs.getInt("product_id");
				String pName = rs.getString("product_name");
				double price = rs.getDouble("price_per_unit");
				String dresc = rs.getString("product_description");
				byte[] img_byte =  rs.getBytes("product_image"); //เก็บรูปภาพเป็นแบบอาเรย์ byte
				BufferedImage bufferedimg = null;
				if(img_byte == null || img_byte.length == 0)
				{
					
				}
				else
				{
					
					ByteArrayInputStream bais = new ByteArrayInputStream(img_byte);
					bufferedimg = ImageIO.read(bais);
					bais.close();
				}
				ProductDB cc = new ProductDB(id,pName,price,dresc,bufferedimg);
				list.add(cc);
			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;
	
	}
}
