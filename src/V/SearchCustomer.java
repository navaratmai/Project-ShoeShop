package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import M.CustomerDB;
import M.CustomerManager;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchCustomer extends JFrame
{

	private JPanel contentPane;
	private JTable table;
	private JTextField textField_search;
	private JButton btnSearch;
	SelectCustomerI xSelectCustomerI;

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
					SearchCustomer frame = new SearchCustomer();
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
	public SearchCustomer()
	{
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 840, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 804, 347);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textField_search = new JTextField();
		textField_search.setBounds(10, 36, 195, 20);
		contentPane.add(textField_search);
		textField_search.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				search();
			}
		});
		btnSearch.setBounds(248, 35, 89, 23);
		contentPane.add(btnSearch);
		
		btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(table.getSelectedRow() == 0)
				{
					JOptionPane.showMessageDialog(SearchCustomer.this, "Please Select 1 row");
					return;
				}
				
				if(xSelectCustomerI != null)
				{
					if(list != null)
					{
						xSelectCustomerI.select(list.get(table.getSelectedRow()));
					
						setVisible(false);
					}
				}
			}
		});
		btnSelect.setBounds(411, 33, 89, 23);
		contentPane.add(btnSelect);
		
		load();
	}
	ArrayList <CustomerDB> list;
	private JButton btnSelect;
	public void load()
	{
		list = CustomerManager.getAllCustomer();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("name");
		model.addColumn("surname");
		model.addColumn("phone");

		for (CustomerDB c : list)
		{
			model.addRow(new Object[]
			{ c.id, c.name, c.surname, c.phone });
		}
		
		table.setModel(model);
	}
	public void search()
	{
		list = CustomerManager.searchCustomer(textField_search.getText());
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("name");
		model.addColumn("surname");
		model.addColumn("phone");

		for (CustomerDB c : list)
		{
			model.addRow(new Object[]
			{ c.id, c.name, c.surname, c.phone });
		}
		
		table.setModel(model);
	}
	public void setSelectCustomerI(SelectCustomerI x)
	{
		xSelectCustomerI = x;
	}
}

interface SelectCustomerI//เติม I ไว้ด้านหลังคือ interface
{
	public void select(CustomerDB x);
}
