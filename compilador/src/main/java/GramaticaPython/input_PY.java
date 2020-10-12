package GramaticaPython;

import Arbol.AritAST.ArbolAritmetica;
import Arbol.AritAST.ArbolPY;
import Instrucciones.argumento_dato;
import Lenguajes.MetodosVisual;

import java.util.ArrayList;

public class input_PY extends InstruccionPy {
    ArrayList<argumento_dato> datos;
    public int TIPO;
    public String string;
    public input_PY(int f1, int c1, int t1, String d, ArrayList<argumento_dato> datos){
        this.FILA=f1;
        this.COLUMNA=c1-1;
        this.TIPO=t1;
        this.datos=datos;
        this.string=d;

    }

    public boolean asignar=false;
    @Override
    public void validate() {
        if(this.datos!=null){
            this.datos.stream()
                    .forEach((x)->{
                        if(x.nodo!=null){
                            ArbolAritmetica aritmetica=new ArbolAritmetica(this.variables);
                            aritmetica.errorClass=this.errorClass;
                            aritmetica.recorrer(x.nodo);
                            val1=aritmetica.lastVal;
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
                    });
            if(numvar==1){
                MetodosVisual.add("print",val1,"","",14);
            }else{
                MetodosVisual.add("print","str_asig","","",14);
            }
            if(!asignar){
                MetodosVisual.add("read","","","",13);
            }else{
                MetodosVisual.add("read","","",val2,13);
            }
        }else{
            if(!asignar){
                MetodosVisual.add("read","","","",13);
            }else{
                MetodosVisual.add("read","","",val2,13);
            }
        }

    }
    int numvar=0;
    String val1,val2;
}
