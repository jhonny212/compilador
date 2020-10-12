package GramaticaPython;

import Arbol.AritAST.ArbolAritmetica;
import Instrucciones.argumento_dato;
import Lenguajes.MetodosVisual;

import java.util.ArrayList;

public class print_PY extends InstruccionPy {
    ArrayList<argumento_dato> datos;
    public print_PY(int f1, int c1, ArrayList<argumento_dato>datos){
        this.FILA=f1;
        this.COLUMNA=c1-1;
        this.datos=datos;
    }

    @Override
    public void validate() {
        if(datos!=null){
            numvar=0;
            datos.stream()
                    .forEach((x)->{
                        if(x!=null){
                            if(x.nodo!=null) {
                                ArbolAritmetica arbol = new ArbolAritmetica(this.variables);
                                arbol.errorClass = this.errorClass;
                                arbol.recorrer(x.nodo);
                                val1 = arbol.lastVal;
                            }else{
                                val1="\""+x.texto+"\"";
                            }
                            if(numvar==0){
                                MetodosVisual.add("",val1,"","str_asig",6);
                                numvar=1;
                            }else{
                                numvar=2;
                                MetodosVisual.add("concat","str_asig",val1,"str_asig",6);
                            }
                        }
                    });
            if(numvar==1){
                MetodosVisual.add("print",val1,"\\n","",14);
            }else{
                MetodosVisual.add("print","str_asig","\\n","",14);
            }
        }else{
            MetodosVisual.add("print"," \\n","","",14);

        }
    }
    int numvar=0;
    String val1,val2;
}
