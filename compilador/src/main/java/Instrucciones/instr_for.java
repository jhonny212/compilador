package Instrucciones;

import Arbol.AritAST.ArbolAritmetica;
import Arbol.AritAST.NodoAritmetica;
import Arbol.Nodo;
import Lenguajes.MetodosVisual;
import Variable.*;

import java.util.ArrayList;

public class instr_for extends Instruccion{
    NodoAritmetica i,j,k;
    String ID;
    int tipo;

    public instr_for(String id,NodoAritmetica i, NodoAritmetica j, NodoAritmetica k, int t,ambito_ a,int f,int c){
        if(t==2){
            tipo=1;
        }else{
            tipo=t;
        }
        this.i=i;
        this.j=j;
        this.k=k;

        this.FILA=f;
        this.COLUMNA=c;

        this.ambito_=a;
        this.ID=id;

    }

    @Override
    public void validate(boolean valid) {
        ArbolAritmetica arbol1=null,arbol2=null,arbol3=null;
        int etiqueta=0;
        if(i!=null){
            arbol1=new ArbolAritmetica(this.variables);
            arbol1.errorClass=this.errores;
            arbol1.recorrer(i);
            Variable var=i.variable;
            VariableDeclaracion var_=new VariableDeclaracion(ID,i,this.FILA,this.COLUMNA);
            var_.TIPO=this.tipo;
            MetodosVisual.CONTADOR_ETIQ++;
            etiqueta=MetodosVisual.CONTADOR_ETIQ;
            MetodosVisual.add("Asig",arbol1.lastVal,"",this.ID,2);
            MetodosVisual.add("Et","E",String.valueOf(etiqueta),"",5);
            if(this.variables==null)
            {
            this.variables=new ArrayList<>();
            }
            this.variables.add(var_);
            if(var!=null){
                if(this.tipo==1 && var.TIPO==0){
                    this.errores.AddError(2,FILA,COLUMNA-1,ID,"" +
                            "Error, la variable "+ID+" no es del mismo tipo al \n" +
                            "que se pretende asignar. Se requiere un entero no decimal");
                }
            }
        }else{
            Variable var=this.getVar(this.ID);
            if(var!=null){
                if(this.tipo==1 && var.TIPO==0){
                    this.errores.AddError(2,FILA,COLUMNA-1,ID,"" +
                            "Error, la variable "+ID+" no es del mismo tipo al \n" +
                            "que se pretende asignar. Se requiere un entero no decimal");
                }
            }else{
                System.out.println("no eistexxx");
            }
        }
        if(j!=null){
            arbol2=new ArbolAritmetica(this.variables);
            arbol2.errorClass=this.errores;
            arbol2.recorrer(j);
            Variable var=j.variable;
            if(var!=null){
                if(this.tipo==1 && var.TIPO==0){
                    this.errores.AddError(2,FILA,COLUMNA-1,ID,"" +
                            "Error, la variable limite del ciclo no es del mismo tipo al \n" +
                            "que se pretende asignar. Se requiere un entero no decimal");
                }
            }
            MetodosVisual.CONTADOR_ETIQ++;
            MetodosVisual.add("<",this.ID,arbol2.lastVal,"goto E"+MetodosVisual.CONTADOR_ETIQ,3);

        }




        MetodosVisual.CONTADOR_ETIQ++;
        int salid=MetodosVisual.CONTADOR_ETIQ;
        MetodosVisual.add("goto","E",String.valueOf(salid),"",4);
        MetodosVisual.add("Et","E",String.valueOf(etiqueta+1),"",5);
        MetodosVisual.CONTADOR_ETIQ++;
        if(this.ambito_!=null){
            ambito_.variables=this.variables;
            ambito_.errores=this.errores;
            MetodosVisual.CONTADOR_AMBITO++;
            ambito_.numeroAm=MetodosVisual.CONTADOR_AMBITO;
            ambito_.ambitoDad=this.numeroAmbito;
            ambito_.validate(valid);
        }
        if(k!=null){
            arbol3=new ArbolAritmetica(this.variables);
            arbol3.errorClass=this.errores;
            arbol3.recorrer(k);
            Variable var=k.variable;
            if(var!=null){
                if(this.tipo==1 && var.TIPO==0){
                    this.errores.AddError(2,FILA,COLUMNA-1,ID,"" +
                            "Error, la variable de aumento no es del mismo tipo al \n" +
                            "que se pretende asignar. Se requiere un entero no decimal");
                }
            }
            MetodosVisual.add("+",this.ID,arbol3.lastVal,this.ID,0);
        }else{
            MetodosVisual.add("+",this.ID,"1",this.ID,0);
        }
        MetodosVisual.add("goto","E",String.valueOf(etiqueta),"",4);
        MetodosVisual.add("Et","E",String.valueOf(salid),"",5);


    }
}
