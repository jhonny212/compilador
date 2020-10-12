package Instrucciones;

import Arbol.AritAST.NodoAritmetica;
import Errores.ErrorClass;
import Lenguajes.MetodosVisual;
import Variable.*;

import java.util.ArrayList;

public class Sub_case {
    public ErrorClass errorClass;
    public Variable nodoAritmetica;
    public NodoAritmetica nodoRetorno;
    public int FILA,COLUMNA;
    public ambito_ ambito_;
    public int etiqueta,numeroAmbito,ambitoDad=0;
    public int retorno,retornosRequeridos=0,contadorRetornos=0;
    public boolean isDefault;
    public ArrayList<VariableDeclaracion> variables;
    public Sub_case(Variable nodoAritmetica, int FILA, int COLUMNA,
                    Instrucciones.ambito_ ambito_,
                    int  retorno
                    , boolean isDefault,
                    NodoAritmetica nodo
                    ) {

        this.nodoAritmetica = nodoAritmetica;
        this.FILA = FILA;
        this.COLUMNA = COLUMNA;
        this.ambito_ = ambito_;
        this.retorno = retorno;

        this.isDefault=isDefault;
        this.nodoRetorno=nodo;

    }
    public String getStr(){
        return nodoAritmetica==null?"":nodoAritmetica.getStr();
    }

    public void validate(boolean valid){

        if(!valid && this.retorno==1){
            this.errorClass.AddError(2, FILA, -1,String.valueOf(nodoAritmetica.getValor()),"" +
                    "Error, no debe haber retornos");
        }


        if(this.ambito_!=null){
            this.ambito_.variables=this.variables;
            ambito_.errores=this.errorClass;
            MetodosVisual.CONTADOR_AMBITO++;
            ambito_.numeroAm=MetodosVisual.CONTADOR_AMBITO;
            ambito_.ambitoDad=this.numeroAmbito;
            this.ambito_.validate(valid);
            this.contadorRetornos+= ambito_.contadorRetornos;
            this.retornosRequeridos+= ambito_.retornosRequeridos;

        }

        if(this.nodoRetorno!=null){
           this.contadorRetornos=this.retornosRequeridos+1;
        }
    }

    public boolean rtn(){
        return this.retorno==2;
    }
}
