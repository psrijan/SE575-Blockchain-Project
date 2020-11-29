# Running the application
1. Install mvn 
2. cd Crypto-Backend
3. ./mvnw spring-boot:run

# End Points
1. Status: (http://localhost:8080/crypto)
- Displays the status of the server. If the server is running returns server start timestamp
- GET request
2. Display All Crypto: (http://localhost:8080/crypto/all)
- GET request 
3. Add Crypto: (http://localhost:8080/crypto/all)
- POST request
```
Same Request: 
{
    "hash":"aaaaaaaa",
    "previousHash":"00000a99e4f7043985ce1cfef0f056be3fda1ce8a5ee9f85712e73075f9fa6d9",
    "blockName": "first",
    "data":"A payed B 100",
    "timestamp":100,
    "nonce":4

}
```
4 Is Valid: (http//localhost:8080/crypto/valid)
- GET request
