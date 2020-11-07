package GramaticaPython;

import GramaticaC.call_c;
import Instrucciones.Instruccion;

public class call_fun_py extends InstruccionPy{
    call_c call;
    public call_fun_py( Instruccion ins,int f,int c){
        this.call=(call_c )ins;
        this.FILA=f;
        this.COLUMNA=c-1;
    }

    @Override
    public void validate() {
        call.validate(false);
    }
}
