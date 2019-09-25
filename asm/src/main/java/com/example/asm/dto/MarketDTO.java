package com.example.asm.dto;

import com.example.asm.entity.Coin;
import com.example.asm.entity.Market;

import java.util.ArrayList;
import java.util.List;

public class MarketDTO {
    public long marketId;
    public String name;
    public String description;
    public long createdAt;
    public long updatedAt;
    public int status;

    public List<Long> coinId;
    public List<String> coinName;

    public MarketDTO(Market market){
        this.marketId = market.getMarketId();
        this.name = market.getName();
        this.description = market.getDescription();
        this.createdAt = market.getCreatedAt();
        this.updatedAt = market.getUpdatedAt();
        this.status = market.getStatus();
        this.coinId = new ArrayList<>();
        this.coinName = new ArrayList<>();
        for (Coin coin: market.getCoins()) {
            this.coinId.add(coin.getCoinId());
        }
        for (Coin coin: market.getCoins()) {
            this.coinName.add(coin.getName());
        }
    }

    public long getMarketId() {
        return marketId;
    }

    public void setMarketId(long marketId) {
        this.marketId = marketId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Long> getCoinId() {
        return coinId;
    }

    public void setCoinId(List<Long> coinId) {
        this.coinId = coinId;
    }

    public List<String> getCoinName() {
        return coinName;
    }

    public void setCoinName(List<String> coinName) {
        this.coinName = coinName;
    }
}
