# SE 575 Group 9 - Blockchain Application

## Group Members
Ameer Jalil

Phat Ngo

Srijan Pandey

## About

The Blockchain simulation application is split into a Vue.js frontend and a Java backend. 
Please see the respective project directory for further instructions
on how to build and run each part of the project.

### Blockchain Simulation Backend
This project provides the services needed by the frontend to create and manage a Blockchain. It is built using Java and Spring-boot

Running the application
Docker (Recommended)
docker build --tag bc-backend .
docker run -p 8080:8080 bc-backend
Locally
Install mvn
cd Blockchain-Backend
./mvnw spring-boot:run
Design Decision
Algorithm:
The Blockchain algorithm is divided into three Java classes: Block, SHA256Utility, and Blockchain. Block.java contains block details, along with two methods: calculateHash and mineBlock. The calculateHash method calls upon the SHA256Utility class (where a function is located that will take a string consisting of the block's details and return a hashed string) This assists in creating the block's hash, which is composed of the previous hash, the current timestamp, a nonce, the block's name and data.

The mineBlock method mines the blocks of the chain and incorporates the difficulty level the user inputs (difficulty determines how many zeros are padded to the beginning of the hash and how many resources and computations are needed to crack the block's hash). Additionally, the method also takes input from the user to limit the amount of maximum attempts to create unique hashes with the selected difficulty in the case of overly long computation times.

The InMemoryBlockchainCore.java contains the ArrayList that stores the blocks an used to determine the previously mentioned difficulty levels and maximum computational limits for block mining. Furthermore, the class contains the isValid method which verifies the validity of the blocks in the chain and confirms whether or not the blocks in the chain have been tamper with. The method verifies the validity by looping through each block that is part of the chain, comparing each one of the hashes against the current, calculated, and previous hashes.

Additionally, a simple implementation of the hashcash proof-of-work is used to prevent tampering due to the considerable amount of resources (time and computational power) that would be required to create new blocks.

Backend Architecture:
The backend project is designed on the Controller, Service, Repository pattern. We have used springboot as our container to build the backend application. This gave us an elegant way to design a clean architecture, while using minimal code/xmls for configuring and running the application.

Using the springboot's annotation framework, we decouple the dependencies among the classes. For instance, we have used interfaces to design our IBlockchainCore.java and it's other child classes. Using this along with spring annotation, we can easily configure the application to choose between an InMemoryBlockchainCore and DBlockchainCore (DB stands for database, for now this is a stub class)

While considering the design we also thought about cross cutting concerns such as error handling. For this we used a centralized error interceptor, RestExceptionHandler, in the application. RestExceptionHandler handles all errors and gracefully and returns error response to the client. This way we reduce the amount of boilerplate code in the application, and also centralize our error handling concerns.

We made separate DTOs for Internal, External Request, and External Response. While this does increase the number of classes in the application, it future proof our design, as there aren't dependencies between Server DTOs (data transfer objects) and Request DTOs. Which means a change in one will not affect the other. We have used inheritance to provide some common behavior for all the DTOs. For instance, DTOs that require message, success (common fields for DTOs) can be inherited from the BaseResponse DTO.

We have also given thought to our Rest API design, and tried to make it simple with minimal number of responses. In addition, since url versions are important part of application, as it causes changes in both client and server side, we have used proper Restful API naming conventions (ie: nouns to represent resource, actions identified by request type).



blockchain-app
Blockchain simulation frontend build using modern Vue.js

Build and Run
Using Docker (Recommended)
First, build and run the Blockchain-Backend service.

In a separate terminal, run the following commands:

docker build --tag bc-app .
docker run -p 3000:80 bc-app
Then, access the UI by visiting http://localhost:3000/

Locally
yarn install
yarn serve
Compiles and minifies for production
yarn build
Design decisions
The views follow the "Parent/Child" paradigm, where the main view is relatively static, but creates children dynamically to populate the screen. Each added block acts as the Child.

The requests made to the server are abstracted into the HttpService.ts to reduce the need to search for specific endpoint/request strings and have them centralized in one location. This allows for painless modification of the server request related functionality, without needing to modify too much.

The overall system architecture follows a Model-View-Controller pattern, by having the frontend render the Blockchain Model into the View, with any changes handled by the server's Controller. This divides the responsibilities to various modular components, rather than having a hard-coded monolithic design.

