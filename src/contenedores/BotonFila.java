/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenedores;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import utils.UtilsColor;

/**
 *
 * @author Nelson
 */
public class BotonFila extends JButton implements MouseListener, ComponentListener{
    
    private int tamanio_flecha = 11;
    
    private boolean selected = false;
    private boolean esta_sobre = false;
    private boolean presionado = false;
    
    private boolean desplegable = true;
    
    private JLabel label_texto;
    private JLabel label_icono;
    
    private ImageIcon icono = null;
    public BotonFila()
    {
        Inicializar();
    }
    public BotonFila(String s)
    {
        Inicializar();
        setText(s);
    }
    int margen = 1;
    private void Inicializar()
    {
        setLayout(null);
        label_texto = new JLabel();
        label_icono = new JLabel();
        setHorizontalAlignment(LEFT);
        setContentAreaFilled(false);
        setBackground(new Color(250,250,250));
        addMouseListener(this);
        add(label_icono);
        //label_icono.setBackground(Color.red);
        //label_icono.setOpaque(true);
        add(label_texto);
        setMargin(new Insets(0,0,0,0));
        bounds_labels(0, 0, 200, 30);
        addComponentListener(this);
        //setPreferredSize(new Dimension(200,30));
    }
    private void bounds_labels(int x, int y, int ancho, int alto)
    {        
        label_icono.setBounds(x+margen, y+margen, alto-(margen*2), alto-(margen*2));
        label_texto.setBounds(label_icono.getX()+label_icono.getWidth(), y, ancho-(label_icono.getWidth()), alto);
    }
    public void setIcono(Icon img)
    {    
       // label_icono.setBounds(decima_ancho.intValue()*2,(decima_alto.intValue()), getWidth() - (decima_ancho.intValue()*4),getHeight() - (56));
        if(img != null)
        {
            ImageIcon ic = aImageIcon(img);
            icono = ic;
            ImageIcon mod = new ImageIcon(ic.getImage().getScaledInstance(label_icono.getWidth(), label_icono.getHeight(), Image.SCALE_DEFAULT));
            //label_icono.setIcon(mod);   
        }
    }
    public ImageIcon aImageIcon(Icon img)
    {
        ImageIcon res = null;
        if(img != null)
        {
            BufferedImage image = new BufferedImage(img.getIconWidth(), img.getIconHeight(), BufferedImage.TYPE_INT_ARGB);            
            Graphics2D g = (Graphics2D) image.createGraphics();
            setRenderings(g);
            //g.fillRect(0, 0, img.getIconWidth(), img.getIconHeight());
            img.paintIcon(null, g, 0,0);
            g.dispose();            
                        
            res = new ImageIcon(image);               
        }
        return res;
    }
    private void setRenderings(Graphics2D g)
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
    
    //<editor-fold defaultstate="collapsed" desc="Sobreescritos">
    @Override
    public final void setText(String s)
    {
        label_texto.setText(s);
    }
    @Override
    public void setFont(Font f)
    {
        if(label_texto != null)
        label_texto.setFont(f);
    }
    @Override
    public void setForeground(Color c)
    {
        if(label_texto != null)
        label_texto.setForeground(c);
    }
    @Override
    public void setVerticalAlignment(int v)
    {
        label_texto.setVerticalAlignment(v);
    }
    @Override
    public void setHorizontalAlignment(int v)
    {
        label_texto.setHorizontalAlignment(v);
    }
    @Override
    public void setVerticalTextPosition(int v)
    {
        label_texto.setVerticalTextPosition(v);
    }
    @Override
    public void setHorizontalTextPosition(int v)
    {
        label_texto.setHorizontalTextPosition(v);
    }
    //</editor-fold>
    
    public void setDesplegable(boolean b)
    {
        desplegable = b;
    }
    public boolean esDesplegable(){return this.desplegable;}
    @Override
    public boolean isSelected(){ return selected;}
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        setRenderings(g2d);
        
        Polygon p = new Polygon();
        
        if(selected == false)
        {
            int x_margin = 10;
            Double separacion = (getHeight()-tamanio_flecha)/2.0;
            p.addPoint(getWidth()-(x_margin+tamanio_flecha), separacion.intValue());
            p.addPoint(getWidth()-(x_margin+tamanio_flecha), getHeight()-(separacion.intValue()));
            p.addPoint((getWidth()-(x_margin+tamanio_flecha))+tamanio_flecha, getHeight()/2);
        }
        else
        {
            int x_margin = 10;
            Double separacion = (getHeight()-tamanio_flecha)/2.0;
            p.addPoint(getWidth()-(x_margin+tamanio_flecha), separacion.intValue());
            p.addPoint(getWidth()-(x_margin), separacion.intValue());
            p.addPoint((getWidth()-(x_margin))-(tamanio_flecha/2), (separacion.intValue()+tamanio_flecha));
        }
        g2d.setColor((selected == true && desplegable == true)? new UtilsColor().getMasObscuro(getBackground(),12):getBackground());
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        if(presionado == true && desplegable == false)
        {
            g2d.setColor(new UtilsColor().getMasObscuro(getBackground(),12));
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        g2d.setColor(label_texto.getForeground());
        /*if(desplegable == true)
        {
            g2d.fillPolygon(p);
        }*/
        if(esta_sobre == true)
        {
            g2d.setColor(new UtilsColor().getMasClaro(new Color(95,95,95), 12));
            g2d.setStroke(new BasicStroke(4f));
            g2d.drawRect(1, 1, this.getWidth()-3, this.getHeight()-3);
        }
        if(icono != null)
        {
            g2d.drawImage(icono.getImage(),margen,margen,label_icono.getWidth(), label_icono.getHeight(), null);
        }
       // super.paintComponent(g2d);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        selected = !selected;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        presionado = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        presionado = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        esta_sobre = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        esta_sobre = false;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        bounds_labels(0, 0, this.getWidth(), this.getHeight());
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
