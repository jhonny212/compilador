package Lenguajes;

import Errores.ErrorClass;
import GramaticaC.ccup;
import GramaticaC.clex;
import GramaticaJAVA.javacup;
import GramaticaJAVA.javalex;
import GramaticaPython.metodo_py;
import GramaticaPython.pycup;
import GramaticaPython.pylex;
import GramaticaVisualBasic.vbcup;
import GramaticaVisualBasic.vblex;
import Instrucciones.metodos;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;

public class Compilador {
    String texto;

    public Compilador(String texto) {
        this.texto = texto;
    }
    String codigoVB="",codigoJAVA="",codigoPY="",codigoC;
    boolean valid=false;
    public void create(){
        try{
           int x= texto.indexOf("%%JAVA")+6;
           codigoVB=texto.substring(0,x);
           int y=texto.indexOf("%%PY")+4;
           codigoJAVA=texto.substring(x,y);
           texto=texto.substring(y,texto.length());
           x=texto.indexOf("%%PROGRAMA")+10;
           codigoPY=texto.substring(0,x);
           codigoC=texto.substring(x,texto.length());
        }catch (Exception ex){
            valid=true;
        }
    }

    public ErrorClass errorClass=new ErrorClass();
    public void compilar_vb(){
        var lex=new vblex(new BufferedReader(new StringReader(codigoVB)));
        lex.errores=errorClass;
        var parser=new vbcup(lex);
        parser.errores=lex.errores;
        try {
            parser.parse();
        }catch (Exception e){
            System.err.println("Error visual basic");
        }
        ErrorClass.numLine=lex.fila-1;
        System.err.println("#"+ErrorClass.numLine);
        MetodosVisual.metodosVB=parser.listado;

    }
    public void compilar_java(){
        var lex=new javalex(new BufferedReader(new StringReader(codigoJAVA)));
        lex.errores=errorClass;
        var parser=new javacup(lex);
        parser.errores=lex.errores;
        try {
            parser.parse();
        }catch (Exception e){
            System.err.println("Error java");
        }
         ErrorClass.numLine+=lex.fila-1;
         System.err.println("#"+ErrorClass.numLine);
        MetodosVisual.clasesJava=parser.java;
        MetodosVisual.clasesJava.validate(this.errorClass);

    }
    public void compilar_pyva(){
        var lex=new pylex(new BufferedReader(new StringReader(codigoPY)));
        lex.errores=errorClass;
        var parser=new pycup(lex);
        parser.errores=lex.errores;
        try {
            parser.parse();
        }catch (Exception e){
            System.err.println("Error py");
        }
         ErrorClass.numLine+=lex.fila-1;
         System.err.println("#"+ErrorClass.numLine);
        MetodosVisual.metodosPY=parser.metodos;
        validatePy();
    }
    public void compilar_c(){
        var lex=new clex(new BufferedReader(new StringReader(codigoC)));
        lex.errores=errorClass;
        var parser=new ccup(lex);
        parser.errores=lex.errores;
        try {
            parser.parse();
        }catch (Exception e){
            System.err.println("Error c");
        }
    }

    public void printError(){
        if(!this.errorClass.haveErrors()){
            this.errorClass.erroresSintacticos();
            this.errorClass.erroresSemanticos();

        }

    }



    public void printCod(){
       String x= MetodosVisual.print();
       System.err.println(x);
    }

    void validatePy(){
        if(MetodosVisual.metodosPY.isEmpty()){
            return;
        }
        for (int i = 0; i <MetodosVisual.metodosPY.size() ; i++) {
            metodo_py x=MetodosVisual.metodosPY.get(i);
            for (int j = i+1; j < MetodosVisual.metodosPY.size(); j++) {
                metodo_py y=MetodosVisual.metodosPY.get(j);
                if(x.ID.equals(y.ID) && (x.size()==y.size())){
                    this.errorClass.AddError(2,y.FILA,y.COLUMNA,x.ID,"El metodos de python se repite en\n" +
                            "Fila:"+x.FILA+" Columna:"+x.COLUMNA);
                }
            }
        }
    }
}
