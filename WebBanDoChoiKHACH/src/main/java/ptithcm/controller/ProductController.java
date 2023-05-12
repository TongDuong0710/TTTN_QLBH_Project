package ptithcm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.hibernate.Session;

import ptithcm.HTTPRequest.ProductAPI;
import ptithcm.entity.DSTAIKHOAN;
import ptithcm.entity.SANPHAM;

@Transactional
@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("/search")
	public String searchProduct(ModelMap model, HttpServletRequest request) {
		HttpSession session2 = request.getSession();

		if (session2.getAttribute("user") == null) {
			model.addAttribute("login", "Guest");
		}
		String searchString = request.getParameter("searchProduct").trim();
		// ADD API
		List<SANPHAM> list = ProductAPI.findByTenSPContaining(searchString);
		PagedListHolder pagedListHolder = new PagedListHolder(list);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(12);

		model.addAttribute("pagedListHolder", pagedListHolder);

		HttpSession session = request.getSession();
		DSTAIKHOAN a = (DSTAIKHOAN) session.getAttribute("user");
		model.addAttribute("tk", a);

		model.addAttribute("sp", "All");
		model.addAttribute("sb", "0");

		return "product";
	}

	@RequestMapping("/single")
	public String singleProduct(ModelMap model, HttpServletRequest request) {
		HttpSession session2 = request.getSession();
		DSTAIKHOAN a = (DSTAIKHOAN) session2.getAttribute("user");
		model.addAttribute("tk", a);
		if (session2.getAttribute("user") == null) {
			model.addAttribute("login", "Guest");
		} else {
			model.addAttribute("tk", a);
		}
		String maSP = request.getParameter("singleProduct");
		// ADD API
		SANPHAM sp = ProductAPI.findOneByMaSP(maSP);
		model.addAttribute("SP", sp);

		return "single-details";
	}

	@RequestMapping("/index")
	public String index(ModelMap model, HttpServletRequest request) {

		HttpSession session2 = request.getSession();

		if (session2.getAttribute("user") == null) {
			model.addAttribute("login", "Guest");
		}
		System.out.println("SANPHAM:"+ ProductAPI.findAllSP().size());
		List<SANPHAM> list = ProductAPI.findAllSP();
		PagedListHolder pagedListHolder = new PagedListHolder(list);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(12);

		model.addAttribute("pagedListHolder", pagedListHolder);

		HttpSession session = request.getSession();
		DSTAIKHOAN a = (DSTAIKHOAN) session.getAttribute("user");
		model.addAttribute("tk", a);

		model.addAttribute("sp", "All");
		model.addAttribute("sb", "0");

		return "product";
	}

	@RequestMapping("/productList/select")
	public String index3(ModelMap model, HttpServletRequest request, HttpSession ss) {

		String dsCart = request.getParameter("listCart").trim();
		String[] words = dsCart.split("&");
		for (String a : words) {
			a = a.trim();
		}
		List<SANPHAM> listCart = new ArrayList<SANPHAM>();
		for (int i = 1; i < words.length; i++) {
			// ADD API
			SANPHAM tam = ProductAPI.findOneByMaSP(words[i]);
			listCart.add(tam);
		}

		HttpSession session1 = request.getSession();

		List<SANPHAM> listCartBefore = (List<SANPHAM>) session1.getAttribute("listCart");
		if (listCartBefore == null) {
			session1.setAttribute("listCart", listCart);
			model.addAttribute("listCart", listCart);
		} else {
			for (SANPHAM a : listCart) {
				boolean kt = false;
				for (SANPHAM b : listCartBefore) {
					if (a.getMaSP().equals(b.getMaSP())) {
						kt = true;
					}
				}
				if (kt == false)
					listCartBefore.add(a);
			}

			session1.setAttribute("listCart", listCartBefore);
			model.addAttribute("listCart", listCartBefore);
		}

		HttpSession session2 = request.getSession();

		if (session2.getAttribute("user") == null) {
			model.addAttribute("login", "Guest");
		}
		String loaiSP = request.getParameter("loaiSP");
		String sapXep = request.getParameter("sapXep");
		String hql = " ";
		int check = 0;
		int checkSS = 0;
		if (loaiSP.equals("All") == false) {
			hql = hql + "Where loaiSP='" + loaiSP + "'";
			check = 1;
		}
		if (sapXep.equals("1")) {
			hql = hql + "ORDER BY donGia ASC";
			checkSS = 1;
		} else if (sapXep.equals("2")) {
			hql = hql + "ORDER BY donGia DESC";
			checkSS = 2;
		}
		List<SANPHAM> list = ProductAPI.findAllSP();
		if (check == 1 && checkSS == 1) {
			list = ProductAPI.findByLoaiSPOrderByDonGiaAsc(loaiSP);
		}
		if (check == 1 && checkSS == 2) {
			list = ProductAPI.findByLoaiSPOrderByDonGiaDesc(loaiSP);
		}
		if (check == 1 && checkSS == 0) {
			list = ProductAPI.findByLoaiSP(loaiSP);
		}
		if (check == 0 && checkSS == 2) {
			list = ProductAPI.findAllByOrderByDonGiaDesc();
		}
		if (check == 0 && checkSS == 1) {
			list = ProductAPI.findAllByOrderByDonGiaAsc();
		}
		if (check == 0 && checkSS == 0) {
			list = ProductAPI.findAllSP();
		}
		PagedListHolder pagedListHolder = new PagedListHolder(list);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(12);

		model.addAttribute("pagedListHolder", pagedListHolder);

		HttpSession session = request.getSession();
		DSTAIKHOAN a = (DSTAIKHOAN) session.getAttribute("user");
		model.addAttribute("tk", a);
		model.addAttribute("sp", loaiSP);
		model.addAttribute("sb", sapXep);

		return "product-list";
	}

	@RequestMapping("/select")
	public String index4(ModelMap model, HttpServletRequest request, HttpSession ss) {
		String dsCart = request.getParameter("listCart").trim();
		String[] words = dsCart.split("&");
		for (String a : words) {
			a = a.trim();
		}
		List<SANPHAM> listCart = new ArrayList<SANPHAM>();

		for (int i = 1; i < words.length; i++) {
			// ADD API
			SANPHAM tam = ProductAPI.findOneByMaSP(words[i]);
			listCart.add(tam);
		}

		HttpSession session1 = request.getSession();

		session1.setAttribute("listCart", listCart);
		model.addAttribute("listCart", listCart);

		String DSCART = " ";
		for (SANPHAM a : listCart) {
			DSCART += " &" + a.getMaSP();
		}
		session1.setAttribute("DSCART", DSCART);

		System.out.println("DSCART: " + DSCART);
		HttpSession session2 = request.getSession();

		if (session2.getAttribute("user") == null) {
			model.addAttribute("login", "Guest");
		}
		String loaiSP = request.getParameter("loaiSP");
		String sapXep = request.getParameter("sapXep");
		String hql = " ";
		int check = 0;
		int checkSS = 0;
		if (loaiSP.equals("All") == false) {
			hql = hql + "Where loaiSP='" + loaiSP + "'";
			check = 1;
		}
		if (sapXep.equals("1")) {
			hql = hql + "ORDER BY donGia ASC";
			checkSS = 1;
		} else if (sapXep.equals("2")) {
			hql = hql + "ORDER BY donGia DESC";
			checkSS = 2;
		}
		List<SANPHAM> list = ProductAPI.findAllSP();
		if (check == 1 && checkSS == 1) {
			list = ProductAPI.findByLoaiSPOrderByDonGiaAsc(loaiSP);
		}
		if (check == 1 && checkSS == 2) {
			list = ProductAPI.findByLoaiSPOrderByDonGiaDesc(loaiSP);
		}
		if (check == 1 && checkSS == 0) {
			list = ProductAPI.findByLoaiSP(loaiSP);
		}
		if (check == 0 && checkSS == 2) {
			list = ProductAPI.findAllByOrderByDonGiaDesc();
		}
		if (check == 0 && checkSS == 1) {
			list = ProductAPI.findAllByOrderByDonGiaAsc();
		}
		if (check == 0 && checkSS == 0) {
			list = ProductAPI.findAllSP();
		}
		PagedListHolder pagedListHolder = new PagedListHolder(list);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(12);

		model.addAttribute("pagedListHolder", pagedListHolder);

		HttpSession session = request.getSession();
		DSTAIKHOAN a = (DSTAIKHOAN) session.getAttribute("user");
		model.addAttribute("tk", a);
		model.addAttribute("sp", loaiSP);
		model.addAttribute("sb", sapXep);

		return "product";
	}

	@RequestMapping("/productList")
	public String index2(ModelMap model, HttpServletRequest request, HttpSession ss) {
		HttpSession session1 = request.getSession();

		if (session1.getAttribute("user") == null) {
			model.addAttribute("login", "Guest");
		}
		List<SANPHAM> list = ProductAPI.findAllSP();

		PagedListHolder pagedListHolder = new PagedListHolder(list);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(8);

		model.addAttribute("pagedListHolder", pagedListHolder);

		HttpSession session = request.getSession();
		DSTAIKHOAN a = (DSTAIKHOAN) session.getAttribute("user");
		model.addAttribute("tk", a);
		return "product-list";
	}

	@RequestMapping("/productList/index")
	public String index13(ModelMap model, HttpServletRequest request, HttpSession ss) {
		HttpSession session1 = request.getSession();

		if (session1.getAttribute("user") == null) {
			model.addAttribute("login", "Guest");
		}
		List<SANPHAM> list = ProductAPI.findAllSP();

		PagedListHolder pagedListHolder = new PagedListHolder(list);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(8);

		model.addAttribute("pagedListHolder", pagedListHolder);

		HttpSession session = request.getSession();
		DSTAIKHOAN a = (DSTAIKHOAN) session.getAttribute("user");
		model.addAttribute("tk", a);
		return "product-list";
	}



	@ModelAttribute("loaiSP")
	public List<String> getLoaiSP() {
		return ProductAPI.findLoaiSP();
	}

	@ModelAttribute("DSCART")
	public String DSCART(HttpServletRequest request) {
		HttpSession session1 = request.getSession();
		String dsCart = (String) session1.getAttribute("DSCART");
		System.out.println("session DSCART: " + dsCart);
		return dsCart;
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

	@RequestMapping(params = "home")
	public String index14(ModelMap model, HttpServletRequest request, HttpSession ss) {
		System.out.println("TongHome");
		String dsCart = request.getParameter("listCart").trim();
		System.out.println("dsCart:" + dsCart);
		String[] words = dsCart.split("&");
		for (String a : words) {
			a = a.trim();
		}
		List<SANPHAM> listCart = new ArrayList<SANPHAM>();
		for (int i = 1; i < words.length; i++) {
			SANPHAM tam = ProductAPI.findOneByMaSP(words[i]);
			listCart.add(tam);
		}

		HttpSession session1 = request.getSession();

		session1.setAttribute("listCart", listCart);
		model.addAttribute("listCart", listCart);

		String DSCART = " ";
		for (SANPHAM a : listCart) {
			DSCART += " &" + a.getMaSP();
		}
		session1.setAttribute("DSCART", DSCART);

		System.out.println("DSCART: " + DSCART);

		return "redirect:/index.htm";
	}

	@RequestMapping(params = "login")
	public String index6(ModelMap model, HttpServletRequest request, HttpSession ss) {
		System.out.println("Tòng");
		String dsCart = request.getParameter("listCart").trim();
		System.out.println("dsCart:" + dsCart);
		String[] words = dsCart.split("&");
		for (String a : words) {
			a = a.trim();
		}
		List<SANPHAM> listCart = new ArrayList<SANPHAM>();
		for (int i = 1; i < words.length; i++) {
			SANPHAM tam = ProductAPI.findOneByMaSP(words[i]);
			listCart.add(tam);
		}

		HttpSession session1 = request.getSession();

		session1.setAttribute("listCart", listCart);
		model.addAttribute("listCart", listCart);

		String DSCART = " ";
		for (SANPHAM a : listCart) {
			DSCART += " &" + a.getMaSP();
		}
		session1.setAttribute("DSCART", DSCART);

		System.out.println("DSCART: " + DSCART);

		return "redirect:/login.htm";
	}

	@RequestMapping(params = "changeInfor")
	public String index7(ModelMap model, HttpServletRequest request, HttpSession ss) {
		System.out.println("Tòng");
		String dsCart = request.getParameter("listCart").trim();
		System.out.println("dsCart:" + dsCart);
		String[] words = dsCart.split("&");
		for (String a : words) {
			a = a.trim();
		}
		List<SANPHAM> listCart = new ArrayList<SANPHAM>();
		for (int i = 1; i < words.length; i++) {
			SANPHAM tam = ProductAPI.findOneByMaSP(words[i]);
			listCart.add(tam);
		}

		HttpSession session1 = request.getSession();

		session1.setAttribute("listCart", listCart);
		model.addAttribute("listCart", listCart);

		String DSCART = " ";
		for (SANPHAM a : listCart) {
			DSCART += " &" + a.getMaSP();
		}
		session1.setAttribute("DSCART", DSCART);

		System.out.println("DSCART: " + DSCART);

		return "redirect:/user/changeInfor.htm";
	}

	@RequestMapping(params = "changePass")
	public String index8(ModelMap model, HttpServletRequest request, HttpSession ss) {
		System.out.println("Tòng");
		String dsCart = request.getParameter("listCart").trim();
		System.out.println("dsCart:" + dsCart);
		String[] words = dsCart.split("&");
		for (String a : words) {
			a = a.trim();
		}
		List<SANPHAM> listCart = new ArrayList<SANPHAM>();
		for (int i = 1; i < words.length; i++) {
			SANPHAM tam = ProductAPI.findOneByMaSP(words[i]);
			listCart.add(tam);
		}

		HttpSession session1 = request.getSession();

		session1.setAttribute("listCart", listCart);
		model.addAttribute("listCart", listCart);

		String DSCART = " ";
		for (SANPHAM a : listCart) {
			DSCART += " &" + a.getMaSP();
		}
		session1.setAttribute("DSCART", DSCART);

		System.out.println("DSCART: " + DSCART);

		return "redirect:/user/changePassword.htm";
	}

	@RequestMapping(params = "orderHistory")
	public String index18(ModelMap model, HttpServletRequest request, HttpSession ss) {
		System.out.println("Tòng");
		String dsCart = request.getParameter("listCart").trim();
		System.out.println("dsCart:" + dsCart);
		String[] words = dsCart.split("&");
		for (String a : words) {
			a = a.trim();
		}
		List<SANPHAM> listCart = new ArrayList<SANPHAM>();
		for (int i = 1; i < words.length; i++) {
			SANPHAM tam = ProductAPI.findOneByMaSP(words[i]);
			listCart.add(tam);
		}

		HttpSession session1 = request.getSession();

		session1.setAttribute("listCart", listCart);
		model.addAttribute("listCart", listCart);

		String DSCART = " ";
		for (SANPHAM a : listCart) {
			DSCART += " &" + a.getMaSP();
		}
		session1.setAttribute("DSCART", DSCART);

		System.out.println("DSCART: " + DSCART);

		return "redirect:/cart/historyOrder.htm";
	}

	@RequestMapping(params = "logout")
	public String index9(ModelMap model, HttpServletRequest request, HttpSession ss) {
		System.out.println("Tòng");
		String dsCart = request.getParameter("listCart").trim();
		System.out.println("dsCart:" + dsCart);
		String[] words = dsCart.split("&");
		for (String a : words) {
			a = a.trim();
		}
		List<SANPHAM> listCart = new ArrayList<SANPHAM>();
		for (int i = 1; i < words.length; i++) {
			SANPHAM tam = ProductAPI.findOneByMaSP(words[i]);
			listCart.add(tam);
		}

		HttpSession session1 = request.getSession();

		session1.setAttribute("listCart", listCart);
		model.addAttribute("listCart", listCart);

		String DSCART = " ";
		for (SANPHAM a : listCart) {
			DSCART += " &" + a.getMaSP();
		}
		session1.setAttribute("DSCART", DSCART);

		System.out.println("DSCART: " + DSCART);

		return "redirect:/user/logout.htm";
	}

	@RequestMapping(params = "register")
	public String index10(ModelMap model, HttpServletRequest request, HttpSession ss) {
		System.out.println("Tòng");
		String dsCart = request.getParameter("listCart").trim();
		System.out.println("dsCart:" + dsCart);
		String[] words = dsCart.split("&");
		for (String a : words) {
			a = a.trim();
		}
		List<SANPHAM> listCart = new ArrayList<SANPHAM>();
		for (int i = 1; i < words.length; i++) {
			SANPHAM tam = ProductAPI.findOneByMaSP(words[i]);
			listCart.add(tam);
		}

		HttpSession session1 = request.getSession();

		session1.setAttribute("listCart", listCart);
		model.addAttribute("listCart", listCart);

		String DSCART = " ";
		for (SANPHAM a : listCart) {
			DSCART += " &" + a.getMaSP();
		}
		session1.setAttribute("DSCART", DSCART);

		System.out.println("DSCART: " + DSCART);

		return "redirect:/register.htm";
	}

	@RequestMapping(params = "product")
	public String index11(ModelMap model, HttpServletRequest request, HttpSession ss) {
		System.out.println("Tòng product");
		String dsCart = request.getParameter("listCart").trim();
		System.out.println("dsCart:" + dsCart);
		String[] words = dsCart.split("&");
		for (String a : words) {
			a = a.trim();
		}
		List<SANPHAM> listCart = new ArrayList<SANPHAM>();
		for (int i = 1; i < words.length; i++) {
			SANPHAM tam = ProductAPI.findOneByMaSP(words[i]);
			listCart.add(tam);
		}

		HttpSession session1 = request.getSession();

		session1.setAttribute("listCart", listCart);
		model.addAttribute("listCart", listCart);

		String DSCART = " ";
		for (SANPHAM a : listCart) {
			DSCART += " &" + a.getMaSP();
		}
		session1.setAttribute("DSCART", DSCART);

		System.out.println("DSCART: " + DSCART);

		return "redirect:/product/index.htm";
	}

	@RequestMapping(params = "productList")
	public String index15(ModelMap model, HttpServletRequest request, HttpSession ss) {
		System.out.println("Tòng");
		String dsCart = request.getParameter("listCart").trim();
		System.out.println("dsCart:" + dsCart);
		String[] words = dsCart.split("&");
		for (String a : words) {
			a = a.trim();
		}
		List<SANPHAM> listCart = new ArrayList<SANPHAM>();
		for (int i = 1; i < words.length; i++) {
			SANPHAM tam = ProductAPI.findOneByMaSP(words[i]);
			listCart.add(tam);
		}

		HttpSession session1 = request.getSession();

		session1.setAttribute("listCart", listCart);
		model.addAttribute("listCart", listCart);

		String DSCART = " ";
		for (SANPHAM a : listCart) {
			DSCART += " &" + a.getMaSP();
		}
		session1.setAttribute("DSCART", DSCART);

		System.out.println("DSCART: " + DSCART);

		return "redirect:/product/productList.htm";
	}

	@RequestMapping(params = "viewCart")
	public String index5(ModelMap model, HttpServletRequest request, HttpSession ss)

	{
		System.out.println("Tòng");
		String dsCart = request.getParameter("listCart").trim();
		System.out.println("dsCart:" + dsCart);
		String[] words = dsCart.split("&");
		for (String a : words) {
			a = a.trim();
		}
		List<SANPHAM> listCart = new ArrayList<SANPHAM>();
		for (int i = 1; i < words.length; i++) {
			SANPHAM tam = ProductAPI.findOneByMaSP(words[i]);
			listCart.add(tam);
		}

		HttpSession session1 = request.getSession();

		session1.setAttribute("listCart", listCart);
		model.addAttribute("listCart", listCart);

		String DSCART = " ";
		for (SANPHAM a : listCart) {
			DSCART += " &" + a.getMaSP();
		}
		session1.setAttribute("DSCART", DSCART);

		System.out.println("DSCART: " + DSCART);
		return "redirect:/cart/viewCart.htm";
	}

	@RequestMapping(params = "checkout")
	public String index12(ModelMap model, HttpServletRequest request, HttpSession ss) {
		System.out.println("Tòng");
		String dsCart = request.getParameter("listCart").trim();
		System.out.println("dsCart:" + dsCart);
		String[] words = dsCart.split("&");
		for (String a : words) {
			a = a.trim();
		}
		List<SANPHAM> listCart = new ArrayList<SANPHAM>();
		for (int i = 1; i < words.length; i++) {
			SANPHAM tam = ProductAPI.findOneByMaSP(words[i]);
			listCart.add(tam);
		}

		HttpSession session1 = request.getSession();

		session1.setAttribute("listCart", listCart);
		model.addAttribute("listCart", listCart);

		String DSCART = " ";
		for (SANPHAM a : listCart) {
			DSCART += " &" + a.getMaSP();
		}
		session1.setAttribute("DSCART", DSCART);

		System.out.println("DSCART: " + DSCART);

		return "redirect:/cart/checkout.htm";
	}

}
