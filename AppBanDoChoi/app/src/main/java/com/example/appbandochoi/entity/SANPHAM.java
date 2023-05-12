package com.example.appbandochoi.entity;

import java.io.Serializable;
import java.util.List;
public class SANPHAM implements Serializable{
	private String maSP;
	private String tenSP;
	private String loaiSP;
	private String nuocSX;
	private int donGia;
	private String moTa;
	private String hinhAnh;
	private float sale;
	private Integer trangthaixoa;
	private Integer soluongton;

	public Integer getTrangthaixoa() {
		return trangthaixoa;
	}

	public void setTrangthaixoa(Integer trangthaixoa) {
		this.trangthaixoa = trangthaixoa;
	}

	public Integer getSoluongton() {
		return soluongton;
	}

	public void setSoluongton(Integer soluongton) {
		this.soluongton = soluongton;
	}

	public float getSale() {
		return sale;
	}
	public void setSale(float sale) {
		this.sale = sale;
	}
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public String getLoaiSP() {
		return loaiSP;
	}
	public void setLoaiSP(String loaiSP) {
		this.loaiSP = loaiSP;
	}
	public String getNuocSX() {
		return nuocSX;
	}
	public void setNuocSX(String nuocSX) {
		this.nuocSX = nuocSX;
	}
	public int getDonGia() {
		return donGia;
	}
	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	
}
