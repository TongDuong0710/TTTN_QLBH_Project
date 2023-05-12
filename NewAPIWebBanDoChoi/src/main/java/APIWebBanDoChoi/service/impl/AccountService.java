package APIWebBanDoChoi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import APIWebBanDoChoi.NewEntity.DSTAIKHOANk;
import APIWebBanDoChoi.NewEntity.KHACHHANGk;
import APIWebBanDoChoi.repository.AccountRepository;
import APIWebBanDoChoi.repository.CustomerRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;	
	@Autowired
	CustomerRepository customerRepository;
	
	public List<DSTAIKHOANk> findAll()
	{
		return accountRepository.findAll();
	}
	public boolean checkAcc(String userName)
	{
		if(accountRepository.findOneByTaiKhoan(userName)!=null)
		{
			return true;
		}
		return false;
	}
	public DSTAIKHOANk findAcc(String userName)
	{
		DSTAIKHOANk acc= accountRepository.findOneByTaiKhoan(userName);
		return acc;
	}
	public DSTAIKHOANk save(DSTAIKHOANk acc)
	{
		DSTAIKHOANk tk= acc;
		accountRepository.save(tk);
		return acc;
	}
	public DSTAIKHOANk deleteAcc(DSTAIKHOANk acc)
	{
		accountRepository.delete(acc);
		return acc;
	}
}
