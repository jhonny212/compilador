package Arbol.AritAST;

import Arbol.Nodo;
import GramaticaC.declaracion_c;
import Variable.Variable;
import Variable.VariableVAL;


public class NodoAritmetica extends Nodo {



    public NodoAritmetica( Variable var){

        this.variable=var;
    }
    public NodoAritmetica(String operador){
        this.operador=operador;
        this.isOp=true;
    }



    public NodoAritmetica addNodo(NodoAritmetica adder,NodoAritmetica href,String tmp,int fila,int columna){
        if(href.izq!=null){
            href.izq= addNodo(adder, (NodoAritmetica) href.izq,tmp,fila,columna);
            return href;
        }else{
            NodoAritmetica tmp_=href;
            href=new NodoAritmetica(tmp);
            href.addSons(adder,tmp_,fila,columna);
            return href;
        }

    }

    public void chaseSing(){
        if(this.variable!=null){
            this.variable.change();
        }

    }
}
