package Instrucciones;

import Errores.ErrorClass;
import Lenguajes.MetodosVisual;
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

    public void validate(){
        String arg2="";
        if(this.argumentos!=null){
            if(!this.argumentos.isEmpty())
            {
               arg2=Controlador_.verifyArgs(this.argumentos,this.errores);
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
        //this.ambito_=null;
        MetodosVisual.add("close","}","","",9);
    }


}
