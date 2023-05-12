package com.example.appbandochoi.entity;

import java.io.Serializable;
public class CTDDH implements Serializable{

	private int MSCTDDH;
	private DDH donDatHang;
	private SANPHAM sanPham;
	private int soLuong;

	public CTDDH() {
	}

	public CTDDH(SANPHAM sanPham, int soLuong) {
		this.sanPham = sanPham;
		this.soLuong = soLuong;
	}

	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	public DDH getDonDatHang() {
		return donDatHang;
	}
	public void setDonDatHang(DDH donDatHang) {
		this.donDatHang = donDatHang;
	}
	public SANPHAM getSanPham() {
		return sanPham;
	}
	public void setSanPham(SANPHAM sanPham) {
		this.sanPham = sanPham;
	}
	public int getMSCTDDH() {
		return MSCTDDH;
	}
	public void setMSCTDDH(int mSCTDDH) {
		MSCTDDH = mSCTDDH;
	}

	
	
	
	
}
