package entity;

public class ChucVu {
	private String macv;
	private String tencv;
	public String getMacv() {
		return macv;
	}
	public void setMacv(String macv) {
		this.macv = macv;
	}
	public String getTencv() {
		return tencv;
	}
	public void setTencv(String tencv) {
		this.tencv = tencv;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((macv == null) ? 0 : macv.hashCode());
		result = prime * result + ((tencv == null) ? 0 : tencv.hashCode());
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
		ChucVu other = (ChucVu) obj;
		if (macv == null) {
			if (other.macv != null)
				return false;
		} else if (!macv.equals(other.macv))
			return false;
		if (tencv == null) {
			if (other.tencv != null)
				return false;
		} else if (!tencv.equals(other.tencv))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ChucVu [macv=" + macv + ", tencv=" + tencv + "]";
	}
	public ChucVu(String macv, String tencv) {
		super();
		this.macv = macv;
		this.tencv = tencv;
	}
	public ChucVu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChucVu(String macv) {
		super();
		this.macv = macv;
	}
	

}
