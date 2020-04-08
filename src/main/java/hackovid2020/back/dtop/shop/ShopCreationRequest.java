package hackovid2020.back.dtop.shop;

import java.awt.geom.Point2D;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonProperty;

import hackovid2020.back.Constants;
import hackovid2020.back.dao.Shop;
import hackovid2020.back.dao.User;
import hackovid2020.back.utils.MD5Util;

public class ShopCreationRequest {
	
	private Long ownerId;
	
	private String coverUrl;
	
	private Point2D location;
	
	private ShopCreationRequest(
			@JsonProperty("ownerId") Long ownerId,
			@JsonProperty("coverUrl") String coverUrl,
			@JsonProperty("location") Point2D location) {
		this.ownerId = ownerId;
		this.coverUrl = Constants.GRAVATAR + MD5Util.md5Hex(coverUrl);
		this.location = location;
	}
	
	public Shop toShop(User owner) {
		return Shop.createShop(coverUrl, location, owner, Calendar.getInstance().getTime(),
				Calendar.getInstance().getTime());
	}

	public Long getOwnerId() {
		return ownerId;
	}

}
