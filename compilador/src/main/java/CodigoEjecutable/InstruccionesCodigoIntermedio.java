package CodigoEjecutable;

import Lenguajes.Cuadruplas;
import TablaSimbolos.SymTable;

public class InstruccionesCodigoIntermedio {
    CodigoIntermedio codigoIntermedio;
    public InstruccionesCodigoIntermedio(CodigoIntermedio codigoIntermedio) {
        this.codigoIntermedio=codigoIntermedio;
    }

    public void addCuadrupla(String OP, String ARG1, String ARG2, int TIPO){

    }

    public void CrearArgumento(Cuadruplas cuadrupla){

    }
    public String INT="",DOUBLE="",CHAR="";
    public void buscarID(String ID,String funcion){
        int posicionInt=0,posicionDouble=0,posicionChar=0;
        INT="";DOUBLE="";CHAR="";
        for (int i = 0; i <SymTable.celdas.size() ; i++) {
            SymTable.celda celda=SymTable.celdas.get(i);

        }
    }


}
