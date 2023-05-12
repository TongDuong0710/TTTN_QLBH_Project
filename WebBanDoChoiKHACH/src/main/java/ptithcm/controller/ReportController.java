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
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.HTTPRequest.CustomerAPI;
import ptithcm.HTTPRequest.OrderAPI;
import ptithcm.HTTPRequest.ProductAPI;
import ptithcm.HTTPRequest.ReportAPI;
import ptithcm.bean.reportCharts;
import ptithcm.entity.CTDDH;
import ptithcm.entity.DDH;
import ptithcm.entity.DSTAIKHOAN;
import ptithcm.entity.HOADON;
import ptithcm.entity.KHACHHANG;
import ptithcm.entity.SANPHAM;

@Transactional
@Controller
@RequestMapping("/report")
public class ReportController {
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		DSTAIKHOAN a = (DSTAIKHOAN) session.getAttribute("user");
		model.addAttribute("tk", a);
		KHACHHANG kh = CustomerAPI.findKHByTK(a.getTaiKhoan().trim());
		model.put("ID", kh.getMaKH());
		return "Report";
	}
}
