package Proyecto4.Comandos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import Proyecto4.Constantes.CM;
import Proyecto4.LecturaDatos.Funcion;
import java.util.Map.Entry;

public class Ejecutor {
    private HashMap<String,Integer> tablaGInt = new HashMap<>();
	private HashMap<String,Double> tablaGDouble = new HashMap<>();
	private HashMap<String,String> tablaGTexto = new HashMap<>();
	private HashMap<String, Funcion > funciones;
	private Stack<String> programaActual = new Stack<String>();
	private String nombrePrograma;
	private InterpreteNoBucle acciones;
	
	public Ejecutor(String nombrePrograma,HashMap<String, Funcion > funciones,ArrayList<String> variables ) {
		this.nombrePrograma = nombrePrograma;
		this.funciones = funciones;
		acciones = new InterpreteNoBucle(funciones);
	
		 
		for(int i = 0;i<variables.size();i++) {
			initLitGlobal(variables.get(i));
		}

		for(Entry<String,Integer > entry: this.tablaGInt.entrySet()) {
			System.out.println(entry.getKey()+"->"+entry.getValue());
		}



		}
	public void inicializar() {
		System.out.println("Inicia programa...\n");
		for(Entry<String,Funcion > entry: this.funciones.entrySet()) {
			if(entry.getKey().equals("inicio")) {
				entry.getValue().main(this.funciones,"");
				break;
			}
			}
			
		
		}

	private void initLitGlobal(String texto) {
		switch(texto.split(" ")[0]) {
			case "entero":
				tablaGInt.put(texto.split(" ")[1], Integer.parseInt(texto.split(" ")[3]));
				break;
			case "real":
				tablaGDouble.put(texto.split(" ")[1], 0.0);
				break;
			case "texto":
				tablaGTexto.put(texto.split(" ")[1], "");
				break;
			default:
				System.out.println("Error en la declaracion de variables globales");
				break;
			}
		if(texto.contains("=")){
			String textoDeclarativo = texto.substring(0,texto.indexOf("=")-1);
			String textoOperacion = texto.substring(texto.split(" ")[0].length()+1, texto.length());
			acciones.accion(textoDeclarativo);
			acciones.accion(textoOperacion);
			String valor ="";
			if(texto.split(" ")[0].equals("texto")){
				valor = acciones.getValorVariableTexto(texto.split(" ")[1])+"";
			}else{
				valor = acciones.getValorVariable(texto.split(" ")[1])+"";
			}
				
			
			switch(texto.split(" ")[0]) {
			case "entero":
				if(valor.contains("."))
					valor = valor.substring(0, valor.indexOf("."));
				tablaGInt.put(texto.split(" ")[1], Integer.parseInt(valor));
				break;
			case "real":
				tablaGDouble.put(texto.split(" ")[1], Double.parseDouble(valor));
				break;
			case "texto":
				tablaGTexto.put(texto.split(" ")[1], valor);
				break;
			default:
				System.out.println("Error en la declaracion de variables globales");
				break;
			}

		}

		}
		



	public void imprimeDatosAlmacenados() {
		System.out.println("Datos almacenados:");
		System.out.println("Nombre del programa:"+this.nombrePrograma);
		System.out.println("Tabla entera de valores:");
		System.out.println("Enteros:");
		for(Entry<String, Integer> entry: tablaGInt.entrySet()) {
			System.out.println(entry.getKey()+" -> "+entry.getValue());
		}
		System.out.println();
		System.out.println("Reales:");
		for(Entry<String, Double> entry: tablaGDouble.entrySet()) {
			System.out.println(entry.getKey()+" -> "+entry.getValue());
		}
		System.out.println();
		System.out.println("Texto:");
		for(Entry<String, String> entry: tablaGTexto.entrySet()) {
			System.out.println(entry.getKey()+" -> "+entry.getValue());
		}
		System.out.println();
		System.out.println("Funciones:");
		for(Entry<String,Funcion > entry: funciones.entrySet()) {
			System.out.println(entry.getKey()+"-->"+entry.getValue().getComandos().size());
		}
		System.out.println();
	}
	
}
