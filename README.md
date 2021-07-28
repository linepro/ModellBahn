# ModellBahn

An educational project to learn about REST and Web apps.
Aims to replace those features of Modellbahnverwaltung MoVe (http://www.henningvoosen.de/Site/Downloads/Modellbahnverwaltung.htm) by Henning Voosen that I use with something cross platform (and not obsolete).

The SpringBoot version with the following features (checkout the Jackson branch for the previous version).
What I learned from this - it's easier to add features using Spring but the result is a disk space and memory hog compared to the Jackson version.

What's in here (could be more, but I forget):

# Database

* H2 (in memory for tests : file for runtime)
* H2 Console.
* Flyway migrations
* JPA DDL Annotations
* Validation Annotations
* Custom Annotations
* Parent Child relationships
* Spring Repositories with Custom Repository extensions
* Criteria API Queries (including wild card)
* Paging (Spring and custom)
* Lazy loading with Hibernate Proxy aware consumers
* OpenInView=false
* Entity Graphs to resolve that last one.
* Custom data type converters
* Cycle detection for parent - child relationships

# Spring

* AOP Interceptors
* Application Listener
* Bean Factory Post Processor
* Logback logging
* HTTPS only

# Spring Security

* Authorities secured endpoints
* Basic Auth
* User Registration with email confirm
* Forgot Password with email confirm
* Failed login lockout

# Internationalization

* Bean Validation with multilingual error messages
* Multilingual error and user messages
* I18n by Accept Languages Header or user preference

# REST

* DTOs (I called them Models, get over it)
* Converters (I called them Mappers / Transcribers, get over it)
* HATEOAS links (with some made up relations) with Model processors (see where models came from now?)
* Actuator with selected endpoints
* File upload via multipart messages
* Optional paging
* Standardized error / exception handling
* CSV Import / Export

# Web

* Static content serving (including those uploaded files)
* Webjars serving javascript UI

# OpenApi

* OpenApi documentation endpoint
* Embedded Swagger UI via webjars

# Testing

* JUnit 5
* Mockito Tests
* Spring Data Tests
* Postman / newman integration tests (happy case only)

# Build

Builds as jar with a lib folder (because SpringBoot fat jars don't really work on Windows and are problematic for maven integration tests).
Demonstrates use of maven archiver and manifest in anycase.

* git

* Java 1.8
  Probably not an issue to go higher.
  
* Maven
  3.6.0 as it happens, but no great dependencies
  
* node (for newman)

* newman 
  npm install -g newman

Just pull.

mvn clean install

java -jar modellbahn-xxx.jar