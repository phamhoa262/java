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
import entity.LinhKien;
import entity.LoaiLK;
import entity.NhaCungCap;

public class KhachHang_DAO {
	ArrayList<KhachHang> dskh;
	KhachHang kh;
	public KhachHang_DAO(){
		dskh= new ArrayList<KhachHang>();
	}
	public KhachHang getElement(int index) {
		ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql = "Select * from khachhang";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);

			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				String makh = rs.getString(1);
				String tenkh = rs.getString(2);
				Date namsinh = rs.getDate(3);
				boolean phai = rs.getBoolean(4);
				String diachi=rs.getString(5);
				String sdt=rs.getString(6);
				KhachHang lk = new KhachHang(makh, tenkh, namsinh,phai, diachi, sdt);
				dskh.add(lk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		if(index<0 || index>dskh.size())
			return null;
		return dskh.get(index);
	}
	public ArrayList<KhachHang> getallKH() {
		ArrayList<KhachHang> dsKh = new ArrayList<KhachHang>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql = "Select * from khachhang";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);

			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				String makh = rs.getString(1);
				String tenkh = rs.getString(2);
				Date namsinh=rs.getDate(3);
				boolean phai = rs.getBoolean(4);
				String diachi=rs.getString(5);
				String sdt=rs.getString(6);
				KhachHang lk = new KhachHang(makh, tenkh, namsinh,phai, diachi, sdt);
				dsKh.add(lk);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return dsKh;
	}
	public boolean create(KhachHang lk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt= null;
		int n=0;
		try {
			stmt = con .prepareStatement("insert into khachhang values(?, ?, ?, ?, ?, ?)");
			stmt.setString(1, lk.getMakh());
			stmt.setString(2, lk.getTenkh());
			stmt.setDate(3, (Date) lk.getNamsinh());
			stmt.setBoolean(4, lk.isGioiTinh());
			stmt.setString(5, lk.getDiachi());
			stmt.setString(6,lk.getSdt());
			n= stmt.executeUpdate();
			
			
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
	public boolean xoa(String lk) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete from khachhang where makh = ?");
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
	public boolean update(KhachHang cv) {
		Connection con = ConnectDB.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update khachhang set tenkh = ?, namsinh = ?, GioiTinh = ?, diachi = ?, sdt = ?  where makh=? ");
			stmt.setString(1,cv.getTenkh());
			stmt.setDate(2, (Date) cv.getNamsinh());
			stmt.setBoolean(3, cv.isGioiTinh());
			stmt.setString(4, cv.getDiachi());
			stmt.setString(5, cv.getSdt());
			stmt.setString(6,cv.getMakh());
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
	public ArrayList<KhachHang> findid(String id)
	{
        ArrayList<KhachHang> khlist= new ArrayList<KhachHang>();
		Connection con = ConnectDB.getInstance().getConnection();
       PreparedStatement stmt= null;
       try {
    	   String sql="select * from khachhang where makh =?" ;
    	   stmt = con.prepareStatement(sql);
    	   stmt.setString(1, id);
    	   ResultSet rs = stmt.executeQuery();
    	   while (rs.next()) {
    		  String makh = rs.getString(1); 
    		  String tenkh = rs.getString(2);
    		  Date namsinh= rs.getDate(3);
              Boolean phai = rs.getBoolean(4);
              String dc = rs.getString(5);
              String sdt = rs.getString(6);
    		
    		  
    		KhachHang kh = new KhachHang(makh, tenkh, namsinh, phai, dc, sdt);
    		   khlist.add(kh);
    		   
    	   }
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	
	return khlist;
	
	}
	public ArrayList<KhachHang> findname(String name)
	{
        ArrayList<KhachHang> khlist= new ArrayList<KhachHang>();
		Connection con = ConnectDB.getInstance().getConnection();
      // PreparedStatement stmt= null;
       try {
    	   String sql="select * from khachhang where tenkh like '%" + name + "%'" ;
    	   //stmt = con.prepareStatement(sql); 
    	   Statement stmt = con.createStatement();
    	 
    	   ResultSet rs = stmt.executeQuery(sql);
    	  
    	   while (rs.next()) {
    		  String makh = rs.getString(1); 
    		  String tenkh = rs.getString(2);
    		  Date namsinh= rs.getDate(3);
              Boolean phai = rs.getBoolean(4);
              String dc = rs.getString(5);
              String sdt = rs.getString(6);
    		
    		  
    		KhachHang kh = new KhachHang(makh, tenkh, namsinh, phai, dc, sdt);
    		   khlist.add(kh);
    		  
    		   
    	   }
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	
	return khlist;
	
	}
}
