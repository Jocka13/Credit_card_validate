package com.example.creditcardvalidation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "credit_card")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditCard {
    @Id
    private Integer id;
    private String cardNumber;
    private String expirationDate;
    private Integer cvv;
}
