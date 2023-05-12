package APIWebBanDoChoi.API;

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

import APIWebBanDoChoi.API.output.reportCharts;
import APIWebBanDoChoi.NewEntity.HOADONk;
import APIWebBanDoChoi.repository.BillRepository;
import APIWebBanDoChoi.service.impl.BillService;


@RestController
public class BillAPI {

	@Autowired
	private BillService billService;
	@GetMapping("/findAllBill")
	public List<HOADONk> findAll()
	{
		return billService.findAllBill();
	}
	@GetMapping("/findOneBill/{id}")
	public HOADONk findOne( @PathVariable("id") String id)
	{
		return billService.findOne(id);
	}
	@GetMapping("/findMaxBillID")
	public int findMaxBillID()
	{
		return billService.findMaxBillID();
	}
	@GetMapping("/findBillByID/{id}")
	public List<HOADONk> findBillByID(@PathVariable("id") int id)
	{
		return billService.findBillByID(id);
	}
}
