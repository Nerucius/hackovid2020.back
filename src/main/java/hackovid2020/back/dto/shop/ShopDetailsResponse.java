package hackovid2020.back.dto.shop;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import hackovid2020.back.dao.Category;
import hackovid2020.back.dao.File;
import hackovid2020.back.dao.Shop;
import hackovid2020.back.dao.Location;
import hackovid2020.back.dto.file.FileResponse;
import hackovid2020.back.dto.product.ProductResponseList;
import hackovid2020.back.dto.user.UserDetailsResponse;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class ShopDetailsResponse {
	
	@JsonProperty
	private Long shopId;
	
	@JsonProperty
	private FileResponse coverImage;
	
	@JsonProperty
	private UserDetailsResponse userDetailsResponse;
	
	@JsonProperty
	private ShopLocationResponse shopLocationResponse;
	
	@JsonProperty
	private Date createdAt;
	
	@JsonProperty
	private Date modifiedAt;
	
	@JsonProperty
	private Set<FileResponse> shopImages;
	
	@JsonProperty
	private Set<ShopCategoryResponse> shopCategories;
	
	@JsonProperty
	private ProductResponseList productList;
	
	@JsonCreator
	private ShopDetailsResponse(Long shopId, FileResponse coverImage, UserDetailsResponse userDetailsResponse,
			Set<FileResponse> shopImages, Set<ShopCategoryResponse> shopCategories, ShopLocationResponse shopLocationResponse,
			ProductResponseList productList, Date createdAt, Date modifiedAt) {
		this.shopId = shopId;
		this.coverImage = coverImage;
		this.userDetailsResponse = userDetailsResponse;
		this.shopLocationResponse = shopLocationResponse;
		this.shopImages = shopImages;
		this.shopCategories = shopCategories;
		this.productList = productList;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	public static ShopDetailsResponse ofShop(Shop shop, UserDetailsResponse userDetailsResponse,
                                             Set<File> shopImages, Set<Category> shopCategories, Location shopLocation,
                                             ProductResponseList prl) {
		Set<FileResponse> shopImageResponseList = shopImages.stream()
				.map(FileResponse::ofFile).collect(Collectors.toSet());
		Set<ShopCategoryResponse> shopCategoriesResponseList = shopCategories.stream()
				.map(ShopCategoryResponse::ofCategory).collect(Collectors.toSet());
		return new ShopDetailsResponse(shop.getShopId(), FileResponse.ofFile(shop.getCoverImage()), userDetailsResponse,
				shopImageResponseList, shopCategoriesResponseList, ShopLocationResponse.ofShopLocation(shopLocation), prl,
				shop.getCreatedAt(), shop.getModifiedAt());
	}

}
