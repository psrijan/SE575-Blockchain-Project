# blockchain-app

Blockchain simulation frontend build using modern Vue.js

## Build and Run

### Using Docker (Recommended)

First, build and run the `Blockchain-Backend` service. 

In a separate terminal, run the following commands:

```
docker build --tag bc-app .
docker run -p 3000:80 bc-app
```

Then, access the UI by visiting [http://localhost:3000/](http://localhost:3000/)


### Locally
```
yarn install
yarn serve
```

### Compiles and minifies for production
```
yarn build
```
