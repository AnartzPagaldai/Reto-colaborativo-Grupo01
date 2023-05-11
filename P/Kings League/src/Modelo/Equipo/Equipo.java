package Modelo.Equipo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Equipo {
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
    private int contarJugadores(){

        return 0;
    }
    private void ficharJugador(){

    }
    private void venderJugador(){

    }

    public void resutlSetObjeto(ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
        } catch (SQLException e) {
            System.out.println("no hay id");
        }
        try {
            nombre = resultSet.getString("nombre");
            presupuestoAnual = resultSet.getDouble("presupuesto_anual");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }






    }
}
