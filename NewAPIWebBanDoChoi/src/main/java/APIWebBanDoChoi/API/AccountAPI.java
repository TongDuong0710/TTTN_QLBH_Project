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

import APIWebBanDoChoi.NewEntity.DSTAIKHOANk;
import APIWebBanDoChoi.NewEntity.KHACHHANGk;
import APIWebBanDoChoi.repository.AccountRepository;
import APIWebBanDoChoi.repository.CustomerRepository;
import APIWebBanDoChoi.service.impl.AccountService;


@RestController
public class AccountAPI {

	@Autowired
	private AccountService accountService;
	
	@GetMapping("account")
	public DSTAIKHOANk getAccount(@RequestParam("userName") String userName)
	{
		return accountService.findAcc(userName);
	}
	@GetMapping("checkAccount")
	public boolean checkAccount(@RequestParam("userName") String userName)
	{
		return accountService.checkAcc(userName);
	}
	
	@PostMapping("createAcc")
	public DSTAIKHOANk createAcc(@RequestBody DSTAIKHOANk acc)
	{
		return accountService.save(acc);
	}
	@PostMapping("deleteAcc")
	public DSTAIKHOANk deleteAcc(@RequestBody DSTAIKHOANk acc)
	{
		return accountService.deleteAcc(acc);
	}
	@PutMapping("updateAcc")
	public DSTAIKHOANk updateAcc(@RequestBody DSTAIKHOANk acc)
	{
		return accountService.save(acc);
	}
	@GetMapping(value = "findTK")
	public List<DSTAIKHOANk> findTK()
	{
		return accountService.findAll();
	}
}
