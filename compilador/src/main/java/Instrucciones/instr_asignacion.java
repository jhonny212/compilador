package Instrucciones;

import Arbol.AritAST.ArbolAritmetica;
import Arbol.AritAST.NodoAritmetica;
import Arbol.Nodo;
import Lenguajes.MetodosVisual;
import Variable.Variable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class instr_asignacion extends Instruccion{
    public String ID;
    public NodoAritmetica nodo=null;
    public instr_input input=null;
    ArrayList<NodoAritmetica>nodoAritmeticas;
    public instr_asignacion(String ID, NodoAritmetica nodo,int f,int c) {
        this.ID = ID;
        this.nodo = nodo;
        this.FILA=f;
        this.COLUMNA=c;
    }
    public instr_asignacion(String ID, Instruccion instrInput,int f,int c) {
        this.ID = ID;
        this.FILA=f;
        this.COLUMNA=c;
        this.input= (instr_input) instrInput;
    }
    public instr_asignacion(String ID,ArrayList<NodoAritmetica> nodos, NodoAritmetica nodo,int f,int c) {
        this.ID = ID;
        this.nodo = nodo;
        this.FILA=f;
        this.COLUMNA=c;
        this.nodoAritmeticas=nodos;
    }

    @Override
    public void validate(boolean valid) {
        Variable var=this.getVar(this.ID);
        String tmp="";
        if(var==null){
            this.errores.AddError(2,FILA,COLUMNA-1,ID,"" +
                    "La variable no existe" +
                    "");
        }else{
            if(var.ID.startsWith("this.") && !this.ID.startsWith("this.")){
                tmp="this.";
            }
            if(var.isFunExter && (this.nodo!=null || this.nodoAritmeticas!=null)){
                this.errores.AddError(2,FILA,COLUMNA-1,ID,"" +
                        "Error de tipos, en asignacion. la variable es de una clase java");
            }else if(var.isConst){
                this.errores.AddError(2,FILA,COLUMNA-1,ID,"" +
                        "La variable es constante, no se le puede asignar valor");
            }
        }


        if(nodo!=null){

            ArbolAritmetica arbolAritmetica=new ArbolAritmetica(this.variables);
            arbolAritmetica.errorClass=this.errores;
            arbolAritmetica.ID=this.ID;
            arbolAritmetica.kindVar=var==null?"e":var.string;
            arbolAritmetica.recorrer(nodo);
            if(this.nodoAritmeticas==null){
                MetodosVisual.add("Asig",arbolAritmetica.lastVal,"",tmp+this.ID,2);
            }else{
                try {
                    Integer.parseInt(arbolAritmetica.lastVal);
                    this.lasValtmp2=arbolAritmetica.lastVal;
                }catch (Exception ex){
                    //this.lasValtmp2=arbolAritmetica.lastVal+"vector";
                    if(start("t_1") || start("t_2") || start("t_3")
                    || start("int_num") || start("char_num") || start("double_num")){
                        this.lasValtmp2=arbolAritmetica.lastVal+"vector";
                    }else{
                        this.lasValtmp2=arbolAritmetica.lastVal;
                    }
                    MetodosVisual.add("asig",arbolAritmetica.lastVal,"",this.lasValtmp2,2);
                }

            }

            if(var!=null){
                var.isNull=false;
                if(nodo.variable!=null){
                    if(var.TIPO==1 && nodo.variable.TIPO==0){
                        this.errores.AddError(2,FILA,COLUMNA-1,ID,"" +
                                "Error, la variable "+ID+" no es del mismo tipo al \n" +
                                "que se pretende asignar. Se requiere un entero no decimal");
                    }
                }

            }

        }else{
                if(this.input!=null){
                this.input.initDatas(this.variables);
                this.input.asignar=true;
                this.input.val2=this.ID;
                this.input.validate(valid);
                var.isNull=false;
                if(var.TIPO==1 && this.input.tipo==0){
                    this.errores.AddError(2,FILA,COLUMNA-1,ID,"" +
                            "Error, la variable "+ID+" no es del mismo tipo al \n" +
                            "que se pretende asignar. Se requiere un entero no decimal");
                }
            }

        }
        cnt=0;
        if(this.nodoAritmeticas!=null){
            this.nodoAritmeticas.stream()
                    .forEach((x)->{
                        if(cnt>0){
                            MetodosVisual.add("Asig",this.lasValtmp1,"","int_num3",2);
                            lasValtmp1="int_num3";

                        }
                        ArbolAritmetica aritmetica=new ArbolAritmetica(this.variables);
                        aritmetica.recorrer(x);
                        if(cnt==0){
                            this.lasValtmp1=aritmetica.lastVal;
                        }else{
                            MetodosVisual.add("*",lasValtmp1,this.ID+"_tam"+(cnt-1),"t_2",0);
                            MetodosVisual.add("+","t_2",aritmetica.lastVal,"t_2",0);
                            lasValtmp1="t_2";
                        }
                        if(x.variable!=null){
                            if(x.variable.TIPO==0){
                                this.errores.AddError(2,this.FILA,this.COLUMNA-1,ID,"" +
                                        "Un vector, en su posicion solo acepta enteros");
                            }
                        }
                        cnt++;
                    });
            MetodosVisual.add("Asig",this.lasValtmp1,this.lasValtmp2,this.ID,10);
        }
    }
    int cnt=0;

    boolean start(String init){
        return this.lasValtmp2.startsWith(init);
    }
}
