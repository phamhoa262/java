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

import connectDB.ConnectDB;
import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.LinhKien_DAO;
import dao.NhanVien_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;

public class FromChiTietHoaDon extends JFrame implements ActionListener,MouseListener {
	private JPanel contentPane;


	PreparedStatement preStmt = null;

	private JComboBox cbomahd;
	private JComboBox tmahd;
	private JComboBox cbomalk;
	private JComboBox cbodongia;
	DefaultTableModel model;
	JTable table;
	private JTextField jbdongia;
	private JTextField soluong;
	private JTextField jbthanhtien;
	String[] cols={"Mã Hoá Đơn","Mã Sản Phẩm", "Đơn Giá","Số Lượng","Thành tiền"};
	private LinhKien_DAO lk_dao;
	private HoaDon_DAO hd_dao;
	private ChiTietHoaDon_DAO ct_dao;
	JButton jbxoa,jbsua,jbtimkiem,jbthem,jbxoatrang,jbexit;
	public FromChiTietHoaDon() {
		// TODO Auto-generated constructor stub
		lk_dao = new LinhKien_DAO();
		hd_dao = new HoaDon_DAO();
		ct_dao = new ChiTietHoaDon_DAO();
		ConnectDB.getInstance().connect();
		setTitle("Hoá Đơn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		setLocationRelativeTo(null);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.YELLOW);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Chi Tiết Hoá Đơn");
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
		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(new JLabel(" Mã HD:            "));
		b1.add(cbomahd= new JComboBox());
		cbomahd.setEditable(false);
		ArrayList<HoaDon> listPB1 = hd_dao.getallKH();
		for (HoaDon p : listPB1) {
			cbomahd.addItem(p.getMaHD());

		}

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(20));
		b3.add(new JLabel(" Mã LK:            "));
		b3.add(cbomalk= new JComboBox());
		cbomalk.setEditable(false);
		ArrayList<LinhKien> listPB = lk_dao.getallLK();
		for (LinhKien p : listPB) {
			cbomalk.addItem(p.getMalk());
		}
		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(20));
		b4.add(new JLabel(" Đơn Giá:         "));
		b4.add(jbdongia= new JTextField(10));
		jbdongia.setEditable(false);

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(20));
		b2.add(new JLabel(" Số Lượng:     "));
		b2.add(soluong= new JTextField(10));

		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(20));
		b5.add(new JLabel(" Thành Tiền:     "));
		b5.add(jbthanhtien= new JTextField(10));
		jbthanhtien.setEditable(false);
		panel_1.add(b,BorderLayout.NORTH);
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		JSplitPane spl=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		//spl.setTopComponent(b);
		spl.setBottomComponent(new JScrollPane(table));
		add(spl);
		JPanel Pduoi = new JPanel(new GridLayout(2,1));
		jbthem = new JButton("Thêm");
		jbsua = new JButton("Sửa");
		jbxoa = new JButton("Xoá");
		jbxoatrang = new JButton("Xoá Trắng");
		jbtimkiem= new JButton("Tìm Kiếm");
		jbexit = new JButton("Trở Về Hoá Đơn");
		docdulieudatavaobang();
		Pduoi.add(jbthem);
		Pduoi.add(jbsua);
		Pduoi.add(jbxoa);
		Pduoi.add(jbxoatrang);
		Pduoi.add(jbexit);
		Pduoi.add(jbtimkiem);
		panel_1.add(Pduoi,BorderLayout.SOUTH);
		cbomalk.addActionListener(this);
		soluong.addActionListener(this);
		jbthem.addActionListener(this);
		jbexit.addActionListener(this);
		table.addMouseListener(this);
		jbsua.addActionListener(this);
		jbxoa.addActionListener(this);
		jbxoatrang.addActionListener(this);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FromChiTietHoaDon().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(cbomalk)) {
			String ma=cbomalk.getSelectedItem().toString();
			LinhKien lkj= new LinhKien(ma);
			double x= lk_dao.dongia(lkj.getMalk());
			jbdongia.setText(String.valueOf(x));
		}
		if(o.equals(soluong)) {
			long x= (long) (Integer.parseInt(soluong.getText())*Double.parseDouble(jbdongia.getText()));
			jbthanhtien.setText(String.valueOf(x));
		}
		if(o.equals(jbthem)) {
			if(soluong.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Không Được để trống");
			}else {
				String c= cbomahd.getSelectedItem().toString();
				HoaDon hd= new HoaDon(c);
				String k= cbomalk.getSelectedItem().toString();
				LinhKien lk= new LinhKien(k);
				ChiTietHoaDon ct= new ChiTietHoaDon(hd, lk, Double.parseDouble(jbdongia.getText()), Integer.parseInt(soluong.getText()), Double.parseDouble(jbthanhtien.getText()));
				ct_dao.create(ct);
				model.addRow(new Object[] {
						ct.getMaHoadon().getMaHD(),ct.getMaLK().getMalk(),ct.getDongia(),ct.getSoluong(),ct.getThanhtien()
				});

				JOptionPane.showMessageDialog(this, "Thêm Thành Công");
			}
		}
		if(o.equals(jbexit)) {
			FromHoaDon info = new FromHoaDon();
			info.setVisible(true);
			info.setLocationRelativeTo(null);
			info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		}
		if(o.equals(jbsua)) {
			int row = table.getSelectedRow();
			if(soluong.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Không Được Để Trống");
			}else if(row>=0) {
				ChiTietHoaDon c= re();
				if(ct_dao.update(c)) {
					table.setValueAt(cbomalk.getSelectedItem(), row, 1);
				//	table.setValueAt(jbdongia.getText(), row, 2);
					table.setValueAt(soluong.getText(), row, 3);
					table.setValueAt(jbthanhtien.getText(), row, 4);
					JOptionPane.showMessageDialog(this, "Sửa Thành Công");
				}
				else
					JOptionPane.showMessageDialog(this, "Sửa Không Thành Công ");
				
			}}
			
		if(o.equals(jbxoa)) {
			int r= table.getSelectedRow();
			model.removeRow(r); // xóa trong table model
			ChiTietHoaDon lk= ct_dao.gete(r);
			ct_dao.xoa(lk.getMaHoadon().getMaHD(),lk.getMaLK().getMalk());
			JOptionPane.showMessageDialog(this, "Xoá Thành Công");
			

		}
		if(o.equals(jbxoatrang)) {
			cbomahd.setSelectedItem("");
			cbomalk.setSelectedItem("");
			jbdongia.setText("");
			jbthanhtien.setText("");
			soluong.setText("");
			cbomahd.requestFocus();
		}
		}
	
	private void docdulieudatavaobang() {
		// TODO Auto-generated method stub

		List <ChiTietHoaDon> list= ct_dao.getallKH();
		for(ChiTietHoaDon ct :list)
		{
			model.addRow(new Object[] {
					ct.getMaHoadon().getMaHD(),ct.getMaLK().getMalk(),ct.getDongia(),ct.getSoluong(),ct.getThanhtien()
			});
		}
		//	table.setModel(model);
		//		table.setBounds(310, 100, 650, 500);

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		//txtMa.setText(model.getValueAt(row, 0).toString());
		cbomahd.setSelectedItem(model.getValueAt(row, 0).toString());
		cbomalk.setSelectedItem(model.getValueAt(row, 1).toString());
		//jbdongia.setDate((java.util.Date) model.getValueAt(row, 3));
		jbdongia.setText(model.getValueAt(row, 2).toString());
		soluong.setText(model.getValueAt(row, 3).toString());
		jbthanhtien.setText(model.getValueAt(row, 4).toString());
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
	private ChiTietHoaDon re() {
		// TODO Auto-generated method stub
		//String maHD= txtMa.getText();
		String kh=cbomahd.getSelectedItem().toString();
		HoaDon maHD= new HoaDon(kh);
		String lk=cbomalk.getSelectedItem().toString();
		LinhKien maLK= new LinhKien(lk);
		//String ten=txttennv.getText().trim();
		Double dongia=Double.parseDouble(jbdongia.getText());
		int soluong1= Integer.parseInt(soluong.getText());
		Double thanhtien=Double.parseDouble(jbthanhtien.getText());
	
		ChiTietHoaDon nv= new ChiTietHoaDon(maHD, maLK, dongia, soluong1, thanhtien);
		return nv;
		//		return null;
	}
}
