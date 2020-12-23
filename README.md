# EcommerceData

Web Application providing the following functionality:
- Upload & Listing of CSV data 

## Getting Started
### Prerequisites
- Java 11
- Node (v14.15.2)
- MySQL (CE v8.0.22.0)

### Setup
1) Create an empty folder and clone both projects:
    - EcommerceDataReact: https://github.com/simweijie/EcommerceDataReact
    - EcommerceDataKotlinSpring: https://github.com/simweijie/EcommerceDataKotlinSpring
2) Install the necessary node modules. At the root folder of EcommerceDataReact run:
```
yarn install
```
2) Register an account and download data.csv file from https://www.kaggle.com/carrie1/ecommerce-data/
3) Ensure that the MySQL server is running with following created:
    - Schema: testdb
    - Username: root
    - Password: 123456
4) Alternatively instead of using the credentials in (3), you may choose to edit the application.properties folder as necessary
```
spring.datasource.url
spring.datasource.username
spring.datasource.password
```

## Development
1) From the root folder of EcommerceDataReact run:
```
yarn start
```
2) From the root folder of EcommerceDataKotlinSpring run:
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
- [ ] Improve progress bar and user awareness on upload progress
- [ ] Expand search functionality
- [ ] Improve insertion time into DB, currently ~1000 records/25s
- [ ] Establish compound key to prevent duplicate entries into DB
