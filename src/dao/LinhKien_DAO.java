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
import entity.LinhKien;
import entity.LoaiLK;
import entity.NhaCungCap;
import entity.NhanVien;

	public class LinhKien_DAO {
		public LinhKien getElement(int index) {
			ArrayList<LinhKien> dslinhkien = new ArrayList<LinhKien>();
			try {
				Connection con = ConnectDB.getInstance().getConnection();
				
				String sql = "Select * from linhkien1";
				Statement statement = con.createStatement();
				// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
				ResultSet rs = statement.executeQuery(sql);

				// Duyệt trên kết quả trả về.
				while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
					
					String malk = rs.getString(1);
					String tenlk = rs.getString(2);
					NhaCungCap ncc = new NhaCungCap(rs.getString(3));
					Double dongia = rs.getDouble(6);
					Date ngaynhap = rs.getDate(5);
					int soluong= rs.getInt(7);
					LoaiLK loailk= new LoaiLK(rs.getString(4));
				//	int soluong= rs.getInt(6);
					LinhKien lk = new LinhKien(malk, tenlk, ncc, loailk, ngaynhap, dongia, soluong);
					dslinhkien.add(lk);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			
			}
			if(index<0 || index>dslinhkien.size())
				return null;
			return dslinhkien.get(index);
		}
		public ArrayList<LinhKien> getallLK() {
		ArrayList<LinhKien> dslinhkien = new ArrayList<LinhKien>();
		try {
			Connection con = ConnectDB.getInstance().getConnection();
			
			String sql = "Select * from linhkien1";
			Statement statement = con.createStatement();
			// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
			ResultSet rs = statement.executeQuery(sql);

			// Duyệt trên kết quả trả về.
			while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
				String malk = rs.getString(1);
				String tenlk = rs.getString(2);
				NhaCungCap ncc = new NhaCungCap(rs.getString(3));
				Double dongia = rs.getDouble(6);
				Date ngaynhap = rs.getDate(5);
				int soluong= rs.getInt(7);
				LoaiLK loailk= new LoaiLK(rs.getString(4));
			//	int soluong= rs.getInt(6);
				LinhKien lk = new LinhKien(malk, tenlk, ncc, loailk, ngaynhap, dongia, soluong);
				dslinhkien.add(lk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return dslinhkien;
	}
		public boolean create(LinhKien lk) {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			PreparedStatement stmt= null;
			int n=0;
			try {
				stmt = con .prepareStatement("insert into linhkien1 values(?, ?, ?, ?, ?, ?, ?)");
				stmt.setString(1, lk.getMalk());
				stmt.setString(3, lk.getNhacc().getMaNcc());
				stmt.setString(2, lk.getTenlk());
				stmt.setDouble(6, lk.getDongia());
				stmt.setDate(5,(Date) lk.getNgaynhap());
				stmt.setInt(7,lk.getSoluong());
				stmt.setString(4, lk.getLoailk().getMaloai());
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
				stmt = con.prepareStatement("delete from linhkien1 where malk = ?");
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
		
		
		public boolean update(LinhKien cv) {
			Connection con = ConnectDB.getInstance().getConnection();
			PreparedStatement stmt = null;
			int n = 0;
			try {
				stmt = con.prepareStatement("update linhkien1 set tenlk = ?, nhacc = ?, loailk = ?, ngaynhap = ?, dongia = ?,soluong= ?  where malk=? ");
				stmt.setString(1,cv.getTenlk());
				stmt.setDate(4, (Date) cv.getNgaynhap());
				stmt.setInt(6, cv.getSoluong());
				stmt.setString(3, cv.getLoailk().getMaloai());
				stmt.setString(2, cv.getNhacc().getMaNcc());
				stmt.setDouble(5,cv.getDongia());
				stmt.setString(7, cv.getMalk());
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
		public double dongia(String ma) {
			double dg = 0;
			try {
				Connection con = ConnectDB.getInstance().getConnection();
				PreparedStatement statement = null;
				
				String sql = "Select * from linhkien1 where malk= ?";
				statement = con.prepareStatement(sql);
				statement.setString(1,ma);
				// Thực thi câu lệnh SQL trả về đối tượng ResultSet.
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {// Di chuyển con trỏ xuống bản ghi kế tiếp.
					dg = rs.getDouble(6);
					return dg;
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();

			}
			return dg;
		}
		public ArrayList<LinhKien> findid(String id)
		{
	        ArrayList<LinhKien> khlist= new ArrayList<LinhKien>();
			Connection con = ConnectDB.getInstance().getConnection();
	       PreparedStatement stmt= null;
	       try {
	    	   String sql="select * from linhkien1 where malk =?" ;
	    	   stmt = con.prepareStatement(sql);
	    	   stmt.setString(1, id);
	    	   ResultSet rs = stmt.executeQuery();
	    	   while (rs.next()) {
	    			String malk = rs.getString(1);
					String tenlk = rs.getString(2);
					NhaCungCap ncc = new NhaCungCap(rs.getString(3));
					Double dongia = rs.getDouble(6);
					Date ngaynhap = rs.getDate(5);
					int soluong= rs.getInt(7);
					LoaiLK loailk= new LoaiLK(rs.getString(4));
				//	int soluong= rs.getInt(6);
					LinhKien lk = new LinhKien(malk, tenlk, ncc, loailk, ngaynhap, dongia, soluong);
					//dslinhkien.add(lk);
	    		   khlist.add(lk);
	    		   
	    	   }
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return khlist;
		
		}
		public ArrayList<LinhKien> findname(String name)
		{
	        ArrayList<LinhKien> khlist= new ArrayList<LinhKien>();
			Connection con = ConnectDB.getInstance().getConnection();
	      // PreparedStatement stmt= null;
	       try {
	    	   String sql="select * from linhkien1 where tenlk like '%" + name + "%'";
	    	   Statement st = con.createStatement();
	    	   ResultSet rs = st.executeQuery(sql);//doi
	    	   while (rs.next()) {
	    			String malk = rs.getString(1);
					String tenlk = rs.getString(2);
					NhaCungCap ncc = new NhaCungCap(rs.getString(3));
					Double dongia = rs.getDouble(6);
					Date ngaynhap = rs.getDate(5);
					int soluong= rs.getInt(7);
					LoaiLK loailk= new LoaiLK(rs.getString(4));
				//	int soluong= rs.getInt(6);
					LinhKien lk = new LinhKien(malk, tenlk, ncc, loailk, ngaynhap, dongia, soluong);
					//dslinhkien.add(lk);
		    		   khlist.add(lk);
	    		   
	    	   }
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return khlist;
		
		}
		}
