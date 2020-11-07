package Lenguajes;

import Arbol.AritAST.ArbolAritmetica;
import Errores.ErrorClass;
import GramaticaC.call_c;
import GramaticaPython.metodo_py;
import Instrucciones.argumento_dato;
import Instrucciones.metodos;
import TablaSimbolos.SymTable;
import Variable.VariableDeclaracion;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MetodosVisual {
    public static int CONTADOR=0,CONTADOR_ETIQ=0,CONTADOR_AMBITO=1;
    public static ArrayList<Cuadruplas> instrucciones=new ArrayList<>();
    public static ArrayList<Cuadruplas> instrOptim=new ArrayList<>();
    public MetodosVisual(ArrayList<metodos> metodos){
                        MetodosVisual.metodosVB=metodos;
    }

    public static int getAndCNT(){
        return CONTADOR_ETIQ+2;
    }

    public static void add(String OP,String ARG1,String ARG2,
                           String RESULT,int TIPO){
        instrucciones.add(new Cuadruplas(OP, ARG1, ARG2, RESULT, TIPO));
        //instrOptim.add(new Cuadruplas(OP, ARG1, ARG2, RESULT, TIPO));

    }
    public static void add(String OP,String ARG1,String ARG2,
                           String RESULT,int TIPO,String KIND){
        instrucciones.add(new Cuadruplas(OP, ARG1, ARG2, RESULT, TIPO,KIND));
        //instrOptim.add(new Cuadruplas(OP, ARG1, ARG2, RESULT, TIPO,KIND));
    }
    public static void addBefore(String OP,String ARG1,String ARG2,
                           String RESULT,int TIPO){
        instrucciones.add((instrucciones.size()-1),(new Cuadruplas(OP, ARG1, ARG2, RESULT, TIPO)));
        //instrOptim.add((instrOptim.size()-1),(new Cuadruplas(OP, ARG1, ARG2, RESULT, TIPO)));
    }

    static  int cnt=0;
    static String cod="";
    public static String print(){
        cnt=0;
        cod="";
        instrucciones.stream()
                .forEach((x)->{
                    cod+=x.getTabs()+x.PRINT()+" "+x.TIPO+"\n";
                    cnt++;
                });
        return cod;
    }
    public static String print2(){
        cnt=0;
        cod="";
        instrOptim.stream()
                .forEach((x)->{
                    if(x.canAdd){
                        cod+=x.getTabs()+x.PRINT()+" "+x.TIPO+"\n";
                        cnt++;
                    }

                });
        return cod;
    }

    
    
    
    

    public static void previo(int x,Cuadruplas w){
        if(w.TIPO==5){
            w.tabulaciones=1;
        }else if( valid(w) ){
            w.tabulaciones=0;
        }
        else{
            if(x!=0){
                if(instrucciones.get(x-1).TIPO==5){
                    w.tabulaciones++;
                }else if(valid(instrucciones.get(x-1))){
                    w.tabulaciones++;
                }
                else{
                    w.tabulaciones=instrucciones.get(x-1).tabulaciones;
                }
            }
        }

    }
    
    public static boolean valid(Cuadruplas w){
    return w.TIPO==15 || w.TIPO==8 || w.TIPO==9;
    }

    public static ArrayList<metodos> metodosVB;
    public static ClasesJava clasesJava;
    public static  ArrayList<metodo_py> metodosPY;
    public static int tipo;
    public static String string;
    public static String buscar_vb(call_c call, String name, ErrorClass errorClass){
        if(!metodosVB.isEmpty()){
            String tmo=getName(call.argumentos,name,metodosVB);
            if(!tmo.isEmpty()){
                return tmo;
            }
            errorClass.AddError(2,call.FILA,call.COLUMNA,call.metodo,"El metodo de visual basica, solicitado\n" +
                    "no fue declarado");
            return name;
        }


        errorClass.AddError(2,call.FILA,call.COLUMNA,call.metodo,"El metodo de visual basica, solicitado\n" +
                "no fue declarado, o verifique los argumentos");
        return name;
    }

    public static String buscar_py(call_c call_c,String name,ErrorClass errorClass){
        if(metodosPY!=null){
            if(metodosPY.isEmpty()){
            }else{
                for (metodo_py metodo_py:metodosPY) {
                    if(metodo_py.ID.equals(name) && metodo_py.numParams==(call_c.argumentos==null?0:call_c.argumentos.size())){
                        return metodo_py.CODIGO;
                    }
                }
            }
        }
        errorClass.AddError(2,call_c.FILA,call_c.COLUMNA,call_c.metodo,"El metodo de python, solicitado\n" +
                "no fue declarado, o verifique los argumentos");
        return name;
    }

    public static String buscar_java(call_c call_c,String clase,String metodo,ErrorClass errorClass){
        if(clasesJava!=null){
            if(clasesJava.classes!=null){
                if(!clasesJava.classes.isEmpty()){
                    for (javaClass x:clasesJava.classes) {
                        if(x.ID.equals(clase)){
                            if(x.metodos!=null){
                                if(!x.metodos.isEmpty()){
                                    String tmo=getName(call_c.argumentos,metodo,x.metodos);
                                    if(!tmo.isEmpty()){
                                        return tmo;
                                    }
                                    errorClass.AddError(2,call_c.FILA,call_c.COLUMNA,call_c.metodo,"la clase "+clase+" no contiene\n" +
                                            "el metodo solicitado, " +
                                            "no fue declarado");
                                }
                            }
                            return metodo;
                        }
                    }
                    errorClass.AddError(2,call_c.FILA,call_c.COLUMNA,call_c.metodo,"la clase "+clase+" " +
                                    "no fue encontrada" );
                }
            }
        }
        return metodo;
    }

   static String getName(ArrayList<argumento_dato>argumentos,String name,ArrayList<metodos> metodos){
        for (metodos x:metodos) {
            if(x.ID.equals(name)){
                if(argumentos!=null && x.argumentos!=null){
                    if(argumentos.size()==x.argumentos.size()){
                        int tmp=0;

                        for (int i = 0; i < argumentos.size(); i++) {
                            argumento_dato a1= argumentos.get(i);
                            VariableDeclaracion a2= x.argumentos.get(i);
                            try {
                                if(a1.nodo.variable.string==a2.string){
                                    tmp++;
                                }

                            }catch (Exception ex){
                                break;
                            }
                        }

                        if(tmp==x.argumentos.size()){
                            tipo=x.TIPO;
                            string=x.str;
                            return x.codigoMetodo;
                        }
                        tmp=0;
                        for (int i = 0; i < argumentos.size(); i++) {
                            argumento_dato a1= argumentos.get(i);
                            VariableDeclaracion a2= x.argumentos.get(i);
                            try {
                                if(a1.nodo.variable.TIPO==a2.TIPO || (a2.TIPO==0)){
                                    tmp++;
                                }

                            }catch (Exception ex){
                                break;
                            }
                        }
                        if(tmp==x.argumentos.size()){
                            tipo=x.TIPO;
                            string=x.str;
                            return x.codigoMetodo;
                        }
                    }
                }else if(argumentos==null && x.argumentos==null){
                    tipo=x.TIPO;
                    string=x.str;
                    return x.codigoMetodo;
                }else if(argumentos == null){
                    if(x.argumentos.size()==0){
                        tipo=x.TIPO;
                        string=x.str;
                        return x.codigoMetodo;
                    }
                }
            }
        }
        return "";
    }


    public static boolean get(ArrayList<argumento_dato>argumentos,metodos x,ArrayList<VariableDeclaracion> vars,String name){
        int previo=instrucciones.size();
        if(argumentos!=null && x.argumentos!=null){
            if(argumentos.size()==x.argumentos.size()){
                int tmp=0;
                for (int i = 0; i < argumentos.size(); i++) {
                    argumento_dato a1= argumentos.get(i);
                    if(a1.nodo!=null){
                        ArbolAritmetica aritmetica=new ArbolAritmetica(vars);
                        aritmetica.recorrer(a1.nodo);
                        MetodosVisual.add("param",aritmetica.lastVal,"","param",11);
                    }
                    VariableDeclaracion a2= x.argumentos.get(i);
                    try {
                        if(a1.nodo.variable.string==a2.string){
                            tmp++;
                        }

                    }catch (Exception ex){
                        break;
                    }
                }

                if(tmp==x.argumentos.size()){
                    MetodosVisual.add("call",x.codigoMetodo,String.valueOf(argumentos.size())
                            ,name,12);
                    return true;
                }
                tmp=0;
                for (int i = 0; i < argumentos.size(); i++) {
                    argumento_dato a1= argumentos.get(i);
                    VariableDeclaracion a2= x.argumentos.get(i);
                    try {
                        if(a1.nodo.variable.TIPO==a2.TIPO || (a2.TIPO==0)){
                            tmp++;
                        }

                    }catch (Exception ex){
                        break;
                    }
                }
                if(tmp==x.argumentos.size()){
                    MetodosVisual.add("call",x.codigoMetodo,String.valueOf(argumentos.size())
                            ,name,12);
                    return true;
                }
            }
        }else if(argumentos==null && x.argumentos==null){
            MetodosVisual.add("call",x.codigoMetodo,""
                    ,name,12);
            return true;
        }
        remove(previo);
        return false;
    }

    public static  void remove(int index){
        int i=instrucciones.size();
        if(index!=i){
            if (i >= index) {
                instrucciones.subList(index , i).clear();
            }
        }
    }

    public static void search(String id){
        int i=instrucciones.size();
        for (int j = i-1; j >=0 ; j--) {
            Cuadruplas x=instrucciones.get(j);
            if(x.TIPO==15){
                x.ARG1=id;
                break;
            }
        }
    }

    public static void clear(){
        instrucciones=new ArrayList<>();
        clasesJava=new ClasesJava();
        metodosPY=new ArrayList<>();
        CONTADOR_ETIQ=0;
        CONTADOR=0;
        metodosVB=new ArrayList<>();
        instrOptim=new ArrayList<>();
        ErrorClass.numLine=0;
        SymTable.canAdd=true;
        SymTable.celdas=new ArrayList<>();
        SymTable.celdas2=new ArrayList<>();
        SymTable.size_char=0;
        SymTable.size_double=0;
        SymTable.size_int=0;
    }

}
