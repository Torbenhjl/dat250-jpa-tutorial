Hand in report for assignment 4

Questions:

1. Explain the used database and how/when it runs

In this project, I am using the H2 database, which is an in-memory or file-based database commonly used in Java development for testing and lightweight applications.
The H2 database is embedded within the application, meaning it starts when the application starts and shuts down when the application ends.

How it runs: 
The H2 database is managed by Hibernate. When you run your Java application, Hibernate automatically handles the lifecycle of the database based on the configuration defined in the presistance.xml.
It uses the JDBC URL provided in the configuration to either connect to a file-based H2 database or create an in-memory database that exists only during the runtime of your application.

When it runs:
The database is initialized when you call: Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
This starts the database connection and applies necessary schema changes, such as creating tables for all the enteties. Then when you call em.getTransaction().begin() , Hibernate interacts with the database and applies operations like inserts, updates and queries.
When the transaction is committed, the changes are persisted to the file-based H2 database.

2. SQl for the customer table:

CREATE TABLE Customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);


3. Inspect the database tables:
   I used the h2 console to view the database tables:
![image](https://github.com/user-attachments/assets/bc5da0f6-71cc-4e08-b746-7517f83e3e24)

The tables that were created were kind of exptected. Every entety has a table and two more join tables were created. BANK_CREDITCARD and CUSTOMER_ADDRESS

All tables:

The CREDITCARD table stores individual credit card data, linked to both a customer and a bank.
The BANK_CREDITCARD join table tracks which credit cards are owned by which banks.
The CUSTOMER_ADDRESS join table tracks the relationship between customers and their addresses.
The ADDRESS table stores address information, while the PINCODE table stores the pincode used for credit cards.
CUSTOMER and BANK store information about individuals and financial institutions, respectively.
![image](https://github.com/user-attachments/assets/a31bbe2f-6f08-45cd-b0a2-028c5124d2a2)


For techincal problems i had one in particular regarding the test class. ![image](https://github.com/user-attachments/assets/933e7b86-01c5-44e3-9e59-fabb9d3dce73)
As seen in the image the test failed even though expected and actual were identical. The problem was that in the code provided the Set of addresses, creditcards and so on were stored as a Collection so I asumed that was the intended data structure for storing those opbjects but the test was comparing it to Sets.
When i changes from collection to set it worked.
