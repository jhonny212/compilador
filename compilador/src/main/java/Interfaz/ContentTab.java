/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.io.File;

/**
 *
 * @author jhonny
 */
public class ContentTab extends javax.swing.JPanel {

    /**
     * Creates new form ContentTab
     */
    public ContentTab() {
        initComponents();
        TextLineNumber tln1 = new TextLineNumber(this._txt);
        this.jScrollPane1.setRowHeaderView(tln1);
    }
    String fileName;
    File fichero=null;
    String texto_="";
    
    public void setTexto(String txt){
        this._txt.setText(txt);
        this.texto_=txt;
    }
    public String getTexto(){
    return this._txt.getText();}
    
    public boolean canSave(){
        try{
            return this.texto_.equals(this._txt.getText());
        }catch(Exception ex){}
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        _txt = new javax.swing.JTextArea();

        _txt.setColumns(20);
        _txt.setRows(5);
        jScrollPane1.setViewportView(_txt);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea _txt;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
