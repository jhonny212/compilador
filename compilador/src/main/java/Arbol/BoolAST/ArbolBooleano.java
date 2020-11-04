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
         /*if(href.isOp && href.cambiarSigno==1){
                 System.err.println("ENTROOOOOOOOOOOOOOOOO");
                 MetodosVisual.CONTADOR_ETIQ++;
                 MetodosVisual.add("","t","","goto E"+gI(),3);
                 int tmp=MetodosVisual.CONTADOR_ETIQ;
                 MetodosVisual.CONTADOR_ETIQ++;
                 MetodosVisual.add("Asig","1","","t",2);
                 MetodosVisual.add("goto","E",String.valueOf(gI()),"",4);
                 MetodosVisual.add("Et","E",String.valueOf(tmp),"",5);
                 MetodosVisual.add("Asig","0","","t",2);
                 MetodosVisual.add("Et","E",String.valueOf(gI()),"",5);
         }*/

    }
     void validate(NodoBoolean href){

        if(href.isOp){
            int previo=-10;
            MetodosVisual.CONTADOR_ETIQ+=2;
            if(href.izq!=null){
                if(href.izq instanceof NodoBoolean){

                    validate((NodoBoolean) href.izq);
                    if(href.operador.equals("and") ){
                        if(href.izq.cambiarSigno==0 || href.izq.isOp){
                            MetodosVisual.CONTADOR_ETIQ++;
                            add();
                            MetodosVisual.add("Asig","0","","t",2);
                            previo=MetodosVisual.getAndCNT();
                            MetodosVisual.add("goto","E",String.valueOf(previo),"",4);
                            MetodosVisual.add("Et","E",String.valueOf(gI()),"",5);
                        }else {
                            MetodosVisual.CONTADOR_ETIQ++;
                            previo=MetodosVisual.getAndCNT();
                            MetodosVisual.add("Asig","0","","t",2);
                            add(previo);
                            MetodosVisual.add("goto","E",String.valueOf(gI()),"",4);
                            MetodosVisual.add("Et","E",String.valueOf(gI()),"",5);
                        }
                    }else{
                        if(href.izq.cambiarSigno==0 || href.izq.isOp){
                            MetodosVisual.CONTADOR_ETIQ++;
                            previo=MetodosVisual.CONTADOR_ETIQ;
                            add();
                        }else{
                            MetodosVisual.add("Asig","0","","t",2);
                            MetodosVisual.CONTADOR_ETIQ++;
                            previo=MetodosVisual.CONTADOR_ETIQ;
                            add();
                        }
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
                        if(href.der.cambiarSigno==0 || href.der.isOp){
                            MetodosVisual.CONTADOR_ETIQ++;
                            add();
                            MetodosVisual.add("Asig","0","","t",2);
                            MetodosVisual.add("goto","E",String.valueOf(previo),"",4);
                            MetodosVisual.add("Et","E",String.valueOf(gI()),"",5);
                            MetodosVisual.add("Asig","1","","t",2);
                            if(previo>=0)
                            {
                                MetodosVisual.add("Et","E",String.valueOf(previo),"",5);
                            }
                            MetodosVisual.CONTADOR_ETIQ++;
                        }else{
                            MetodosVisual.CONTADOR_ETIQ++;
                            if(href.izq.cambiarSigno!=1 || href.izq.isOp){
                                MetodosVisual.add("Asig","0","","t",2);
                            }
                            add(previo);
                            MetodosVisual.add("Asig","1","","t",2);
                            if(previo>=0)
                            {
                                MetodosVisual.add("Et","E",String.valueOf(previo),"",5);
                            }
                            MetodosVisual.CONTADOR_ETIQ++;
                        }
                    }else if(!href.izq.isOp && href.izq.cambiarSigno==1 && href.der.cambiarSigno==1){
                        MetodosVisual.add("Asig","1","","t",2);
                        MetodosVisual.CONTADOR_ETIQ++;
                        MetodosVisual.add("goto","E",String.valueOf(gI()),"",4);
                        MetodosVisual.add("Et","E",String.valueOf(previo),"",5);
                        add();
                        MetodosVisual.add("Asig","1","","t",2);
                        MetodosVisual.add("Et","E",String.valueOf(gI()),"",5);
                    }else if(!href.izq.isOp && href.izq.cambiarSigno==1 && href.der.cambiarSigno==0){
                        MetodosVisual.add("Asig","1","","t",2);
                        MetodosVisual.CONTADOR_ETIQ++;
                        MetodosVisual.add("goto","E",String.valueOf(gI()),"",4);
                        MetodosVisual.add("Et","E",String.valueOf(previo),"",5);
                        MetodosVisual.add("Asig","1","","t",2);
                        add();
                        MetodosVisual.add("Asig","0","","t",2);
                        MetodosVisual.add("Et","E",String.valueOf(gI()),"",5);
                    }else if(!href.izq.isOp && href.izq.cambiarSigno==0 && href.der.cambiarSigno==1){

                        MetodosVisual.add("Asig","0","","t",2);
                        MetodosVisual.CONTADOR_ETIQ++;
                        add();
                        MetodosVisual.add("Et","E",String.valueOf(previo),"",5);
                        MetodosVisual.add("Asig","1","","t",2);
                        MetodosVisual.add("Et","E",String.valueOf(gI()),"",5);

                    }
                    else {
                        add(previo);
                        MetodosVisual.CONTADOR_ETIQ++;
                        MetodosVisual.add("Asig","0","","t",2);
                        MetodosVisual.add("goto","E",String.valueOf(gI()),"",4);
                        MetodosVisual.add("Et","E",String.valueOf(previo),"",5);
                        MetodosVisual.add("Asig","1","","t",2);
                        MetodosVisual.add("Et","E",String.valueOf(gI()),"",5);
                    }


                    val1="t";
                    operador="";
                    val2="";


                }
            }
            if(href.cambiarSigno==1 && href.isOp){
                MetodosVisual.CONTADOR_ETIQ++;
                MetodosVisual.add("","t","","goto E"+gI(),3);
                int tmp=MetodosVisual.CONTADOR_ETIQ;
                MetodosVisual.CONTADOR_ETIQ++;
                MetodosVisual.add("Asig","1","","t",2);
                MetodosVisual.add("goto","E",String.valueOf(gI()),"",4);
                MetodosVisual.add("Et","E",String.valueOf(tmp),"",5);
                MetodosVisual.add("Asig","0","","t",2);
                MetodosVisual.add("Et","E",String.valueOf(gI()),"",5);
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
                    MetodosVisual.add("Asig",val1,"","num_tmp_float",2);
                    val1="num_tmp_float";
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
