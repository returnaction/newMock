package com.example.newMock.Controller;

import com.example.newMock.Model.RequestDTO;
import com.example.newMock.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Random;


@RestController  // обозначаем что это контролер
public class MainController {

    // for login
    private Logger log = LoggerFactory.getLogger(MainController.class);

    ObjectMapper mapper = new ObjectMapper();

    Random random = new Random();

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO){
        try{
            String clientId = requestDTO.getClientId();
            char firstDigit = clientId.charAt(0);
            Double maxLimit;
            String currency = "";
            BigDecimal balance;


            if(firstDigit == '8'){
                maxLimit = 2000.00;
                currency = "US";
                // temp, change later. Make it random
//                balance = new BigDecimal(1700);
                balance = BigDecimal.valueOf(random.nextDouble(0, maxLimit + 1));
                balance = balance.setScale(2, BigDecimal.ROUND_HALF_UP);
            } else if(firstDigit == '9'){
                maxLimit = 1000.00;
                currency = "EUR";
                // temp, change later. Make it random
                balance = BigDecimal.valueOf(random.nextDouble(0, maxLimit + 1));
                balance = balance.setScale(2, BigDecimal.ROUND_HALF_UP);
            } else{
                maxLimit = 10000.00;
                currency = "RUB";
                // temp, change later. Make it random
                balance = BigDecimal.valueOf(random.nextDouble(0, maxLimit + 1));
                balance = balance.setScale(2, BigDecimal.ROUND_HALF_UP);
            }

            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setRqUID(requestDTO.getRqUID());
            responseDTO.setClientId(clientId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setMaxLimit(maxLimit);
            responseDTO.setCurrency(currency);
            responseDTO.setBalance(balance);

            log.info("**************** RequestDTO **********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.info("**************** ResponseDTO **********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));

            return responseDTO;

        } catch(Exception e){

            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
