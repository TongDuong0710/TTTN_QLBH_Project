package ptithcm.controller;

import java.io.File;
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
@RequestMapping("/user/")
public class UserController {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;
	@RequestMapping("logout")
	public String logout(ModelMap model,HttpServletRequest request,HttpSession ss)
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("user")!=null)
		{
			session.removeAttribute("user");
			model.addAttribute("login","Login");
			return "login";
			
		}
		model.addAttribute("tk",null);
		return "login";
	}
	
	@RequestMapping("changeInfor")
	public String index(ModelMap model,HttpServletRequest request,HttpSession ss) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null)
		{
			model.addAttribute("login","Login");
			return "login";
		}
		else
		{
			DSTAIKHOAN a= (DSTAIKHOAN) session.getAttribute("user");
			model.addAttribute("tk",a);
			KHACHHANG user = CustomerAPI.findKHByTK(a.getTaiKhoan());
			model.addAttribute("KHACHHANG",user);
		}
		return "changeInfor";
	}
	@RequestMapping(value ="updateInfor",method=RequestMethod.POST)
	public String update(ModelMap model,HttpSession ss,HttpServletRequest request,
			@ModelAttribute("KHACHHANG") KHACHHANG khachHang) {
		
		HttpSession session1 = request.getSession();
		khachHang.setTaiKhoan((DSTAIKHOAN) session1.getAttribute("user"));
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try
		{
			//ADD API 
			CustomerAPI.updateCus(khachHang);
			t.commit();
			model.addAttribute("message","Cập Nhật Thành Công !");
		}
		catch(Exception e)
		{
			t.rollback();
			model.addAttribute("message","Cập Nhật Thất Bại !");
		}
		finally {
			session.close();
		}
		return "changeInfor";
	}
	
	@RequestMapping("changePassword")
	public String index1(ModelMap model,HttpServletRequest request,HttpSession ss) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null)
		{
			return "login";
		}
		else
		{
			DSTAIKHOAN a= (DSTAIKHOAN) session.getAttribute("user");
			model.addAttribute("tk",a);
			return "changePassword";
		}
	}
	
	@RequestMapping(value = "updatePassword",method=RequestMethod.POST)
	public String updatePass(ModelMap model,HttpSession ss,HttpServletRequest request) {
		String oldPass = request.getParameter("oldPass"); 
		String newPass = request.getParameter("newPass");
		String cfnewPass = request.getParameter("confirmNewPass");
	
		HttpSession session1 = request.getSession();
		DSTAIKHOAN a= (DSTAIKHOAN) session1.getAttribute("user");
		
		if(oldPass .equals(a.getMatKhau()))
		{
			if(newPass.equals(cfnewPass))
			{
				a.setMatKhau(newPass);
			}
			else
			{
				model.addAttribute("message", "Mật Khẩu mới không khớp !");
				return "changePassword";
			}
		}
		else
		{
			model.addAttribute("message", "Sai Mật Khẩu cũ !");
			return "changePassword";
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try
		{
			//ADD API
			a.setChucVu("KHACH");
			AccountAPI.updateMK(a);
			t.commit();
			model.addAttribute("message","Cập Nhật Thành Công !");
		}
		catch(Exception e)
		{
			t.rollback();
			model.addAttribute("message","Cập Nhật Thất Bại !");
		}
		finally {
			session.close();
		}
		return "changePassword";
	}
	@ModelAttribute("login")
	public void fillLogin(ModelMap model,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null)
		{
			model.addAttribute("login","Login");
		}
		else
		{
			DSTAIKHOAN a= (DSTAIKHOAN) session.getAttribute("user");
			model.addAttribute("tk",a);
		}
		
	}
}
