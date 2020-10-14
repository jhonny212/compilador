package GramaticaPython;

import Arbol.AritAST.ArbolAritmetica;
import Arbol.AritAST.ArbolPY;
import Arbol.AritAST.NodoAritmetica;
import Lenguajes.MetodosVisual;

public class return_py extends InstruccionPy {
    public NodoAritmetica nodo;
    public return_py(int f1,int c1,NodoAritmetica nodo){
        this.nodo=nodo;
        this.FILA=f1;
        this.COLUMNA=c1-1;
    }

    @Override
    public void validate() {
        if(nodo!=null){
            ArbolAritmetica arbol=new ArbolAritmetica(this.variables);
            arbol.errorClass=this.errorClass;
            arbol.recorrer(nodo);
            MetodosVisual.add("return",arbol.lastVal,"","",17);
        }
    }
}
