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
		texto = getPalabraInicial(texto);
		for(int i=0; i<CASOCICLO.values().length;i++) {
			if(texto.equals(CASOCICLO.values()[i].toString()))
				return true;
			}
		
				return false;
			}

	static public boolean isCASO(String texto){
		texto = getPalabraInicial(texto);
		return texto.equals(CASOCICLO.SI.toString()) || texto.equals(CASOCICLO.SINO.toString());
	}

	static public boolean isCICLO(String texto){
		texto = getPalabraInicial(texto);
		return texto.equals(CASOCICLO.MIENTRAS.toString());
	}

	static private String getPalabraInicial(String texto){
		if(texto.contains("("))
			texto = texto.substring(0, texto.indexOf("("));
		texto = texto.replace("{", "");
		if(texto.contains(" ")){
			texto = texto.substring(0, texto.indexOf(" "));
		}

		return texto;
	}
}
