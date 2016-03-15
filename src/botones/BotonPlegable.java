/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botones;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import popups.Popup;

/**
 *
 * @author Nelson
 */
public class BotonPlegable extends Boton implements MouseListener,ActionListener{
    private Popup menu = new Popup();
    private JPanel content_menu = new JPanel();
    private boolean presionado = false;
    public BotonPlegable(String s){
        this.setText(s);
        inicializar();
    }
    public BotonPlegable(){     
        inicializar();
    }
    private void inicializar()
    {
        this.add(menu);
        menu.setBorder(BorderFactory.createLineBorder(new Color(170,170,170), 1));
        menu.addPopupMenuListener(new PopupMenuListener(){
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                presionado = true;
                menu.setPopupSize(new Dimension(getWidth(), getParent().getHeight()));
                menu.setPosicion(getY(), getHeight());
                requestFocusInWindow();
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                presionado = false;
                repaint();
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                presionado = false;
            }
        });
        this.addMouseListener(this);
        this.addActionListener(this);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        presionado = true;
        menu.show(this.getParent(), this.getX()+this.getWidth(), 0);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        presionado = false;
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        //this.doClick();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       
    }
    @Override 
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        super.paintComponent(g2d);
        if(menu.isVisible())
        {            
            Polygon p = new Polygon();
            int tamanio_flecha = 12;
            int ancho_flecha = (tamanio_flecha/3)*4;
            g2d.setColor(menu.getBackground());
            p.addPoint(this.getWidth()-tamanio_flecha, (this.getHeight()/2));
            p.addPoint(this.getWidth()+1, ((this.getHeight()/2)-ancho_flecha)-1);
            p.addPoint(this.getWidth()+1, ((this.getHeight()/2)+ancho_flecha)+1);
            g2d.fill(p);
            
            if(menu.getBorder() != null)
            {
                LineBorder lb = ((LineBorder) menu.getBorder());
                g2d.setColor(lb.getLineColor());
                g2d.draw(p);
            }
        }
    }
    
    public Popup getPopup()
    {
        return menu;
    }
}
