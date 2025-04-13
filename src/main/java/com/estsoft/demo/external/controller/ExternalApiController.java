package com.estsoft.demo.external.controller;

import com.estsoft.demo.external.service.ExternalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExternalApiController {

    private final ExternalService externalService;

    public ExternalApiController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping("/api/external")
    public ResponseEntity<String> callExternal() {
        // RestTemplate
        externalService.call();

        return ResponseEntity.ok().build();
    }
}
