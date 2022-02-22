package Ex4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.postgresql.util.JdbcBlackHole.close;

public class EmployeeManagement {
    public static String jdbcURL = "jdbc:postgresql://localhost:5432/jdbpractice";

    public static void main(String[] args) {
        tableCreation();
        showTable();
        List<Employee> employeeInsetList = new ArrayList();
        employeeInsetList.add(new Employee("Adam", "Falon", "adam.falon@dci.com"));
        employeeInsetList.add(new Employee("Mary", "Gold", "mary.gold@dci.com"));
        employeeInsetList.add(new Employee("Adam", "Currie", "adam.currie@dci.com"));
        employeeInsetList.add(new Employee("Bryan", "Jhonson", "bryan.Jhonson@dci.com"));
        employeeInsetList.add(new Employee("Prasad", "Ritesh", "prasad.ritesh@dci.com"));
        employeeInsetList.add(new Employee("Mary", "Jhonson", "mary.jhonson@dci.com"));
        insertValues(employeeInsetList);
        if (checkRecords().size() == employeeInsetList.size()) {
            System.out.println("loaded Employee list is same as that inserted");
        } else {
            System.out.println("Some difference in loaded Employee list than that inserted");
        }
    }



        // Open a connection

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


    //creating the table
    public static void tableCreation () {
            try (Statement stmt = connect().createStatement();
            ) {
                String query = "CREATE TABLE Employee " +
                        "(fname VARCHAR(30) not null, " +
                        " lname VARCHAR(30) not null, " +
                        " email VARCHAR(50) not null UNIQUE, " +
                        " contact NUMERIC(15,0))";

                stmt.executeUpdate(query);
                System.out.println("Created table in given database...");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    //showing the created table
        public static void showTable(){
            // Query to retrieve records
        String query = "SELECT * FROM Employee";
            // Create statement so that we can execute
            // all of our queries
        Statement stmt=null;
        try{
            stmt=connect().createStatement();
            //Executing the query
            ResultSet resultSet = stmt.executeQuery(query);
            // Get the ResultSetMetaData object
            ResultSetMetaData resultSetMetaData
                    = resultSet.getMetaData();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                System.out.println("ColumnName = " + resultSetMetaData.getColumnName(i));
                System.out.println("ColumnLabel = " + resultSetMetaData.getColumnLabel(i) + "   ");
                System.out.println("ColumnDisplaySize = " + resultSetMetaData.getColumnDisplaySize(i) + "   ");
                System.out.println("ColumnTypeName = " + resultSetMetaData.getColumnTypeName(i) + "   ");
                System.out.println("------------------");
            }
        }
        // in case of any SQL exceptions
        catch (SQLException s) {
            System.out.println(
                    "SQL statement is not executed!");
        }
    }
    //insert values to the table
    public static void insertValues(List<Employee> employees) {
        String query = "INSERT INTO Employee VALUES  (?, ?, ?);";
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn=connect();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(query);

            for (Employee emp : employees) {
                stmt.setString(1, emp.getFname());
                stmt.setString(2, emp.getLname());
                stmt.setString(3, emp.getEmail());
                stmt.addBatch();
            }

            stmt.executeBatch();
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
            }
        } finally {
            close(stmt);
        }
    }

    public static List<Employee> checkRecords(){
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employee;";
        Statement stmt=null;
        Connection conn = null;
        try{conn=connect();
            stmt=conn.createStatement();
            ResultSet resultSet= stmt.executeQuery(query);

            while(resultSet.next()){
                Employee employee=new Employee();
                employee.setFname(resultSet.getString(1));
                employee.setLname(resultSet.getString(2));
                employee.setEmail(resultSet.getString(3));
                employees.add(employee);
            }
        } catch (Exception e) {
        } finally {
            close(stmt);
        }
        return employees;
    }


}

