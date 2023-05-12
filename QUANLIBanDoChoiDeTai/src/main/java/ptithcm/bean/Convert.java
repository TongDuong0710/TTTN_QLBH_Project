package ptithcm.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import ptithcm.HTTPRequest.CustomerAPI;
import ptithcm.HTTPRequest.OrderAPI;
import ptithcm.entity.CTDDHk;
import ptithcm.entity.DDHk;
import ptithcm.entity.KHACHHANGk;


public class Convert {
	
	public static DDHk convertToDDH(OrderInput dh)
	{
		DDHk od= new DDHk();
		od.setMSDDH(dh.getMSDDH());
		od.setHoTenKH(dh.getHoTenKH());
		od.setMaKH(CustomerAPI.findKHByID(dh.getMaKH()));
		od.setDiaChi(dh.getDiaChi());
		od.setEmail(dh.getEmail());
		od.setSdt(dh.getSdt());
		od.setCTDDH(convertToListCTDH(dh.getCTDDH(),od));
		od.setTrangThai(dh.getTrangThai());
		return od;
	}
	public static List<DDHk> convertToListDDHk(List<OrderInput> list)
	{
		List<DDHk> listDDHk= new ArrayList<DDHk>();
		KHACHHANGk kh= new KHACHHANGk();
		if(list.size()>0)
		{
			CustomerAPI.findKHByID(list.get(0).getMaKH());
		}
		for(OrderInput dh : list)
		{
			DDHk od= new DDHk();
			od.setMSDDH(dh.getMSDDH());
			od.setHoTenKH(dh.getHoTenKH());
			od.setMaKH(kh);
			od.setDiaChi(dh.getDiaChi());
			od.setEmail(dh.getEmail());
			od.setSdt(dh.getSdt());
			od.setCTDDH(convertToListCTDH(dh.getCTDDH(),od));
			od.setTrangThai(dh.getTrangThai());
			listDDHk.add(od);
		}
		return listDDHk;
	}
	public static List<CTDDHk> convertToListCTDH(List<DetailOrderInput> list, DDHk dh)
	{
		List<CTDDHk> listdt= new ArrayList<CTDDHk>();
		for(DetailOrderInput ct: list)
		{
			CTDDHk dt= new CTDDHk();
			dt.setDonDatHang(dh);
			dt.setMSCTDDH(ct.getMSCTDDH());
			dt.setSanPham(ct.getSANPHAM());
			dt.setSoLuong(ct.getSoLuong());
			listdt.add(dt);
		}
		return listdt;
	}
	public static OrderInput convertToOrderInput(DDHk dh)
	{
		OrderInput od= new OrderInput();
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
		od.setCTDDH(convertToListDetailOrderInput(dh.getCTDDH()));
		od.setTrangThai(dh.getTrangThai());
		return od;
	}
	public static List<DetailOrderInput> convertToListDetailOrderInput(List<CTDDHk> list)
	{
		List<DetailOrderInput> listdt= new ArrayList<DetailOrderInput>();
		for(CTDDHk ct: list)
		{
			DetailOrderInput dt= new DetailOrderInput();
			dt.setDonDatHang(ct.getDonDatHang().getMSDDH());
			dt.setMSCTDDH(ct.getMSCTDDH());
			dt.setSANPHAM(ct.getSanPham());
			dt.setSoLuong(ct.getSoLuong());
			listdt.add(dt);
		}
		return listdt;
	}
}
