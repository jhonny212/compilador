/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Instrucciones;

import Lenguajes.MetodosVisual;

/**
 *
 * @author jhonny
 */
public class inst_clear extends Instruccion{

    @Override
    public void validate(boolean valid) {
        MetodosVisual.add("clr", "", "", "", 16);
    }
    
}
