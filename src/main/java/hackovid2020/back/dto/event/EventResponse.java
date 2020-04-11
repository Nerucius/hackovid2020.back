package hackovid2020.back.dto.event;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import hackovid2020.back.dao.Event;
import hackovid2020.back.dao.support.EventType;

public class EventResponse {
	
	@JsonProperty
	private Long eventId;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private EventType eventType;
	
	@JsonProperty
	private Long shopId;
	
	@JsonProperty
	private Date createdAt;
	
	@JsonProperty
	private Date modifiedAt;
	
	@JsonCreator
	private EventResponse(Long eventId, String name, EventType eventType, Long shopId, Date createdAt, Date modifiedAt) {
		this.eventId = eventId;
		this.name = name;
		this.eventType = eventType;
		this.shopId = shopId;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	public static EventResponse ofEvent(Event event) {
		return new EventResponse(event.getEventId(), event.getName(), event.getEventType(), event.getShop().getShopId(),
				event.getCreatedAt(), event.getModifiedAt());
	}

}
