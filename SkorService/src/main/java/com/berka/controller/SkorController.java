package com.berka.controller;

import com.berka.repository.entity.Skor;
import com.berka.service.SkorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/skor")
public class SkorController {

    private final SkorService service;


    @GetMapping("/sirala")
    public ResponseEntity<List<Skor>> sirala() {
        return ResponseEntity.ok(service.sirala());
    }
}
