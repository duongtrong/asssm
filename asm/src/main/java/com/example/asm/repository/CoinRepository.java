package com.example.asm.repository;

import com.example.asm.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {
    @Query("select c from Coin as c where c.name =: name")
    Coin findByName(String name);

    List<Coin> findByMarket_MarketId(long id);
}
