package Arbol.BoolAST;

import Arbol.Arbol;
import Arbol.AritAST.ArbolAritmetica;
import Arbol.AritAST.NodoAritmetica;
import Lenguajes.MetodosVisual;
import Variable.VariableDeclaracion;

import java.util.ArrayList;

public class ArbolBooleano extends Arbol {
    public ArbolBooleano(ArrayList<VariableDeclaracion> syms){
        this.tablaSimbolos=syms;
    }

    public void recorrer(NodoBoolean href){
         validate(href);

    }
     void validate(NodoBoolean href){

        if(href.isOp){
            int previo=-10;
            MetodosVisual.CONTADOR_ETIQ+=2;
            if(href.izq!=null){
                if(href.izq instanceof NodoBoolean){
                    validate((NodoBoolean) href.izq);
                    if(href.operador.equals("and") ){
                        MetodosVisual.CONTADOR_ETIQ++;
                        //MetodosVisual.add(operador,val1,val2,"goto E"+gI(),3);
                        add();
                        MetodosVisual.add("Asig","0","","t",2);
                        //stringBuffer.append(getSTR());
                        //stringBuffer.append("   t=0;\n");
                        previo=MetodosVisual.getAndCNT();
                        MetodosVisual.add("goto","E",String.valueOf(previo),"",4);
                        //stringBuffer.append("   goto E"+MetodosVisual.getAndCNT()+";\n");
                        //stringBuffer.append("E"+MetodosVisual.CONTADOR_ETIQ+":\n");
                        MetodosVisual.add("Et","E",String.valueOf(gI()),"",5);
                    }else{
                        MetodosVisual.CONTADOR_ETIQ++;
                        previo=MetodosVisual.CONTADOR_ETIQ;
                        add();
                        //stringBuffer.append(getSTR());
                    }
                    val1="t";
                    operador="";
                    val2="";
                }
            }



            if(href.der!=null){
                if(href.der instanceof NodoBoolean){
                    validate((NodoBoolean) href.der);

                    if(href.operador.equals("and") ){
                        MetodosVisual.CONTADOR_ETIQ++;
                        add();
                        MetodosVisual.add("Asig","0","","t",2);
                        MetodosVisual.add("goto","E",String.valueOf(previo),"",4);
                        MetodosVisual.add("Et","E",String.valueOf(gI()),"",5);
                        MetodosVisual.add("Asig","1","","t",2);
                        //stringBuffer.append("   "+getSTR());
                        //stringBuffer.append("   t=0;\n");
                        //stringBuffer.append("   goto E"+previo+";\n");
                        //stringBuffer.append("E"+MetodosVisual.CONTADOR_ETIQ+":\n");
                        //stringBuffer.append("   t=1;\n");
                        if(previo>=0)
                        {
                            MetodosVisual.add("Et","E",String.valueOf(previo),"",5);
                            //stringBuffer.append("E"+previo+":\n");
                        }
                        MetodosVisual.CONTADOR_ETIQ++;
                    }else{
                        //stringBuffer.append(getSTR(previo));
                        add(previo);
                        MetodosVisual.CONTADOR_ETIQ++;
                        MetodosVisual.add("Asig","0","","t",2);
                        MetodosVisual.add("goto","E",String.valueOf(gI()),"",4);
                        MetodosVisual.add("Et","E",String.valueOf(previo),"",5);
                        MetodosVisual.add("Asig","1","","t",2);
                        MetodosVisual.add("Et","E",String.valueOf(gI()),"",5);

                        //stringBuffer.append("   t=0;\n");
                        //stringBuffer.append("   goto E"+MetodosVisual.CONTADOR_ETIQ+";\n");
                        //stringBuffer.append("E"+(previo)+":\n");
                        //stringBuffer.append("   t=1;\n");
                        //stringBuffer.append("E"+(MetodosVisual.CONTADOR_ETIQ)+":\n");


                    }
                    val1="t";
                    operador="";
                    val2="";
                }
            }



        }else{
            val1="";val2="";operador="";
            if(href.izq!=null){
                if(href.izq instanceof NodoAritmetica){
                    ArbolAritmetica arbolAritmetica=new ArbolAritmetica(this.tablaSimbolos);
                    arbolAritmetica.errorClass=this.errorClass;
                    arbolAritmetica.recorrer((NodoAritmetica) href.izq);
                    val1=arbolAritmetica.lastVal;
                }
            }
            if(href.der!=null){
                if(href.der instanceof NodoAritmetica){
                    ArbolAritmetica arbolAritmetica=new ArbolAritmetica(this.tablaSimbolos);
                    arbolAritmetica.errorClass=this.errorClass;
                    arbolAritmetica.recorrer((NodoAritmetica) href.der);
                    val2=arbolAritmetica.lastVal;
                }
                operador=href.operador;
            }
        }
    }

    public String val1="",val2="",operador="";

    String getSTR(){
         return  "if ("+val1+operador+val2+") goto E"+MetodosVisual.CONTADOR_ETIQ+";\n";
     }
    String getSTR(int x){
        return  "if ("+val1+operador+val2+") goto E"+x+";\n";
    }

    int gI(){
          return MetodosVisual.CONTADOR_ETIQ;
    }

    public void add(){
        MetodosVisual.add(operador,val1,val2,"goto E"+gI(),3);
    }
   public void add(int x){
        MetodosVisual.add(operador,val1,val2,"goto E"+x,3);
    }

}
