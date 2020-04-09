package hackovid2020.back.dto.shop;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.Shop;
import hackovid2020.back.dao.ShopImage;
import hackovid2020.back.dao.ShopLocation;
import hackovid2020.back.dto.user.UserDetailsResponse;

public class ShopDetailsResponse {
	
	@JsonProperty
	private Long shopId;
	
	@JsonProperty
	private String coverUrl;
	
	@JsonProperty
	private UserDetailsResponse userDetailsResponse;
	
	@JsonProperty
	private ShopLocationResponse shopLocationResponse;
	
	@JsonProperty
	private Date createdAt;
	
	@JsonProperty
	private Date modifiedAt;
	
	@JsonProperty
	private List<ShopImageResponse> shopImages;
	
	@JsonProperty
	private List<ShopCategoryResponse> shopCategories;
	
	@JsonCreator
	private ShopDetailsResponse(Long shopId, String coverUrl, UserDetailsResponse userDetailsResponse, List<ShopImageResponse> shopImages,
			List<ShopCategoryResponse> shopCategories, ShopLocationResponse shopLocationResponse, Date createdAt, Date modifiedAt) {
		this.shopId = shopId;
		this.coverUrl = coverUrl;
		this.userDetailsResponse = userDetailsResponse;
		this.shopLocationResponse = shopLocationResponse;
		this.shopImages = shopImages;
		this.shopCategories = shopCategories;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	public static ShopDetailsResponse ofShop(Shop shop, UserDetailsResponse userDetailsResponse, 
			List<ShopImage> shopImages, List<Category> shopCategories, ShopLocation shopLocation) {
		List<ShopImageResponse> shopImageResponseList = shopImages.stream()
				.map(ShopImageResponse::ofShopImage).collect(Collectors.toList());
		List<ShopCategoryResponse> shopCategoriesResponseList = shopCategories.stream()
				.map(ShopCategoryResponse::ofCategory).collect(Collectors.toList());
		return new ShopDetailsResponse(shop.getShopId(), shop.getCoverId(), userDetailsResponse, 
				shopImageResponseList, shopCategoriesResponseList, ShopLocationResponse.ofShopLocation(shopLocation),
				shop.getCreatedAt(), shop.getModifiedAt());
	}

}
