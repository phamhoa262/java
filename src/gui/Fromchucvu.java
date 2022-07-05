package gui;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import javax.sound.sampled.ReverbType;
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
import dao.ChucVu_DAO;
import entity.ChucVu;
import entity.NhanVien;

public class Fromchucvu extends JFrame implements ActionListener ,MouseListener{
	JTextField txtManv,txtHo;
	JButton jbxoa,jbsua,jbtimkiem,jbthem,jbxoatrang,jbexit;
	DefaultTableModel model;
	JTable table;
	String[] cols={"Mã Chức Vụ", "Tên Chức Vụ" };
	private ChucVu_DAO cv_dao;

	public Fromchucvu() {
		// TODO Auto-generated constructor stub
		setTitle("Quản Lý Chức Vụ");
		setResizable(false);
		setSize(500, 500);
		setBounds(100, 100, 1000, 650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ConnectDB.getInstance().connect();
		cv_dao=new ChucVu_DAO();
		// CREATE TABLE FOR EDIT
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		//JFrame f= new JFrame();
		//add(f);
		//	JPanel f= new JPanel(new GridLayout(3, 1));
		JPanel ptren = new JPanel();
		JLabel lblTitile = new JLabel("Quản Lý Chức Vụ");
		Font fTitle = new Font("tahoma", Font.BOLD,24);
		lblTitile.setFont(fTitle);
		ptren.add(lblTitile);
		add(ptren,BorderLayout.NORTH);


		Box b = Box.createVerticalBox();
		Box b1,b2;
		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(new JLabel(" Mã Chức Vụ:  "));
		b2.add(txtManv= new JTextField(10));

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(new JLabel(" Tên Chức Vụ: "));
		b1.add(txtHo = new JTextField(10));
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Fromchucvu().setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(jbthem)) {
			String ma =txtManv.getText();
			String cv = txtHo.getText();
			ChucVu cv1 = new ChucVu(ma, cv);
			if(txtHo.getText().trim().equals("") || txtManv.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Không được để trống");
			}else
				if(!(cv_dao.create(cv1))) {
					JOptionPane.showMessageDialog(this, "Trùng mã");
				}
				else {
					cv_dao.create(cv1);
					model.addRow(new Object[] {
							cv1.getMacv(),cv1.getTencv()
					});
				}}
		if(o.equals(jbxoa)) {

			int r = table.getSelectedRow();
			if(r>=0) {
				model.removeRow(r); // xóa trong table model
				ChucVu nv=cv_dao.getElement(r);
				cv_dao.xoa(nv.getMacv());
				JOptionPane.showMessageDialog(this, "Xoá Thành Công");
			}else
				JOptionPane.showMessageDialog(this, "Xoá Không Thành Công hoặc chưa có đối tượng để xoá");

		}
		if(o.equals(jbxoatrang)) {
			txtHo.setText("");
			txtManv.setText("");
			txtManv.requestFocus();
		}if(o.equals(jbexit)) {
			FromNhanVien info=new FromNhanVien();
			info.setVisible(true);
			//	info.pHD8ack();
			info.setLocationRelativeTo(null);
			info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		}if(o.equals(jbsua)) {
			int row = table.getSelectedRow();
			if(txtHo.getText().trim().equals("") || txtManv.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Không được để trống");
			}else
			if(row>=0) {
				ChucVu c= re();
				if(cv_dao.update(c)) {
					table.setValueAt(txtHo.getText(), row, 1);
					JOptionPane.showMessageDialog(this, "Sửa Thành Công");
				}else
					JOptionPane.showMessageDialog(this, "Không Được sửa mã ");
			}else
				JOptionPane.showMessageDialog(this, "chưa chọn đối tượng xoá");
		}
	}

	public void DocDuLieuDatabaseVaoTable() {
		List<ChucVu> list = cv_dao.getalltbChucVu();
		for (ChucVu cv : list) {
			model.addRow(new Object[] {
					cv.getMacv(),cv.getTencv()
			});}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtManv.setText(model.getValueAt(row, 0).toString());
		txtHo.setText(model.getValueAt(row, 1).toString());
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
	private  ChucVu re() {
		String ma=txtManv.getText().trim();
		String ten=txtHo.getText().trim();

		return new ChucVu(ma, ten);
	}
	private void xoahetdulieutrongbang() {
		DefaultTableModel md=(DefaultTableModel) table.getModel();
		md.getDataVector().removeAllElements();
	}
}
