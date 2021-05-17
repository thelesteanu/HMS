#HMS - Hotel Management System

This is a mockup project for the Hotel Management System internship application. It contains a Spring Boot basic structure and a thymeleaf structure with some basic screens.


#Technologies used:
* Spring Boot
* Thymeleaf 
* Jquery  v 2.1.4
* Bootstrap v 3.3.4

_This application should not be used as a whole template when developing the Hotel Management System application_

# H2 console
* Access http://localhost:8080/h2-console/
* Username: sa
* Password: password

* Please make sure to modify application.properties to the location of the project.


#The Application structure:
```

+-hms
    |-- src                                       # Source code
    |   |-- main
    |   |   |-- java                              # Spring Boot Java classes
    |   |   |   `-- com
    |   |   |       `-- hele
    |   |   |           |-- controller            # Controllers
    |   |   |           |-- model                 # Front end models
    |   |   |           `-- HmsApplication.java
    |   |   `-- resources
    |   |       |-- static
    |   |       |   |-- css                      
    |   |       |   |   `-- hms.css               # Application's CSS file
    |   |       |   |-- images                    # Application's images
    |   |       |   |   |-- action                # Images for actions
    |   |       |   |   |-- carrousel             # Images for carrousel
    |   |       |   |   |-- error                 # Images for error screen
    |   |       |   |   |   |-- errorLegend.png
    |   |       |   |   |   `-- legend.jpg
    |   |       |   `-- js                        # Javascript resouces-> Bootstrap
    |   |       |       `-- bootstrap.min.js
    |   |       |-- templates
    |   |       |   |-- common                    # Common thymeleaf templates
    |   |       |   |-- fragments                 # Reusable thymeleaf templates (forms)
    |   |       |   |-- hotelManagement           # Hotel Management pages
    |   |       |   |-- userManagement            # User Management pages
    |   |       |   |-- error.html
    |   |       |   `-- index.html     
    |   |       `-- application.properties
    |   `-- test                                  # Front end test classes
    |       `-- java
    |           `-- com
    |               `-- hele
    |                   `-- HmsApplicationTests.java
    |-- target                                    # targets

```