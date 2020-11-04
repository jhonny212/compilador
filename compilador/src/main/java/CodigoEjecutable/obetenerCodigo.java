package CodigoEjecutable;

public class obetenerCodigo {
    public static void caso14(int values[],StringBuffer stringBuffer,String string,String arg){
        switch (values[1]){
            case 0:
                append("gcvt("+getStack(values[1],arg)+"[p1]"+", 6, "+string+")",stringBuffer);
                break;
            case 1:
                append("gcvt("+getStack(values[1],arg)+"[p1]"+",6,"+string+")",stringBuffer);
                break;
            case 2:
                append("strcpy("+string+",&"+getStack(values[1],arg)+"[p1])",stringBuffer);
                break;
        }
    }
    public static String getType(int x,String hrf){
        return x==0 ?hrf+"Double":x==1?hrf+"Int":hrf+"Char";
    }
    public static String getStack(int x,String argumento){
        boolean isHEAP=argumento.startsWith("this.");
        String var= x==0 ?"stack_Double":x==1?"stack_Int":"stack_Char";
        if(isHEAP){
            var+="HEAP";
        }
        isHEAP=false;
        return var;
    }
    public static void append(String val,StringBuffer stringBuffer){
       stringBuffer.append(val+";\n");
    }

}
