package com.example.appbandochoi.entity;

import java.io.Serializable;
import java.util.List;


public class KHACHHANG implements Serializable {

	private int maKH;
	private String hoTen;
	private String diaChi;
	private String sdt;
	private String email;
	private int doanhSo;
	private String hinhAnh;
	private DSTAIKHOAN taiKhoan;
	private List<DDH> DDH;

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	
	public List<DDH> getDDH() {
		return DDH;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDDH(List<DDH> dDH) {
		DDH = dDH;
	}

	public int getMaKH() {
		return maKH;
	}

	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
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

	public int getDoanhSo() {
		return doanhSo;
	}

	public void setDoanhSo(int doanhSo) {
		this.doanhSo = doanhSo;
	}

	public DSTAIKHOAN getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(DSTAIKHOAN taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
}
