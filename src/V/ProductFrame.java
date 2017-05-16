package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Buffer;

import M.CustomerDB;
import M.CustomerManager;
import M.ProductDB;
import M.ProductManager;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductFrame extends JFrame
{

	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_price_per_unit;
	private JTextField textField_description;
	private ImagePanel imagePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName());
					ProductFrame frame = new ProductFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductFrame()
	{
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 820, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 0, 407, 438);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) //ให้คลิกที่ตารางแล้วมาแสดงที่ช่องด้านข้าง
			{
				if(table.getSelectedRowCount() < 1)
				{
					return;
				}
				int index = table.getSelectedRow();
				int product_id = 			Integer.parseInt(table.getValueAt(index, 0).toString());
				String product_name = 		table.getValueAt(index, 1).toString();
				double price_per_unit = 	Double.parseDouble(table.getValueAt(index, 2).toString());
				String product_dest = 		table.getValueAt(index, 3).toString();
				BufferedImage img = list.get(index).product_image;
				if(img != null)
				{
					imagePanel.setImage(img); 
				}
				
				textField_id.setText("" + product_id);
				textField_name.setText("" + product_name);
				textField_price_per_unit.setText("" + price_per_unit);
				textField_description.setText("" + product_dest);
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblProductId = new JLabel("product id");
		lblProductId.setBounds(439, 11, 77, 14);
		contentPane.add(lblProductId);
		
		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setColumns(10);
		textField_id.setBackground(Color.YELLOW);
		textField_id.setBounds(582, 11, 105, 20);
		contentPane.add(textField_id);
		
		JLabel lblProductName = new JLabel("product name");
		lblProductName.setBounds(439, 46, 77, 14);
		contentPane.add(lblProductName);
		
		textField_name = new JTextField();
		textField_name.setColumns(10);
		textField_name.setBounds(582, 46, 105, 20);
		contentPane.add(textField_name);
		
		JLabel lblPricePerUnit = new JLabel("price per unit");
		lblPricePerUnit.setBounds(439, 77, 77, 14);
		contentPane.add(lblPricePerUnit);
		
		textField_price_per_unit = new JTextField();
		textField_price_per_unit.setColumns(10);
		textField_price_per_unit.setBounds(582, 77, 105, 20);
		contentPane.add(textField_price_per_unit);
		
		JLabel lblProductDest = new JLabel("product dest.");
		lblProductDest.setBounds(439, 115, 77, 14);
		contentPane.add(lblProductDest);
		
		textField_description = new JTextField();
		textField_description.setColumns(10);
		textField_description.setBounds(582, 115, 105, 20);
		contentPane.add(textField_description);
		
		JButton button_save = new JButton("SAVE NEW");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//วิธีเช็คว่าใช้ double ไหม
				if(!textField_price_per_unit.getText().trim().matches("[-+]?\\d*\\.?\\d+"));
				{
					JOptionPane.showMessageDialog(ProductFrame.this, "Number only Please !!! ");
					textField_price_per_unit.requestFocus();
					textField_price_per_unit.selectAll();
				}
				ProductDB x = new ProductDB();
				x.product_id = 0;
				x.product_name = textField_name.getText().trim();
				x.price_per_unit =Double.parseDouble(textField_price_per_unit.getText().trim());
				//ควรจะเช็คว่าผู้ใช้ใส่อะไรประหลาดมาไหมสามารถเปลี่ยนเป็น double ได้ไหม วิธีเช็ค เซิร์ฟใน google ว่า isnumberic java
				x.product_description = textField_description.getText().trim();
				x.product_image = (BufferedImage)imagePanel.getImage();
				
				ProductManager.saveProduct(x);
				load();
				textField_id.setText("");
				textField_name.setText("");
				textField_price_per_unit.setText("");
				textField_description.setText("");
				
				JOptionPane.showMessageDialog(ProductFrame.this, "finish!!");
				
			}
		});
		button_save.setBounds(421, 395, 111, 23);
		contentPane.add(button_save);
		
		JButton button_edit = new JButton("EDIT");
		button_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ProductDB x = new ProductDB(
						Integer.parseInt(textField_id.getText()), 
						textField_name.getText().trim(),
						Double.parseDouble(textField_price_per_unit.getText().trim()),
						textField_description.getText().trim(),
						(BufferedImage)imagePanel.getImage());
				ProductManager.editProduct(x);
				load();
				textField_id.setText("");
				textField_name.setText("");
				textField_price_per_unit.setText("");
				textField_description.setText("");
				
				JOptionPane.showMessageDialog(ProductFrame.this, "finish!!");
			}
		});
		button_edit.setBounds(542, 395, 111, 23);
		contentPane.add(button_edit);
		
		JButton button_delete = new JButton("DELETE");
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(
						ProductFrame.this,"Do you want to delete ?",
						"DELETE ?", JOptionPane.OK_CANCEL_OPTION))
				{
				ProductDB x = new ProductDB(
						Integer.parseInt(textField_id.getText()), 
						textField_name.getText().trim(),
						Double.parseDouble(textField_price_per_unit.getText().trim()),
						textField_description.getText().trim(),
						(BufferedImage)imagePanel.getImage());
				ProductManager.deleteProduct(x);
				load();
				textField_id.setText("");
				textField_name.setText("");
				textField_price_per_unit.setText("");
				textField_description.setText("");
				
				JOptionPane.showMessageDialog(ProductFrame.this, "finish!!");
				}
			}
		});
		button_delete.setBounds(663, 395, 111, 23);
		contentPane.add(button_delete);
		
		imagePanel = new ImagePanel();
		imagePanel.setBounds(476, 180, 241, 193);
		contentPane.add(imagePanel);
		
		JButton btnNewButton = new JButton("Browse img");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();//ให้เลือกไฟล์ได้
				
				fc.addChoosableFileFilter(new OpenFileFilter("jpeg","Photo in JPEG format") );//ให้เลือกได้แต่ไฟล์รูปอย่างเดียว
				fc.addChoosableFileFilter(new OpenFileFilter("jpg","Photo in JPEG format") );
				fc.addChoosableFileFilter(new OpenFileFilter("png","PNG image") );
				fc.addChoosableFileFilter(new OpenFileFilter("svg","Scalable Vector Graphic") );
				fc.setAcceptAllFileFilterUsed(false);//ให้แสดงแต่ไฟล์ชนิดที่เราเลือกอย่างเดียว
				int returnVal = fc.showOpenDialog(ProductFrame.this);//ให้เลือกไฟล์ได้
				if(returnVal == JFileChooser.APPROVE_OPTION)
				{
					System.out.println("x");
					File f = fc.getSelectedFile();
					try
					{
						BufferedImage bimg = ImageIO.read(f);
						imagePanel.setImage(bimg);
					} 
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(542, 146, 105, 23);
		contentPane.add(btnNewButton);
		
		
		load();
	}
	ArrayList<ProductDB> list;
	private JTable table;
	public void load()
	{
		list = ProductManager.getAllProduct();
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("product_id");
		model.addColumn("product_name");
		model.addColumn("price_per_unit");
		model.addColumn("product_description");

		for (ProductDB c : list)
		{
			model.addRow(new Object[]
			{ c.product_id, c.product_name, c.price_per_unit, c.product_description });
		}
		
		table.setModel(model);
	}
	
}

class OpenFileFilter extends FileFilter {

    String description = "";
    String fileExt = "";

    public OpenFileFilter(String extension) {
        fileExt = extension;
    }

    public OpenFileFilter(String extension, String typeDescription) {
        fileExt = extension;
        this.description = typeDescription;
    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        return (f.getName().toLowerCase().endsWith(fileExt));
    }

    @Override
    public String getDescription() {
        return description;
    }
}
