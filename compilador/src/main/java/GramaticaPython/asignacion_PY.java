package GramaticaPython;

import Arbol.AritAST.ArbolAritmetica;
import Arbol.AritAST.NodoAritmetica;
import Lenguajes.MetodosVisual;

public class asignacion_PY extends InstruccionPy {
    public String ID;
    public NodoAritmetica nodo;
    public input_PY input_py;

    public asignacion_PY(String ID, NodoAritmetica nodo, int f, int c) {
        this.ID = ID;
        this.nodo = nodo;
        this.FILA=f;
        this.COLUMNA=c-1;
    }
    public asignacion_PY(String ID, input_PY nodo, int f, int c) {
        this.ID = ID;
        this.input_py = nodo;
        this.FILA=f;
        this.COLUMNA=c-1;
    }

    @Override
    public void validate() {
        if(nodo!=null){
            ArbolAritmetica arbol=new ArbolAritmetica(this.variables);
            arbol.errorClass=this.errorClass;
            arbol.recorrer(nodo);
            MetodosVisual.add("Asig",arbol.lastVal,"",this.ID,2);
        }
        if(input_py!=null){
            input_py.variables=this.variables;
            input_py.errorClass=this.errorClass;
            input_py.asignar=true;
            input_py.val2=this.ID;
            input_py.validate();
        }
    }
}
