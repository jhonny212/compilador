package Variable;

import Arbol.AritAST.NodoAritmetica;

public class VariablePY extends Variable{
    public VariablePY(String ID, int fila, int columna, NodoAritmetica nodoAritmetica){
        this.ID=ID;
        this.nodo=nodoAritmetica;
        this.fila=fila;
        this.columna=columna;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public Object getValor() {
        return null;
    }

    @Override
    public void change() {

    }

    @Override
    public String getStr() {
        return null;
    }
}
