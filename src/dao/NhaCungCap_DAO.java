package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhaCungCap;

public class NhaCungCap_DAO {
	public NhaCungCap getElement(int index) {
		ArrayList<NhaCungCap> dschucvu = new ArrayList<NhaCungCap>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql = "Select * from nhacungcap";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);

			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				String maNcc = rs.getString(1);
				String tenncc = rs.getString(2);
				String  diachi= rs.getString(3);
				String email = rs.getString(4);
				String sdt = rs.getString(5);
				NhaCungCap p = new NhaCungCap(maNcc, tenncc, diachi, email, sdt);
				dschucvu.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		if(index<0 || index>dschucvu.size())
			return null;
		return dschucvu.get(index);
	}
	public ArrayList<NhaCungCap> getalltbncc() {
		ArrayList<NhaCungCap> dschucvu = new ArrayList<NhaCungCap>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql = "Select * from nhacungcap";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);

			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				String maNcc = rs.getString(1);
				String tenncc = rs.getString(2);
				String  diachi= rs.getString(3);
				String email = rs.getString(4);
				String sdt = rs.getString(5);
				NhaCungCap p = new NhaCungCap(maNcc, tenncc, diachi, email, sdt);
				dschucvu.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return dschucvu;
	}
	public boolean create(NhaCungCap cv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into nhacungcap values(?, ?, ?, ?, ?)");
			stmt.setString(1,cv.getMaNcc());
			stmt.setString(2,cv.getTenncc());
			stmt.setString(3,cv.getDiachi());
			stmt.setString(4,cv.getEmail());
			stmt.setString(5,cv.getSdt());
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
	public boolean xoa(String cv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from nhacungcap where maNcc = ?");
			stmt.setString(1,cv);
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
	public boolean update(NhaCungCap cv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update nhacungcap set Tenncc = ?, Diachi = ?, email= ?, sdt = ? where maNcc=? ");
			stmt.setString(1,cv.getTenncc());
			stmt.setString(2, cv.getDiachi());
			stmt.setString(3,cv.getEmail());
			stmt.setString(4, cv.getSdt());
			stmt.setString(5, cv.getMaNcc());
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
}
