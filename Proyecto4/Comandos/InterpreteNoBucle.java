package Proyecto4.Comandos;

import java.util.HashMap;
import java.util.Scanner;

import Proyecto4.LecturaDatos.Funcion;



public class InterpreteNoBucle {
    private Scanner sc = new Scanner(System.in);
	private OpMat opMat;
    private HashMap<String, Funcion > funciones;

    public InterpreteNoBucle(HashMap<String, Funcion > funciones) {
        this.funciones = funciones;
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
            if(opMat.isLiteralExist(lineaSeparada[1])){
            try {
            System.out.print("Asignar numero a "+lineaSeparada[1]+":"); 
			double opcion = Double.parseDouble(sc.next());
			opMat.asignarValorLiteral(lineaSeparada[1], opcion);
			System.out.println();
                
            } catch (NumberFormatException e) {
                System.out.println("Error en leer.");
            }
            }else{
                System.out.println("Error. No existe la variable");
            }
            
            break;
            case "imprimeS":
            saltoLinea = true;
            case "imprime":
            try{
                restante = linea.substring(linea.indexOf("(")+1, linea.indexOf(")"));
            String[] separaRestante = restante.split(",");
            for(int i=0;i<separaRestante.length;i++){
                if(opMat.isLiteralExist(separaRestante[i])){
                    System.out.print(opMat.buscarLiteral(separaRestante[i]));
                }else{
                    separaRestante[i] = separaRestante[i].replaceAll("'", "");
                    System.out.print(separaRestante[i]);
                }
            }
            if (saltoLinea) {
                System.out.println();
            }
            }catch(StringIndexOutOfBoundsException e){
                System.out.println("Error en imprime");
            }
            

            break;

            case "entero":
            case "real":
			variable = lineaSeparada[1];
			opMat.inicializarLiteral(comando, variable);
            System.out.println("Declarada variable "+variable+" de tipo "+comando);
            break;

            default:
            
            variable = lineaSeparada[0];
            System.out.println("Entra variable:"+variable);
            if(opMat.isLiteralExist(variable)){
            for(int i= 2;i<lineaSeparada.length;i++) {
                if(isNumero(lineaSeparada[i])){
                    opMat.colocarDatoEnPila(Double.parseDouble(lineaSeparada[i]));
                }else{
                    opMat.colocarDatoEnPila(lineaSeparada[i]);
                }    
            }
            opMat.vaciarPilaOperador();
            opMat.asignarValorLiteral(variable, opMat.retiraNumPila());
            
        }else{
            System.out.println("Error. No existe la variable");
        }
        break;
            }

            
		}

    public boolean isNumero(String dato){
            try{
                Double.parseDouble(dato);
                return true;
            }catch(NumberFormatException e){
                return false;
            }
        }

	public double getValorLiteral(String nombre) {
		return opMat.buscarLiteral(nombre);
	}
	

	public OpMat getOperaciones() {
		return this.opMat;
	}
    
}
