package GramaticaC;

import Arbol.AritAST.ArbolAritmetica;
import GramaticaJAVA.javacup;
import Instrucciones.Instruccion;
import Instrucciones.argumento_dato;
import Instrucciones.metodos;
import Lenguajes.MetodosVisual;
import Lenguajes.javaClass;
import Variable.VariableDeclaracion;
import  static Lenguajes.MetodosVisual.clasesJava;
import java.util.ArrayList;
import java.util.Arrays;

public class call_c extends Instruccion {
    public String metodo;
    public int typeMetodo;
    public ArrayList<argumento_dato> argumentos;
    public int tipoMetodo=-1;
    public call_c(int tipe,String str,int f,int c){
        this.metodo=str;
        this.FILA=f;
        this.COLUMNA=c;
        this.typeMetodo=tipe;
    }
    public call_c(int tipe,String str,int f,int c,ArrayList<argumento_dato>argumento_datoes){
        this.metodo=str;
        this.FILA=f;
        this.argumentos=argumento_datoes;
        this.COLUMNA=c;
        this.typeMetodo=tipe;
    }
    public String string="e";

    @Override
    public void validate(boolean valid) {
        if(this.argumentos!=null){
            this.argumentos.stream()
                    .forEach((x)->{
                        if(x.nodo!=null){
                            try{
                                ArbolAritmetica aritmetica=new ArbolAritmetica(this.variables);
                                aritmetica.errorClass=this.errores;
                                aritmetica.recorrer(x.nodo);
                                MetodosVisual.add("param",aritmetica.lastVal,"","param",11);
                            }catch (Exception ex){}
                        }
                    });
        }
        String vector[]=this.metodo.split("\\.");
        String name="";
        MetodosVisual.tipo=-10;
        MetodosVisual.string="";
        String extra="";
      if(tipoMetodo==-1){
          if(vector.length==2){
              if(vector[0].equals("VB")){
                  name =MetodosVisual.buscar_vb(this,vector[1],this.errores);
                  this.tipo=MetodosVisual.tipo;
                  this.string=MetodosVisual.string;

              }else if(vector[0].equals("PY")){
                  name=MetodosVisual.buscar_py(this,vector[1],this.errores);
                  this.tipo=1;
                  this.string="e";
              }else {
                  VariableDeclaracion var=this.getVar(vector[0]);
                  if(var!=null){
                      name= MetodosVisual.buscar_java(this,var.nameClass,vector[1],errores);
                      this.tipo=MetodosVisual.tipo;
                      this.string=MetodosVisual.string;
                      extra=vector[0];
                  }else{
                      errores.AddError(2,this.FILA,this.COLUMNA,this.metodo,"No se encontro la " +
                              "clase java declarada");
                  }
              }

              if(argumentos==null){
                  MetodosVisual.add("call",name,"",this.lasValtmp1.equals("%")?"call_fun":"",12);
                  MetodosVisual.instrucciones.get(MetodosVisual.instrucciones.size()-1).EXTRA=extra;
                  MetodosVisual.instrOptim.get(MetodosVisual.instrOptim.size()-1).EXTRA=extra;

              }else{
                  MetodosVisual.add("call",name,String.valueOf(argumentos.size()),this.lasValtmp1.equals("%")?"call_fun":"",12);
                  MetodosVisual.instrucciones.get(MetodosVisual.instrucciones.size()-1).EXTRA=extra;
                  MetodosVisual.instrOptim.get(MetodosVisual.instrOptim.size()-1).EXTRA=extra;
              }
          }
          else if(vector.length==3){
              name=MetodosVisual.buscar_java(this,vector[1],vector[2],errores);
              this.tipo=MetodosVisual.tipo;
              this.string=MetodosVisual.string;
              MetodosVisual.add("call",name,"",this.lasValtmp1.equals("%")?"call_fun":"",12);

          }
      }else{
          switch (tipoMetodo){
              case 0:
                  name =MetodosVisual.buscar_vb(this,this.metodo,this.errores);
                  this.tipo=MetodosVisual.tipo;
                  this.string=MetodosVisual.string;
                  break;
              case 1:
                  name=MetodosVisual.buscar_py(this,this.metodo,this.errores);
                  this.tipo=0;
                  this.string="r";
                  break;
              case 2:
                  //VariableDeclaracion var=this.getVar(vector[0]);
                  //if(var!=null){
                   //   name= MetodosVisual.buscar_java(this, javacup.stringJavaClass,this.metodo,errores);
                  if(clasesJava!=null){
                      if(clasesJava.classes!=null){
                          if(!clasesJava.classes.isEmpty()){
                              for (javaClass x:clasesJava.classes) {
                                  if(x.ID.equals(javacup.stringJavaClass)){
                                      if(x.metodos!=null){
                                          if(!x.metodos.isEmpty()){
                                              String tmo="";
                                              for (metodos m: x.metodos) {
                                                  if(m.ID.equals(this.metodo)){
                                                      tmo=m.codigoMetodo;
                                                      this.tipo=m.TIPO;
                                                      this.string=m.str;
                                                      name=tmo;
                                                      break;
                                                  }
                                              }
                                              if(!tmo.isEmpty()){
                                                  break;
                                              }}}}}}}}

                      //this.tipo=MetodosVisual.tipo;
                      //this.string=MetodosVisual.string;
                      extra="";

                  break;
          }
          if(argumentos==null){
              MetodosVisual.add("call",name,"",this.lasValtmp1.equals("%")?"call_fun":"",12);
          }else{
              MetodosVisual.add("call",name,String.valueOf(argumentos.size()),this.lasValtmp1.equals("%")?"call_fun":"",12);
          }
      }
        this.lasValtmp1=this.lasValtmp1.equals("%")?"call_fun":"";
    }

}
