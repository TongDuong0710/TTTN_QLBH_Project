package APIWebBanDoChoi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import APIWebBanDoChoi.API.output.DetailImportOutput;
import APIWebBanDoChoi.API.output.ImportOutput;
import APIWebBanDoChoi.API.output.reportCharts;
import APIWebBanDoChoi.API.output.reportDoubleColumnCharts;
import APIWebBanDoChoi.NewEntity.CTDDHk;
import APIWebBanDoChoi.NewEntity.CTNHAPHANG;
import APIWebBanDoChoi.NewEntity.DDHk;
import APIWebBanDoChoi.NewEntity.HOADONk;
import APIWebBanDoChoi.NewEntity.NHAPHANG;
import APIWebBanDoChoi.NewEntity.SANPHAMk;
import APIWebBanDoChoi.repository.CustomerRepository;
import APIWebBanDoChoi.repository.DetailImportRepository;
import APIWebBanDoChoi.repository.DetailOrderRepository;
import APIWebBanDoChoi.repository.ImportRepository;
import APIWebBanDoChoi.repository.OrderRepository;
import APIWebBanDoChoi.repository.ProductRepository;

@Service
public class ImportService {
	@Autowired
	ImportRepository importRepository;
	@Autowired
	BillService billService;
	@Autowired
	ProductService productService;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	DetailImportRepository detailImportRepository;
	public List<NHAPHANG> findAll()
	{
		List<NHAPHANG> list= importRepository.findAll();
		return list;
	}
	public boolean save(ImportOutput nh) {
		NHAPHANG nhapHang = ImportOutput.convertToNHAPHANG(nh);
		System.out.println(nhapHang.getID());
		System.out.println(nhapHang.getNGAYNHAP());
		System.out.println(nhapHang.getNhanVien().getManv());
		for(CTNHAPHANG x: nhapHang.getCTNHAPHANG())
		{
			System.out.println("id "+ x.getID());
			System.out.println(x.getMSNHAPHANG());
			System.out.println(x.getSL());
			System.out.println(x.getDONGIA());
			System.out.println(x.getSanPham().getMaSP());
		}
		
		try {
			NHAPHANG nhapHangRespone = importRepository.save(nhapHang);
			for (DetailImportOutput dt : nh.getListDetail()) {
				CTNHAPHANG ctnhapHang = DetailImportOutput.convertToCTNHAPHANG(dt);
				ctnhapHang.setMSNHAPHANG(nhapHangRespone.getID());
				try
				{
					detailImportRepository.save(ctnhapHang);
				}
				catch (Exception e) {
					SANPHAMk sp = productRepository.findOneByMaSP(ctnhapHang.getSanPham().getMaSP());
					sp.setSoluongton(sp.getSoluongton()+ ctnhapHang.getSL());
					productService.saveProduct(sp);
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public List<reportCharts> findTKNhapHang()
	{
		List<NHAPHANG> list = importRepository.findAll();
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(NHAPHANG a: list)
		{
			int tongGia =0;
			for(CTNHAPHANG x: a.getCTNHAPHANG())
			{
				tongGia += x.getDONGIA()*x.getSL();
			}
			a.setTongGia(tongGia);
			a.setNGAYNHAP(a.getNGAYNHAP().substring(0,7));
			if(map.get(a.getNGAYNHAP())==null)
			{
				map.put(a.getNGAYNHAP(), (int)a.getTongGia());
			}
			else {
				map.put(a.getNGAYNHAP(), (int) a.getTongGia() + map.get(a.getNGAYNHAP()));
			}
		}
		Map<String,Integer> treeMap = new TreeMap<>(map);
		List<reportCharts> listReport = new ArrayList<reportCharts>();
		for(String a : treeMap.keySet())
		{
			reportCharts re = new reportCharts();
			re.setLabel(a);
			re.setY(treeMap.get(a).doubleValue());
			listReport.add(re);
		}
		return listReport;
	}
	
	
	public List<reportDoubleColumnCharts> findTKNhapHangXuatHang()
	{
		List<reportDoubleColumnCharts> list = new ArrayList<reportDoubleColumnCharts>();
		List<reportCharts> listC1Report = billService.findDoanhThu();
		List<reportCharts> listC2Report = findTKNhapHang();
		
		for(reportCharts x: listC1Report)
		{
			System.out.println("doanh thu: " + x.getLabel());
		}
		for(reportCharts x: listC2Report)
		{
			System.out.println("Nhap hang: " + x.getLabel());
		}
		
		
		for(reportCharts x:listC2Report)
		{
			x.setLabel(x.getLabel().replace("/", "-"));
			reportDoubleColumnCharts rp = new reportDoubleColumnCharts();
			rp.setLabel(x.getLabel());
			rp.setY2(x.getY());
			list.add(rp);
		}
		
		for(reportCharts x:listC1Report)
		{
			boolean kt = false;
			for(reportDoubleColumnCharts rp: list)
			{
				if(rp.getLabel().trim().equals(x.getLabel().trim()))
				{
					rp.setY(x.getY());
					kt= true;
				}
			}
			if(kt==false)
			{
				reportDoubleColumnCharts rp = new reportDoubleColumnCharts();
				rp.setLabel(x.getLabel());
				rp.setY(x.getY());
				list.add(rp);
			}
		}

		return list;
	}
}
