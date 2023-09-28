package com.example.creditcardvalidation.controller;

import com.example.creditcardvalidation.model.CreditCardRequest;
import com.example.creditcardvalidation.service.CreditCardValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
public class CreditCardController {
    private final CreditCardValidationService validationService;

    @PostMapping("/validate")
    @CrossOrigin(origins = "http://localhost:8083")
    public ResponseEntity<String> validateCreditCard(@RequestBody CreditCardRequest data) {
        boolean isValid = validationService.validateCreditCard(data);
        if (isValid) {
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.badRequest().body("Failure");
        }
    }
}
