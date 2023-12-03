package Proyecto02;

import java.util.ArrayList;
import java.util.Scanner;
import Proyecto02.Baraja.BarajaPoker;
import Proyecto02.Personas.Crupier;
import Proyecto02.Personas.Jugador;
import Proyecto02.JuegoMesa.BlackJack;


public class App {

    static Scanner sc = new Scanner(System.in);//Leer datos
	
	public static BarajaPoker barajaNueva() {//Crea una baraja nueva
		int numeroCartas = 0;
		do {
			System.out.print("Numero de decks en la baraja:");
			String opcion = sc.next();
			try {
				numeroCartas = Integer.parseInt(opcion);
				if(numeroCartas>0) {
					break;
				}
				System.out.println("Introducir un valor mayor de 0");	
			}catch(Exception e ) {
				System.out.println("Introducir un valor valido");
			}
		}while(true);
		
		return new BarajaPoker(numeroCartas);
	}
	
	public static Crupier crupierNuevo() {//Crea un crupier nuevo
		String nombre, cantidad;
		int cantidadFichas;
		do {
			
			System.out.print("Nombre del crupier:");
			nombre = sc.next();
			System.out.print("Numero de fichas:");
			cantidad = sc.next();
			try {
				cantidadFichas = Integer.parseInt(cantidad);
				break;
			}
			catch(Exception e) {
				System.out.println("Escribe un valor valido");
			}
		}while(true);
		String nombreC = "Crupier "+nombre;
		Crupier c = new Crupier(nombreC,cantidadFichas);
		return c;
	}
	
	public static Jugador jugadorNuevo(int numero) { //Crea un jugador
		String nombre, cantidad;
		int cantidadFichas;
		do {
			System.out.print("Nombre del jugador"+numero+":");
			nombre = sc.next();
			System.out.print("Numero de fichas:");
			cantidad = sc.next();
			try {
				cantidadFichas = Integer.parseInt(cantidad);
				break;
			}
			catch(Exception e) {
				System.out.println("Escribe un valor valido");
			}
		}while(true);
		Jugador jNuevo = new Jugador(nombre,cantidadFichas);
		System.out.println("Jugador "+nombre+" anadido al juego\n");
		return jNuevo;
	}
	
	public static ArrayList<Jugador> listaJugadores(){//Crea una lista de jugadores
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		int numeroJugadores;
		do {
			System.out.print("Numero de jugadores (7 Maximo):");
			String valorNumeroJugadores = sc.next();
			try {
				numeroJugadores = Integer.parseInt(valorNumeroJugadores);
				if(numeroJugadores>=1 && numeroJugadores<=7) {
					System.out.println();
					break;
				}
				System.out.println("Ingrese al menos un jugador");
			}
			catch(Exception e) {
				System.out.println("Error. Introduce un valor correcto");
			}
		}while(true);
		
		for(int i=0;i<numeroJugadores;i++) {
			jugadores.add(jugadorNuevo((i+1)));
		}
		
		return jugadores;
	}
	
	public static BlackJack mesaNuevaBJ() {//Crea una nueva mesa de BlackJack
		System.out.println("Bienvenido al juego de mesa BlackJack");
		BarajaPoker baraja =  barajaNueva();
		baraja.barajar();
		ArrayList<Jugador> jugadores = listaJugadores();
		Crupier c = crupierNuevo();
		
		return new BlackJack(jugadores,baraja,c);
		
	}

	public static void main(String[] args) {
		
		BlackJack mesa1 =  mesaNuevaBJ(); //  mesaPruebas();
		mesa1.menuJugarBJ();
		
		mesa1.cerrarScanner();
		sc.close();
		System.out.println("Se termino las rondas");
	}
    
}
