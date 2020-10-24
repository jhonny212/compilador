package TablaSimbolos;

import java.util.ArrayList;
import java.util.Arrays;

public class SymTable {
    public static ArrayList<celda> celdas=new ArrayList<>();

    public static void ADD(int TIPO_VAR, boolean IS_ARRAY, int POS_MEMORIA,
                           int AMBITO, int AMBITO_PADRE, String ID){
        if(AMBITO==AMBITO_PADRE){
            celdas.add(new celda(TIPO_VAR,IS_ARRAY,POS_MEMORIA,
                    AMBITO,AMBITO_PADRE,ID));
        }else{
            for (celda celda:celdas) {
                if(celda.AMBITO==AMBITO_PADRE){
                    celdas.add(new celda(TIPO_VAR,IS_ARRAY,POS_MEMORIA,
                            AMBITO,AMBITO_PADRE,ID));
                    return;
                }
            }

            while (true){
                int tmp=ambitoTable.buscarNext(AMBITO_PADRE);
                if(tmp==-1){
                    break;
                }
                AMBITO_PADRE=tmp;
                for (celda celda:celdas) {
                    if(celda.AMBITO==tmp){
                        celdas.add(new celda(TIPO_VAR,IS_ARRAY,POS_MEMORIA,
                                AMBITO,tmp,ID));
                        return;
                    }
                }
            }
            celdas.add(new celda(TIPO_VAR,IS_ARRAY,POS_MEMORIA,
                    AMBITO,AMBITO,ID));

        }
    }

    public static void print(){
        celdas.stream()
                .forEach((x)->{
                    System.err.println("ID: "+x.ID+" AMB: "+x.AMBITO+" AMB_DAD:"+x.AMBITO_PADRE);
                });
        System.err.println("*******************");
        ambitoTable.ambitos
                .stream()
                .forEach((x)->{
                    System.err.println(Arrays.toString(x));
                });
    }

    public static class  celda{
        public final int TIPO_VAR;
        public final boolean IS_ARRAY;
        public final int POS_MEMORIA;
        public final int AMBITO;
        public final int AMBITO_PADRE;
        public final String ID;

        public celda(int TIPO_VAR, boolean IS_ARRAY, int POS_MEMORIA,
                     int AMBITO, int AMBITO_PADRE, String ID) {
            this.TIPO_VAR = TIPO_VAR;
            this.IS_ARRAY = IS_ARRAY;
            this.POS_MEMORIA = POS_MEMORIA;
            this.AMBITO = AMBITO;
            this.AMBITO_PADRE = AMBITO_PADRE;
            this.ID = ID;
        }
    }
}
