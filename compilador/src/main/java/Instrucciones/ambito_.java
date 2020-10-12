package Instrucciones;

import Arbol.AritAST.ArbolAritmetica;
import Arbol.AritAST.NodoAritmetica;
import Errores.ErrorClass;
import GramaticaC.declaracion_c;
import Variable.Variable;
import Variable.VariableDeclaracion;
import controlador.Controlador_;

import java.util.ArrayList;

public class ambito_ {
    ArrayList<Instruccion> instruccions;
    ArrayList<VariableDeclaracion> variables;
    public int numeroAm=0,numTmp=0;
    public int TIPORETORNO;
    public ErrorClass errores=null;
    public int retornosRequeridos=0,contadorRetornos=0;
    public NodoAritmetica retorno;
    public ambito_(NodoAritmetica retorno){
        this.instruccions=new ArrayList<>();
        this.retorno=retorno;
    }
    int fila,columna;
    public ambito_(NodoAritmetica retorno,int fila,int columna){
        this.instruccions=new ArrayList<>();
        this.retorno=retorno;
        this.fila=fila;
        this.columna=columna;
    }

    public ambito_ (ArrayList<Instruccion> instruccions){
        this.instruccions=instruccions;
    }
    public ambito_(){

        this.instruccions=new ArrayList<>();
    }

    public void add(Instruccion in){
        if(this.instruccions==null){
            this.instruccions=new ArrayList<>();
        }
        this.instruccions.add(in);
    }
    public void addIndex(Instruccion instruccion){
        if(this.instruccions==null){
            this.instruccions=new ArrayList<>();
        }
        this.instruccions.add(0,instruccion);
    }
    public int contador=0,ambitoDad=0;
    public int cnt=0;
    public boolean entered=false;
    public void validate(boolean isFun){
        //System.out.println("TABLA AMBIOTS "+this.numeroAm+" DAD:"+this.ambitoDad);
        if(this.instruccions!=null){
            this.instruccions.stream()
                    .forEach((x)->{
                        if(x!=null){

                            x.TIPORETORNO=this.TIPORETORNO;
                            if(variables!=null){
                                x.initDatas(variables);
                            }
                           x.errores=this.errores;
                           x.ambitoDad=this.ambitoDad;
                           x.numeroAmbito=this.numeroAm;
                           x.validate(isFun);

                           this.retornosRequeridos+=x.retornosRequeridos;
                           this.contadorRetornos+=x.contadorRetornos;
                            if(x instanceof instr_declaracion ){
                                variables= x.variables;
                                entered=true;
                            }if(x instanceof declaracion_c){
                                variables= x.variables;
                                entered=true;
                            }

                        }
                        cnt++;
                    });
            Controlador_.verifyInstr(variables,this.errores);
        }else if(!isFun){
             if(this.retorno!=null){
                 this.errores.AddError(2, fila, columna,"","" +
                         "No se permite retornos a un procedimiento solo a funciones");
             }
        }
        if(this.retorno!=null){
            ArbolAritmetica aritmetica=new ArbolAritmetica(this.variables);
            aritmetica.recorrer(this.retorno);
            if(retorno.variable!=null){
                if(retorno.variable.TIPO==0 && this.TIPORETORNO==1){
                    this.errores.AddError(2,fila, columna,"Return","" +
                            "El tipo de retorno no es el mismo");
                }
                }
            this.contadorRetornos=this.retornosRequeridos;
            }
            if(!isFun){
                if(this.retorno!=null){
                    this.errores.AddError(2, fila, columna,"Return","" +
                            "No se permite retornos a un procedimiento solo a funciones");
                }

            }

        }


    }






