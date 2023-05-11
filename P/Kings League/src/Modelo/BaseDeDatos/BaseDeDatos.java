package Modelo.BaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDeDatos {
    private static Connection con;

    public static void abrirConexion(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url= "jdbc:oracle:thin:@SrvOracle:1521:orcl";
            String user= "eqdaw01";
            String passwd= "eqdaw01";
            con= DriverManager.getConnection (url, user,passwd);
        }
        catch(Exception e){
            System.out.println("Problemas con la base de datos " +e.getMessage() +" "+ e.getClass() );
        }
    }

    public static void cerrarConexion(){
        try{
            con.close ();
        }
        catch(Exception e) {
            System.out.println("Problemas con la base de datos " +e.getMessage() +" "+ e.getClass() );
        }
    }
    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        BaseDeDatos.con = con;
    }
}

