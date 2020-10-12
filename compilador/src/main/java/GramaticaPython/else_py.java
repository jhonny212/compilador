package GramaticaPython;

import Arbol.AritAST.ArbolAritmetica;
import Arbol.BoolAST.ArbolBooleano;
import Arbol.BoolAST.NodoBoolean;
import Lenguajes.MetodosVisual;

public class else_py extends InstruccionPy {
    NodoBoolean nodoBoolean;
    public else_py(int f1, int c1, NodoBoolean nodoBoolean){
        this.FILA=f1;
        this.COLUMNA=c1-1;
        this.nodoBoolean=nodoBoolean;
    }

    @Override
    public void validate() {
        if(!ambitoVal){
            if(nodoBoolean!=null){
                ArbolBooleano arbol=new ArbolBooleano(this.variables);
                arbol.errorClass=this.errorClass;
                arbol.recorrer(nodoBoolean);
                MetodosVisual.CONTADOR_ETIQ++;
                etiqueta=MetodosVisual.CONTADOR_ETIQ;
                if(nodoBoolean.isOp){
                    MetodosVisual.add("","t","","goto E"+MetodosVisual.CONTADOR_ETIQ,3);
                }else{
                    arbol.add();
                }
            }else{
                MetodosVisual.CONTADOR_ETIQ++;
                etiqueta=MetodosVisual.CONTADOR_ETIQ;
                MetodosVisual.add("goto","E",String.valueOf(etiqueta),"",4);
            }
        }else{
            MetodosVisual.add("Et","E",String.valueOf(etiqueta),"",5);

            if(this.ambitoPY!=null){
                ambitoPY.add(this.variables);
                ambitoPY.errorClass=this.errorClass;
                ambitoPY.validate();
            }
            if(salida!=-1){
                MetodosVisual.add("goto","E",String.valueOf(salida),"",4);
            }
        }
    }
    int etiqueta=0,salida=-1;
}
