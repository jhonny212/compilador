package Instrucciones;

import Arbol.AritAST.ArbolAritmetica;
import Lenguajes.MetodosVisual;

import java.util.ArrayList;

public class instr_print extends Instruccion{
    public ArrayList<argumento_dato> argumentoDato;
    public boolean isLN;
    public instr_print(ArrayList<argumento_dato> argumentoDato,boolean ln) {
        isLN=ln;
        this.argumentoDato = argumentoDato;
    }
    public instr_print(boolean ln) {
        isLN=ln;
    }


    @Override
    public void validate(boolean valid) {
        if(this.argumentoDato!=null){
            numvar=0;
            this.argumentoDato.stream()
                    .forEach((x)->{
                        if(x.nodo!=null){
                             ArbolAritmetica aritmetica=new ArbolAritmetica(this.variables);
                            aritmetica.errorClass=this.errores;
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
                MetodosVisual.add("print",val1,this.isLN?"\\n":"","",14);
            }else{
                MetodosVisual.add("print","str_asig",this.isLN?"\\n":"","",14);
            }
        }else{
                MetodosVisual.add("print",this.isLN?" \\n":"","","",14);


        }
    }

    int numvar=0;
    String val1,val2;
}
