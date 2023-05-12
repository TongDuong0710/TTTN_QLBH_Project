package APIWebBanDoChoi.API;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import APIWebBanDoChoi.API.output.Statistic;
import APIWebBanDoChoi.API.output.reportCharts;
import APIWebBanDoChoi.API.output.reportDoubleColumnCharts;
import APIWebBanDoChoi.API.output.test;
import APIWebBanDoChoi.NewEntity.CTNHAPHANG;
import APIWebBanDoChoi.NewEntity.HOADONk;
import APIWebBanDoChoi.NewEntity.NHAPHANG;
import APIWebBanDoChoi.repository.BillRepository;
import APIWebBanDoChoi.repository.ImportRepository;
import APIWebBanDoChoi.service.impl.BillService;
import APIWebBanDoChoi.service.impl.ImportService;


@RestController
public class ReportAPI {

	@Autowired
	private BillService billService;
	@Autowired
	private ImportService importService;
	@Autowired
	private BillRepository billRepository;
	@Autowired
	private ImportRepository importRepository;
	@GetMapping("/findChiTieu/{id}.json")
	public List<reportCharts> findChiTieu(@PathVariable("id") int id)
	{
		return billService.findChiTieu(id);
	}
	@GetMapping("/findDoanhThu.json")
	public List<reportCharts> findDoanhThu()
	{
		return billService.findDoanhThu();
	}
	@GetMapping("/findDoanhThuList.json")
	public test findDoanhThuList()
	{
		test t = new test();
		t.setList(billService.findDoanhThu());
		return t;
	}
	@GetMapping("/findTKNhapHang.json")
	public List<reportCharts> findTKNhapHang()
	{
		return importService.findTKNhapHang();
	}
	@GetMapping("/findTKNhapHangXuatHang.json")
	public List<reportDoubleColumnCharts> findTKNhapHangXuatHang()
	{
		return importService.findTKNhapHangXuatHang();
	}
	
	@GetMapping("/findDoanhThu/{startDate}/{endDate}.json")
	public List<Statistic> findDoanhThuTheoNgay(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws ParseException
	{
		List<HOADONk> listBill = billRepository.findAll();
		List<HOADONk> listBillFilter = new ArrayList<HOADONk>();
		Date startDates = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		Date endDates = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		for(HOADONk hd : listBill)
		{
			 String sDate= hd.getNgayLapHoaDon().trim();  
			 Date date= new SimpleDateFormat("yyyy-MM-dd").parse(sDate); 
			 if(date.compareTo(endDates) <= 0 && date.compareTo(startDates)>=0)
			 {
				 listBillFilter.add(hd);
			 }
		}
		List<Statistic> listStatistic = new ArrayList<>();
		for(HOADONk hd : listBillFilter)
		{
			boolean kt = true;
			for(Statistic statistic : listStatistic)
			{
				if(statistic.getDate().trim().equals(hd.getNgayLapHoaDon().trim()))
				{
					statistic.setMoney(statistic.getMoney() + (int) hd.getTongGia());
					statistic.setSlDH(statistic.getSlDH() +  1);
					kt = false;
				}
			}
			if(kt == true)
			{
				Statistic st = new Statistic();
				st.setDate(hd.getNgayLapHoaDon().trim());
				st.setMoney((int)hd.getTongGia());
				st.setSlDH(1);
				listStatistic.add(st);
			}
		}
		Collections.sort(listStatistic, new Comparator<Statistic>() {

			@Override
			public int compare(Statistic o1, Statistic o2) {
				try {
					return ReportAPI.compareDate(o2.getDate(), o1.getDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
			}

		});
		return listStatistic;
	}
	@GetMapping("/findNhapHang/{startDate}/{endDate}.json")
	public List<Statistic> findNhapHangTheoNgay(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws ParseException
	{
		List<NHAPHANG> listBill = importRepository.findAll();
		List<NHAPHANG> listBillFilter = new ArrayList<NHAPHANG>();
		Date startDates = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		Date endDates = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		for(NHAPHANG hd : listBill)
		{
			 String sDate= hd.getNGAYNHAP().trim();  
			 Date date= new SimpleDateFormat("yyyy-MM-dd").parse(sDate); 
			 if(date.compareTo(endDates) <= 0 && date.compareTo(startDates)>=0)
			 {
				 listBillFilter.add(hd);
			 }
		}
		List<Statistic> listStatistic = new ArrayList<>();
		for(NHAPHANG hd : listBillFilter)
		{
			boolean kt = true;
			int tongTien1Don = 0;
			for(CTNHAPHANG ct : hd.getCTNHAPHANG())
			{
				tongTien1Don += ct.getDONGIA() * ct.getSL();
			}
			
			for(Statistic statistic : listStatistic)
			{
				if(statistic.getDate().trim().equals(hd.getNGAYNHAP().trim()))
				{
					statistic.setMoney(statistic.getMoney() + tongTien1Don);
					statistic.setSlDH(statistic.getSlDH() +  1);
					kt = false;
				}
			}
			if(kt == true)
			{
				Statistic st = new Statistic();
				st.setDate(hd.getNGAYNHAP().trim());
				st.setMoney(tongTien1Don);
				st.setSlDH(1);
				listStatistic.add(st);
			}
		}
		Collections.sort(listStatistic, new Comparator<Statistic>() {

			@Override
			public int compare(Statistic o1, Statistic o2) {
				try {
					return ReportAPI.compareDate(o2.getDate(), o1.getDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
			}

		});
		return listStatistic;
	}
	@GetMapping("/findDoanhThuThang/{startDate}/{endDate}.json")
	public List<Statistic> findDoanhThuTheoThang(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws ParseException
	{
		List<HOADONk> listBill = billRepository.findAll();
		List<HOADONk> listBillFilter = new ArrayList<HOADONk>();
		Date startDates = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		Date endDates = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		for(HOADONk hd : listBill)
		{
			 String sDate= hd.getNgayLapHoaDon().trim();  
			 Date date= new SimpleDateFormat("yyyy-MM-dd").parse(sDate); 
			 if(date.compareTo(endDates) <= 0 && date.compareTo(startDates)>=0)
			 {
				 listBillFilter.add(hd);
			 }
		}
		List<Statistic> listStatistic = new ArrayList<>();
		for(HOADONk hd : listBillFilter)
		{
			boolean kt = true;
			for(Statistic statistic : listStatistic)
			{
				if(statistic.getDate().equals(hd.getNgayLapHoaDon().trim().substring(0,7)))
				{
					statistic.setMoney(statistic.getMoney() + (int) hd.getTongGia());
					statistic.setSlDH(statistic.getSlDH() +  1);
					kt = false;
				}
			}
			if(kt == true)
			{
				Statistic st = new Statistic();
				st.setDate(hd.getNgayLapHoaDon().trim().substring(0,7));
				st.setMoney((int)hd.getTongGia());
				st.setSlDH(1);
				listStatistic.add(st);
			}
		}
		Collections.sort(listStatistic, new Comparator<Statistic>() {

			@Override
			public int compare(Statistic o1, Statistic o2) {
				try {
					return ReportAPI.compareDateM(o2.getDate(), o1.getDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
			}

		});
		return listStatistic;
	}
	@GetMapping("/findNhapHangThang/{startDate}/{endDate}.json")
	public List<Statistic> findNhapHangTheoThang(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws ParseException
	{
		List<NHAPHANG> listBill = importRepository.findAll();
		List<NHAPHANG> listBillFilter = new ArrayList<NHAPHANG>();
		Date startDates = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		Date endDates = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		for(NHAPHANG hd : listBill)
		{
			 String sDate= hd.getNGAYNHAP().trim();  
			 Date date= new SimpleDateFormat("yyyy-MM-dd").parse(sDate); 
			 if(date.compareTo(endDates) <= 0 && date.compareTo(startDates)>=0)
			 {
				 listBillFilter.add(hd);
			 }
		}
		List<Statistic> listStatistic = new ArrayList<>();
		for(NHAPHANG hd : listBillFilter)
		{
			boolean kt = true;
			
			int tongTien1Don = 0;
			for(CTNHAPHANG ct : hd.getCTNHAPHANG())
			{
				tongTien1Don += ct.getDONGIA() * ct.getSL();
			}
			hd.setTongGia(tongTien1Don);
			for(Statistic statistic : listStatistic)
			{
				if(statistic.getDate().equals(hd.getNGAYNHAP().trim().substring(0,7)))
				{
					statistic.setMoney(statistic.getMoney() + (int) hd.getTongGia());
					statistic.setSlDH(statistic.getSlDH() +  1);
					kt = false;
				}
			}
			if(kt == true)
			{
				Statistic st = new Statistic();
				st.setDate(hd.getNGAYNHAP().trim().substring(0,7));
				st.setMoney((int)hd.getTongGia());
				st.setSlDH(1);
				listStatistic.add(st);
			}
		}
		Collections.sort(listStatistic, new Comparator<Statistic>() {

			@Override
			public int compare(Statistic o1, Statistic o2) {
				try {
					return ReportAPI.compareDateM(o2.getDate(), o1.getDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
			}

		});
		return listStatistic;
	}
	@GetMapping("/findChiTieu/{id}/{startDate}/{endDate}.json")
	public List<Statistic> findChiTieuTheoThang(@PathVariable("id") int id, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws ParseException
	{
		List<HOADONk> listBill = billRepository.findAll();
		List<HOADONk> listBillFilter = new ArrayList<HOADONk>();
		Date startDates = new SimpleDateFormat("yyyy-MM-dd").parse(startDate.concat("-01"));
		Date endDates = new SimpleDateFormat("yyyy-MM-dd").parse(endDate.concat("-01"));
		for(HOADONk hd : listBill)
		{
			if(hd.getMaKH() == id)
			{
				String sDate= hd.getNgayLapHoaDon().trim();  
				 Date date= new SimpleDateFormat("yyyy-MM-dd").parse(sDate); 
				 if(date.compareTo(endDates) <= 0 && date.compareTo(startDates)>=0)
				 {
					 listBillFilter.add(hd);
				 }
			}
		}
		List<Statistic> listStatistic = new ArrayList<>();
		for(HOADONk hd : listBillFilter)
		{
			boolean kt = true;
			for(Statistic statistic : listStatistic)
			{
				if(statistic.getDate().equals(hd.getNgayLapHoaDon().trim().substring(0,7)))
				{
					statistic.setMoney(statistic.getMoney() + (int) hd.getTongGia());
					statistic.setSlDH(statistic.getSlDH() +  1);
					kt = false;
				}
			}
			if(kt == true)
			{
				Statistic st = new Statistic();
				st.setDate(hd.getNgayLapHoaDon().trim().substring(0,7));
				st.setMoney((int)hd.getTongGia());
				st.setSlDH(1);
				listStatistic.add(st);
			}
		}
		Collections.sort(listStatistic, new Comparator<Statistic>() {

			@Override
			public int compare(Statistic o1, Statistic o2) {
				try {
					return ReportAPI.compareDateM(o2.getDate(), o1.getDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
			}

		});
		return listStatistic;
	}
	@GetMapping("/findLoiNhuan/{startDate}/{endDate}.json")
	public List<Statistic> findLoiNhuan(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws ParseException
	{
		startDate = startDate.concat("-01");
		endDate = endDate.concat("-01");
		List<NHAPHANG> listBill = importRepository.findAll();
		List<NHAPHANG> listBillFilter = new ArrayList<NHAPHANG>();
		Date startDates = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		Date endDates = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		for(NHAPHANG hd : listBill)
		{
			 String sDate= hd.getNGAYNHAP().trim();  
			 Date date= new SimpleDateFormat("yyyy-MM-dd").parse(sDate); 
			 if(date.compareTo(endDates) <= 0 && date.compareTo(startDates)>=0)
			 {
				 listBillFilter.add(hd);
			 }
		}
		List<Statistic> listStatistic = new ArrayList<>();
		for(NHAPHANG hd : listBillFilter)
		{
			boolean kt = true;
			
			int tongTien1Don = 0;
			for(CTNHAPHANG ct : hd.getCTNHAPHANG())
			{
				tongTien1Don += ct.getDONGIA() * ct.getSL();
			}
			hd.setTongGia(tongTien1Don);
			for(Statistic statistic : listStatistic)
			{
				if(statistic.getDate().equals(hd.getNGAYNHAP().trim().substring(0,7)))
				{
					statistic.setMoney2(statistic.getMoney2() + (int) hd.getTongGia());
					kt = false;
				}
			}
			if(kt == true)
			{
				Statistic st = new Statistic();
				st.setDate(hd.getNGAYNHAP().trim().substring(0,7));
				st.setMoney2((int)hd.getTongGia());
				listStatistic.add(st);
			}
		}
		
		List<HOADONk> listHD = billRepository.findAll();
		List<HOADONk> listHDFilter = new ArrayList<HOADONk>();
		for(HOADONk hd : listHD)
		{
			 String sDate= hd.getNgayLapHoaDon().trim();  
			 Date date= new SimpleDateFormat("yyyy-MM-dd").parse(sDate); 
			 if(date.compareTo(endDates) <= 0 && date.compareTo(startDates)>=0)
			 {
				 listHDFilter.add(hd);
			 }
		}
		for(HOADONk hd : listHDFilter)
		{
			boolean kt = true;
			for(Statistic statistic : listStatistic)
			{
				if(statistic.getDate().equals(hd.getNgayLapHoaDon().trim().substring(0,7)))
				{
					statistic.setMoney(statistic.getMoney() + (int) hd.getTongGia());
					kt = false;
				}
			}
			if(kt == true)
			{
				Statistic st = new Statistic();
				st.setDate(hd.getNgayLapHoaDon().trim().substring(0,7));
				st.setMoney((int)hd.getTongGia());
				listStatistic.add(st);
			}
		}
		
		for(Statistic st : listStatistic)
		{
			st.setProfit(st.getMoney() - st.getMoney2());
		}
		
		
		
		
		Collections.sort(listStatistic, new Comparator<Statistic>() {

			@Override
			public int compare(Statistic o1, Statistic o2) {
				try {
					return ReportAPI.compareDateM(o2.getDate(), o1.getDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
			}

		});
		return listStatistic;
	}
	 public static int compareDate(String date1, String date2) throws ParseException
	 {
		 Date date1d = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
		 Date date2d = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
		 if(date1d.compareTo(date2d) < 0) return -1;
		 else if(date1d.compareTo(date2d) > 0) return 1;
		 else return 0;
	 }
	 public static int compareDateM(String date1, String date2) throws ParseException
	 {
		 Date date1d = new SimpleDateFormat("yyyy-MM-dd").parse(date1.concat("-00"));
		 Date date2d = new SimpleDateFormat("yyyy-MM-dd").parse(date2.concat("-00"));
		 if(date1d.compareTo(date2d) < 0) return -1;
		 else if(date1d.compareTo(date2d) > 0) return 1;
		 else return 0;
	 }
}
