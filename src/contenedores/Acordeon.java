/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenedores;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Nelson
 */
public class Acordeon extends JScrollPane{
    private AcordeonPanel acordeonPanel;
    public Acordeon(){
        acordeonPanel = new AcordeonPanel();
        this.setOpaque(false);
        this.setViewportView(acordeonPanel);
    }    
    public AcordeonPanel getPanelAcordeon()
    {
        return this.acordeonPanel;
    }
    public FilaAcordeon getFilaAcordeon(int pos)
    {
        return acordeonPanel.getFilaAcordeon(pos);
    }
    public FilaAcordeon addFilaAcordeon(String titulo, String id)
    {
        return acordeonPanel.addFilaAcordeon(titulo, id);
    }
    public FilaAcordeon addFilaAcordeon(FilaAcordeon fila)
    {
        return acordeonPanel.addFilaAcordeon(fila);
    }
    public void vaciar()
    {
        acordeonPanel.vaciar();
    }
    public void setAltoBoton(int alto)
    {
        acordeonPanel.setAltoBoton(alto);
    }
}

