package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    public static Connection databaseConnection;
    private static String Connection_String = "jdbc:oracle:thin:@localhost:1522/XEPDB1";
    private static String usuario = "System";
    private static String contra = "root";


    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Se encontró el Driver exitosamente");
            try {
                databaseConnection = DriverManager.getConnection(Connection_String, usuario, contra);
            } catch (SQLException e) {
                System.out.println("Error al conectarse a la Base de datos");
                System.out.println(e.getErrorCode());
                System.err.format("SQL state: %s \n %s", e.getSQLState(), e.getMessage());
                e.printStackTrace();
            }
        } catch (ClassNotFoundException cnfex) {
            System.out.println("No tienes el driver en tu build path");
            cnfex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return databaseConnection;
    }

}
