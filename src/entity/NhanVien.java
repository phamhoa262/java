package entity;

import java.util.Date;

public class NhanVien {
	private String manv;
	private String tennv;
	private ChucVu cv;
	private Date ngayvao;
	private boolean Gioitinh;
	private String diachi;
	private String sdt;
	public String getManv() {
		return manv;
	}
	public void setManv(String manv) {
		this.manv = manv;
	}
	public String getTennv() {
		return tennv;
	}
	public void setTennv(String tennv) {
		this.tennv = tennv;
	}
	public ChucVu getCv() {
		return cv;
	}
	public void setCv(ChucVu cv) {
		this.cv = cv;
	}
	public Date getNgayvao() {
		return ngayvao;
	}
	public void setNgayvao(Date ngayvao) {
		this.ngayvao = ngayvao;
	}
	public boolean isGioitinh() {
		return Gioitinh;
	}
	public void setGioitinh(boolean gioitinh) {
		Gioitinh = gioitinh;
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
		result = prime * result + ((manv == null) ? 0 : manv.hashCode());
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
		NhanVien other = (NhanVien) obj;
		if (manv == null) {
			if (other.manv != null)
				return false;
		} else if (!manv.equals(other.manv))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "NhanVien [manv=" + manv + ", tennv=" + tennv + ", cv=" + cv + ", ngayvao=" + ngayvao + ", Gioitinh="
				+ Gioitinh + ", diachi=" + diachi + ", sdt=" + sdt + "]";
	}
	public NhanVien(String manv, String tennv, ChucVu cv, Date ngayvao, boolean gioitinh, String diachi, String sdt) {
		super();
		this.manv = manv;
		this.tennv = tennv;
		this.cv = cv;
		this.ngayvao = ngayvao;
		Gioitinh = gioitinh;
		this.diachi = diachi;
		this.sdt = sdt;
	}
	public NhanVien(String manv) {
		super();
		this.manv = manv;
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
