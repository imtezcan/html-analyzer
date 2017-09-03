# HTML Analyzer

This project is a simple web app to analyze a given HTML page.
Below you can find instructions for installing and using it, as well as details on some design decisions.

### Features

The analyzer displays basic information about a web page's title, HTML version, headings and links.
It also tells whether the page has a login form or not.
 
There is also a link validator, which will start querying every link in the page to see if they are working.
As the requests execute, the link's "status" will change from "false" to "true", or "Reason" field will change to the 
explanation if there was an error.
 
### How to build and run

To build the project, type:
```
gradle build
```
This will compile the project and place a runnable .jar file `htmlanalyzer-0.0.1-SNAPSHOT.jar` under `build/libs` folder.
After build is complete, you can run this .jar with the following command:
```
java -jar build/libs/htmlanalyzer-0.0.1-SNAPSHOT.jar
```

Alternatively, you can use the following command to run the project in place:
 ```
gradle bootRun
```

This will compile and execute the project, which will start a web server on port `8080`. You can use your browser to 
open the main page, presumably `http://localhost:8080` on your local computer. The default port can be changed from
`application.properties`.

#### Running tests

To run the unit tests within the project, simply type:
```
gradle test
```

### Technology
The project uses the following technologies: Kotlin, Spring Boot, Thymeleaf, Gradle, JQuery.

Originally this used to be a Java 8 project but I converted it to Kotlin, just for fun. The main idea was to see how
Kotlin, Spring Boot and Thymeleaf can be used together. It was pretty easy since I needed to do only a few minor fixes 
after auto-conversion. After that, some simple refactorings and slight modifications to tests were all it took.

Let me know if there are any best practices that can be introduced.