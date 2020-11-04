package Lenguajes;

import Errores.ErrorClass;
import Instrucciones.metodos;
import Variable.VariableDeclaracion;

import java.util.ArrayList;

public class javaClass {
    public String ID;
    int FILA,COLUMNA;
    public ArrayList<Instrucciones.metodos> metodos,construc;
    ArrayList<VariableDeclaracion> variablesDeclar;

    public javaClass(String ID, int FILA, int COLUMNA, ArrayList<Instrucciones.metodos> metodos, ArrayList<Instrucciones.metodos> construc, ArrayList<VariableDeclaracion> variablesDeclar) {
        this.ID = ID;
        this.FILA = FILA;
        this.COLUMNA = COLUMNA;
        this.metodos = metodos;
        this.construc = construc;
        this.variablesDeclar = variablesDeclar;
    }

    public boolean validate(VariableDeclaracion var,int size,ArrayList<VariableDeclaracion>vars,String name){
        boolean tmp=false;
        if(this.construc!=null){
            if(!this.construc.isEmpty()){
                for (metodos x:construc) {
                    if(var.args==null && x.argumentos==null){
                        MetodosVisual.add("call",x.codigoMetodo,size==0?"":String.valueOf(size)
                                ,x.ID,12);
                        return true;
                    }else  if(var.args==null && x.argumentos.size()==0){
                        MetodosVisual.add("call",x.codigoMetodo,""
                                ,x.ID,12);
                        return true;
                    }
                    else if(var.args!=null && x.argumentos!=null){
                        if(size==x.argumentos.size()){
                            tmp=MetodosVisual.get(var.args,x,vars,name);
                            if(tmp){
                                return true;
                            }
                        }
                    }

                }
            }
        }
       return tmp;
    }
}
