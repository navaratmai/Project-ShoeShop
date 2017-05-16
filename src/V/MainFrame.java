package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame
{

	private JPanel contentPane;

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
					try// ตั้งค่าปุ่มให้เป็นแบบปกติ
					{
						// Set System L&F
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (UnsupportedLookAndFeelException e)
					{
						// handle exception
					} catch (ClassNotFoundException e)
					{
						// handle exception
					} catch (InstantiationException e)
					{
						// handle exception
					} catch (IllegalAccessException e)
					{
						// handle exception
					}
					MainFrame frame = new MainFrame();
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
	public MainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 695, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				CustomerFrame f = new CustomerFrame();
				f.setVisible(true);
			}
		});
		btnCustomer.setBounds(34, 29, 89, 23);
		contentPane.add(btnCustomer);

		JButton btnProduct = new JButton("Product");
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ProductFrame f = new ProductFrame();
				f.setVisible(true);
			}
		});
		btnProduct.setBounds(34, 74, 89, 23);
		contentPane.add(btnProduct);

		JButton btnUser = new JButton("User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				UserFrame f = new UserFrame();
				f.setVisible(true);
			}
		});
		btnUser.setBounds(34, 122, 89, 23);
		contentPane.add(btnUser);

		JButton btnInvoice = new JButton("Invoice");
		btnInvoice.setBounds(34, 167, 89, 23);
		contentPane.add(btnInvoice);
	}
}
