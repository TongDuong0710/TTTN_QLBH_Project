package ptithcm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ptithcm.HTTPRequest.ProductAPI;
import ptithcm.entity.SANPHAM;
@Transactional
@Controller
@RequestMapping("/page")
public class PageController {
	@Autowired
	SessionFactory factory;
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView  index(ModelMap model,HttpServletRequest request,HttpSession ss) 
	{
		String page = request.getParameter("page").trim();
		System.out.println(page);
		
		String dsCart = request.getParameter("listCart").trim();
		String[] words = dsCart.split("&");
		for(String a : words)
		{
			a=a.trim();
		}
		List<SANPHAM> listCart= new ArrayList<SANPHAM>();
		for(int i=1;i<words.length;i++)
		{

			//ADD API
			SANPHAM tam= ProductAPI.findOneByMaSP(words[i]) ;
			listCart.add(tam);
		}
		HttpSession session1 = request.getSession();
//		List<SANPHAMk> listCartBefore= (List<SANPHAMk>) session1.getAttribute("listCart");	
//		if(listCartBefore==null)
//		{
//			session1.setAttribute("listCart", listCart);
//			model.addAttribute("listCart",listCart);
//		}
//		else
//		{
//			for(SANPHAMk a: listCart)
//			{
//				listCartBefore.add(a);
//			}
//			
//			session1.setAttribute("listCart", listCartBefore);
//			model.addAttribute("listCart",listCartBefore);
//		}
		
		session1.setAttribute("listCart", listCart);
		model.addAttribute("listCart", listCart);
		
		
		String DSCART = " ";
		for(SANPHAM a: listCart)
		{
			DSCART+=" &"+a.getMaSP();
		}
		session1.setAttribute("DSCART",DSCART);
		
		System.out.println("DSCART: "+DSCART);
		
		return new ModelAndView("redirect:" + page);
	}
	
}
