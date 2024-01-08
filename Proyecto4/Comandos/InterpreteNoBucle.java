package Proyecto4.Comandos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Proyecto4.LecturaDatos.Funcion;

public class InterpreteNoBucle {
    private Scanner sc = new Scanner(System.in);
	private OpMat opMat;

    public InterpreteNoBucle(HashMap<String, Funcion > funciones) {
        opMat = new OpMat(funciones);
    }

	public void accion(String linea) {
        String[] lineaSeparada = linea.split(" ");
		String comando = lineaSeparada[0];
		String restante = "";
		String variable = "";
        boolean saltoLinea = false;
        switch(comando){
            
            case "leer":
            if(opMat.isVariableExist(lineaSeparada[1])){
            try {
            System.out.print("Asignar numero a "+lineaSeparada[1]+":"); 
			double opcion = Double.parseDouble(sc.next());
			opMat.asignarValorVariable(lineaSeparada[1], opcion);
			System.out.println();
                
            } catch (NumberFormatException e) {
                //System.out.println("--->Error en leer.");
            }
            }else{
                //System.out.println("--->Error. No existe la variable");
            }
            
            break;
            case "imprimeS":
            saltoLinea = true;
            case "imprime":
            try{
                restante = linea.substring(linea.indexOf("(")+1, linea.indexOf(")"));
            String[] separaRestante = restante.split(",");
            for(int i=0;i<separaRestante.length;i++){
                if(opMat.isVariableExist(separaRestante[i])){
                    System.out.print(opMat.buscarVariable(separaRestante[i]));
                }else if(opMat.isVariableTextoExist(separaRestante[i])){
                    System.out.print(opMat.buscarVariableTexto(separaRestante[i]));
                }
                else{
                    separaRestante[i] = separaRestante[i].replaceAll("'", "");
                    System.out.print(separaRestante[i]);
                }
            }
            if (saltoLinea) {
                System.out.println();
            }
            }catch(StringIndexOutOfBoundsException e){
                //System.out.println("--->Error en imprime");
            }
            

            break;
            case "texto":
            case "entero":
            case "real":
			variable = lineaSeparada[1];
			opMat.inicializarVariable(comando, variable);
            if(!variable.equals("return")){
                //System.out.println("--->Declarada variable "+variable+" de tipo "+comando);
            }
            
            break;

            default:
            variable = lineaSeparada[0];
            if(opMat.isVariableExist(variable)){
            for(int i= 2;i<lineaSeparada.length;i++) {
                if(isNumero(lineaSeparada[i])){
                    opMat.colocarDatoEnPila(Double.parseDouble(lineaSeparada[i]));
                }else{
                    opMat.colocarDatoEnPila(lineaSeparada[i]);
                }    
            }
            opMat.vaciarPilaOperador();
            opMat.asignarValorVariable(variable, opMat.retiraNumPila());
            if(!variable.equals("return")){
                //System.out.println("--->Se asigno el valor "+opMat.buscarVariable(variable)+" a la variable "+variable);
            }
            
            
        }else if(opMat.isVariableTextoExist(variable)){
            ArrayList<String> textoCompleto = new ArrayList<String>();
            for(int i= 2;i<lineaSeparada.length;i++) {
                textoCompleto.add(lineaSeparada[i]);
            }

            ArrayList<String> textoSeparado = separadorTexto(textoCompleto);

            for(int i= 0;i<textoSeparado.size();i++) {
                opMat.colocarDatoEnPilaString(textoSeparado.get(i));
            }
            opMat.vaciarPilaOperador();
            opMat.asignarValorVariable(variable, opMat.retiraTextoPila());
            if(!variable.equals("return")){
                //System.out.println("--->Se asigno el valor de texto -"+opMat.buscarVariableTexto(variable)+"- a la variable "+variable);
            }
            
        }
        else{
            //System.out.println("--->Error. No existe la variable");
        }
        break;
            }

            
		}

    private ArrayList<String> separadorTexto(ArrayList<String> textoCompleto){
        String texto = "";
        for(int i=0;i<textoCompleto.size();i++){
            texto += textoCompleto.get(i)+ " ";
        }
        
        String[] partes = texto.split(" ");
        ArrayList<String> todos = new ArrayList<String>();
        boolean combinar = false;
        for(int i=0;i<partes.length;i++){
            if(contarRepeticionSimbolo(partes[i], "'") == 2){
                todos.add(partes[i]);
            }else if(contarRepeticionSimbolo(partes[i], "'") == 1){
                if(!combinar){
                    combinar = true;
                    todos.add(partes[i]);
                }else{
                    todos.set(todos.size()-1, todos.get(todos.size()-1)+" "+partes[i]);
                    combinar = false;
                }
            }else if(combinar){
                todos.set(todos.size()-1, todos.get(todos.size()-1)+" "+partes[i]);
            }else{
                todos.add(partes[i]);
            }
        }
        return todos;
    }

    public int contarRepeticionSimbolo(String texto, String simbolo){
        return texto.length() - texto.replace(simbolo, "").length();
    }

    public boolean isNumero(String dato){
            try{
                Double.parseDouble(dato);
                return true;
            }catch(NumberFormatException e){
                return false;
            }
        }

	public double getValorVariable(String nombre) {
		return opMat.buscarVariable(nombre);
	}

    public String getValorVariableTexto(String nombre) {
		return opMat.buscarVariableTexto(nombre);
	}
	

	public OpMat getOperaciones() {
		return this.opMat;
	}

    public void inicializarGlobales(HashMap<String,Integer> tablaGInt,HashMap<String,Double> tablaGDouble,HashMap<String,String> tablaGTexto){
        opMat.inicializarGlobales(tablaGInt, tablaGDouble, tablaGTexto);
    }
    
}
