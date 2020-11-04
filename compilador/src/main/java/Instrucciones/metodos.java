package Instrucciones;

import Errores.ErrorClass;
import Lenguajes.Cuadruplas;
import Lenguajes.MetodosVisual;
import TablaSimbolos.SymTable;
import Variable.*;
import controlador.Controlador_;

import java.util.ArrayList;

public class metodos {
    public ambito_ ambito_;
    public boolean isFun;
    public ArrayList<VariableDeclaracion> argumentos=null;
    public ErrorClass errores;
    public int FILA,COLUMNA;
    public String ID;
    public int TIPO=-10;
    public int args=-1;
    public String codigoMetodo,str,string="VB_";
    public metodos(ambito_ ambito_,boolean isFun){
        this.ambito_=ambito_;
        this.isFun=isFun;
        if(ambito_!=null){
            this.ambito_.retornosRequeridos=1;
        }
    }
    public metodos(ambito_ ambito_,ArrayList<VariableDeclaracion> argumento_functions,boolean isFun){
        this.ambito_=ambito_;
        this.isFun=isFun;
        this.argumentos=argumento_functions;
        args=argumento_functions.size();
        if(ambito_!=null){
            this.ambito_.retornosRequeridos=1;
        }
   }

   public void addVars(ArrayList<VariableDeclaracion> variableDeclaracions){
        if(this.argumentos==null){
            this.argumentos=new ArrayList();
        }
        argumentos.addAll(variableDeclaracions);
        argumentos.stream()
                .forEach((x)->{
                    x.isNull=false;
                });
   }
   public void add(ArrayList<VariableDeclaracion>variableDeclaracions){
        if(this.ambito_==null){
            return;
        }
        if(this.ambito_.variables==null){
            this.ambito_.variables=new ArrayList();
        }
        this.ambito_.variables.addAll(variableDeclaracions);
   }
    public void validateSTRING(){

        if(this.argumentos!=null){
            if(!this.argumentos.isEmpty())
            {
                arg2=Controlador_.verifyArgs(this.argumentos);
            }
        }
        if(this.isFun){

            if(this.TIPO==1){
                arg2+="_int";

                str="e";
            }else if(this.TIPO==0){
                arg2+="_float";
                str="r";

            }else if(this.TIPO==2){
                arg2+="_char";
                str="c";
                this.TIPO=1;
            }
        }

        this.codigoMetodo=string+this.ID+arg2;
    }
    String arg2="";
    public void validate(){
        int tamPrevio=SymTable.celdas.size();
        int tamCuad=MetodosVisual.instrucciones.size();
        SymTable.celda tmp=new SymTable.celda(this.isFun?this.TIPO:10,false,0,0,(this.string.equals("VB_")?0:-1),this.codigoMetodo,SymTable.getPos(this.TIPO));
        tmp.IS_FUN=true;
        SymTable.celdas.add(tmp);
        if(this.argumentos!=null){
            if(!this.argumentos.isEmpty())
            {
               Controlador_.verifyArgs(this.argumentos,this.errores);
            }
        }

        tmp.ID=codigoMetodo;
        MetodosVisual.add("Fun",string+this.ID,arg2,"",8);

        if(this.ambito_!=null){
            ambito_.TIPORETORNO=this.TIPO;
            this.ambito_.errores=this.errores;
            this.ambito_.variables=this.argumentos;
            ambito_.retornosRequeridos=1;
            ambito_.ambitoDad=0;
            ambito_.numeroAm=1;
            this.ambito_.validate(this.isFun);

            if(isFun){
                if(ambito_.contadorRetornos<ambito_.retornosRequeridos){
                    this.errores.AddError(2,this.FILA,this.COLUMNA,"El metodo "+this.ID,"" +
                            "El metodo no cumple con los return requeridos, \nverifique o agregue" +
                            "un return al final del metodo");
                }
            }
        }else if(this.isFun){
            this.errores.AddError(2,this.FILA,this.COLUMNA,"El metodo "+this.ID,"" +
                    "El metodo no cumple con los return requeridos, \nverifique o agregue" +
                    "un return al final del metodo");
        }

        int tamSig=SymTable.celdas.size();
        //int tamCuad_next=MetodosVisual.instrucciones.size();
        MetodosVisual.CONTADOR_ETIQ++;
        boolean entro=false;
        for (int i = tamCuad; i < MetodosVisual.instrucciones.size(); i++) {
            Cuadruplas cuadrupla=MetodosVisual.instrucciones.get(i);
            if(cuadrupla.TIPO==17){
                entro=true;
                MetodosVisual.instrucciones.add(i+1,new Cuadruplas("goto","E",String.valueOf(MetodosVisual.CONTADOR_ETIQ),"",4));
                i=i+1;
            }
        }
        if(entro){
            MetodosVisual.add("","E",String.valueOf(MetodosVisual.CONTADOR_ETIQ),"",5);
        }
        tmp.POS_MEMORIA=tamSig-tamPrevio;
        MetodosVisual.add("close","}","","",9);
    }


}
