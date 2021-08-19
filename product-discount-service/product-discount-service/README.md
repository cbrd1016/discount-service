# Discount Service
The Discount service stores discounts and determines the best discount for a given set of items. The discounts are categorized: by item type, by total cost of items, by count of a particular item

## Building
Use Maven to build the service from the command line by running the following command at the root directory
of the project:

`mvn clean package`

Once the build is complete, the runtime application will be available in the `product-discount-service/target` directory.

## Running
### As SpringBoot jar
If you want to run the service locally, simply run the following at the command line from the root directory
of the project:

`cd product-discount-service && mvn spring-boot:run`

Once the service is running, you may access the Swagger UI API [documentation](http://localhost:8080/swagger-ui.html)
page and interact with the service directly from that page.  Alternatively you may submit requests from Postman, 
or even command line using curl (examples are self contained in the Swagger documentation! 😃)

You can also view health and metrics through actuator functionality:
- [Health](http://localhost:8080/actuator/health)
- [Info](http://localhost:8080/actuator/info)

```
- Verify logs
- [Swagger UI](http://localhost:8080/discount/api/swagger-ui.html)
- [Health check](http://localhost:8080/discount/api/actuator/health)