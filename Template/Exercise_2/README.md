# Exercise # 1 - List Schemas in a PostgreqSQL Database

> **Create a Java Application, "SchemaLister" that connects to the PostgreqSQL database server on localhost and list out names of all Schemas**

***Hint:*** 
- Download JDBC Driver library for PostgreqSQL
-- Search on internet for the above.
- Add this to the classpath
- Create an instance of *Connection* using *DriverManager* API
-- Specify the URL with specified database, username and password appropriately
- Lookup into "information_schema.schemata" view for the "schema_name" field.
- Iterate through the result and gather the result/values 
- Display the gathered values as the list of schemas

