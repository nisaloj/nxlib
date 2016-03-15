/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Nelson
 */
public class CabeceraRender implements TableCellRenderer{
    
    private int alto = 40;
    private Color back_color = Color.white;
    private Color foreground_color = new Color(92,92,92);
    private boolean dibujarBorde = false;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JComponent jcomponent = null;

        if(value instanceof String ) {
            jcomponent = new JLabel((String) " " + value);
            ((JLabel)jcomponent).setHorizontalAlignment(SwingConstants.LEFT );
            ((JLabel)jcomponent).setSize(alto, jcomponent.getWidth());
            ((JLabel)jcomponent).setPreferredSize( new Dimension(6, jcomponent.getWidth()) );
        } 

        jcomponent.setEnabled(true);        
        if(dibujarBorde == true) 
        {
            jcomponent.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(221, 211, 211)));
        }
        jcomponent.setOpaque(true);
        jcomponent.setBackground(back_color);
        jcomponent.setForeground(foreground_color);
        jcomponent.setToolTipText("Columa Número. "+(column+1));

        return jcomponent;
    }
    public void setAlto(int alto_p)
    {
        alto = alto_p;
    }
    public void setBackground(Color c)
    {
        back_color = c;
    }
    public void dibujarBorde(boolean b)
    {
        dibujarBorde = b;
    }
    public void setForeground(Color c)
    {
        foreground_color = c;
    }
}