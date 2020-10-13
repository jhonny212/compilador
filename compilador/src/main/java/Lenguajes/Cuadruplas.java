package Lenguajes;

public class Cuadruplas {
    public String OP,ARG1,ARG2,RESULT;
    public final int TIPO;
    public String KIND;

    public Cuadruplas(String OP, String ARG1, String ARG2,
                      String RESULT, int TIPO) {
        this.OP = OP;
        this.ARG1 = ARG1;
        this.ARG2 = ARG2;
        this.RESULT = RESULT;
        this.TIPO = TIPO;
        KIND="";
    }

    public Cuadruplas(String OP, String ARG1, String ARG2, String RESULT, int TIPO, String KIND) {
        this.OP = OP;
        this.ARG1 = ARG1;
        this.ARG2 = ARG2;
        this.RESULT = RESULT;
        this.TIPO = TIPO;
        switch (KIND){
            case "e":
                this.KIND = "int";
                break;
            case "r":
                this.KIND = "float";
                break;
            case "c":
                this.KIND = "char";
                break;
        }

    }



    public String print(){
        String texto="{\n" +
                "  Op:   "+this.OP+"\n" +
                "  Res:  "+this.RESULT+"\n" +
                "  Arg1: "+this.ARG1+"\n" +
                "  Arg2: "+this.ARG2+"\n" +
                "}";
        return texto;
    }
    public String PRINT(){
        switch (TIPO){
            case 0:
                return RESULT+"="+ARG1+OP+ARG2+";";
            case 1:
                return RESULT+"="+ARG1+";";
            case 2:
                return RESULT+"="+ARG1 +";";
            case 3:
                return "if("+ARG1+OP+ARG2+")"+RESULT+";";
            case 4:
                return OP+" "+ARG1+ARG2 +";";
            case 5:
                return ARG1+ARG2+":";
            case 6:
                return RESULT+"="+ARG1+" "+OP+" "+ARG2+";";
            case 7:
                return  RESULT+"="+ARG1+"["+ARG2+"];";
            case 8:
                return "void "+this.ARG1+this.ARG2+"() {";
            case 9:
                return "}";
            case 10:
                return  RESULT+"["+ARG1+"]="+ARG2+"";
            case 11:
                return RESULT+" "+ARG1;
            case 12:
                return RESULT.isEmpty()?"call "+ARG1+" "+ARG2: RESULT+"=call "+ARG1+" "+ARG2;
            case 13:
                return "Read "+RESULT;
            case 14:
                return "print "+ARG1+(ARG2.isEmpty()?"":" "+ARG2);
            case 15:
                return "class "+ARG1+"{";
            case 16:
                return "clear";
        }
        return "";
    }
    public int tabulaciones=0;

    public String getTabs(){
        String tabs="";
        if(this.TIPO==5){
            tabulaciones=1;
        }else if(this.TIPO==15 || this.TIPO==9){
            tabulaciones=0;
        }else{
            this.tabulaciones=2;
        }

        for (int i = 0; i < tabulaciones; i++) {
            tabs+="   ";
        }
        return tabs;
    }
}