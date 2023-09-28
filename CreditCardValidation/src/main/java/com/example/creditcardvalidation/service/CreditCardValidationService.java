package com.example.creditcardvalidation.service;

import com.example.creditcardvalidation.model.CreditCardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class CreditCardValidationService {

    public boolean validateCreditCard(CreditCardRequest data) {
        String cardNumber = data.getCardNumber();
        Integer cvv = data.getCvv();
        String expiryDate = data.getExpirationDate();

        boolean result = false;

        if (isValidCardNumber(cardNumber)) {
            System.out.println("Card number is valid");
            result = true;
        }

        if (isValidCvv(cvv, cardNumber)) {
            System.out.println("CVV is valid");
            result = true;
        }

        if(isValidLuhn(cardNumber)) {
            System.out.println("Card number is valid with Luhn algorithm");
            result = true;
        } else {
            System.out.println("Card number is invalid with Luhn algorithm");
            result = false;
        }

        if(isValidExpiryDate(expiryDate)) {
            System.out.println("Expiry date is valid");
            result = true;
        } else {
            System.out.println("Expiry date is invalid");
            result = false;
        }

        return result;
    }
    private boolean isValidExpiryDate(String expiryDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
            sdf.setLenient(false); // Strict date parse

            Date inputDate = sdf.parse(expiryDate);

            Date currentDate = new Date();

            if (inputDate != null && inputDate.after(currentDate)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isValidCvv(Integer cvv, String cardNumber) { // done
        if(cvv != null){
            System.out.println("CVV is not null");
            if (countCvvDigits(cvv) == 3){
                System.out.println("CVV is European");
                return true;
            } else if (countCvvDigits(cvv) == 4){
                System.out.println("CVV is American");
                if(cardNumber.startsWith("34") || cardNumber.startsWith("37")) {
                    System.out.println("Card number starts with 34 or 37");
                }
                return true;
            } else {
                System.out.println("CVV is invalid");
                return false;
            }
        }
        return false;
    }

    private Integer countCvvDigits(Integer cvv) {
        Integer count = 0;
        while (cvv != 0) {
            cvv /= 10;
            count++;
        }
        return count;
    }

    private boolean isValidCardNumber(String cardNumber) { // done
        return cardNumber.length() >= 16 && cardNumber.length() <= 19;
    }

    private boolean isValidLuhn(String cardNumber) { // done
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
