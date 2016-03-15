/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textfields;

import botones.Boton;
import componentes.BorderCompila;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import utils.UtilsGraphics;

/**
 *
 * @author Nelson
 */
public class TextField extends JTextField implements ComponentListener{
    
    private int porcentaje_radio = 0;
    private Insets margen;
    private String placeholder = "";
    private Color color_placeholder = new Color(165,165,165);
    
    private Rectangle bounds_icono = new Rectangle(10,5,20,20);    
    private ImageIcon icono = null;
    private boolean icono_variable = false;
    
    UtilsGraphics utils_graphics = new UtilsGraphics();
    
    private boolean cambio_local = false;    
    private BorderCompila borde = null;
    private Icon icono_ = null;
    
    private Boton boton = new Boton("...");
    public TextField()
    {
        inicializar();
    }
    public TextField(String texto)
    {
        this.setText(texto);
        inicializar();
    }    
    private void inicializar()
    {
        setPreferredSize(new Dimension(120,35));
        setMargin(new Insets(0,10,0,10));
        setBorder(null);
        boundsIcono(this.getWidth(), this.getHeight());
        addComponentListener(this);
        setForeground(new Color(175,175,175));
        setRedondez(25);
        boton.setVisible(false);
        boton.setHorizontalTextPosition(CENTER);
        boton.setBackground(new Color(245,245,245));
        boton.setRedondeado(true);
        this.add(boton);
    }
    
    private void boundsIcono(int ancho, int alto)
    {
        if(icono != null)
        {
            int tam_icono = 0;
            if(ancho > alto) tam_icono = alto-(margen.top+margen.bottom);
            else tam_icono = ancho-(margen.left+margen.right); 
                                         
            cambio_local = true;
            this.setMargin(new Insets(margen.bottom, tam_icono+5, margen.top,margen.right));
            bounds_icono.setBounds(5, margen.top, tam_icono, tam_icono);
        }
        if(boton.isVisible())
        {
            int tam_boton;
            if(ancho > alto) tam_boton = alto-(margen.top+margen.bottom);
            else tam_boton = ancho-(margen.left+margen.right);
            tam_boton = tam_boton - 8;
            int x = (this.getWidth()-(tam_boton+4));
            int y = (this.getHeight()/2)-((tam_boton)/2);
            boton.setBounds(x, y, tam_boton, tam_boton);
            cambio_local = true;
            this.setMargin(new Insets(getMargin().bottom, getMargin().left, getMargin().top,tam_boton+8));
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Get's y sets">
    public void setPlaceholder(String p_placeholder)
    {
        this.placeholder = p_placeholder;
    }
    public String getPlaceholder()
    {
        return this.placeholder;
    }
    public void setColorPlaceholder(Color c)
    {
        this.color_placeholder = c;
    }
    public Color getColorPlaceholder()
    {
        return this.color_placeholder;
    }
    public void setRedondez(int por)
    {
        this.porcentaje_radio = por;
        boton.setRedondez(por, -1);
    }
    public int getRedondez()
    {
        return this.porcentaje_radio;
    }
    public void setIcono(Icon img)
    {          
        if(img != null)
        {
            ImageIcon ic = utils_graphics.aImageIcon(img);
            icono = ic;
        }
        boundsIcono(this.getWidth(), this.getHeight());
        repaint();
    }
    public Icon getIcono()
    {
        return this.icono_;
    }
    public void setBorde(BorderCompila bc)
    {
        this.borde = bc;
    }
    public BorderCompila getBorde()
    {
        return this.borde;
    }
    public boolean esVariable(){return this.icono_variable;}
    public void setVariable(boolean b){
        this.icono_variable = b;
    }
    public void setBotonVisible(boolean b)
    {
        boton.setVisible(b);
        boundsIcono(this.getWidth(), this.getHeight());
        repaint();
    }
    public Boton getBoton()
    {
        return this.boton;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Override's">
    @Override 
    public void setMargin(Insets i)
    {
        super.setMargin(i);
        if(cambio_local == false) margen = i;
        else cambio_local = false;
    }
    @Override
    public Insets getInsets()
    {
        return getMargin();
    }
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        utils_graphics.setRenderings(g2);
        
        int r = (this.getHeight()*this.porcentaje_radio)/100;
        RoundRectangle2D round = new RoundRectangle2D.Float(0, 0, this.getWidth()-0, this.getHeight()-0, r, r);
        if(this.getBorde() == null && this.getBorder()== null && porcentaje_radio >= 5)
        {
            g2.setColor(getBackground());
            g2.setStroke(new BasicStroke(2f));
            g2.draw(round);
        }
        g2.clip(round);
        super.paintComponent(g2);
        //Crear el estilo redondeado
        g2.setClip(null);
        
        g2.setColor(color_placeholder);
        //dibuja placeholder
        Font fuente = getFont();
        g2.setFont(fuente);
        g2.drawString((getText().equals(""))?placeholder:"", getInsets().left, (getSize().height)/2 + fuente.getSize()/2 );        
        
        if(icono != null)
        {
            Image imagen = icono.getImage();
            if(icono_variable == true)
            {
                imagen = utils_graphics.cambiarColor(utils_graphics.getBufferedImage(imagen), this.getForeground());
            }
            g2.drawImage(imagen,bounds_icono.x,bounds_icono.y,bounds_icono.width, bounds_icono.height, null);
        }        
    }
    @Override 
    public void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int r = (this.getHeight()*this.porcentaje_radio)/100;
        RoundRectangle2D round = new RoundRectangle2D.Float(2, 2, this.getWidth()-4, this.getHeight()-4, r, r);        
        if(this.getBorde() != null && this.getBorder() == null)
        {           
            g2.setStroke(new BasicStroke(borde.getThickness()));
            Insets ins = borde.getInsets();
            if(ins.left == ins.bottom && ins.top == ins.right && ins.left == ins.right)
            {
                if(!isFocusOwner())
                    g2.setColor(borde.getColor());
                else
                    g2.setColor(borde.getColorFocus());
                g2.draw(round);
            }
            else
            {
                if(!isFocusOwner())
                    g2.setColor(borde.getColor());
                else
                    g2.setColor(borde.getColorFocus());
                
                if(ins.left > 0) g2.drawLine(ins.left, 0, ins.left, getHeight());
                if(ins.right > 0) g2.drawLine(getWidth()-ins.right, 0, getWidth()-ins.right, getHeight());
                if(ins.bottom > 0) g2.drawLine(ins.left, getHeight()-ins.bottom, getWidth()-ins.right, getHeight()-ins.bottom);
                if(ins.top > 0) g2.drawLine(ins.left, getHeight()-ins.top, getWidth()-ins.right, getHeight()-ins.top);
            }            
        }
        if(this.getBorder() != null)
        {
            super.paintBorder(g);
        }
        
    }
    //</editor-fold>

    @Override
    public void componentResized(ComponentEvent e) {
        boundsIcono(this.getWidth(), this.getHeight());
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
