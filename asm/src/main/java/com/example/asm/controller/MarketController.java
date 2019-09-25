package com.example.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t1708e.assignment_1.dto.MarketDTO;
import t1708e.assignment_1.entity.Coin;
import t1708e.assignment_1.entity.Market;
import t1708e.assignment_1.entity.rest.RESTResponse;
import t1708e.assignment_1.services.MarketServices;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/markets")
@CrossOrigin
public class MarketController  {
    @Autowired
    MarketServices marketServices;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<MarketDTO> marketsDTO = new ArrayList<>();
        for (Market market: marketServices.getAll()) {
            MarketDTO marketDTO = new MarketDTO(market);
            marketsDTO.add(marketDTO);
        }
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(marketsDTO)
                .build(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id){
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(new MarketDTO(marketServices.getById(id)))
                .build(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/getByName/{name}")
    public ResponseEntity<Object> getByName(@PathVariable String name){
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(new MarketDTO(marketServices.getByName(name)))
                .build(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Market market){
        for (Coin coin : market.getCoins()) {
            coin.setMarket(market);
        }
        if(marketServices.save(market)){
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.CREATED.value())
                    .setMessage("Action Success")
                    .build(),
                    HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Action Success")
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Market market){
        if(marketServices.update(market)){
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.CREATED.value())
                    .setMessage("Action Success")
                    .build(),
                    HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new RESTResponse.Error()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Action Success")
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {
        Market market = marketServices.getById(id);
        if (market == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not found")
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        marketServices.delete(market);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Delete Success")
                .build(),
                HttpStatus.OK);
    }
}
