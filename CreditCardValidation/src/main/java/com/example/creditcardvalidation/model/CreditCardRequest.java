package com.example.creditcardvalidation.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditCardRequest {
    private String cardNumber;
    private String expirationDate;
    private Integer cvv;
}
