package GramaticaC;

import Arbol.AritAST.ArbolAritmetica;
import Instrucciones.Instruccion;
import Instrucciones.argumento_dato;
import Lenguajes.MetodosVisual;

import java.util.ArrayList;

public class print_c  extends Instruccion {
    public String cadena;
    public print_c(String str){
        this.cadena=str;
    }
    public String datos;
    public ArrayList<argumento_dato> args;
    public print_c(){}
    public print_c(String  tipo, ArrayList<argumento_dato> list){
        this.args=list;
        this.datos=tipo;
        int tmp=tipo.indexOf("%d");
        if(tmp!=-1){
            this.tipo=1;
            this.datos=datos.replaceFirst("%d","");
            this.string="entero";
        }else{
            tmp=tipo.indexOf("%f");
            if(tmp!=-1){
                this.tipo=0;
                this.datos=datos.replaceFirst("%f","");
                this.string="real";
            }else{
                this.tipo=1;
                this.datos=datos.replaceFirst("%c","");
                this.string="caracter";
            }
        }

    }

    @Override
    public void validate(boolean valid) {
        if(this.cadena!=null){
            MetodosVisual.add("print","\""+cadena+"\"","","",14);
        }
        if(this.datos!=null){
            MetodosVisual.add("print","\""+datos+"\"","","",14);
        }


        if(this.args!=null){
            numvar=0;
            this.args.stream()
                    .forEach((x)->{
                        if(x.nodo!=null){
                            ArbolAritmetica aritmetica=new ArbolAritmetica(this.variables);
                            aritmetica.errorClass=this.errores;
                            aritmetica.recorrer(x.nodo);
                            if(x.nodo.variable.TIPO==0 && this.tipo==1){
                                this.errores.AddError(2,this.FILA,this.COLUMNA,"En el print solo acepta "+this.string);
                            }
                            val1=aritmetica.lastVal;
                        }else{
                            val1="\""+x.texto+"\"";
                        }
                        MetodosVisual.add("print",val1,"","",14);

                        /*if(numvar==0){
                            MetodosVisual.add("",val1,"","str_asig",6);
                            numvar=1;
                        }else{
                            numvar=2;
                            MetodosVisual.add("concat","str_asig",val1,"str_asig",6);
                        }*/

                    });
            //MetodosVisual.add("concat",this.datos,val1,"str_asig",6);
            //MetodosVisual.add("print","str_asig","","",14);
        }
    }
    int numvar=0;
    String val1,val2;
}
