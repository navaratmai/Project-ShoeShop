package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import M.CustomerDB;
import M.CustomerManager;
import M.ProductDB;
import M.ProductManager;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchProduct extends JFrame
{

	private JPanel contentPane;
	private JTextField textField_search;
	private JTable table;
	private JButton button_search;
	private JButton button_select;

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
					SearchProduct frame = new SearchProduct();
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
	public SearchProduct()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 835, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 804, 347);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textField_search = new JTextField();
		textField_search.setColumns(10);
		textField_search.setBounds(10, 14, 195, 20);
		contentPane.add(textField_search);
		
		button_search = new JButton("Search");
		button_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		button_search.setBounds(238, 13, 89, 23);
		contentPane.add(button_search);
		
		button_select = new JButton("Select");
		button_select.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(table.getSelectedRowCount() == 0)
				{
					JOptionPane.showMessageDialog(SearchProduct.this, "Please Select 1 row");
					return;
				}
				
				if(xSelectProductI != null)
				{
					if(list != null)
					{
						xSelectProductI.select(list.get(table.getSelectedRow()));
					
						setVisible(false);
					}
				}				
			}
		});
		button_select.setBounds(401, 11, 89, 23);
		contentPane.add(button_select);
	}
	SelectProductI xSelectProductI;
	public void setSelectProduct(SelectProductI x)
	{
		xSelectProductI = x;
	}
	
	ArrayList <ProductDB> list;
	public void search()
	{
		list = ProductManager.searchProduct(textField_search.getText());
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("product_id");
		model.addColumn("product_name");
		model.addColumn("price_per_unit");
		model.addColumn("product_description");
		//model.addColumn("product_image");
		
		for (ProductDB c : list)
		{
			model.addRow(new Object[]
			{ c.product_id, c.product_name, c.price_per_unit, c.product_description });
		}
		
		table.setModel(model);
	}
}
interface SelectProductI//เติม I ไว้ด้านหลังคือ interface
{
	public void select(ProductDB x);
}

