


## stories

* As a salon employee, I need to be able to see a list of all our stylists.
* As an employee, I need to be able to select a stylist, see their details, and see a * list of all clients that belong to that stylist.
* As an employee, I need to add new stylists to our system when they are hired.
* As an employee, I need to be able to add new clients to a specific stylist.
* As an employee, I need to be able to update a stylist's details.
* As an employee, I need to be able to update a client's details.
* As an employee, I need to be able to delete a stylist if they're no longer employed here.
* As an employee, I need to be able to delete a client if they no longer visit our salon.


## Setup

In PSQL:
* CREATE DATABASE hair_salon;
* CREATE TABLE clients (id serial PRIMAY KEY, name varchar, stylistId int);
* CREATE TABLE stylists (id serial PRIMARY KEY, name varchar, description varchar, experience varchar);
* CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;
