package GramaticaC;

import Instrucciones.Instruccion;
import Lenguajes.MetodosVisual;
import Lenguajes.javaClass;
import TablaSimbolos.SymTable;
import Variable.VariableDeclaracion;

import java.util.ArrayList;

public class declaracion_c extends Instruccion {
    public String ID;
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
                                       if(!var.hasBennCheck && var.isJava){
                                           var.hasBennCheck=true;
                                           int sizeArgs=var.args==null?0:var.args.size();
                                           declar=x.validate(var,sizeArgs,this.variables,var.ID);

                                           var.nameClass=this.ID.split("\\.")[1];
                                           SymTable.ADD(15,false,1,this.numeroAmbito,this.ambitoDad,var.ID);
                                           SymTable.ADD(1,false,1,this.numeroAmbito,this.ambitoDad,var.ID+"_INTEGER");
                                           SymTable.ADD(0,false,1,this.numeroAmbito,this.ambitoDad,var.ID+"_DOUBLE");
                                           SymTable.ADD(2,false,1,this.numeroAmbito,this.ambitoDad,var.ID+"_CHAR");


                                       }
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
