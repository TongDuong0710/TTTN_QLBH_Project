package ptithcm.bean;

public class DetailImportInput {
	private int ID;
	private int SL;
	private int DONGIA;
	private String tenSanPham;
	private String maSanPham;
	private String hinhAnhSP;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getSL() {
		return SL;
	}
	public void setSL(int sL) {
		SL = sL;
	}
	public int getDONGIA() {
		return DONGIA;
	}
	public void setDONGIA(int dONGIA) {
		DONGIA = dONGIA;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public String getMaSanPham() {
		return maSanPham;
	}
	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}
	public String getHinhAnhSP() {
		return hinhAnhSP;
	}
	public void setHinhAnhSP(String hinhAnhSP) {
		this.hinhAnhSP = hinhAnhSP;
	}
	
}
