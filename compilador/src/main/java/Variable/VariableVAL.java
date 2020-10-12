package Variable;

public class VariableVAL extends Variable{


    public VariableVAL(Object value,int tipo,String string) {
        this.value = value;
        this.TIPO=tipo;
        this.string=string;
    }





    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public Object getValor() {
        return this.value;
    }

    @Override
    public void change() {
        if(this.TIPO==0){
            double db=(double)this.value;
            this.value=(-1)*db;
        }else if(this.TIPO==1){
            int db=(Integer) this.value;
            this.value=(-1)*db;
        }
    }

    @Override
    public String getStr() {
        if(this.string.equals("c")){
            return "'"+(this.value)+"'";
        }else{
             return String.valueOf(this.value);
        }
    }


}
