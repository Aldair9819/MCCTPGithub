package Proyecto4.Comandos;

import java.util.HashMap;
import java.util.Stack;
import java.util.Map.Entry;

import Proyecto4.Constantes.OPERADOR;
import Proyecto4.LecturaDatos.Funcion;

public class OpMat {
	//ignorar esto de abajo
	private HashMap<String, Funcion > funciones;
	private HashMap<String,String> tablaTexto = new HashMap<>();
	private Stack<String> pilaTexto = new Stack<String>();
	//IGNORAR ESTO DE ARRIBA
	
    private HashMap<String,Integer> tablaInt = new HashMap<>();
	private HashMap<String,Double> tablaDouble = new HashMap<>();

	private Stack<Double> pilaNumero = new Stack<Double>();
	private Stack<String> pilaOperador = new Stack<String>();
	
	//Ignora este constructor
	public OpMat(HashMap<String, Funcion > funciones) {
		this.funciones = funciones;
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
        System.out.println("No se encontro la Variable "+Variable);
	}

	public void asignarValorVariable(String Variable, String valor) { 
		for(Entry<String, String> entry: tablaTexto.entrySet()) {
			if(entry.getKey().equals(Variable)) {
				tablaTexto.put(Variable, valor);
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
		return false;
	}

	public boolean isVariableTextoExist(String nombre){
		for(Entry<String, String> entry: this.tablaTexto.entrySet()) {
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
			vaciarPilaOperador();
		}
		else if(OPERADOR.isMayor(operador, pilaOperador.peek())) {
			pilaOperador.push(operador);
		}
		else {
            vaciarPilaOperador();
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

    public void vaciarPilaOperador() {
		while(true) {
			if(pilaOperador.isEmpty()) {
				return;
			}else if(pilaOperador.peek().equals(OPERADOR.getParentesis())) {
				pilaOperador.pop();
				return;
			}
			else {
				if(pilaTexto.isEmpty()){
					pilaNumero.push(realizaroperacion(
						pilaNumero.pop(),pilaNumero.pop(),pilaOperador.pop()));
				}else{
					pilaTexto.push(realizaroperacion(pilaTexto.pop(), pilaTexto.pop(), pilaOperador.pop()));
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
				parametrosEntrada = parametrosEntrada.substring(0, parametrosEntrada.length()-1);

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
