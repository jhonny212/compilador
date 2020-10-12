package Instrucciones;

import Arbol.Arbol;
import Arbol.AritAST.ArbolAritmetica;
import Lenguajes.MetodosVisual;

import java.util.ArrayList;

public class instr_input extends Instruccion{
    public ArrayList<argumento_dato> argumentoDato;

    public instr_input(ArrayList<argumento_dato> argumentoDato) {
        this.argumentoDato = argumentoDato;
    }
    public instr_input(ArrayList<argumento_dato> argumentoDato,int tipo,String type,int f,int c) {
        this.argumentoDato = argumentoDato;
        this.FILA=f;
        this.COLUMNA=c;
        this.tipo=tipo;
        this.string=type;
    }
    public instr_input(int tipo,String type,int f,int c) {

        this.FILA=f;
        this.COLUMNA=c;
        this.tipo=tipo;
        this.string=type;
    }


    public instr_input() {
        this.argumentoDato = null;
    }
    public boolean asignar=false;
    @Override
    public void validate(boolean valid) {
           if(this.argumentoDato!=null){
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
