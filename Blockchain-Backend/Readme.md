# Running the application
1. Install mvn 
2. cd Blockchain-Backend
3. ./mvnw spring-boot:run

# End Points
1. Status: (http://localhost:8080/blockchain)
- Displays the status of the server. If the server is running returns server start timestamp
- GET request
2. Display All Blocks: (http://localhost:8080/blockchain/all)
- GET request 
3. Add Blockchain: (http://localhost:8080/blockchain/add)
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
4 Is Valid: (http://localhost:8080/blockchain/valid)
GET request


Proper URL Naming Convention:


Segregation of different modules

Encapsulation of data.

Use of streams.

Proper handling of cross cutting concerns using advice. 




### Docker

`docker build --tag bc-backend .`

`docker run -p 8080:8080 bc-backend`
