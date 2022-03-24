# REST ASSURED - API Automation Framework
1) Java library (API) for testing RESTful web services.
2) Used to test XML and JSON based web services.
3) Supports GET, POST, PATCH, PUT, DELETE, HEAD, OPTIONS

### Create a Fake REST API:
JSON Server: Used to create our own fake Rest API.
1) Installing JSON Server: 
```
npm install -g json-server
```
2) Start JSON Server: 
```
json-server --watch db.json
```
REFERENCE LINK: https://medium.com/codingthesmartway-com-blog/create-a-rest-api-with-json-server-36da8680136d
3) Created API will be available in the local server "http://localhost:3000/".

### Demo Url's
https://reqres.in/
http://ergast.com/mrd/

### Authentication
Example : Login with registered account

### Authentication
Process to prove that you are authentic user or not
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
 	
### Important Notes
User requirement: Swagger -> YAML file - What is the API, Request body, whether it is a GET, POST, PATCH, PUT, DELETE
	How is the response will look like
Stored procedure (Store Proc) -> DAS layer will be integrated with Business layer

Apigee -> POST(Client ID, client Secret, grant type) Response -> Token, Access token

### WIREMOCK
WireMock is a library for stubbing and mocking web services. It constructs a HTTP server that we could connect to as we would to an actual web service.

When a WireMock server is in action, we can set up expectations, call the service, and then verify its behaviors.

The WireMock server can be run in its own process, and configured via the Java API, JSON over HTTP or JSON files.

Once you have downloaded the standalone JAR you can run it simply by doing this:
java -jar wiremock-standalone-2.27.2.jar

### Command line options
--port: Set the HTTP port number e.g. java -jar wiremock-standalone-2.27.2.jar --port 9091

--https-port: If specified, enables HTTPS on the supplied port. Note: When you specify this parameter, WireMock will still, additionally, bind to an HTTP port (8080 by default). So when running multiple WireMock servers you will also need to specify the --port parameter in order to avoid conflicts.