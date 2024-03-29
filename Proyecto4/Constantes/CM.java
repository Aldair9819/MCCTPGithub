package Proyecto4.Constantes;

public enum CM {
LEER("leer"),  IMPRIME("imprime"),IMPRIMES("imprimeS"),
ENTERO("entero"), REAL("real"), TEXTO("texto"), 
IGUAL("=");
	
	private String comando;
	
	private CM(String comando) {
		this.comando = comando;
	}
	public String toString() {
		return this.comando;
	}
	
	static public boolean isComando(String texto) {
		for(int i=0; i<CM.values().length;i++) {
			
			try{
				if(texto.split(" ")[0].equals(CM.values()[i].toString())) 
				return true;
			}catch(ArrayIndexOutOfBoundsException e) {
				return false;
			}
			}

			if(texto.contains(CM.IGUAL.toString())&& !OPCOMPARAR.isOPCOMPARAR(texto))
			return true;

			return false;	
	}

}

