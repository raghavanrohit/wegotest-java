# Wego Test Java Backend App #

### Overview ###

* This app serves a backend app to the Wego Test Ruby on Rails Frontend App
* The Ruby on Rails frontend app can make RESTful requests to the endpoint exposed by this app
* This app is hosted on Heroku --> https://wegotest-java.heroku.com
* The endpoint(s) exposed by this app are
  * findAirlines - POST request, produces XML

### Cloning the app ###

```
git clone https://github.com/raghavanrohit/wegotest-java
```

### Building the app ###

```
mvn clean install -DskipTests=true
```

### Building and executing test cases ###

```
mvn clean install
```

#### Tests executed ####

* AirlineResourceTest.testFindAirlinesWithSuccessfulResponse() -> verifies successful response
* AirlineResourceTest.testFindAirlinesWithNoDataFoundResponse() -> verifies no data found response
* AirlineResourceTest.testFindAirlinesThrowBadRequestResponseForEmptyToParam() -> verifies a bad request response for an empty 'to' request parameter
* AirlineResourceTest.testFindAirlinesThrowBadRequestResponseForEmptyFromParam() -> verifies a bad request response for an empty 'from' request parameter
* AirlineResourceTest.testFindAirlinesThrowBadRequestResponseForEmptyRequest() -> verifies a bad request response for an empty request

### Running the app ###

```
java -cp "target\classes;target\dependency\*" com.wego.airlines.AirlineApplication server
```

* All requests served by the app can be viewed on the console
* To verify that the app has started successfully, type http://localhost:8080 in your favourite browser. It should display an empty page