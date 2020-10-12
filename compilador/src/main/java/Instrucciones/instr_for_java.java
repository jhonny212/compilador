package Instrucciones;

import Arbol.AritAST.ArbolAritmetica;
import Arbol.AritAST.NodoAritmetica;
import Arbol.Nodo;
import Lenguajes.MetodosVisual;
import Variable.Variable;
import Variable.VariableDeclaracion;

public class instr_for_java extends Instruccion {
    public String ID2,ID3;
    public int fila2,columna2,fila3,columna3;
    NodoAritmetica i,j,k;
    String ID;
    int tipo;

    public instr_for_java(Object [] obj, NodoAritmetica t1,NodoAritmetica t2,
                          int l1,int r1,int l2,int r2,
                          String s1,String s2,ambito_ ambito_){

    }
    public String id2,id3;
    public instr_for_java(String id,NodoAritmetica t1,NodoAritmetica t2,NodoAritmetica t3
                          ,int tipos,ambito_ ambito_,int f1,int c1,String id2,int f2,int c2,
                          String id3,int f3,int c3){
        this.FILA=f1;
        this.COLUMNA=c1;
        this.fila2=f2;
        this.columna2=c2;
        this.fila3=f3;
        this.columna3=c3;
        this.ID=id;
        this.i=t1;
        this.j=t2;
        this.id2=id2;
        this.id3=id3;
        this.k=t3;
        this.ambito_=ambito_;
        this.tipo=tipos;
    }

    @Override
    public void validate(boolean valid) {

        ArbolAritmetica arbol1=new ArbolAritmetica(this.variables);
        int etiqueta=0;
        if(i!=null){
            VariableDeclaracion var_=new VariableDeclaracion(ID,i,FILA,COLUMNA);
            this.variables.add(var_);
            var_.TIPO=this.tipo;
            arbol1.recorrer(i);
            MetodosVisual.CONTADOR_ETIQ++;
            etiqueta=MetodosVisual.CONTADOR_ETIQ;
            MetodosVisual.add("Asig",arbol1.lastVal,"",this.ID,2);
            MetodosVisual.add("Et","E",String.valueOf(etiqueta),"",5);

            var_.isNull=false;
            switch (this.tipo){
                case 0:
                    var_.string="r";
                    break;
                case 1:
                    var_.string="e";
                    break;
                case 2:
                    var_.string="c";
                    break;
            }
            Variable var=i.variable;
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
                this.errores.AddError(2,FILA,COLUMNA-1,ID,"" +
                        "Error, la variable "+ID+" no existe");
            }
        }
        if(!id2.equals(ID))
         {
            Variable var=this.getVar(id2);
            if(var!=null){
                if(this.tipo==1 && var.TIPO==0){
                    this.errores.AddError(2,fila2,columna2-1,id2,"" +
                            "Error, la variable "+id2+" no es del mismo tipo al \n" +
                            "que se pretende asignar. Se requiere un entero no decimal");
                }
            }else{
                this.errores.AddError(2,FILA,COLUMNA-1,ID,"" +
                        "Error, la variable "+ID+" no existe");
            }
        }
        if(!id3.equals(ID))
        {

            Variable var=this.getVar(id3);
            if(var!=null){
                if(this.tipo==1 && var.TIPO==0){
                    this.errores.AddError(2,fila3,columna3-1,id3,"" +
                            "Error, la variable "+id3+" no es del mismo tipo al \n" +
                            "que se pretende asignar. Se requiere un entero no decimal");
                }
            }else{
                this.errores.AddError(2,FILA,COLUMNA-1,ID,"" +
                        "Error, la variable "+ID+" no existe");
            }
        }

        if(j!=null){
            arbol1.recorrer(j);
            MetodosVisual.CONTADOR_ETIQ++;
            MetodosVisual.add("<",this.id2,arbol1.lastVal,"goto E"+MetodosVisual.CONTADOR_ETIQ,3);

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
            arbol1.recorrer(k);
            if(id3.equals(ID)){
                Variable var=k.variable;
                if(var!=null){
                    if(this.tipo==1 && var.TIPO==0){
                        this.errores.AddError(2,fila3,columna3-1,id3,"" +
                                "Error en el valor de aumento del ciclo for, la variable "+id3+" no es del mismo tipo al \n" +
                                "que se pretende asignar. Se requiere un entero no decimal");
                    }
                }
            }
            MetodosVisual.add("+","",arbol1.lastVal,this.id3,0);
        }
        MetodosVisual.add("goto","E",String.valueOf(etiqueta),"",4);
        MetodosVisual.add("Et","E",String.valueOf(salid),"",5);



    }
}
