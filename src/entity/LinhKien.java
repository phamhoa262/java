package entity;

import java.util.Date;

public class LinhKien {
	private String malk;
	private String tenlk;
	private NhaCungCap nhacc;
	private LoaiLK loailk;
	private Date ngaynhap;
	private double dongia;
	private int soluong;
	
	public String getMalk() {
		return malk;
	}
	public void setMalk(String malk) {
		this.malk = malk;
	}
	public String getTenlk() {
		return tenlk;
	}
	public void setTenlk(String tenlk) {
		this.tenlk = tenlk;
	}
	public NhaCungCap getNhacc() {
		return nhacc;
	}
	public void setNhacc(NhaCungCap nhacc) {
		this.nhacc = nhacc;
	}
	public LoaiLK getLoailk() {
		return loailk;
	}
	public void setLoailk(LoaiLK loailk) {
		this.loailk = loailk;
	}
	public Date getNgaynhap() {
		return ngaynhap;
	}
	public void setNgaynhap(Date ngaynhap) {
		this.ngaynhap = ngaynhap;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((malk == null) ? 0 : malk.hashCode());
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
		LinhKien other = (LinhKien) obj;
		if (malk == null) {
			if (other.malk != null)
				return false;
		} else if (!malk.equals(other.malk))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "LinhKien [malk=" + malk + ", tenlk=" + tenlk + ", nhacc=" + nhacc + ", loailk=" + loailk + ", ngaynhap="
				+ ngaynhap + ", dongia=" + dongia + ", soluong=" + soluong + "]";
	}
	public LinhKien(String malk, String tenlk, NhaCungCap nhacc, LoaiLK loailk, Date ngaynhap, double dongia,
			int soluong) {
		super();
		this.malk = malk;
		this.tenlk = tenlk;
		this.nhacc = nhacc;
		this.loailk = loailk;
		this.ngaynhap = ngaynhap;
		this.dongia = dongia;
		this.soluong = soluong;
	}
	public LinhKien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LinhKien(String malk) {
		super();
		this.malk = malk;
	}
	
	
	
}
