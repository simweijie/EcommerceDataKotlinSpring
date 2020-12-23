# EcommerceDataKotlinSpring

### Tech Stack
1) Java 11
2) Kotlin
2) Node (v14.15.2)
3) MySQL (CE v8.0.22.0)

## Setup
1) Create an empty folder and clone both EcommerceDataReact & EcommerceDataKotlinSpring 
2) Register an account and download data.csv file from https://www.kaggle.com/carrie1/ecommerce-data/
3) Ensure that the MySQL server is running with following created:
```
Schema: testdb
Username: root
Password: 123456
```



## Starting Up
1) FrontEnd: From the root folder of EcommerceDataReact enter the following into the terminal:
```
yarn start
```
2) BackEnd: From the root folder of EcommerceDataKotlinPsring enter the following into the terminal:
```
gradlew bootrun
```
3) Start http://localhost:3000
  
## To Fix
- [ ] /upload unit test
- [ ] Improve progress bar and user feedback on progress
- [ ] Expand search functionality
- [ ] Improve insertion time into DB
- [ ] Establish compound key to prevent duplicate entries into DB

## References
1) Upload
- https://bezkoder.com/react-file-upload-spring-boot/
- https://bezkoder.com/spring-boot-upload-csv-file/
2) Paginate
- https://bezkoder.com/spring-boot-pagination-filter-jpa-pageable/
- https://bezkoder.com/react-pagination-material-ui/
3) Unit test for /upload
- https://www.baeldung.com/spring-multipart-post-request-test
