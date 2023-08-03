package com.berka.controller;

import com.berka.dto.request.EkleRequestDto;
import com.berka.repository.entity.Sehir;
import com.berka.service.SehirService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sehir")
@RequiredArgsConstructor
public class SehirController {

    private final SehirService service;


    @PostMapping("/ekle")
    public ResponseEntity<Sehir> ekle(@RequestBody EkleRequestDto dto) {
        return ResponseEntity.ok(service.ekle(dto));
    }

    @GetMapping("/{sehirid}")
    public ResponseEntity<Sehir> sehirGetir(@PathVariable Long sehirid) {
        return ResponseEntity.ok(service.sehirGetir(sehirid));
    }


    @GetMapping("/randomsehir")
    public ResponseEntity<Sehir> sehirGetir() {
        return ResponseEntity.ok(service.randomSehirGetir());
    }
}
