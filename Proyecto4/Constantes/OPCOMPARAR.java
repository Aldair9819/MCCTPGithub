package Proyecto4.Constantes;

public enum OPCOMPARAR{
	MAYOR(">"), MAYORIGUAL(">="), MENOR("<"), MENORIGUAL("<="),
	DISTINTO("<>"),IGUALCOMPARA("==");

	
	private String condicion;
		
	private OPCOMPARAR(String condicion) {
		this.condicion = condicion;
	}
	public String toString() {
		return this.condicion;
	}
	static public boolean isOPCOMPARAR(String texto) {
		for(int i=0; i<OPCOMPARAR.values().length;i++) {
			if(texto.equals(OPCOMPARAR.values()[i].toString())) 
				return true;
		}
		return false;
		}

}