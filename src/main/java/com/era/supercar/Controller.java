package com.era.supercar;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Controller
{
    @GetMapping("/events")
    public Event getEvents()
    {
        Event event = new Event(35, 0);
        return event;
    }

    @GetMapping("/greeting")
    public String greeting()
    {
        return "Hello! I'm a spring greeting!";
    }
}