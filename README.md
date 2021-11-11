# ATL Flexible Tasks

## Requirements

* JDK 11

## Running the application

* Open a terminal session in the root of this project
* Run `./gradlew bootRun` on UNIX or `gradlew.bat bootRun` on Windows

## Using the application

* Send a POST request to `localhost:1337`, containing a request body with two fields:
    * drinkId: a string that identifies your drink
    * type: either BEER or WINE, the type of drink you want
