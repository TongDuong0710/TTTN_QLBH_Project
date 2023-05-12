package ptithcm.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ptithcm.HTTPRequest.AccountAPI;
import ptithcm.HTTPRequest.CustomerAPI;
import ptithcm.entity.DSTAIKHOAN;
import ptithcm.entity.KHACHHANG;
import ptithcm.entity.SANPHAM;

@Transactional
@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model,HttpServletRequest request,HttpSession ss) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null)
		{
			model.addAttribute("login","Guest");
		}
		else
		{
			DSTAIKHOAN a= (DSTAIKHOAN) session.getAttribute("user");
			model.addAttribute("tk",a);
		}
		model.addAttribute("KHACHHANG",new KHACHHANG());
		model.addAttribute("DSTAIKHOAN",new DSTAIKHOAN());
		return "register";
	}
	
	@RequestMapping(value = "/insert",method=RequestMethod.POST)
	public String insert(ModelMap model,@Validated @ModelAttribute("KHACHHANG") KHACHHANG khachHang,
			HttpServletRequest request,BindingResult errors,HttpSession ss,
			@ModelAttribute("DSTAIKHOAN") DSTAIKHOAN taiKhoan)
	{
		HttpSession session1 = request.getSession();
		
		String captcha = ss.getAttribute("captcha_security").toString();
		String verifyCaptcha = request.getParameter("captcha");
		boolean kt=true;
		if (khachHang.getHoTen().trim().toString().equals("")) {
			errors.rejectValue("hoTen", "KHACHHANG", "Username cannot be blank");
			kt=false;
		}
		if (khachHang.getDiaChi().trim().toString().equals("")) {
			errors.rejectValue("diaChi", "KHACHHANG", "Address cannot be blank");
			kt=false;
		}
		if (khachHang.getSdt().trim().toString().equals("")) {
			errors.rejectValue("sdt", "KHACHHANG", "Phone Number cannot be blank");
			kt=false;
		}
		if (khachHang.getTaiKhoan().getTaiKhoan().trim().toString().equals("")) {
			errors.rejectValue("taiKhoan.taiKhoan", "KHACHHANG", "Username cannot be blank");
			kt=false;
		}
		if (khachHang.getTaiKhoan().getMatKhau().trim().toString().equals("")) {
			errors.rejectValue("taiKhoan.matKhau", "KHACHHANG", "Password cannot be blank");
			kt=false;
		}
		if(captcha.equals(verifyCaptcha)==false)
		{
			model.addAttribute("errorCaptcha", "Please enter correct Captcha");
			kt=false;
		}
		
		if(kt==false)
		{
			if(session1.getAttribute("user")==null)
			{
				model.addAttribute("login","Login");
			}
			else
			{
				DSTAIKHOAN a= (DSTAIKHOAN) session1.getAttribute("user");
				model.addAttribute("tk",a);
				session1.removeAttribute("user");
			}
			return "register";
		}
		
		khachHang.setDoanhSo(0);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		taiKhoan.setTaiKhoan(khachHang.getTaiKhoan().getTaiKhoan());
		taiKhoan.setMatKhau(khachHang.getTaiKhoan().getMatKhau());
		taiKhoan.setChucVu("KHACH");
		
		try
		{
			// ADD POST API THÊM TAIKHOAN , KHACHHANG
			boolean kq= AccountAPI.saveTaiKhoan(taiKhoan);
			System.out.println("Kết quả: "+ kq);
			boolean kq2= CustomerAPI.saveKhachHang(khachHang);
			System.out.println("Kết quả: "+ kq2);
			t.commit();
			
			model.addAttribute("tk",taiKhoan);
			ss.setAttribute("user", taiKhoan);
			//model.addAttribute("message","Thành Công");
			
			if(session1.getAttribute("user")==null)
			{
				model.addAttribute("login","Login");
			}
			else
			{
				DSTAIKHOAN a= (DSTAIKHOAN) session1.getAttribute("user");
				model.addAttribute("tk",a);
				session1.removeAttribute("user");
				ss.setAttribute("user", a);
			}
			
			return "index";
		}
		catch(Exception e)
		{
			t.rollback();
			model.addAttribute("message",e.getMessage());
			
			
			if(session1.getAttribute("user")==null)
			{
				model.addAttribute("login","Login");
			}
			else
			{
				DSTAIKHOAN a= (DSTAIKHOAN) session1.getAttribute("user");
				model.addAttribute("tk",a);
				session1.removeAttribute("user");
			}
			
			return "register";
		}
		finally {
			session.close();
		}
		
	}
	
	
	
}
