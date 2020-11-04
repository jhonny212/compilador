package Instrucciones;

import Arbol.AritAST.ArbolAritmetica;
import Arbol.AritAST.NodoAritmetica;
import Arbol.Nodo;
import Errores.ErrorClass;
import Lenguajes.MetodosVisual;
import TablaSimbolos.SymTable;
import Variable.Variable;
import Variable.VariableDeclaracion;

public class instr_for_java extends Instruccion {

    public int fila2,columna2,fila3,columna3;
    NodoAritmetica i,j,k;
    String ID;
    int tipo;
    public String op="<";
    public boolean canAdd=true;
    public instr_for_java(Object [] obj, NodoAritmetica t1,NodoAritmetica t2,
                          int l1,int r1,int l2,int r2,
                          String s1,String s2,ambito_ ambito_){
    this.ambito_=ambito_;
    this.fila2=l1;
    this.columna2=r1;
    this.fila3=l2;
    this.columna3=r2;
    this.op=s1;
    this.j=t1;
    this.k=t2;
    this.ID=String.valueOf((obj[0]));
    this.FILA=(int)obj[1];
    this.COLUMNA=(int)obj[2];
        this.canAdd=false;
    if(obj[4]!=null){
        NodoAritmetica tt1= (NodoAritmetica) obj[4];

        this.i=tt1;
        if(obj[3]!=null){
            this.tipo=(int)obj[3];
            this.canAdd=true;
        }
    }

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
        VariableDeclaracion var_=null;

        ArbolAritmetica arbol1=new ArbolAritmetica(this.variables);
        int etiqueta=0;
        String tmp="";
        if(i!=null){
            var_=new VariableDeclaracion(ID,i,FILA,COLUMNA);
            var_.pass=true;
            var_.TIPO=this.tipo;
            if(this.canAdd){
                this.variables.add(var_);
                var_.string=this.tipo==0?"r":this.tipo==1?"e":"c";
                SymTable.ADD(var_.getTipe_var(),false,1,this.numeroAmbito,this.ambitoDad,var_.ID);

            }else{
                Variable var=this.getVar(this.ID);
                if(var!=null){
                    var.isNull=false;
                    if(var.ID.startsWith("this.") && !this.ID.startsWith("this.")){
                        tmp="this.";
                    }
                }

            }

            arbol1.recorrer(i);
            MetodosVisual.CONTADOR_ETIQ++;
            etiqueta=MetodosVisual.CONTADOR_ETIQ;
            MetodosVisual.add("Asig",arbol1.lastVal,"",tmp+this.ID,2);
            tmp="";
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
                if(var.ID.startsWith("this.") && !this.id2.startsWith("this.")){
                    tmp="this.";
                }
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
        String tmp3="";
        if(!id3.equals(ID))
        {

            Variable var=this.getVar(id3);
            if(var!=null){
                if(var.ID.startsWith("this.") && !this.id3.startsWith("this.")){
                    tmp3="this.";
                }
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
            MetodosVisual.add(this.op,tmp+this.id2,arbol1.lastVal,"goto E"+MetodosVisual.CONTADOR_ETIQ,3);

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
            MetodosVisual.add("asig",arbol1.lastVal,"",tmp3+this.id3,2);
        }
        MetodosVisual.add("goto","E",String.valueOf(etiqueta),"",4);
        MetodosVisual.add("Et","E",String.valueOf(salid),"",5);



    }
}
