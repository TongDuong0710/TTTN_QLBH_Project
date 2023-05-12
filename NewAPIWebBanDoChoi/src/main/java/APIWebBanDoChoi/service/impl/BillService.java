package APIWebBanDoChoi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import APIWebBanDoChoi.API.output.reportCharts;
import APIWebBanDoChoi.NewEntity.HOADONk;
import APIWebBanDoChoi.repository.BillRepository;

@Service
public class BillService {
	@Autowired
	BillRepository billRepository;	
	
	public List<HOADONk> findAllBill()
	{
		return billRepository.findAll();
	}
	public HOADONk findOne(String id)
	{
		return billRepository.findOne(id);
	}
	public int findMaxBillID()
	{
		return billRepository.findMaxBillID();
	}
	public List<HOADONk> findBillByID(int Id)
	{
		return billRepository.findByMaKH(Id);
	}
	public List<reportCharts> findChiTieu(int Id)
	{
		List<HOADONk> list = billRepository.findByMaKH(Id);
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(HOADONk a: list)
		{
			a.setNgayLapHoaDon(a.getNgayLapHoaDon().substring(0,7));
			if(map.get(a.getNgayLapHoaDon())==null)
			{
				map.put(a.getNgayLapHoaDon(), (int)a.getTongGia());
			}
			else {
				map.put(a.getNgayLapHoaDon(), (int) a.getTongGia() + map.get(a.getNgayLapHoaDon()));
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
	public List<reportCharts> findDoanhThu()
	{
		List<HOADONk> list = billRepository.findAll();
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(HOADONk a: list)
		{
			a.setNgayLapHoaDon(a.getNgayLapHoaDon().substring(0,7));
			if(map.get(a.getNgayLapHoaDon())==null)
			{
				map.put(a.getNgayLapHoaDon(), (int)a.getTongGia());
			}
			else {
				map.put(a.getNgayLapHoaDon(), (int) a.getTongGia() + map.get(a.getNgayLapHoaDon()));
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
}
