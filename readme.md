To prepare application for PROD you need: 
1) Insert postgres credentails into: 
https://github.com/veterr/guavapay-test/blob/main/auth/src/main/resources/application.yml
https://github.com/veterr/guavapay-test/blob/main/core/src/main/resources/application.properties
https://github.com/veterr/guavapay-test/blob/main/delivery/src/main/resources/application.yml
block spring: datasource:
2) Start application - it will create necessary tables
3) Insert into table "roles" records: "ROLE_USER", "ROLE_COURIER", "ROLE_ADMIN" - generate UUID id-s. 

To start in docker compose you need to - 
1) "clean install" core
"clean install" auth
"clean install" delivery
"clean install" gateway
2) Put jars: 
auth/target/auth-1.0-SNAPSHOT.jar         -> assembly/auth/auth-1.0-SNAPSHOT.jar
delivery/target/delivery-1.0-SNAPSHOT.jar -> assembly/delivery/delivery-1.0-SNAPSHOT.jar
gateway/target/gateway-1.0-SNAPSHOT.jar   -> assembly/gateway/gateway-1.0-SNAPSHOT.jar
3) Go to /assembly/ folder and do "docker compose up"

I have not yet tested docker-compose assembly, only had time to write the docker-compose descriptor 
and create assembly structure. 
I tested when started locally - 
1) authentication - create user, and signin as user with jwt
2) create order with jwt - success
3) request to url with wrong role - 403 mistake - no permissions
4) gateway redirects by url pattern from "gateway" to "auth" and "delivery" rest api-s

I have experience but didn't have time: 
1) add liquibase script and instructions to launch it
2) add swagger descriptions


