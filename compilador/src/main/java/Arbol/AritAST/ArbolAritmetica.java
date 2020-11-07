package Arbol.AritAST;

import Arbol.Arbol;
import Lenguajes.MetodosVisual;
import Variable.*;

import Variable.VariableDeclaracion;

import java.util.ArrayList;

public class ArbolAritmetica extends Arbol {

    public ArbolAritmetica(ArrayList<VariableDeclaracion>list){
        this.tablaSimbolos=list;
    }
    public void recorrer(NodoAritmetica href){

        if(href.isOp){
            validate(href);
            numeroVar=numeroVar==0?1:0;
            this.lastVal=this.kindVar+(numeroVar);

        }else if(href.variable!=null){
            Variable variable1=href.variable;
            if(variable1 instanceof VariableVECTOR){
                Variable t1=this.getVar(variable1.ID);
                if(t1==null){
                    this.errorClass.AddError(2,variable1.fila,variable1.columna,variable1.ID,"No existe la variable");
                }else{
                    verify(variable1,t1);
                    href.variable=t1;
                }
                variable1.string=t1==null?"r":t1.string;
                ((VariableVECTOR)variable1).validate2(this.tablaSimbolos,this.errorClass);
                this.lastVal=variable1.getStr();
            }
            else if(variable1 instanceof VariableMETODO){
                ((VariableMETODO)variable1).declaracion_c.errores=this.errorClass;
                variable1.lasValtmp1="%";

                ((VariableMETODO)variable1).validate(this.tablaSimbolos);
                this.lastVal=variable1.getStr();
                if(variable1.TIPO==-10){
                    this.errorClass.AddError(2,((VariableMETODO) variable1).declaracion_c.FILA,
                            ((VariableMETODO) variable1).declaracion_c.COLUMNA,((VariableMETODO) variable1).declaracion_c.metodo,"" +
                            "El metodo es un procedimiento, no una funcion por lo que no hay retorno");
                }

            }else{
                this.lastVal=variable1.getStr();
                if(variable1.isNull()){
                VariableDeclaracion t1=this.getVar(variable1.ID);

                if(t1!=null){
                    try{
                        if(t1.ID.startsWith("this.") && !variable1.ID.startsWith("this.")){
                            this.lastVal="this."+this.lastVal;
                        }
                    }catch (Exception ex){}
                    if(t1.variableVECTOR!=null){
                        this.errorClass.AddError(2,variable1.fila,variable1.columna,variable1.ID,"La variable es un vector, asigne una posicion al vector para poder operar");
                    }
                    verify(variable1,t1);
                    if(t1.isNull() ){
                        this.errorClass.AddError(2,variable1.fila,variable1.columna,variable1.ID,"La variable no ha sigo inicializada");
                    }
                    variable1.TIPO=t1.TIPO;
                    variable1.isNull=false;
                }else {
                    this.errorClass.AddError(2,variable1.fila,variable1.columna,variable1.ID,"No existe la variable");

                }
            }

        }}


    }

    void validate(NodoAritmetica href){
        if(href.izq!=null){
            validate((NodoAritmetica) href.izq);
        }
        if(href.der!=null){
            validate((NodoAritmetica) href.der);
        }
        if(href.der==null || href.izq==null){
            return;
        }


        if(href.isOp){
            String value1="",value2="";
            int tipo1=1;
            Variable variable1=href.izq.variable;
            Variable variable2=href.der.variable;
            int vector=0,metodo=0;
            if(variable1 instanceof VariableVECTOR){
                vector=1;
                Variable t1=this.getVar(variable1.ID);

                if(t1==null){
                    variable1.string="e";
                }else{
                    verify(variable1,t1);
                    variable1.string=t1.string;
                }

                ((VariableVECTOR)variable1).validate2(this.tablaSimbolos,this.errorClass);

                if(t1==null){
                    this.errorClass.AddError(2,variable1.fila,variable1.columna,variable1.ID,"No existe la variable");

                }else{
                    tipo1=t1.TIPO;
                }
                value1=variable1.getStr();
                variable1=t1;

            }
            else if(variable1 instanceof VariableMETODO){
                metodo=1;
                ((VariableMETODO)variable1).declaracion_c.errores=this.errorClass;
                variable1.lasValtmp1="%";
                ((VariableMETODO)variable1).validate(this.tablaSimbolos);
                value1=variable1.getStr();
                if(variable1.TIPO==-10){
                    this.errorClass.AddError(2,((VariableMETODO) variable1).declaracion_c.FILA,
                            ((VariableMETODO) variable1).declaracion_c.COLUMNA,((VariableMETODO) variable1).declaracion_c.metodo,"" +
                                    "El metodo es un procedimiento, no una funcion por lo que no hay retorno");
                }
            }else{
                value1=variable1.getStr();
                if(variable1.isNull()){
                    int c1=variable1.columna;
                    int f1=variable1.fila;

                    VariableDeclaracion t1=this.getVar(variable1.ID);

                    if(t1!=null){
                        try{
                            if(t1.ID.startsWith("this.") && !variable1.ID.startsWith("this.")){
                                value1="this."+value1;
                            }
                        }catch (Exception ex){}
                        if(t1.variableVECTOR!=null){
                            this.errorClass.AddError(2,f1,c1,variable1.ID,"La variable es un vector, asigne una posicion al vector para poder operar");
                        }
                        verify(variable1,t1);
                        variable1=t1;
                        if(t1.isNull() ){
                            this.errorClass.AddError(2,f1,c1,variable1.ID,"La variable no ha sigo inicializada");
                        }
                    }else {
                        this.errorClass.AddError(2,f1,c1,variable1.ID,"No existe la variable");
                    }

                }
            }

            if(variable2 instanceof VariableVECTOR){
                if(vector==1){
                    String x=tipo1==0?"float_ex_num"+numeroVar:"int_ex_num"+numeroVar;
                    MetodosVisual.add("Asig",value1,"",x,2);
                    value1=x;
                }
                Variable t1=this.getVar(variable2.ID);

                if(t1==null){
                    variable2.string="e";
                }else{
                    verify(variable2,t1);
                    variable2.string=t1.string;
                }
                ((VariableVECTOR)variable2).validate2(this.tablaSimbolos,this.errorClass);
                value2=variable2.getStr();
                if(t1==null){
                    this.errorClass.AddError(2,variable2.fila,variable2.columna,variable2.ID,"No existe la variable");
                }
                variable2=t1;
            }
            else if(variable2 instanceof VariableMETODO){
                if(metodo==1){
                    MetodosVisual.add("Asig",value1,"","call_fun_2"+(variable1.TIPO==1?"_int":"_double"),2);
                    value1="call_fun_2"+(variable1.TIPO==1?"_int":"_double");
                }
                ((VariableMETODO)variable2).declaracion_c.errores=this.errorClass;
                variable2.lasValtmp1="%";
                ((VariableMETODO)variable2).validate(this.tablaSimbolos);
                value2=variable2.getStr();
                if(variable2.TIPO==-10){
                    this.errorClass.AddError(2,((VariableMETODO) variable2).declaracion_c.FILA,
                            ((VariableMETODO) variable2).declaracion_c.COLUMNA,((VariableMETODO) variable2).declaracion_c.metodo,"" +
                                    "El metodo es un procedimiento, no una funcion por lo que no hay retorno");
                }


            }else{
                value2=variable2.getStr();
                if(variable2.isNull()){
                    int f1=variable2.fila;
                    int c1=variable2.columna;
                    VariableDeclaracion t1=this.getVar(variable2.ID);
                    if(t1!=null){
                        try{
                            if(t1.ID.startsWith("this.") && !variable2.ID.startsWith("this.")){
                                value2="this."+value2;
                            }
                        }catch (Exception ex){}
                        if(t1.variableVECTOR!=null){
                            this.errorClass.AddError(2,f1,c1,variable2.ID,"La variable es un vector, asigne una posicion al vector para poder operar");
                        }
                        verify(variable2,t1);
                        variable2=t1;
                        if(t1.isNull()){
                            this.errorClass.AddError(2,f1,c1,variable2.ID,"La variable no ha sigo inicializada");
                        }
                    }else{
                        this.errorClass.AddError(2,f1,c1,variable2.ID,"No existe la variable");
                    }

                }
            }
            try{
                int min=Math.min(variable1.TIPO,variable2.TIPO);
                if(min<0){
                    min=Math.max(variable1.TIPO,variable2.TIPO);
                }
                String string= min==0? "r":"e";
                this.kindVar=string.equals("r")?"float_num":"int_num";
                if(href.cambiarSigno==1){
                  href.variable=new VariableVAL("-"+this.kindVar+numeroVar,min,string);
                }else{
                    href.variable=new VariableVAL(this.kindVar+numeroVar,min,string);
                }
            }catch (Exception ex){
                this.kindVar="float_num";
                if(href.cambiarSigno==1){
                    href.variable=new VariableVAL("-"+this.kindVar+numeroVar,1,"e");
                }else{
                    href.variable=new VariableVAL(this.kindVar+numeroVar,1,"e");
                }
            }
                if(value1.equals("x") ){
                    System.out.println("");
                }
                MetodosVisual.add(href.operador+"",value1,value2,this.kindVar+numeroVar,0);
                numeroVar=numeroVar==0?1:0;
        }
    }

    public int numeroVar=0;



    public void verify(Variable variable1,Variable t1){
        try {
            if(t1.isFunExter){
                this.errorClass.AddError(2,variable1.fila,variable1.columna,variable1.ID,"" +
                        "La variable es de tipo java, no puede ser operada");
            }

        }catch (Exception ex){}
    }

}
