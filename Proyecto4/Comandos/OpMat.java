package Proyecto4.Comandos;

import java.util.HashMap;
import java.util.Stack;
import java.util.Map.Entry;

import Proyecto4.Constantes.OPERADOR;
import Proyecto4.LecturaDatos.Funcion;

public class OpMat {
	//ignorar esto de abajo
	private HashMap<String, Funcion > funciones;

    private HashMap<String,Integer> tablaInt = new HashMap<>();
	private HashMap<String,Double> tablaDouble = new HashMap<>();
	private HashMap<String,String> tablaTexto = new HashMap<>();
	

	private HashMap<String,Integer> tablaGInt = new HashMap<String,Integer>();
	private HashMap<String,Double> tablaGDouble = new HashMap<String,Double>();
	private HashMap<String,String> tablaGTexto = new HashMap<String,String>();

	private Stack<Double> pilaNumero = new Stack<Double>();
	private Stack<String> pilaOperador = new Stack<String>();
	private Stack<String> pilaTexto = new Stack<String>();
	
	public OpMat(HashMap<String, Funcion > funciones) {
		this.funciones = funciones;
	}

	public void inicializarGlobales(HashMap<String,Integer> tablaGInt,HashMap<String,Double> tablaGDouble,HashMap<String,String> tablaGTexto){
		this.tablaGInt = tablaGInt;
		this.tablaGDouble = tablaGDouble;
		this.tablaGTexto = tablaGTexto;
	}
	
	//Inicializa Variable
	public void inicializarVariable(String tipo, String nombre) {
        switch(tipo){
            case "entero":
                tablaInt.put(nombre, 0);
                break;
            case "real":
                tablaDouble.put(nombre, 0.0);
                break;
			case "texto":
				tablaTexto.put(nombre, "");
				break;
            default:
                System.out.println("Error en inicializar Variable");
                break;
        }
		}

	public void asignarValorVariable(String Variable, double valor) { 
		for(Entry<String, Integer> entry: tablaInt.entrySet()) {
			if(entry.getKey().equals(Variable)) {
				tablaInt.put(Variable, (int) valor);
                return;
			}
		}

		for(Entry<String, Double> entry: tablaDouble.entrySet()) {
			if(entry.getKey().equals(Variable)) {
				tablaDouble.put(Variable, valor);
                return;
			}
		}

		for(Entry<String, Integer> entry: tablaGInt.entrySet()) {
			if(entry.getKey().equals(Variable)) {
				tablaGInt.put(Variable, (int) valor);
                return;
			}
		}

		for(Entry<String, Double> entry: tablaGDouble.entrySet()) {
			if(entry.getKey().equals(Variable)) {
				tablaGDouble.put(Variable, valor);
                return;
			}
		}
        System.out.println("No se encontro la Variable "+Variable);
	}

	public void asignarValorVariable(String Variable, String valor) { 
		for(Entry<String, String> entry: tablaTexto.entrySet()) {
			if(entry.getKey().equals(Variable)) {
				tablaTexto.put(Variable, valor);
                return;
			}
		}

		for(Entry<String, String> entry: tablaGTexto.entrySet()) {
			if(entry.getKey().equals(Variable)) {
				tablaGTexto.put(Variable, valor);
                return;
			}
		}
        System.out.println("No se encontro el Variable "+Variable);
	}
	
	public String buscarVariableTexto(String nombre){
		for(Entry<String, String> entry: tablaTexto.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return entry.getValue();
			}
		}

		for(Entry<String, String> entry: tablaGTexto.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return entry.getValue();
			}
		}

		System.out.print(nombre+" no existe.");
		return "";
	}

	public double buscarVariable(String nombre) {
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

		for(Entry<String, Integer> entry: tablaGInt.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return entry.getValue();
			}
		}
		
		for(Entry<String, Double> entry: tablaGDouble.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return entry.getValue();
			}
		}
		System.out.print(nombre+" no existe.");
		return 0;
	}
	
	public boolean isVariableExist(String nombre) {
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

		for(Entry<String, Integer> entry: this.tablaGInt.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return true;
			}
		}
		
		for(Entry<String, Double> entry: this.tablaGDouble.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return true;
			}
		}

		return false;
	}

	public boolean isVariableTextoExist(String nombre){
		for(Entry<String, String> entry: this.tablaTexto.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return true;
			}
		}

		for(Entry<String, String> entry: this.tablaGTexto.entrySet()) {
			if(entry.getKey().equals(nombre)) {
				return true;
			}
		}
		return false;
	}

	private String realizaroperacion(String y, String x, String operador){
		String valor = "";
		switch(operador) {
		case "+":
			valor = x+y;
			break;
		case "-":
			valor = x.replace(y, "");
			break;
		}		
		return valor;
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
		if(pilaOperador.isEmpty()||operador.equals(OPERADOR.getParentesis())) {
			pilaOperador.push(operador);
		}else if(operador.equals(OPERADOR.getParentesisCerrado())){
			vaciarPilaOperador(operador);
		}
		else if(OPERADOR.isMayor(operador, pilaOperador.peek())) {
			pilaOperador.push(operador);
		}
		else {
            vaciarPilaOperador(operador);
			pilaOperador.push(operador);
		}
	}

	public boolean isFuncion(String linea){
		for(Entry<String, Funcion> entry: this.funciones.entrySet()) {
			if(linea.contains(entry.getKey())) {
				return true;
			}
		}
		return false;
	}

    public void vaciarPilaOperador(String operadorAMeter) {
		while(!pilaOperador.isEmpty()) {
			if(pilaOperador.peek().equals(OPERADOR.getParentesis())) {
				if(!operadorAMeter.equals(OPERADOR.getParentesisCerrado())){
					return;
				}
				else if(operadorAMeter.equals(OPERADOR.getParentesisCerrado())) {
					pilaOperador.pop();
					return;
				}
				pilaOperador.pop();
			}else if(OPERADOR.isMayor(operadorAMeter, pilaOperador.peek())) {
				return;
			}
			
			else {
				if(pilaTexto.isEmpty()){
					pilaNumero.push(realizaroperacion(
						pilaNumero.pop(),pilaNumero.pop(),pilaOperador.pop()));
				}else{
					pilaTexto.push(realizaroperacion(
						pilaTexto.pop(), pilaTexto.pop(), pilaOperador.pop()));
				}
				
			}
		}
		
	}

    public double retiraNumPila(){
        return pilaNumero.pop();
    }

	public String retiraTextoPila(){
        return pilaTexto.pop();
    }

	public void colocarDatoEnPilaString(String valor) {
		if(OPERADOR.isOperador(valor)) {
			colocarOperadorEnPila(valor);
		}else if(isVariableTextoExist(valor)){
			pilaTexto.push(buscarVariableTexto(valor));
		}else if (valor.contains("'")) {
			pilaTexto.push(valor.replace("'", ""));
		}else{
			System.out.println("Error en colocar dato pila String. Compruebe el nombre de "+valor);
		}
	}
	
	public void colocarDatoEnPila(String valor) {
		if(OPERADOR.isOperador(valor)) {
			colocarOperadorEnPila(valor);
		}
		else if(isVariableExist(valor)) {
			pilaNumero.push(buscarVariable(valor));
		}else if(isFuncion(valor)) {
			sacarDatoDeFuncion(valor);
		}
		else{
			System.out.println("No se anadio a la pila. Compruebe el nombre de "+valor);
		}
	}

	public void colocarDatoEnPila(double valor) {
		pilaNumero.push(valor);
	}

	private void sacarDatoDeFuncion(String linea){
		for(Entry<String, Funcion> entry: this.funciones.entrySet()) {
			if(linea.contains(entry.getKey())) {
				String parametrosEntradaInicial = linea.substring(linea.indexOf("(")+1, linea.indexOf(")"));
				String[] separar = parametrosEntradaInicial.split(",");
				String parametrosEntrada = "";
				for(int i=0;i<separar.length;i++){
					if(isVariableExist(separar[i])){
						parametrosEntrada += buscarVariable(separar[i])+",";
					}	
				}
				if(parametrosEntrada.length()>0){
					parametrosEntrada = parametrosEntrada.substring(0, parametrosEntrada.length()-1);
				}

				String valor = entry.getValue().sacarValorFuncion(funciones, parametrosEntrada);
				if(entry.getValue().getretorno().equals("entero")){
					if(valor.contains(".")){
						valor = valor.substring(0, valor.indexOf("."));
					}
				}
				colocarDatoEnPila(Double.parseDouble(valor));
				break;
			}
		}

		

	}
	
	
	

    
}
