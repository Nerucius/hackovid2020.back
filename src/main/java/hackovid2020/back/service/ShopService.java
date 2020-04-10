package hackovid2020.back.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.File;
import hackovid2020.back.dao.Shop;
import hackovid2020.back.dao.ShopLocation;
import hackovid2020.back.repository.ShopLocationRepository;
import hackovid2020.back.repository.CategoryRepository;
import hackovid2020.back.repository.FileRepository;
import hackovid2020.back.repository.ShopRepository;

@Service
public class ShopService {
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ShopLocationRepository shopLocationRepository;
	
	@Autowired
	private FileRepository fileRepository;
	
	public Shop saveShop(Shop shop) {
		return shopRepository.save(shop);
	}
	
	public List<File> findAllShopImages(Shop shop) {
		return fileRepository.findByShopShopId(shop.getShopId());
	}
	
	public List<Category> findAllShopCategories(Shop shop) {
		return categoryRepository.findByShopShopId(shop.getShopId());
	}

	public Shop findById(Long id) {
		return shopRepository.getOne(id);
	}
	
	public ShopLocation saveShopLocation(float latitude, float longitude, String streetName) {
		Date date = Calendar.getInstance().getTime();
		ShopLocation location = ShopLocation.createShopLocation(latitude, longitude, streetName, date, date);
		return shopLocationRepository.save(location);
	}
	
	public ShopLocation findShopLocation(Shop shop) {
		return shopLocationRepository.findByShopShopId(shop.getShopId());
	}

	public List<Shop> findAllShops() {
		return shopRepository.findAll();
	}

	public Shop updateShop(Long id, Long coverImageId, float latitude, float longitude, String streetName) {
		Shop shop = shopRepository.getOne(id);
		File coverImage = fileRepository.getOne(coverImageId);
		ShopLocation location = shopLocationRepository.findByShopShopId(id);
		
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		location.setStreetName(streetName);
		location.setModifiedAt(Calendar.getInstance().getTime());
		shopLocationRepository.save(location);
		shop.setLocation(location);
		
		shop.setCoverImage(coverImage);
		shop.setModifiedAt(Calendar.getInstance().getTime());
		return shopRepository.save(shop);
	}

	public void deleteShop(Long id) {
		shopRepository.deleteById(id);
	}

}
