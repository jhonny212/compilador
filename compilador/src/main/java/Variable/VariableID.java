package Variable;

public class VariableID extends Variable{

    public boolean singChange=false;

    public VariableID(String ID,int fila,int columna) {
        this.ID = ID;
        this.fila=fila;
        this.columna=columna;
        this.isNull=true;
    }



    @Override
    public boolean isNull() {
        return isNull;
    }

    @Override
    public Object getValor() {
        return ID;
    }

    @Override
    public void change() {
        this.singChange=true;
    }

    @Override
    public String getStr() {
        return this.ID;
    }

}
