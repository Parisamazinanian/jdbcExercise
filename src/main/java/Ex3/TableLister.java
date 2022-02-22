package Ex3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.DatabaseMetaData;
import static org.postgresql.util.JdbcBlackHole.close;

public class TableLister {
    public static void main(String[] args) throws SQLException {
        System.out.println(connect());
        System.out.println(listTable());

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
    public static List<String> listTable () throws SQLException {
        //create an empty list of tables
        List<String> TableName = new ArrayList<String>();
        //DatabaseMetaData from the Connection object
        DatabaseMetaData dbMetadata = connect().getMetaData();
        ResultSet result= dbMetadata.getTables(null, null, null, new String[]{"TABLE"});
        //iterate through the result and gather the result/values
        while (result.next()) {
            TableName.add(result.getString("TABLE_NAME"));
        }
        //Display the gathered values as the list of tables
        return TableName;
    }
                }