package hackovid2020.back.service;

import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.File;
import hackovid2020.back.dao.Shop;
import hackovid2020.back.dao.Location;
import hackovid2020.back.repository.FileRepository;
import hackovid2020.back.repository.LocationRepository;
import hackovid2020.back.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ShopService {
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private LocationRepository shopLocationRepository;
	
	@Autowired
	private FileRepository fileRepository;
	
	public Shop saveShop(Shop shop) {
		return shopRepository.save(shop);
	}
	
	public List<File> findAllShopImages(Shop shop) {
		return fileRepository.findByShopShopId(shop.getShopId());
	}
	
	public List<Category> findAllShopCategories(Shop shop) {
		return shopRepository.findAllShopCategories(shop.getShopId());
	}

	public Shop findById(Long id) {
		return shopRepository.getOne(id);
	}
	
	public Location saveShopLocation(float latitude, float longitude, String streetName) {
		Date date = Calendar.getInstance().getTime();
		Location location = Location.createLocation(latitude, longitude, streetName, date, date);
		return shopLocationRepository.save(location);
	}

	public List<Shop> findAllShops() {
		return shopRepository.findAll();
	}

	public Shop updateShop(Long id, Long coverImageId, float latitude, float longitude, String streetName) {
		Shop shop = shopRepository.getOne(id);
		File coverImage = fileRepository.getOne(coverImageId);
		Location location = shopLocationRepository.getOne(shop.getLocation().getLocationId());
		
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
