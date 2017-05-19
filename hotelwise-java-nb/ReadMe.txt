Introduction
============

The Sea Jewel Resort (a local tourist resort) wants to change their current booking system (Excel Spreadsheets) to an application. As the Sea Jewel is a popular tourist resort, they require an efficient and easy to use system to register and manage multiple bookings. 

Business Functions
==================

Register Customer
-----------------

Customer is able to register, supplying first name, last name, email, address, password and date of birth. After registration, customer can log into the application to book a room using their email and password. Customer's email is validated and password is verified.

Book hotel room
---------------

Customers are able to book a hotel room (Single, Single with Ocean View, Double or Family - more detail below) from the seeded hotel room data (see below). The check in and check out dates need to be validated to ensure that they are after the current date, different from each other and that the check out date is after the check in date. If the hotel room is not available (due to being previously booked or having an 'Unavailable' status), the customer will be unable to book that roomtype during the specified dates.


Staff Update booking
--------------------
Staff is able to update bookings through staff manage bookings. This enables Staff to edit aspects of a booking (customer email, check in date, check out date, room type and number of guests staying) and update them in the file.


Test Data
=========

Staff
-----
Staff username: staff
Staff password: asdf

Customer
--------
Customer id: 1
Customer email: jsu@email.com
Customer password: asdf

Room Types
----------
Single Room:
	Name: Single
	Price: 25.0

Single with Ocean View Room:
	Name: Single with Ocean View
	Price: 50.0
	
Double Room:
	Name: Double
	Price: 75.0
	
Family Room:
	Name: Family
	Price: 100.0

Hotel Rooms
-----------
Single Rooms: 101 (Available), 102 (Available)
Double Rooms: 201 (Available), 202 (Available), 301 (Available)
Single with Ocean View Rooms: 302 (Unavailable) 
Family Rooms: 401 (Available)


App Data
========

File Location Mac / Windows
---------------------------
In Mac:
	/tmp/appdata.ser
In Windows:
	/temp/appdata.ser

If the file is not at this address it will create a new file using the seeded data.