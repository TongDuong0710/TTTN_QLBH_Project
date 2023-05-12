package com.example.appbandochoi.entity;

import java.io.Serializable;
import java.util.List;

public class DDH implements Serializable{

	private int MSDDH;
	private String hoTenKH;
	private String diaChi;
	private String sdt;
	private String trangThai;
	private String email;

	  private KHACHHANG maKH;

	  private List<CTDDH> CTDDH;
	 
	  public List<CTDDH> getCTDDH() {
			return CTDDH;
		}

		public void setCTDDH(List<CTDDH> cTDDH) {
			CTDDH = cTDDH;
		}

	  private float tongGia;
	  
	 
	public float getTongGia() {
		return tongGia;
	}

	public int getMSDDH() {
		return MSDDH;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMSDDH(int mSDDH) {
		MSDDH = mSDDH;
	}

	public void setTongGia(float tongGia) {
		this.tongGia = tongGia;
	}

	public KHACHHANG getMaKH() {
		return maKH;
	}

	public void setMaKH(KHACHHANG maKH) {
		this.maKH = maKH;
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

}
