package storeprocedures;

import java.sql.*;

public class IncreaseSalariesForDepartmentInout {
    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        CallableStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root" , "root");

            // 2. Create a statement
            myStmt = myConn.prepareCall("{call greet_the_department(?)}");
            myStmt.registerOutParameter(1, Types.VARCHAR);
            myStmt.setString(1, "Engineering");
            myStmt.execute();
            System.out.println(myStmt.getString(1));
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
