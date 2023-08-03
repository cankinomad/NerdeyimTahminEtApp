package com.berka.service;

import com.berka.rabbitmq.model.SkorModel;
import com.berka.repository.ISkorRepository;
import com.berka.repository.entity.Skor;
import com.berka.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkorService extends ServiceManager<Skor, Long> {

    private final ISkorRepository repository;

    public SkorService(ISkorRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Skor saveOrUpdateSkor(SkorModel model) {
        Optional<Skor> skor = repository.findByUserid(model.getUserid());

        if (skor.isEmpty()) {
             skor= Optional.of(save(Skor.builder().skor(model.getSkor()).userid(model.getUserid()).build()));
        }else{
            skor.get().setSkor(skor.get().getSkor() + model.getSkor());
            update(skor.get());
        }

        return skor.get();
    }

    public List<Skor> sirala() {
        return repository.findAllByOrderBySkorDesc();
    }
}
