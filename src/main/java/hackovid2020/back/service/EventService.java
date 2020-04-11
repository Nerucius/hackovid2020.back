package hackovid2020.back.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hackovid2020.back.dao.Event;
import hackovid2020.back.dao.support.EventType;
import hackovid2020.back.repository.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;

	public Event saveEvent(Event event) {
		return eventRepository.save(event);
	}

	public Event findEvent(Long id) {
		return eventRepository.getOne(id);
	}

	public Event updateEvent(Long id, String name, EventType eventType) {
		Event event = eventRepository.getOne(id);
		event.setName(name);
		event.setEventType(eventType);
		event.setModifiedAt(Calendar.getInstance().getTime());
		return eventRepository.save(event);
	}

	public void deleteEvent(Long id) {
		eventRepository.deleteById(id);
	}
	
	

}
