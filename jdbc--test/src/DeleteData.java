import java.sql.*;

public class DeleteData {
    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root" , "root");

            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myStmt.executeUpdate(
                    "DELETE FROM `demo`.`employees` WHERE (`id` = '14');\n");

            myRs = myStmt.executeQuery("select * from employees");


            // 4. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
    }

}
