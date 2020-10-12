package Instrucciones;

import Arbol.AritAST.ArbolAritmetica;
import Arbol.AritAST.NodoAritmetica;
import Lenguajes.MetodosVisual;
import Variable.Variable;
import Variable.VariableDeclaracion;

import java.util.ArrayList;

public class instr_declaracion extends Instruccion{
    int tipo;
    public instr_declaracion(ArrayList<VariableDeclaracion>variableDeclaracions,int tipo){
        this.variables=variableDeclaracions;
        this.tipo=tipo;
    }


    @Override
    public void validate(boolean valid) {
        this.variables.stream()
                .forEach((x)->{
                    if(x!=null && !x.pass){
                        //System.err.println("AMBITO: "+this.numeroAmbito+" ID: "+x.ID+" TIPO:"+x.TIPO+ " STR: "+x.string+" DAD: "+this.ambitoDad);
                    }
                });

        this.variables.stream()
                .forEach((x)->{
                    if(!x.isNull()){
                       if(!x.pass){
                           if(x.nodo==null){
                               if(x.variableVECTOR!=null){
                                   x.variableVECTOR.validate(this.variables,this.errores);
                                   x.variableVECTOR.TIPO=this.tipo;
                               }
                           }else{
                               ArbolAritmetica arbol=new ArbolAritmetica(this.variables);
                               arbol.errorClass=this.errores;
                               arbol.kindVar=x.string;
                               arbol.recorrer((NodoAritmetica) x.nodo);
                               MetodosVisual.add("Decl",arbol.lastVal,"",x.ID,1,x.string);
                               Variable var=x.nodo.variable;
                               if(var.TIPO==0 && this.tipo==1){
                                   this.errores.AddError(2,x.fila,x.columna-1,x.ID,"" +
                                           "Error, la variable "+x.ID+" no es del mismo tipo al \n" +
                                           "que se pretende asignar. Se requiere un entero no decimal");
                               }
                               x.nodo.variable.TIPO=this.tipo;
                           }

                          //System.out.println(x.ID+"="+lastVals);

                       }
                       x.pass=true;
                      }
                });
    }
}
