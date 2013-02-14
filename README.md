WUB
===

WUB is a web-based teacher assessment application written in Java that utilizes the Struts framework.

Data is persisted with the help of the hibernate framework.

This project was develeoped as a preliminary diploma work.

WUB stands for "Webbasierte Unterrichts Beurteilung", German for web-based teacher assessment.

Installation (short guide)
==

* Install Tomcat (or another web server that can handle Java servlets and containers).
* Install MySQL (works with 5.0), not tested with other version.
* Build the source (developed in eclipse) and package it as a WAR
* Deploy the WAR to your web server
* Setup a database and run the database initialization script ("Database_Init_Script.sql")
* Configure the database connection to match your settings (edit hibernate.cfg.xml and change server name, database name, user and password)
* Load the application in a browser and create the base data (students, teachers, criteria etc.)

