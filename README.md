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
1) Create an empty folder and clone both EcommerceDataReact & EcommerceDataKotlinSpring 
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
3) Start http://localhost:3000

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

## References
### Upload
- https://bezkoder.com/react-file-upload-spring-boot/
- https://bezkoder.com/spring-boot-upload-csv-file/
### Paginate
- https://bezkoder.com/spring-boot-pagination-filter-jpa-pageable/
- https://bezkoder.com/react-pagination-material-ui/
### Unit test for /upload
- https://www.baeldung.com/spring-multipart-post-request-test
