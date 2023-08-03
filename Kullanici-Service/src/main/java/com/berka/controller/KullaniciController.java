package com.berka.controller;

import com.berka.dto.request.GirisRequestDto;
import com.berka.dto.request.KayitRequestDto;
import com.berka.dto.response.KayitResponseDto;
import com.berka.repository.entity.Kullanici;
import com.berka.service.KullaniciService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kullanici")
@RequiredArgsConstructor
public class KullaniciController {

    private final KullaniciService service;

    @PostMapping("/kayit")
    public ResponseEntity<KayitResponseDto> kayit(@RequestBody KayitRequestDto dto) {
        return ResponseEntity.ok(service.kayit(dto));
    }


    @PostMapping("/giris")
    public ResponseEntity<Boolean> giris(@RequestBody GirisRequestDto dto) {
        return ResponseEntity.ok(service.giris(dto));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Kullanici>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/updateaktiftahmin/{id}/{tahminId}")
    public ResponseEntity<Boolean> updateAktifTahminId(@PathVariable Long id,@PathVariable Long tahminId) {
        return ResponseEntity.ok(service.updateAktifTahminId(id,tahminId));
    }
}
