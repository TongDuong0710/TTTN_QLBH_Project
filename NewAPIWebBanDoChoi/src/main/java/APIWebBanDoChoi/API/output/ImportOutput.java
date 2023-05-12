package APIWebBanDoChoi.API.output;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.util.CollectionUtils;

import APIWebBanDoChoi.NewEntity.CTNHAPHANG;
import APIWebBanDoChoi.NewEntity.NHANVIEN;
import APIWebBanDoChoi.NewEntity.NHAPHANG;
import APIWebBanDoChoi.NewEntity.SANPHAMk;

public class ImportOutput {
	private int ID;
	private String NGAYNHAP;
	private String tenNhanVien;
	private int maNhanVien;
	private List<DetailImportOutput> listDetail;
	public List<DetailImportOutput> getListDetail() {
		return listDetail;
	}
	public void setListDetail(List<DetailImportOutput> listDetail) {
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
	public static NHAPHANG convertToNHAPHANG(ImportOutput nh)
	{
		NHAPHANG io= new NHAPHANG();
		if( nh == null) return null;
		io.setID(nh.getID());
		io.setNGAYNHAP(nh.getNGAYNHAP());
		NHANVIEN nv = new NHANVIEN();
		nv.setManv(nh.getMaNhanVien());
		io.setNhanVien(nv);
		List<CTNHAPHANG> listCTNH= new ArrayList<CTNHAPHANG>(); 
		for(DetailImportOutput x: nh.getListDetail())
		{
			CTNHAPHANG ct = DetailImportOutput.convertToCTNHAPHANG(x);
			ct.setMSNHAPHANG(nh.getID());
			listCTNH.add(ct);
		}
		io.setCTNHAPHANG(listCTNH);		
		return io;
	}
	public static ImportOutput convertToImportOutput(NHAPHANG nh)
	{
		ImportOutput io= new ImportOutput();
		if( nh == null) return null;
		io.setID(nh.getID());
		io.setMaNhanVien(nh.getNhanVien().getManv());
		io.setNGAYNHAP(nh.getNGAYNHAP());
		io.setTenNhanVien(nh.getNhanVien().getHoten());
		List<DetailImportOutput> listCTNH= new ArrayList<>(); 
		for (CTNHAPHANG x : nh.getCTNHAPHANG()) {
			DetailImportOutput ct = DetailImportOutput.convertToDetailImportOutput(x);
			listCTNH.add(ct);
		}
		io.setListDetail(listCTNH);
		return io;
	}
	public static List<ImportOutput> convertToListImportOutput(List<NHAPHANG> nh)
	{
		List<ImportOutput> io = new ArrayList<>();
		if (CollectionUtils.isEmpty(nh))
			return io;
		for (NHAPHANG x : nh) {
			io.add(convertToImportOutput(x));
		}
		return io;
	}
}
