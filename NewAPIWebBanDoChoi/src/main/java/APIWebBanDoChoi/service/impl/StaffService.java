package APIWebBanDoChoi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import APIWebBanDoChoi.NewEntity.NHANVIEN;
import APIWebBanDoChoi.repository.StaffRepository;

@Service
public class StaffService {
	@Autowired
	StaffRepository staffRepository;	
	
	public List<NHANVIEN> findAll()
	{
		return staffRepository.findAll();
	}
	public int findMaxStaffID()
	{
		return staffRepository.findMaxStaffID();
	}
	public NHANVIEN findStaff(String id)
	{
		return staffRepository.findOne(id);
	}
	public NHANVIEN insertStaff(NHANVIEN staff)
	{
		return staffRepository.save(staff);
	}
	public NHANVIEN deleteStaff(NHANVIEN staff)
	{
		staffRepository.delete(staff);
		return staff;
	}
}
