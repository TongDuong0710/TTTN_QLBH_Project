package ptithcm.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import ptithcm.entity.DSTAIKHOANk;

public class CustomerInput {
	private int maKH;
	private String hoTen;
	private String diaChi;
	private String sdt;
	private String email;
	private int doanhSo;
	private String hinhAnh;
	private DSTAIKHOANk taiKhoan;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDoanhSo() {
		return doanhSo;
	}
	public void setDoanhSo(int doanhSo) {
		this.doanhSo = doanhSo;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public DSTAIKHOANk getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(DSTAIKHOANk taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	
}
