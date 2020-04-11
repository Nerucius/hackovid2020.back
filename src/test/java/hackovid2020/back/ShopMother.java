package hackovid2020.back;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.File;
import hackovid2020.back.dao.Location;
import hackovid2020.back.dao.Shop;
import hackovid2020.back.dao.User;

public class ShopMother {
	
	public static Shop createCandyShop(User user, File coverImage, List<File> randomImages,
			List<Category> randomCategories, Location location) {
		return Shop.createShop(coverImage, user,
				randomImages.stream().collect(Collectors.toSet()),
				randomCategories.stream().collect(Collectors.toSet()),
				location,
				Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
	}
	
}
