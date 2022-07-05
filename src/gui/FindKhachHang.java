package gui;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.PreparedStatement;


import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.KhachHang;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class FindKhachHang extends JFrame  implements ActionListener,MouseListener{

	private JPanel contentPane;
	private JTextField textName1;
	Connection con = null;
	Statement st = null;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnExit;
	private JTextField textId;
	private JButton btReset;
	private DefaultTableModel model;
	private KhachHang_DAO kh_dao;
	private JButton btnSearch1;
	private JButton btnSearch;
	private String timkiem;
	private JLabel lblthongbao;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new FindKhachHang().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public FindKhachHang() {
		setTitle("Tìm kiếm khách hàng ");
		kh_dao=  new KhachHang_DAO();
		ConnectDB.getInstance().connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 740, 426);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nhập tên : ");
		lblNewLabel.setBounds(10, 11, 107, 23);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblNewLabel);

		textName1 = new JTextField();
		textName1.setBounds(127, 12, 297, 20);
		contentPane.add(textName1);
		textName1.setColumns(10);

		 btnSearch = new JButton("Tìm kiếm ");
		
		btnSearch.setBounds(456, 11, 89, 23);
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(btnSearch);

		


		btnExit = new JButton("Thoát");
		
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExit.setBounds(153, 338, 89, 23);
		contentPane.add(btnExit);
        btnExit.setBackground(Color.red); 
		
		JLabel lblEnterTheId = new JLabel("Nhập mã :");
		lblEnterTheId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterTheId.setBounds(10, 57, 94, 14);
		contentPane.add(lblEnterTheId);

		textId = new JTextField();
		textId.setBounds(127, 54, 297, 20);
		contentPane.add(textId);
		textId.setColumns(10);

		btnSearch1 = new JButton("Tìm kiếm");
		
			
		btnSearch1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch1.setBounds(456, 53, 100, 23);
		contentPane.add(btnSearch1);
		
		btReset = new JButton("Reset");
		
		btReset.setFont(new Font("Tahoma", Font.BOLD, 11));
		btReset.setBounds(335, 339, 89, 23);
		contentPane.add(btReset);
		
		 
		lblthongbao= new JLabel("Thông báo !!");
		lblthongbao.setBounds(20, 295, 100, 30);
		contentPane.add(lblthongbao);
		
		
		String []col= {"Mã ","Ho tên","Năm sinh","Giới tính","Địa chỉ ","Số điện thoại"};
		model = new DefaultTableModel(col,0);// DefaultTableModel model= new DefaultTableModel(col,0);//ko dc
		table = new JTable(model);
		JScrollPane pane= new JScrollPane(table);	
		pane.setBounds(20, 100, 700, 200);
		contentPane.add(pane);
		String timkiem="";
		
		 btnSearch.addActionListener(this);
		 btnSearch1.addActionListener(this);
		 btnExit.addActionListener(this);
		btReset.addActionListener(this);
		DocDuLieuDatabaseVaoTable();
		 
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		Object o = e.getSource();
		if (o.equals(btnSearch1)) {
			
			KhachHang_DAO dskh = new KhachHang_DAO();
			List<KhachHang> list =dskh.findid(textId.getText());
			if(textId.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mã cần tìm !!!");
				
			}
			else if(list.size()==0)
			{
				lblthongbao.setText("Không tìm thấy !!");
				lblthongbao.setForeground(Color.RED);
				JOptionPane.showMessageDialog(this, "Không tìm thấy mã : " +textId.getText());
				
			}
			else
			{
				model.getDataVector().removeAllElements();
				for(KhachHang kh :list) {
					String gt="Nam";

					if(kh.isGioiTinh()==true) {
						gt="Nữ";
					}
					model.addRow(new Object[] {
							kh.getMakh(),kh.getTenkh(),kh.getNamsinh(),gt,kh.getDiachi(),kh.getSdt()
					});
				
					}lblthongbao.setText("Tìm thấy !!");
					lblthongbao.setForeground(Color.blue);
				table.setModel(model);
				}
			
			}
			
			
		else if(o.equals(btnSearch))
		{
			KhachHang_DAO dskh = new KhachHang_DAO();
			List<KhachHang> list =kh_dao.findname(textName1.getText());
			if(textName1.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Vui lòng nhập tên cần tìm !!!");
				
			}
			else if(list.size()==0)
			{
				lblthongbao.setText("Không tìm thấy !!");
				lblthongbao.setForeground(Color.RED);
				JOptionPane.showMessageDialog(this, "Không tìm thấy tên : " +textName1.getText());
			}
			else
			{
				model.getDataVector().removeAllElements();
				for(KhachHang kh :kh_dao.findname(textName1.getText())) {
					String gt="Nam";

					if(kh.isGioiTinh()==true) {
						gt="Nữ";
					}
					model.addRow(new Object[] {
							kh.getMakh(),kh.getTenkh(),kh.getNamsinh(),gt,kh.getDiachi(),kh.getSdt()
					});
				
					}
				lblthongbao.setText("Tìm thấy !!");
				lblthongbao.setForeground(Color.blue);
				table.setModel(model);
				}	
		
		
		
		}
		else if(o.equals(btReset))
			
		{
			try {

				Statement stmt = ConnectDB.getConnection().createStatement();
				String query = "select * from khachhang ";
				ResultSet resultSet = stmt.executeQuery(query);
				table.setModel(DbUtils.resultSetToTableModel(resultSet));
				//process();
				textName1.setText("");
				textId.setText("");
				textName1.requestFocus();
			} catch (Exception f) {
				f.printStackTrace();
			}
		}
		else if(o.equals(btnExit))
		{
			Find info = new Find();
			info.setVisible(true);
		//	info.pack();
			info.setLocationRelativeTo(null);
			info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		}
	}
	
		
	
	
		
		public void DocDuLieuDatabaseVaoTable() {
			List<KhachHang> list = kh_dao.getallKH();
			for (KhachHang cv : list) {
				String gt="Nam";

				if(cv.isGioiTinh()==true) {
					gt="Nữ";
				}
				model.addRow(new Object[] {
						cv.getMakh(),cv.getTenkh(),cv.getNamsinh(),gt,cv.getDiachi(),cv.getSdt()
				});}
		}
		
	
			
	
			
		}

	
	

