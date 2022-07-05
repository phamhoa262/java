package entity;

public class ChiTietHoaDon {
	public ChiTietHoaDon(HoaDon maHoadon, LinhKien maLK, double dongia, int soluong) {
		super();
		this.maHoadon = maHoadon;
		this.maLK = maLK;
		this.dongia = dongia;
		this.soluong = soluong;
	}
	private HoaDon maHoadon;
	private LinhKien maLK;
	private double dongia;
	private int soluong;
	private double thanhtien;
	public HoaDon getMaHoadon() {
		return maHoadon;
	}
	public void setMaHoadon(HoaDon maHoadon) {
		this.maHoadon = maHoadon;
	}
	public LinhKien getMaLK() {
		return maLK;
	}
	public void setMaLK(LinhKien maLK) {
		this.maLK = maLK;
	}
	public double getDongia() {
		return dongia;
	}
	public void setDongia(double dongia) {
		this.dongia = dongia;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public double getThanhtien() {
		return thanhtien;
	}
	public void setThanhtien(double thanhtien) {
		this.thanhtien = thanhtien;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maHoadon == null) ? 0 : maHoadon.hashCode());
		result = prime * result + ((maLK == null) ? 0 : maLK.hashCode());
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
		ChiTietHoaDon other = (ChiTietHoaDon) obj;
		if (maHoadon == null) {
			if (other.maHoadon != null)
				return false;
		} else if (!maHoadon.equals(other.maHoadon))
			return false;
		if (maLK == null) {
			if (other.maLK != null)
				return false;
		} else if (!maLK.equals(other.maLK))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ChiTietHoaDon [maHoadon=" + maHoadon + ", maLK=" + maLK + ", dongia=" + dongia + ", soluong=" + soluong
				+ ", thanhtien=" + thanhtien + "]";
	}
	public ChiTietHoaDon(HoaDon maHoadon, LinhKien maLK, double dongia, int soluong, double thanhtien) {
		super();
		this.maHoadon = maHoadon;
		this.maLK = maLK;
		this.dongia = dongia;
		this.soluong = soluong;
		this.thanhtien = thanhtien;
	}
	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChiTietHoaDon(HoaDon maHoadon, LinhKien maLK) {
		super();
		this.maHoadon = maHoadon;
		this.maLK = maLK;
	}
	
}
