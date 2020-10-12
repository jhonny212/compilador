package Arbol;

import Errores.ErrorClass;
import Variable.VariableDeclaracion;

import java.util.ArrayList;

public class Arbol {
    public String kindVar,ID,lastVal;
    public ErrorClass errorClass=null;

    protected ArrayList<VariableDeclaracion> tablaSimbolos;

    protected VariableDeclaracion getVar(String ID){
        if(this.tablaSimbolos==null){
            return null;
        }
        if(ID.startsWith("this.")){
            String tmp=ID.replaceFirst("this.","");
            for (VariableDeclaracion x:this.tablaSimbolos) {
                if(x.ID.equals(tmp) && x.globalVar==1){
                    return x;
                }
            }
        }else{
            for (int i = this.tablaSimbolos.size()-1; i >=0 ; i--) {
                VariableDeclaracion x=this.tablaSimbolos.get(i);
                if(x.ID.equals(ID)){
                    return x;
                }
            }
        }
        return null;
    }
}
