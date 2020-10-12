package Arbol.AritAST;

import Arbol.Arbol;
import Errores.ErrorClass;
import GramaticaPython.asignacion_PY;

import java.util.ArrayList;

public class ArbolPY {
    ErrorClass errorClass; ArrayList<asignacion_PY> vars;
    public ArbolPY(ErrorClass errorClass, ArrayList<asignacion_PY> vars){
        this.errorClass=errorClass;
        this.vars=vars;
    }
    public void recorrer(NodoAritmetica main){

    }
}
