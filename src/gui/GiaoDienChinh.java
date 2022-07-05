package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.NhanVien;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GiaoDienChinh extends JFrame {

	private JPanel contentPane;
	private JLabel lbllk;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public GiaoDienChinh() {
		setTitle("Link kiện");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(5, 5, 548, 69);
		contentPane.add(panel);
		panel.setLayout(null);
		contentPane.setBackground(Color.pink);
		
		JLabel lblQunLNh = new JLabel("Quản Lý Bán Linh kiện");
		lblQunLNh.setBounds(154, 11, 353, 31);
		lblQunLNh.setForeground(Color.BLUE);
		lblQunLNh.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel.add(lblQunLNh);
		
		JButton btnQunLNhn = new JButton("Quản Lý Khách Hàng ");
		btnQunLNhn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FromKhachHang info = new FromKhachHang();
				info.setVisible(true);
			//	info.pack();
				info.setLocationRelativeTo(null);
				info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
				
			
			}
		});
		btnQunLNhn.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnQunLNhn.setBounds(202, 100, 187, 23);
		contentPane.add(btnQunLNhn);
		
		JButton btnThngTinKhch = new JButton("Quản Lý Nhân Viên");
		btnThngTinKhch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FromNhanVien info = new FromNhanVien();
				info.setVisible(true);
			//	info.pack();
				info.setLocationRelativeTo(null);
				info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		btnThngTinKhch.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnThngTinKhch.setBounds(202, 160, 187, 23);
		contentPane.add(btnThngTinKhch);
		
		
		JButton btnNhpThuc = new JButton("Quản Lý Linh Kiện ");
		btnNhpThuc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FromLinhKien info = new FromLinhKien();
				info.setVisible(true);
			//	info.pack();
				info.setLocationRelativeTo(null);
				info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		btnNhpThuc.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNhpThuc.setBounds(202, 218, 187, 23);
		contentPane.add(btnNhpThuc);
		
		JButton btnHonKhch = new JButton("Hoá Đơn");
		btnHonKhch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FromHoaDon info = new FromHoaDon();
				info.setVisible(true);
			//	info.pack();
				info.setLocationRelativeTo(null);
				info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		btnHonKhch.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHonKhch.setBounds(202, 276, 187, 23);
		contentPane.add(btnHonKhch);
		
		JButton btnTmKim = new JButton("Tìm Kiếm");
		btnTmKim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Find info = new Find();
				info.setVisible(true);
				info.setLocationRelativeTo(null);
				info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		btnTmKim.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTmKim.setBounds(202, 336, 187, 23);
		contentPane.add(btnTmKim);
	}
}
