package GramaticaPython;

import Errores.ErrorClass;
import Lenguajes.Cuadruplas;
import Lenguajes.MetodosVisual;
import TablaSimbolos.SymTable;
import Variable.VariableDeclaracion;
import java_cup.runtime.Symbol;

import java.util.ArrayList;

public class metodo_py {
    public ArrayList<InstruccionPy> instruccionPIES;
    public int FILA,COLUMNA;
    public String ID,CODIGO;
    public int numParams=0;
    public int tamaño=0;
    public metodo_py(String id,int f1,int c1,ArrayList<InstruccionPy> instruccionPIES){
        this.FILA=f1;
        this.COLUMNA=c1-1;
        this.instruccionPIES=instruccionPIES;
        this.ID=id;
        
    }
    ArrayList<VariableDeclaracion> variables;
    public metodo_py(String id,int f1, int c1, ArrayList<InstruccionPy> instruccionPIES, ArrayList<VariableDeclaracion> args){
        this.FILA=f1;
        this.COLUMNA=c1-1;
        this.instruccionPIES=instruccionPIES;
        this.variables=args;
        this.ID=id;
        tamaño=args.size();
    }


    public boolean check(){

        if(this.instruccionPIES!=null){
            InstruccionPy previo=this.instruccionPIES.get(0);
            if(previo.COLUMNA<this.COLUMNA){
                this.errorClass.AddError(1,previo.FILA,previo.COLUMNA,"La instruccion","" +
                        "Ajustar la instruccion o tabular bien");
                return false;
            }else{
                for (int i = 1; i <this.instruccionPIES.size() ; i++) {
                    InstruccionPy next=this.instruccionPIES.get(i);
                    if(nextPrev(next)){
                        if(nextPrev(previo)){
                            if(next.COLUMNA!=previo.COLUMNA){
                                if(next.COLUMNA>previo.COLUMNA){
                                    this.errorClass.AddError(1,next.FILA,next.COLUMNA,"La instruccion","" +
                                            "Ajustar la instruccion o tabular bien");
                                    return false;
                                }else{
                                    if(!verifyT1(next,i)){
                                        this.errorClass.AddError(1,next.FILA,next.COLUMNA,"La instruccion","" +
                                                "Ajustar la instruccion o tabular bien");
                                        return false;
                                    }
                                }

                            }
                        }else{
                            if(next.COLUMNA<=previo.COLUMNA){
                                if(!verifyT1(next,i)){
                                    this.errorClass.AddError(1,next.FILA,next.COLUMNA,"La instruccion","" +
                                            "Ajustar la instruccion o tabular bien");
                                    return false;
                                }
                            }
                        }
                    }else {
                        if(next instanceof else_py){
                            if(!verifyT2(i,next.COLUMNA)){
                                this.errorClass.AddError(1,next.FILA,next.COLUMNA,"La instruccion","" +
                                        "Ajustar la instruccion o tabular bien");
                                return false;
                            }
                        }else if(nextPrev(previo)){
                            if(next.COLUMNA>previo.COLUMNA){
                                this.errorClass.AddError(1,next.FILA,next.COLUMNA,"La instruccion","" +
                                        "Ajustar la instruccion o tabular bien");
                                break;
                            }else if(next.COLUMNA<previo.COLUMNA){
                                if(!verifyT1(next,i)){
                                    this.errorClass.AddError(1,next.FILA,next.COLUMNA,"La instruccion","" +
                                            "Ajustar la instruccion o tabular bien");
                                    return false;
                                }
                            }
                        }else{
                            if(next.COLUMNA<previo.COLUMNA){
                                if(!verifyT1(next,i)){
                                    this.errorClass.AddError(1,next.FILA,next.COLUMNA,"La instruccion","" +
                                            "Ajustar la instruccion o tabular bien");
                                    return false;
                                }
                            }
                        }


                    }
                    previo=next;
                }
            }
        }
        return true;
    }

    public boolean nextPrev(InstruccionPy next){
        if((next instanceof asignacion_PY) ||(next instanceof return_py)
                || (next instanceof print_PY ) ||(next instanceof input_PY)){
            return true;
        }
        return false;
    }

    boolean verifyT1(InstruccionPy instr,int i){
        for (int j = i-1; j >=0 ; j--) {
            InstruccionPy tmp=this.instruccionPIES.get(j);
            if(tmp.COLUMNA==instr.COLUMNA){
                if(instr instanceof else_py ){
                    if((tmp instanceof else_py)||(tmp instanceof if_py)){
                        return true;
                    }else{
                        return false;
                    }
                }

                if(nextPrev(tmp)){
                    return false;
                }else{
                    return true;
                }
            }else if(tmp.COLUMNA<instr.COLUMNA){
                return false;
            }
        }
    return false;
    }


    boolean verifyT2(int j,int c){
        for (int i = j-1; i >=0; i--) {
              InstruccionPy tmp=this.instruccionPIES.get(i);

              if(tmp.COLUMNA==c){
                  if((tmp instanceof else_py) || (tmp instanceof if_py)){
                      return true;
                  }else{
                      return false;
                  }
              }else if(tmp.COLUMNA<c){
                  return false;
              }
        }
        return false;
    }
    public ErrorClass errorClass;
    String x;
    public void getCodigo(){
         x=(this.variables==null?"0":String.valueOf(this.variables.size()));
        this.CODIGO="PY_"+this.ID+"_"+x;
        numParams=this.variables==null?0:variables.size();

    }
    public void validate(){

        MetodosVisual.add("Fun","PY_"+this.ID+"_",x,"",8);
        int tamCuad=MetodosVisual.instrucciones.size();
        int tamPrevio= SymTable.celdas.size();
        SymTable.celda tmp=new SymTable.celda(0,false,0,0,-1,this.CODIGO,0);
        SymTable.celdas.add(tmp);
        if(numParams>0){
            this.variables
                    .stream()
                    .forEach((var)->{
                       SymTable.ADD(0,false,1,0,-1,var.ID);
                    });
        }
        if(check()){
            ambitoPY ambitoPY=get(0);
            ambitoPY.variables=this.variables;
            ambitoPY.errorClass=this.errorClass;
            ambitoPY.validate();
        }
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
        MetodosVisual.add("close","}","","",9);
        int tamSig=SymTable.celdas.size();
        tmp.POS_MEMORIA=tamSig-tamPrevio-1;
    }

    ambitoPY get(int i){
        int previo=-1;
        ambitoPY ambitoPY=new ambitoPY();
        for (int j = i; j < this.instruccionPIES.size(); j++) {
            InstruccionPy tmp=this.instruccionPIES.get(j);
            if(tmp.COLUMNA>previo && previo!=-1){
                ambitoPY getAmbito=get(j);
                InstruccionPy dad=this.instruccionPIES.get(j-1);
                dad.ambitoPY=getAmbito;
                j=TOTAL-1;
                previo= dad.COLUMNA;
                if(j<0){
                    return ambitoPY;
                }
           }else if(tmp.COLUMNA<previo){
                TOTAL=j;
                return ambitoPY;
            }else{
                ambitoPY.instruccionPIES.add(tmp);
                previo=tmp.COLUMNA;
            }

        }
        TOTAL=this.instruccionPIES.size();
        return ambitoPY;
    }

    public int TOTAL=0;

    public int size(){
        if(this.variables==null){
            return 0;
        }
        return this.variables.size();
    }
}
