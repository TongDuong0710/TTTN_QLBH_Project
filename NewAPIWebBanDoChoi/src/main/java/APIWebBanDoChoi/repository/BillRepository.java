package APIWebBanDoChoi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import APIWebBanDoChoi.NewEntity.HOADONk;



public interface BillRepository extends JpaRepository<HOADONk, String> {
	@Query("select max( CAST(soHoaDon AS int)) from HOADONk")
	int findMaxBillID();
	List<HOADONk> findByMaKH(int maKH);
	
}
