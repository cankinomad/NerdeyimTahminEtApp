package com.berka.repository;

import com.berka.repository.entity.Skor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISkorRepository extends JpaRepository<Skor, Long> {


    Optional<Skor> findByUserid(Long userid);

    List<Skor> findAllByOrderBySkorDesc();
}
