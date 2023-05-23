package Modelo.Partido;

import Modelo.Equipo.Equipo;
import Modelo.Jornada.Jornada;

import java.sql.Date;

/**
 * Generar la clase Partido.
 */
public class Partido {
    private int id;
    private int golesEquipo1;
    private int golesEquipo2;
    private Date fecha;
    private String lugar;
    private Equipo equipo1;
    private Equipo equipo2;
    private Jornada jornada;

    /**
     * Generar un objeto Partido vacío.
     */
    public Partido() {
    }

    /**
     * Generar un objeto Partido.
     *
     * @param fecha Date
     * @param lugar String
     * @param equipo1 Equipo
     * @param equipo2 Equipo
     */
    public Partido(Date fecha, String lugar, Equipo equipo1, Equipo equipo2) {
        this.fecha = fecha;
        this.lugar = lugar;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(int golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo2(int golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }
}
