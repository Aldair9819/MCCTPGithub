package Proyecto4.LecturaDatos;

import java.util.Stack;
import java.util.HashMap;

import Proyecto4.Comandos.InterpreteNoBucle;
import Proyecto4.Constantes.CASOCICLO;
import Proyecto4.Constantes.CM;

public class Funcion{
    private Stack<String> comandos;
    private String parametros;
    private String retorno;
    private InterpreteNoBucle interprete = new InterpreteNoBucle();

    public Funcion( Stack<String> comandos,String parametros, String retorno) {
        this.retorno = retorno;
        this.parametros = parametros;
        this.comandos = comandos;
    }

    public void main(HashMap<String, Funcion> funciones){
        while(!comandos.isEmpty()){
            if(isFunction(this.comandos.peek())){
                ejecutarFuncion(this.comandos.pop(), funciones);
            }else if(CASOCICLO.isCASOCICLO(this.comandos.peek())){
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

   
    
}
