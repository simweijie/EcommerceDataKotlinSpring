# EcommerceData

Web Application providing the following functionality:
1) Upload & Listing of CSV data 

## Getting Started
### Prerequisites
```
Java 11
Node (v14.15.2)
MySQL (CE v8.0.22.0)
```

### Setup
1) Create an empty folder and clone both projects:
    - EcommerceDataReact: https://github.com/simweijie/EcommerceDataReact
    - EcommerceDataKotlinSpring: https://github.com/simweijie/EcommerceDataKotlinSpring
2) Register an account and download data.csv file from https://www.kaggle.com/carrie1/ecommerce-data/
3) Ensure that the MySQL server is running with following created:
```
Schema: testdb
Username: root
Password: 123456
```



## Development
1) FrontEnd: From the root folder of EcommerceDataReact run:
```
yarn start
```
2) BackEnd: From the root folder of EcommerceDataKotlinSpring run:
```
gradlew bootrun
```
3) Open http://localhost:3000 to view it in the browser.

## Technology
- Kotlin
- Spring
- React
- MySQL
  
## To Fix
- [ ] /upload unit test
- [ ] Improve progress bar and user feedback on progress
- [ ] Expand search functionality
- [ ] Improve insertion time into DB
- [ ] Establish compound key to prevent duplicate entries into DB
