package Proyecto03.enums;

public enum ESTADOCASILLA {
    DESBLOQUEADO("*"), DESCONOCIDO("?"), BANDERA("M");
    
    private String simbolo;
    private ESTADOCASILLA(String simbolo){
        this.simbolo = simbolo;
    }

    public String toString(){
        return this.simbolo;
    }
    
}
