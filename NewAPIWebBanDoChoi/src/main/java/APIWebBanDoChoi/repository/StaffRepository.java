package APIWebBanDoChoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import APIWebBanDoChoi.NewEntity.NHANVIEN;


public interface StaffRepository extends JpaRepository<NHANVIEN, String> {
	@Query("SELECT MAX(A.manv) FROM NHANVIEN A") 
	Integer findMaxStaffID();
}
