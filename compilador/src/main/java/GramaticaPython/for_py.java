package GramaticaPython;

import Arbol.AritAST.ArbolAritmetica;
import Arbol.AritAST.NodoAritmetica;
import Arbol.BoolAST.ArbolBooleano;
import Lenguajes.MetodosVisual;
import Variable.VariableDeclaracion;
import controlador.Controlador_;

import java.util.ArrayList;

public class for_py extends InstruccionPy {
    NodoAritmetica nodo1,nodo2,nodo3;
    public int f2,c2;
    public String ID;
    public for_py(int f1, int c1, String id, int f2, int c2,
                  NodoAritmetica n1,NodoAritmetica n2,NodoAritmetica n3){
        this.FILA=f1;
        this.COLUMNA=c1-1;
        this.f2=f2;
        this.c2=c2;
        this.nodo1=n1;
        this.nodo2=n2;
        this.nodo3=n3;
        this.ID=id;
    }

    @Override
    public void validate() {
        int etiqueta=0;
        if(nodo1!=null){
            ArbolAritmetica arbol=new ArbolAritmetica(this.variables);
            arbol.errorClass=this.errorClass;
            arbol.recorrer(nodo1);
            MetodosVisual.CONTADOR_ETIQ++;
            etiqueta=MetodosVisual.CONTADOR_ETIQ;
            if(Controlador_.canAdd(this.variables,this.ID)){
                if(this.variables==null){
                    variables=new ArrayList<>();
                }
                VariableDeclaracion var=new VariableDeclaracion(this.ID,this.FILA,this.COLUMNA);
                var.isNull=false;
                variables.add(var);
            }
            MetodosVisual.add("Asig",arbol.lastVal,"",this.ID,2);
            MetodosVisual.add("Et","E",String.valueOf(etiqueta),"",5);

        }
        if(nodo2!=null){
            ArbolAritmetica arbol=new ArbolAritmetica(this.variables);
            arbol.errorClass=this.errorClass;
            arbol.recorrer(nodo2);
            MetodosVisual.CONTADOR_ETIQ++;
            MetodosVisual.add("<",this.ID,arbol.lastVal,"goto E"+MetodosVisual.CONTADOR_ETIQ,3);

        }
        MetodosVisual.CONTADOR_ETIQ++;
        int salid=MetodosVisual.CONTADOR_ETIQ;
        MetodosVisual.add("goto","E",String.valueOf(salid),"",4);
        MetodosVisual.add("Et","E",String.valueOf(etiqueta+1),"",5);
        MetodosVisual.CONTADOR_ETIQ++;


        if(this.ambitoPY!=null){
            ambitoPY.errorClass=this.errorClass;
            ambitoPY.add(this.variables);
            MetodosVisual.CONTADOR_AMBITO++;
            ambitoPY.numeroAm=MetodosVisual.CONTADOR_AMBITO;
            ambitoPY.ambitoDad=this.numeroAmbito;
            ambitoPY.validate();
        }
        if(nodo3!=null){
            ArbolAritmetica arbol=new ArbolAritmetica(this.variables);
            arbol.errorClass=this.errorClass;
            arbol.recorrer(nodo3);
            MetodosVisual.add("+",this.ID,arbol.lastVal,this.ID,0);
        }else{
            MetodosVisual.add("+",this.ID,"1",this.ID,0);
        }

        MetodosVisual.add("goto","E",String.valueOf(etiqueta),"",4);
        MetodosVisual.add("Et","E",String.valueOf(salid),"",5);

    }
}
