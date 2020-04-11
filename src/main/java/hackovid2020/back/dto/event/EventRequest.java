package hackovid2020.back.dto.event;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import hackovid2020.back.dao.Event;
import hackovid2020.back.dao.Shop;
import hackovid2020.back.dao.support.EventType;

public class EventRequest {
	
	private String name;
	
	private Long shopId;
	
	private EventType eventType;
	
	@JsonCreator
	private EventRequest(
			@JsonProperty("name") String name,
			@JsonProperty("shopId") Long shopId,
			@JsonProperty("eventType") EventType eventType) {
		this.name = name;
		this.shopId = shopId;
		this.eventType = eventType;
	}
	
	public Event toEvent(Shop shop) {
		return Event.createEvent(name, eventType, shop,
				Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
	}

	public String getName() {
		return name;
	}

	public Long getShopId() {
		return shopId;
	}

	public EventType getEventType() {
		return eventType;
	}
	
}
