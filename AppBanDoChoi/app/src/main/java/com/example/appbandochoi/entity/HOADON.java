package com.example.appbandochoi.entity;

import java.util.Date;
public class HOADON {

	private String soHoaDon;
	private String ngayLapHoaDon;
	private String maNV;
	private float tongGia;
	private DDH DDH;
	
	public DDH getDDH() {
		return DDH;
	}
	public void setDDH(DDH dDH) {
		DDH = dDH;
	}
	
	
	
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getSoHoaDon() {
		return soHoaDon;
	}
	
	public String getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}

	public void setNgayLapHoaDon(String ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}

	public void setSoHoaDon(String soHoaDon) {
		this.soHoaDon = soHoaDon;
	}


	public float getTongGia() {
		return tongGia;
	}
	public void setTongGia(float tongGia) {
		this.tongGia = tongGia;
	}
	
	
}
