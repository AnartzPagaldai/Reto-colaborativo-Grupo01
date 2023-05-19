package Modelo.Enumeraciones;

public enum TipoSueldo {
    DIEZ_MILLONES(10000000),
    DIEZ_MILLONES_MEDIO(10500000),
    QUINCE_MILLONES(15000000),
    VEINTIDOS_MILLONES_MEDIO(22500000);
    private final int valor;
    TipoSueldo(int valor) {
        this.valor = valor;
    }
    public int getValor(){
        return valor;
    }
}
