/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popups;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;

/**
 *
 * @author Nelson
 */
public class Popup extends JPopupMenu{
    private Point posicion = new Point(0,0);
    public Popup(){}
    public void setPosicion(int y, int alto)
    {
        posicion = new Point(y,alto);
    }
    @Override 
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(this.getBackground());
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    @Override
    public void paintBorder(Graphics g)
    {    
        Graphics2D g2d = (Graphics2D)g;
        if(getBorder() != null)
        {
            LineBorder lb = ((LineBorder) getBorder());
            int tamanio = lb.getThickness();
            g2d.setColor(lb.getLineColor());
            g2d.setStroke(new BasicStroke(tamanio));
            g2d.drawRect(0, 0, this.getWidth()-tamanio, this.getHeight()-tamanio);
            g2d.setColor(this.getBackground());
                        
            int tamanio_flecha = 12;
            int ancho_flecha = (tamanio_flecha/3)*4;
            g2d.fillRect(0, posicion.x+((posicion.y/2)-ancho_flecha), tamanio, ancho_flecha*2);
        }
    }
}