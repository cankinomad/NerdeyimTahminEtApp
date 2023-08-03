package com.berka.service;

import com.berka.dto.request.TahminRequestDto;
import com.berka.dto.resposne.TahminResponseDto;
import com.berka.dto.resposne.Sehir;
import com.berka.exception.ErrorType;
import com.berka.exception.TahminManagerException;
import com.berka.manager.IKullaniciManager;
import com.berka.manager.ISehirManager;
import com.berka.mapper.ITahminMapper;
import com.berka.rabbitmq.model.SkorModel;
import com.berka.rabbitmq.producer.SkorProducer;
import com.berka.repository.ITahminRepository;
import com.berka.repository.entity.Tahmin;
import com.berka.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TahminService extends ServiceManager<Tahmin,Long> {

    private final ITahminRepository repository;
    private final ISehirManager sehirManager;
    private final IKullaniciManager kullaniciManager;

    private final SkorProducer skorProducer;

    public TahminService(ITahminRepository repository, ISehirManager sehirManager, IKullaniciManager kullaniciManager, SkorProducer skorProducer) {
        super(repository);
        this.repository = repository;
        this.sehirManager = sehirManager;
        this.kullaniciManager = kullaniciManager;
        this.skorProducer = skorProducer;
    }

    public TahminResponseDto olustur(Long userId) {
        Sehir sehir = sehirManager.sehirGetir().getBody();

        Tahmin tahmin = Tahmin.builder().userid(userId).dogruCevap(sehir.getIsim()).blur(sehir.getBlur()).build();
        save(tahmin);

        kullaniciManager.updateAktifTahminId(tahmin.getUserid(), tahmin.getId());

        return ITahminMapper.INSTANCE.toTahminResponseDto(tahmin);

    }

    public String tahminYap(TahminRequestDto dto) {
        Optional<Tahmin> tahmin = repository.findById(dto.getTahminId());

        if (tahmin.get().getHak() == 0) {
            return "tahmin hakkiniz bitmistir";
        }

        if (tahmin.get().isDogrulandiMi()) {
            throw new TahminManagerException(ErrorType.BAD_REQUEST,"Tahmin daha once dogruilanmis");
        }

        tahmin.get().getTahminler().add(dto.getTahmin());


        if (tahmin.get().getDogruCevap().equalsIgnoreCase(dto.getTahmin())) {
            tahmin.get().setDogrulandiMi(true);
            update(tahmin.get());
            skorProducer.sendSkor(SkorModel.builder().skor(10L).userid(dto.getUserId()).build());
            return "Tebrikler Dogru cevap";
        }

        tahmin.get().setHak(tahmin.get().getHak() - 1);
        update(tahmin.get());
        skorProducer.sendSkor(SkorModel.builder().skor(-5L).userid(dto.getUserId()).build());
        return "Yanlis cevap lutfen tekrar deneyiniz "+ tahmin.get().getHak()+" hakkiniz kaldi";

    }
}
