
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
            String ruta = "src/main/java/GramaticaC/"; //ruta donde tenemos los archivos con extension .jflex y .cup
            String opcFlex[] = {ruta + "clex.jflex", "-d", ruta};
            jflex.Main.generate(opcFlex);
            String opcCUP[] = {"-destdir", ruta, "-parser", "javacup", ruta + "javacup.cup"};
            //java_cup.Main.main(opcCUP);
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
                        "Public Function Factorial(ByVal valor As Integer) As Integer\n" +
                        "  If valor==0 Then\n" +
                        "    \tReturn 1\n" +
                        "  Else\n" +
                        "    \tReturn valor*Factorial(valor-1)\n" +
                        "  End If\n" +
                        "End Function\n" +
                        "Public Sub pintarNota(ByVal valor As Integer)\n" +
                        "  Console.WriteLine(\"\\n La nota Es de \" & valor)\n" +
                        "End Sub\n" +
                        "%%JAVA\n" +
                        "public class alumno{\n" +
                        "  char codigo;\n" +
                        "  int  year;\n" +
                        "  float promedio;\n" +
                        "  int curso1,curso2,curso3;\n" +
                        "\n" +
                        "  public alumno(int curso1,int curso2,int curso3){\n" +
                        "    this.curso1=this.curso1+this.curso2+curso1;\n" +
                        "    this.curso2=curso2;\n" +
                        "    this.curso3=curso3;" +
                        "    if(this.curso1){}switch(this.curso1){case 0:break;} while(this.curso1){}\n" +
                        "  }\n" +
                        "\n" +
                        "  public float getPromedio(){\n" +
                        "    return promedio;\n" +
                        "  }\n" +
                        "\n" +
                        "  public void calcularPromedio(int division){\n" +
                        "    promedio=(curso1+curso2+curso3)/3;\n" +
                        "  }\n" +
                        "\n" +
                        "  public void pintarPromedio(){\n" +
                        "    System.out.println(\"EL PROMEDIO ES DE \"+promedio);\n" +
                        "  }\n" +
                        "\n" +
                        "  public void actualizarPromedio(int tipo, int valor){\n" +
                        "    switch(tipo){\n" +
                        "      case 1:\n" +
                        "        curso1=valor;\n" +
                        "      break;\n" +
                        "      case 2:\n" +
                        "        curso2=valor;\n" +
                        "      break;\n" +
                        "      case 3:\n" +
                        "        curso3=valor;\n" +
                        "      break;\n" +
                        "    }\n" +
                        "\n" +
                        "  }\n" +
                        "}\n" +
                        "%%PY\n" +
                        "def validarCurso(promedio):\n" +
                        "  print(\"EVALUANDO NOTA: \",promedio)\n" +
                        "  if promedio<50:\n" +
                        "    print(\" REPROBADO \\n\")\n" +
                        "  else:\n" +
                        "    print(\" APROBADO NOTA \\n\")\n" +
                        "\n" +
                        "%%PROGRAMA\n" +
                        "#include \"PY\"\n" +
                        "#include \"VB\"\n" +
                        "#include \"JAVA.*\"\n" +
                        "const int variablePrueba=10;" +
                        "\n" +
                        "void main(){\n" +
                        "  int curso1;\n" +
                        "  int curso2;\n" +
                        "  int curso3;\n" +
                        "\tint factorial;\n" +
                        "  int booleano=0;\n" +
                        "\n" +
                        "  scanf(\"Ingrese la nota del curso1 \\n %d\", &curso1);\n" +
                        "  scanf(\"Ingrese la nota del curso2 \\n %d\", &curso2);\n" +
                        "  scanf(\"Ingrese la nota del curso3 \\n %d\", &curso3);\n" +
                        "\n" +
                        "  JAVA.alumno estudiante(curso1,curso2,curso3);\n" +
                        "  estudiante.calcularPromedio(3);\n" +
                        "  estudiante.pintarPromedio();\n" +
                        "\n" +
                        "  VB.pintarNota(curso1);\n" +
                        "  VB.pintarNota(curso2);\n" +
                        "  VB.pintarNota(curso3);\n" +
                        "  PY.validarCurso(estudiante.getPromedio());\n" +
                        "\tfactorial=VB.Factorial(5);\n" +
                        "\tprintf(\"\\n VALOR: %d\",factorial);\n" +
                        "  estudiante.actualizarPromedio(1,60);\n" +
                        "  estudiante.actualizarPromedio(2,60);\n" +
                        "  estudiante.actualizarPromedio(3,60);\n" +
                        "  getch();\n" +
                        "  estudiante.calcularPromedio(3);\n" +
                        "  estudiante.pintarPromedio();\n" +
                        "  PY.validarCurso(estudiante.getPromedio());\n" +
                        "\n" +
                        "  printf(\"Desea limpiar la pantalla? Presione 1\");\n" +
                        "  booleano=getch();\n" +
                        "  if(booleano==1){\n" +
                        "    clrscr();\n" +
                        "  }\n" +
                        "\n" +
                        "\n" +
                        "  printf(\"\\n DATOS ESTUDIANTE 2 \\n\");\n" +
                        "  scanf(\"Ingrese la nota del curso1 \\n %d\", &curso1);\n" +
                        "  scanf(\"Ingrese la nota del curso2 \\n %d\", &curso2);\n" +
                        "  scanf(\"Ingrese la nota del curso3 \\n %d\", &curso3);\n" +
                        "  JAVA.alumno estudiante2(curso1,curso2,curso3);\n" +
                        "  estudiante2.calcularPromedio(3);\n" +
                        "  PY.validarCurso(estudiante2.getPromedio());\n" +
                        "}";
        Compilador compilador = new Compilador(texto);
        compilador.create();
        compilador.compilar_vb();
        compilador.compilar_java();
        compilador.compilar_pyva();
        compilador.compilar_c();
        SymTable.fix();
        //SymTable.print2();
        generarCodigoIntermedio codigoIntermedio = new generarCodigoIntermedio();
        compilador.printCod();
        //codigoIntermedio.generar(MetodosVisual.instrucciones);

        //Editor.compilarCodigoC(generarCodigoIntermedio.codigoFinal);
        //compilador.printError();
    }

}
