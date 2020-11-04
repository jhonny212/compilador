package Variable;

import GramaticaC.call_c;
import GramaticaC.declaracion_c;

import java.util.ArrayList;

public class VariableMETODO extends Variable{
    public call_c declaracion_c;
    public VariableMETODO(call_c declaracion_c){
        this.declaracion_c=declaracion_c;
        isNull=false;
        this.TIPO=-10;
        this.string="e";
    }
    @Override
    public boolean isNull() {
        return this.isNull;
    }

    @Override
    public Object getValor() {
        return null;
    }
    boolean change=false;
    @Override
    public void change() {
        this.change=!change;
    }

    @Override
    public String getStr() {
        return this.lasValtmp1;
    }

    public void validate(ArrayList<VariableDeclaracion> list){
        declaracion_c.variables=list;
        if(this.lasValtmp1.equals("%")){

            declaracion_c.lasValtmp1="%";
        }

        declaracion_c.validate(false);
        this.TIPO=declaracion_c.tipo;
        this.string=declaracion_c.string;
        this.lasValtmp1=declaracion_c.lasValtmp1;

    }
}
