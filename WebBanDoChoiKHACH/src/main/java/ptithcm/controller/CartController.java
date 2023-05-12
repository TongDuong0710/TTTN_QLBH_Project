package ptithcm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.HTTPRequest.CustomerAPI;
import ptithcm.HTTPRequest.OrderAPI;
import ptithcm.HTTPRequest.ProductAPI;
import ptithcm.entity.CTDDH;
import ptithcm.entity.DDH;
import ptithcm.entity.DSTAIKHOAN;
import ptithcm.entity.HOADON;
import ptithcm.entity.KHACHHANG;
import ptithcm.entity.SANPHAM;

@Transactional
@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	JavaMailSender mailer;
	@Autowired
	SessionFactory factory;
	@RequestMapping("/cancelOrder")
	public String index6(ModelMap model,HttpServletRequest request)
	{
		System.out.println("Ấn cancel");
		HttpSession session2 = request.getSession();
		if(session2.getAttribute("user")==null)
		{
			model.addAttribute("login","Guest");
			return "login";
		}
		else
		{
			DSTAIKHOAN a= (DSTAIKHOAN) session2.getAttribute("user");
			String MSDDH= request.getParameter("cancelOrder").trim();
			//ADD API
			DDH ddh = OrderAPI.getOrderByID(Integer.valueOf(MSDDH));
			ddh.setTrangThai("CANCELLED");
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try
			{
				//ADD API
				//session.save(ddh);
				OrderAPI.cancelOrder(ddh);
				
				
				model.addAttribute("tk",a);
			
			}
			catch(Exception e)
			{
				model.addAttribute("message",e.getMessage());
			}
			finally {
				session.close();
			}
		}
		
		return "redirect:/cart/historyOrder.htm";
	}
	@RequestMapping("/historyOrder")
	public String index5(ModelMap model,HttpServletRequest request) 
	{
		HttpSession session2 = request.getSession();
		if(session2.getAttribute("user")==null)
		{
			model.addAttribute("login","Guest");
			return "login";
		}
		else
		{
			DSTAIKHOAN a= (DSTAIKHOAN) session2.getAttribute("user");
			//ADD API 
			KHACHHANG user = CustomerAPI.findKHByTK(a.getTaiKhoan());
			List<DDH> list = OrderAPI.getHistoryOrder(user.getMaKH());
			
			for(DDH tam: list)
			{
				for(CTDDH tam1: tam.getCTDDH())
				{
					float tongGia=tam1.getSanPham().getDonGia()*tam1.getSoLuong();
					tam.setTongGia(tongGia);
				}
			}
			model.addAttribute("order",list);
			
//			String hql2 = "FROM HOADON  where DDH.maKH ="+user.getMaKH()+"";
//			//ADD API
//			Query query2 = session1.createQuery(hql2); 
//			List<HOADON> list2 = query2.list();
			
		}
		
		return "historyOrder";
	}
	@RequestMapping("/viewCart")
	public String index(ModelMap model,HttpServletRequest request) 
	{
		HttpSession session2 = request.getSession();
		
		if(session2.getAttribute("user")==null)
		{
			model.addAttribute("login","Guest");
		}
		else
		{
			DSTAIKHOAN a= (DSTAIKHOAN) session2.getAttribute("user");
			model.addAttribute("tk",a);
		}
		
		return "cart";
	}
	@RequestMapping("/confirmCart")
	public String index4 (ModelMap model,HttpServletRequest request,@Validated @ModelAttribute("DDH") DDH donDatHang,BindingResult errors)
	{
		System.out.println("vaof confirmcart");
		HttpSession session2 = request.getSession();
		DDH tam= (DDH)session2.getAttribute("DDH");
		tam.setHoTenKH(donDatHang.getHoTenKH());
		tam.setDiaChi(donDatHang.getDiaChi());
		tam.setSdt(donDatHang.getSdt());
		tam.setEmail(donDatHang.getEmail());
		
		boolean kt=true;
		
		if (donDatHang.getHoTenKH().trim().toString().equals("")) {
			errors.rejectValue("hoTenKH", "DDH", "FullName cannot be blank");
			model.addAttribute("hoTenKH","FullName cannot be blank");
			kt=false;
		}
		if (donDatHang.getDiaChi().trim().toString().equals("")) {
			errors.rejectValue("diaChi", "DDH", "Address cannot be blank");
			model.addAttribute("diaChi","Address cannot be blank");
			kt=false;
		}
		if (donDatHang.getSdt().trim().toString().equals("")) {
			errors.rejectValue("sdt", "DDH", "PhoneNumber cannot be blank");
			model.addAttribute("sdt","PhoneNumber cannot be blank");
			kt=false;
		}
		if (donDatHang.getEmail().trim().toString().equals("")) {
			errors.rejectValue("email", "DDH", "Email cannot be blank");
			model.addAttribute("email","Email cannot be blank");
			kt=false;
		}
		
		if(kt==false)
		{
			model.addAttribute("DDH", tam);
			return "checkout";
		}
		KHACHHANG user=null;
		if(session2.getAttribute("user")!=null)
		{
			System.out.println("VO HAM CO USER");
			DSTAIKHOAN a= (DSTAIKHOAN) session2.getAttribute("user");
			//ADD API
			System.out.println("TEST API: "+a.getTaiKhoan());
			user = CustomerAPI.findKHByTK(a.getTaiKhoan());
			tam.setMaKH(user);
			
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		tam.setTrangThai("NEW");
		try
		{
			System.out.println("Vaof try catch");
			//ADD API
			
			OrderAPI.insertOrder(tam);
			System.out.println("Sau insert");
//			for(CTDDH x: tam.getCTDDH())
//			{
//				//ADD API
//				session.save(x);
//				
//			}
			
			model.addAttribute("DDH", tam);
			model.addAttribute("message","Thành Công");
			
			MimeMessage mail=mailer.createMimeMessage();
			 //Sử Dụng lớp trợ giúp
			 MimeMessageHelper helper= new MimeMessageHelper(mail,true);
//			 if(user!=null)
//			 {
				 helper.setFrom("tong10tn3@gmail.com","tong10tn3@gmail.com");
				 helper.setTo(tam.getEmail());
				 helper.setReplyTo("tong10tn3@gmail.com","tong10tn3@gmail.com");
				 helper.setSubject("THÔNG TIN ĐƠN HÀNG");
				 String body="MSDDH: "+tam.getMSDDH()+"<br>Họ Tên: "+tam.getHoTenKH()+"<br>Địa chỉ: "+tam.getDiaChi()+"<br>SDT: "+ tam.getSdt()+"<br>Chi tiết đơn Hàng<br>";
				 body+="<h2>THÔNG TIN ĐƠN HÀNG</h2>";
				 body+="<h4>HỌ TÊN: "+tam.getHoTenKH()+"</h4>";
				 body+="<h4>ĐỊA CHỈ: "+tam.getDiaChi()+"</h4>";
				 body+="<h4>SỐ ĐIỆN THOẠI: "+tam.getSdt()+"</h4>";
				 body+="<h4>TỔNG GIÁ: $"+tam.getTongGia()+"</h4>";
				 body+="<h3>CHI TIẾT ĐƠN HÀNG</h3>";
				 body+="<table style='border: 1px solid black; border-collapse: collapse;'>\r\n"
				 		+ "  <tr  >\r\n"
				 		+ "    <th style=' border: 1px solid black' >Product Name</th>\r\n"
				 		+ "    <th  style=' border: 1px solid black' >Model</th>\r\n"
				 		+ "    <th style=' border: 1px solid black'  >Quantity</th>\r\n"
				 		+ "    <th style=' border: 1px solid black'  >Unit Price</th>\r\n"
				 		+ "    <th style=' border: 1px solid black'  >Total</th>\r\n"
				 		+ "  </tr>";
				 for(CTDDH x: tam.getCTDDH())
				 {
					 body+="<tr   >\r\n"
					 		+ "    <td style=' border: 1px solid black' >"+x.getSanPham().getTenSP()+"</td>\r\n"
					 		+ "    <td style=' border: 1px solid black' >"+x.getSanPham().getLoaiSP()+"</td>\r\n"
					 		+ "    <td style=' border: 1px solid black'  >"+x.getSoLuong()+"</td>\r\n"
					 		+ "    <td style=' border: 1px solid black'  >"+x.getSanPham().getDonGia()+"</td>\r\n"
					 		+ "    <td style=' border: 1px solid black' >"+(x.getSanPham().getDonGia()*x.getSoLuong())+"</td>\r\n"
					 		+ "  </tr>";
				 }
				 body+="</table>";
				 body+="<h3>VUI LÒNG ĐỢI NHÂN VIÊN XÁC NHẬN ĐƠN HÀNG!</h3>";
				 helper.setText(body,true);
				 
				 mailer.send(mail);
				 
			// }
			
			return "Success";
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			model.addAttribute("DDH", tam);
			model.addAttribute("message",e.getMessage());
			return "checkout";
		}
		finally {
			session.close();
		}
	}
	@RequestMapping("/checkout")
	public String index2(ModelMap model,HttpServletRequest request) 
	{
		System.out.println("checkout controller");
		DDH ddh=new DDH();
		List<CTDDH> chiTietDDH= new ArrayList<CTDDH>();
		HttpSession session2 = request.getSession();
		
		if(session2.getAttribute("user")==null)
		{
			model.addAttribute("login","Guest");
		}
		else
		{
			DSTAIKHOAN a= (DSTAIKHOAN) session2.getAttribute("user");
			model.addAttribute("tk",a);
		}
		String thongTin= request.getParameter("inputSP").trim();
		thongTin=thongTin.substring(1,thongTin.length());
		String[] words = thongTin.split("&");
		for(String tam:words)
		{
			
			CTDDH ctddh= new CTDDH();
			String []tt= tam.split("#"); 
			//ADD API
			SANPHAM sp= ProductAPI.findOneByMaSP(tt[0].trim());
			ctddh.setSanPham(sp);
			ctddh.setDonDatHang(ddh);
			ctddh.setSoLuong(Integer.parseInt(tt[1]));
			chiTietDDH.add(ctddh);
		}
		System.out.println("có user");
		if(session2.getAttribute("user")!=null)
		{
			System.out.println("có user");
			DSTAIKHOAN a= (DSTAIKHOAN) session2.getAttribute("user");
			System.out.println(a.getTaiKhoan());
//			Session session = factory.openSession();
//			String hql = "FROM KHACHHANG where taiKhoan.taiKhoan='"+a.getTaiKhoan()+"'"; 
//			Query query = session.createQuery(hql);

			//ADD API
			List<KHACHHANG> list = new ArrayList<KHACHHANG>();
			list.add(CustomerAPI.findKHByTK(a.getTaiKhoan()));	

			System.out.println("trước add");
			ddh.setHoTenKH(list.get(0).getHoTen());
			ddh.setDiaChi(list.get(0).getDiaChi());
			ddh.setSdt(list.get(0).getSdt());
			ddh.setEmail(list.get(0).getEmail());
			System.out.println("sau add");
		}
		
		
		
		ddh.setCTDDH(chiTietDDH);
		
		System.out.println(ddh.getHoTenKH());
		System.out.println(ddh.getDiaChi());
		System.out.println(ddh.getSdt());
		float tongGia=0;
		for(CTDDH a: ddh.getCTDDH())
		{
			tongGia+=a.getSoLuong()*a.getSanPham().getDonGia();
			System.out.println(a.getSanPham().getTenSP());
			System.out.println(a.getSanPham().getHinhAnh());
		}
		System.out.println("Tong gia "+ tongGia);
		ddh.setTongGia(tongGia);
		model.addAttribute("DDH",ddh);
		session2.setAttribute("DDH",ddh);
		//model.addAttribute("CTDDH",ddh.getCTDDH());
		
		
		System.out.println(ddh.getHoTenKH());
		return "checkout";
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
