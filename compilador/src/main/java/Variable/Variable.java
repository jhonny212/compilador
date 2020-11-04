package Variable;

import Arbol.Nodo;

abstract public class Variable {
    public Object value;
    public int TIPO=-1;
    public String ID;
    public Nodo nodo;
    public int globalVar=0;
    public int fila,columna;
    public String string;
    public boolean isNull=false,pass=false;
    public abstract boolean isNull();
    public abstract Object getValor();
    public String codigo3d="";
    public  String lasValtmp1="";

    public boolean isFunExter=false,isConst=false,isJava=false;

    abstract public void change();

    abstract public String getStr();
}
