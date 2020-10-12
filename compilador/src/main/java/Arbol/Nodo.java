package Arbol;

import Arbol.AritAST.NodoAritmetica;
import Variable.Variable;

public class Nodo {
    public Nodo izq,der;
    public Variable variable;
    public boolean isOp=false;
    public String operador;
    public int cambiarSigno=0;
    int FILA,COLUMNA;
    public void addSons(NodoAritmetica v1,NodoAritmetica v2){
        this.izq=v1;
        this.der=v2;
    }
    public void addSons(NodoAritmetica v1,NodoAritmetica v2,int f,int c){
        this.izq=v1;
        this.der=v2;
        this.FILA=f;
        this.COLUMNA=c;
    }

}
