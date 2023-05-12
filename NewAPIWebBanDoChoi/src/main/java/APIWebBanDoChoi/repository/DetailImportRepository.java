package APIWebBanDoChoi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import APIWebBanDoChoi.NewEntity.CTNHAPHANG;
import APIWebBanDoChoi.NewEntity.NHAPHANG;

public interface DetailImportRepository extends JpaRepository<CTNHAPHANG, Integer> {

}
