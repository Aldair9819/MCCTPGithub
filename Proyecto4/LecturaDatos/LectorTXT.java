package Proyecto4.LecturaDatos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import Proyecto4.Constantes.CASOCICLO;
import Proyecto4.Constantes.CM;
import Proyecto4.Constantes.LITVAR;
import Proyecto4.Constantes.OPCOMPARAR;
import Proyecto4.Constantes.OPERADOR;
import java.util.Map.Entry;

public class LectorTXT {
    String Null = "";
	//private final String NombreArchivo = "codigo2.txt";
	//private final String NombreCarpeta = "codigos";
	//private final String Ruta = NombreCarpeta+ "\\"+NombreArchivo;
	private final String Ruta = "C:/Users/Waldosir/Desktop/Tecno de Progra/MCCTPGithub/Proyecto4/LecturaDatos/codigos/codigo2.txt";
	
	private HashMap<String, Stack<String> > funciones = new HashMap<>();
	private ArrayList<String> literalesGlobales = new ArrayList<String>();
	private String nombrePrograma="";
	
	private Stack<String> llavesFuncion = new Stack<String>();
	private Stack<String> llavesDemas = new Stack<String>();
	
	public ArrayList<String> leerTexto() {//Lista de todos los usuarios y sus datos
		ArrayList<String> informacion = new ArrayList<String>();
		try(BufferedReader bf = new BufferedReader(new FileReader(Ruta))){//Lee los datos
			String s;
			while((s = bf.readLine())!=null) {//Hasta que no haya linea para leer
				if(!s.replace(" ", "").equals(""))
				informacion.add(s);//Se anade el usuario a la lista
				}
			}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		//Se regresa el ArrayList en Array
		return informacion;
		
	}
	
	public void iniciar() {
		organizarPrograma(leerTexto());
		getDatosTotales();
	}
	
	public void getDatosTotales() {
		System.out.println("Nombre del programa:"+this.nombrePrograma);

// ...

Stack<String> primero = new Stack<String>();

for (Entry<String, Stack<String>> entry : funciones.entrySet()) {
    System.out.println("Funcion " + entry.getKey() + ":");
    primero.addAll(funciones.get(entry.getKey()));
    while (true) {
        if (primero.isEmpty()) {
            break;
        }
        System.out.println("-->" + primero.pop());
    }
    System.out.println("\n");
}

System.out.println("Datos globales:");
		System.out.println("Datos globales:");
		for(String datosGlobales:literalesGlobales) {
			System.out.println(datosGlobales);
		}
	}
	
	public void organizarPrograma(ArrayList<String> programa) {
		boolean anadirComandos= false;
		String nombreFuncion="";
		Stack<String> datos = new Stack<String>();
		for(String comandos:programa) {
			if(isPrograma(comandos)) {
				this.nombrePrograma = comandos.replace("Programa", "");
				this.nombrePrograma = this.nombrePrograma.replace("{", "");
				this.nombrePrograma = this.nombrePrograma.replace(" ", "");
			} else if(isFuncion(comandos) &&!isNotFunction(comandos)) {
				for (char caracter : comandos.toCharArray()) {
					if(!((caracter == '{') || (caracter == ' ') )) {
						nombreFuncion+=caracter;
					}
				}
				anadirComandos = true;
			}else if(terminaFuncion(comandos)) {
			funciones.put(nombreFuncion, datos);
			nombreFuncion = "";
			datos = new Stack<String>();
			anadirComandos = false;
		}else if(anadirComandos) {
			datos.push(comandos);
		}else if(terminaPrograma(comandos)) {
			break;
		}else {
			literalesGlobales.add(comandos);
		}
		
	}
		funciones.remove("");
	}
	

	public boolean isPrograma(String linea) {
		return linea.contains("Programa");
	}
	
	public boolean terminaPrograma(String linea) {
		return (linea.contains("}")) &&(llavesDemas.isEmpty() && llavesFuncion.isEmpty());
	}
	

	public boolean haveKey(String linea) {
		return linea.contains("{");
	}
	
	public boolean terminaFuncion(String linea) {
		for (char caracter : linea.toCharArray()) {
			if(caracter == '}') {
				if(llavesDemas.isEmpty()) {
					if(!terminaPrograma(linea)) {
						llavesFuncion.pop();
						return true;
					}
					
				}else {
					llavesDemas.pop();
				}
				
			}
				
		}
		return false;
		}
	
	
	public boolean isKeyNotFunction(String texto) {
        return CM.isComando(texto);
	}
	

private boolean isNotFunction(String texto) {
    if(OPCOMPARAR.isOPCOMPARAR(texto) || LITVAR.isDeclaracion(texto)  
    || CASOCICLO.isCASOCICLO(texto) || OPERADOR.isOperador(texto) ) {
        return true;
    }
    return false;
}
	
	public boolean isFuncion(String texto) {
		if(!(isKeyNotFunction(texto)) && haveKey(texto)) {
			llavesFuncion.push("{");

			return true;
		}else if(haveKey(texto)) {
			llavesDemas.push("{");
		}
		return false;
	}

	public HashMap<String, Stack<String>> getFunciones() {
		//return (HashMap<String, Stack<String>>) this.funciones.clone();
		return  this.funciones;
	}

	public ArrayList<String> getLiteralesGlobales() {
		return this.literalesGlobales;
	}

	public String getNombrePrograma() {
		return this.nombrePrograma;
	}
/* 
	private boolean isFunction(String linea) {
		return linea.contains("(") && CASOCICLO.isCASOCICLO(linea);
	}
*/
    
}
