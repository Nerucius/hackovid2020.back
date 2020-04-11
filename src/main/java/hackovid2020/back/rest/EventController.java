package hackovid2020.back.rest;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hackovid2020.back.dao.Shop;
import hackovid2020.back.dto.event.EventRequest;
import hackovid2020.back.dto.event.EventResponse;
import hackovid2020.back.service.EventService;
import hackovid2020.back.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/event")
@Api(value = "event", description = "Event management", tags = { "Event" })
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private ShopService shopService;
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Creates a new event.")
	@Transactional
	public EventResponse createEvent(@RequestBody EventRequest request) {
		Shop shop = shopService.findById(request.getShopId());
		return EventResponse.ofEvent(eventService.saveEvent(request.toEvent(shop)));
	}
	
	@GetMapping(value="/{id}")
	@ResponseBody
	@ApiOperation(value= "Get Event.")
	@Transactional
	public EventResponse getEvent(@PathVariable("id") Long id) {
		return EventResponse.ofEvent(eventService.findEvent(id));
	}
	
	@PutMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="Update user.")
	@Transactional
	public EventResponse updateEvent(@PathVariable("id") Long id, @RequestBody EventRequest request) {
		return EventResponse.ofEvent(eventService.updateEvent(id, request.getName(), request.getEventType()));
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation(value="Delete event.")
	@Transactional
	public HttpStatus deleteEvent(@PathVariable("id") Long id) {
		eventService.deleteEvent(id);
		return HttpStatus.OK;
	}

}
