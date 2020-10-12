package controlador;

import Arbol.AritAST.NodoAritmetica;
import Errores.ErrorClass;
import GramaticaPython.asignacion_PY;
import Instrucciones.metodos;
import Variable.VariableDeclaracion;
import Variable.VariableMETODO;
import Variable.VariableVAL;
import Variable.VariableVECTOR;
import java_cup.runtime.Symbol;

import java.util.ArrayList;

public class Controlador_ {

    public NodoAritmetica Operar(String op, NodoAritmetica val1, NodoAritmetica val2){
        NodoAritmetica nodo=new NodoAritmetica(op);
        nodo.addSons(val1,val2);
        /*if(val1.variable==null || val2.variable==null){
            nodo=new NodoAritmetica(op);
            nodo.addSons(val1,val2);
        }else if(val1.variable.isNull() || val2.variable.isNull()
        || (val1.variable instanceof VariableMETODO) || val2.variable instanceof VariableMETODO
                || (val1.variable instanceof VariableVECTOR) || val2.variable instanceof VariableVECTOR
        )
        {
            nodo=new NodoAritmetica(op);
            nodo.addSons(val1,val2);
        }else{

            int type=Integer.min(val1.variable.TIPO,val2.variable.TIPO);
            if(type==0){
                double x1=-1;
                try{
                    x1=Double.parseDouble(String.valueOf(val1.variable.getValor()));
                }catch (Exception ex){
                    x1=(double)((char)val1.variable.getValor());
                }
                double x2=-1;
                try{
                   x2= Double.parseDouble(String.valueOf(val2.variable.getValor()));
                }catch (Exception ex){
                   x2= (double)((char)val2.variable.getValor());
                }
                switch(op){
                    case "+":
                        return new NodoAritmetica (new VariableVAL(x1+x2,0,"r"));
                    case "*":
                        return new NodoAritmetica (new VariableVAL(x1*x2,0,"r"));
                    case "/":
                        return new NodoAritmetica (new VariableVAL(x1/x2,0,"r"));
                    case "-":
                        return new NodoAritmetica (new VariableVAL(x1-x2,0,"r"));
                    case "%":
                        return new NodoAritmetica (new VariableVAL(x1%x2,0,"r"));
                }
            }else if(type==1){
                int x1=-1;
                int x2=-1;
                try{
                    x1=Integer.parseInt(String.valueOf(val1.variable.getValor()));
                }catch (Exception ex){
                    x1=(int) ((char)val1.variable.getValor());
                }
                try {
                    x2=Integer.parseInt(String.valueOf(val2.variable.getValor()));
                }catch (Exception ex){
                    x2=(int) ((char)val2.variable.getValor());
                }
                switch(op){
                    case "+":
                        return new NodoAritmetica (new VariableVAL(x1+x2,1,"e"));
                    case "*":
                        return new NodoAritmetica (new VariableVAL(x1*x2,1,"e"));
                    case "/":
                        return new NodoAritmetica (new VariableVAL(x1/x2,1,"e"));
                    case "-":
                        return new NodoAritmetica (new VariableVAL(x1-x2,1,"e"));
                    case "%":
                        return new NodoAritmetica (new VariableVAL(x1%x2,1,"e"));
                }
            }
        }*/
        return nodo;
    }
    public void verifyID(ArrayList<VariableDeclaracion>list, ErrorClass errorClass,int tipo){
        //this.parser.errores.update(x1left,x1right,"Error al declarar una variable, verifique que la asignacion sea correcta");
        for (int i = 0; i < list.size(); i++) {
            VariableDeclaracion x=list.get(i);
            switch (tipo){
                case 0:
                    x.TIPO=tipo;
                    x.string="r";
                    break;
                case 1:
                    x.TIPO=1;
                    x.string="e";
                    break;
                case 2:
                    x.TIPO=1;
                    x.string="c";
                    break;
            }

        }

    }

    public static String verifyArgs(ArrayList<VariableDeclaracion>list, ErrorClass errorClass){
        String args="";
        for (int i = 0; i < list.size(); i++) {
            VariableDeclaracion x=list.get(i);
            switch (x.TIPO){
                case 0:
                    x.string="r";
                    args+="_"+"float";
                    break;
                case 1:
                    x.TIPO=1;
                    x.string="e";
                    args+="_"+"int";
                    break;
                case 2:
                    x.TIPO=1;
                    x.string="c";
                    args+="_"+"char";
                    break;
            }

            if(x.hasBennCheck){
               continue;
            }
            for (int j = i+1; j < list.size(); j++) {
                VariableDeclaracion y=list.get(j);
                if(x.ID.equals(y.ID) && x.globalVar==y.globalVar){
                    errorClass.AddError(2, y.fila, y.columna, y.ID,"El Identificador" +
                            "se repite en:\nfila:"+x.fila+"\ncolumna:"+x.columna);

                }

            }
            x.hasBennCheck=true;
        }
        return args;
    }

    public static void verifyMetodos(ArrayList<metodos> metodos,ErrorClass errorClass){
        if(metodos.isEmpty()){
            return;
        }
        for (int i = 0; i < metodos.size(); i++) {
            metodos x=metodos.get(i);
            for (int j = i+1; j < metodos.size(); j++) {
                metodos y =metodos.get(j);
                if(x.ID.equals(y.ID) && x.args==y.args){
                    if(x.args==-1){
                        errorClass.AddError(2,y.FILA,y.COLUMNA,y.ID,"Iguales metodos" +
                                " En la fila:"+x.FILA+" Columna"+x.COLUMNA);
                    }else{
                        boolean equals=true;
                        for (int k = 0; k < x.args; k++) {
                            VariableDeclaracion k1=x.argumentos.get(k);
                            VariableDeclaracion l1=y.argumentos.get(k);
                                if(k1.string!=l1.string){
                                    equals=false;
                                    break;
                                }

                        }
                        if(equals){
                            errorClass.AddError(2,y.FILA,y.COLUMNA,y.ID,"Iguales metodos" +
                                    " En la fila:"+x.FILA+" Columna"+x.COLUMNA);
                            break;
                        }
                    }
                }
            }
        }
    }
    public static void verifyConstruc(ArrayList<metodos> metodos,ErrorClass errorClass,String ID){
        if(metodos.isEmpty()){
            return;
        }
        for (int i = 0; i < metodos.size(); i++) {
            metodos x=metodos.get(i);
            if(!x.ID.equals(ID)){
                errorClass.AddError(2,x.FILA,x.COLUMNA,x.ID,"Error al " +
                        "en el nombre del constructor, debe tener de nombre "+ID);
            }
            for (int j = i+1; j < metodos.size(); j++) {
                metodos y =metodos.get(j);
                if( x.args==y.args){
                    if(x.args==-1){
                        errorClass.AddError(2,y.FILA,y.COLUMNA,y.ID,"Iguales costructores" +
                                ", en la fila:"+x.FILA+" Columna"+x.COLUMNA);
                    }else{
                        boolean equals=true;
                        for (int k = 0; k < x.args; k++) {
                            VariableDeclaracion k1=x.argumentos.get(k);
                            VariableDeclaracion l1=y.argumentos.get(k);
                            if(k1.string!=l1.string){
                                equals=false;
                                break;
                            }

                        }
                        if(equals){
                            errorClass.AddError(2,y.FILA,y.COLUMNA,y.ID,"Iguales costructores" +
                                    " En la fila:"+x.FILA+" Columna"+x.COLUMNA);
                            break;
                        }
                    }
                }
            }
        }
    }

    public static boolean getVarPy(ArrayList<asignacion_PY> list,String id,int f,int c,ErrorClass errorClass){

        for (asignacion_PY x:list) {
            if(id.equals(x.ID) && x.COLUMNA<=c){
                System.out.println("Id"+id+":"+x.COLUMNA+" Id"+x.ID+":"+c);
                return true;
            }
        }
        errorClass.AddError(2,f,c,id,"La variable "+id +" aun no ha sido declarada");
        return false;
    }
    public static void verifyInstr(ArrayList<VariableDeclaracion>list,ErrorClass errorClass){
        if(list==null){
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            VariableDeclaracion x=list.get(i);
            if(x.hasBennCheck){
                continue;
            }


            for (int j = i-1; j >=0; j--) {
                VariableDeclaracion y=list.get(j);
                if(x.ID.equals(y.ID) && x.globalVar==y.globalVar){
                    errorClass.AddError(2, x.fila, x.columna, y.ID,"El Identificador" +
                            "se repite en:\nfila:"+y.fila+"\ncolumna:"+y.columna);

                }

            }
            x.hasBennCheck=true;
        }
    }

    public static void verifyLibrary(String id,int f,int c,ArrayList<String> list,ErrorClass errorClass){
        for (int i = list.size()-1; i >=0 ; i--) {
            if(list.get(i).equals(id)){
                return;
            }
        }
        errorClass.AddError(2,f,c,id,"" +
                "La libreria de "+id +"(libreria java) no ha sido declarada");

    }

    public Object[] getObj(Object v1,Object v2){
        Object object[]=new Object[2];
        object[0]=v1;
        object[1]=v2;
    return object;
    }

    public Object[] getObj(Object v1,Object v2,int f,int c){
        Object object[]=new Object[4];
        object[0]=v1;
        object[1]=v2;
        object[2]=f;
        object[3]=c;

        return object;
    }


    public static boolean canAdd(ArrayList<VariableDeclaracion>list,String id){
        if(list==null){
            return true;
        }
        for (VariableDeclaracion x:list) {
            if(x!=null){
                if(x.ID.equals(id)){
                    return false;
                }
            }
        }
        return true;
    }

}
