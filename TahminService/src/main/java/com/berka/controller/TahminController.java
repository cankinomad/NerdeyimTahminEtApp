package com.berka.controller;

import com.berka.dto.request.TahminRequestDto;
import com.berka.dto.resposne.TahminResponseDto;
import com.berka.service.TahminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tahmin")
@RequiredArgsConstructor
public class TahminController {

    private final TahminService service;

    @GetMapping("/olsutur")
    public ResponseEntity<TahminResponseDto> tahminOlustur(Long userId) {
        return ResponseEntity.ok(service.olustur(userId));
    }

    @PostMapping("/tahminyap")
    public ResponseEntity<String> tahminYap(@RequestBody TahminRequestDto dto) {
        return ResponseEntity.ok(service.tahminYap(dto));
    }
}
