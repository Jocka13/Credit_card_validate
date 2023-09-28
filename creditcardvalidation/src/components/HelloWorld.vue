<template>
  <div class="container">
    <h1>Credit Card Validation</h1>
    <form id="creditCardForm" @submit.prevent="validateCreditCard()">
      <div class="ms-0 mb-3">
        <label for="cardNumber" class="form-label">Credit Card Number:</label>
        <input v-model="cardNumber" type="text" class="form-control" id="cardNumber" placeholder="Enter card number" required>
      </div>
      <div class="mb-3">
        <label for="expiryDate" class="form-label">Expiry Date (MM/YYYY):</label>
        <input v-model="expiryDate" type="text" class="form-control" id="expiryDate" placeholder="MM/YYYY" required>
      </div>
      <div class="mb-3">
        <label for="cvv" class="form-label">CVV:</label>
        <input v-model="cvv" type="text" class="form-control" id="cvv" placeholder="CVV" required>
      </div>
      <button type="submit" class="btn btn-primary">Validate</button>
    </form>
    <div id="validationResult">{{ validationResult }}</div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'CreditCardValidation',
  data() {
    return {
      cardNumber: '',
      expiryDate: '',
      cvv: '',
      validationResult: ''
    }
  },
  methods: {
    async validateCreditCard() {
      try {
        const response = await axios.post('http://localhost:8080/validate', {
          cardNumber: this.cardNumber,
          expiryDate: this.expiryDate,
          cvv: this.cvv
        }, {
          headers: {
            'Access-Control-Allow-Origin': '*'
          }
        });
        this.validationResult = response.data.message;
      } catch (error) {
        console.log('Error: ', error);
        this.validationResult = 'Error occurred while validating credit card.';
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
body {
  background-color: #f8f9fa;
}

.container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  margin-top: 50px;
}

h1 {
  text-align: center;
}

#validationResult {
  text-align: center;
  margin-top: 20px;
}
</style>
