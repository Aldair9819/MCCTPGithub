package Proyecto4.Principal;

import java.util.Scanner;
import Proyecto4.Comandos.InterpreteNoBucle;

public class App {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
       InterpreteNoBucle a = new InterpreteNoBucle();
       a.accion("entero base");
       a.accion("base = 20");
      a.accion("imprime ('hello wey')");
      a.accion("imprimeS (' como estas wey')");
      a.accion("imprimeS ('--Prueba de salto de linea',12,' la base es de:',base)");
      System.out.print("Hey");
    }
}