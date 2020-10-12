package Instrucciones;

import Arbol.BoolAST.ArbolBooleano;
import Arbol.BoolAST.NodoBoolean;
import Lenguajes.MetodosVisual;

public class instr_dowhile extends Instruccion{
    public NodoBoolean nodoBoolean;
    public instr_dowhile(ambito_ ambito_, NodoBoolean nodoBoolean){
        this.ambito_=ambito_;
        this.nodoBoolean=nodoBoolean;
    }

    @Override
    public void validate(boolean valid) {
        ArbolBooleano booleano=new ArbolBooleano(this.variables);
        MetodosVisual.CONTADOR_ETIQ++;
        int etiqueta=MetodosVisual.CONTADOR_ETIQ;
        MetodosVisual.add("Et","E",String.valueOf(etiqueta),"",5);
        if(this.ambito_!=null){
            ambito_.errores=this.errores;
            ambito_.variables=this.variables;
            MetodosVisual.CONTADOR_AMBITO++;
            ambito_.numeroAm=MetodosVisual.CONTADOR_AMBITO;
            ambito_.ambitoDad=this.numeroAmbito;
            ambito_.validate(valid);
            
        }
        try{
            booleano.errorClass=this.errores;
            booleano.recorrer(nodoBoolean);
            booleano.add(etiqueta);
        }catch (Exception ex){}



    }
}
