package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.ChucVu_DAO;
import dao.LinhKien_DAO;
import dao.LoaiLK_DAO;
import dao.NhaCungCap_DAO;
import dao.NhanVien_DAO;
import entity.ChucVu;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhaCungCap;
import entity.NhanVien;

public class FromNhanVien extends JFrame implements ActionListener ,MouseListener{
	private JPanel contentPane;


	PreparedStatement preStmt = null;
	private JTextField txtMa;
	private JTextField txttennv;
	private JComboBox chucvu;
	private JDateChooser dateChooser;
	private JCheckBox chkNu;
	private JTextField txtdiachi;
	private JTextField txtsdt;
	private DefaultTableModel model;
	private JTextField txtngaynhap;
	LinhKien_DAO dslk = new LinhKien_DAO();
	private NhanVien_DAO nv_dao;
	private ChucVu_DAO cv_dao;
	private JTable table;
	private JButton btnxoa;
	Connection con = null;
	Statement st = null;
	JButton jbxoa,jbsua,jbtimkiem,jbthem,jbxoatrang,jbexit;
	String[] cols={"Mã Nhân Viên","Tên Nhân Viên", "Chức vụ","Năm Sinh","Giới Tính","Địa Chỉ","Số Điện Thoại" };
	//private JComboBox<String> cbomacc;
	private JComboBox<String> cboloailk;
	/**
	 * Launch the application.
	 */

	private JTextField textmacungcap;

	private JButton btnAdd;
	private JButton btnFix;
	private JButton btnxoatrang;
	private JButton btnthoat;

	private JTextField txtTen;




	private JComboBox<String> cbomcv;



	NhanVien_DAO dsnv = new NhanVien_DAO();



	private ChucVu_DAO cv_Dao;


	private NhanVien_DAO nv_Dao;


	private JTextField txtdc;



	private JButton btncv;


	private JLabel jlblCv;


	private JLabel jlbl1;


	private Component jlbl2;


	private Component jlbl3;


	private Component jlbl4;


	private Component jlbl5;


	private JLabel jlbl6;


	private Component jlbl7;
	public FromNhanVien() {
		// TODO Auto-generated constructor stub
		ConnectDB.getInstance().connect();
		nv_dao= new NhanVien_DAO();
		cv_dao= new ChucVu_DAO();
		setTitle("Thông tin nhân viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		setLocationRelativeTo(null);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.YELLOW);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Quản Lý NHÂN VIÊN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(250, 20, 500, 53);
		//lblNewLabel.setBackground(Color.BLUE);
		contentPane.add(lblNewLabel,BorderLayout.NORTH);

		JPanel panel_1 = new JPanel(new BorderLayout());
		contentPane.add(panel_1,BorderLayout.WEST);

		JLabel lblNewLabel_1 = new JLabel("Thông tin Nhân Viên");
		//	panel_1.add(lblNewLabel_1,BorderLayout.NORTH);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));

		Box b = Box.createVerticalBox();
		Box b1,b2,b3,b4,b5,b6,b7;

		b.add(b7 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b7.add(lblNewLabel_1);
		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(new JLabel(" Mã NV:     "));
		b2.add(txtMa= new JTextField(17));

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(new JLabel(" Tên NV:    "));
		b1.add(txttennv= new JTextField(17));

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(new JLabel(" Chức Vụ:  "));
		b3.add(chucvu= new JComboBox());
		chucvu.setEditable(true);
		ArrayList<ChucVu> listPB = cv_dao.getalltbChucVu();
		for (ChucVu p : listPB) {
			chucvu.addItem(p.getMacv());
		}


		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(new JLabel(" Ngay Vao: "));
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		b4.add(dateChooser);
		b4.add(new JLabel(" Phái: "));
		b4.add(chkNu = new JCheckBox("Nữ"));


		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b5.add(new JLabel(" Địa Chỉ:     "));
		b5.add(txtdiachi= new JTextField(10));

		b.add(b6 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b6.add(new JLabel(" Số Điện Thoại:     "));
		b6.add(txtsdt= new JTextField(10));


		b.add(b7 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		btncv= new JButton();
		btncv.setBounds(20, 300, 100, 80);
		btncv.setIcon(new ImageIcon("img/cc.jpg"));
		panel_1.add(btncv);

		jlblCv= new JLabel("QUẢN LÝ CHỨC VỤ");

		jlblCv.setForeground(Color.BLUE);
		jlblCv.setBounds(140, 310, 180, 50);		
		b7.add(jlblCv);	
		panel_1.add(b,BorderLayout.NORTH);

		JPanel Pduoi = new JPanel();
		jbthem = new JButton("Thêm");
		jbsua = new JButton("Sửa");
		jbxoa = new JButton("Xoá");
		jbxoatrang = new JButton("Xoá Trắng");
	//	jbtimkiem= new JButton("Tìm Kiếm");
		jbexit = new JButton("Trở Lại");
		Pduoi.add(jbthem);
		Pduoi.add(jbsua);
		Pduoi.add(jbxoa);
		Pduoi.add(jbxoatrang);
		Pduoi.add(jbexit);
	//	Pduoi.add(jbtimkiem);
		panel_1.add(Pduoi,BorderLayout.SOUTH);
		//	DocDuLieuDatabaseVaoTable();
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		JSplitPane spl=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		spl.setBottomComponent(new JScrollPane(table));
		add(spl);
		DocDuLieuDatabaseVaoTable();
		jbexit.addActionListener(this);
		jbthem.addActionListener(this);
		jbsua.addActionListener(this);
		jbxoatrang.addActionListener(this);
		jbxoa.addActionListener(this);
		table.addMouseListener(this);
		btncv.addActionListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMa.setText(model.getValueAt(row, 0).toString());
		txttennv.setText(model.getValueAt(row, 1).toString());
		chucvu.setSelectedItem(model.getValueAt(row, 2).toString());
		dateChooser.setDate((java.util.Date) model.getValueAt(row, 3));
		String phai = model.getValueAt(row, 4).toString();
		if(phai.equals("Nam"))
		{
			chkNu.setSelected(false);
		}
		else
		{
			chkNu.setSelected(true);
		}
		txtdiachi.setText(model.getValueAt(row, 5).toString());
		txtsdt.setText(model.getValueAt(row, 6).toString());

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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
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
			if(txtMa.getText().equals("")||txttennv.getText().equals("")||txtdiachi.getText().equals("")||txtsdt.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "khong de trong");
			}else {
				if(kiemtra()) {
					String cv=chucvu.getSelectedItem().toString();
					ChucVu chv= new ChucVu(cv);
					java.util.Date utilStartDate = dateChooser.getDate();
					java.sql.Date ngayvao = new java.sql.Date(utilStartDate.getTime());
					boolean phai = chkNu.isSelected();
					NhanVien nv= new NhanVien(txtMa.getText(), txttennv.getText(), chv, ngayvao, chkNu.isSelected(), txtdiachi.getText(), txtsdt.getText());
					if(!(nv_dao.create(nv))) {
						JOptionPane.showMessageDialog(this, "Trung ma");
					}else {
						nv_dao.create(nv);
						String gt="Nam";
						if(nv.isGioitinh()==true) {
							gt="Nữ";
						}
						model.addRow(new Object[] {
								nv.getManv(),nv.getTennv(),nv.getCv().getMacv(),nv.getNgayvao(),gt,nv.getDiachi(),nv.getSdt()
						});

						JOptionPane.showMessageDialog(this, "Them  thanh cong");
					}}else
					{
						JOptionPane.showMessageDialog(this, "Them Khong thanh cong");
					}
			}}
		if(o.equals(jbxoa)) {
			int r= table.getSelectedRow();
			model.removeRow(r); // xóa trong table model
			NhanVien lk=nv_dao.getelement(r);
			nv_dao.xoa(lk.getManv());
		}
		if(o.equals(jbsua)) {
			int row = table.getSelectedRow();
			if(txttennv.getText().equals("")||txtdiachi.getText().equals("")||txtsdt.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "khong de trong");
			}
			else 
				if(row>=0) {
					if (kiemtra()) {
						NhanVien c= re();
						if(nv_dao.update(c)) {
							table.setValueAt(txttennv.getText(), row, 1);
							table.setValueAt(chucvu.getSelectedItem(), row, 2);
							java.util.Date utilStartDate = dateChooser.getDate();
							java.sql.Date namsinh = new java.sql.Date(utilStartDate.getTime());
							table.setValueAt(namsinh, row, 3);
							String gt="Nam";

							if(c.isGioitinh()==true) {
								gt="Nữ";
							}
							table.setValueAt(gt, row, 4);
							table.setValueAt(txtdiachi.getText(), row, 5);
							table.setValueAt(txtsdt.getText(), row, 6);
							JOptionPane.showMessageDialog(this, "Sửa Thành Công");
						}
						else
							JOptionPane.showMessageDialog(this, "Sửa Không Thành Công ");
					}}else
						JOptionPane.showMessageDialog(this, "chưa chọn đối tượng để Sửa");

		}
		if(o.equals(btncv)) {
			Fromchucvu info =new Fromchucvu();
			info.setVisible(true);
			//	info.pack();
			info.setLocationRelativeTo(null);
			info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();


		}
	}
	private NhanVien re() {
		// TODO Auto-generated method stub
		String ma=txtMa.getText().trim();
		String ten=txttennv.getText().trim();
		String cv=chucvu.getSelectedItem().toString();
		ChucVu chv= new ChucVu(cv);
		java.util.Date utilStartDate = dateChooser.getDate();
		java.sql.Date namsinh = new java.sql.Date(utilStartDate.getTime());
		boolean phai = chkNu.isSelected();
		String Diachi=txtdiachi.getText();
		String sdt= txtsdt.getText();
		NhanVien nv= new NhanVien(ma, ten, chv, namsinh, phai, Diachi, sdt);
		return nv;
		//		return null;
	}
	private boolean kiemtra() {
		String ma=txtMa.getText().trim();
		String sdt= txtsdt.getText();
		String diachi = txtdiachi.getText();
		if(!(ma.matches("[N][V][0-9]+"))) {
			JOptionPane.showMessageDialog(this, "Nhập lại Theo Mẫu KH + Số Bất Kỳ VD:NV01237");
			return false;
		}
		if(!(sdt.length()==10)) {
			JOptionPane.showMessageDialog(this, "Số Điện thoại Phải là 10 số");
			return false;
		}
		if (sdt.length() > 0) {
			try {
				int x = Integer.parseInt(sdt);
				if (x <= 0) {
					JOptionPane.showMessageDialog(null, "Nhập Lại: Số Điện Thoại Phải là Số Nguyên Dương");
					return false;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "nhập Lại: Số Điện Thoại Phải là Số");

				return false;
			}}
		if(!(diachi.length()>10)) {
			JOptionPane.showMessageDialog(this, "địa chỉ phải từ 10 ký tự trở lên");
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		new FromNhanVien().setVisible(true);
	}
	public void DocDuLieuDatabaseVaoTable() {
		List<NhanVien> list = nv_dao.getallNv();
		for (NhanVien nv : list) {
			//	String gt="Nam";

			String gt="Nam";
			if(nv.isGioitinh()==true) {
				gt="Nữ";
			}
			model.addRow(new Object[] {
					nv.getManv(),nv.getTennv(),nv.getCv().getMacv(),nv.getNgayvao(),gt,nv.getDiachi(),nv.getSdt()
			});}
	}

}
