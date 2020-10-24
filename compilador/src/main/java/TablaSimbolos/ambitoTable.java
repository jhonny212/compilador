package TablaSimbolos;

import java.util.ArrayList;

public class ambitoTable {
    public static ArrayList<int []> ambitos=new ArrayList<>();

    public static void ADD_AMB(int i,int j){
        int array[]={i,j};
        ambitos.add(array);
    }

    public static int buscarNext(int i){
        for (int vector[]:ambitos) {
            if(i==vector[0]){
                return vector[1];
            }
        }
    return -1;
    }

}
