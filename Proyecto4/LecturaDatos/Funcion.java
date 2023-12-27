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
    private InterpreteNoBucle interprete;
    private Scanner sc = new Scanner(System.in);
    private String valorRetorno;

    public Funcion( Stack<String> comandos,String parametros, String retorno) {
        this.comandos = comandos;
        this.parametros = parametros;
        this.retorno = retorno;
    }

    public void main(HashMap<String, Funcion> funciones,String parametrosEntrada){
        this.interprete = new InterpreteNoBucle(funciones);
        declararParametros(parametrosEntrada);
        Stack <String> comandosAux = new Stack<String>();
        comandosAux.addAll(comandos);
        while(!comandosAux.isEmpty()){
            if(isFunction(comandosAux.peek())){
                ejecutarFuncion(comandosAux.pop(), funciones);
            }else if(CASOCICLO.isCASO(comandosAux.peek())){
                retirarCaso(comandosAux);
            }else if(CASOCICLO.isCICLO(comandosAux.peek())){
                retiraBucle(comandosAux);
                //sc.nextLine();
            }else if(isReturn(comandosAux.peek())){
                valorRetorno = calcularRetorno(comandosAux.pop(), funciones);
                comandosAux.clear();
            }
            else{
                interprete.accion(comandosAux.pop());
            }
            
        }

    }

    private void declararParametros(String parametrosEntrada){
        //Sin filtro: (2,5,7)
        //Parametro entrada ejemplo: 2,5,7
        if(parametrosEntrada.equals("")){
            return;
        }
        System.out.println("Entra:-"+parametrosEntrada+"-");
         String[] variablesParametros = parametros.split(",");
         String[] variablesEntrada = parametrosEntrada.split(",");
         for(int i = 0; i<variablesParametros.length;i++){
             this.interprete.accion(variablesParametros[i]);

             ///AQUI VA EL RESTO DE LA FUNCION
             if(this.interprete.isNumero(variablesEntrada[i])){
                this.interprete.accion(variablesParametros[i].split(" ")[1]+" = "+variablesEntrada[i]);
             }else{
                this.interprete.accion(variablesParametros[i].split(" ")[1]+" = "+this.interprete.getValorVariable(variablesEntrada[i]));
             }
             
         }
    }

    public String sacarValorFuncion(HashMap<String, Funcion> funciones, String declararParametros){
        //Aqui también ya llega con filtro
        main(funciones, declararParametros);
        return valorRetorno;
    }

    private String calcularRetorno(String linea, HashMap<String, Funcion> funciones){
  
        if(retorno.equals("void")){
            return "";
        }
        
        switch(retorno){
            case "int":
            interprete.accion("entero return");
            break;
            case "double":
            interprete.accion("real return");
            break;
            case "string":
            interprete.accion("string return");
            break;
            default:
            System.out.println("Error en retorno");
        }
        String returnComoOperacion = "return = "+linea.substring(7);
        interprete.accion(returnComoOperacion);
        return interprete.getValorVariable("return")+"";
        

    }


    private boolean isReturn(String linea){
        return linea.contains("return");
    }

    private boolean isFunction(String linea){
        return linea.contains("(") && !CASOCICLO.isCASOCICLO(linea) && !CM.isComando(linea);
    }

    private void ejecutarFuncion(String linea, HashMap<String, Funcion> funciones){
        //Aqui aplica filtro
        String parametrosEntrada = linea.substring(linea.indexOf("(")+1, linea.indexOf(")"));
        for(int i = 0; i<funciones.size();i++){
            if(linea.contains(funciones.keySet().toArray()[i].toString())){
                funciones.get(funciones.keySet().toArray()[i]).main(funciones, parametrosEntrada);
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

    private void retiraBucle(Stack <String> comandosAux){
        boolean añadir = false;
        Stack<String> caso = new Stack<String>();
        LinkedHashMap<String, Stack<String>> casos = new LinkedHashMap<String, Stack<String>>();
        Stack<String> llavesSiSino = new Stack<String>();
        String nombreCaso = "error";

        do{
            if(comandosAux.peek().contains("}")){
                llavesSiSino.pop();
                if(llavesSiSino.isEmpty()){
                    comandosAux.pop();
                    break;
                }else{
                    caso.push(comandosAux.pop());
                }
            }
            else if(añadir){
                if(comandosAux.peek().contains("{")){
                    llavesSiSino.push("{");
                }
                caso.push(comandosAux.pop());
                
            }else if(CASOCICLO.isCICLO(comandosAux.peek())){
                llavesSiSino.push("{");
                añadir = true;
                nombreCaso = comandosAux.pop();
            }
            else{
                break;
            }
        }while(!comandosAux.isEmpty());
        if(verificarCaso(nombreCaso)){
            Stack<String> casoAux = new Stack<String>();
            casoAux.addAll(caso);
            comandosAux.push("}");


            while(!caso.isEmpty()){
                comandosAux.push(caso.pop());
            }
            
            comandosAux.push(nombreCaso);
            

            while(!casoAux.isEmpty()){
                comandosAux.push(casoAux.pop());
            }
            
            
        }  
        }

    private void retirarCaso(Stack <String> comandosAux){
        boolean añadir = false;
        Stack<String> caso = new Stack<String>();
        LinkedHashMap<String, Stack<String>> casos = new LinkedHashMap<String, Stack<String>>();
        Stack<String> llavesSiSino = new Stack<String>();
        String nombreCaso = "error";

        do{
            if(comandosAux.peek().contains("}")){
                llavesSiSino.pop();
                if(llavesSiSino.isEmpty()){
                    comandosAux.pop();
                    casos.put(nombreCaso, caso);

                    añadir = false;
                    nombreCaso = "error";
                    caso = new Stack<String>();
                    
                    
                }else{
                    caso.push(comandosAux.pop());
                }
            }
            else if(añadir){
                if(comandosAux.peek().contains("{")){
                    llavesSiSino.push("{");
                }
                caso.push(comandosAux.pop());
                
            }else if(CASOCICLO.isCASO(comandosAux.peek())){
                llavesSiSino.push("{");
                añadir = true;
                nombreCaso = comandosAux.pop();
            }
            else{
                break;
            }
        }while(!comandosAux.isEmpty()&&repetirCicloCaso(comandosAux.peek(), llavesSiSino));


        for(Map.Entry<String, Stack<String>> entry : casos.entrySet()) {
            String key = entry.getKey();
            if(verificarCaso(key)){
                while(!entry.getValue().isEmpty()){
                comandosAux.push(entry.getValue().pop());
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
                valor1 = interprete.getValorVariable(valor1) + "";
            }

            if(!interprete.isNumero(valor2)){
                valor2 = interprete.getValorVariable(valor1) + "";
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

    private boolean repetirCicloCaso(String linea, Stack<String> llavesSiSino){
        return (linea.contains(CASOCICLO.SINO.toString()) || linea.contains(CASOCICLO.SI.toString())|| !llavesSiSino.isEmpty());
    }


   
    
}
