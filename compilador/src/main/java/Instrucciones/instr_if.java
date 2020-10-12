package Instrucciones;

import Arbol.BoolAST.ArbolBooleano;
import Arbol.BoolAST.NodoBoolean;
import Lenguajes.MetodosVisual;

import java.util.ArrayList;

public class instr_if extends Instruccion{
    ArrayList<instr_if> instr_ifs;
    public boolean isElse=false;
    instr_if lastElse;
    NodoBoolean nodoBoolean;
    public instr_if(NodoBoolean nodoBoolean,
                    ambito_ ambito_,
                    ArrayList<instr_if>list,
                    instr_if lastElse
                    ){
        this.lastElse=lastElse;
        this.nodoBoolean=nodoBoolean;
        this.ambito_=ambito_;
        this.instr_ifs=list;
        this.retornosRequeridos=1;
        try{
            if(this.instr_ifs!=null){
                this.retornosRequeridos+=this.instr_ifs.size();
            }
            if(this.lastElse!=null){
                this.retornosRequeridos+=1;
            }
        }catch (Exception ex){}


    }

    public instr_if(ambito_ ambito_){
        this.ambito_=ambito_;
        this.isElse=true;

    }
    public instr_if(ambito_ ambito_,NodoBoolean nodo){
        this.nodoBoolean=nodo;
        this.ambito_=ambito_;

    }

    @Override
    public void validate(boolean valid) {
        ArbolBooleano booleano=null;
        if(this.nodoBoolean!=null){
            booleano=new ArbolBooleano(this.variables);
            try{
                booleano.errorClass=this.errores;
                booleano.recorrer(nodoBoolean);
            }catch (Exception ex){}

        }
        int previo=0;
        MetodosVisual.CONTADOR_ETIQ++;
        int previo2=MetodosVisual.CONTADOR_ETIQ;
        if(nodoBoolean!=null){
            if(nodoBoolean.isOp){
                MetodosVisual.add("","t","","goto E"+MetodosVisual.CONTADOR_ETIQ,3);
            }else{
                booleano.add();
            }
        }
        MetodosVisual.CONTADOR_ETIQ++;
        previo=MetodosVisual.CONTADOR_ETIQ;
        MetodosVisual.add("goto","E",String.valueOf(previo),"",4);
        MetodosVisual.add("Et","E",String.valueOf(previo2),"",5);

        if(this.ambito_!=null){
            ambito_.variables=this.variables;
            ambito_.errores=this.errores;
            MetodosVisual.CONTADOR_AMBITO++;
            ambito_.numeroAm=MetodosVisual.CONTADOR_AMBITO;
            ambito_.ambitoDad=this.numeroAmbito;
            ambito_.validate(valid);
            this.retornosRequeridos+= ambito_.retornosRequeridos;
            this.contadorRetornos+= ambito_.contadorRetornos;
            if(ambito_.retorno!=null){
                this.contadorRetornos+=1;
            }



        }
        if(this.instr_ifs!=null){
            MetodosVisual.CONTADOR_ETIQ++;
            int salida=MetodosVisual.CONTADOR_ETIQ;
            MetodosVisual.add("goto","E",String.valueOf(salida),"",4);
            MetodosVisual.add("Et","E",String.valueOf(previo),"",5);

            this.instr_ifs.stream()
                    .forEach((x)->{
                        x.variables=this.variables;
                        x.errores=this.errores;
                        x.validate(valid);
                        this.contadorRetornos+=x.contadorRetornos;
                        this.retornosRequeridos+=x.retornosRequeridos;
                        MetodosVisual.addBefore("goto","E",String.valueOf(salida),"",4);

                    });
            if(this.lastElse!=null){
                MetodosVisual.add("goto","E",String.valueOf(MetodosVisual.CONTADOR_ETIQ+1),"",4);
            }
            MetodosVisual.add("Et","E",String.valueOf(salida),"",5);

        }else {
            if(this.lastElse==null){
                MetodosVisual.add("Et","E",String.valueOf(previo),"",5);
            }else{
                MetodosVisual.CONTADOR_ETIQ--;
            }

        }
        if(this.lastElse!=null){
            this.lastElse.variables=this.variables;
            this.lastElse.errores=this.errores;
            this.lastElse.validate(valid);
            this.retornosRequeridos+= lastElse.retornosRequeridos;
            this.contadorRetornos+= lastElse.contadorRetornos;
            if(this.lastElse.ambito_!=null){
                if(this.lastElse.ambito_.retorno!=null){
                    this.contadorRetornos+=1;

                }else{
                    int x1=lastElse.contadorRetornos;
                    int x2= lastElse.retornosRequeridos;
                    if(x1>x2){
                        this.contadorRetornos+=1;
                    }
                }
            }

        }

    }

}
