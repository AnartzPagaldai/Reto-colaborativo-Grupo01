package Modelo.Jugador;

import Modelo.Enumeraciones.TipoSueldo;
import Modelo.Equipo.Equipo;
import Modelo.Jugador.Jugador;

import java.sql.Date;

/**
 * Generar la clase ContratoJugador.
 */
public class ContratoJugador {
    private int id;
    private Date fechaInicio;
    private Date fechaFin;
    private double clausula;
    private String dorsal;
    private TipoSueldo tipoSueldo;
    private Jugador jugador;
    private Equipo equipo;

    /**
     * Generar un objeto ContratoJugador vacío.
     */
    public ContratoJugador() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getClausula() {
        return clausula;
    }

    public void setClausula(double clausula) {
        this.clausula = clausula;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public TipoSueldo getTipoSueldo() {
        return tipoSueldo;
    }

    public void setTipoSueldo(TipoSueldo tipoSueldo) {
        this.tipoSueldo = tipoSueldo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
