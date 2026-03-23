package com.cloudhebat.cobaspringotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String home() {
        log.info("Home endpoint called");
        return "Hello World!";
    }

    @GetMapping("/exception")
    public String exception() {
        throw new RuntimeException();
    }

    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        log.info("Greeting user: {}", name);
        simulateWork();
        return "Hello, " + name + "!";
    }

    @GetMapping("/slow")
    public String slow() throws InterruptedException {
        MDC.put("productId", "112233");
        log.info("Starting slow operation");
        Thread.sleep(500);
        log.info("Slow operation completed");
        MDC.clear();
        return "Done!";
    }

    private void simulateWork() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}