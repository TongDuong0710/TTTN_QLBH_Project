package APIWebBanDoChoi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import APIWebBanDoChoi.NewEntity.SANPHAMk;



public interface ProductRepository extends JpaRepository<SANPHAMk, Integer> {
	List<SANPHAMk> findByLoaiSPOrderByDonGiaAsc(String loaiSP);
	List<SANPHAMk> findByLoaiSPOrderByDonGiaDesc(String loaiSP);
	List<SANPHAMk> findByLoaiSP(String loaiSP);
	List<SANPHAMk> findAllByOrderByDonGiaDesc();
	List<SANPHAMk> findAllByOrderByDonGiaAsc();
	List<SANPHAMk> findByTenSPContaining(String tenSP);
	SANPHAMk findOneByMaSP(String maSP);
	
	@Query("SELECT DISTINCT loaiSP From SANPHAMk") 
	List<String> findLoaiSP();
	
	@Query("select max( CAST(maSP AS int)) from SANPHAMk")
	int findMaxProductID();
	
	@Query("FROM SANPHAMk A WHERE A.trangthaixoa='0'order by length(A.maSP), maSP")
	List<SANPHAMk> findSanPhamDaXoa();
}
