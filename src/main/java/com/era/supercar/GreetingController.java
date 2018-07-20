package com.era.supercar;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GreetingController
{
    @GetMapping("/greeting")
    public String greeting()
    {
        return "Hello! I'm a spring greeting!";
    }
}