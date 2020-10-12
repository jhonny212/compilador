package Instrucciones;

import Errores.ErrorClass;
import Variable.VariableDeclaracion;

import java.util.ArrayList;

public abstract class Instruccion {
  public  String codigo3d="";
  public  String lasValtmp1="",lasValtmp2="";
  public  String asignacionVal="";
  public int TIPORETORNO=0,numeroAmbito;
  public  int ambitoDad=0;
   public ArrayList<VariableDeclaracion> variables=null;
   public int FILA,COLUMNA,tipo=-10;
   public ambito_ ambito_;
   public int contadorRetornos=0,retornosRequeridos=0;
   public ErrorClass errores=null;
   public String string;


   public void initDatas(ArrayList<VariableDeclaracion>variables){
      if(this.variables==null){
         this.variables=variables;
      }else{
         this.variables.addAll(0,variables);
      }
   }

   abstract public void validate(boolean valid);

   protected VariableDeclaracion getVar(String ID){
      if(this.variables==null){
         return null;
      }
      if(ID.startsWith("this.")){
         String tmp=ID.replaceFirst("this.","");
         for (VariableDeclaracion x:this.variables) {
            if(x.ID.equals(tmp) && x.globalVar==1){
               return x;
            }
         }
      }else{
         for (int i = this.variables.size()-1; i >=0 ; i--) {
            VariableDeclaracion x=this.variables.get(i);
            if(x.ID.equals(ID)){
               return x;
            }
         }
      }
     return null;
   }

}
