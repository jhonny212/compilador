package Variable;

import Arbol.AritAST.NodoAritmetica;
import Instrucciones.argumento_dato;

import java.util.ArrayList;

public class VariableDeclaracion extends Variable{
    public  boolean hasBennCheck=false;
    public String nameClass="";
    public VariableVECTOR variableVECTOR;


    public VariableDeclaracion(String id,VariableVECTOR variableVECTOR,int fila,int columna){
        this.ID=id;
        this.fila=fila;
        this.columna=columna;
        this.variableVECTOR=variableVECTOR;
        isNull=false;
        if(variableVECTOR!=null){
            variableVECTOR.fila=this.fila;
            variableVECTOR.columna=this.columna;
            variableVECTOR.ID=ID;
        }

    }

    public VariableDeclaracion(String id, NodoAritmetica nodo,int fila,int columna){
        this.nodo=nodo;
        this.ID=id;
        this.fila=fila;
        this.columna=columna;
    }
    public VariableDeclaracion(String id,int fila,int columna){
        this.ID=id;
        this.isNull=true;
        this.pass=true;
        this.fila=fila;
        this.columna=columna;
        entered=1;
    }
    public int entered=0;
    public VariableDeclaracion(String id,int fila,int columna, boolean isnull){
        this.ID=id;
        this.isNull=false;
        this.pass=true;
        this.fila=fila;
        this.columna=columna;
        isFunExter=true;
        isJava=true;
    }
    public ArrayList<argumento_dato> args;
    public VariableDeclaracion(String id,int fila,int columna, ArrayList<argumento_dato> args){
        this.ID=id;
        this.isNull=false;
        this.pass=true;
        this.fila=fila;
        this.columna=columna;
        isFunExter=true;
        this.args=args;
        isJava=true;
    }

    public VariableDeclaracion(String id,int fila,int columna,int t1,boolean is){
        this.ID=id;
        this.isNull=is;
        this.pass=true;
        this.fila=fila;
        this.columna=columna;

        switch (t1){
            case 0:
                this.TIPO=t1;
                this.string="r";
                break;
            case 1:
                this.TIPO=t1;
                this.string="e";
                break;
            case 2:
                this.TIPO=1;
                this.string="c";
                break;
        }

    }

    public int getTipe_var(){
        switch (this.string){
            case "r":
                return 0;
            case "e":
                return 1;
            case "c":
                return 2;
        }
        return 0;
    }


    @Override
    public boolean isNull() {
        return this.isNull;
    }

    @Override
    public Object getValor() {
        return null;
    }



    @Override
    public void change() {

    }

    @Override
    public String getStr() {
        return null;
    }


}
