package GramaticaPython;


import Errores.ErrorClass;
import Lenguajes.MetodosVisual;
import Variable.VariableDeclaracion;
import controlador.Controlador_;

import java.util.ArrayList;

public class  ambitoPY{
    public ArrayList<InstruccionPy> instruccionPIES;
    public ArrayList<VariableDeclaracion> variables;
    public ambitoPY(){
        this.instruccionPIES=new ArrayList<>();
    }
    public ErrorClass errorClass;
    public void validate(){
        if(this.variables==null){
            variables=new ArrayList<>();
        }
        cnt=0;
        this.instruccionPIES.stream()
                .forEach((x)->{
                        x.errorClass=this.errorClass;
                        if(x.variables==null){
                            x.variables=new ArrayList();
                        }
                        if(x instanceof if_py){
                            if(num==1){
                                MetodosVisual.add("Et","E",String.valueOf(salida),"",5);
                            }
                            x.variables.addAll(0,this.variables);
                            x.validate();
                            createCod(cnt);
                            boolean v=false;
                            int tmp=0;
                            if(salida==-1){
                                MetodosVisual.CONTADOR_ETIQ++;
                                tmp=MetodosVisual.CONTADOR_ETIQ;
                                v=true;
                                MetodosVisual.add("goto","E",String.valueOf(tmp),"",4);

                            }
                            x.ambitoVal=true;
                            ((if_py) x).salida=this.salida;
                            x.validate();
                            if(v){
                                MetodosVisual.add("Et","E",String.valueOf(tmp),"",5);
                            }
                            num=0;
                        }else if(x instanceof else_py){
                            x.ambitoVal=true;
                            ((else_py) x).salida=this.salida;
                            x.validate();
                            num=1;
                        }else {
                            if(num==1){
                                MetodosVisual.add("Et","E",String.valueOf(salida),"",5);

                            }
                            x.variables.addAll(0,this.variables);
                            x.validate();
                            num=0;
                        }

                       if(x instanceof asignacion_PY){
                        asignacion_PY tmp=(asignacion_PY)x ;
                        if(Controlador_.canAdd(this.variables,tmp.ID)){
                            VariableDeclaracion var=new VariableDeclaracion(tmp.ID,tmp.FILA,tmp.COLUMNA);
                            var.isNull=false;
                            variables.add(var);
                        }

                       }
                       cnt++;
                });
        if(num==1){
            MetodosVisual.add("Et","E",String.valueOf(salida),"",5);

        }
    }
   int cnt=0;

    public void add(ArrayList<VariableDeclaracion> variables){
        if(this.variables==null){
            this.variables=new ArrayList<>();
        }
        this.variables.addAll(0,variables);

    }

    void createCod(int index){
        salida=-1;
        for (int i = index+1; i < instruccionPIES.size() ; i++) {
            InstruccionPy x=this.instruccionPIES.get(i);

            if(isIf_else(x)){
                x.errorClass=this.errorClass;
                if(x.variables==null){
                    x.variables=new ArrayList();
                }
                x.variables.addAll(0,variables);
                x.validate();
                MetodosVisual.CONTADOR_ETIQ++;
                salida=MetodosVisual.CONTADOR_ETIQ;
            }else{
                break;
            }
        }
    }

    boolean isIf_else(InstruccionPy x){
        return ( x instanceof else_py);
    }

    int salida=-1;
    int num=0;
}