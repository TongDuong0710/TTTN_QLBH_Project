package APIWebBanDoChoi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import APIWebBanDoChoi.NewEntity.CTDDHk;
import APIWebBanDoChoi.NewEntity.DDHk;
import APIWebBanDoChoi.repository.CustomerRepository;
import APIWebBanDoChoi.repository.DetailOrderRepository;
import APIWebBanDoChoi.repository.OrderRepository;
import APIWebBanDoChoi.repository.ProductRepository;

@Service
public class OrderService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	DetailOrderRepository detailOrderRepository;
	
	public DDHk getOrderByID(int MSDDH)
	{
		return orderRepository.findOne(MSDDH);
	}
	public List<DDHk> getHistoryOrder(int maKH)
	{
		return orderRepository.findHistoryOrder(maKH);
	}
	public DDHk insertOrder(DDHk od)
	{
		DDHk newDDH = orderRepository.save(od);
		List<CTDDHk> listCT= new ArrayList<>();
		for(CTDDHk dt: od.getCTDDH())
		{
			dt.setDonDatHang(newDDH);
			detailOrderRepository.save(dt);
		}
		return od;
	}
	public DDHk cancelOrder(DDHk od)
	{
		od.setTrangThai("CANCELLED");
		orderRepository.save(od);
		return od;
	}
	public List<DDHk> getAllOrder() {
		return  orderRepository.findAll();
	}
	
}
