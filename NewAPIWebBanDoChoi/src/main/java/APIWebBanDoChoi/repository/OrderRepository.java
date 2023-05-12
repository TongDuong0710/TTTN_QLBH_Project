package APIWebBanDoChoi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import APIWebBanDoChoi.NewEntity.DDHk;



public interface OrderRepository extends JpaRepository<DDHk, Integer> {
	@Query("SELECT dh FROM DDHk dh WHERE (trim(dh.trangThai)='NEW' or trim(dh.trangThai)='CANCELLED'or trim(dh.trangThai)='CONFIRMED') and dh.maKH.maKH=:maKH "
			+ "order by dh.MSDDH DESC") 
	List<DDHk> findHistoryOrder( @Param("maKH") int maKH);
}
