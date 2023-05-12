package ptithcm.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CTDDH")
public class CTDDH implements Serializable{
	
	@Id
	@Column(name="Id")
	@GeneratedValue
	private int MSCTDDH;
	
	@ManyToOne
	@JoinColumn(name="MSDDH")
	private DDH donDatHang;
	
	@ManyToOne
	@JoinColumn(name="MASP")
	private SANPHAM sanPham;
	
	@Column(name="SL")
	private int soLuong;
	
	
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
