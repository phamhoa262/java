package gui;




import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JDateChooser;

//import Hoanthien2.Oder_detail;

import java.util.List;

import connectDB.ConnectDB;
import dao.LinhKien_DAO;
import dao.LoaiLK_DAO;
import dao.NhaCungCap_DAO;
import entity.ChucVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.LoaiLK;
import entity.NhaCungCap;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class FromLinhKien extends JFrame implements ActionListener ,MouseListener  {

	private JPanel contentPane;


	PreparedStatement preStmt = null;
	private JTextField textmacungcap;

	private JDateChooser dateChooser;
	private JButton btnAdd;
	private JButton btnFix;
	private JButton btnxoatrang;
	//	private JButton btnxoatrang;


	private JButton btnthoat;
	private JTextField txtMa;
	private JTextField txtmacungcap;
	private JTextField txtTen;
	private JTextField txtDonGia;
	private JTextField txtSoLuong;
	private JTextField txtLoai;
	private JComboBox<String> cbomacc;
	private JComboBox<String> cboloailk;
	private DefaultTableModel model;
	private JTextField txtngaynhap;
	LinhKien_DAO dslk = new LinhKien_DAO();
	private NhaCungCap_DAO ncc_Dao;
	private LinhKien_DAO lk_Dao;
	private LoaiLK_DAO llk_Dao;
	private JTable table;
	private JButton btnxoa;
	Connection con = null;
	Statement st = null;
	
	String []col= {"Mã linh kiện","Mã nhà cung cấp","Tên linh kiện","Giá","Ngày nhập","Số lượng","Loai linh kiện "};


	private JButton btnlinhkien;


	private JButton btnnhacungcap;
	public FromLinhKien() {
ConnectDB.getInstance().connect();
		
		ncc_Dao=new NhaCungCap_DAO();
		lk_Dao = new LinhKien_DAO();
		llk_Dao = new LoaiLK_DAO();
		
		setTitle("Thông tin linh kiện");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(Color.lightGray);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//bỏ lên trên này
		
	

		JLabel lblNewLabel = new JLabel("THÔNG TIN LINH KIỆN");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(250, 20, 500, 53);
		contentPane.add(lblNewLabel);

		btnlinhkien= new JButton("Loại linh kiện");
		btnlinhkien.setBounds(0,50, 160, 30);
		contentPane.add(btnlinhkien);
		
		btnnhacungcap= new JButton("Nhà cung cấp ");
		btnnhacungcap.setBounds(150,50, 151, 30);
		contentPane.add(btnnhacungcap);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(1, 81, 300, 569);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Thông tin linh kiện");
		lblNewLabel_1.setForeground(Color.red);
		lblNewLabel_1.setBounds(20, 11, 165, 14);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lblId = new JLabel("Mã :");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(10, 67, 70, 14);
		panel_1.add(lblId);

		txtMa = new JTextField();
		txtMa.setBounds(84, 67, 174, 20);
		panel_1.add(txtMa);
		

		JLabel lblMacungcap = new JLabel("Mã cung cấp: ");
		lblMacungcap.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMacungcap.setBounds(10, 104, 180, 14);
		panel_1.add(lblMacungcap);
        
		cbomacc = new JComboBox<String>();
		cbomacc.setBounds(87, 104, 168, 20);
		cbomacc.setEditable(true);
		ArrayList <NhaCungCap> listncc = ncc_Dao.getalltbncc();
	    panel_1.add(cbomacc);
		
		for(NhaCungCap ncc :listncc)
		{
			cbomacc.addItem(ncc.getMaNcc());//getmaphong len jcombobox
		}
		

		JLabel lblTen = new JLabel("Tên :");
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTen.setBounds(10, 144, 75, 14);
		panel_1.add(lblTen);

		txtTen = new JTextField();
		txtTen.setBounds(84, 144, 174, 20);
		panel_1.add(txtTen);
		
		JLabel lblDonGia = new JLabel("Đơn giá:");
		lblDonGia.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDonGia.setBounds(10, 184, 60, 14);
		panel_1.add(lblDonGia);

		txtDonGia = new JTextField();
		txtDonGia.setBounds(84, 184, 174, 20);
		panel_1.add(txtDonGia);
		
		JLabel lblNgayNhap = new JLabel("Ngày nhập:");
		lblNgayNhap.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNgayNhap.setBounds(10, 224, 100, 14);
		panel_1.add(lblNgayNhap);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(85, 224, 173, 20);
		panel_1.add(dateChooser);
        
           
         /*txtngaynhap = new JTextField();
         txtngaynhap.setBounds(85, 224, 173, 20);
         panel_1.add(txtngaynhap);
		*/
		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSoLuong.setBounds(10, 264, 64, 14);
		panel_1.add(lblSoLuong);

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(84, 264, 174, 20);
		panel_1.add(txtSoLuong);
		
		
		JLabel lblLoailk = new JLabel("Loại :");
		lblLoailk.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLoailk.setBounds(10, 304, 64, 14);
		panel_1.add(lblLoailk);
		
		
		
		JLabel jlbl1 = new JLabel("Mã linh kiện");
		jlbl1.setBounds(320, 63, 100, 50);
		
		contentPane.add(jlbl1);
		
		JLabel jlbl2 = new JLabel("Mã nhà cung cấp");
		jlbl2.setBounds(400, 63, 100, 50);
		contentPane.add(jlbl2);
		
		JLabel jlbl3 = new JLabel("Tên linh kiện");
		jlbl3.setBounds(506, 63, 100, 50);
		contentPane.add(jlbl3);
		
		JLabel jlbl7 = new JLabel("Đơn Giá");
		jlbl7.setBounds(600, 63, 100, 50);
		contentPane.add(jlbl7);
		
		
		JLabel jlbl4 = new JLabel("Ngày nhập");
		jlbl4.setBounds(690, 63, 100, 50);
		contentPane.add(jlbl4);
		
		JLabel jlbl5 = new JLabel("Số lượng");
		jlbl5.setBounds(790, 63, 100, 50);
		contentPane.add(jlbl5);
		
		JLabel jlbl6 = new JLabel("Loại linh kiện");
		jlbl6.setBounds(870, 63, 100, 50);
		contentPane.add(jlbl6);
		
		
		
		
		jlbl1.setForeground(Color.BLUE);
		jlbl2.setForeground(Color.BLUE);
		jlbl3.setForeground(Color.BLUE);
		jlbl4.setForeground(Color.BLUE);
		jlbl5.setForeground(Color.BLUE);
		jlbl6.setForeground(Color.BLUE);
		jlbl7.setForeground(Color.BLUE);

		String []col= {"Mã linh kiện","Mã nhà cung cấp","Tên linh kiện","Đơn Giá","Ngày nhập","Số lượng","Loai linh kiện "};
		model = new DefaultTableModel(col,0);// DefaultTableModel model= new DefaultTableModel(col,0);//ko dc
		table = new JTable(model);
		JScrollPane pane= new JScrollPane(table);	
		pane.setBounds(320, 85, 660, 510);
		
		
		cboloailk = new JComboBox<String>();
		cboloailk.setBounds(87, 304, 178, 20);
		cboloailk.setEditable(true);
		ArrayList<LoaiLK> listllk = llk_Dao.getalltbLoaiLK();
	    panel_1.add(cboloailk);
		
		for(LoaiLK llk :listllk)
		{
			cboloailk.addItem(llk.getMaloai());//getmaphong len jcombobox
		}

		table = new JTable();
		table.setBounds(290, 81, 684, 569);
		contentPane.add(table);
		
		
		//btnlk= new JButton()
		
		 btnAdd = new JButton("Thêm");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		
		btnAdd.setBounds(10, 416, 75, 23);
		panel_1.add(btnAdd);

	    btnFix = new JButton("Sửa");
		btnFix.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnFix.setBounds(101, 416, 74, 23);
		panel_1.add(btnFix);

		btnxoatrang = new JButton("Xóa trắng ");
		btnxoatrang.setFont(new Font("Tahoma", Font.BOLD, 11));
	
		btnxoatrang.setBounds(193, 416,95, 23);
		panel_1.add(btnxoatrang);

	    btnxoa = new JButton("Xóa");
		btnxoa.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnxoa.setBounds(10, 470, 75, 23);
		panel_1.add(btnxoa);

	    btnthoat = new JButton("Thoát");
		btnthoat.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnthoat.setBounds(105, 470, 70, 23);
		panel_1.add(btnthoat);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 390, 274, 2);
		panel_1.add(separator_1);
		
		btnAdd.addActionListener(this);
		btnxoa.addActionListener(this);
		btnFix.addActionListener(this);
		btnthoat.addActionListener(this);
		btnxoatrang.addActionListener(this);
		btnlinhkien.addActionListener(this);
		btnnhacungcap.addActionListener(this);
		table.addMouseListener(this);
		
		docdulieudatavaobang();

	}
	public static void main(String[] args) {
		new FromLinhKien().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnAdd)) {
			if(txtMa.getText().trim().equals("") || txtTen.getText().trim().equals("")||txtDonGia.getText().trim().equals("")||txtSoLuong.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Không được để trống");
			}else {
				if(KiemTra()) {
					String mancc = cbomacc.getSelectedItem().toString();
					NhaCungCap ncc= new NhaCungCap(mancc);
					java.util.Date utilStartDate = dateChooser.getDate();
					java.sql.Date ngaynhap = new java.sql.Date(utilStartDate.getTime());
					String loailk = cboloailk.getSelectedItem().toString();
					LoaiLK llk = new LoaiLK(loailk);
					LinhKien lk= new  LinhKien(txtMa.getText(), txtTen.getText(), ncc, llk, ngaynhap, Double.parseDouble(txtDonGia.getText()), Integer.parseInt(txtSoLuong.getText()));
					if(!(lk_Dao.create(lk))) {
						JOptionPane.showMessageDialog(this, "Trùng mã");
					}
					else {
						lk_Dao.create(lk);
						model.addRow(new Object[] {
								lk.getMalk(),lk.getNhacc().getMaNcc(),lk.getTenlk(),lk.getDongia(),
								lk.getNgaynhap(),lk.getSoluong(),lk.getLoailk().getMaloai() 
						});
					}}}}

		else if(o.equals(btnxoa))
		{
			int r= table.getSelectedRow();
			model.removeRow(r); // xóa trong table model
			Connection con = ConnectDB.getConnection();
			try {
				Statement st = (Statement) con.createStatement();
				String query = "DELETE FROM linhkien1 WHERE malk = '" + txtMa.getText() + "'";

				
				st.execute(query);
				//st.execute(query);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				
			}

		}
		else if(o.equals(btnxoatrang))
		{
			txtMa.setText("");
			txtTen.setText("");
			txtDonGia.setText("");
			txtSoLuong.setText("");
			cbomacc.setSelectedItem("");
			cboloailk.setSelectedItem("");
			dateChooser.setDateFormatString("");
		}

		else if(o.equals(btnthoat)) {
			GiaoDienChinh info =new GiaoDienChinh();
			info.setVisible(true);
			//	info.pack();
			info.setLocationRelativeTo(null);
			info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();

		}
		else if(o.equals(btnlinhkien)) {
			FromLoaiLK info =new FromLoaiLK();
			info.setVisible(true);
			//	info.pack();
			info.setLocationRelativeTo(null);
			info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();

		}
		else if(o.equals(btnnhacungcap)) {
			FromNhaCungCap info =new FromNhaCungCap();
			info.setVisible(true);
			//	info.pack();
			info.setLocationRelativeTo(null);
			info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();

		}



		if(o.equals(btnFix)) {
			int row = table.getSelectedRow();
			if(txtMa.getText().trim().equals("") || txtTen.getText().trim().equals("")||txtDonGia.getText().trim().equals("")||txtSoLuong.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Không được để trống");
			}
			else 
				if(row>=0) {
					if (KiemTra()) {
						LinhKien c= re();
						if(lk_Dao.update(c)) {
							table.setValueAt(cbomacc.getSelectedItem(), row, 1);
							table.setValueAt(txtTen.getText(), row, 2);
							table.setValueAt(txtDonGia.getText(), row, 3);
							table.setValueAt(txtSoLuong.getText(), row, 5);
							java.util.Date utilStartDate = dateChooser.getDate();
							java.sql.Date namsinh = new java.sql.Date(utilStartDate.getTime());
							table.setValueAt(namsinh, row, 4);
							table.setValueAt(cbomacc.getSelectedItem(), row, 6);
							JOptionPane.showMessageDialog(this, "Sửa Thành Công");

						}else
							JOptionPane.showMessageDialog(this, "Sửa Không Thành Công ");
					}}else
						JOptionPane.showMessageDialog(this, "chưa chọn đối tượng để Sửa");
		}

	}

	private LinhKien re() {
		String ma=txtMa.getText().trim();
		String tenlk=txtTen.getText().trim();
		String mancc = cbomacc.getSelectedItem().toString();
		NhaCungCap ncc= new NhaCungCap(mancc);
		java.util.Date utilStartDate = dateChooser.getDate();
		java.sql.Date namsinh = new java.sql.Date(utilStartDate.getTime());
		String dongia=txtDonGia.getText().trim();
		String soluong=txtSoLuong.getText().trim();
		String loailk = cboloailk.getSelectedItem().toString();
		LoaiLK llk = new LoaiLK(loailk);
		LinhKien kh= new LinhKien(ma, tenlk, ncc, llk, namsinh, Double.parseDouble(dongia), Integer.parseInt(soluong));
		return kh;
	}
	private boolean KiemTra() {
		// TODO Auto-generated method stub
		String soluong= txtSoLuong.getText();
		String DonGia= txtDonGia.getText();
		String ma=txtMa.getText().trim();
		if(!(ma.matches("[L][K][0-9]+"))) {
			JOptionPane.showMessageDialog(this, "Nhập lại Theo Mẫu LK + Số Bất Kỳ VD:LK01237");
			return false;
		}
		if (soluong.length() > 0) {
			try {
				int x = Integer.parseInt(soluong);
				if (x <= 0) {
					JOptionPane.showMessageDialog(null, "Nhập Lại: Số luong Phải là Số Nguyên Dương");
					return false;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "nhập Lại: Số luong Phải là Số");

				return false;
			}}
		if (DonGia.length() > 0) {
			try {
				Double x= Double.parseDouble(DonGia);
				if (x < 0) {
					JOptionPane.showMessageDialog(null, "Nhập Lại: đơn giá phải lớn hơn 0");
					return false;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "nhập Lại: đơn giá phải là số");

				return false;
			}}
		return true;

		//		return false;
	}
	private void docdulieudatavaobang() {
		// TODO Auto-generated method stub

		List <LinhKien> list= lk_Dao.getallLK();
		for(LinhKien lk :list)
		{
			model.addRow(new Object[] {lk.getMalk(),lk.getNhacc().getMaNcc(),lk.getTenlk(),lk.getDongia(),lk.getNgaynhap()
					, lk.getSoluong(),lk.getLoailk().getMaloai()});
		}
		table.setModel(model);
		table.setBounds(310, 100, 650, 500);

	}




	@Override
	public void mouseClicked(MouseEvent e) {
		try {
		int row = table.getSelectedRow();
		TableModel model= table.getModel();
		txtMa.setText(table.getValueAt(row, 0).toString());
		cbomacc.setSelectedItem(model.getValueAt(row, 1).toString());
		txtTen.setText(model.getValueAt(row, 2).toString());
		txtDonGia.setText(model.getValueAt(row, 3).toString());
		txtSoLuong.setText(model.getValueAt(row, 5).toString());
		dateChooser.setDate((java.util.Date) model.getValueAt(row, 4));
		cboloailk.setSelectedItem(model.getValueAt(row, 6).toString());
		}catch (Exception ư) {
			// TODO: handle exception
			System.out.println("....");
		}


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

}


