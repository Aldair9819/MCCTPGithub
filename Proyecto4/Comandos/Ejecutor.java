package Proyecto4.Comandos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import Proyecto4.Constantes.CASOCICLO;
import Proyecto4.Constantes.CM;
import Proyecto4.Constantes.OPCOMPARAR;
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
		//*/
		}
	
	private void initLitGlobal(String texto) {
		
		if(texto.contains("=")) {
			acciones.accion(texto);
			String valor = acciones.getValorLiteral(texto.split(" ")[1])+"";
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

		}else if(CM.isComando(texto)){
			switch(texto.split(" ")[0]) {
			case "entero":
				tablaGInt.put(texto.split(" ")[1], 0);
				break;
			case "real":
				tablaGDouble.put(texto.split(" ")[1], 0.0);
				break;
			case "string":
				tablaGTexto.put(texto.split(" ")[1], "");
				break;
			default:
				System.out.println("Error en la declaracion de variables globales");
				break;
			}

		}
		
		else{
			System.out.println("Error en la declaracion de variables globales");
		}
	}

	private void colocarValorEnLiteralG(String nombre,double numero){
		for(Entry<String, Integer> entry: tablaGInt.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				tablaGInt.put(nombre, (int)numero);
				break;
			}
		}

		for(Entry<String, Double> entry: tablaGDouble.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				tablaGDouble.put(nombre, numero);
				break;
			}
		}
		System.out.println("No se encuentra el valor");
	}

	private void colocarValorEnLiteralG(String nombre,String texto){
		for(Entry<String, String> entry: tablaGTexto.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				tablaGTexto.put(nombre, texto);
				break;
			}
		}
		System.out.println("No se encuentra el valor");
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
		
	private void anadirFuncionALaPila(String texto) {
		Stack<String> temporal = new Stack<String>();
		for(Entry<String,Funcion > entry: this.funciones.entrySet()) {
			if(texto.contains(entry.getKey())) {
				temporal.addAll(entry.getValue().getComandos()) ;
				break;
			}
	}
		while(!temporal.isEmpty()) {
			this.programaActual.push(temporal.pop());
		}
	}
	
	/* 
	private boolean isNumero(String texto) {
		for(int i=0;i<10;i++) {
			if((texto.charAt(0)+"").equals(i+"")) {
				return true;
			}
			
		}
		return false;
	}
	*/
	private boolean isFuncion(String texto) {
		if(isNotFunction(texto)) {
			return false;
		}
		for(Entry<String,Funcion > entry: this.funciones.entrySet()) {
			if(texto.contains(entry.getKey())&&texto.contains("(")) {
				return true;
			}
	}
		return false;
	}
	
	private boolean isNotFunction(String texto) {
		if(CASOCICLO.isCASOCICLO(texto)||CM.isComando(texto)||
				OPCOMPARAR.isOPCOMPARAR(texto)) {
			return true;
		}
		return false;
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
