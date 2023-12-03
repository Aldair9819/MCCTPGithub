package Proyecto03;

import java.util.Scanner;

import Proyecto03.JuegoBuscaminas.TablaBuscaminas;
import Proyecto03.datosUsuario.Usuario;
import Proyecto03.enums.DIFICULTAD;

public class App {
    static Scanner sc = new Scanner(System.in);
	
	static DIFICULTAD escogerDificultad() {//Escoge dificultad
		boolean opcionNoValida = true;
		int opcion=0;
		DIFICULTAD dificultadEscogida = null;
		do {
			System.out.println("Nivel de dificultad:");
			System.out.println("1- Dificultad Principiante");
			System.out.println("2- Dificultad facil");
			System.out.println("3- Dificultad normal");
			System.out.println("4- Dificultad experto");
			System.out.print("Opcion:");
			try {
				opcion = Integer.parseInt(sc.next());
			}catch(NumberFormatException e) {
			}
				
			switch(opcion) {
			case 1://Principiante
				dificultadEscogida = DIFICULTAD.principiante;
				opcionNoValida = false;
				break;//facil
			case 2:
				dificultadEscogida = DIFICULTAD.facil;
				opcionNoValida = false;
				break;
			case 3://Normal
				dificultadEscogida = DIFICULTAD.normal;
				opcionNoValida = false;
				break;
			case 4://Dificil
				dificultadEscogida = DIFICULTAD.dificil;
				opcionNoValida = false;
				break;
			default:
				System.out.println("Opcion no valida. Ingrese un valor apropiado.");
			}
			
		}while(opcionNoValida);
		return dificultadEscogida;
	}
	
	
	static Usuario crearUsuario() {//Crea un usuario
		String nombre;
		System.out.print("Nombre del jugador:");
		nombre = sc.next();
		DIFICULTAD d = escogerDificultad();
		
		return new Usuario(nombre,d);
	}
	

	public static void main(String[] args) {

		
		todo();
	}

    public static void todo(){
        String opcion = "";
        do {
		System.out.println("Buscaminas: ");
		System.out.println("1-Iniciar juego");
		System.out.println("2- Tabla de puntajes");
		System.out.println("3- Salir");
		System.out.print("Opcion:");
		opcion = sc.next();
		System.out.println();
		if(opcion.equals("1")) {//Iniciar juego
			Usuario uActual = crearUsuario();
			TablaBuscaminas t = new TablaBuscaminas(uActual);
			t.partidaBuscaminas();
			t = null;
		}else if(opcion.equals("2")) { //Tabla de puntajes
			TablaBuscaminas t = new TablaBuscaminas();
			System.out.println(t.tablaPuntajes());
			t = null;
		}else if(opcion.equals("3")) { //Salir del juego
			break;
		}else {
			System.out.println("Escoja una opcino valida\n");
		}
		
		//System.gc(); //Inicia recolector de basura
		}while(true);
		
		sc.close();
    }
	

}
