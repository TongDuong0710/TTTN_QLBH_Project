package APIWebBanDoChoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import APIWebBanDoChoi.NewEntity.KHACHHANGk;


public interface CustomerRepository  extends JpaRepository<KHACHHANGk, Integer> {
	@Query("SELECT  kh FROM KHACHHANGk kh where kh.taiKhoan.taiKhoan=:tk") 
	KHACHHANGk findKHByTK( @Param("tk") String tk);
}
