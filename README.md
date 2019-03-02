

# flowers micro services

This is a Apache Maven project consisting of a collection of REST json micro service modules sub-projects. By nature, micro-services are best when light weight with a minimal footprint. It naturally follows therefore that these micro-services are bootstrap implemented using Spring Boot, and data is persisted to a NoSQL database (MongoDB).
The Hystrix API (Netflix) was used to implement the exception isolation strategy, circuit breaker, and fall-back software design patterns (specific to micro-services) to prevent micro service failures from cascading.
Build process and library version management was configured to use the Apache Maven build tool. The service is designed to interact with a MongoDB key/value model with associations between various types of user defined entities. 
As a rule, each micro service adheres to the standard C-R-U-D operations on the narrow set of entity and data each service provides. Other operations have also been implemented that manipulate more complex derived data structures.

# technology stack

The technology stack includes Apache Maven, Java 1.8, Stream API, Method references, Functions, Functional Interfaces, Lambda expressions and other more recent tools.

Performance was a key concern in terms of throughput and resource (cpu|memory) management. As a result the following were carefully considered:

* Special care was made to ensure that looping and other expensive operations were limited to a maximum O(log n) performance.
* Stream API was used to perform bulk operations on various Collections objects.

Apache Maven was used to streamline and manage jar file versions as well as automate the various JUnit tests that were integrated into the compile and packaging process. Security being an implicit and obvious design time concern was built in from day one. Various OWASP suggested code features were implemented. All communication was done over Transport Layer Security (TLS).
Various software architectural design patterns were used. These include Fa�ade & Null Objects.
Exception tunnelling (Lambda Expressions) was used to restore the conciseness of code when the lambda expression throws an exception.
The generalized target-type inference feature of the Java programming language was used extensively to simplify passing various types of parameter objects as well as reduce the effect of generic types handling complexities.
Stream API was used preferably in place of classic looping control structures.
Special attention was made to ensure the proper documentation of key functionalities, as well as the code being javadoc compliant.

## technologies used
* Java 8
* Spring Boot 4
* Hystrix
* Maven 3
* MongoDB 
* Aspects 
* Eureka
* Zuul
* Loggly
* MongoDB

# design documentation
