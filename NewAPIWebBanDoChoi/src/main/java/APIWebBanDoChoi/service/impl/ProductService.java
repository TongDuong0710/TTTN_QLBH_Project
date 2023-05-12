package APIWebBanDoChoi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import APIWebBanDoChoi.NewEntity.SANPHAMk;
import APIWebBanDoChoi.repository.ProductRepository;


@Service
public class ProductService  {

	@Autowired
	private ProductRepository productRepository;
	public List<SANPHAMk> findAll()
	{
		List<SANPHAMk> list= productRepository.findAll();
		return list;
	}
	public List<SANPHAMk> findByLoaiSPOrderByDonGiaAsc(String loaiSP)
	{
		return productRepository.findByLoaiSPOrderByDonGiaAsc(loaiSP);
	}
	public List<SANPHAMk> findByLoaiSPOrderByDonGiaDesc(String loaiSP)
	{
		return productRepository.findByLoaiSPOrderByDonGiaDesc(loaiSP);
	}
	public List<SANPHAMk> findByLoaiSP(String loaiSP)
	{
		return productRepository.findByLoaiSP(loaiSP);
	}
	public List<SANPHAMk> findAllByOrderByDonGiaDesc()
	{
		return productRepository.findAllByOrderByDonGiaDesc();
	}
	public List<SANPHAMk> findAllByOrderByDonGiaAsc()
	{
		return productRepository.findAllByOrderByDonGiaAsc();
	}
	public List<SANPHAMk> findByTenSPContaining(String loaiSP)
	{
		return productRepository.findByTenSPContaining(loaiSP);
	}
	public SANPHAMk findOneByMaSP(String maSP)
	{
		return productRepository.findOneByMaSP(maSP);
	}
	public List<String> findLoaiSP()
	{
		return productRepository.findLoaiSP();
	}
	public SANPHAMk saveProduct(SANPHAMk product)
	{
		return productRepository.save(product);
	}
	public int  findMaxProductID()
	{
		return productRepository.findMaxProductID();
	}
	public List<SANPHAMk> findSanPhamDaXoa()
	{
		return productRepository.findSanPhamDaXoa();
	}
	
}
