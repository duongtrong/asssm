package com.example.asm.controller;

import com.example.asm.dto.CoinDTO;
import com.example.asm.entity.Coin;
import com.example.asm.entity.rest.RESTResponse;
import com.example.asm.services.CoinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/coins")
@CrossOrigin
public class CoinController {
    @Autowired
    CoinServices coinServices;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<CoinDTO> coinsDTO = new ArrayList<>();
        for (Coin coin: coinServices.getAll()) {
            CoinDTO coinDTO = new CoinDTO(coin);
            coinsDTO.add(coinDTO);
        }
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(coinsDTO)
                .build(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id){
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(new CoinDTO(coinServices.getById(id)))
                .build(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/getByName/{name}")
    public ResponseEntity<Object> getByName(@PathVariable String name){
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(new CoinDTO(coinServices.getByName(name)))
                .build(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/getByMarket/{id}")
    public ResponseEntity<Object> getByMarket(@PathVariable long id){
        List<CoinDTO> coinsDTO = new ArrayList<>();
        for (Coin coin: coinServices.getByMarketId(id)
        ) {
            CoinDTO coinDTO = new CoinDTO(coin);
            coinsDTO.add(coinDTO);
        }
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(coinsDTO)
                .build(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Coin coin){
        if(coinServices.save(coin)){
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
    public ResponseEntity<Object> update(@RequestBody Coin coin){
        if(coinServices.update(coin)){
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
        Coin coin = coinServices.getById(id);
        if (coin == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not found")
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        coinServices.delete(coin);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Delete Success")
                .build(),
                HttpStatus.OK);
    }
}
