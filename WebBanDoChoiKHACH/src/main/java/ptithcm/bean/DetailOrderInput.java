package ptithcm.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ptithcm.entity.SANPHAM;

public class DetailOrderInput {
	private int MSCTDDH;
	private int donDatHang;
	private SANPHAM sanPham;
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
	public SANPHAM getSanPham() {
		return sanPham;
	}
	public void setSanPham(SANPHAM sanPham) {
		this.sanPham = sanPham;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	
}
