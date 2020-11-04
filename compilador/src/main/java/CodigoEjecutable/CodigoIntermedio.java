package CodigoEjecutable;

import Lenguajes.Cuadruplas;

import java.util.ArrayList;

public class CodigoIntermedio {
    ArrayList<Cuadruplas> cuadruplas=new ArrayList<>();
    String identificador="",string_valIzq="",string_valDer="";
    String inicioMetodo="-1";
    int tipo_metodo=0,numParam=0;
    int [] values,datos[];
    public void generar(ArrayList<Cuadruplas>href){
        InstruccionesCodigoIntermedio intermedio=new InstruccionesCodigoIntermedio(this);
         for (int i = 0; i < href.size(); i++) {
           Cuadruplas cuadrupla=href.get(i);
           switch (cuadrupla.TIPO){
               case 0:
                   //instruccion tipo x=10+x
                    intermedio.CrearArgumento(cuadrupla);
                   break;

           }
        }
    }
}
