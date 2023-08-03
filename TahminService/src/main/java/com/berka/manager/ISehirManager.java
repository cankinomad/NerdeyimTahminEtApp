package com.berka.manager;

import com.berka.dto.resposne.Sehir;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:9091/sehir",decode404 = true,name = "tahmin-sehirmanager")
public interface ISehirManager {


    @GetMapping("/{sehirid}")
    ResponseEntity<Sehir> sehirGetir(@PathVariable Long sehirid);

    @GetMapping("/randomsehir")
     ResponseEntity<Sehir> sehirGetir();
}
