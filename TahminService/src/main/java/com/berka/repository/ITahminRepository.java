package com.berka.repository;

import com.berka.repository.entity.Tahmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITahminRepository extends JpaRepository<Tahmin,Long> {


}
