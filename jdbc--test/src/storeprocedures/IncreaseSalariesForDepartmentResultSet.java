package storeprocedures;

import java.sql.*;

public class IncreaseSalariesForDepartmentResultSet {
    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        CallableStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3307/demo", "root" , "root");

            // 2. Create a statement
            myStmt = myConn.prepareCall("{call get_employees_for_department(?)}");
            myStmt.setString(1, "Engineering");

            boolean temResultado = myStmt.execute();

            while (temResultado) {
                ResultSet rs = myStmt.getResultSet();

                // Imprime o resultado
                ResultSetMetaData meta = rs.getMetaData();
                int colunas = meta.getColumnCount();

                while (rs.next()) {
                    for (int i = 1; i <= colunas; i++) {
                        System.out.print(meta.getColumnLabel(i) + ": " + rs.getString(i) + "\t");
                    }
                    System.out.println();
                }

                rs.close();

                // Se houver mais resultados (ex: múltiplos SELECTs), continua
                temResultado = myStmt.getMoreResults();
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
