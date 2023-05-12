package com.example.appbandochoi.bean;

import java.util.List;


public class OrderInput {
	private int MSDDH;
	private String hoTenKH;
	private String diaChi;
	private String sdt;
	private String trangThai;
	private String email;
	private int maKH;
	private List<DetailOrderInput> CTDDH;
	public int getMSDDH() {
		return MSDDH;
	}
	public void setMSDDH(int mSDDH) {
		MSDDH = mSDDH;
	}
	public String getHoTenKH() {
		return hoTenKH;
	}
	public void setHoTenKH(String hoTenKH) {
		this.hoTenKH = hoTenKH;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public List<DetailOrderInput> getCTDDH() {
		return CTDDH;
	}
	public void setCTDDH(List<DetailOrderInput> cTDDH) {
		CTDDH = cTDDH;
	}
	
	
	
	
}
