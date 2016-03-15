/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenedores;

import utils.UtilsColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Nelson
 */
public class FilaAcordeon extends JPanel implements MouseListener, ComponentListener{
    private BotonFila boton_titulo;
    private AcordeonPanel acordeon = new AcordeonPanel();
    int gap = 8;
    public BotonFila getBotonTitulo()
    {
        return this.boton_titulo;
    }
    public void setDesplegable(boolean b)
    {
        boton_titulo.setDesplegable(b);
    }
    public FilaAcordeon(String titulo)
    {
        boton_titulo = new BotonFila(titulo);
        boton_titulo.setForeground(Color.black);
        inicializar();
    }
    public FilaAcordeon()
    {
        inicializar();
    }
    @Override
    public void setBackground(Color c)
    {
        if(boton_titulo != null)
        {
            boton_titulo.setBackground(c);
        }
        super.setBackground(c);
        invalidate();
    }
    private void inicializar()
    {
        boton_titulo.setDesplegable(true);
        add(boton_titulo);
        setBackground(Color.white);
        boton_titulo.addMouseListener(this);
        
        
        acordeon.setVisible(false);
        
        add(acordeon);        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(boton_titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(gap, gap, gap)
            .addComponent(acordeon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        
        Group g = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addComponent(boton_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(acordeon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        );
        
        layout.setVerticalGroup(
            g
        );      
       this.setLayout(layout);
       this.addComponentListener(this);
       //setPreferredSize(new Dimension(200,30));
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
    
    public void setAltoBoton(int alto_boton)
    {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(boton_titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGap(10, 10, 10)
            .addComponent(acordeon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        
        Group g = layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        //.addComponent(boton_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(boton_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, (alto_boton > 0)? alto_boton:40, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addComponent(acordeon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        );
        
        layout.setVerticalGroup(
            g
        );      
        this.setLayout(layout);  
        this.repaint();
    }
    public AcordeonPanel getAcordeonPanel()
    {
        return this.acordeon;
    }
    public FilaAcordeon addFilaAcordeon(FilaAcordeon fila)
    {        
        return acordeon.addFilaAcordeon(fila);
    }
    public void addActionListener(ActionListener al)
    {
        boton_titulo.addActionListener(al);
    }
    public void setTitulo(String titulo)
    {
        boton_titulo.setText(titulo);
        invalidate();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(boton_titulo.esDesplegable())
        {
            if(boton_titulo.isSelected() == true)
            {
                acordeon.setVisible(true);
            }
            else
            {
                acordeon.setVisible(false);
            }
        }
        repaint();
        //acordeon.setVisible(!acordeon.isVisible());
        //BoxLayout bl = new javax.swing.BoxLayout(getParent(), javax.swing.BoxLayout.Y_AXIS);
        //bl.
        //getParent().setLayout(bl);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void componentResized(ComponentEvent e) {
       
    }

    @Override
    public void componentMoved(ComponentEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentShown(ComponentEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
