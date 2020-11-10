
import CodigoEjecutable.CodigoIntermedio;
import CodigoEjecutable.generarCodigoIntermedio;
import Errores.ErrorClass;
import GramaticaC.ccup;
import GramaticaC.clex;
import GramaticaJAVA.javacup;
import GramaticaJAVA.javalex;
import GramaticaPython.pycup;
import GramaticaPython.pylex;
import GramaticaVisualBasic.vbcup;
import GramaticaVisualBasic.vblex;
import Interfaz.Editor;
import Interfaz.MainInterfaz;
import Lenguajes.Compilador;
import Lenguajes.MetodosVisual;
import OptimizarCodigoIntermedio.OptimizarCodigo;
import TablaSimbolos.SymTable;
import java_cup.runtime.Symbol;
import org.apache.tools.ant.taskdefs.condition.Http;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        //generarCompilador();
        //test();
        MainInterfaz interfaz=new MainInterfaz();
        interfaz.show();



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

        String texto = "";

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
        String texto =
                "%%VB\n" +
                        "Public Function Incremento(ByVal Valor As Integer) As Integer\n" +
                        "  Valor = Valor + 1\n" +
                        "  Valor = 2\n" +
                        "  Valor =3*Valor\n" +
                        "  Valor = Valor\n" +
                        "  Return Valor\n" +
                        "End Function\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "%%JAVA\n" +
                        "\n" +
                        "%%PY\n" +
                        "def Mensaje(tipo):\n" +
                        "    if tipo == 1:\n" +
                        "        print(\"Arreglo antes de ordenarse\")\n" +
                        "    elif tipo == 2:\n" +
                        "        print(\"Arreglo depues de ordenarse\")\n" +
                        "    else:\n" +
                        "        print(\"default\")\n" +
                        "\n" +
                        "def Mostrar(indice, valor):\n" +
                        "    print(\"arreglo[\", indice, \"] = \",valor)\n" +
                        "\n" +
                        "%%PROGRAMA\n" +
                        "/* ---------------------------------------------\n" +
                        "\tSeccion de Librerias\n" +
                        "   --------------------------------------------- */\n" +
                        "   #include \"VB\"\n" +
                        "   #include \"PY\"\n" +
                        "\n" +
                        "/* ---------------------------------------------\n" +
                        "    Declaracion de Constantes\n" +
                        "   --------------------------------------------- */\n" +
                        "\n" +
                        "    int tamano;\n" +
                        "\n" +
                        "/* ---------------------------------------------\n" +
                        "    Declaracion de Variables Globales\n" +
                        "   --------------------------------------------- */\n" +
                        "\n" +
                        "\n" +
                        "   int i;\n" +
                        "   int j;\n" +
                        "   int tmp;\n" +
                        "\n" +
                        "   void main ()\n" +
                        "   {\n" +
                        "        // Inicializar arreglo\n" +
                        "    tamano=10;\n" +
                        "    j=0;\n" +
                        "    int arreglo[tamano];\n" +
                        "\t\tarreglo[0]=7;\n" +
                        "\t\tarreglo[1]=14;\n" +
                        "\t\tarreglo[2]=18;\n" +
                        "\t\tarreglo[3]=19;\n" +
                        "\t\tarreglo[4]=21;\n" +
                        "\t\tarreglo[5]=2;\n" +
                        "\t\tarreglo[6]=9;\n" +
                        "\t\tarreglo[7]=24;\n" +
                        "\t\tarreglo[8]=29;\n" +
                        "\t\tarreglo[9]=47;\n" +
                        "\n" +
                        "\t\tPY.Mensaje(1);\n" +
                        "\t\tfor(i = 0; i < tamano; i = i + 1)\n" +
                        "\t    {\n" +
                        "\t\t\t//Pascal.Mostrar(i,arreglo[i]);\n" +
                        "      PY.Mostrar(i,arreglo[i]);\n" +
                        "\t    }\n" +
                        "\n" +
                        "\t\ti = 0;\n" +
                        "\n" +
                        "\t\t// Ordenar el arreglo\n" +
                        "\t\t// Inicio while 1\n" +
                        "\t\twhile(i < 10)\n" +
                        "\t\t{\n" +
                        "\t\t\t// Inicio while 2\n" +
                        "\t\t\twhile(j < 9)\n" +
                        "\t\t\t{\n" +
                        "\t\t\t\t// Inicio if\n" +
                        "\t\t\t\tif(arreglo[j] < arreglo[j+1])\n" +
                        "\t\t\t\t{\n" +
                        "                    tmp = arreglo[j];\n" +
                        "                    arreglo[j] = arreglo[j + 1];\n" +
                        "                    arreglo[j + 1] = tmp;\n" +
                        "\n" +
                        "\t\t\t\t} // fin if\n" +
                        "\n" +
                        "\t\t\t\t// contador 2\n" +
                        "\t\t\t\tj = VB.Incremento(j);\n" +
                        "\n" +
                        "\t\t\t} // fin while\n" +
                        "\n" +
                        "\t\t\t// contador 1\n" +
                        "\t\t\tj = 0;\n" +
                        "\t\t\ti = VB.Incremento(i);\n" +
                        "\n" +
                        "\t\t} // fin while\n" +
                        "\n" +
                        "\n" +
                        "\t   PY.Mensaje(2);\n" +
                        "\n" +
                        "\t   // Mostrar el arreglo ordenado\n" +
                        "\n" +
                        "\t   for(i = 0; i < tamano; i = i + 1)\n" +
                        "\t   {\n" +
                        "\t\t\tPY.Mostrar(i,arreglo[i]);\n" +
                        "\t   }\n" +
                        "\t   getch();\n" +
                        "   }";
        Compilador compilador = new Compilador(texto);
        compilador.create();
        compilador.compilar_vb();
        //compilador.compilar_java();
        //compilador.compilar_pyva();
        //compilador.compilar_c();
        //SymTable.fix();
        OptimizarCodigo optimizarCodigo=new OptimizarCodigo();
        optimizarCodigo.optimiar();
        optimizarCodigo.print();
        //optimizarCodigo.print();
        //compilador.printCodOP();
        //SymTable.print2();
       // generarCodigoIntermedio codigoIntermedio = new generarCodigoIntermedio();
        //compilador.printCod();
       //codigoIntermedio.generar(MetodosVisual.instrucciones);

       //Editor.compilarCodigoC(generarCodigoIntermedio.codigoFinal);
       // compilador.printError();
    }

}
