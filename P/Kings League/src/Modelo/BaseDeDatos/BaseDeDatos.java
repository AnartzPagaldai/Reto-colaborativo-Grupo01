package Modelo.BaseDeDatos;

import java.sql.*;

public class BaseDeDatos {
    private static Connection con;

    public static void abrirConexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = "jdbc:oracle:thin:@SrvOracle:1521:orcl";
            String user = "eqdaw01";
            String passwd = "eqdaw01";
            con = DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
            System.out.println("Problemas con la base de datos " + e.getMessage() + " " + e.getClass());
        }
    }

    public static void cerrarConexion() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Problemas con la base de datos " + e.getMessage() + " " + e.getClass());
        }
    }

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        BaseDeDatos.con = con;
    }


    public static boolean insert(String insert, Object[] argumentos) {
        try {
            abrirConexion();
            PreparedStatement statement = rellenarStatemet(insert, argumentos);
            int resultado = statement.executeUpdate();
            cerrarConexion();
            return resultado != 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static PreparedStatement rellenarStatemet(String consulta, Object[] argumentos) throws SQLException {
        abrirConexion();
        PreparedStatement statement = con.prepareStatement(consulta);
        for (int i = 0; i < argumentos.length; i++) {
            if (argumentos[i] instanceof String) {
                statement.setString(i + 1, (String) argumentos[i]);
            } else if (argumentos[i] instanceof Integer) { // todo quiza hay que añadir mas tipos de datos
                statement.setInt(i + 1, (Integer) argumentos[i]);
            } else if (argumentos[i] instanceof Date) {
                statement.setDate(i + 1, (Date) argumentos[i]);
            }
        }
        return statement;
    }

    public static void cosultaObjeto(ResultSetObjeto objeto, String consulta, Object[] argumentos) {
        try {
            PreparedStatement statement = rellenarStatemet(consulta, argumentos);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                objeto.resultSetObjeto(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            objeto = null;
        }
    }
}

