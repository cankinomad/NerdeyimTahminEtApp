package com.berka.service;

import com.berka.dto.request.EkleRequestDto;
import com.berka.exception.ErrorType;
import com.berka.exception.SehirManagerException;
import com.berka.mapper.ISehirMapper;
import com.berka.repository.ISehirRepository;
import com.berka.repository.entity.Sehir;
import com.berka.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class SehirService extends ServiceManager<Sehir,Long> {

    private final ISehirRepository repository;


    public SehirService(ISehirRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Sehir ekle(EkleRequestDto dto) {
        Sehir sehir = ISehirMapper.INSTANCE.toSehir(dto);

        sehir.setBlur(sehir.getIsim().substring(0,2));
       return save(sehir);
    }

    public Sehir sehirGetir(Long sehirid) {

        Optional<Sehir> byId = findById(sehirid);
        if (byId.isEmpty()) {
            throw new SehirManagerException(ErrorType.SEHIR_NOT_FOUND);
        }

        return byId.get();
    }

    public Sehir randomSehirGetir() {
        Random random = new Random();

        Long id1 = repository.getLastId();
        Sehir sehir2 = repository.getFirstByOrderByIdDesc();
        Long id2 = repository.countAllBy();

        Long id = random.nextLong(1,id2+1);
        System.out.println(id1);
        System.out.println(sehir2);
        System.out.println(id2);
        Optional<Sehir> byId = findById(id);
        if (byId.isEmpty()) {
            throw new SehirManagerException(ErrorType.SEHIR_NOT_FOUND);
        }

        return byId.get();
    }
}
