package ptithcm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.entity.DSTAIKHOAN;
import ptithcm.entity.SANPHAM;

@Controller
@RequestMapping("/index")
public class HomeController {
	@Autowired
	SessionFactory factory;

	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model, HttpServletRequest request, HttpSession ss) {

		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			model.addAttribute("login", "Login");
		} else {
			DSTAIKHOAN a = (DSTAIKHOAN) session.getAttribute("user");
			model.addAttribute("tk", a);
		}
		return "index";
	}
	@RequestMapping("/index")
	public String index1(ModelMap model, HttpServletRequest request, HttpSession ss) {

		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			model.addAttribute("login", "Login");
		} else {
			DSTAIKHOAN a = (DSTAIKHOAN) session.getAttribute("user");
			model.addAttribute("tk", a);
		}
		session.removeAttribute("DSCART");
		return "index";
	}

	@ModelAttribute("listCart")
	public List<SANPHAM> getListCart(HttpServletRequest request) {

		HttpSession session1 = request.getSession();

		List<SANPHAM> list = (List<SANPHAM>) session1.getAttribute("listCart");
		if (list != null) {
			for (SANPHAM a : list) {
				System.out.println(a.getMaSP());
			}
		}

		return list;
	}

	

}
