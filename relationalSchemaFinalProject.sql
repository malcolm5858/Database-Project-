/*
Malcolm Machesky
Pawel Siglowy
*/ /*
Tables
*/
CREATE TABLE Inventory(id VARCHAR(10),
                          cost Float(10),
                               LeadTime Varchar(30),
                                        CategoryType VARCHAR(30), Number INTEGER(30),
                                                                         primary key (id),
                       FOREIGN KEY (id) REFERENCES Model(ModelNumber));


CREATE Table Model(ModelNumber INTEGER, SalePrice INTEGER, Primary KEY (ModelNumber));


Create Table User(ID INTEGER, Priviledge Varchar(30), LoginTime VARCHAR(30), LogoutTime VARCHAR(30), PRIMARY Key (ID));


Create Table Customer(CustomerID INTEGER, FirstName VARCHAR(30), LastName VARCHAR(30), PRIMARY Key (CustomerID));


CREATE TABLE Order(OrderNumber INTEGER, CustomerID INTEGER, EmployeeID INTEGER, Model INTEGER, SaleValue INTEGER, PRIMARY KEY(OrderNumber),
                   FOREIGN KEY (Model) REFERENCES Model(ModelNumber),
                   FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID),
                   FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID));


Create Table Employee(EmployeeID INTEGER, FirstName Varchar(30), Lastname Varchar(30), SSN INTEGER, Salary FLOAT(4), PayType Varchar(30), jobType Varchar(30), PRIMARY KEY (EmployeeID));

/*
Index's
*/
CREATE INDEX Category ON Inventory (CategoryType);


CREATE INDEX Worker ON Employee (EmployeeID, FirstName, LastName);


CREATE INDEX ModelType ON Order(Model, CustomerID);

/*
Views
*/
CREATE VIEW employeeInfo(FirstName, LastName, JobType) as
SELECT FirstName,
       LastName,
       JobType
from Employee /*
User's
*/
CREATE USER ADMIN;

GRANT ALL PRIVILEGES ON Employee,
                        Model,
                        Inventory,
Order,
                        Customer TO ADMIN;


CREATE USER SALE GRANT ALL PRIVILEGES on
Order to SALE;

GRANT
SELECT,
UPDATE on Customer TO SALE;


CREATE USER ENGINEER;

GRANT
SELECT,
UPDATE on Model,
          Inventory to ENGINEER;

Grant
SELECT om employeeInfo to ENGINEER;


CREATE USER HR GRANT
SELECT,
UPDATE ON Employee to HR;