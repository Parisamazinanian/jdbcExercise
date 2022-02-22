# Exercise # 1 - Employee Management using JDBC

**Create a Java Application, "EmployeeManagementApp" that does the following tasks**

> **Create "Employees" table with the following details**
 
 | Column Name | Description | Data Type | Is Unique? | Is Nullable?|
 |:--- | :--- | :---: | --- | --- |
 |fname|First Name|Character (30)|No|No|
 |lname|Last Name|Character (30)|No|No|
 |email|Email ID|Character (50)|Yes|No|
 |contact|Phone Number|Integer (15)|No|Yes|

***Hint:*** use CREATE TABLE command.

> **Confirm that "Employees" table has been created correctly. List out the column names with their datatypes**

***Hint:*** 
- Execute a query on the "Employees" table to get all records.
- Inspect the *ResultSetMetaData* from above received *ResultSet* object

> **Insert the following records in the "Employees" table**

|fname|lname|email|
|---|---|---|
|Adam|Falon|adam.falon@dci.com|
|Mary|Gold|mary.gold@dci.com|
|Adam|Currie|adam.currie@dci.com|
|Bryan|Jhonson|bryan.Jhonson@dci.com|
|Prasad|Ritesh|prasad.ritesh@dci.com|
|Mary|Jhonson|mary.jhonson@dci.com|

***Hint:*** 

- Create a *PreparedStatement* object for the *INSERT INTO* command
- Build a batch of queries for each Employee record listed above
- Execute the batch at the end

> **Confirm that all records listed above were inserted into the "Employees" table**

***Hint:*** 

- Use *SELECT* command to find all records from the Employees table
- Confirm that the number of loaded records is equal to the number of records inserted above.

