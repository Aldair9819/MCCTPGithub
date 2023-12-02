package Proyecto4.Constantes;

public enum LITVAR {
ENTERO("entero"), REAL("real"), TEXTO("texto"), IGUAL("=");

		
	private String declaracion;
		
	private LITVAR(String declaracion) {
		this.declaracion = declaracion;
	}
	public String toString() {
		return this.declaracion;
	}
	static public boolean isDeclaracion(String texto) {
		if(OPCOMPARAR.isOPCOMPARAR(texto)) 
			return false;

		for(int i=0; i<LITVAR.values().length;i++) {
			if(texto.equals(LITVAR.values()[i].toString())) 
				return true;
		}
		return false;
				
		}
	
	
}
