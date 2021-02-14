# CPSC 210 Personal Project 

## Restaurant COVID-19 Tracker

**What will the application do**

This application will allow restaurants or other retail establishments to keep track of customers which
have visited their location during certain times, and then cross compare that data with a list of people 
who are confirmed COVID-19 positive. Names which appear on both lists will then be automatically contacted by
email or text message with a warning of potential exposure and to self-isolate. 

*Input data of customers*:
- Name
- Phone Number
- Email
- Time which they visited restaurant

Future improvements on this project will include employee tracking for COVID-19 exposure,
as well as an additional measure of proximity to the COVID-19 positive customer.
Customers seated closer - within 2 tables - would be considered higher risk than those who are seated further away 
to a confirmed positive case. 

This project was of interest to me for 2 reasons. Firstly, I am a pharmacist and finding better ways to mitigate 
COVID-19 transmission is critical to my profession. Secondly, my experience with COVID-19 tracking at restaurants 
thus far has been disappointing, half the time requiring customers to use the same pen to write in a book.

The ultimate goal would be to make this project into a mobile application, and the application would have 
every restaurant in Vancouver as a selectable option. Once a check-in is finished by the customer on their phone, 
a QR code will be generated, which can be scanned by the restaurant to confirm the visit. This allows for 
proximity tracking which is not persistent, but only active whenever someone visits a restaurant. In addition, the 
use would not be limited to COVID-19, but for future pandemic tracking as well. 


## User Stories ##

- As a user, I want to be able to create new customers and add customer information - elements - to each customer
- As a user, I want to be able to store each new customer in a list
- As a user, I want to be able to see the entire list of customers visiting the restaurant each day
- As a user, I want to be able to filter customers in the list based on their check in time
- As a user, I want to be able to see the entire list of customers which meet filter criteria, along with customer info
