/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import CodigoEjecutable.generarCodigoIntermedio;
import Errores.ErrorClass;
import Lenguajes.MetodosVisual;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jhonny
 */
public class Editor {

    public static void SaveAs(String nombre,JTabbedPane Content) {
        try {
            ContentTab tab = (ContentTab) Content.getSelectedComponent();
            if(tab==null){
            return;}
            File file = obtenerRuta(nombre + ".mlg", tab.getTexto());
            tab.fileName=nombre;
            tab.fichero = file;
            tab.texto_ = tab.getTexto();
            
        } catch (Exception ex) {
        }
    }

    public static void Save(JTabbedPane Content) {
        try {
            ContentTab tab = (ContentTab) Content.getSelectedComponent();
            if(tab==null){
            return;}
            if (tab.fichero != null) {
                write(tab.fichero, tab.getTexto());
                tab.texto_ = tab.getTexto();
            } else {
                File file = obtenerRuta(tab.fileName + ".mlg", tab.getTexto());
                tab.fichero = file;
                tab.texto_ = tab.getTexto();
            }
        } catch (Exception ex) {
        }
    }
static ContentTab program_=null;
    public static void Nuevo(JTabbedPane Content, String fn) {

        if(program_==null){
            program_ = new ContentTab();
            Content.add("Programa", program_);
            program_.fileName = fn;
        }else{
            program_.setTexto("");
            program_.fileName=fn;
        }
    }
    public static ContentTab codInter=null;
    public static void addCodInter(JTabbedPane Content){
        String txt= generarCodigoIntermedio.codigoEjecutable.toString();
        codInter=new ContentTab();
        codInter.setTexto(txt);
        Content.add("Codigo Intermedio",codInter);
    }

    public static void Nuevo(JTabbedPane Content, File file, String texto) {
        program_ = new ContentTab();
        program_.fichero = file;
        program_.setTexto(texto);
        Content.add("Programa", program_);

    }

    static File obtenerRuta(String name, String texto) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setDialogTitle("select folder");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setAcceptAllFileFilterUsed(false);
        int seleccion = chooser.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getCurrentDirectory();
            String path = f.getAbsolutePath() + "/" + name;
            File file = new File(path);
            write(file, texto);
            return file;
        }
        return null;
    }

    public static boolean Open(JTabbedPane Content) {
        File file = GetFile();
        if (file != null) {
            String texto = read(file);
            Nuevo(Content, file, texto);
            return true;
        }
        return false;
    }

    public static File GetFile() {
        File fichero = null;
        JFileChooser fileChooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter(".mlg", "mlg");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(filter);
        int seleccion = fileChooser.showSaveDialog(null);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            fichero = fileChooser.getSelectedFile();
        }
        return fichero;
    }

    public static void write(File path, String texto) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(path);
            pw = new PrintWriter(fichero);
            pw.write(texto);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
            }
        }
    }

    static String read(File file) {
        FileReader fr = null;
        String retorno = "";

        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea = "";
            while ((linea = br.readLine()) != null) {
                if (retorno.isEmpty()) {
                    retorno = linea;
                } else {
                    retorno += "\n" + linea;
                }
            }
        } catch (IOException e) {
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (IOException e2) {
            }
        }
        return retorno;
    }
    
    public static void addError(ErrorClass er,JTabbedPane Content){
        TablaErrores tb=new TablaErrores();
        tb.initDatas(er);
        tb.show();
        //Content.add("Errores", tb);
    }

    public static void compilarCodigoC(StringBuffer stringBuffer){
        write(new File("codigo.c"),stringBuffer.toString());
        try{
            Runtime.getRuntime().exec("gcc codigo.c -o codigo");
        }catch(Exception ex){}

    }
    
}
