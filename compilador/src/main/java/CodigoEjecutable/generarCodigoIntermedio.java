package CodigoEjecutable;

import Lenguajes.Cuadruplas;

import TablaSimbolos.SymTable;

import java.util.ArrayList;

public class generarCodigoIntermedio {
    public static StringBuffer codigoFinal,codigoEjecutable,codigoMostrar;
    public static ArrayList<Cuadruplas> instrucciones;
    public static int posCLASE=0,posEXTRACLASE=0;
    public void generar(ArrayList<Cuadruplas> cua){
        instrucciones=new ArrayList<>();
        SymTable.celdas.get(2);
        cua.forEach((x)->{
            instrucciones.add((Cuadruplas) x.clone());
        });
        codigoFinal=new StringBuffer();
        codigoEjecutable=new StringBuffer();
        codigoMostrar=new StringBuffer();
        codigoFinal.append("#include <stdio.h>\n" +
                "#include <stdlib.h>\n");

        String identifier="",strop1="",strop2="",integer_="",double_="",char_="";
        int inttmp=0,floattmp=0,chartmp=0;
        int inicio_metodo=-1;
        int tipo_metodo=0,numParam=0;
        int [] values;
        int[] datos_extra=null;
        int[] parametros=null;

        int tmpM;
        codigoFinal.append(globales);
        for (int i = 0; i < instrucciones.size(); i++)
        {
            Cuadruplas x=instrucciones.get(i);
            if(!x.canAdd){
                continue;
            }
            switch (x.TIPO){

                case 15:
                    values=SymTable.getID(x.ARG1,-1);
                    posCLASE=values[2];
                    for (int j = 0; j < SymTable.celdas.size(); j++) {
                        SymTable.celda cl=SymTable.celdas.get(j);
                        if(cl.ID.contains(x.ARG1) && cl.TIPO_VAR==20){
                            posEXTRACLASE=cl.POS_MEMORIA;
                        }
                    }
                    break;
                case 18:

                    inicio_metodo=-1;
                    append(x.PRINT());
                    codigoFinal.append(TEMPORALES);
                    break;
                case 9:
                    if(i==instrucciones.size()-1){
                        append("return 0; }");
                    }else {
                        append(x.PRINT());
                    }
                    break;
                case 4:
                    append(x.PRINT());
                    break;
                case 13:
                    if(!x.RESULT.isEmpty()){
                        values=SymTable.getID(x.RESULT,inicio_metodo);
                        if(values!=null){
                            append("p1="+getType(values[1],"puntero",x.RESULT)+" + "+SymTable.SIZE_POS);
                            switch (values[1]){
                                case 0:
                                    append("scanf(\"%f\",&"+getStack(0,x.RESULT)+"[p1])");
                                    break;
                                case 1:
                                    append("scanf(\"%d\",&"+getStack(1,x.RESULT)+"[p1])");
                                    break;
                                case 2:
                                    append("scanf(\"%c\",&"+getStack(2,x.RESULT)+"[p1])");
                                    break;
                            }
                        }
                    }

                    break;
                case 21:
                    if(!x.RESULT.isEmpty()){
                        values=SymTable.getID(x.RESULT,inicio_metodo);
                        if(values!=null){
                            append("p1="+getType(values[1],"puntero",x.RESULT)+" + "+SymTable.SIZE_POS);
                            append("scanf(\"%d\",&"+getStack(1,x.RESULT)+"[p1])");
                        }else {
                            append("scanf(\"%d\",&getch)");
                        }
                    }else{
                        append("scanf(\"%d\",&getch)");
                    }
                    break;
                case 6:

                case 14:
                    values=SymTable.getID(x.ARG1,inicio_metodo);
                    if(values!=null){
                        append("p1="+getType(values[1],"puntero",x.ARG1)+" + "+SymTable.SIZE_POS);
                        switch (values[1]){
                            case 0:
                                append("printf(\"%f\","+getStack(0,x.ARG1)+"[p1])");
                                break;
                            case 1:
                                append("printf(\"%d\","+getStack(1,x.ARG1)+"[p1])");
                                break;
                            case 2:
                                append("printf(\"%c\","+getStack(2,x.ARG1)+"[p1])");
                                break;
                        }
                    }else{
                        if(x.ARG1.contains("\"")){
                            append("printf("+x.ARG1+")");
                        }else{
                            if(x.ARG1.contains("char")
                                    || x.ARG1.contains("Char")
                                    || x.ARG1.contains("CHAR")){
                                append("printf(\"%c\","+x.ARG1+")");
                            }else if(
                                    x.ARG1.contains("float")
                                    || x.ARG1.contains("Float")
                                    || x.ARG1.contains("FLOAT")
                                    || x.ARG1.contains("double")
                                    || x.ARG1.contains("Double")
                                    || x.ARG1.contains("DOUBLE")

                            ){
                                append("printf(\"%f\","+x.ARG1+")");
                            }else if(
                                            x.ARG1.contains("int")
                                            || x.ARG1.contains("Int")
                                            || x.ARG1.contains("INT")

                            ){
                                append("printf(\"%d\","+x.ARG1+")");
                            }else{
                                append("gcvt("+x.ARG1+",10,string_asig);");
                                append("printf(\"%s\",string_asig)");
                            }

                        }

                    }
                    break;

                case 8:
                    identifier=x.ARG1+x.ARG2;
                    append("void "+identifier+"(){");
                    codigoFinal.append(TEMPORALES);
                    int[] datos=SymTable.obtenerMe(identifier,inicio_metodo);
                    inicio_metodo=datos[0];
                    tipo_metodo=datos[1];
                    break;
                case 1:

                case 2:
                    if(x.RESULT.equals(x.ARG1)){
                        continue;
                    }
                    values=SymTable.getID(x.RESULT,inicio_metodo);
                        if(values!=null){
                            append("p1="+getType(values[1],"puntero",x.RESULT)+" + "+SymTable.SIZE_POS);
                            int vals[]=SymTable.getID(x.ARG1,inicio_metodo);
                            //String HEAP=x.RESULT.startsWith("this.")?"HEAP":"";
                            if(vals==null){
                                append(getStack(values[1],x.RESULT)+"[p1]="+x.ARG1);
                            }else{
                                append("p2="+getType(vals[1],"puntero",x.ARG1)+" + "+SymTable.SIZE_POS);
                                strop2=getType(values[1],"valTmp2_","");
                                append(strop2+"="+getStack(values[1],x.ARG1)+"[p2]");
                                append(getStack(values[1],x.RESULT)+"[p1]="+strop2);
                            }
                        }else{
                            int vals[]=SymTable.getID(x.ARG1,inicio_metodo);
                            if(vals==null){
                                append(x.RESULT+"="+x.ARG1);
                            }else{
                                append("p2="+getType(vals[1],"puntero",x.ARG1)+" + "+SymTable.SIZE_POS);
                                strop2=getType(vals[1],"valTmp1_","");
                                append(strop2+"="+getStack(vals[1],x.ARG1)+"[p2]");
                                append(x.RESULT+"="+strop2);
                            }
                        }
                  break;
                case 3:
                    if(x.OP.isEmpty()){
                        values=SymTable.getID(x.ARG1,inicio_metodo);
                        if(values==null){
                            append(x.PRINT());
                        }else{
                            append("p1="+getType(values[1],"puntero",x.ARG1)+" + "+SymTable.SIZE_POS);
                            strop2=getType(values[1],"valTmp2_","");
                            append(strop2+"="+getStack(values[1],x.ARG1)+"[p1]");
                            append("if ( "+strop2+" ) "+x.RESULT);
                        }
                    }else{
                        values=SymTable.getID(x.ARG1,inicio_metodo);
                        if(values==null){
                            strop1=x.ARG1;
                        }else{
                            append("p1="+getType(values[1],"puntero",x.ARG1)+" + "+SymTable.SIZE_POS);
                            strop1=getType(values[1],"valTmp2_","");
                            append(strop1+"="+getStack(values[1],x.ARG1)+"[p1]");
                        }
                        values=SymTable.getID(x.ARG2,inicio_metodo);
                        if(values==null){
                            strop2=x.ARG2;
                        }else{
                            append("p1="+getType(values[1],"puntero",x.ARG2)+" + "+SymTable.SIZE_POS);
                            strop2=getType(values[1],"valTmp1_","");
                            append(strop2+"="+getStack(values[1],x.ARG2)+"[p1]");
                        }
                        append("if ( "+strop1+" "+x.OP+" "+strop2+" ) "+x.RESULT);

                    }
                    break;
                case 5:
                    append(x.PRINT());
                    break;
                case 0:
                    values=SymTable.getID(x.ARG1,inicio_metodo);
                    if(values!=null){
                        append("op_1="+getType(values[1],"puntero",x.ARG1)+"+"+SymTable.SIZE_POS);
                        strop1=getType(values[1],"valTmp1_","");
                        append(strop1+"="+getStack(values[1],x.ARG1)+"[op_1]");

                    }else {
                        strop1=x.ARG1;
                    }
                    values=SymTable.getID(x.ARG2,inicio_metodo);
                    if(values!=null){
                        append("op_2="+getType(values[1],"puntero",x.ARG2)+"+"+SymTable.SIZE_POS);
                        strop2=getType(values[1],"valTmp2_","");
                        append(strop2+"="+getStack(values[1],x.ARG2)+"[op_2]");

                    }else{
                        strop2=x.ARG2;
                    }
                    values=SymTable.getID(x.RESULT,inicio_metodo);
                    if(values!=null){
                        append("op_3="+getType(values[1],"puntero",x.RESULT)+"+"+SymTable.SIZE_POS);
                        String tmp=getType(values[1],"valTmp1_","");
                        strop1=tmp+"="+strop1+x.OP+strop2;
                        append(strop1);
                        append(getStack(values[1],x.RESULT)+"[op_3]="+tmp);
                    }else{
                        append(x.RESULT+"="+strop1+x.OP+strop2);
                    }
                    break;
                case 17:
                    values=SymTable.getID(x.ARG1,inicio_metodo);
                    if(values!=null){
                        String strReturn=getType(values[1],"valTmp1_","");
                        append("op_1="+getType(values[1],"puntero",x.ARG1)+"+"+SymTable.SIZE_POS);
                        append(strReturn+"="+getStack(tipo_metodo,x.ARG1)+"[op_1]");
                        append("op_1="+getType(tipo_metodo,"puntero","")+"-1");

                        append(getStack(tipo_metodo,"")+"[op_1]="+strReturn);
                    }else{
                        append("op_1="+getType(tipo_metodo,"puntero",x.ARG1)+"-1");
                        append(getStack(tipo_metodo,"")+"[op_1]="+x.ARG1);
                    }
                    break;
                case 11:
                    if (datos_extra == null) {
                        for (int j = i; j < instrucciones.size() ; j++) {
                            Cuadruplas cuadrupla=instrucciones.get(j);
                            if(cuadrupla.TIPO==12){
                                tmpM=cuadrupla.ARG2.isEmpty()?0:Integer.parseInt(cuadrupla.ARG2);
                                identifier=cuadrupla.ARG1;
                                datos_extra =SymTable.getMethod(identifier,inicio_metodo);
                                integer_=SymTable.posINT;
                                double_=SymTable.posDOUBLE;
                                char_=SymTable.posCHAR;
                                append("punteroTMPInt="+SymTable.posINT);
                                append("punteroTMPDouble="+SymTable.posDOUBLE);
                                append("punteroTMPChar="+SymTable.posCHAR);

                                parametros =SymTable.getParams(identifier,tmpM);

                                break;
                            }
                        }
                    }
                    values=SymTable.getID(x.ARG1,inicio_metodo);
                    int param=parametros[numParam];
                    switch (parametros[numParam]){
                        case 0:
                            floattmp++;
                            break;
                        case 1:
                            inttmp++;
                            break;
                        case 2:
                            chartmp++;
                            break;
                    }
                    if(values==null){
                        append("op_3="+getType(param,"punteroTMP","")+"+"+(param==0?floattmp:param==1?inttmp:chartmp));
                        append("op_3=op_3+"+(getType(param,"puntero",x.ARG1)));
                        append(getStack(param,"")+"[op_3]="+x.ARG1);
                    }else{
                        append("op_1="+getType(values[1],"puntero",x.ARG1)+"+"+SymTable.SIZE_POS);

                        strop1=getType(values[1],"valTmp1_","");
                        append(strop1+"="+getStack(values[1],x.ARG1)+"[op_1]");
                        append("op_1="+getType(param,"punteroTMP","")+"+"+(param==0?floattmp:param==1?inttmp:chartmp));
                        append("op_1=op_1+"+(getType(param,"puntero","")));
                        append(getStack(param,"")+"[op_1]="+strop1);
                    }

                    numParam++;
                    break;
                case 12:
                    if(numParam==0){
                        identifier=x.ARG1;
                        datos_extra =SymTable.getMethod(identifier,inicio_metodo);
                        integer_=SymTable.posINT;
                        double_=SymTable.posDOUBLE;
                        char_=SymTable.posCHAR;
                        append("punteroTMPInt="+SymTable.posINT);
                        append("punteroTMPDouble="+SymTable.posDOUBLE);
                        append("punteroTMPChar="+SymTable.posCHAR);
                    }
                    //if(datos_extra!=null){

                    //}

                    if(x.ARG1.startsWith("JAVA_") && !x.ARG1.contains("constru") && inicio_metodo==-1 ){
                       SymTable.getID(x.EXTRA+"_INTEGER",inicio_metodo);
                       String temporal=(getStack(1,"")+"["+SymTable.SIZE_POS+"]");
                       append("punteroIntHEAP="+temporal);
                       SymTable.getID(x.EXTRA+"_CHAR",inicio_metodo);
                       temporal= (getStack(2,"")+"["+SymTable.SIZE_POS+"]");
                       append("punteroCharHEAP="+temporal);
                       SymTable.getID(x.EXTRA+"_DOUBLE",inicio_metodo);
                       temporal= (getStack(0,"")+"["+SymTable.SIZE_POS+"]");
                       append("punteroDoubleHEAP="+temporal);


                    }else if(x.ARG1.startsWith("JAVA_") && x.ARG1.contains("constru") && inicio_metodo==-1){
                        append("punteroIntHEAP=sizeINTHEAP");
                        append("punteroCharHEAP=sizeCHARHEAP");
                        append("punteroDoubleHEAP=sizeDOUBLEHEAP");


                    }
                    append("punteroInt=punteroTMPInt+punteroInt");
                    append("punteroDouble=punteroTMPDouble+punteroDouble");
                    append("punteroChar=punteroTMPChar+punteroChar");
                    append("punteroInt=1+punteroInt");
                    append("punteroDouble=1+punteroDouble");
                    append("punteroChar=1+punteroChar");

                    append(""+x.ARG1+"()");
                    //if(datos_extra!=null){
                        append("punteroInt=punteroInt-punteroTMPInt");
                        append("punteroDouble=punteroDouble-punteroTMPDouble");
                        append("punteroChar=punteroChar-punteroTMPChar");
                        append("punteroInt=punteroInt-1");
                        append("punteroDouble=punteroDouble-1");
                        append("punteroChar=punteroChar-1");

                    //}
                    if(!x.RESULT.isEmpty() && datos_extra!=null){
                        String href=datos_extra[1]==0?double_:datos_extra[1]==1?integer_:char_;
                        append("op_1="+getType(datos_extra[1],"puntero",x.RESULT)+"+"+href);
                        values=SymTable.getID(x.RESULT,inicio_metodo);
                        if(x.ARG1.contains("JAVA_")){
                            //append("op_1=op_1+1");
                        }
                        //Cuadruplas cuadrupla=instrucciones.get(i+1);
                        if(values!=null){
                            if( x.ARG1.startsWith("JAVA_constru") && inicio_metodo==-1){
                                //SymTable.celda celda=SymTable.celdas.get(posCLASE);

                                SymTable.getID(x.RESULT+"_INTEGER",inicio_metodo);
                                append(getStack(1,"")+"["+SymTable.SIZE_POS+"]"+"=sizeINTHEAP");
                                append("sizeINTHEAP=sizeINTHEAP+"+posEXTRACLASE);

                                SymTable.getID(x.RESULT+"_CHAR",inicio_metodo);
                                append(getStack(2,"")+"["+SymTable.SIZE_POS+"]"+"=sizeCHARHEAP");
                                append("sizeCHARHEAP=sizeCHARHEAP+"+posEXTRACLASE);

                                SymTable.getID(x.RESULT+"_DOUBLE",inicio_metodo);
                                append(getStack(0,"")+"["+SymTable.SIZE_POS+"]"+"=sizeDOUBLEHEAP");
                                append("sizeDOUBLEHEAP=sizeDOUBLEHEAP+"+posEXTRACLASE);
                            }else{
                                //append("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                                append(x.RESULT+"="+getStack(datos_extra[1],"")+"[op_1]");

                            }
                        }else {
                            append(x.RESULT+"="+getStack(datos_extra[1],"")+"[op_1]");
                        }
                        /*else{
                            if(cuadrupla.ARG1.startsWith("call_fun")){
                                x.RESULT+=getType(datos_extra[1],"_","");
                                cuadrupla.ARG1=x.RESULT;
                            }
                            if(cuadrupla.ARG2.startsWith("call_fun") && cuadrupla.TIPO==0){
                                cuadrupla.ARG2=x.RESULT+"_2"+getType(datos_extra[1],"_","");
                                x.RESULT+="_2";
                                x.RESULT=cuadrupla.ARG2;
                            }
                            append(x.RESULT+"="+getStack(datos_extra[1],"")+"[op_1]");
                        }*/

                    }
                    datos_extra=null;
                    parametros=null;
                    numParam=0;
                    inttmp=0;
                    floattmp=0;
                    chartmp=0;

                    break;
                case 7:
                    //arg1
                    values=SymTable.getID(x.ARG2,inicio_metodo);
                    if(values!=null){
                        append("op_1="+getType(values[1],"puntero",x.ARG2)+"+"+SymTable.SIZE_POS);
                        strop1=getType(values[1],"valTmp1_","");
                        append(strop1+"="+getStack(values[1], x.ARG2)+"[op_1]");

                    }else {
                        strop1=x.ARG2;
                    }
                    //result
                    values=SymTable.getID(x.ARG1,inicio_metodo);
                    if(values!=null){
                        String result="op_3="+getType(values[1],"puntero",x.ARG1)+"+"+SymTable.SIZE_POS;
                        append(result);
                        result="op_3=op_3+"+strop1;
                        append(result);
                        result="op_3=op_3+1";
                        append(result);
                        strop2=getType(values[1],"valTmp2_","");
                        append(strop2+"="+getStack(values[1],x.ARG1)+"[op_3]");
                    }

                    values=SymTable.getID(x.RESULT,inicio_metodo);
                    if(values!=null){
                        append("op_2="+getType(values[1],"puntero",x.RESULT)+"+"+SymTable.SIZE_POS);
                        append(getStack(values[1],x.RESULT)+"[op_2]="+strop2);

                    }else{
                        append(x.RESULT+"="+strop2);
                    }


                    break;
                case 16:
                    append("system(\"clear\");");
                    break;
                case 10:
                    values=SymTable.getID(x.ARG1,inicio_metodo);
                    if(values!=null){
                        append("op_1="+getType(values[1],"puntero",x.ARG1)+"+"+SymTable.SIZE_POS);
                        strop1=getType(values[1],"valTmp1_","");
                        append(strop1+"="+getStack(values[1],x.ARG1)+"[op_1]");

                    }else {
                        strop1=x.ARG1;
                    }
                    values=SymTable.getID(x.ARG2,inicio_metodo);
                    if(values!=null){
                        append("op_2="+getType(values[1],"puntero",x.ARG2)+"+"+SymTable.SIZE_POS);
                        strop2=getType(values[1],"valTmp2_","");
                        append(strop2+"="+getStack(values[1],x.ARG2)+"[op_2]");

                    }else{
                        strop2=x.ARG2;
                    }
                    values=SymTable.getID(x.RESULT,inicio_metodo);
                    if(values!=null){
                        String result="op_3="+getType(values[1],"puntero",x.RESULT)+"+"+SymTable.SIZE_POS;
                        append(result);
                        result="op_3=op_3+"+strop1;
                        append(result);
                        result="op_3=op_3+1";
                        append(result);
                        append(getStack(values[1],x.RESULT)+"[op_3]="+strop2);
                    }

                    break;

            }
        }

        //System.err.println(codigoFinal.toString());
    }


    public void append(String val){
        codigoEjecutable.append(val).append(";\n");
        codigoFinal.append(val).append(";\n");
    }
    String globales="\n" +
            " int stack_IntHEAP[100],stack_Int[100];\n" +
            " double stack_DoubleHEAP[100],stack_Double[100];\n" +
            " double stack_CharHEAP[100],stack_Char[100];\n" +
            "\n" +
            "\n" +
            " int punteroInt=0,punteroChar=0,punteroDouble=0;\n" +
            " int punteroIntHEAP=0,punteroCharHEAP=0,punteroDoubleHEAP=0;\n" +
            " int sizeINTHEAP=0,getch;\n" +
            " int sizeCHARHEAP=0;\n" +
            " int sizeDOUBLEHEAP=0;\n";
    String TEMPORALES=
            //"   int call_fun_Int_2_Int, call_fun_Int,call_fun_2_Int,call_fun_Int_Int,call_fun_2_Int_2_Int;\n" +
            //"   double call_fun_Double_2_Double, call_fun_Double,call_fun_2_Double,call_fun_Double_Double,call_fun_2_Double_2_Double;\n" +
            //"   double call_fun;\n" +
            //"   char call_fun_Char_2_Char,call_fun_Char,call_fun_2_Char,call_fun_Char_Char,call_fun_2_Char_2_Char;\n" +
            "   char string_asig[300];" +
            "   int call_fun_int,call_fun_2_int;"+
            "   char call_fun_char,call_fun_2_char;"+
            "   double call_fun_float,call_fun_2_float;"+
            "   int p1,op_1,op_2,op_3,op_4,p2;\n" +
            "   int valTmp1_Int,valTmp2_Int,valTmp4_Int;\n" +
            "   double valTmp1_Double,valTmp2_Double,valTmp4_Double;\n" +
            "   char valTmp1_Char,valTmp2_Char,valTmp4_Char;\n" +
            "   int int_num0vector,int_num1vector;\n" +
            "   double double_num0vector,double_num1vector;\n" +
            "   char char_num0vector,char_num1vector;\n" +
            "   int t_1,t_2,t_3,t_1_int;\n" +
            "   int t_1vector,t_2vector,t_3vector;\n" +
            "   double t_1Double,t_2Double,t_3Double;\n" +
            "   double t_1Doublevector,t_2Doublevector,t_3Doublevector,t_1_float;\n" +
            "   int int_num0,int_num1,int_num2,int_num3;\n" +
            "   int int_ex_num0,int_ex_num1,int_ex_num2;\n" +
            "   double double_ex_num0,double_ex_num1,double_ex_num2;\n" +
            "   char char_ex_num0,char_ex_num1,char_ex_num2,t_1_char;\n" +
            "   int punteroTMPInt;\n" +
            "   int punteroTMPChar;\n" +
            "   int punteroTMPDouble;\n" +
            "   int num_tmp_int;\n" +
            "   int posDouble,posInt,posChar;\n" +
            "   double float_num0,float_num1,float_num3,float_num2;\n" +
            "   double num_tmp_float,num_tmp_double;\n" +
            "   char num_tmp_char;\n" +
            "   char char_num0,char_num1,char_num3,char_num2;\n" ;
            //"   char string_tmp[200],string_tmp2[200],string_asig[200];\n";



    public String getType(int x,String hrf,String var){
       isHEAP=var.startsWith("this.");
       String tmp=x==0 ?hrf+"Double":x==1?hrf+"Int":hrf+"Char";
       if(isHEAP){
           tmp+="HEAP";
       }
       isHEAP=false;
       return tmp;
    }
    public String getStack(int x,String argumento){
        isHEAP=argumento.startsWith("this.");
        String var= x==0 ?"stack_Double":x==1?"stack_Int":"stack_Char";
        if(isHEAP){
            var+="HEAP";
        }
        isHEAP=false;
        return var;
    }
    public static boolean isHEAP=false;
    public boolean canAdd(String string){
        return string.startsWith("str");
    }

}
