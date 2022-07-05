package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.LinhKien;
import entity.NhanVien;

public class ChiTietHoaDon_DAO {
	public ArrayList<ChiTietHoaDon> getallKH() {
		ArrayList<ChiTietHoaDon> dsKh = new ArrayList<ChiTietHoaDon>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql = "Select * from chitiethoadon";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);

			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				HoaDon maHoadon = new HoaDon(rs.getString(1));
				LinhKien maLK= new LinhKien(rs.getString(2));
				Double dongia=rs.getDouble(3);
				int soluong=rs.getInt(4);
				Double thanhtien=rs.getDouble(5);
				ChiTietHoaDon lk = new ChiTietHoaDon(maHoadon, maLK, dongia, soluong, thanhtien);
				dsKh.add(lk);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return dsKh;
	}
	public ChiTietHoaDon gete(int index) {
		ArrayList<ChiTietHoaDon> dsKh = new ArrayList<ChiTietHoaDon>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql = "Select * from chitiethoadon";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);

			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				HoaDon maHoadon = new HoaDon(rs.getString(1));
				LinhKien maLK= new LinhKien(rs.getString(2));
				Double dongia=rs.getDouble(3);
				int soluong=rs.getInt(4);
				Double thanhtien=rs.getDouble(5);
				ChiTietHoaDon lk = new ChiTietHoaDon(maHoadon, maLK, dongia, soluong, thanhtien);
				dsKh.add(lk);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		if(index<0 || index>dsKh.size())
			return null;
		return dsKh.get(index);
	}
	public boolean create(ChiTietHoaDon cv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into chitiethoadon values(?, ?, ?, ?, ?)");
			stmt.setString(1,cv.getMaHoadon().getMaHD());
			stmt.setString(2,cv.getMaLK().getMalk());
			stmt.setDouble(3, cv.getDongia());
			stmt.setInt(4, cv.getSoluong());
			stmt.setDouble(5, cv.getThanhtien());
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
			//	System.out.println("...........");
			}
		}
		return n > 0; 
	}
	public boolean update(ChiTietHoaDon cv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update chitiethoadon set dongia= ?, soluong= ?, thanhtien= ? where maHoadon=? AND malk=? ");
		//	stmt.setString(1, cv.getMaLK().getMalk());
			stmt.setDouble(1, cv.getDongia());
			stmt.setInt(2, cv.getSoluong());
			stmt.setDouble(3, cv.getThanhtien());
			stmt.setString(4, cv.getMaHoadon().getMaHD());
			stmt.setString(5, cv.getMaLK().getMalk());
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
	public boolean xoa(String hd,String lk) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from chitiethoadon where maHoadon = ? and maLK = ?");
			stmt.setString(1,hd);
			stmt.setString(2,lk);
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
