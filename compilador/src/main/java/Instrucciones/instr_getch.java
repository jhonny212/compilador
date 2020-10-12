/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

/**
 *
 * @author jhonny
 */
public class instr_getch extends Instruccion{
    public instr_getch(){
    
    }
    public instr_getch(String id,int f,int c){
        this.COLUMNA=c;
        this.FILA=f;
        this.string=id;
    }
    

    @Override
    public void validate(boolean valid) {
        
    }
    
}
