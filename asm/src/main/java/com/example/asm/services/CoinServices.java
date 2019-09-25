package com.example.asm.services;

import com.example.asm.entity.Coin;
import com.example.asm.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class CoinServices {
    @Autowired
    CoinRepository coinRepository;

    public List<Coin> getAll(){
        return coinRepository.findAll();
    }

    public Coin getById(long id){
        return coinRepository.findById(id).orElse(null);
    }

    public Coin getByName(String name){
        if(coinRepository.findByName(name) != null){
            return coinRepository.findByName(name);
        }
        return null;
    }

    public List<Coin> getByMarketId(long id){
        if(coinRepository.findByMarket_MarketId(id) != null){
            return coinRepository.findByMarket_MarketId(id);
        }
        return null;
    }

    public boolean save(Coin coin){
        if(coin != null){
            try{
                coinRepository.save(coin);
                return true;
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean update(Coin coin){
        if(coin != null){
            try{
                coinRepository.save(coin);
                return true;
            }
            catch (Exception ex){
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean delete(Coin coin){
        if(coin != null){
            try{
                coinRepository.delete(coin);
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
        Coin existCoin = coinRepository.findById(id).orElse(null);
        if(existCoin != null){
            existCoin.status = 0;
            existCoin.updatedAt = Calendar.getInstance().getTimeInMillis();
            try{
                coinRepository.save(existCoin);
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
