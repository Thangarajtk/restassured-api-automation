# REST ASSURED - API Automation Framework

1) Java library (API) for testing RESTful web services.
2) Used to test XML and JSON based web services.
3) Supports GET, POST, PATCH, PUT, DELETE, HEAD, OPTIONS. Can be integrated with testing frameworks like JUnit, TestNG.
4) Rest Assured is implement in Groovy. It uses Groovy under the hood to fetch the JSON object (GPath Expression)

REST:
	- Representation State Transfer is an architectural style sending the representational state of an object at that particular time.
	- Rest API is Stateless (i.e., Server will not store any information about the client)
JSON:
    - Key value pair where key is always a String and Value can be String, number, boolean, JSON Object, JSON Array, null etc.,
    - {} --> JSON Object
    - [] --> JSON Array

Path Param - Used to identify specific resource, it is mandatory.
Query Param - Used to Sort/filter the resources, it is optional.

### Quick Start
	- Java
	- IDE (IntelliJ)
	- Maven (It is required if we need to run from command line)

### Create a local REST API with JSON Server for testing
JSON Server: Used to create our own fake Rest API.
1) Installing JSON Server 
```
npm install -g json-server
```
2) Start JSON Server
```
json-server --watch db.json
```
<b>REFERENCE LINK: </b>
https://medium.com/codingthesmartway-com-blog/create-a-rest-api-with-json-server-36da8680136d

3) Created API will be available in the local server 
```
http://localhost:3000/
```

### Demo Uri's
```
https://reqres.in/
http://ergast.com/mrd/
```

### Validate JSON Schema
1) Create or Get JSON Schema
2) Add JSON Schema file in src/test/resources path
3) Add maven dependency for JSON Schema validator

### Validate XML Schema
1) Create or Get XML Schema (XSD)
2) Add XSD in src/test/resources path

### Authorization
Authorization gives the users permission to access a resource.

### Authentication
Process to prove that you are valid user or not
Supports several authentication schemes - OAuth, digest, certificate, form and preemptive basic authentication.
Basic authentication:
	- Preemptive : Will supply the credentials as header before the server response.
	- Challanged : Will not supply credentials until the server asks for it.

### OAuth
 	- Open Standard for authorization protocol.
 Dependency:
 	1) Rest Assured.
 	2) ScribeJava-APIs
 	3) Developer Account
 NOTE: Developer account is mandatory to work with OAuth authentication.

### Cookie based Authentication
 	- An HTTP cookie (Web cookie, browser cookie) is a small piece of data that a server sends to the user's web browser.
 	Example: Keeping a user logged in.
 	- Uses HTTP cookies to authenticate client requests and maintain session information.
 	- JSESSIONID is a cookie in J2EE web application which is used in session tracking.

### WIREMOCK - Running as standalone process
	- WireMock is a library for stubbing and mocking web services. 
	- It constructs an HTTP server that we could connect to as we would to an actual web service.
	- When a WireMock server is in action, we can set up expectations, call the service, and then verify its behaviors.
	- The WireMock server can be run in its own process, and configured via the Java API, JSON over HTTP or JSON files.
	- Running it through Maven/gradle will start the mock service once the test start running and stops after test completes.

Once you have downloaded the standalone JAR you can run it simply by doing this
```
java -jar wiremock-jre8-standalone-2.33.1.jar
```

### Command line options
`--port`: Set the HTTP port number e.g. java -jar wiremock-jre8-standalone-2.33.1.jar --port 9091

`--https-port`: If specified, enables HTTPS on the supplied port. 
Note: When you specify this parameter, WireMock will still, additionally, bind to an HTTP port (8080 by default). 
So when running multiple WireMock servers you will also need to specify the --port parameter in order to avoid conflicts.

### Different ways of constructing POST request
1. As String
2. From external file (.json file)
3. Read it as String from external file (.json file) and replace values.
4. Using HashMap (for Json Object {} ) and ArrayList (for Json Array []).
5. Using external Json library (JSONObject, JSONArray).