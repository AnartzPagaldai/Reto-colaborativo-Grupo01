package Modelo.Equipo;

import Modelo.BaseDeDatos.ResultSetObjeto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Equipo implements ResultSetObjeto {
    private int id;
    private String nombre;
    private double presupuestoAnual;

    public Equipo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPresupuestoAnual() {
        return presupuestoAnual;
    }

    public void setPresupuestoAnual(double presupuestoAnual) {
        this.presupuestoAnual = presupuestoAnual;
    }

    private int contarJugadores() {

        return 0;
    }

    private void ficharJugador() {

    }

    private void venderJugador() {

    }

    public void resultSetObjeto(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("id");
        nombre = resultSet.getString("nombre");
        presupuestoAnual = resultSet.getDouble("presupuesto_anual");
    }

}
