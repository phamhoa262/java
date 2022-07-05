package entity;

public class LoaiLK {
	private String maloai;
	private String tenloai;
	public String getMaloai() {
		return maloai;
	}
	public void setMaloai(String maloai) {
		this.maloai = maloai;
	}
	public String getTenloai() {
		return tenloai;
	}
	public void setTenloai(String tenloai) {
		this.tenloai = tenloai;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maloai == null) ? 0 : maloai.hashCode());
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
		LoaiLK other = (LoaiLK) obj;
		if (maloai == null) {
			if (other.maloai != null)
				return false;
		} else if (!maloai.equals(other.maloai))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "LoaiLK [maloai=" + maloai + ", tenloai=" + tenloai + "]";
	}
	public LoaiLK() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoaiLK(String maloai, String tenloai) {
		super();
		this.maloai = maloai;
		this.tenloai = tenloai;
	}
	public LoaiLK(String maloai) {
		super();
		this.maloai = maloai;
	}

}
