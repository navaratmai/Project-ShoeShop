package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import M.CustomerDB;
import M.CustomerManager;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerFrame extends JFrame
{

	private JPanel contentPane;
	private JTable table;
	private JLabel label;
	private JTextField textField_id;
	private JLabel label_1;
	private JTextField textField_name;
	private JLabel label_2;
	private JTextField textField_surname;
	private JLabel label_3;
	private JTextField textField_phone;
	private JButton btnSaveNew;
	private JButton btnEdit;
	private JButton btnDelete;
	ArrayList <CustomerDB> list;

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
					CustomerFrame frame = new CustomerFrame();
					frame.setVisible(true);
				} catch (Exception e)//เป็นคลาสแม่ ทำครั้งเดียวใช้ได้หมด
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerFrame()
	{
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 649, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 407, 438);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRowCount() < 1)
				{
					return;
				}
				int index = table.getSelectedRow();
				int id = 			Integer.parseInt(table.getValueAt(index, 0).toString());
				String name = 		table.getValueAt(index, 1).toString();
				String surname = 	table.getValueAt(index, 2).toString();
				String phone = 		table.getValueAt(index, 3).toString();
				
				textField_id.setText("" + id);
				textField_name.setText("" + name);
				textField_surname.setText("" + surname);
				textField_phone.setText("" + phone);
				
			}
		});
		scrollPane.setViewportView(table);
		table.setBackground(Color.white);
		
		label = new JLabel("id");
		label.setBounds(429, 11, 46, 14);
		contentPane.add(label);
		
		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setColumns(10);
		textField_id.setBackground(Color.YELLOW);
		textField_id.setBounds(486, 11, 105, 20);
		contentPane.add(textField_id);
		
		label_1 = new JLabel("name");
		label_1.setBounds(429, 46, 46, 14);
		contentPane.add(label_1);
		
		textField_name = new JTextField();
		textField_name.setColumns(10);
		textField_name.setBounds(486, 46, 105, 20);
		contentPane.add(textField_name);
		
		label_2 = new JLabel("surname");
		label_2.setBounds(429, 77, 46, 14);
		contentPane.add(label_2);
		
		textField_surname = new JTextField();
		textField_surname.setColumns(10);
		textField_surname.setBounds(486, 77, 105, 20);
		contentPane.add(textField_surname);
		
		label_3 = new JLabel("phone");
		label_3.setBounds(429, 115, 46, 14);
		contentPane.add(label_3);
		
		textField_phone = new JTextField();
		textField_phone.setColumns(10);
		textField_phone.setBounds(486, 115, 105, 20);
		contentPane.add(textField_phone);
		
		btnSaveNew = new JButton("SAVE NEW");
		btnSaveNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// .trim คือตัดช่องว่างเผื่อผู้ใช้พิมพ์ช่องว่างมา
				CustomerDB x = new CustomerDB(0, textField_name.getText().trim(),
						textField_surname.getText().trim(),
						textField_phone.getText().trim());
				CustomerManager.saveNewCustomer(x);
				load();
				textField_id.setText("");
				textField_name.setText("");
				textField_surname.setText("");
				textField_phone.setText("");
				
				JOptionPane.showMessageDialog(CustomerFrame.this, "finish!!");
			}
		});
		btnSaveNew.setBounds(429, 159, 111, 23);
		contentPane.add(btnSaveNew);
		
		btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerDB x = new CustomerDB(
						Integer.parseInt(textField_id.getText()), 
						textField_name.getText().trim(),
						textField_surname.getText().trim(),
						textField_phone.getText().trim());
				CustomerManager.editCustomer(x);
				load();
				textField_id.setText("");
				textField_name.setText("");
				textField_surname.setText("");
				textField_phone.setText("");
				
				JOptionPane.showMessageDialog(CustomerFrame.this, "finish!!");
			}
		});
		btnEdit.setBounds(429, 207, 111, 23);
		contentPane.add(btnEdit);
		
		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(
						CustomerFrame.this,"Do you want to delete ?",
						"DELETE ?", JOptionPane.OK_CANCEL_OPTION))
				{
				CustomerDB x = new CustomerDB(
						Integer.parseInt(textField_id.getText()), 
						textField_name.getText().trim(),
						textField_surname.getText().trim(),
						textField_phone.getText().trim());
				CustomerManager.deleteCustomer(x);
				load();
				textField_id.setText("");
				textField_name.setText("");
				textField_surname.setText("");
				textField_phone.setText("");
				
				JOptionPane.showMessageDialog(CustomerFrame.this, "finish!!");
				}
			}
		});
		btnDelete.setBounds(429, 256, 111, 23);
		contentPane.add(btnDelete);
		
		load();
	}
	
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
}
