package Instrucciones;

import Lenguajes.MetodosVisual;
import Variable.Variable;

import java.util.ArrayList;

public class switch_var extends Instruccion{
    public String ID;
    public ArrayList<Sub_case> cases;

    public switch_var(String ID, ArrayList<Sub_case> cases,int f,int c) {
        this.ID = ID;
        this.FILA=f;
        this.COLUMNA=c;
        this.cases = cases;
        if(cases!=null){
            this.retornosRequeridos+=this.cases.size();
        }

    }
    String tmp="";
    @Override
    public void validate(boolean valid) {
        Variable var=this.getVar(ID);

        if(var==null){
            this.errores.AddError(2,FILA,COLUMNA-1,ID,"" +
                    "Error en el identificador del select,\n" +
                    "no existe la variable");

        }else{
            if(var.ID.startsWith("this.") && !this.ID.startsWith("this.")){
                tmp="this.";
            }
        }

        if(this.cases!=null){
            MetodosVisual.CONTADOR_ETIQ++;
            MetodosVisual.add("Et","E",String.valueOf(MetodosVisual.CONTADOR_ETIQ),"",5);

            this.cases
                    .stream()
                    .forEach((x)->{
                        if(x.nodoAritmetica!=null){
                            MetodosVisual.CONTADOR_ETIQ++;
                            MetodosVisual.add("==",tmp+this.ID,x.getStr(),"goto E"+MetodosVisual.CONTADOR_ETIQ,3);
                            x.etiqueta=MetodosVisual.CONTADOR_ETIQ;
                        }else{
                            MetodosVisual.CONTADOR_ETIQ++;
                            elseSelect=MetodosVisual.CONTADOR_ETIQ;
                            x.etiqueta=elseSelect;
                        }
                    });
            int salida=0;
            MetodosVisual.CONTADOR_ETIQ++;
            salida=MetodosVisual.CONTADOR_ETIQ;
            MetodosVisual.CONTADOR_ETIQ++;
            if(this.elseSelect!=-1){
                MetodosVisual.add("goto","E",String.valueOf(elseSelect),"",4);
            }else{
                MetodosVisual.add("goto","E",String.valueOf(salida),"",4);
            }
            Sub_case default_=null;

            for (int i = 0; i < this.cases.size(); i++) {

                Sub_case x=this.cases.get(i);
                if(x.nodoAritmetica!=null){
                    MetodosVisual.add("Et","E",String.valueOf(x.etiqueta),"",5);
                }else{
                    MetodosVisual.add("Et","E",String.valueOf(x.etiqueta),"",5);
                }
                x.variables=this.variables;
                x.errorClass=this.errores;
                x.numeroAmbito=MetodosVisual.CONTADOR_AMBITO;
                x.ambitoDad=this.numeroAmbito;
                x.validate(valid);
                if(x.rtn()){
                    MetodosVisual.add("goto","E",String.valueOf(salida),"",4);
                }
                this.retornosRequeridos+=x.retornosRequeridos;
                this.contadorRetornos+=x.contadorRetornos;
                if(var!=null  && x.nodoAritmetica!=null){
                    if(var.TIPO==1 && x.nodoAritmetica.TIPO==0){
                        this.errores.AddError(2,FILA,COLUMNA-1,ID,"" +
                                "Error, la variable "+ID+" no es del mismo tipo al \n" +
                                "que se pretende asignar. Se requiere un entero no decimal");
                    }
                }if(x.isDefault){
                    default_=x;
                }
                if(i+1<this.cases.size()){
                 for (int j = i+1; j < this.cases.size(); j++) {
                        Sub_case y=this.cases.get(j);


                        if(x.nodoAritmetica!=null && y.nodoAritmetica!=null){
                            if(x.nodoAritmetica.value.equals(y.nodoAritmetica.value)){
                                this.errores.AddError(2, y.FILA, y.COLUMNA -1,String.valueOf(y.nodoAritmetica.getValor()),"" +
                                        "Error, este valor del case ya ha sido declarado en\n" +
                                        "Fila: "+x.FILA+" COLUMNA: "+(x.COLUMNA-1));
                            }
                        }
                        else if(x.nodoAritmetica==null && y.nodoAritmetica==null){
                            this.errores.AddError(2, y.FILA, y.COLUMNA -1,"Case Else","" +
                                    "Error, no puede haber mas de un default en un select, ya ha sido declarado en\n" +
                                    "Fila: "+x.FILA+" COLUMNA: "+(x.COLUMNA-1));
                        }
                    }
                }

            }
            MetodosVisual.add("Et","E",String.valueOf(salida),"",5);

            if(default_!=null){
                if(default_.nodoRetorno!=null){
                    this.contadorRetornos+=1;
                }else{
                    int x1=default_.contadorRetornos;
                    int x2=default_.retornosRequeridos;
                    if(x1>x2){
                        this.contadorRetornos+=1;
                    }
                }
            }

        }
    }
    int elseSelect=-1;
}
