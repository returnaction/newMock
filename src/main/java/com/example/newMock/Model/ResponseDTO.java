package com.example.newMock.Model;

import lombok.*;

import java.math.BigDecimal;

// this is lombock. It creates for us Getters and Setters and other stuff
// Evgeniy said that we can just use @Data to implement all of that.s
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDTO {

    private String rqUID;
    private String clientId;
    private String account;
    private String currency;
    private BigDecimal balance;
    private Double maxLimit;

}
