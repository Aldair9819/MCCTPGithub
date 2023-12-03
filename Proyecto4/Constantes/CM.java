package Proyecto4.Constantes;

public enum CM {
LEER("leer"),  IMPRIME("imprime"),ENTERO("entero"), REAL("real"), TEXTO("texto"), IGUAL("=");
	
	private String comando;
	
	private CM(String comando) {
		this.comando = comando;
	}
	public String toString() {
		return this.comando;
	}
	
	static public boolean isComando(String texto) {
		for(int i=0; i<CM.values().length;i++) {
			if(texto.equals(CM.values()[i].toString())) 
				return true;
			}
				return false;
			}
			
	}

