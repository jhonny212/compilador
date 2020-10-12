package Lenguajes;

import Errores.ErrorClass;
import Instrucciones.metodos;
import Variable.VariableDeclaracion;

import java.util.ArrayList;

public class ClasesJava {
    public ArrayList<javaClass> classes=new ArrayList<>();
    public void add(String id, int f, int c, ArrayList<metodos> metodos
            ,ArrayList<metodos> construc,ArrayList<VariableDeclaracion> variablesDeclar){
        classes.add(new javaClass(id,f,c,metodos,construc,variablesDeclar));
    }

    public void validate(ErrorClass errorClass){
        if(this.classes.isEmpty()){
            return;
        }
        for (int i = 0; i < classes.size(); i++) {
            javaClass x=classes.get(i);
            for (int j = i+1; j < classes.size(); j++) {
                javaClass y=classes.get(j);
                if(x.ID.equals(y.ID)){
                    errorClass.AddError(2, y.FILA, y.COLUMNA, x.ID,"La clase se repite en\n" +
                            "Fila:"+x.FILA+" Columna:"+x.COLUMNA);
                }
            }
        }
    }
}
