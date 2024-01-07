package Proyecto05_A1;

import java.util.ArrayList;

public class desfragmentar {
    public static void main(String[] args) {
        String texto = "'hello' + 'hamon there' + 'hi' + Bruh";
        
        String[] partes = texto.split(" ");
        ArrayList<String> todos = new ArrayList<String>();
        boolean combinar = false;
        for(int i=0;i<partes.length;i++){
            if(contarRepeticionSimbolo(partes[i], "'") == 2){
                todos.add(partes[i]);
            }else if(contarRepeticionSimbolo(partes[i], "'") == 1){
                if(!combinar){
                    combinar = true;
                    todos.add(partes[i]);
                }else{
                    todos.set(todos.size()-1, todos.get(todos.size()-1)+" "+partes[i]);
                    combinar = false;
                }
            }else if(combinar){
                todos.set(todos.size()-1, todos.get(todos.size()-1)+" "+partes[i]);
            }else{
                todos.add(partes[i]);
            }
        }

        for(int i=0;i<todos.size();i++){
            System.out.println(todos.get(i));
        }
    
        
}

public static int contarRepeticionSimbolo(String texto, String simbolo){
    return texto.length() - texto.replace(simbolo, "").length();
}
}
