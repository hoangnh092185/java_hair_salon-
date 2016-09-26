# :file_folder: [Hair_Salon](https://github.com/hoangnh092185/java_hair_salon-) :file_folder:


  __Version 1.0.0: September 23, 2016__
## by [Nhat Hoang](https://github.com/hoangnh092185)

### Description
__*Individual Project for Epicodus Java Programming*__

Create a webpage that have all the elements that meet the client needs listed below.
Test all the class methods with gradle test function before integrating functionality into Sparks.
Include CRUD functionality include for each class in Sparks.
Spark routes fallow RESTful convention.

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

### Setup/Installation
*Clone or download and extract to use, or click [here](https://github.com/hoangnh092185/java_hair_salon-.git) to view.*


### Technologies Used
###### HTML, CSS, Bootstrap, Java, Spark, Sql

### Legal
*Licensed under the GNU General Public License v3.0*

Copyright &copy; 2016 **_Nhat Hoang_**
