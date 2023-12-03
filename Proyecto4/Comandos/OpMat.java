package Proyecto4.Comandos;

import java.util.HashMap;
import java.util.Stack;
import java.util.Map.Entry;

import Proyecto4.Constantes.OPERADOR;

public class OpMat {
    private HashMap<String,Integer> tablaInt = new HashMap<>();
	private HashMap<String,Double> tablaDouble = new HashMap<>();
	
	private Stack<Double> pilaNumero = new Stack<Double>();
	private Stack<String> pilaOperador = new Stack<String>();
	
	
	//Inicializa literales
	public void inicializarLiteral(String tipo, String nombre) {
        switch(tipo){
            case "entero":
                tablaInt.put(nombre, 0);
                break;
            case "real":
                tablaDouble.put(nombre, 0.0);
                break;
            default:
                System.out.println("Error en inicializar Literales");
                break;
        }
		}

	public void asignarValorLiteral(String Literales, double valor) { 
		for(Entry<String, Integer> entry: tablaInt.entrySet()) {
			if(entry.getKey().equals(Literales)) {
				tablaInt.put(Literales, (int) valor);
                return;
			}
		}
		
		for(Entry<String, Double> entry: tablaDouble.entrySet()) {
			if(entry.getKey().equals(Literales)) {
				tablaDouble.put(Literales, valor);
                return;
			}
		}
        System.out.println("No se encontro el literal "+Literales);
	}
	
	
	public double buscarLiteral(String nombre) {
		for(Entry<String, Integer> entry: tablaInt.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return entry.getValue();
			}
		}
		
		for(Entry<String, Double> entry: tablaDouble.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return entry.getValue();
			}
		}
		System.out.print(nombre+" no existe.");
		return 0;
	}
	
	public boolean isLiteralExist(String nombre) {
		for(Entry<String, Integer> entry: this.tablaInt.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return true;
			}
		}
		
		for(Entry<String, Double> entry: this.tablaDouble.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return true;
			}
		}
		return false;
	}
	
	public double realizaroperacion(double y, double x, String operador) {
		double valor = 0;
		switch(operador) {
		case "+":
			valor = x+y;
			break;
		case "-":
			valor = x-y;
			break;
		case "*":
			valor = x*y;
			break;
		case "/":
			valor = x/y;
			break;
		case "^":
			valor = Math.pow(x, y);
			break;		
		}		
		return valor;

	}
	

	public void colocarOperadorEnPila(String operador) {
		if(pilaOperador.isEmpty()) {
			pilaOperador.push(operador);
		} else if(OPERADOR.isMayor(operador, pilaOperador.peek())) {
			pilaOperador.push(operador);
		}
		else {
            vaciarPilaOperador();
			pilaOperador.push(operador);
		}
	}

    public void vaciarPilaOperador() {
		while(true) {
			if(pilaOperador.isEmpty()) {
				return;
			}else {
				pilaNumero.push(realizaroperacion(
						pilaNumero.pop(),pilaNumero.pop(),pilaOperador.pop()));
			}
		}
		
	}

    public double retiraNumPila(){
        return pilaNumero.pop();
    }
	
	public void colocarDatoEnPila(String valor) {
		if(OPERADOR.isOperador(valor)) {
			colocarOperadorEnPila(valor);
		}else if(isLiteralExist(valor)) {
			pilaNumero.push(buscarLiteral(valor));
		}
        else {
			System.out.println("No se anadio a la pila. Compruebe el nombre de "+valor);
		}
	}

	public void colocarDatoEnPila(double valor) {
		pilaNumero.push(valor);
	}
	
    //DUDA DE BORRAR
	public HashMap<String,Integer> getTablaInt() {
		return this.tablaInt;
		
	}
	
	public HashMap<String,Double> getTablaDouble() {
		return this.tablaDouble;
		
	}

    //IMPRESIONES. SE PUEDEN IGNORAR    
    public void imprimeValorTope() {
		if(pilaOperador.isEmpty()) {
			System.out.println("Valor tope en operador es nulo");
		}else {
			System.out.println("Valor tope en operador: "+pilaOperador.peek());
		}
		if(pilaNumero.isEmpty()) {
			System.out.println("Valor tope en numero es nulo");
		}else {
			System.out.println("Valor tope en numero es: "+pilaNumero.peek());
		}
		
	}

    public void RecorreDatosTablas() {
		System.out.println("Tabla entera de valores:");
		System.out.println("Enteros:");
		for(Entry<String, Integer> entry: tablaInt.entrySet()) {
			System.out.println(entry.getKey()+" -> "+entry.getValue());
		}
		System.out.println();
		System.out.println("Reales:");
		for(Entry<String, Double> entry: tablaDouble.entrySet()) {
			System.out.println(entry.getKey()+" -> "+entry.getValue());
		}
		System.out.println();
	}
    
}