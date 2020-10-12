
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
            String ruta = "src/main/java/GramaticaPython/"; //ruta donde tenemos los archivos con extension .jflex y .cup
            String opcFlex[] = {ruta + "pylex.jflex", "-d", ruta};
            jflex.Main.generate(opcFlex);
            String opcCUP[] = {"-destdir", ruta, "-parser", "pycup", ruta + "pycup.cup"};
            java_cup.Main.main(opcCUP);
        } catch (Exception ex) {
        }

    }

    public static void probar() {

        String texto = "%%VB\n"
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
                "Public Sub Suma()\n" +
                "    Dim edad As Integer\n" +
                "    Dim year=2020 As Integer\n" +
                "    While 2\n" +
                "        Dim xx As Integer\n" +
                "    End While\n" +
                "    edad=intinput(\"Escriba su edad coño\")\n" +
                "    Console.WriteLine(\"La edad es de \" &edad)\n" +
                "    Console.Write(\"Come back son\")    \n" +
                "End Sub\n" +
                "%%JAVA\n" +
                "public class aritmetica{\n" +
                "    int suma=10;\n" +
                "    public aritmetica(int x,int y){\n" +
                "        this.suma=x+2;\n" +
                "    }\n" +
                "    public void sumar(int x,int y){\n" +
                "        int suma=5;\n" +
                "        this.suma=5*4;\n" +
                "        suma=intinput(\"Escriba un numero\");\n" +
                "    }\n" +
                "}\n" +
                "%%PY\n" +
                "def suma(x,y):\n" +
                "    print(\"hola\",x,\"jeje\")\n" +
                "    suma=intinput(\"Escriba algo\")\n" +
                "    while 2:\n" +
                "        suma=suma*2\n" +
                "    if 2:\n" +
                "        suma=2*suma\n" +
                "    elif 2:\n" +
                "        suma=2\n" +
                "    else:\n" +
                "        suma=2\n" +
                "def suma(x,y):\n" +
                "    print(2)\n" +
                "%%PROGRAMA\n" +
                "\n" +
                "void main(){\n" +
                "    int x=10;\n" +
                "}";
        Compilador compilador = new Compilador(texto);
        compilador.create();
        compilador.compilar_vb();
        compilador.compilar_java();
        compilador.compilar_pyva();
        compilador.compilar_c();
        compilador.printError();
        compilador.printCod();
    }

}
