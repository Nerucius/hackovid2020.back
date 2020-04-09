package hackovid2020.back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hackovid2020.back.dao.File;
import hackovid2020.back.repository.FileRepository;

public class FileService {
	
	@Autowired
	private FileRepository fileRepository;
	
	public List<File> findAllShopImages(List<Long> shopImageIds) {
		return fileRepository.findAllById(shopImageIds);
	}
	
	public File findFileById(Long id) {
		return fileRepository.getOne(id);
	}

	public List<File> findAllShopImages(Long shopId) {
		return fileRepository.findByShopShopId(shopId);
	}

}
