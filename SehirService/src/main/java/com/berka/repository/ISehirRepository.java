package com.berka.repository;

import com.berka.repository.entity.Sehir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISehirRepository extends JpaRepository<Sehir,Long> {

    Long countAllBy();

    @Query(value = "select s.id from Sehir as s order by s.id desc limit 1",nativeQuery = true)
    Long getLastId();

    Sehir getFirstByOrderByIdDesc();
}
