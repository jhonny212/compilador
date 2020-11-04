package TablaSimbolos;

import CodigoEjecutable.generarCodigoIntermedio;

import java.util.ArrayList;
public class SymTable {
    public static ArrayList<celda> celdas=new ArrayList<>(),celdas2=new ArrayList<>();
    public static boolean canAdd=true;
    public static  int size_int=0,size_double=0,size_char=0;
    public static void ADD(int TIPO_VAR, boolean IS_ARRAY, int POS_MEMORIA,
                           int AMBITO, int AMBITO_PADRE, String ID){
        switch (TIPO_VAR){
            case 0:
                size_double++;
                break;
            case 1:
                size_int++;
                break;
            case 2:
                size_char++;
                break;
        }
        if(AMBITO==AMBITO_PADRE || (AMBITO==0 && AMBITO_PADRE==-1)){
            if(canAdd){
                celdas.add(new celda(TIPO_VAR,IS_ARRAY,POS_MEMORIA,
                        AMBITO,AMBITO_PADRE,ID,getPos(TIPO_VAR)));
            }else{
                celdas2.add(new celda(TIPO_VAR,IS_ARRAY,POS_MEMORIA,
                        AMBITO,AMBITO_PADRE,ID,getPos(TIPO_VAR)));
            }
        }else{
            if(canAdd){
                for (celda celda:celdas) {
                    if(celda.AMBITO==AMBITO_PADRE){
                        celdas.add(new celda(TIPO_VAR,IS_ARRAY,POS_MEMORIA,
                                AMBITO,AMBITO_PADRE,ID,getPos(TIPO_VAR)));
                        return;
                    }
                }
            }else{
                for (celda celda:celdas2) {
                    if(celda.AMBITO==AMBITO_PADRE){
                        celdas2.add(new celda(TIPO_VAR,IS_ARRAY,POS_MEMORIA,
                                AMBITO,AMBITO_PADRE,ID,getPos(TIPO_VAR)));
                        return;
                    }
                }
            }

            while (true){
                int tmp=ambitoTable.buscarNext(AMBITO_PADRE);
                if(tmp==-1){
                    break;
                }
                AMBITO_PADRE=tmp;
                if(canAdd){
                    for (celda celda:celdas) {
                        if(celda.AMBITO==tmp){
                            celdas.add(new celda(TIPO_VAR,IS_ARRAY,POS_MEMORIA,
                                    AMBITO,tmp,ID,getPos(TIPO_VAR)));
                            return;
                        }
                    }
                }else{
                    for (celda celda:celdas) {
                        if(celda.AMBITO==tmp){
                            celdas.add(new celda(TIPO_VAR,IS_ARRAY,POS_MEMORIA,
                                    AMBITO,tmp,ID,getPos(TIPO_VAR)));
                            return;
                        }
                    }
                }
            }
            if(canAdd){
                celdas.add(new celda(TIPO_VAR,IS_ARRAY,POS_MEMORIA,
                        AMBITO,AMBITO,ID,getPos(TIPO_VAR)));
            }else{
                celdas2.add(new celda(TIPO_VAR,IS_ARRAY,POS_MEMORIA,
                        AMBITO,AMBITO,ID,getPos(TIPO_VAR)));
            }

        }
    }

    public static void print(){
        celdas.stream()
                .forEach((x)->{
                    System.err.println("{" +
                            "ID:   "+x.ID+"," +
                            "AMB:  "+x.AMBITO+"," +
                            "AMD:  "+x.AMBITO_PADRE+"," +
                            "TIP:  "+x.TIPO_VAR+"," +
                            "POS:  "+x.POS_MEMORIA+"" +
                            "}");
                });
        System.err.println("*******************");

    }

    public static void print2(){
        celdas.stream()
                .forEach((x)->{
                    System.err.println(x.ID+","+x.AMBITO+","+x.AMBITO_PADRE+","+x.TIPO_VAR+","+x.POS_MEMORIA+" ,"+x.IS_ARRAY+", "+x.sizePOS);
                });
    }
    public static void fix(){
        celdas.addAll(0,celdas2);
        celdas2=new ArrayList<>();
    }
    public static int getPos(int x){
        switch (x){
            case 0:
                return size_double;

            case 1:
                return size_int;
            case 2:
                return size_char;
        }
        return 0;
    }
    public static class  celda{
        public final int TIPO_VAR;
        public final boolean IS_ARRAY;
        public boolean IS_FUN=false;
        public  int POS_MEMORIA;
        public final int AMBITO;
        public final int AMBITO_PADRE;
        public String ID;
        public int sizePos=0;
        public String sizePOS="";
        public celda(int TIPO_VAR, boolean IS_ARRAY, int POS_MEMORIA,
                     int AMBITO, int AMBITO_PADRE, String ID,int sizePos) {
            this.TIPO_VAR = TIPO_VAR;
            this.IS_ARRAY = IS_ARRAY;
            this.POS_MEMORIA = POS_MEMORIA;
            this.AMBITO = AMBITO;
            this.AMBITO_PADRE = AMBITO_PADRE;
            this.ID = ID;
            this.sizePos=sizePos;
            this.sizePOS=String.valueOf(sizePos);
        }
    }

    public static String SIZE_POS;

    public static int[] getID(String id,int inicio){
        validADD=false;
        strings.clear();
        int tipo=0;
        if(id.startsWith("this.")){
            inicio=generarCodigoIntermedio.posCLASE;
        }
        for (int i = inicio+1; i <celdas.size() ; i++) {
            if((celdas.get(i).ID.equals(id))){
                tipo=celdas.get(i).TIPO_VAR;
                break;
            }
        }
        int tmp[]= obtenerID(id,inicio,tipo);
        if(tmp!=null){
            strings.forEach((string)->{
                int x= (int) string[1];
                if(x==tmp[1]){
                    append(String.valueOf(string[0]));
                }
            });
        }

        return tmp;
    }
    public static boolean validADD;
    public static int[] getMethod(String id, int inicio){
        validADD=true;
        strings.clear();
        int[] tmp = obtenerMethod(id,inicio);
        if(tmp!=null){
            strings.forEach((string)->{
                 append(String.valueOf(string[0]));
            });
        }
        return tmp;
    }
    static ArrayList<Object[]> strings=new ArrayList<>();

     static int[] obtenerID(String id,int inicio,int tipo){
        int posInt_=0,posDoub_=0,posChar_=0;
        String INT,DOUBLE,CHAR;
        INT="";DOUBLE="";CHAR="";

        for (int i = inicio+1; i <celdas.size() ; i++) {
            celda celda=celdas.get(i);
            if(celda.ID.equals(id)){
                if(posInt_!=0 && !INT.isEmpty()){
                    Object ob[]={"posInt=posInt+"+posInt_,1};
                    strings.add(ob);
                }
                if(posDoub_!=0 && !DOUBLE.isEmpty()){
                    Object ob[]={"posDouble=posDouble+"+posDoub_,0};
                    strings.add(ob);
                }
                if(posChar_!=0 && !CHAR.isEmpty()){
                    Object ob[]={"posChar=posChar+"+posChar_,2};
                    strings.add(ob);
                }

                INT=INT.isEmpty()?String.valueOf(posInt_):"posInt";
                CHAR=CHAR.isEmpty()?String.valueOf(posChar_):"posChar";
                DOUBLE=DOUBLE.isEmpty()?String.valueOf(posDoub_):"posDouble";

                SIZE_POS=celda.TIPO_VAR==0?DOUBLE:celda.TIPO_VAR==1?INT:CHAR;

                int tmp[]={celda.sizePos,celda.TIPO_VAR,i};
               return tmp;
            }
            Object obj[]=new Object[2];

            if(celda.TIPO_VAR==tipo || validADD){
                switch (celda.TIPO_VAR){
                    case 0:
                        if(!celda.IS_ARRAY){
                            posDoub_++;
                        }else{
                            if(DOUBLE.isEmpty()){
                                String tmp=add(celda.sizePOS,inicio,0,strings,0);
                                DOUBLE="posDouble="+posDoub_+"+"+tmp;
                            }else{
                                DOUBLE="posDouble=posDouble+"+posDoub_;
                                Object ob[]=new Object[2];
                                ob[0]=DOUBLE;
                                ob[1]=0;
                                strings.add(ob);
                                String tmp=add(celda.sizePOS,inicio,0,strings,0);
                                DOUBLE="posDouble=posDouble+"+tmp;
                            }
                            obj[0]=DOUBLE;
                            obj[1]=0;
                            strings.add(obj);
                            posDoub_=0;
                        }
                        break;
                    case 1:
                        if(!celda.IS_ARRAY){
                            posInt_++;

                        }else{
                            posInt_++;
                            if(INT.isEmpty()){
                                String tmp=add(celda.sizePOS,inicio,1,strings,1);
                                INT="posInt="+posInt_+" + "+tmp;
                            }else{
                                INT="posInt=posInt+"+posInt_;

                                Object ob[]=new Object[2];
                                ob[0]=INT;
                                ob[1]=1;
                                strings.add(ob);
                                String tmp=add(celda.sizePOS,inicio,1,strings,1);
                                INT="posInt=posInt+"+tmp;
                            }
                            //append(INT);
                            obj[0]=INT;
                            obj[1]=1;
                            strings.add(obj);
                            posInt_=0;
                        }


                        break;
                    case 2:
                        if(!celda.IS_ARRAY){
                            posChar_++;

                        }else{
                            if(CHAR.isEmpty()){
                                String tmp=add(celda.sizePOS,inicio,2,strings,2);
                                CHAR="posChar="+posChar_+"+"+tmp;
                            }else{
                                CHAR="posChar=posChar+"+posChar_;
                                Object ob[]=new Object[2];
                                ob[0]=CHAR;
                                ob[1]=2;
                                strings.add(ob);
                                String tmp=add(celda.sizePOS,inicio,2,strings,2);

                                CHAR="posChar=posChar+"+tmp;
                            }
                            //append(CHAR);
                            obj[0]=CHAR;
                            obj[1]=2;
                            strings.add(obj);
                            posChar_=0;
                        }
                        break;
                }
            }
        }
        return null;
    }
    public static String posCHAR="",posINT="",posDOUBLE="";
     static int[] obtenerMethod(String id, int inicio){
        posCHAR="";posINT="";posDOUBLE="";
        int posInt_=0,posDoub_=0,posChar_=0;
        String INT,DOUBLE,CHAR;
        INT="";DOUBLE="";CHAR="";
        boolean canADD=true;
        for (int i = 0; i <celdas.size() ; i++) {
            celda celda=celdas.get(i);
            if(celda.ID.equals(id)){
                if(posInt_!=0 && !INT.isEmpty()){
                    Object ob[]={"posInt=posInt+"+posInt_,1};
                    strings.add(ob);
                }
                if(posDoub_!=0 && !DOUBLE.isEmpty()){
                    Object ob[]={"posDouble=posDouble+"+posDoub_,0};
                    strings.add(ob);
                }
                if(posChar_!=0 && !CHAR.isEmpty()){
                    Object ob[]={"posChar=posChar+"+posChar_,2};
                    strings.add(ob);
                }

                //INT=INT.isEmpty()?String.valueOf(posInt_):"posInt";
                //CHAR=CHAR.isEmpty()?String.valueOf(posChar_):"posChar";
                //DOUBLE=DOUBLE.isEmpty()?String.valueOf(posDoub_):"posDouble";
                INT=INT.isEmpty()?String.valueOf(posInt_):"posInt";
                CHAR=CHAR.isEmpty()?String.valueOf(posChar_):"posChar";
                DOUBLE=DOUBLE.isEmpty()?String.valueOf(posDoub_):"posDouble";
                SIZE_POS=celda.TIPO_VAR==0?DOUBLE:celda.TIPO_VAR==1?INT:CHAR;
                posINT=INT;
                posCHAR=CHAR;
                posDOUBLE=DOUBLE;
                int tmp[]={celda.TIPO_VAR==0?posDoub_:celda.TIPO_VAR==1?posInt_:posChar_,celda.TIPO_VAR,posChar_,posInt_,posDoub_,i};
                return tmp;
            }
            if(canADD){
                Object obj[]=new Object[2];
                switch (celda.TIPO_VAR){
                    case 0:
                        if(!celda.IS_ARRAY){
                            posDoub_++;
                        }else{
                            if(DOUBLE.isEmpty()){
                                String tmp=add(celda.sizePOS,inicio,0,strings,0);
                                DOUBLE="posDouble="+posDoub_+"+"+tmp;
                            }else{
                                DOUBLE="posDouble=posDouble+"+posDoub_;
                                Object ob[]=new Object[2];
                                ob[0]=DOUBLE;
                                ob[1]=0;
                                strings.add(ob);
                                String tmp=add(celda.sizePOS,inicio,0,strings,0);
                                DOUBLE="posDouble=posDouble+"+tmp;
                            }
                            obj[0]=DOUBLE;
                            obj[1]=0;
                            strings.add(obj);
                            posDoub_=0;
                        }
                        break;
                    case 1:
                        if(!celda.IS_ARRAY){
                            posInt_++;

                        }else{
                            posInt_++;
                            if(INT.isEmpty()){
                                String tmp=add(celda.sizePOS,inicio,1,strings,1);
                                INT="posInt="+posInt_+" + "+tmp;
                            }else{
                                INT="posInt=posInt+"+posInt_;

                                Object ob[]=new Object[2];
                                ob[0]=INT;
                                ob[1]=1;
                                strings.add(ob);
                                String tmp=add(celda.sizePOS,inicio,1,strings,1);
                                INT="posInt=posInt+"+tmp;
                            }
                            //append(INT);
                            obj[0]=INT;
                            obj[1]=1;
                            strings.add(obj);
                            posInt_=0;
                        }


                        break;
                    case 2:
                        if(!celda.IS_ARRAY){
                            posChar_++;

                        }else{
                            if(CHAR.isEmpty()){
                                String tmp=add(celda.sizePOS,inicio,2,strings,2);
                                CHAR="posChar="+posChar_+"+"+tmp;
                            }else{
                                CHAR="posChar=posChar+"+posChar_;
                                Object ob[]=new Object[2];
                                ob[0]=CHAR;
                                ob[1]=2;
                                strings.add(ob);
                                String tmp=add(celda.sizePOS,inicio,2,strings,2);
                                CHAR="posChar=posChar+"+tmp;
                            }
                            //append(CHAR);
                            obj[0]=CHAR;
                            obj[1]=2;
                            strings.add(obj);
                            posChar_=0;
                        }
                        break;
                    case 10:
                    case 20:
                        canADD=false;
                        break;
                }
            }
        }
        return null;
    }


    public static celda get(int x){
        int posInt=0,posDoub=0,posChar=0;
        for (int i = 0; i <x ; i++) {
            celda celda=celdas.get(i);
            switch (celda.TIPO_VAR){
                case 0:
                    posDoub++;
                    break;
                case 1:
                    posInt++;
                    break;
                case 2:
                    posChar++;
                    break;
            }
        }

        celda tmp=celdas.get(x);
        tmp.sizePos=tmp.TIPO_VAR==0?posDoub:tmp.TIPO_VAR==1?posInt:posChar;
        return tmp;

    }
    public static   int types_metodo[];
    public static void crearTypes(String id){
        String [] jv=id.split("_");
        try
        {
            switch (jv[0]){
                case "VB":
                    types_metodo=new int[jv.length-2];
                    for (int i = 2; i < jv.length; i++) {
                        switch (jv[i]){
                            case "int":
                                types_metodo[i-2]=1;
                                break;
                            case "float":
                                types_metodo[i-2]=0;
                                break;
                            case "char":
                                types_metodo[i-2]=2;
                                break;
                        }
                    }
                    break;
                case "PY":
                    break;
            }
        }catch (Exception ex){

        }

    }


    public static void append(String str)
    {

        generarCodigoIntermedio.codigoEjecutable.append(str+";\n");
        generarCodigoIntermedio.codigoFinal.append(str).append(";\n");
    }
    public static String getType(int x,String hrf,String var){
        boolean isHEAP;
        isHEAP=var.startsWith("this.");
        String tmp=x==0 ?hrf+"Double":x==1?hrf+"Int":hrf+"Char";
        if(isHEAP){
            tmp+="HEAP";
        }
        return tmp;
    }
    public static String getStack(int x,String argumento){
        boolean isHEAP;
        isHEAP=argumento.startsWith("this.");
        String var= x==0 ?"stack_Double":x==1?"stack_Int":"stack_Char";
        if(isHEAP){
            var+="HEAP";
        }

        return var;
    }
    public static String add(String sizePos, int i,int tipo,ArrayList<Object[]>strings,int typo){
        int values[]=obtenerID(sizePos,i,typo);
        if(values!=null){
            String tmp="op_4="+getType(values[1],"puntero",sizePos)+"+"+SymTable.SIZE_POS;
            Object[] ob =new Object[2];
            ob[0]=tmp;
            ob[1]=tipo;
            strings.add(ob);
            tmp=getType(values[1],"valTmp4_","");
            Object [] obj3=new Object[2];
            obj3[0]=tmp+"="+getStack(values[1],sizePos)+"[op_4]";
            obj3[1]=tipo;
            strings.add(obj3);
            return tmp;
        }
        return sizePos;
    }

    public static int[] getParams(String string,int params){
        String []vector=string.split("_");
        int param[]=null;
        if(string.startsWith("PY_")){
            param=new int[Integer.parseInt(vector[2])+1];
        }else{
            param=new int[params+1];
            int cnt=0;
            try{
                for (int i = vector.length-params-1; i <vector.length ; i++) {
                    switch (vector[i]){
                        case "int":
                            param[cnt]=1;
                            break;
                        case "char":
                            param[cnt]=2;
                            break;
                        case "float":
                            param[cnt]=0;
                            break;
                        default:
                            cnt=cnt-1;
                            break;
                    }
                    cnt++;
                }
            }catch (Exception ex){}
        }
        return param;
    }

    public static int[] obtenerMe(String id, int inicio){

        for (int i = 0; i <celdas.size() ; i++) {
            celda celda=celdas.get(i);
            if(celda.ID.equals(id)) {
                int t[]={i,celda.TIPO_VAR};
                return t;
            }

        }
        return null;
    }

}
