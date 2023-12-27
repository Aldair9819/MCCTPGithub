package Proyecto05_A1;

import java.util.ArrayList;
import java.util.HashMap;

public class pruebas {
    public static void main(String[] args) {
        /* 
        String retorno = "return 0 + 4";
        String nuevo = "return = "+retorno.substring(7);

        System.out.println(nuevo);
        //*/
        //String funcion = "Huevos(int x,int y)";
        String comando = "'Hey man' + variable1 + 'hola'";
        System.out.println(comando);

        
    }

    public void otraMas(){
        //String funcion = "Huevos(int x,int y)";
        String funcion = "Huevos(int x,int y)";
        String parametros = funcion.substring(funcion.indexOf("(")+1, funcion.indexOf(")"));
        System.out.println("-"+parametros+"-"+"longitud:"+parametros.length());
    }

    public void paraHaber(){
        ArrayList<Integer> hey = new ArrayList<Integer>();
        hey.add(16);
        System.out.println(hey.get(0));
        pruebas2 aPruebas2 = new pruebas2();
        aPruebas2.haber(hey);
        System.out.println(hey.get(0));
    }

    public void paraHash(){
        HashMap<String, Integer> Enteros = new HashMap<String, Integer>();
        Enteros.put("Var1", 24);
        pruebas2 aPruebas2 = new pruebas2();
        aPruebas2.paraHash(Enteros);

        System.out.println(Enteros.get("Var1"));
    }
}
