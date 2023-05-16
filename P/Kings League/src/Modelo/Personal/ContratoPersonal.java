package Modelo.Personal;

import Modelo.Enumeraciones.TipoSueldo;
import Modelo.Equipo.Equipo;
import Modelo.Personal.Personal;

import java.sql.Date;

public class ContratoPersonal {
    private int id;
    private Date fechaInicio;
    private Date fechaFin;
    private TipoSueldo sueldo;
    private Personal personal;
    private Equipo equipo;

    public ContratoPersonal() {
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

    public TipoSueldo getSueldo() {
        return sueldo;
    }

    public void setSueldo(TipoSueldo sueldo) {
        this.sueldo = sueldo;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
