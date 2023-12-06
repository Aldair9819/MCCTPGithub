package Proyecto4.Principal;

import java.util.Scanner;
import Proyecto4.Comandos.InterpreteNoBucle;
import Proyecto4.LecturaDatos.LectorTXT;
import Proyecto4.Comandos.Ejecutor;

public class App {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
      InterpreteNoBucle a = new InterpreteNoBucle();
      // primeraParte(a);
       LectorTXT l = new LectorTXT();
       l.iniciar();
      Ejecutor ejecutor = new Ejecutor(l.getNombrePrograma(),l.getFunciones(),l.getLiteralesGlobales());
      ejecutor.inicializar();
      //*/
    }

    static void primeraParte(InterpreteNoBucle a){
      a.accion("entero base");
       a.accion("base = 20");
      a.accion("imprime ('hello wey')");
      a.accion("imprimeS (' como estas wey')");
      a.accion("imprimeS ('--Prueba de salto de linea',12,' la base es de:',base)");
      System.out.println("Hey:");

      String comandos = "int sinoLa(int x, int y){";
      String retorno = comandos.substring(0, comandos.indexOf(" ")).replace(" ", "");
			String nombreFuncion = comandos.substring(comandos.indexOf(" ")+1, comandos.indexOf("("));
			String parametrosFuncion = comandos.substring(comandos.indexOf("(")+1, comandos.indexOf(")"));

      System.out.println(retorno);
      System.out.println(nombreFuncion);
      System.out.println(parametrosFuncion);

    }
}