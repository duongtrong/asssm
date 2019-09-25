package com.example.asm.entity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name="coin")
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long coinId;
    public String name;
    public String baseAsset;
    public String quoteAsset;
    public String lastPrice;
    public String volumn24h;
    public long createdAt;
    public long updatedAt;
    public int status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "marketId")
    public Market market;

    public Coin() {
        this.createdAt = Calendar.getInstance().getTimeInMillis();
        this.updatedAt = Calendar.getInstance().getTimeInMillis();
        this.status = 1;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public long getCoinId() {
        return coinId;
    }

    public void setCoinId(long coinId) {
        this.coinId = coinId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseAsset() {
        return baseAsset;
    }

    public void setBaseAsset(String baseAsset) {
        this.baseAsset = baseAsset;
    }

    public String getQuoteAsset() {
        return quoteAsset;
    }

    public void setQuoteAsset(String quoteAsset) {
        this.quoteAsset = quoteAsset;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getVolumn24h() {
        return volumn24h;
    }

    public void setVolumn24h(String volumn24h) {
        this.volumn24h = volumn24h;
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
}
