package com.era.supercar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Controller
{
	
    @GetMapping("/events")
    public List<Long> getEvents()
    {
    	ArrayList<Long> event = new  ArrayList<Long>();
    	for(Event e : ServerWorker.bag) {
    		event.add(e.getEvent());
    		
    	}
        return event;
    }
        

    @GetMapping("/greeting")
    public String greeting()
    {
        return "Hello! I'm a spring greeting!";
    }
}