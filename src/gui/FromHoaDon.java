package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.NhanVien_DAO;
import entity.ChucVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;

public class FromHoaDon extends JFrame implements ActionListener, MouseListener{

	private JPanel contentPane;


	PreparedStatement preStmt = null;
	private JTextField txtMa;
	private JComboBox cbokhachhang;
	private JComboBox cbonhanvien;
	private JDateChooser dateChooser;
	String[] cols={"Mã Hoá Đơn","Tên Khách Hàng", "Tên Nhan Vien","Ngày Xuất","Tổng Tiền"};
	private NhanVien_DAO nv_dao;
	private KhachHang_DAO kh_dao;
	private HoaDon_DAO hd_dao;
	private DefaultTableModel model;
	private JTable table;
	private JTextField tongtien;
	JButton jbxoa,jbsua,jbtimkiem,jbthem,jbxoatrang,jbexit,jbcapnhatt;
	public FromHoaDon() {
		// TODO Auto-generated constructor stub
		nv_dao= new NhanVien_DAO();
		kh_dao= new KhachHang_DAO();
		hd_dao = new HoaDon_DAO();
		ConnectDB.getInstance().connect();
		setTitle("Hoá Đơn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		setLocationRelativeTo(null);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.YELLOW);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Hoá Đơn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(250, 20, 500, 53);
		contentPane.add(lblNewLabel,BorderLayout.NORTH);

		JPanel panel_1 = new JPanel(new BorderLayout());
		contentPane.add(panel_1,BorderLayout.WEST);

		JLabel lblNewLabel_1 = new JLabel("Thông Tin Hoá Đơn");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		Box b = Box.createVerticalBox();
		Box b1,b2,b3,b4,b5,b6,b7;

		b.add(b7 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b7.add(lblNewLabel_1);
		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(new JLabel(" Mã HD:        "));
		b2.add(txtMa= new JTextField(17));

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(new JLabel(" Tên KH:       "));
		b3.add(cbokhachhang= new JComboBox());
		cbokhachhang.setEditable(false);
		ArrayList<KhachHang> listPB = kh_dao.getallKH();
		for (KhachHang p : listPB) {
			cbokhachhang.addItem(p.getTenkh());
		}

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(new JLabel(" Tên NV:       "));
		b1.add(cbonhanvien= new JComboBox());
		cbonhanvien.setEditable(false);
		ArrayList<NhanVien> listPB1 = nv_dao.getallNv();
		for (NhanVien p : listPB1) {
			cbonhanvien.addItem(p.getTennv());
		}

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(new JLabel(" Ngày Xuất: "));
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		b4.add(dateChooser);
		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(20));
		b5.add(new JLabel(" Tong Tien:     "));
		b5.add(tongtien= new JTextField(10));
		tongtien.setEditable(false);
		panel_1.add(b,BorderLayout.NORTH);
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		JSplitPane spl=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		spl.setBottomComponent(new JScrollPane(table));
		add(spl);

		JPanel Pduoi = new JPanel(new GridLayout(3, 1));
		jbthem = new JButton("Thêm");
	//	jbsua = new JButton("Sửa");
		jbxoa = new JButton("Xoá");
		jbxoatrang = new JButton("Xoá Trắng");
		jbtimkiem= new JButton("Xem Chi Tiết Hoá Đơn");
		jbexit = new JButton("quay lại");
		jbcapnhatt= new JButton("Cap nhat tong tien");
		Pduoi.add(jbthem);
	//	Pduoi.add(jbsua);
		Pduoi.add(jbxoa);
		Pduoi.add(jbtimkiem);
		Pduoi.add(jbxoatrang);
		Pduoi.add(jbexit);
		Pduoi.add(jbcapnhatt);
		panel_1.add(Pduoi,BorderLayout.SOUTH);
		docdulieudatavaobang();
		jbexit.addActionListener(this);
		jbthem.addActionListener(this);
		jbcapnhatt.addActionListener(this);
		table.addMouseListener(this);
		jbtimkiem.addActionListener(this);
		jbxoa.addActionListener(this);
		jbxoatrang.addActionListener(this);
	//	jbsua.addActionListener(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FromHoaDon().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMa.setText(model.getValueAt(row, 0).toString());
		cbokhachhang.setSelectedItem(model.getValueAt(row, 1).toString());
		cbonhanvien.setSelectedItem(model.getValueAt(row, 2).toString());
		dateChooser.setDate((java.util.Date) model.getValueAt(row, 3));
		tongtien.setText(model.getValueAt(row, 4).toString());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(jbexit)) {
			GiaoDienChinh info =new GiaoDienChinh();
			info.setVisible(true);
			//	info.pack();
			info.setLocationRelativeTo(null);
			info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();

		}
		if(o.equals(jbthem)) {
			if(txtMa.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Không Được Để Trống");
			}else {
				if(KiemTra()) {
				java.util.Date utilStartDate = dateChooser.getDate();
				java.sql.Date namsinh = new java.sql.Date(utilStartDate.getTime());
				String cv=cbokhachhang.getSelectedItem().toString();
				KhachHang chv= new KhachHang(cv);
				String v= cbonhanvien.getSelectedItem().toString();
				double t=0;
				// tongtien.setText(String.valueOf(t));
				NhanVien nv=new NhanVien(v);
				HoaDon hd= new HoaDon(txtMa.getText(), chv, nv, namsinh);

				if(!(hd_dao.create(hd))) {
					JOptionPane.showMessageDialog(this, "Trùng mã");
				}else 
				{
					hd_dao.create(hd);
					model.addRow(new Object[] {hd.getMaHD(),hd.getMaKH().getMakh(),hd.getMaNv().getManv(),hd.getNgayxuat(),hd.getTongtien() });
					JOptionPane.showMessageDialog(this, "Thêm Thành Công");	
					FromChiTietHoaDon info = new FromChiTietHoaDon();
					info.setVisible(true);
					//	info.pHD8ack();
					info.setLocationRelativeTo(null);
					info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();

				}}else
				{
					JOptionPane.showMessageDialog(this, "Thêm Không Thành Công");
				}
			}
		}
		if(o.equals(jbcapnhatt)) {
			Connection con = ConnectDB.getConnection();
			try {
				Statement st = (Statement) con.createStatement();
				String sqlCommand = "update " + "hoadon " + " set " +"hoadon.tongtien=" + "'" + hd_dao.tongtien(txtMa.getText()) + "' "
						+ "where hoadon.MaHD=" + "'" + txtMa.getText() + "' ;";

				model = (DefaultTableModel) table.getModel();
				model.setValueAt(hd_dao.tongtien(txtMa.getText()), table.getSelectedRow(), 4);
				st.execute(sqlCommand);
				//	docdulieudatavaobang();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(o.equals(jbtimkiem)) {
			FromChiTietHoaDon info = new FromChiTietHoaDon();
			info.setVisible(true);
			//	info.pHD8ack();
			info.setLocationRelativeTo(null);
			info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		}
		if(o.equals(jbexit)) {
			GiaoDienChinh info=new GiaoDienChinh();
			info.setVisible(true);
			//	info.pHD8ack();
			info.setLocationRelativeTo(null);
			info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		}
		if(o.equals(jbxoa)) {
			int r= table.getSelectedRow();
			model.removeRow(r); // xóa trong table model
			HoaDon lk=hd_dao.getelement(r);
			hd_dao.xoa(lk.getMaHD());
			JOptionPane.showMessageDialog(this, "Xoá Thành Công");
			Connection con = ConnectDB.getConnection();
			try {
				Statement st = (Statement) con.createStatement();
				String query = "DELETE FROM chitiethoadon WHERE maHoadon = '" + lk.getMaHD() + "'";

				//	model = (DefaultTableModel) table.getModel();
				//	model.setValueAt(hd_dao.tongtien(txtMa.getText()), table.getSelectedRow(), 4);
				st.execute(query);
				//	docdulieudatavaobang();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if(o.equals(jbxoatrang)) {
			txtMa.setText("");
			cbokhachhang.setSelectedItem("");
			cbonhanvien.setSelectedItem("");
			dateChooser.setDateFormatString("");
			tongtien.setText("");
			txtMa.requestFocus();
			
		}
		
	}
	private boolean KiemTra() {
		// TODO Auto-generated method stub
		String ma=txtMa.getText().trim();
		if(!(ma.matches("[H][D][0-9]+"))) {
			JOptionPane.showMessageDialog(this, "Nhập lại Theo Mẫu HD + Số Bất Kỳ VD:HD01237");
			return false;
		}
		return true;
	}

	private HoaDon re() {
		// TODO Auto-generated method stub
		String maHD= txtMa.getText();
		String kh=cbokhachhang.getSelectedItem().toString();
		KhachHang maKH= new KhachHang(kh);
		//String ten=txttennv.getText().trim();
		String cv=cbonhanvien.getSelectedItem().toString();
		NhanVien maNv= new NhanVien(cv);
		java.util.Date utilStartDate = dateChooser.getDate();
		java.sql.Date ngayxuat = new java.sql.Date(utilStartDate.getTime());
		Double tongtient=Double.parseDouble(tongtien.getText());
		HoaDon nv= new HoaDon(maHD, maKH, maNv, ngayxuat, tongtient);
		return nv;
		//		return null;
	}
	private void docdulieudatavaobang() {
		// TODO Auto-generated method stub

		List <HoaDon> list= hd_dao.getallKH();
		for(HoaDon hd :list)
		{
			model.addRow(new Object[] {hd.getMaHD(),hd.getMaKH().getMakh(),hd.getMaNv().getManv(),hd.getNgayxuat(),hd.getTongtien() });
		}
		table.setModel(model);
		table.setBounds(310, 100, 650, 500);

	}
}
