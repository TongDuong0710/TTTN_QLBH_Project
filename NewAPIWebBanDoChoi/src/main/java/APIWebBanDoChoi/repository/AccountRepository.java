package APIWebBanDoChoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import APIWebBanDoChoi.NewEntity.DSTAIKHOANk;


public interface AccountRepository extends JpaRepository<DSTAIKHOANk, Integer> {
	DSTAIKHOANk findOneByTaiKhoan(String code);
}
