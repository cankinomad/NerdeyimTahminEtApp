package com.berka.manager;

import com.berka.dto.resposne.Sehir;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:9090/kullanici",decode404 = true,name = "tahmin-kullanicimanager")
public interface IKullaniciManager {


    @GetMapping("/updateaktiftahmin/{id}/{tahminId}")
    ResponseEntity<Boolean> updateAktifTahminId(@PathVariable Long id,@PathVariable Long tahminId);

}
