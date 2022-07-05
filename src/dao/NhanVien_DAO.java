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
import entity.KhachHang;
import entity.LoaiLK;
import entity.NhaCungCap;
import entity.NhanVien;

public class NhanVien_DAO {
	public ArrayList<NhanVien> getallNv() {
		ArrayList<NhanVien> dsKh = new ArrayList<NhanVien>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql = "Select * from nhanvien";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);

			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				String manv = rs.getString(1);
				String tennv = rs.getString(2);
				ChucVu cv= new ChucVu(rs.getString(3));
				Date ngayvao=rs.getDate(4);
				boolean gioitinh = rs.getBoolean(5);
				String diachi=rs.getString(6);
				String sdt=rs.getString(7);
				NhanVien lk = new NhanVien(manv, tennv, cv, ngayvao, gioitinh, diachi, sdt);
				dsKh.add(lk);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return dsKh;
	}
	public NhanVien getelement(int index) {
		ArrayList<NhanVien> dsKh = new ArrayList<NhanVien>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql = "Select * from nhanvien";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);

			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				String manv = rs.getString(1);
				String tennv = rs.getString(2);
				ChucVu cv= new ChucVu(rs.getString(3));
				Date ngayvao=rs.getDate(4);
				boolean gioitinh = rs.getBoolean(5);
				String diachi=rs.getString(6);
				String sdt=rs.getString(7);
				NhanVien lk = new NhanVien(manv, tennv, cv, ngayvao, gioitinh, diachi, sdt);
				dsKh.add(lk);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		
		if(index<0 || index>dsKh.size())
			return null;
		return dsKh.get(index);
	}
	public boolean create(NhanVien cv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into nhanvien values(?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1,cv.getManv());
			stmt.setString(2,cv.getTennv());
			stmt.setString(3,cv.getCv().getMacv());
			stmt.setDate(4, (Date) cv.getNgayvao());
			stmt.setBoolean(5,cv.isGioitinh());
			stmt.setString(6,cv.getDiachi());
			stmt.setString(7,cv.getSdt());
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
			stmt = con.prepareStatement("delete from nhanvien where manv = ?");
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
	
	public boolean update(NhanVien cv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update nhanvien set tennv = ?, cv = ?, ngayvao = ?, gioitinh = ?, diachi = ?, sdt= ?  where manv=? ");
			stmt.setString(1,cv.getTennv());
			stmt.setString(2, cv.getCv().getMacv());
			stmt.setDate(3, (Date) cv.getNgayvao());
			stmt.setBoolean(4, cv.isGioitinh());
			stmt.setString(5, cv.getDiachi());
			stmt.setString(6, cv.getSdt());
			stmt.setString(7, cv.getManv());
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
	public ArrayList<NhanVien> findid(String id)
	{
        ArrayList<NhanVien> khlist= new ArrayList<NhanVien>();
		Connection con = ConnectDB.getInstance().getConnection();
       PreparedStatement stmt= null;
       try {
    	   String sql="select * from nhanvien where manv =?" ;
    	   stmt = con.prepareStatement(sql);
    	   stmt.setString(1, id);
    	   ResultSet rs = stmt.executeQuery();
    	   while (rs.next()) {
    			String manv = rs.getString(1);
				String tennv = rs.getString(2);
				ChucVu cv= new ChucVu(rs.getString(3));
				Date ngayvao=rs.getDate(4);
				boolean gioitinh = rs.getBoolean(5);
				String diachi=rs.getString(6);
				String sdt=rs.getString(7);
				NhanVien lk = new NhanVien(manv, tennv, cv, ngayvao, gioitinh, diachi, sdt);
    		   khlist.add(lk);
    		   
    	   }
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	
	return khlist;
	
	}
	public ArrayList<NhanVien> findname(String name)
	{
        ArrayList<NhanVien> khlist= new ArrayList<NhanVien>();
		Connection con = ConnectDB.getInstance().getConnection();
       //PreparedStatement stmt= null;
       try {
    	   String sql="select * from nhanvien where tennv like '%" +name+ "%'";
    	   Statement stmt = con.createStatement();
    	   ResultSet rs = stmt.executeQuery(sql);
    	   while (rs.next()) {
    			String manv = rs.getString(1);
				String tennv = rs.getString(2);
				ChucVu cv= new ChucVu(rs.getString(3));
				Date ngayvao=rs.getDate(4);
				boolean gioitinh = rs.getBoolean(5);
				String diachi=rs.getString(6);
				String sdt=rs.getString(7);
				NhanVien lk = new NhanVien(manv, tennv, cv, ngayvao, gioitinh, diachi, sdt);
    		   
    		   khlist.add(lk);
    	   }
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	
	return khlist;
	
	}
	
}
