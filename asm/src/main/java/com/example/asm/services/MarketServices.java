package com.example.asm.services;

import com.example.asm.entity.Market;
import com.example.asm.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class MarketServices {

    @Autowired
    MarketRepository marketRepository;

    public List<Market> getAll(){
        return marketRepository.findAll();
    }

    public Market getById(long id){
        return marketRepository.findById(id).orElse(null);
    }

    public Market getByName(String name){
        if(marketRepository.findByName(name) != null){
            return marketRepository.findByName(name);
        }
        return null;
    }

    public boolean save(Market market){
        if(market != null){
            try{
                marketRepository.save(market);
                return true;
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean update(Market market){
        if(market != null){
            try{
                marketRepository.save(market);
                return true;
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean delete(Market market){
        if(market != null){
            try{
                marketRepository.delete(market);
                return true;
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean deactive(long id){
        Market existMarket = marketRepository.findById(id).orElse(null);
        if(existMarket != null){
            existMarket.status = 0;
            existMarket.updatedAt = Calendar.getInstance().getTimeInMillis();
            try{
                marketRepository.save(existMarket);
                return true;
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return false;
    }
}
