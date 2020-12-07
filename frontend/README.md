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

## Design decisions

The views follow the "Parent/Child" paradigm, where the main view is relatively static, 
but creates children dynamically to populate the screen. Each added block acts as the Child.

The requests made to the server are abstracted into the `HttpService.ts` to 
reduce the need to search for specific endpoint/request strings and have them centralized
in one location. This allows for painless modification of the server request related
functionality, without needing to modify too much.

The overall system architecture follows a Model-View-Controller pattern, by having the
frontend render the Blockchain Model into the View, with any changes handled by the server's Controller.
This divides the responsibilities to various modular components, rather than having a hard-coded
monolithic design. 
