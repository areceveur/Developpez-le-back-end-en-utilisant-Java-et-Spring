# Project 3 : ChâTop - Seasonal rental portal

Welcome to the ChâTop project ! This portal is designed to put prospective tenants and homeowners in touch with each other
for seasonal rentals on the Basque coast, and later throughout France.

## Description

ChâTop is a platform that enable users (renters and homeowners) to authenticate themselves and to have access to rental ads.
Landlords can publish ads and tenants can look for rentals.

The application is implemented with to Angular for the front-end part and with Java for the back-end part,
the pictures are stored on the server and their URL is saved in the database.

## Installation

### Prerequisite

- Java 11 or 17
- Spring Boot 3
- MySQL
- Node.js et npm (for the Angular project)

###  Installation steps

1. Clone the repository

> git clone https://github.com/areceveur/Developpez-le-back-end-en-utilisant-Java-et-Spring.git

Go inside folder:

> cd Developpez-le-back-end-en-utilisant-Java-et-Spring

2. Creation of the database

> CREATE DATABASE DBChatop;

Update the file `application.properties` with the connexion information of the database.

3. Creation of the tables

Execute the script in the file `ressources/sql/script.sql`

4. Launch the back-end

Run the `WebappApplication` file

5. Launch the front-end

Install dependencies:

> npm install

Launch Front-end:

> npm run start

## Use

After the installation and the launch of the project, you can access to the Angular application with the address
http://localhost:4200. You have to register first to have access to the rental ads or to create one.
To create an ad, click on the button `create` and complete the form.
Careful, the chosen image has to be a small one (less than 1 Mo).

## Security

- Use of Spring Security and JWT to secure the routes
- Encryption of the passwords in the database

## Documentation of the API

The Swagger documentation of the API is available at the following URL after launch of the server SpringBoot:
http://localhost:8080/swagger-ui/index.html#/
