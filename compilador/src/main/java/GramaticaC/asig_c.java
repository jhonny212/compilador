package GramaticaC;

import Instrucciones.Instruccion;
import Lenguajes.MetodosVisual;
import Variable.VariableDeclaracion;

public class asig_c extends Instruccion {
    public String ID;
    public String  datos;
    public asig_c(String ID,String tipo,int f,int c){
        this.FILA=f;
        this.COLUMNA=c;
        this.datos=tipo;
        this.ID=ID;
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
        VariableDeclaracion x=this.getVar(this.ID);

        if(x==null){
            this.errores.AddError(2,this.FILA,this.COLUMNA,this.ID,"La variable no existe");
        }else{
            x.isNull=false;
            if(this.tipo==1 && x.TIPO==0){
                this.errores.AddError(2,this.FILA,this.COLUMNA,this.ID,"La variable no es del mismo tipo");
            }
            MetodosVisual.add("print","\""+this.datos+"\"","","",14);
            MetodosVisual.add("read","","",this.ID,13);
        }
    }
}
