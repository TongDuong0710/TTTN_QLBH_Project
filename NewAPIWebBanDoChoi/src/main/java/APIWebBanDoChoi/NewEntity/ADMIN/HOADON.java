package APIWebBanDoChoi.NewEntity.ADMIN;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import APIWebBanDoChoi.NewEntity.NHANVIEN;

@Entity
public class HOADON {
	@Id
	@Column(name = "SOHD")
	private String sohd;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date ngaylaphd;
	@ManyToOne
	@JoinColumn(name="MANV")
	private NHANVIEN nhanvien;
	private Integer tonggia; 
	private String feedback;
	
	public HOADON() {
		super();
	}
	
	public DONDATHANG getDondathang() {
		return dondathang;
	}

	public void setDondathang(DONDATHANG dondathang) {
		this.dondathang = dondathang;
	}



	public String getSohd() {
		return sohd;
	}
	public void setSohd(String sohd) {
		this.sohd = sohd;
	}
	public Date getNgaylaphd() {
		return ngaylaphd;
	}
	public void setNgaylaphd(Date ngaylaphd) {
		this.ngaylaphd = ngaylaphd;
	}
	
	public NHANVIEN getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(NHANVIEN nhanvien) {
		this.nhanvien = nhanvien;
	}

	public Integer getTonggia() {
		return tonggia;
	}
	public void setTonggia(Integer tonggia) {
		this.tonggia = tonggia;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	 @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "MSDDH")
	  private DONDATHANG dondathang;

}
