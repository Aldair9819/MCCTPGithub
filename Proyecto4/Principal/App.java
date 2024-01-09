package Proyecto4.Principal;

import java.util.Scanner;
import Proyecto4.LecturaDatos.LectorTXT;
import Proyecto4.Comandos.Ejecutor;

public class App {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
      
      System.out.print("Numero de codigo:");
      String numeroCodigo = sc.nextLine();
       LectorTXT l = new LectorTXT();
       l.iniciar(numeroCodigo);
      Ejecutor ejecutor = new Ejecutor(l.getNombrePrograma(),l.getFunciones(),l.getLiteralesGlobales());
      ejecutor.inicializar();
    }

}