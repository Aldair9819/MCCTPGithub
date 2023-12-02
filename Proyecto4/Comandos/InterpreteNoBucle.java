package Proyecto4.Comandos;

import java.util.Scanner;



public class InterpreteNoBucle {
    private Scanner sc = new Scanner(System.in);
	private OpMat opMat = new OpMat();

	public void accion(String linea) {
        String[] lineaSeparada = linea.split(" ");
		String comando = lineaSeparada[0];
		String restante = "";
		String variable = "";
        boolean saltoLinea = false;
        switch(comando){
            case "leer":
            try {
            System.out.print("Asignar numero a "+variable+":"); 
			double opcion = Double.parseDouble(sc.next());
			opMat.asignarValorLiteral(variable, opcion);
			System.out.println();
                
            } catch (NumberFormatException e) {
                System.out.println("Error en leer.");
            }
            break;
            case "imprimeS":
            saltoLinea = true;
            case "imprime":
            restante = linea.substring(linea.indexOf("(")+1, linea.indexOf(")"));
            String[] separaRestante = restante.split(",");
            for(int i=0;i<separaRestante.length;i++){
                if(opMat.isLiteralExist(separaRestante[i])){
                    System.out.print(opMat.buscarLiteral(separaRestante[i]));
                }else{
                    separaRestante[i] = separaRestante[i].replaceAll("'", "");
                    System.out.print(separaRestante[i]);
                }
            }
            if (saltoLinea) {
                System.out.println();
            }

            break;

            case "entero":
            case "real":
			variable = lineaSeparada[1];
			opMat.inicializarLiteral(comando, variable);
            break;

            default:
            variable = lineaSeparada[0];
            for(int i= 2;i<lineaSeparada.length;i++) {
                if(isNumero(lineaSeparada[i])){
                    opMat.colocarDatoEnPila(Double.parseDouble(lineaSeparada[i]));
                }else{
                    opMat.colocarDatoEnPila(lineaSeparada[i]);
                }    
            }
            opMat.vaciarPilaOperador();
            opMat.asignarValorLiteral(variable, opMat.retiraNumPila());
            break;
        }
		}

    public boolean isNumero(String dato){
            try{
                Double.parseDouble(dato);
                System.out.println("Entra");
                return true;
            }catch(NumberFormatException e){
                return false;
            }
        }

	public double getValorLiteral(String nombre) {
		return opMat.buscarLiteral(nombre);
	}
	

	public OpMat getOperaciones() {
		return this.opMat;
	}
    
}
