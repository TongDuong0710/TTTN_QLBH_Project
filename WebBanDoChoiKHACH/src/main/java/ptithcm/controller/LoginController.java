package ptithcm.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.constraints.Null;

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

import ptithcm.HTTPRequest.AccountAPI;
import ptithcm.entity.DSTAIKHOAN;
import ptithcm.entity.KHACHHANG;
import ptithcm.entity.SANPHAM;


@Transactional
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	SessionFactory factory;
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model,HttpServletRequest request,HttpSession ss) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null)
		{
			model.addAttribute("login","Login");
		}
		else
		{
			session.removeAttribute("user");
			model.addAttribute("login","Login");
		}
		return "login";
	}
	
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public String insert(ModelMap model,HttpServletRequest request,HttpSession ss)
	{
		HttpSession session1 = request.getSession();
		session1.removeAttribute("user");
		
		String captcha = ss.getAttribute("captcha_security").toString();
		String verifyCaptcha = request.getParameter("captcha");
		
		String username = request.getParameter("taiKhoan"); 
		String password = request.getParameter("matKhau");
		username.trim();
		password.trim();
		boolean kt=true;
		if(username.equals("")==true)
		{
			model.addAttribute("errorTK", "Username is required !");
			kt=false;
		}
		if(password.equals("")==true)
		{
			model.addAttribute("errorMK", "Password is required !");
			kt=false;
		}
		if(captcha.equals(verifyCaptcha)==false)
		{
			model.addAttribute("errorCaptcha", "Please enter correct Captcha");
			kt=false;
		}
		if(kt==false)
		{
			model.addAttribute("login","Login");
			return "login";
		}
		
		//  API GET DSTAIKHOAN
		
		List<DSTAIKHOAN> list= new ArrayList<DSTAIKHOAN>();
		DSTAIKHOAN tkLoaded=AccountAPI.getTaiKhoan(username);
		if(tkLoaded!=null) list.add(tkLoaded);
		for(DSTAIKHOAN u : list) {
			if(username.equals(u.getTaiKhoan().trim())==true) {
				if(password.equals(u.getMatKhau().trim()) == false) { 
					model.addAttribute("messageA", "Sai mật khẩu !");
					model.addAttribute("login","Login");
					return "login";
				}
				else
				{
					if(u.getChucVu().trim().equals("KHACH"))
					{
						DSTAIKHOAN a= new DSTAIKHOAN();
						a.setTaiKhoan(username);
						a.setMatKhau(password);
						
						model.addAttribute("tk",a);
						ss.setAttribute("user", a);
						return "index";
					}
					else
					{
						model.addAttribute("messageA", "Vui lòng đăng nhập lại");
						model.addAttribute("login","Login");
						return "login";
					}
					
					
				}
			}
		}
		model.addAttribute("messageA", "Tài Khoản không Tồn Tại !");
		model.addAttribute("login","Login");
		return "login";
	}

	
	public List<SANPHAM> getProducts(String dieuKien) {
		Session session = factory.getCurrentSession();
		String hql = "FROM SANPHAM " + dieuKien;
		Query query = session.createQuery(hql);
		List<SANPHAM> list = query.list();
		return list;
	}
	@ModelAttribute("listCart")
	public  List<SANPHAM> getListCart(HttpServletRequest request)
	{
		HttpSession session1 = request.getSession();
		List<SANPHAM> list= (List<SANPHAM>) session1.getAttribute("listCart");
		if(list !=null)
		{
			for(SANPHAM a : list)
			{
				System.out.println(a.getMaSP());
			}
		}
		return list;
	}
}
