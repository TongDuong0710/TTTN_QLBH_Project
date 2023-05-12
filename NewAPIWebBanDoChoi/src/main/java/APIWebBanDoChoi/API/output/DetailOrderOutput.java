package APIWebBanDoChoi.API.output;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import APIWebBanDoChoi.NewEntity.DDHk;
import APIWebBanDoChoi.NewEntity.SANPHAMk;

public class DetailOrderOutput {
	private int MSCTDDH;
	private int donDatHang;
	private SANPHAMk sanPham;
	private int soLuong;
	public int getMSCTDDH() {
		return MSCTDDH;
	}
	public void setMSCTDDH(int mSCTDDH) {
		MSCTDDH = mSCTDDH;
	}
	public int getDonDatHang() {
		return donDatHang;
	}
	public void setDonDatHang(int donDatHang) {
		this.donDatHang = donDatHang;
	}
	public SANPHAMk getSanPham() {
		return sanPham;
	}
	public void setSanPham(SANPHAMk sanPham) {
		this.sanPham = sanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	
}
