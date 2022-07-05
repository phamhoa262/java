package entity;

import java.util.Date;

public class HoaDon {
	    private String MaHD;
	    private KhachHang maKH;
	    private NhanVien maNv;
	    private Date ngayxuat;
	    private double tongtien;
	    
		public HoaDon(String maHD, KhachHang maKH, NhanVien maNv, Date ngayxuat) {
			super();
			MaHD = maHD;
			this.maKH = maKH;
			this.maNv = maNv;
			this.ngayxuat = ngayxuat;
		}
		public String getMaHD() {
			return MaHD;
		}
		public void setMaHD(String maHD) {
			MaHD = maHD;
		}
		public KhachHang getMaKH() {
			return maKH;
		}
		public void setMaKH(KhachHang maKH) {
			this.maKH = maKH;
		}
		public NhanVien getMaNv() {
			return maNv;
		}
		public void setMaNv(NhanVien maNv) {
			this.maNv = maNv;
		}
		public Date getNgayxuat() {
			return ngayxuat;
		}
		public void setNgayxuat(Date ngayxuat) {
			this.ngayxuat = ngayxuat;
		}
		public double getTongtien() {
			return tongtien;
		}
		public void setTongtien(double tongtien) {
			this.tongtien = tongtien;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((MaHD == null) ? 0 : MaHD.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			HoaDon other = (HoaDon) obj;
			if (MaHD == null) {
				if (other.MaHD != null)
					return false;
			} else if (!MaHD.equals(other.MaHD))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "HoaDon [MaHD=" + MaHD + ", maKH=" + maKH + ", maNv=" + maNv + ", ngayxuat=" + ngayxuat
					+ ", tongtien=" + tongtien + "]";
		}
		public HoaDon(String maHD, KhachHang maKH, NhanVien maNv, Date ngayxuat, double tongtien) {
			super();
			MaHD = maHD;
			this.maKH = maKH;
			this.maNv = maNv;
			this.ngayxuat = ngayxuat;
			this.tongtien = tongtien;
		}
		public HoaDon() {
			super();
			// TODO Auto-generated constructor stub
		}
		public HoaDon(String maHD) {
			super();
			MaHD = maHD;
		}
		
	    
	   
}
