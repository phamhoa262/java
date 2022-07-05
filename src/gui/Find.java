package gui;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Find extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Find frame = new Find();
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
	public Find() {
		setTitle("Tìm kiếm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel.setBounds(5, 5, 424, 55);
		contentPane.add(panel);
		panel.setLayout(null);
		
		contentPane.setBackground(Color.lightGray);
		
		JLabel lblTmKim = new JLabel("TÌM KIẾM ");
		lblTmKim.setBackground(Color.WHITE);
		lblTmKim.setBounds(151, 5, 200, 39);
		lblTmKim.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel.add(lblTmKim);
		
		JButton btnTmKimNhn = new JButton("Tìm kiếm nhân viên");
		btnTmKimNhn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindNhanVien info = new FindNhanVien();
				info.setVisible(true);
			//	info.pack();
				info.setLocationRelativeTo(null);
				info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		btnTmKimNhn.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTmKimNhn.setBounds(139, 85, 165, 23);
		contentPane.add(btnTmKimNhn);
		
		JButton btnTmKimSn = new JButton("Tìm kiếm linh kiện");
		btnTmKimSn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindLinhKien info = new FindLinhKien();
				info.setVisible(true);
			//	info.pack();
				info.setLocationRelativeTo(null);
				info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		btnTmKimSn.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTmKimSn.setBounds(139, 139, 165, 23);
		contentPane.add(btnTmKimSn);
		
		JButton btnExit = new JButton("Thoát");
		btnExit.setBackground(Color.RED);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GiaoDienChinh info = new GiaoDienChinh();
				info.setVisible(true);
			//	info.pack();
				info.setLocationRelativeTo(null);
				info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBounds(327, 268, 89, 23);
		contentPane.add(btnExit);
		
		JButton btnTmKimKhch = new JButton("Tìm kiếm khách hàng");
		btnTmKimKhch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FindKhachHang info = new FindKhachHang();
				info.setVisible(true);
				// info.pack();
				info.setLocationRelativeTo(null);
				info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		btnTmKimKhch.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTmKimKhch.setBounds(139, 196, 165, 23);
		contentPane.add(btnTmKimKhch);
	}
}
