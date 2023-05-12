package APIWebBanDoChoi.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import APIWebBanDoChoi.NewEntity.SANPHAMk;
import APIWebBanDoChoi.service.impl.ProductService;


@RestController
public class ProductAPI {
	@Autowired
	private ProductService productService;
	
	@GetMapping(value="/findAllSP")
	 public List<SANPHAMk> findAllSP()
	 {
		 return productService.findAll();
	 }
	 @GetMapping(value="/findByLoaiSPOrderByDonGiaAsc")
	 public List<SANPHAMk> findByLoaiSPOrderByDonGiaAsc(@RequestParam("loaiSP") String loaiSP)
	 {
		 return productService.findByLoaiSPOrderByDonGiaAsc(loaiSP);
	 }
	 @GetMapping(value="/findByLoaiSPOrderByDonGiaDesc")
	 public List<SANPHAMk> findByLoaiSPOrderByDonGiaDesc(@RequestParam("loaiSP") String loaiSP)
	 {
		 return productService.findByLoaiSPOrderByDonGiaDesc(loaiSP);
	 }
	 @GetMapping(value="/findByLoaiSP")
	 public List<SANPHAMk> findByLoaiSP(@RequestParam("loaiSP") String loaiSP)
	 {
		 return productService.findByLoaiSP(loaiSP);
	 }
	 @GetMapping(value="/findAllByOrderByDonGiaDesc")
	 public List<SANPHAMk> findAllByOrderByDonGiaDesc()
	 {
		 return productService.findAllByOrderByDonGiaDesc();
	 }
	 @GetMapping(value="/findAllByOrderByDonGiaAsc")
	 public List<SANPHAMk> findAllByOrderByDonGiaAsc( )
	 {
		 return productService.findAllByOrderByDonGiaAsc();
	 }
	 @GetMapping(value="/findByTenSPContaining")
	 public List<SANPHAMk> findByTenSPContaining(@RequestParam("tenSP") String tenSP)
	 {
		 return productService.findByTenSPContaining(tenSP);
	 }
	 @GetMapping(value="/findOneByMaSP")
	 public SANPHAMk findOneByMaSP(@RequestParam("maSP") String maSP)
	 {
		 return productService.findOneByMaSP(maSP);
	 }
	 @GetMapping(value="/findLoaiSP")
	 public List<String> findLoaiSP()
	 {
		 return productService.findLoaiSP();
	 }
	 @PostMapping(value="/deleteProduct")
	 public SANPHAMk deleteProduct(@RequestBody SANPHAMk product)
	 {
		 return productService.saveProduct(product);
	 }
	 @GetMapping(value="/findMaxProductId")
	 public int findMaxProductId()
	 {
		 return productService.findMaxProductID();
	 }
	 @GetMapping(value="/findSanPhamDaXoa")
	 public List<SANPHAMk> findSanPhamDaXoa()
	 {
		 return productService.findSanPhamDaXoa();
	 }
}
