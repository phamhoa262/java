package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.LoaiLK;

public class LoaiLK_DAO {
	public LoaiLK getElement(int index) {
		ArrayList<LoaiLK> dschucvu = new ArrayList<LoaiLK>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql = "Select * from loailinhkien";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);

			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				String macv = rs.getString(1);
				String tencv = rs.getString(2);
				LoaiLK p = new LoaiLK(macv, tencv);
				dschucvu.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		if(index<0 || index>dschucvu.size())
			return null;
		return dschucvu.get(index);
	}
	public ArrayList<LoaiLK> getalltbLoaiLK() {
	ArrayList<LoaiLK> dschucvu = new ArrayList<LoaiLK>();
	try {
		Connection con = ConnectDB.getInstance().getConnection();
		
		String sql = "Select * from loailinhkien";
		Statement statement = con.createStatement();
		// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
		ResultSet rs = statement.executeQuery(sql);

		// Duyệt trên kết quả trả về.
		while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
			String macv = rs.getString(1);
			String tencv = rs.getString(2);
			LoaiLK p = new LoaiLK(macv, tencv);
			dschucvu.add(p);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	
	}
	return dschucvu;
}
	public boolean create(LoaiLK cv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into loailinhkien values(?, ?)");
			stmt.setString(1,cv.getMaloai());
			stmt.setString(2,cv.getTenloai());
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
			stmt = con.prepareStatement("delete from loailinhkien where maloai = ?");
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
	public boolean update(LoaiLK cv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			//stmt = con.prepareStatement("insert into NhanVien values(?, ?, ?, ?, ?, ?,?)");
		//	String sql="update CV "+"set TenCV = ?,"+"where MaCV = ?";
			stmt = con.prepareStatement("update loailinhkien set tenloai = ? where maloai=? ");

			stmt.setString(1,cv.getTenloai());
			stmt.setString(2,cv.getMaloai());
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
