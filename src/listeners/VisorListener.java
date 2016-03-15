/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import javax.swing.JInternalFrame;

/**
 *
 * @author Nelson
 */
public abstract interface VisorListener
{    
    public abstract void frameAgregado(JInternalFrame ventana);
    public abstract void frameEliminado(JInternalFrame ventana);
    public abstract void frameCambiado(JInternalFrame actual, int direccion);
}
