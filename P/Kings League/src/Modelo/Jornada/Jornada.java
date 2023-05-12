package Modelo.Jornada;

import Modelo.Split.Split;

public class Jornada {
    private int id;
    private int numJornada;
    private TipoJornada tipoJornada;
    private Split split;
    public enum TipoJornada{
        Normal,
        PlayOff
    }

    public Jornada() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumJornada() {
        return numJornada;
    }

    public void setNumJornada(int numJornada) {
        this.numJornada = numJornada;
    }

    public TipoJornada getTipoJornada() {
        return tipoJornada;
    }

    public void setTipoJornada(TipoJornada tipoJornada) {
        this.tipoJornada = tipoJornada;
    }

    public Split getSplit() {
        return split;
    }

    public void setSplit(Split split) {
        this.split = split;
    }
}
