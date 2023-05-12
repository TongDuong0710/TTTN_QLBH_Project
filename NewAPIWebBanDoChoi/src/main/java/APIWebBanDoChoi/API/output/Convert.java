package APIWebBanDoChoi.API.output;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import APIWebBanDoChoi.NewEntity.CTDDHk;
import APIWebBanDoChoi.NewEntity.DDHk;
import APIWebBanDoChoi.service.impl.CustomerService;
import APIWebBanDoChoi.service.impl.OrderService;

@Component
public class Convert {
	@Autowired
	CustomerService customerService;
	@Autowired
	OrderService orderService;
	public OrderOutput convertToOrderOut(DDHk dh)
	{
		OrderOutput od= new OrderOutput();
		od.setMSDDH(dh.getMSDDH());
		od.setHoTenKH(dh.getHoTenKH());
		od.setMaKH(dh.getMaKH().getMaKH());
		od.setDiaChi(dh.getDiaChi());
		od.setEmail(dh.getEmail());
		od.setSdt(dh.getSdt());
		od.setCTDDH(convertToListDetailOrderOut(dh.getCTDDH()));
		od.setTrangThai(dh.getTrangThai());
		return od;
	}
	public DDHk convertToDDH(OrderOutput dh)
	{
		DDHk od= new DDHk();
		od.setMSDDH(dh.getMSDDH());
		od.setHoTenKH(dh.getHoTenKH());
		od.setMaKH(customerService.findOneByMaKH(dh.getMaKH()));
		od.setDiaChi(dh.getDiaChi());
		od.setEmail(dh.getEmail());
		od.setSdt(dh.getSdt());
		od.setCTDDH(convertToListCTDH(dh.getCTDDH()));
		od.setTrangThai(dh.getTrangThai());
		return od;
	}
	public List<OrderOutput> convertToListOrderOut(List<DDHk> list)
	{
		List<OrderOutput> listOd= new ArrayList<OrderOutput>();
		for(DDHk dh: list)
		{
			OrderOutput od= new OrderOutput();
			od.setMSDDH(dh.getMSDDH());
			od.setHoTenKH(dh.getHoTenKH());
			if(dh.getMaKH()==null)
			{
				od.setMaKH(0);
			}
			else
			{
				od.setMaKH(dh.getMaKH().getMaKH());
			}
			od.setDiaChi(dh.getDiaChi());
			od.setEmail(dh.getEmail());
			od.setSdt(dh.getSdt());
			od.setCTDDH(convertToListDetailOrderOut(dh.getCTDDH()));
			od.setTrangThai(dh.getTrangThai());
			listOd.add(od);
		}
		return listOd;
	}
	public static DetailOrderOutput convertToDetailOrderOut(CTDDHk ct)
	{
		DetailOrderOutput dt= new DetailOrderOutput();
		dt.setDonDatHang(ct.getDonDatHang().getMSDDH());
		dt.setMSCTDDH(ct.getMSCTDDH());
		dt.setSanPham(ct.getSanPham());
		dt.setSoLuong(ct.getSoLuong());
		return dt;
	}
	public static List<DetailOrderOutput> convertToListDetailOrderOut(List<CTDDHk> list)
	{
		List<DetailOrderOutput> listdt= new ArrayList<DetailOrderOutput>();
		for(CTDDHk ct: list)
		{
			DetailOrderOutput dt= new DetailOrderOutput();
			dt.setDonDatHang(ct.getDonDatHang().getMSDDH());
			dt.setMSCTDDH(ct.getMSCTDDH());
			dt.setSanPham(ct.getSanPham());
			dt.setSoLuong(ct.getSoLuong());
			listdt.add(dt);
		}
		return listdt;
	}
	public List<CTDDHk> convertToListCTDH(List<DetailOrderOutput> list)
	{
		List<CTDDHk> listdt= new ArrayList<CTDDHk>();
		for(DetailOrderOutput ct: list)
		{
			CTDDHk dt= new CTDDHk();
			dt.setDonDatHang(orderService.getOrderByID(ct.getDonDatHang()));
			dt.setMSCTDDH(ct.getMSCTDDH());
			dt.setSanPham(ct.getSanPham());
			dt.setSoLuong(ct.getSoLuong());
			listdt.add(dt);
		}
		return listdt;
	}
}
