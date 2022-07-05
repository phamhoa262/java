package gui;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class FormDangNhap extends JFrame {

	private JPanel contentPane;
	private JTextField textUsename;
	private JPasswordField textPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDangNhap frame = new FormDangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormDangNhap() {
		setTitle("Đăng nhập");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(0, 0, 434, 52);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblLoginSystems = new JLabel("ĐĂNG NHẬP ");
		lblLoginSystems.setBounds(150, 11, 300, 30);
		lblLoginSystems.setForeground(Color.red);
		lblLoginSystems.setBackground(Color.PINK);
		lblLoginSystems.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel.add(lblLoginSystems);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(0, 52, 434, 209);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUsename = new JLabel("Username :");
		lblUsename.setBounds(50, 40, 80, 14);
		lblUsename.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblUsename);
		
		textUsename = new JTextField();
		textUsename.setBounds(120, 37, 200, 20);
		panel_1.add(textUsename);
		textUsename.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password  :");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(50, 81, 67, 14);
		panel_1.add(lblPassword);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBackground(Color.ORANGE);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textUsename.setText(null);
				textPass.setText(null);
			}
		});
		
		textPass = new JPasswordField();
		textPass.setBounds(120, 78, 200, 20);
		panel_1.add(textPass);
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReset.setBounds(33, 159, 89, 23);
		panel_1.add(btnReset);
		
		JButton btnLoin = new JButton("Login");
		btnLoin.setBackground(Color.green);
		btnLoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = textPass.getText();
				String username = textUsename.getText();
				
				if(password.contains("123456")&& username.contains("nhom15")) {
					textPass.setText(null);
					textUsename.setText(null);
					GiaoDienChinh info = new GiaoDienChinh();
					info.setVisible(true);
				//	info.pack();
					info.setLocationRelativeTo(null);
					info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
					
				}else {
					JOptionPane.showMessageDialog(null, "Nhập sai tên đăng nhập hoặc mật khẩu !!", "Đăng nhập lỗi !!",JOptionPane.ERROR_MESSAGE);
					textPass.setText(null);
					textUsename.setText(null);
				}
			}
		});
		btnLoin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLoin.setBounds(162, 159, 89, 23);
		panel_1.add(btnLoin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(Color.RED);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setBounds(293, 159, 89, 23);
		panel_1.add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(28, 136, 371, 2);
		panel_1.add(separator);
	}
}
