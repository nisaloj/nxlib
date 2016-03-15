/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenedores;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import utils.UtilsGraphics;

/**
 *
 * @author Nelson
 */
public class PanelFondo extends JPanel{
    private ImageIcon imagen_fondo;
    private boolean circular = false;
    UtilsGraphics utils_graphics = new UtilsGraphics();
    
    public PanelFondo()
    {}
    public void setCircular(boolean b){
        circular = b;
        repaint();
    }
    public void setImagenFondo(Icon img)
    {    
        if(img != null)
        {
            this.setOpaque(false);
            BufferedImage image = new BufferedImage(img.getIconWidth(), img.getIconHeight(), BufferedImage.TYPE_INT_ARGB);            
            Graphics2D g = (Graphics2D) image.createGraphics();
            utils_graphics.setRenderings(g);
            img.paintIcon(null, g, 0,0);
            g.dispose();            
                        
            ImageIcon ic = new ImageIcon(image);
            imagen_fondo = ic;              
        }
        repaint();
    }
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        utils_graphics.setRenderings(g2d);
        
        if(this.isOpaque() == true)
        {
            g2d.setColor(this.getBackground());
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        if(imagen_fondo != null)
        {
            g2d.drawImage(imagen_fondo.getImage(), -1,-1,this.getWidth()+2, this.getHeight()+2,null);
        }
        if(circular == true)
        {
            Shape circulo = new Ellipse2D.Float(0, 0, this.getWidth(), this.getHeight());
            Container parent = this.getParent();
            if(parent!=null) {
              g2d.setColor(parent.getBackground());
              Area corner = new Area(new Rectangle2D.Float(0, 0, this.getWidth(), this.getHeight()));
              corner.subtract(new Area(circulo));
              g2d.fill(corner);
            }
        }
    }
}
