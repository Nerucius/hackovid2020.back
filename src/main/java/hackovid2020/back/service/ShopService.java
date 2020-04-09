package hackovid2020.back.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hackovid2020.back.Constants;
import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.Shop;
import hackovid2020.back.dao.ShopImage;
import hackovid2020.back.dao.ShopLocation;
import hackovid2020.back.dao.support.CategoriesEnum;
import hackovid2020.back.repository.ShopImageRepository;
import hackovid2020.back.repository.ShopLocationRepository;
import hackovid2020.back.repository.CategoryRepository;
import hackovid2020.back.repository.ShopRepository;
import hackovid2020.back.utils.MD5Util;

@Service
public class ShopService {
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ShopImageRepository shopImageRepository;
	
	@Autowired
	private ShopLocationRepository shopLocationRepository;
	
	public Shop saveShop(Shop shop) {
		return shopRepository.save(shop);
	}
	
	public List<Category> saveShopCategories(List<String> shopCategoriesString, Shop shop) {
		List<Category> shopCategories = shopCategoriesString.stream().map(
				x -> Category.createCategory(shop, CategoriesEnum.valueOf(x),
						shop.getCreatedAt(), shop.getModifiedAt())).collect(Collectors.toList());
		return categoryRepository.saveAll(shopCategories);
	}
	
	public List<ShopImage> saveShopImages(List<String> shopImagesString, Shop shop) {
		List<ShopImage> shopImages = shopImagesString.stream()
				.map(x -> ShopImage.createCatalogImage(shop, Constants.GRAVATAR + MD5Util.md5Hex(x),
						shop.getCreatedAt(), shop.getModifiedAt())).collect(Collectors.toList());
		return shopImageRepository.saveAll(shopImages);
	}
	
	public List<ShopImage> findAllShopImages(Shop shop) {
		return shopImageRepository.findByShopShopId(shop.getShopId());
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

}
