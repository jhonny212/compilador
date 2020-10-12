package Arbol.BoolAST;

import Arbol.Nodo;

public class NodoBoolean extends Nodo {
    public String condicional="";
    public int contadorEtiquetaSalida,contadorEtiquetaSiguiente;

    public NodoBoolean(Nodo izq,String op,Nodo der){
        this.isOp=true;
        this.izq=izq;
        this.der=der;
        this.operador=op;
    }
    public boolean canAdd=true;
    public NodoBoolean(Nodo izq){
        this.izq=izq;
        this.isOp=false;


    }
    public NodoBoolean(Nodo izq,Nodo der,String op){
        this.izq=izq;
        this.der=der;
        this.canAdd=false;
        this.isOp=false;
        this.operador=op;
    }

}
