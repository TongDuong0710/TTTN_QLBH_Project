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

import APIWebBanDoChoi.API.output.ImportOutput;
import APIWebBanDoChoi.NewEntity.NHAPHANG;
import APIWebBanDoChoi.NewEntity.SANPHAMk;
import APIWebBanDoChoi.service.impl.ImportService;
import APIWebBanDoChoi.service.impl.ProductService;


@RestController
public class ImportAPI {
	@Autowired
	private ImportService importService;
	
	@GetMapping(value="/findAllImport")
	 public List<ImportOutput> findAll()
	 {
		 return ImportOutput.convertToListImportOutput(importService.findAll());
	 }
	 @PostMapping(value="/saveImport")
	 public boolean saveImport(@RequestBody ImportOutput nh)
	 {
		 return importService.save(nh);
	 }
	 
}
