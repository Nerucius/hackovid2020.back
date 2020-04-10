package hackovid2020.back.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hackovid2020.back.dao.File;
import hackovid2020.back.dao.Shop;
import hackovid2020.back.dao.support.FileType;
import hackovid2020.back.repository.FileRepository;

@Service
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

	public File saveFile(File file) {
		return fileRepository.save(file);
	}

	public File updateFile(Long id, String name, FileType fileType, String url) {
		File file = fileRepository.getOne(id);
		file.setName(name);
		file.setFileType(fileType);
		file.setUrl(url);
		file.setModifiedAt(Calendar.getInstance().getTime());
		return fileRepository.save(file);
	}

	public void deleteFile(Long id) {
		fileRepository.deleteById(id);
	}
	
	public void assignShopToFiles(List<Long> ids, Shop shop) {
		List<File> files = fileRepository.findAllById(ids);
		files.forEach(x -> x.setShop(shop));
		fileRepository.saveAll(files);
	}

}
