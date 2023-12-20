package Proyecto4.LecturaDatos;

import java.util.Stack;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import Proyecto4.Comandos.InterpreteNoBucle;
import Proyecto4.Constantes.CASOCICLO;
import Proyecto4.Constantes.CM;

public class Funcion{
    private Stack<String> comandos;
    private String parametros;
    private String retorno;
    private InterpreteNoBucle interprete = new InterpreteNoBucle();
    private Stack<String> llavesSiSino = new Stack<String>();
    private Scanner sc = new Scanner(System.in);

    public Funcion( Stack<String> comandos,String parametros, String retorno) {
        this.retorno = retorno;
        this.parametros = parametros;
        this.comandos = comandos;
    }

    public void main(HashMap<String, Funcion> funciones){
        while(!comandos.isEmpty()){
            if(isFunction(this.comandos.peek())){
                ejecutarFuncion(this.comandos.pop(), funciones);
            }else if(CASOCICLO.isCASO(this.comandos.peek())){
                retirarCaso();
                //sc.nextLine();
            }
            else{
                interprete.accion(this.comandos.pop());
            }
            
        }

    }

    private boolean isFunction(String linea){
        return linea.contains("(") && !CASOCICLO.isCASOCICLO(linea) && !CM.isComando(linea);
    }

    private void ejecutarFuncion(String linea, HashMap<String, Funcion> funciones){
        for(int i = 0; i<funciones.size();i++){
            if(linea.contains(funciones.keySet().toArray()[i].toString())){
                funciones.get(funciones.keySet().toArray()[i]).main(funciones);
            }
        }

    }



    public String getretorno() {
        return retorno;
    }

    public String getParametros() {
        return parametros;
    }

    public Stack<String> getComandos() {
        return comandos;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public void setComandos(Stack<String> comandos) {
        this.comandos = comandos;
    }

    public void retirarCaso(){
        boolean añadir = false;
        Stack<String> caso = new Stack<String>();
        LinkedHashMap<String, Stack<String>> casos = new LinkedHashMap<String, Stack<String>>();
        String nombreCaso = "error";
        do{
            if(comandos.peek().contains("}")){
                comandos.pop();
                llavesSiSino.pop();
                if(llavesSiSino.isEmpty()){
                    añadir = false;
                    casos.put(nombreCaso, caso);
                    nombreCaso = "error";
                    caso = new Stack<String>();
                }
            }
            else if(añadir){
                caso.push(comandos.pop());
            }else if(CASOCICLO.isCASO(this.comandos.peek())){
                llavesSiSino.push("{");
                añadir = true;
                nombreCaso = comandos.pop();
            }
            else{
                break;
            }
        }while(!comandos.isEmpty()&&!terminaCaso(comandos.peek())  );


        for(Map.Entry<String, Stack<String>> entry : casos.entrySet()) {
            String key = entry.getKey();
            if(verificarCaso(key)){
                while(!entry.getValue().isEmpty()){
                comandos.push(entry.getValue().pop());
                }
                break;
            }
            }
            
        }

    
    

    private boolean verificarCaso(String linea){

        //Ejempo de linea que entra: "Si (x > 2)"
        if(!linea.contains("(") && linea.contains(CASOCICLO.SINO.toString())){
            return true;
        }else{
            linea = linea.substring(linea.indexOf("(")+1, linea.indexOf(")"));
            String[] partes = linea.split(" ");
            String valor1 = partes[0];
            String operador = partes[1];
            String valor2 = partes[2];

            if(!interprete.isNumero(valor1)){
                valor1 = interprete.getValorLiteral(valor1) + "";
            }

            if(!interprete.isNumero(valor2)){
                valor2 = interprete.getValorLiteral(valor1) + "";
            }

            switch(operador){
                case ">":
                return Double.parseDouble(valor1) > Double.parseDouble(valor2);
                case "<":
                return Double.parseDouble(valor1) < Double.parseDouble(valor2);
                case ">=":
                return Double.parseDouble(valor1) >= Double.parseDouble(valor2);
                case "<=":
                return Double.parseDouble(valor1) <= Double.parseDouble(valor2);
                case "==":
                return Double.parseDouble(valor1) == Double.parseDouble(valor2);
                case "<>":
                return Double.parseDouble(valor1) != Double.parseDouble(valor2);
            }

        }

       
        System.out.println("Error");
        return false;
    }

    private boolean terminaCaso(String linea){
        /* 
        System.out.println("Entra"+linea);
        System.out.println("Regresa:"+!llavesSiSino.isEmpty()+"--"+!(linea.contains(CASOCICLO.SINO.toString()) || linea.contains(CASOCICLO.SI.toString())));
        System.out.println("Y eso da:"+(!llavesSiSino.isEmpty() && !(linea.contains(CASOCICLO.SINO.toString()) || linea.contains(CASOCICLO.SI.toString()))));
        */
        return llavesSiSino.isEmpty() && !(linea.contains(CASOCICLO.SINO.toString()) || linea.contains(CASOCICLO.SI.toString()));
    }
    /* 
    private boolean isCaso(String linea){
        linea = linea.substring(0, linea.indexOf(")")).replace(" ", "");
        return linea.equals(CASOCICLO.SI.toString()) || linea.equals(CASOCICLO.SINO.toString());
    }
    */

   
    
}
