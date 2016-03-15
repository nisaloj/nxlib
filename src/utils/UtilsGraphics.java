/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Nelson
 */
public class UtilsGraphics {
    public UtilsGraphics(){} 
    public void setRenderings(Graphics2D g)
    {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE);
        //g.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }
     public ImageIcon aImageIcon(Icon img)
    {
        ImageIcon res = null;
        if(img != null)
        {
            BufferedImage image = new BufferedImage(img.getIconWidth(), img.getIconHeight(), BufferedImage.TYPE_INT_ARGB);            
            Graphics2D g = (Graphics2D) image.createGraphics();
            setRenderings(g);
            img.paintIcon(null, g, 0,0);
            g.dispose();            
                        
            res = new ImageIcon(image);               
        }
        return res;
    }
    public BufferedImage getBufferedImage(Image img){
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        setRenderings(bGr);
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }
    public Image cambiarColor(BufferedImage f, Color color){
        BufferedImage foto = f;
        
        BufferedImage image = new BufferedImage(f.getWidth(), f.getHeight(), BufferedImage.TYPE_INT_ARGB);            
        Graphics2D gr = (Graphics2D) image.createGraphics();
        setRenderings(gr);
        for(int i=0;i<foto.getWidth();i++){
            for(int j=0;j<foto.getHeight();j++){
                if(foto.getRGB(i, j) <= -14777216)
                {
                    gr.setColor(color);
                    //Shape s = new Line2D.Double(i, j, i+1, j+1);
                    //gr.draw(s);
                    gr.drawLine(i, j, i+1, j+1);
                }
                //System.out.println(foto.getRGB(i, j));
            }
        }
        setRenderings(gr);
        foto = image;
        gr.dispose();
        return (Image) foto;
    }   
    
    public void redondeado(Graphics2D g2d, Component c, int porcentaje, int posicion_radio)
    {
        int r = (c.getHeight()*porcentaje)/100;
        RoundRectangle2D round = new RoundRectangle2D.Float(-(c.getWidth()/2), 2, (c.getWidth()+(c.getWidth()/2))-2, c.getHeight()-4, r, r);
        switch(posicion_radio)
        {
            case 1:
                round = new RoundRectangle2D.Float(2, 2, c.getWidth()+(c.getWidth()/2), c.getHeight()-4, r, r);
                break;
            case 2:
                round = new RoundRectangle2D.Float(2, 2, c.getWidth()-4, c.getHeight()-4, r, r);
                break;
        }
        g2d.setColor(c.getBackground());
        g2d.draw(round);
        g2d.clip(round);   
    }
    public void circular(Graphics2D g2d, Component c)
    {
        Shape circulo = new Ellipse2D.Float(0, 0, c.getWidth(), c.getHeight());
        Container parent = c.getParent();
        if(parent!=null) {
          g2d.setColor(parent.getBackground());
          Area corner = new Area(new Rectangle2D.Float(0, 0, c.getWidth(), c.getHeight()));
          corner.subtract(new Area(circulo));
          g2d.fill(corner);
        }
    }
}
