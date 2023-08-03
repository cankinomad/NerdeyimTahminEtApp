package com.berka.service;

import com.berka.dto.request.GirisRequestDto;
import com.berka.dto.request.KayitRequestDto;
import com.berka.dto.response.KayitResponseDto;
import com.berka.exception.ErrorType;
import com.berka.exception.KullaniciManagerException;
import com.berka.mapper.IKullaniciMapper;
import com.berka.repository.IKullaniciRepository;
import com.berka.repository.entity.Kullanici;
import com.berka.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KullaniciService extends ServiceManager<Kullanici,Long> {

    private final IKullaniciRepository repository;

    public KullaniciService(IKullaniciRepository kullaniciRepository) {
        super(kullaniciRepository);
        this.repository = kullaniciRepository;
    }

    public KayitResponseDto kayit(KayitRequestDto dto) {
        Kullanici kullanici = IKullaniciMapper.INSTANCE.toKullanici(dto);
        save(kullanici);
        return IKullaniciMapper.INSTANCE.toKayitResponseDto(kullanici);
    }

    public Boolean giris(GirisRequestDto dto) {
        Optional<Kullanici> optionalKullanici = repository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());

        if (optionalKullanici.isEmpty()) {
            throw new KullaniciManagerException(ErrorType.USER_NOT_FOUND);
        }

        return true;
    }

    public Boolean updateAktifTahminId(Long id,Long tahminId) {

        Optional<Kullanici> optionalKullanici = findById(id);
        if (optionalKullanici.isEmpty()) {
            throw new KullaniciManagerException(ErrorType.USER_NOT_FOUND);
        }

        optionalKullanici.get().setAktifTahminId(tahminId);
        optionalKullanici.get().getTahminler().add(tahminId);
        update(optionalKullanici.get());

        return true;
    }
}
