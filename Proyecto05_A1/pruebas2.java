package Proyecto05_A1;

import java.util.ArrayList;
import java.util.HashMap;

public class pruebas2 {
    public void haber(ArrayList<Integer> no){
        pruebas3 p = new pruebas3();
        no.set(0, 14);
        p.saca(no);
    }

    public void paraHash(HashMap<String, Integer> Ent2){
        Ent2.put("Var1", 42);
        pruebas3 p = new pruebas3();
        p.paraHash(Ent2);
    }
}
