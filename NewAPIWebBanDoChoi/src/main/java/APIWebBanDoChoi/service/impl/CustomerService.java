package APIWebBanDoChoi.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import APIWebBanDoChoi.NewEntity.DSTAIKHOANk;
import APIWebBanDoChoi.NewEntity.KHACHHANGk;
import APIWebBanDoChoi.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	public List<KHACHHANGk> findAll()
	{
		return customerRepository.findAll();
	}
	public boolean saveCustomer(KHACHHANGk kh)
	{
		customerRepository.save(kh);
		return true;
	}
	public KHACHHANGk updateCus(KHACHHANGk kh)
	{
		customerRepository.save(kh);
		return kh;
	}
	public KHACHHANGk findOneByMaKH(int id)
	{
		return customerRepository.findOne(id);
	}
	public KHACHHANGk findKHByTK(String tk)
	{
		return customerRepository.findKHByTK(tk);
	}
}
