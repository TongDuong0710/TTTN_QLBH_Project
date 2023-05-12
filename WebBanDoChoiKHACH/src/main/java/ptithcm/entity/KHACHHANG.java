package ptithcm.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="KHACHHANG")
public class KHACHHANG implements Serializable {

	@Id
	@GeneratedValue
	private int maKH;
	@Column(name="HOTEN")
	private String hoTen;
	
	@Column(name="DIACHI")
	private String diaChi;
	
	@Column(name="SDT")
	private String sdt;
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="DOANHSO")
	private int doanhSo;
	
	@Column(name="HINHANH")
	private String hinhAnh;
	
	@OneToOne
	@JoinColumn(name = "TAIKHOAN")
	private DSTAIKHOAN taiKhoan;

	@OneToMany(mappedBy ="maKH",fetch = FetchType.EAGER)
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
