package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChucVu;



public class ChucVu_DAO {
	ArrayList<ChucVu> dschucvu;
	public ChucVu_DAO() {
		// TODO Auto-generated constructor stub
		dschucvu = new ArrayList<ChucVu>();
	}
	ChucVu cv;
	public ChucVu getElement(int index) {
		ArrayList<ChucVu> dschucvu = new ArrayList<ChucVu>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql = "Select * from chucvu";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);

			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				String macv = rs.getString(1);
				String tencv = rs.getString(2);
				ChucVu p = new ChucVu(macv, tencv);
				dschucvu.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		if(index<0 || index>dschucvu.size())
			return null;
		return dschucvu.get(index);
	}
	public ArrayList<ChucVu> getalltbChucVu() {
	ArrayList<ChucVu> dschucvu = new ArrayList<ChucVu>();
	try {
		Connection con = ConnectDB.getInstance().getConnection();
		
		String sql = "Select * from chucvu";
		Statement statement = con.createStatement();
		// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
		ResultSet rs = statement.executeQuery(sql);

		// Duyệt trên kết quả trả về.
		while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
			String macv = rs.getString(1);
			String tencv = rs.getString(2);
			ChucVu p = new ChucVu(macv, tencv);
			dschucvu.add(p);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	
	}
	return dschucvu;
}
	public boolean create(ChucVu cv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into chucvu values(?, ?)");
			stmt.setString(1,cv.getMacv());
			stmt.setString(2,cv.getTencv());
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
			stmt = con.prepareStatement("delete from chucvu where macv = ?");
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
	public boolean update(ChucVu cv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			//stmt = con.prepareStatement("insert into NhanVien values(?, ?, ?, ?, ?, ?,?)");
		//	String sql="update CV "+"set TenCV = ?,"+"where MaCV = ?";
			stmt = con.prepareStatement("update chucvu set tencv = ? where macv=? ");

			stmt.setString(1,cv.getTencv());
			stmt.setString(2,cv.getMacv());
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
	public int getsize() {
		ArrayList<ChucVu> dschucvu = new ArrayList<ChucVu>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql = "Select * from chucvu";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);

			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				String macv = rs.getString(1);
				String tencv = rs.getString(2);
				ChucVu p = new ChucVu(macv, tencv);
				dschucvu.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		
		return dschucvu.size();
	}
	}

