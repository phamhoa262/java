package entity;

public class NhaCungCap {
	private String maNcc;
	private String Tenncc;
	private String Diachi;
	private String email;
	private String sdt;
	public String getMaNcc() {
		return maNcc;
	}
	public void setMaNcc(String maNcc) {
		this.maNcc = maNcc;
	}
	public String getTenncc() {
		return Tenncc;
	}
	public void setTenncc(String tenncc) {
		Tenncc = tenncc;
	}
	public String getDiachi() {
		return Diachi;
	}
	public void setDiachi(String diachi) {
		Diachi = diachi;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	@Override
	public String toString() {
		return "NhaCungCap [maNcc=" + maNcc + ", Tenncc=" + Tenncc + ", Diachi=" + Diachi + ", email=" + email + ", sdt="
				+ sdt + "]";
	}
	public NhaCungCap(String maNcc, String tenncc, String diachi, String email, String sdt) {
		super();
		this.maNcc = maNcc;
		Tenncc = tenncc;
		Diachi = diachi;
		this.email = email;
		this.sdt = sdt;
	}
	public NhaCungCap() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNcc == null) ? 0 : maNcc.hashCode());
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
		NhaCungCap other = (NhaCungCap) obj;
		if (maNcc == null) {
			if (other.maNcc != null)
				return false;
		} else if (!maNcc.equals(other.maNcc))
			return false;
		return true;
	}
	public NhaCungCap(String maNcc) {
		super();
		this.maNcc = maNcc;
	}

}
