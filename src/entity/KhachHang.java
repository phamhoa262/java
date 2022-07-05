package entity;

import java.util.Date;

public class KhachHang {
	private String makh;
	private String tenkh;
	private Date namsinh;
	private boolean GioiTinh;
	private String diachi;
	private String sdt;
	public String getMakh() {
		return makh;
	}
	public void setMakh(String makh) {
		this.makh = makh;
	}
	public String getTenkh() {
		return tenkh;
	}
	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
	}
	public Date getNamsinh() {
		return namsinh;
	}
	public void setNamsinh(Date namsinh) {
		this.namsinh = namsinh;
	}
	public boolean isGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((makh == null) ? 0 : makh.hashCode());
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
		KhachHang other = (KhachHang) obj;
		if (makh == null) {
			if (other.makh != null)
				return false;
		} else if (!makh.equals(other.makh))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "KhachHang [makh=" + makh + ", tenkh=" + tenkh + ", namsinh=" + namsinh + ", GioiTinh=" + GioiTinh
				+ ", diachi=" + diachi + ", sdt=" + sdt + "]";
	}
	public KhachHang(String makh, String tenkh, Date namsinh, boolean gioiTinh, String diachi, String sdt) {
		super();
		this.makh = makh;
		this.tenkh = tenkh;
		this.namsinh = namsinh;
		GioiTinh = gioiTinh;
		this.diachi = diachi;
		this.sdt = sdt;
	}
	public KhachHang(String makh) {
		super();
		this.makh = makh;
	}
	public KhachHang() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
