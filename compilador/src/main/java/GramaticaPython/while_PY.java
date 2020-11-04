package GramaticaPython;

import Arbol.AritAST.ArbolAritmetica;
import Arbol.BoolAST.ArbolBooleano;
import Arbol.BoolAST.NodoBoolean;
import Lenguajes.MetodosVisual;

import java.util.ArrayList;

public class while_PY extends InstruccionPy {
    NodoBoolean nodoBoolean;
    public while_PY(NodoBoolean bool, int f, int c){
        this.nodoBoolean=bool;
        this.FILA=f;
        this.COLUMNA=c-1;
    }

    @Override
    public void validate() {
        ArbolBooleano arbol=null;
        int previo=0,dad=0;
        if(nodoBoolean!=null){
            MetodosVisual.CONTADOR_ETIQ++;
            dad=MetodosVisual.CONTADOR_ETIQ;
            MetodosVisual.add("Et","E",String.valueOf(dad),"",5);
            arbol=new ArbolBooleano(this.variables);
            arbol.errorClass=this.errorClass;
            arbol.recorrer(nodoBoolean);
        }

        MetodosVisual.CONTADOR_ETIQ++;
        int previo2=MetodosVisual.CONTADOR_ETIQ;
        if(nodoBoolean!=null){
            if(nodoBoolean.isOp){
                MetodosVisual.add("","t","","goto E"+MetodosVisual.CONTADOR_ETIQ,3);
            }else{
               arbol.add();
            }
        }
        MetodosVisual.CONTADOR_ETIQ++;
        previo=MetodosVisual.CONTADOR_ETIQ;
        MetodosVisual.add("goto","E",String.valueOf(previo),"",4);
        MetodosVisual.add("Et","E",String.valueOf(previo2),"",5);

        if(this.ambitoPY!=null){
            ambitoPY.errorClass=this.errorClass;

            ambitoPY.add(this.variables);
            MetodosVisual.CONTADOR_AMBITO++;
            ambitoPY.numeroAm=MetodosVisual.CONTADOR_AMBITO;
            ambitoPY.ambitoDad=this.numeroAmbito;
            ambitoPY.validate();
        }
        MetodosVisual.add("goto","E",String.valueOf(dad),"",4);
        MetodosVisual.add("Et","E",String.valueOf(previo),"",5);


    }
}
