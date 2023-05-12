package APIWebBanDoChoi.API;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import APIWebBanDoChoi.NewEntity.NHANVIEN;
import APIWebBanDoChoi.service.impl.AccountService;
import APIWebBanDoChoi.service.impl.StaffService;


@RestController
public class StaffAPI {

	@Autowired
	private StaffService staffService;
	
	@GetMapping("findAllStaff")
	public List<NHANVIEN> findAllStaff()
	{
		return staffService.findAll();
	}
	@GetMapping("findMaxStaffID")
	public int findMaxStaffID()
	{
		return staffService.findMaxStaffID();
	}
	@GetMapping("findOneStaff")
	public NHANVIEN findOneStaff(String id)
	{
		return staffService.findStaff(id);
	}
	@PostMapping("saveStaff")
	public NHANVIEN saveStaff(@RequestBody NHANVIEN staff)
	{
		return staffService.insertStaff(staff);
	}
	@PostMapping("deleteStaff")
	public NHANVIEN deleteStaff(@RequestBody NHANVIEN staff)
	{
		return staffService.deleteStaff(staff);
	}
	
}
