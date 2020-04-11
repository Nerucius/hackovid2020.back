package hackovid2020.back.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import hackovid2020.back.dao.support.EventType;

@Entity(name="event")
public class Event extends EntityObject {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long eventId;
	
	private String name;
	
	private EventType eventType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Shop shop;

	private Event(String name, EventType eventType, Shop shop, Date createdAt, Date modifiedAt) {
		super(createdAt, modifiedAt);
		this.name = name;
		this.eventType = eventType;
		this.shop = shop;
	}
	
	public static Event createEvent(String name, EventType eventType, Shop shop, Date createdAt, Date modifiedAt) {
		return new Event(name, eventType, shop, createdAt, modifiedAt);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType evenType) {
		this.eventType = evenType;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Long getEventId() {
		return eventId;
	}
	
}
