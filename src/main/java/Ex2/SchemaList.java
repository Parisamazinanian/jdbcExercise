package Ex2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.postgresql.util.JdbcBlackHole.close;

public class SchemaList {
    public static void main(String[] args) {
        System.out.println(connect());
        System.out.println(listSchema());

    }

    //creating URL
    public static String jdbcURL = "jdbc:postgresql://localhost:5432/java_heroes";
//Create an instance of Connection using DriverManager API -- Specify the URL
// (with default database), username and password appropriately

    //connecting to the database using DriverManager API
    public static Connection connect () {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbcURL);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    //creating a list of database
    public static List<String> listSchema () {
        // query database names from the pg_database catalog that contains information on databases
        //Lookup into "pg_database" table for the "datname" field.
        String query = "SELECT schema_name FROM information_schema.schemata;";
        //create an empty list of databases
        List<String> schemaName = new ArrayList<String>();
        //iterate through the result and gather the result/values
        Statement stmt = null;
        try {
            stmt = connect().createStatement();
            //create a ResultSet by executing a Statement
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                schemaName.add(result.getString("schema_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(stmt);
        }
        //Display the gathered values as the list of databases
        return schemaName;
    }

}
