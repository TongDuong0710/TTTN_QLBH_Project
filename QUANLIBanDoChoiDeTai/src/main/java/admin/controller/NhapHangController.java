package admin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.HTTPRequest.ImportAPI;
import ptithcm.HTTPRequest.ProductAPI;
import ptithcm.bean.DetailImportInput;
import ptithcm.bean.ImportInput;
import ptithcm.controller.LoginController;
import ptithcm.entity.CTDDH;
import ptithcm.entity.CTHD;
import ptithcm.entity.DSTAIKHOAN;
import ptithcm.entity.HOADON;
import ptithcm.entity.NHANVIEN;
import ptithcm.entity.SANPHAMk;


@Controller
@Transactional
@RequestMapping("/nhapHang/")
public class NhapHangController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("importList")
	public String index(ModelMap model ,HttpServletRequest request)
	{
		List<ImportInput> listNH = ImportAPI.findAll();
		model.addAttribute("listNhapHang", listNH);
		model.addAttribute("nv", LoginController.nv);
		model.addAttribute("tk", LoginController.taikhoan);
		return "admin/view/list-nhapHang";
	}
	
	@RequestMapping(value = "import/insert", method = RequestMethod.GET)
	public String insert(ModelMap model,@ModelAttribute("NH") ImportInput NH)
	{
		List<SANPHAMk> listSP = ProductAPI.findAllSP();
		model.addAttribute("listSP", listSP);
		model.addAttribute("nv", LoginController.nv);
		model.addAttribute("tk", LoginController.taikhoan);
		return "admin/view/add-nhapHang";
	}
	
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String insert2(ModelMap model,@ModelAttribute("NH") ImportInput NH, @PathVariable("id") String id)
	{
		List<ImportInput> listNH = ImportAPI.findAll();
		ImportInput x = new ImportInput();
		for(ImportInput im: listNH)
		{
			if(im.getID() == Integer.valueOf(id.trim()))
			{
				x = im;
				break;
			}
		}
		if(x.getListDetail() != null)
		{
			int tongTien = 0;
			for(DetailImportInput tam: x.getListDetail())
			{
				tongTien += tam.getDONGIA()*tam.getSL();
			}
			model.addAttribute("tongTien", tongTien);
		}
		model.addAttribute("nv", LoginController.nv);
		model.addAttribute("nhaphang", x);
		model.addAttribute("tk", LoginController.taikhoan);
		return "admin/view/chitiet-nhaphang";
	}
	@RequestMapping(value = "import/add", method = RequestMethod.POST)
	public String add(ModelMap model,@ModelAttribute("NH") ImportInput NH)
	{
		System.out.println("add nhập hàng");
		List<DetailImportInput> listNH = new ArrayList<>();
		for(DetailImportInput x: NH.getListDetail())
		{
			if(x.getMaSanPham() != null)
			{
				System.out.println("MASP: "+x.getMaSanPham());
				DetailImportInput tam = new DetailImportInput();
				tam.setMaSanPham(x.getMaSanPham().trim());
				tam.setDONGIA(x.getDONGIA());
				tam.setSL(x.getSL());
				listNH.add(tam);
			}
		}
		NH.setListDetail(listNH);
		if(ImportAPI.save(NH))
		{
			model.addAttribute("message", "Nhập Hàng Thành Công !");
			model.addAttribute("link", "/nhapHang/importList");
		}
		else {
			model.addAttribute("message", "Nhập Hàng Thất Bại !");
			model.addAttribute("link", "/nhapHang/importList");
		}
		model.addAttribute("nv", LoginController.nv);
		model.addAttribute("tk", LoginController.taikhoan);
		return "admin/view/Success";
	}
}
