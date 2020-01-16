# Testing Device Task

##  Overall Incomplete:
Lost too much time dealing with the Java bindings for FonoApi which seem to me out of date.
Requests pass via `curl` but not through their library with same parameters.

Uploading what is currently done, although extremely limited.

## General ideas
 * Create endpoint for adding devices: 
   * `POST /api/v1/devices` idempotent for when we already have the device
   * endpoint could expect a JSON like `{ "model": "Samsung S9"}`
   * A service for Device related operations will first try to call FonoApi to get phone details,
   then store the device to a local DB.
   * If a FonoApi call fails we could either store with default details or
   reject the add request depending on requirements.
   * We should have a Circuit Breaker "around" every FonoApi call.  
 * Endpoint for booking a device
   * Either `POST /api/v1/devices/{deviceId}/book` or `PATCH /api/v1/devices/{deviceId}/book`
   * Prefer `PATCH` since we're editing a record and we have idempotence by specification
 * Endpoint for retrieving device details
   * `GET /api/v1/devices/{deviceId}`
   * Service would first check local DB and if device details are not present
   we can retry FonoApi request at this stage depending on requirements
   
## Technology choices
  * MongoDB is chosen for implementation speed. 
  An RDB is better (e.x. Postgre) since data is structured.
  * Swagger should be enabled for REST API documentation
  * We can use [Problem Details](https://tools.ietf.org/html/rfc7807) for error handling
  
## Build
  * `./mvnw clean install`

## Run
  * As prerequisite Run MongoDB locally E.X. with docker `docker run -d -p 27017:27017 --name mongo mongo:latest` 
