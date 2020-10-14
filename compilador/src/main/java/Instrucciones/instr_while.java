package Instrucciones;

import Arbol.BoolAST.ArbolBooleano;
import Arbol.BoolAST.NodoBoolean;
import Lenguajes.MetodosVisual;

public class instr_while extends Instruccion{
    public NodoBoolean nodoBoolean;

    public instr_while(ambito_ ambito_, NodoBoolean nodoBoolean){
        this.ambito_=ambito_;
        this.nodoBoolean=nodoBoolean;
    }


    @Override
    public void validate(boolean valid) {
        ArbolBooleano booleano=new ArbolBooleano(this.variables);
        int previo=0,dad=0;
        try{
            MetodosVisual.CONTADOR_ETIQ++;
            dad=MetodosVisual.CONTADOR_ETIQ;
            MetodosVisual.add("Et","E",String.valueOf(dad),"",5);

            booleano.errorClass=this.errores;
            booleano.recorrer(nodoBoolean);
        }catch (Exception ex){}


        MetodosVisual.CONTADOR_ETIQ++;
        int previo2=MetodosVisual.CONTADOR_ETIQ;
        if(nodoBoolean!=null){
            if(nodoBoolean.isOp){
                MetodosVisual.add("","t","","goto E"+MetodosVisual.CONTADOR_ETIQ,3);
            }else{
                booleano.add();
            }
        }
        MetodosVisual.CONTADOR_ETIQ++;
        previo=MetodosVisual.CONTADOR_ETIQ;
        MetodosVisual.add("goto","E",String.valueOf(previo),"",4);
        MetodosVisual.add("Et","E",String.valueOf(previo2),"",5);
        if(this.ambito_!=null){

            ambito_.variables=this.variables;
            ambito_.errores=this.errores;
            MetodosVisual.CONTADOR_AMBITO++;
            ambito_.numeroAm=MetodosVisual.CONTADOR_AMBITO;
            ambito_.ambitoDad=this.numeroAmbito;
            ambito_.validate(valid);
        }
        MetodosVisual.add("goto","E",String.valueOf(dad),"",4);
        MetodosVisual.add("Et","E",String.valueOf(previo),"",5);

    }
}
