package GramaticaC;

import Instrucciones.Instruccion;
import Lenguajes.MetodosVisual;
import Lenguajes.javaClass;
import Variable.VariableDeclaracion;

import java.util.ArrayList;

public class declaracion_c extends Instruccion {
    public String ID;
    //public ArrayList<VariableDeclaracion> variableDeclaracions;
    String name;
    public declaracion_c(String id,int f,int c, ArrayList<VariableDeclaracion> variableDeclaracions){
        this.FILA=f;
        this.COLUMNA=c;
        this.ID=id;
        name=this.ID.split("\\.")[1];
        this.variables=variableDeclaracions;
    }
    @Override
    public void validate(boolean valid) {

        buscarClase();
    }
    boolean declar=false;
    public void  buscarClase(){
        declar=false;
        if(MetodosVisual.clasesJava!=null){
            if(MetodosVisual.clasesJava.classes!=null){
                if(!MetodosVisual.clasesJava.classes.isEmpty()){
                    for (javaClass x:MetodosVisual.clasesJava.classes) {
                        if(x.ID.equals(name)){
                            this.variables
                                    .stream()
                                    .forEach((var)->{
                                        int sizeArgs=var.args==null?0:var.args.size();
                                        declar=x.validate(var,sizeArgs,this.variables,var.ID);
                                        var.nameClass=this.ID.split("\\.")[1];
                                    });
                          if(!declar){
                              errores.AddError(2,this.FILA,this.COLUMNA,this.ID,"La clase fue encontrada, pero no\n" +
                                      "hay un constructor que cumpla con los parametros");
                          }
                         return;
                        }
                    }

                }
            }
        }
        errores.AddError(2,this.FILA,this.COLUMNA,this.ID,"No se encontro la clase");
    }
}
