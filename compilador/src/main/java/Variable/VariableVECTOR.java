package Variable;

import Arbol.AritAST.ArbolAritmetica;
import Arbol.AritAST.NodoAritmetica;
import Errores.ErrorClass;
import Lenguajes.MetodosVisual;
import TablaSimbolos.SymTable;

import java.util.ArrayList;

public class VariableVECTOR extends Variable{
    public ArrayList<NodoAritmetica> nodos;
    public VariableVECTOR(ArrayList<NodoAritmetica> nodos){
        this.nodos=nodos;
    }
    public VariableVECTOR(int f, int c,String id,ArrayList<NodoAritmetica> nodos){
        this.isNull=true;
        this.fila=f;
        this.columna=c;
        this.ID=id;
        this.nodos=nodos;
    }

    @Override
    public boolean isNull() {
        return false;
    }
    public  int AMBITO,AMBITO_DAD;
    @Override
    public Object getValor() {
        return null;
    }
    public boolean change=false;
    @Override
    public void change() {
        change=!change;
    }

    @Override
    public String getStr() {
        return this.lasValtmp1;
    }

    public void validate(ArrayList<VariableDeclaracion> variableDeclaracions, ErrorClass errorClass){
        cnt=0;
        if(nodos!=null){
            if(!nodos.isEmpty()){
                String tam=this.ID+"_tam"+nodos.size();
                MetodosVisual.add("Asig","0","",tam,2);

                nodos.stream().
                        forEach((x)->{
                            ArbolAritmetica aritmetica=new ArbolAritmetica(variableDeclaracions);
                            aritmetica.errorClass=errorClass;
                            aritmetica.recorrer(x);
                            MetodosVisual.add("Asig",aritmetica.lastVal,"",this.ID+"_tam"+cnt,2);
                            SymTable.ADD(1,false,1,this.AMBITO,this.AMBITO_DAD,this.ID+"_tam"+cnt);
                            MetodosVisual.add("+",aritmetica.lastVal,tam,tam,0);

                            if(x.variable!=null){
                                if(x.variable.TIPO==0){
                                    errorClass.AddError(2,this.fila,this.columna-1,ID,"" +
                                            "Un vector, en su posicion solo acepta enteros");
                                }
                            }
                            cnt++;
                        });
                cnt++;
                SymTable.ADD(1,false,1,this.AMBITO,this.AMBITO_DAD,this.ID+"_tam"+nodos.size());
                this.lasValtmp1=tam;
            }
        }
        cnt=0;
    }
    public void validate2(ArrayList<VariableDeclaracion> variableDeclaracions, ErrorClass errorClass){
        this.lasValtmp1="";
        cnt=0;
        if(nodos!=null){
            if(!nodos.isEmpty()){
                nodos.stream().
                        forEach((x)->{
                            if(cnt>0){
                                MetodosVisual.add("Asig",this.lasValtmp1,"","int_num3",2);
                                lasValtmp1="int_num3";

                            }
                            ArbolAritmetica aritmetica=new ArbolAritmetica(variableDeclaracions);
                            aritmetica.errorClass=errorClass;
                            aritmetica.recorrer(x);
                            if(cnt==0){
                                this.lasValtmp1=aritmetica.lastVal;
                            }else{
                                MetodosVisual.add("*",lasValtmp1,this.ID+"_tam"+(cnt-1),"t_1",0);
                                MetodosVisual.add("+","t_1"+this.TIPO,aritmetica.lastVal,"t_1",0);
                                lasValtmp1="t_1";
                            }
                            if(x.variable!=null){
                                if(x.variable.TIPO==0){
                                    errorClass.AddError(2,this.fila,this.columna-1,ID,"" +
                                            "Un vector, en su posicion solo acepta enteros");
                                }
                            }
                            cnt++;
                        });
            }
        }

        MetodosVisual.add("Asig",this.ID,this.lasValtmp1,"t_1"+tipo(),7);

        this.lasValtmp1="t_1"+tipo();
    }
    int cnt=0;

    public String tipo(){
        switch (this.string){
            case "e":
                return "_int";
            case "r":
                return "_float";
            case "c":
                return "_char";
        }
        return "_float";
    }
}
