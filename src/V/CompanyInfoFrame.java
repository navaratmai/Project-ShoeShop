package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import M.CompanyInfoDB;
import M.CompanyInfoManager;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompanyInfoFrame extends JFrame
{

	private JPanel contentPane;
	private JTextField textField_company_name;
	private JTextField textField_addressno;
	private JTextField textField_phone;
	private JTextField textField_email;
	private JTextField textField_road;
	private JTextField textField_kwang;
	private JTextField textField_ket;
	private JTextField textField_province;
	private JTextField textField_zipcode;

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
					CompanyInfoFrame frame = new CompanyInfoFrame();
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
	public CompanyInfoFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCompanyName = new JLabel("Company Name");
		lblCompanyName.setBounds(36, 35, 125, 14);
		contentPane.add(lblCompanyName);
		
		JLabel lblAddress = new JLabel("AddressNo");
		lblAddress.setBounds(36, 79, 98, 14);
		contentPane.add(lblAddress);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(36, 323, 46, 14);
		contentPane.add(lblPhone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(36, 367, 46, 14);
		contentPane.add(lblEmail);
		
		textField_company_name = new JTextField();
		textField_company_name.setBounds(193, 32, 339, 20);
		contentPane.add(textField_company_name);
		textField_company_name.setColumns(10);
		
		textField_addressno = new JTextField();
		textField_addressno.setBounds(193, 76, 339, 20);
		contentPane.add(textField_addressno);
		textField_addressno.setColumns(10);
		
		textField_phone = new JTextField();
		textField_phone.setBounds(193, 320, 339, 20);
		contentPane.add(textField_phone);
		textField_phone.setColumns(10);
		
		textField_email = new JTextField();
		textField_email.setBounds(193, 364, 339, 20);
		contentPane.add(textField_email);
		textField_email.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				xCompanyInfoDB.company_name = textField_company_name.getText();
				xCompanyInfoDB.addressno = textField_addressno.getText();
				xCompanyInfoDB.road = textField_road.getText();
				xCompanyInfoDB.kwang = textField_kwang.getText();
				xCompanyInfoDB.ket = textField_ket.getText();
				xCompanyInfoDB.province = textField_province.getText();
				xCompanyInfoDB.zipcode = textField_zipcode.getText();
				xCompanyInfoDB.phone = textField_phone.getText();
				xCompanyInfoDB.email = textField_email.getText();
				
				CompanyInfoManager.edit(xCompanyInfoDB);
			}
		});
		btnSave.setBounds(276, 395, 89, 23);
		contentPane.add(btnSave);
		
		JLabel lblRoad = new JLabel("Road");
		lblRoad.setBounds(36, 119, 98, 14);
		contentPane.add(lblRoad);
		
		textField_road = new JTextField();
		textField_road.setColumns(10);
		textField_road.setBounds(193, 116, 339, 20);
		contentPane.add(textField_road);
		
		JLabel lblKwang = new JLabel("Kwang");
		lblKwang.setBounds(36, 162, 98, 14);
		contentPane.add(lblKwang);
		
		textField_kwang = new JTextField();
		textField_kwang.setColumns(10);
		textField_kwang.setBounds(193, 159, 339, 20);
		contentPane.add(textField_kwang);
		
		JLabel lblKey = new JLabel("Ket");
		lblKey.setBounds(36, 204, 98, 14);
		contentPane.add(lblKey);
		
		textField_ket = new JTextField();
		textField_ket.setColumns(10);
		textField_ket.setBounds(193, 201, 339, 20);
		contentPane.add(textField_ket);
		
		JLabel lblProvince = new JLabel("Province");
		lblProvince.setBounds(36, 246, 98, 14);
		contentPane.add(lblProvince);
		
		textField_province = new JTextField();
		textField_province.setColumns(10);
		textField_province.setBounds(193, 243, 339, 20);
		contentPane.add(textField_province);
		
		JLabel lblZipcode = new JLabel("zipcode");
		lblZipcode.setBounds(36, 277, 98, 14);
		contentPane.add(lblZipcode);
		
		textField_zipcode = new JTextField();
		textField_zipcode.setColumns(10);
		textField_zipcode.setBounds(193, 274, 339, 20);
		contentPane.add(textField_zipcode);
		
		loadData();
	}
	CompanyInfoDB xCompanyInfoDB;
	public void loadData()
	{
		xCompanyInfoDB = CompanyInfoManager.getCompanyinfo();
		textField_company_name.setText(xCompanyInfoDB.company_name);
		textField_addressno.setText(xCompanyInfoDB.addressno);
		textField_road.setText(xCompanyInfoDB.road);
		textField_kwang.setText(xCompanyInfoDB.kwang);
		textField_ket.setText(xCompanyInfoDB.ket);
		textField_province.setText(xCompanyInfoDB.province);
		textField_zipcode.setText(xCompanyInfoDB.zipcode);
		textField_phone.setText(xCompanyInfoDB.phone);
		textField_email.setText(xCompanyInfoDB.email);
		
	}

}
