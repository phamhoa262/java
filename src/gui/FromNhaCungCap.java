package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.LoaiLK_DAO;
import dao.NhaCungCap_DAO;
import entity.KhachHang;
import entity.LoaiLK;
import entity.NhaCungCap;

public class FromNhaCungCap extends JFrame implements ActionListener,MouseListener{
	JTextField txtMancc,txttenncc,txtdiachi,txtemail,txtsdt;
	JButton jbxoa,jbsua,jbtimkiem,jbthem,jbxoatrang,jbexit;
	DefaultTableModel model;
	JTable table;
	String[] cols={"Mã Nhà Cung Cấp","Tên Nhà Cung Cấp","Địa Chỉ","Email","Số Điện Thoại" };
	private NhaCungCap_DAO Nhacc_dao;
	public FromNhaCungCap() {
		// TODO Auto-generated constructor stub
		setTitle("Quản Lý Loại Linh Kiện");
		//setResizable(false);
		setSize(900, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ConnectDB.getInstance().connect();
		Nhacc_dao=new NhaCungCap_DAO();
		// CREATE TABLE FOR EDIT
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		JPanel ptren = new JPanel();
		JLabel lblTitile = new JLabel("Quản Lý Nhà Cung Cấp");
		Font fTitle = new Font("tahoma", Font.BOLD,24);
		lblTitile.setFont(fTitle);
		ptren.add(lblTitile);
		add(ptren,BorderLayout.NORTH);


		Box b = Box.createVerticalBox();
		Box b1,b2,b3,b4,b5;
		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(new JLabel(" Mã NCC:  "));
		b2.add(txtMancc= new JTextField(10));

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(new JLabel(" Tên NCC: "));
		b1.add(txttenncc = new JTextField(10));

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(new JLabel(" Địa Chỉ:   "));
		b3.add(txtdiachi = new JTextField(10));

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(new JLabel(" Email:      "));
		b4.add(txtemail = new JTextField(10));

		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b5.add(new JLabel(" Số Điện Thoại: "));
		b5.add(txtsdt = new JTextField(10));
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
		jbexit = new JButton("Exit");
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
	private void DocDuLieuDatabaseVaoTable() {
		// TODO Auto-generated method stub
		List<NhaCungCap> list = Nhacc_dao.getalltbncc();
		for (NhaCungCap cv : list) {
			model.addRow(new Object[] {
					cv.getMaNcc(),cv.getTenncc(),cv.getDiachi(),cv.getEmail(),cv.getSdt()
			});}

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMancc.setText(model.getValueAt(row, 0).toString());
		txttenncc.setText(model.getValueAt(row, 1).toString());
		txtdiachi.setText(model.getValueAt(row, 2).toString());
		txtemail.setText(model.getValueAt(row, 3).toString());
		txtsdt.setText(model.getValueAt(row, 4).toString());

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FromNhaCungCap().setVisible(true);

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
			String mancc =txtMancc.getText();
			String tenncc = txttenncc.getText();
			String diachi=txtdiachi.getText();
			String email=txtemail.getText();
			String sdt= txtsdt.getText();
			if(txtMancc.getText().trim().equals("") || txttenncc.getText().trim().equals("")||txtdiachi.getText().trim().equals("")||txtemail.getText().trim().equals("")||txtsdt.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Không được để trống");
			}else {
				if(KiemTra()) {
					NhaCungCap ncc= new NhaCungCap(mancc, tenncc, diachi, email, sdt);
					if(!(Nhacc_dao.create(ncc))) {
						JOptionPane.showMessageDialog(this, "Trùng mã");
					}
					else {
						Nhacc_dao.create(ncc);
						model.addRow(new Object[] {
								ncc.getMaNcc(),ncc.getTenncc(),ncc.getDiachi(),ncc.getEmail(),ncc.getSdt()
						});
					}}}}
		if(o.equals(jbxoa)) {
			int r = table.getSelectedRow();
			model.removeRow(r); // xóa trong table model
			NhaCungCap nv=Nhacc_dao.getElement(r);
			Nhacc_dao.xoa(nv.getMaNcc());

		}
		if(o.equals(jbxoatrang)) {
			txtMancc.setText("");
			txttenncc.setText("");
			txtdiachi.setText("");
			txtemail.setText("");
			txtsdt.setText("");
			txtMancc.requestFocus();
		}if(o.equals(jbsua)) 
		{

			int row = table.getSelectedRow();
			if(txtMancc.getText().trim().equals("") || txttenncc.getText().trim().equals("")||txtdiachi.getText().trim().equals("")||txtemail.getText().trim().equals("")||txtsdt.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Không được để trống");
			}
			else 
				if(row>=0) {
					if (KiemTra()) {
						NhaCungCap c= re();
						if(Nhacc_dao.update(c)) {
							table.setValueAt(txttenncc.getText(), row, 1);
							table.setValueAt(txtdiachi.getText(), row, 2);
							table.setValueAt(txtemail.getText(), row, 3);
							table.setValueAt(txtsdt.getText(), row, 4);
					//		table.setValueAt(txtMancc.getText(), row, 5);
							JOptionPane.showMessageDialog(this, "Sửa Thành Công");
						}
					}else
						JOptionPane.showMessageDialog(this, "Sửa Không Thành Công ");
				}else
					JOptionPane.showMessageDialog(this, "chưa chọn đối tượng để Sửa");
		}
		if(o.equals(jbexit)) {
			FromLinhKien info =new FromLinhKien();
			info.setVisible(true);
			//	info.pack();
			info.setLocationRelativeTo(null);
			info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		}

	}
	private NhaCungCap re() {
		// TODO Auto-generated method stub
		String ma= txtMancc.getText().trim();
		String ten=txttenncc.getText().trim();
		String diachi=txtdiachi.getText().trim();
		String email=txtemail.getText().trim();
		String sdt=txtsdt.getText().trim();

		NhaCungCap ncc= new NhaCungCap(ma, ten, diachi, email, sdt);
		return ncc;
	}
	private boolean KiemTra() {
		// TODO Auto-generated method stub
		String sdt= txtsdt.getText();
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
		if(!(txtdiachi.getText().length()>4)) {
			JOptionPane.showMessageDialog(this, "địa chỉ phải từ 4 ký tự trở lên");
			return false;
		}
		return true;
	}
}



