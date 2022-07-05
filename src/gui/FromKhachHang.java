package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.KhachHang_DAO;
import entity.ChucVu;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;

public class FromKhachHang extends JFrame implements ActionListener, MouseListener {
	JTextField txtMa,txtten,txtnamsinh,txtdiachi,txtsdt;
	JButton jbxoa,jbsua,jbtimkiem,jbthem,jbxoatrang,jbexit;
	DefaultTableModel model;
	JTable table;
	private JCheckBox chkNu;
	private JDateChooser dateChooser;
	String[] cols={"Mã Khách Hàng","Tên Khách Hàng", "Năm Sinh","Giới Tính","Địa Chỉ","Số Điện Thoại" };
	private KhachHang_DAO kh_dao;
	public FromKhachHang() {
		// TODO Auto-generated constructor stub
		setTitle("Quản Lý Khách Hàng");
		setResizable(true);
		setSize(900, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ConnectDB.getInstance().connect();
		kh_dao=  new KhachHang_DAO();
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		JPanel ptren = new JPanel();
		JLabel lblTitile = new JLabel("Quản Lý Khách Hàng");
		Font fTitle = new Font("tahoma", Font.BOLD,24);
		lblTitile.setFont(fTitle);
		ptren.add(lblTitile);
		add(ptren,BorderLayout.NORTH);

		Box b = Box.createVerticalBox();
		Box b1,b2,b3,b4,b5;

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(new JLabel(" Mã Khách Hàng:  "));
		b2.add(txtMa= new JTextField(10));

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(new JLabel(" Tên Khách Hàng:  "));
		b1.add(txtten= new JTextField(10));

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(new JLabel(" Năm Sinh:              "));
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		b3.add(dateChooser);
		b3.add(new JLabel("Phái: "));
		b3.add(chkNu = new JCheckBox("Nữ"));

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(new JLabel(" Địa Chỉ:                  "));
		b4.add(txtdiachi= new JTextField(10));

		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b5.add(new JLabel(" Số Điện Thoại:     "));
		b5.add(txtsdt= new JTextField(10));
		add(b,BorderLayout.CENTER);

		JSplitPane spl=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		spl.setTopComponent(b);
		spl.setBottomComponent(new JScrollPane(table));
		add(spl);
		JPanel Pduoi = new JPanel();
		jbthem = new JButton("Thêm");
		jbsua = new JButton("Sửa");
		jbxoa = new JButton("Xoá");
		jbxoatrang = new JButton("Xoá Trắng");
		jbtimkiem= new JButton("Tìm Kiếm");
		jbexit = new JButton("Trở Lại");
		Pduoi.add(jbthem);
		Pduoi.add(jbsua);
		Pduoi.add(jbxoa);
		Pduoi.add(jbxoatrang);
		Pduoi.add(jbexit);
		add(Pduoi,BorderLayout.SOUTH);
		DocDuLieuDatabaseVaoTable();
		jbthem.addActionListener(this);
		jbxoa.addActionListener(this);
		jbxoatrang.addActionListener(this);
		jbexit.addActionListener(this);
		jbsua.addActionListener(this);
		table.addMouseListener(this);

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FromKhachHang().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMa.setText(model.getValueAt(row, 0).toString());
		txtten.setText(model.getValueAt(row, 1).toString());
		dateChooser.setDate((java.util.Date) model.getValueAt(row, 2));
		String phai = model.getValueAt(row, 3).toString();
		if(phai.equals("Nam"))
		{
			chkNu.setSelected(false);
		}
		else
		{
			chkNu.setSelected(true);
		}
		//chkNu.setSelected(model.getValueAt(row, 3) == "true" ? true : false);
		txtdiachi.setText(model.getValueAt(row, 4).toString());
		txtsdt.setText(model.getValueAt(row, 5).toString());

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
		if (o.equals(jbthem)) {
			if(txtten.getText().equals("")|| txtMa.getText().equals("")||txtsdt.getText().equals("")||txtdiachi.getText().equals(""))

				JOptionPane.showMessageDialog(this, "Không được để trống");
			else {
				if(KiemTra()) {
					java.util.Date utilStartDate = dateChooser.getDate();
					java.sql.Date namsinh = new java.sql.Date(utilStartDate.getTime());
					boolean phai = chkNu.isSelected();
					KhachHang cv= new KhachHang(txtMa.getText(), txtten.getText(), namsinh, phai, txtdiachi.getText(), txtsdt.getText());
					if(!(kh_dao.create(cv))) {
						JOptionPane.showMessageDialog(this, "Trùng Mã");
					}else {
						kh_dao.create(cv);
						String gt="Nam";

						if(cv.isGioiTinh()==true) {
							gt="Nữ";
						}
						model.addRow(new Object[] {
								cv.getMakh(),cv.getTenkh(),cv.getNamsinh(),gt,cv.getDiachi(),cv.getSdt()
						});
						JOptionPane.showMessageDialog(this, "Thêm Thành Công");
					}
				}}}
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
			KhachHang lk=kh_dao.getElement(r);
			kh_dao.xoa(lk.getMakh());
		}if(o.equals(jbsua)) {
			int row = table.getSelectedRow();
			if(txtten.getText().equals("")|| txtMa.getText().equals("")||txtsdt.getText().equals("")||txtdiachi.getText().equals(""))
			{	
				JOptionPane.showMessageDialog(this, "Không được để trống");
			}
			else 
				if(row>=0) {
					if (KiemTra()) {
						KhachHang c= re();
						if(kh_dao.update(c)) {
							table.setValueAt(txtten.getText(), row, 1);
							java.util.Date utilStartDate = dateChooser.getDate();
							java.sql.Date namsinh = new java.sql.Date(utilStartDate.getTime());
							table.setValueAt(namsinh, row, 2);
							String gt="Nam";

							if(c.isGioiTinh()==true) {
								gt="Nữ";
							}
							table.setValueAt(gt, row, 3);
							table.setValueAt(txtdiachi.getText(), row, 4);
							table.setValueAt(txtsdt.getText(), row, 5);
							JOptionPane.showMessageDialog(this, "Sửa Thành Công");
						
						}else
							JOptionPane.showMessageDialog(this, "Sửa Không Thành Công ");
				}}else
					JOptionPane.showMessageDialog(this, "chưa chọn đối tượng để Sửa");
		}
		if(o.equals(jbxoatrang))

		{
			txtMa.setText("");
			txtten.setText("");
			txtdiachi.setText("");
			txtsdt.setText("");
			chkNu.setSelected(false);
			dateChooser.setDateFormatString("");
			
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
	private  KhachHang re() 
	{
		String ma=txtMa.getText().trim();
		String ten=txtten.getText().trim();
		java.util.Date utilStartDate = dateChooser.getDate();
		java.sql.Date namsinh = new java.sql.Date(utilStartDate.getTime());
		boolean phai = chkNu.isSelected();
		String Diachi=txtdiachi.getText();
		String sdt= txtsdt.getText();
		KhachHang kh= new KhachHang(ma, ten, namsinh, phai, Diachi, sdt);
		return kh;
	}
	private boolean KiemTra() {
		String ma=txtMa.getText().trim();
		String sdt= txtsdt.getText();
		if(!(ma.matches("[K][H][0-9]+"))) {
			JOptionPane.showMessageDialog(this, "Nhập lại Theo Mẫu KH + Số Bất Kỳ VD:KH01237");
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
		if(!(txtdiachi.getText().trim().length()>10)) {
			JOptionPane.showMessageDialog(this, "địa chỉ phải từ 10 ký tự trở lên");
			return false;
		}
		return true;
	}
}
