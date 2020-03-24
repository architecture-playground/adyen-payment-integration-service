package com.playground.adyen.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/adyen-api/payment")
@RestController
public class AdyenNotificationController {

    @PostMapping("/notification")
    public void handleNotification(@RequestBody Map<String, String> notification) {
        log.info("Received notification {}", notification);
    }
}
