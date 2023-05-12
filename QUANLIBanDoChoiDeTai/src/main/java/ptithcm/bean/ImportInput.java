package ptithcm.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.util.CollectionUtils;


public class ImportInput {
	private int ID;
	private String NGAYNHAP;
	private String tenNhanVien;
	private int maNhanVien;
	private List<DetailImportInput> listDetail;
	public List<DetailImportInput> getListDetail() {
		return listDetail;
	}
	public void setListDetail(List<DetailImportInput> listDetail) {
		this.listDetail = listDetail;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getNGAYNHAP() {
		return NGAYNHAP;
	}
	public void setNGAYNHAP(String nGAYNHAP) {
		NGAYNHAP = nGAYNHAP;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}
	public int getMaNhanVien() {
		return maNhanVien;
	}
	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	
}
