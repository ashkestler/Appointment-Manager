==========================================
            Appointment Manager
==========================================
Version 1.0     December 1, 2021
Ashley Kestler  akestle@wgu.edu
==========================================

-Project Overview-
This project is a graphical application built with Java using JavaFX. The purpose of this project is 
an Appointment Manager that a business would need in order to schedule appointments with customers.

It contains the features to:

	• Verify login credentials stored in a MySQL database
	• Translate the Login Page between English and French based on a user's system locale
	• Add, Modify, and Delete Customers and Appointments into MySQL
	• Translate appointment times into current user's system timezone
	• Generate reports for users by calling queries to the database
	• View the appointments upcoming in the current week and month
	• Generate an Alert if an appointment is scheduled within 15 minutes of a user logging in
	• Logging successful and failed login attempts into "login_activity.txt"


-IDE-
	• IntelliJ IDEA Community 2021.1.1

-Java JDK version-
	• jdk-11.0.11

-JavaFX version-
	• openjfx-11.0.2

-MySQL Connector driver version-
	• mysql-connector-java-8.0.25

-How to Build-
This project requires the external libraries of JavaFX and MySQL JDBC, so it cannot (easily) be compiled via the command line.

	• Get Newest Release
	- Download the latest release from the releases tab.

	• Import Project into IDE
	- Import the project into an IDE of your choice (further instructions will be given for IntelliJ IDEA 2021.1.1)
	- In IntelliJ IDEA. File -> Open -> (Select the .zip file you downloaded) -> OK

	• Run Project	
	- Click the Green Play button at the top of the IDE to run the project. Use the following test credentials to login:
		• Username: test
		• Password: test

-Additional Report-
	• For my third report in section A3f, I chose to generate a list of appointments by customer.