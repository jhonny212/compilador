
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
        test();
        //MainInterfaz interfaz=new MainInterfaz();
        //interfaz.show();



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
                        "Public Function validar(ByVal valor As Integer) As Integer\n" +
                        "  Return valor-1\n" +
                        "End Function\n" +
                        "Public Function factorial(ByVal valor As Integer) As Integer\n" +
                        "  If valor==0 Then\n" +
                        "    Return 1\n" +
                        "  Else\n" +
                        "    Return valor*factorial(validar(valor))\n" +
                        "  End If\n" +
                        "End Function\n" +
                        "%%JAVA\n" +
                        "public class animal{\n" +
                        "  float promedioVida;" +
                        "  int x,y,z;" +
                        "  \n" +
                        "\n" +
                        "  public animal(float promedio){\n" +
                        "\n" +
                        "    promedioVida=promedio;\n" +
                        "  }\n" +
                        "  public float getPromedio(){\n" +
                        "    return promedioVida;\n" +
                        "  }\n" +
                        "\n" +
                        "}\n" +
                        "%%PY\n" +
                        "def mostrarSaludo():\n" +
                        "  print(\"PRESIONE 1 PARA CONTINUAR\")\n" +
                        "  PY saltoLinea()\n" +
                        "\n" +
                        "\n" +
                        "def saltoLinea():\n" +
                        "  print(\" \\n\")\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "%%PROGRAMA\n" +
                        "#include \"PY\"\n" +
                        "#include \"JAVA.*\"\n" +
                        "#include \"VB\"\n" +
                        "int valor;\n" +
                        "float valorFlotante;\n" +
                        "void main(){\n" +
                        "    JAVA.animal animal(10.5);\n" +
                        "    JAVA.animal animal2(20.5);\n" +
                        "    JAVA.animal animal3(40.5);\n" +
                        "\n" +
                        "    valorFlotante=animal.getPromedio();\n" +
                        "    printf(\"VALOR %f\",valorFlotante);\n" +
                        "\n" +
                        "}";
        Compilador compilador = new Compilador(texto);
        compilador.create();
        compilador.compilar_vb();
        compilador.compilar_java();
        compilador.compilar_pyva();
        compilador.compilar_c();
        SymTable.fix();
        //OptimizarCodigo optimizarCodigo=new OptimizarCodigo();
        //optimizarCodigo.optimiar();
        //optimizarCodigo.print();
        //compilador.printCodOP();
        //SymTable.print2();
        generarCodigoIntermedio codigoIntermedio = new generarCodigoIntermedio();
        //compilador.printCod();
       codigoIntermedio.generar(MetodosVisual.instrucciones);

       Editor.compilarCodigoC(generarCodigoIntermedio.codigoFinal);
        //compilador.printError();
    }

}
