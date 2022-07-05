package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDon_DAO {
	public ArrayList<HoaDon> getallKH() {
		ArrayList<HoaDon> dsKh = new ArrayList<HoaDon>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql = "Select * from hoadon";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);

			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				String makh = rs.getString(1);
				KhachHang maKH= new KhachHang(rs.getString(2));
				NhanVien maNv= new NhanVien(rs.getString(3));
				Date ngayxuat=rs.getDate(4);
				Double tongtien=rs.getDouble(5);
				HoaDon lk = new HoaDon(makh, maKH, maNv, ngayxuat,tongtien);
				dsKh.add(lk);
			
			}
		} catch (SQLException e) {
			System.out.println("......");
		
		}
		return dsKh;
	}
	public HoaDon getelement(int index) {
		ArrayList<HoaDon> dsKh = new ArrayList<HoaDon>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql = "Select * from hoadon";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);

			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				String makh = rs.getString(1);
				KhachHang maKH= new KhachHang(rs.getString(2));
				NhanVien maNv= new NhanVien(rs.getString(3));
				Date ngayxuat=rs.getDate(4);
				Double tongtien=rs.getDouble(5);
				HoaDon lk = new HoaDon(makh, maKH, maNv, ngayxuat,tongtien);
				dsKh.add(lk);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		if(index<0 || index>dsKh.size())
			return null;
		return dsKh.get(index);
	}
	public boolean create(HoaDon cv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into hoadon values(?, ?, ?, ?, ?)");
			stmt.setString(1,cv.getMaHD());
			stmt.setString(2,cv.getMaKH().getMakh());
			stmt.setString(3, cv.getMaNv().getManv());
			stmt.setDate(4, (Date) cv.getNgayxuat());
			stmt.setDouble(5, cv.getTongtien());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("...........");
			}
		}
		return n > 0; 
	}
	public boolean update(HoaDon cv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update hoadon maKH = ?, maNv = ?, ngayxuat = ?,tongtien = ?  where mahd=? ");
			stmt.setString(1, cv.getMaKH().getTenkh());
			stmt.setString(2, cv.getMaNv().getTennv());
			stmt.setDate(3, (Date) cv.getNgayxuat());
			stmt.setDouble(4, cv.getTongtien());
			stmt.setString(5, cv.getMaHD());
			
			n = stmt.executeUpdate();


		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n > 0; 
	}
	public double tongtien(String ma) {
		double dg = 0;
		double tong=0;
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement statement = null;
			
			String sql = "Select * from chitiethoadon where maHoadon= ?";
			statement = con.prepareStatement(sql);
			statement.setString(1,ma);
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				dg = rs.getDouble(5);
				tong+=dg;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return tong;
	}
	public ArrayList<HoaDon> getNhanVienTheoMaNV(String id) {
		ArrayList<HoaDon> dsnv = new ArrayList<HoaDon>();
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement statement =null;
		try {


			String sql = "Select * From hoadon where maHoadon = ?";
			statement=con.prepareStatement(sql);
			statement.setString(1, id);
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);

			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				String makh = rs.getString(1);
				KhachHang maKH= new KhachHang(rs.getString(2));
				NhanVien maNv= new NhanVien(rs.getString(3));
				Date ngayxuat=rs.getDate(4);
				Double tongtien=rs.getDouble(5);
				HoaDon lk = new HoaDon(makh, maKH, maNv, ngayxuat,tongtien);
				dsnv.add(lk);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	return dsnv;
		
	}
	public boolean xoa(String lk) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from hoadon where mahd = ?");
			stmt.setString(1,lk);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("...........");
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n > 0; 
	}
}
