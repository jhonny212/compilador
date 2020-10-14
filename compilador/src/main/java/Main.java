
import GramaticaC.ccup;
import GramaticaC.clex;
import GramaticaJAVA.javacup;
import GramaticaJAVA.javalex;
import GramaticaPython.pycup;
import GramaticaPython.pylex;
import GramaticaVisualBasic.vbcup;
import GramaticaVisualBasic.vblex;
import Interfaz.MainInterfaz;
import Lenguajes.Compilador;
import Lenguajes.MetodosVisual;
import java_cup.runtime.Symbol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        //generarCompilador();
        test();
        //MainInterfaz main=new MainInterfaz();
        //main.show();
    }

    private static void generarCompilador() {
        try {
            String ruta = "src/main/java/GramaticaC/"; //ruta donde tenemos los archivos con extension .jflex y .cup
            String opcFlex[] = {ruta + "clex.jflex", "-d", ruta};
            jflex.Main.generate(opcFlex);
            String opcCUP[] = {"-destdir", ruta, "-parser", "ccup", ruta + "ccup.cup"};
            //java_cup.Main.main(opcCUP);
        } catch (Exception ex) {
        }

    }

    public static void probar() {

        String texto = "%%VB\n" +
                " //hola mundo\n\n"
                + "Public Sub Suma()\n"
                + "    \n"
                + "End Sub\n"
                + "Public Sub Res()\n"
                + "End Sub\n"
                + "%%JAVA\n"
                + "public class test1{\n"
                + "    public void suma(){\n"
                + "        int x=10;\n"
                + "    }\n"
                + "    public void suma(){\n"
                + "        int y=10;\n"
                + "    }\n"
                + "}\n"
                + "public class test12{\n"
                + "    int x=10;\n"
                + "    int y=20;\n"
                + "    public void suma(){\n"
                + "        int x=10;\n"
                + "        this.x=20;\n"
                + "    }\n"
                + "}\n"
                + "%%PY\n"
                + "def suma():\n"
                + "def suma():\n"
                + "%%\n"
                + "#include \"PY\"\n"
                + "#include \"VB\"";

        var lex = new vblex(new BufferedReader(new StringReader(texto)));

        var parser = new vbcup(lex);
        parser.errores = lex.errores;
        try {
            parser.parse();
        } catch (Exception e) {
            System.out.println("Errorxdxdxd");
        }
        if (!parser.errores.haveErrors()) {
            parser.errores.erroresSemanticos();
            parser.errores.erroresSintacticos();
        }

        MetodosVisual.print();
    }

    public static void test() {
        String texto ="%%VB\n" +
                "  //xxxxxxxxxxxx\n" +
                "Public Sub suma (ByVal x As Integer,ByVal yy As Integer)\n" +
                "    Dim y=x*2 As Integer\n" +
                "    While x*y <= 10\n" +
                "        If ( 0 Or Not(1) ) Then\n" +
                "            x=intinput(\"Escriba su edad\")\n" +
                "            Console.WriteLine(\"La edad es de \" & x)\n" +
                "            Do\n" +
                "                x=x+1\n" +
                "            Loop While 2*x>1*x\n" +
                "        End If\n" +
                "    End While\n" +
                "End Sub\n" +
                "Public Sub resta (ByVal z As Double,ByVal y As Integer)\n" +
                "    Dim x=y+2 As Integer\n" +
                "    For w As Integer=10 To 20\n" +
                "        If x = 1 Then\n" +
                "            Console.Write(\"Caso 1\")\n" +
                "        ElseIf x=2 Then\n" +
                "            Console.Write(\"Caso 2\")\n" +
                "        ElseIf x=3 Then\n" +
                "            Console.WriteLine(\"Caso 3\")\n" +
                "        Else\n" +
                "            Console.Write(\"caso 4\")\n" +
                "        End If        \n" +
                "    Next\n" +
                "    Select Case x\n" +
                "        Case 1\n" +
                "            x=10*x\n" +
                "        Case 2\n" +
                "            x=10/x\n" +
                "        Case 3\n" +
                "            x=10+x\n" +
                "        Case 4\n" +
                "            x=10%x\n" +
                "        Case Else\n" +
                "            x=x\n" +
                "    End Select\n" +
                "End Sub\n" +
                "%%JAVA\n" +
                "   //sdaaaaaaaaaaa\n" +
                " public class aritmetica{\n" +
                "     public aritmetica(){\n" +
                "\n" +
                "     }\n" +
                "     public int suma(int x,int y){\n" +
                "                 for(int i=0;i<10;i=i+1){\n" +
                "                   int k=10;" +
                "                 }\n" +
                "         return 0;\n" +
                "     }\n" +
                " }\n" +
                "%%PY\n" +
                "def suma():\n" +
                "    if (not(0 or 0) and 1):\n" +
                "        print(\"Hola mundo\")\n" +
                "    while 0*1:\n" +
                "        print(\"xd\")\n" +
                "        for x in range(0,10,1):\n" +
                "            print(\"is a for\")\n" +
                "%%PROGRAMA\n" +
                "#include \"PY\"\n" +
                "#include \"VB\"\n" +
                "#include \"JAVA.*\"\n" +
                "\n" +
                "void main(){\n" +
                "   //xxxxxxx\n" +
                "    JAVA.aritmetica arit();\n" +
                "    int suma=PY.suma()*10;\n" +
                "    int resta=JAVA.aritmetica.suma(2*suma,3)+suma;\n" +
                "\n" +
                "}\n";
        Compilador compilador = new Compilador(texto);
        compilador.create();
        compilador.compilar_vb();
        compilador.compilar_java();
        compilador.compilar_pyva();
        compilador.compilar_c();
        compilador.printError();
        //compilador.printCod();
    }

}
