package com.example.asm.repository;

import com.example.asm.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
    @Query("select u from Market as u where u.name =:name")
    Market findByName(String name);
}
