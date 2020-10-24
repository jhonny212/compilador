package OptimizarCodigoIntermedio;

import Interfaz.Editor;
import Lenguajes.Cuadruplas;
import Lenguajes.MetodosVisual;

import java.io.File;
import java.util.ArrayList;

public class OptimizarCodigo {
    ArrayList<Cuadruplas> codigoOptimizado=new ArrayList<>();
    public void optimiar(){
        if(validar()){
            vectorDatosExtra=new String[MetodosVisual.instrOptim.size()];
            optimizar2(MetodosVisual.instrOptim,false);
        }
    }
    void optimizar2(ArrayList<Cuadruplas> list,boolean valid){
            for (int i = 0; i < list.size(); i++) {
                Cuadruplas cuadrupla=list.get(i);
                if(cuadrupla.canAdd){
                    if(vectorDatosExtra[i]==null){
                        vectorDatosExtra[i]="";
                    }
                    switch (cuadrupla.TIPO){
                        case 1:
                        case 2:
                            if(cuadrupla.RESULT.equals(cuadrupla.ARG1)){
                                vectorDatosExtra[i]="La asignacion corresponde a la misma variable";
                                cuadrupla.canAdd=false;
                            }
                            break;
                        case 0:
                            int xx=numArgs(cuadrupla);
                            switch (xx){
                                case 1:
                                    validateType1(cuadrupla);
                                    vectorDatosExtra[i]="Operacion con valor cero, puede ser reducida";
                                    break;
                                case -1:
                                    validateType2(cuadrupla);
                                    vectorDatosExtra[i]="Operacion con valor cero, puede ser reducida";
                                    break;
                                case 2:
                                    validateType3(cuadrupla);
                                    vectorDatosExtra[i]="Operacion con valor cero, puede ser reducida";
                                    break;
                            }
                            break;
                    }
                    if(cuadrupla.TIPO==1 || cuadrupla.TIPO==2){
                        try{
                            Cuadruplas cuadr=list.get(i-1);
                            if(cuadr.TIPO==0 && cuadr.canAdd){
                                if(cuadr.RESULT.equals(cuadrupla.ARG1)){
                                    cuadrupla.canAdd=false;
                                    cuadr.RESULT=cuadrupla.RESULT;
                                    vectorDatosExtra[i]="La asignacion puede asignarse de una vez, a la instruccion anterior";
                                }
                            }

                        }catch (Exception ex){}
                    }else if(cuadrupla.TIPO==5){
                        try {
                            Cuadruplas cuadr=list.get(i+1);
                            if(cuadr.canAdd && (cuadr.TIPO==5 || cuadr.TIPO==4)){
                                String numEtiqueta=cuadr.ARG2;
                                for (int j = i; j >=0 ; j--) {
                                    Cuadruplas tmp=list.get(j);
                                    if(tmp.TIPO==3){
                                        String string=tmp.RESULT.replaceFirst("goto E","");
                                        if(string.equals(cuadrupla.ARG2)){
                                            tmp.RESULT="goto E"+numEtiqueta;
                                            vectorDatosExtra[j]="La parte de la instruccion goto E# puede ir al destino directamente";
                                            vectorDatosExtra[i]="La etiqueta no es usada por un goto, puede eliminarse";
                                            cuadrupla.canAdd=false;
                                            if(cuadr.TIPO==4){
                                                vectorDatosExtra[i+1]="La instruccion puede ser eliminada, dado que se elimina la etiqueta";
                                                cuadr.canAdd=false;
                                            }
                                        }
                                    }else if(tmp.TIPO==4 && tmp.ARG2.equals(cuadrupla.ARG2)){
                                        tmp.ARG2=numEtiqueta;
                                        vectorDatosExtra[i]="La etiqueta no es usada por un goto, puede eliminarse";
                                        vectorDatosExtra[j]="La parte de la instruccion goto E# puede ir al destino directamente";
                                        cuadrupla.canAdd=false;
                                        if(cuadr.TIPO==4){
                                            cuadr.canAdd=false;
                                        }
                                    }
                                }
                            }
                        }catch (Exception ex){}
                    }
                }
                /*if(valid){
                    if(cuadrupla.canAdd){
                        this.codigoOptimizado.add(cuadrupla);
                    }
                }*/
            }

    }
    int numArgs(Cuadruplas x){
        if(x.ARG1.equals("0")){
            if(x.ARG2.equals("0")){
                return -1;
            }
            return 1;
        }else if(x.ARG2.equals("0")){
            return 2;
        }
        return 0;
    }

    String vectorDatosExtra[];

    void validateType1(Cuadruplas x){
        switch (x.OP){
            case "+":
            case "-":
                x.TIPO=1;
                x.ARG1=x.ARG2;
                x.ARG2="";
                break;
            case "*":
            case "/":
                x.TIPO=1;
                x.ARG1="0";
                x.ARG2="";
                break;
        }
    }
    void validateType2(Cuadruplas x){
        switch (x.OP){
            case "+":
            case "-":
                x.TIPO=1;
                x.ARG1="0";
                x.ARG2="";
                break;

        }
    }
    void validateType3(Cuadruplas x){
        switch (x.OP){
            case "+":
            case "-":
            case "*":
                x.TIPO=1;
                x.ARG2="";
                break;
        }
    }
    public void print(){
        String codigo="<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n" +
                "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\n" +
                "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700\" rel=\"stylesheet\">\n" +
                "      \n" +
                "</head>\n" +
                "<body>\n" +
                "    <style>\n" +
                "        .table{\n" +
                "            width: 95%;\n" +
                "            height: 100%;\n" +
                "            margin: 50px auto;\n" +
                "        }\n" +
                "        body{\n" +
                "            background-color: rgb(192, 190, 187);\n" +
                "        }\n" +
                "    </style>\n" +
                "    <table class=\"table\">\n" +
                "        <thead class=\"thead-dark\">\n" +
                "          <tr>\n" +
                "            <th scope=\"col\">#</th>\n" +
                "            <th scope=\"col\">Codigo Intermedio</th>\n" +
                "            <th scope=\"col\">Codigo Optimizado</th>\n" +
                "            <th scope=\"col\">Dato</th>\n" +
                "          </tr>\n" +
                "        </thead>\n" +
                "        <tbody>";
        for (int i = 0; i < MetodosVisual.instrucciones.size(); i++) {
            Cuadruplas x=MetodosVisual.instrucciones.get(i);
            Cuadruplas y=MetodosVisual.instrOptim.get(i);
            codigo+="          <tr>\n" +
                    "            <th scope=\"row\">"+(i+1)+"</th>\n" +
                    "            <td>"+x.PRINT()+"</td>\n" +
                    "            "+(y.canAdd?"<td>"+y.PRINT()+"</td>":"<td style=\"color:red\">"+y.PRINT()+"</td>")+"\n" +
                    "            <td>"+vectorDatosExtra[i]+"</td>\n" +
                    "          </tr>\n";
        }
        codigo+="        </tbody>\n" +
                "      </table>\n" +
                "     \n" +
                "</body>\n" +
                "</html>";
        Editor.write(new File("reporte.html"),codigo);
        //System.err.println(codigo);
    }

    boolean validar(){
        if(MetodosVisual.instrOptim==null)
            return false;
        return !MetodosVisual.instrOptim.isEmpty();
    }

    class  createHtML{
        String THEAD="";
        String TBODY="";
        String BODY="";
        String HTML="";


    }
}
