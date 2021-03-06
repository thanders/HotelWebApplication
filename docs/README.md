## Description

This is a web application for a hotel reservation system.

Related technology: Java EE, JSP, HTML and CSS. Hosted using TomCat.

## Database setup

**Linux**:

Log into MySQL from this repository's root directory (username root) `mysql -u root -p`

Create a database called sse `CREATE database sse;`

Execute the included SQL file to create the webApp's tables `source docs/db/createDatabase.sql;`

## Webserver deployment

This project can be deployed on any webserver. However, we developed it using Tomcat.

### Deploying systemd service

After the project has been deployed on a server, a systemd service can be created in order to start, stop and monitor the embedded Tomcat webserver. This project includes the files listed below to create a systemd service:
- HotelWebApplication/docs/systemd/hotelWebApp.service
- HotelWebApplication/docs/systemd/start_hotelWebApp.sh

On a Linux server each file can be placed in the following locations:

Path for service file:

/lib/systemd/system/hotelWebApp.service

Path for start shell script:

/usr/bin/start_hotelWebApp.sh

Once the service has been setup as described above, the webapp can be started with the following command:

`systemctl start hotelWebApp.service`

### SSL
We have provided a two sample files for deploying our app with SSL.

The `/server` directory contains server.xml and keystore. If you are using Tomcat and want to deploy SSL you should replace your local Tomcat's server.xml configuration file with the one found in `/server/server.xml` You will need to search for "keystoreFile" and replace the path with your local path to `/server/keystore`

Once the webserver is working and SSL has been setup our project can be accessed from:

HTTPS:

https://localhost:8443/MarriotWebApp/

## Project Trello board

This is effectively a diary that documents the contribution of each team member to the project.
<https://trello.com/invite/b/3rONPJFY/b6924afd4c73865357ae565eb06815da/hotelwebapp>

## Java External dependencies

JavaServer Pages Standard Tag Library - javax.servlet.jsp.jstl

JavaServer Pages Standard Tag Library API - javax.servlet.jsp.jstl-api

MySQL Connector/J (the official JDBC driver for MySQL) - mysql-connector-java

## Functionality

The following functionality was implemented:

- Show available rooms: users should be able to view rooms that are available between specific dates and for a certain number of guests.
 
- Guest users can book one or more available rooms. Allow users to  book available rooms as guests. To do so each user should provide his/her personal details (name, surname, address, phone number, email address). When booking a room a user will be required to provide his/her credit card number to terminate the booking.
 
- Guest users can retrieve their past reservations. Guest users should be able to retrieve their reservations and to cancel them within 24 hours from the checkin date. Reservation should provide an indication of the rooms booked, the guest names (if necessary) and the dates for which the room was booked.
 
- Users can register as Starwood members: Users can register by providing their personal details (name, surname, address, phone number, email address). They will the create a username and password that will be used for future logins.
 
- Starwood users  can login and logout from the system.
 
- Show available rooms to Starwood members:  users who have successfully performed a login can book available rooms at discounted prices (10%).
 
- Starwood users can book one or more available rooms. Allow users to  book available rooms as Starwood member. To do so the user will only have to select the credit card that’s/he wants to use for payment.
 
- Starwood members can save their credit card details and also modify their personal details: after the login is performed successfully, Starwood members can save their credit card details (more than a credit card can be saved) and also save their personal details.
 
- Starwood members can visualize an history of their reservations: after the login is performed successfully, Starwood members can visualize their reservations and their status (Active, Cancelled, Inactive). If an active reservation is selected, the user can cancel it.
 
- Starwood members can delete their registration.

## Screenshots

**Reservation - Available rooms**:

<kbd> <img src="assets/resAvailableRooms.png" alt="Reservation - Available rooms" style="height: 75%; width: 75%;"/> </kbd>

**Reservation - Confirmation**:

<kbd> <img src="assets/resConfirmation.png" alt="Reservation - Confirmation" style="height: 75%; width: 75%;"/> </kbd>

