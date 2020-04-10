package hackovid2020.back.dto.shop;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hackovid2020.back.dao.Shop;
import hackovid2020.back.dto.user.UserDetailsResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ShopDetailsResponseList {
	
	@JsonProperty
	private List<ShopDetailsResponse> shops;
	
	@JsonCreator
	private ShopDetailsResponseList(List<ShopDetailsResponse> shops) {
		this.shops = shops;
	}
	
	public static ShopDetailsResponseList ofShopsList(List<Shop> shopList) {
		List<ShopDetailsResponse> shops = shopList.stream().map(x -> ShopDetailsResponse.ofShop(
				x,
				UserDetailsResponse.ofUser(x.getUser()),
				x.getShopImages(),
				x.getCategories(),
				x.getLocation()))
				.collect(Collectors.toList());
		return new ShopDetailsResponseList(shops);
	}

}
