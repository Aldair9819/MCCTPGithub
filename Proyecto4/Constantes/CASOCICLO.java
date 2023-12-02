package Proyecto4.Constantes;

public enum CASOCICLO {
SI("si"), SINO("sino"),MIENTRAS("mientras");

	private String condicion;
			
	private CASOCICLO(String condicion) {
		this.condicion = condicion;
		}
    public String toString() {
		return this.condicion;
	}
	
    static public boolean isCASOCICLO(String texto) {
		for(int i=0; i<CASOCICLO.values().length;i++) {
			if(texto.equals(CASOCICLO.values()[i].toString()))
				return true;
			}
				return false;
			}
}
