package Instrucciones;

import Arbol.AritAST.NodoAritmetica;

public class argumento_dato {
    public String texto;
    public NodoAritmetica nodo=null;

    public argumento_dato(String texto) {
        this.texto = texto;
    }

    public argumento_dato(NodoAritmetica nodo) {
        this.nodo = nodo;
    }
    public argumento_dato(){}
}
