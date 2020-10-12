package GramaticaPython;

import Errores.ErrorClass;
import Variable.VariableDeclaracion;

import java.util.ArrayList;

public abstract class InstruccionPy {
    public int FILA,COLUMNA,EXTRA;
    public ambitoPY ambitoPY=null;
    public ArrayList<VariableDeclaracion> variables;
    public ErrorClass errorClass;
    public boolean ambitoVal=false;
    public abstract void validate();
}
