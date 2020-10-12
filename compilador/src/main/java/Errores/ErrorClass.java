package Errores;


import java_cup.runtime.Symbol;

import java.util.ArrayList;

public class ErrorClass {

    public ArrayList<errorProduced> lexico, semantico, sintactico;
    public static int numLine=0;
    public ErrorClass() {
        this.lexico = new ArrayList<>();
        this.semantico = new ArrayList<>();
        this.sintactico = new ArrayList<>();
    }

    public  class errorProduced {

        public final int fila, columna;
        public String token, dato;

        private errorProduced(String tk, String dt, int f, int c) {
            this.columna = c;
            this.fila = f;
            this.dato = dt;
            this.token = tk;
        }

        private errorProduced(String tk, int f, int c) {
            this.columna = c;
            this.fila = f;
            this.dato = "";
            this.token = tk;
        }
    }

    public void AddError(int opc, int fila, int columna, String token) {
        switch (opc) {
            case 0:
                this.lexico.add(new errorProduced(token, numLine+fila, columna));
                break;
            case 1:
                this.sintactico.add(new errorProduced(token, numLine+fila, columna));
                break;
            case 2:
                this.semantico.add(new errorProduced(token, numLine+fila, columna));
                break;
        }

    }

    public void AddError(int opc, int fila, int columna, String token, String solucion) {
        switch (opc) {
            case 0:
                this.lexico.add(new errorProduced(token,solucion, numLine+fila, columna));
                break;
            case 1:
                this.sintactico.add(new errorProduced(token,solucion, numLine+fila, columna));

                break;
            case 2:
                this.semantico.add(new errorProduced(token,solucion, numLine+fila, columna));
                break;
        }

    }

    public void update(int f,int c,String extra){
        if(this.sintactico!=null){
            if(!this.sintactico.isEmpty()){
                errorProduced xx=this.sintactico.get(this.sintactico.size()-1);
                if(xx.fila==f){
                    xx.dato=extra;
                }
            }
        }
    }

    public boolean haveErrors(){
        return this.lexico.isEmpty() && this.semantico.isEmpty() && this.sintactico.isEmpty();
    }

    public void erroresSintacticos(){
        if(this.sintactico.isEmpty()){
            return;
        }
        System.out.println("******************Sintacticos*****************************");
        for(errorProduced x: this.sintactico){
            System.out.println("COLUMNA "+x.columna+" FILA  "+x.fila+" TOKEN    "+x.token+" SOLU   "+x.dato+"\n");
        }
        System.out.println("***************************************************\n");
    }
    public void erroresSemanticos(){
        if(this.semantico.isEmpty()){
            return;
        }
        System.out.println("******************Semanticos*****************************");
        for(errorProduced x: this.semantico){
            System.out.println("COLUMNA "+x.columna+" FILA  "+x.fila+" TOKEN    "+x.token+" SOLU   "+x.dato+"\n");
        }
        System.out.println("***************************************************\n");

    }
}