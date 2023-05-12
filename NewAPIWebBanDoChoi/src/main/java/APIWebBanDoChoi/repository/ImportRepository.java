package APIWebBanDoChoi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import APIWebBanDoChoi.NewEntity.NHAPHANG;



public interface ImportRepository extends JpaRepository<NHAPHANG, Integer> {
}
