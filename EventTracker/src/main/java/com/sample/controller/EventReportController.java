package com.sample.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.model.Event;

@RestController
public class EventReportController {

	@RequestMapping("/events")
	public List<Event> getEvents(){
		List<Event> events=new ArrayList<Event>();
		
		Event event1=new Event();
		event1.setName("Java User Group");
		
		events.add(event1);
		
		Event event2=new Event();
		event2.setName("Python User Group");
		
		events.add(event2);
		return events;
			
	}

}
