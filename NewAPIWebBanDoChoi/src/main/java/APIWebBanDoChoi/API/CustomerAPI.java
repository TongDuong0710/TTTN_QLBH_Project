package APIWebBanDoChoi.API;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import APIWebBanDoChoi.API.output.CustomerOutput;
import APIWebBanDoChoi.NewEntity.DSTAIKHOANk;
import APIWebBanDoChoi.NewEntity.KHACHHANGk;
import APIWebBanDoChoi.repository.CustomerRepository;
import APIWebBanDoChoi.service.impl.CustomerService;

@RestController
public class CustomerAPI {
	@Autowired
	CustomerService customerService;

	@PostMapping("createCus")
	public boolean createAcc(@RequestBody KHACHHANGk kh) {
		return customerService.saveCustomer(kh);
	}

	@PutMapping(value = "updateCus/{id}")
	public KHACHHANGk updateNew(@RequestBody KHACHHANGk kh, @PathVariable("id") int id) {
		kh.setMaKH(id);
		return customerService.updateCus(kh);
	}

	@GetMapping(value = "findKHByTK/{id}")
	public CustomerOutput findKHByTK(@PathVariable("id") String id) {
		KHACHHANGk kh = customerService.findKHByTK(id);
		CustomerOutput cus = new CustomerOutput();
		cus.setMaKH(kh.getMaKH());
		cus.setHoTen(kh.getHoTen());
		cus.setDiaChi(kh.getDiaChi());
		cus.setEmail(kh.getEmail());
		cus.setSdt(kh.getSdt());
		cus.setTaiKhoan(kh.getTaiKhoan());
		return cus;
	}
	@GetMapping(value = "findKHByID/{id}")
	public CustomerOutput findKHByTK(@PathVariable("id") int id) {
		KHACHHANGk kh = customerService.findOneByMaKH(id);
		CustomerOutput cus = new CustomerOutput();
		cus.setMaKH(kh.getMaKH());
		cus.setHoTen(kh.getHoTen());
		cus.setDiaChi(kh.getDiaChi());
		cus.setEmail(kh.getEmail());
		cus.setSdt(kh.getSdt());
		cus.setTaiKhoan(kh.getTaiKhoan());
		return cus;
	}

	@GetMapping(value = "findKH")
	public List<CustomerOutput> findKH() {
		List<CustomerOutput> list = new ArrayList<CustomerOutput>();
		for (KHACHHANGk kh : customerService.findAll()) {
			CustomerOutput cus = new CustomerOutput();
			cus.setMaKH(kh.getMaKH());
			cus.setHoTen(kh.getHoTen());
			cus.setDiaChi(kh.getDiaChi());
			cus.setEmail(kh.getEmail());
			cus.setSdt(kh.getSdt());
			cus.setTaiKhoan(kh.getTaiKhoan());
			list.add(cus);
		}
		return list;
	}
}
