package com.example.appbandochoi.bean;

import com.example.appbandochoi.HTTPRequest.CustomerAPI;
import com.example.appbandochoi.entity.CTDDH;
import com.example.appbandochoi.entity.DDH;
import com.example.appbandochoi.entity.KHACHHANG;

import java.util.ArrayList;
import java.util.List;



public class Convert {
	
	public static DDH convertToDDH(OrderInput dh)
	{
		DDH od= new DDH();
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
	public static List<DDH> convertToListDDH(List<OrderInput> list)
	{
		List<DDH> listDDH= new ArrayList<DDH>();
		KHACHHANG kh= new KHACHHANG();
		if(list.size()>0)
		{
			CustomerAPI.findKHByID(list.get(0).getMaKH());
		}
		for(OrderInput dh : list)
		{
			DDH od= new DDH();
			od.setMSDDH(dh.getMSDDH());
			od.setHoTenKH(dh.getHoTenKH());
			od.setMaKH(kh);
			od.setDiaChi(dh.getDiaChi());
			od.setEmail(dh.getEmail());
			od.setSdt(dh.getSdt());
			od.setCTDDH(convertToListCTDH(dh.getCTDDH(),od));
			od.setTrangThai(dh.getTrangThai());
			listDDH.add(od);
		}
		return listDDH;
	}
	public static List<CTDDH> convertToListCTDH(List<DetailOrderInput> list, DDH dh)
	{
		List<CTDDH> listdt= new ArrayList<CTDDH>();
		for(DetailOrderInput ct: list)
		{
			CTDDH dt= new CTDDH();
			dt.setDonDatHang(dh);
			dt.setMSCTDDH(ct.getMSCTDDH());
			dt.setSanPham(ct.getSanPham());
			dt.setSoLuong(ct.getSoLuong());
			listdt.add(dt);
		}
		return listdt;
	}
	public static OrderInput convertToOrderInput(DDH dh)
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
	public static List<DetailOrderInput> convertToListDetailOrderInput(List<CTDDH> list)
	{
		List<DetailOrderInput> listdt= new ArrayList<DetailOrderInput>();
		for(CTDDH ct: list)
		{
			DetailOrderInput dt= new DetailOrderInput();
			dt.setDonDatHang(ct.getDonDatHang().getMSDDH());
			dt.setMSCTDDH(ct.getMSCTDDH());
			dt.setSanPham(ct.getSanPham());
			dt.setSoLuong(ct.getSoLuong());
			listdt.add(dt);
		}
		return listdt;
	}
}
