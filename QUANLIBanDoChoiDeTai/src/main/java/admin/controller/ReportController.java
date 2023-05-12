package admin.controller;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.controller.LoginController;
import ptithcm.entity.CTDDH;
import ptithcm.entity.CTHD;
import ptithcm.entity.HOADON;


@Controller
@Transactional
@RequestMapping("/report/")
public class ReportController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("reportBDCOT")
	public String index(ModelMap model)
	{
		model.addAttribute("nv", LoginController.nv);
		model.addAttribute("tk", LoginController.taikhoan);
		return "admin/view/reportBDCOT";
	}
	@RequestMapping("reportBDTRON")
	public String index2(ModelMap model)
	{
		model.addAttribute("nv", LoginController.nv);
		model.addAttribute("tk", LoginController.taikhoan);
		return "admin/view/reportBDTRON";
	}
	@RequestMapping("reportNhapHang")
	public String index3(ModelMap model)
	{
		model.addAttribute("nv", LoginController.nv);
		model.addAttribute("tk", LoginController.taikhoan);
		return "admin/view/reportNhapHang";
	}
	@RequestMapping("reportNhapHangXuatHang")
	public String index4(ModelMap model)
	{
		model.addAttribute("nv", LoginController.nv);
		model.addAttribute("tk", LoginController.taikhoan);
		return "admin/view/reportNhapHangXuatHang";
	}
	
	
}
