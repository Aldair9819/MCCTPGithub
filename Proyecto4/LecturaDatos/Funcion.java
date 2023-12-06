package Proyecto4.LecturaDatos;

import java.util.Stack;

public class Funcion{
    private Stack<String> comandos;
    private String parametros;
    private String retorno;

    public Funcion( Stack<String> comandos,String parametros, String retorno) {
        this.retorno = retorno;
        this.parametros = parametros;
        this.comandos = comandos;
    }

    public void inicio(Stack<String> funciones){

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
