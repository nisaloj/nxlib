/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenedores;

import java.awt.*;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import java.util.Iterator;
import listeners.VisorListener;
import utils.UtilsControls;
import utils.UtilsGraphics;

/**
 *
 * @author Nelson
 */
public class VisorPagina extends JDesktopPane implements ComponentListener, HierarchyListener, java.io.Serializable{
    
    //<editor-fold defaultstate="collapsed" desc="listeners">
    private final ArrayList visorListeners = new ArrayList<>();    
    public void addVisorListener(VisorListener vl)
    {
        visorListeners.add(vl);
    }
    public void frameAgregado(JInternalFrame ji)
    {
        if(visorListener != null) {visorListener.frameAgregado(ji);}
        for (Iterator it = visorListeners.iterator(); it.hasNext();) {
            VisorListener vil = (VisorListener)it.next();
            vil.frameAgregado(ji);
        }
    }
    public void frameEliminado(JInternalFrame ji)
    {
        if(visorListener != null) {visorListener.frameEliminado(ji);}
        for (Iterator it = visorListeners.iterator(); it.hasNext();) {
            VisorListener vil = (VisorListener)it.next();
            vil.frameEliminado(ji);
        }
    }
    public void frameCambiado(JInternalFrame nueva, int direccion)
    {
        if(visorListener != null) {visorListener.frameCambiado(nueva, direccion);}
        for (Iterator it = visorListeners.iterator(); it.hasNext();) {
            VisorListener vil = (VisorListener)it.next();
            vil.frameCambiado(nueva, direccion);
        }
    }
    VisorListener visorListener = null;
    public void setVisorListener(VisorListener vl)
    {
        visorListener = vl;
    }
    //</editor-fold>
    
    UtilsGraphics utils_graphics= new UtilsGraphics();
            
    class Ventana{
        public JInternalFrame ventana;
        public Ventana anterior;
        public Ventana siguiente;
        public int posicion = -1;
    }

    private Ventana ventana_actual = null;
    private ImageIcon imagen_fondo;
    private Boolean ventana_transparente = false;
    private Dimension backup_tam = null;
    
    public VisorPagina()
    {
        this.addComponentListener(this);
        this.addHierarchyListener(this);
        setTamanioLayout(getWidth(), getHeight());
    }
    public void onResize()
    {
        if(ventana_actual != null)
        {
            ventana_actual.ventana.setLocation(0, 0);
            if(ventana_actual.ventana.getWidth() < getWidth() && ventana_actual.ventana.getHeight() < getHeight())
            {                
                ventana_actual.ventana.setSize(getWidth(), getHeight());
            }
            else if(ventana_actual.ventana.getWidth() >= getWidth() && ventana_actual.ventana.getHeight() >= getHeight())
            {
                if(backup_tam != null) ventana_actual.ventana.setSize(backup_tam);
            }
            else if(ventana_actual.ventana.getWidth() < getWidth() && ventana_actual.ventana.getHeight() >= getHeight())
            {
                if(backup_tam != null) ventana_actual.ventana.setSize(backup_tam);
                ventana_actual.ventana.setSize(getWidth(), ventana_actual.ventana.getHeight());
            }
            else if(ventana_actual.ventana.getWidth() >= getWidth() && ventana_actual.ventana.getHeight() < getHeight())
            {
                if(backup_tam != null) ventana_actual.ventana.setSize(backup_tam);
                ventana_actual.ventana.setSize(ventana_actual.ventana.getWidth(), getHeight());
            }
            
        }
    }
    
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        //g2d.setColor((this.getBackground() !=  null)? this.getBackground():Color.white);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        
        //this.setOpaque(this.iso);
        super.paintComponent(g2d);
        if(imagen_fondo != null)
        {
            g2d.drawImage(imagen_fondo.getImage(), 0,0,this.getWidth(), this.getHeight(),this);
            if(ventana_actual != null) ventana_actual.ventana.repaint();
        }
    }
    
   private void setTamanioLayout(int Width, int Height)
    {
        GroupLayout layoutVisor = new javax.swing.GroupLayout(this);
        this.setLayout(layoutVisor);
        layoutVisor.setHorizontalGroup(
            layoutVisor.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, Width, Short.MAX_VALUE)
        );
        layoutVisor.setVerticalGroup(
            layoutVisor.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, Height, Short.MAX_VALUE)
        );  
    }
    private void ocultarResto(JInternalFrame ji)
    {
        for(Component c: getComponents())
        {
            JInternalFrame jif = (JInternalFrame)c;
            if(jif != ji) jif.hide();                
        }
    }
    private void establecerFrame(JInternalFrame jif)
    {
        UtilsControls.JIFUpdateUI(jif);
        ocultarResto(jif);
        jif.show();
        setTamanioLayout(getWidth(), getHeight());        
        onResize();
    }
    private boolean agregarFrame(Component comp)
    {
        boolean respuesta = false;
        if(comp instanceof JInternalFrame)
        {
            int count = getComponentCount();
            if(count > 0)
            {
                if(ventana_actual.ventana != comp)
                {
                    Ventana vaux = ventana_actual;
                    ventana_actual = new Ventana();
                    ventana_actual.ventana = (JInternalFrame)comp;
                    ventana_actual.anterior = vaux;
                    establecerFrame(ventana_actual.ventana);
                    frameAgregado(ventana_actual.ventana);
                    respuesta = true;
                }
            }
            else
            {
                ventana_actual = new Ventana();
                ventana_actual.ventana = (JInternalFrame) comp;
                establecerFrame(ventana_actual.ventana);
                frameAgregado(ventana_actual.ventana);                
                respuesta = true;
            }
        }
        return respuesta;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Gets y sets">
    public void setFrameAnterior()
    {
        if(ventana_actual != null)
        {
            if(ventana_actual.anterior != null)
            {
                Ventana aux = ventana_actual;
                ventana_actual = ventana_actual.anterior;
                ventana_actual.siguiente = aux;
                establecerFrame(ventana_actual.ventana);
                frameCambiado(ventana_actual.ventana, -1);
            }
        }
    }
    public void setFrameSiguiente()
    {
        if(ventana_actual != null)
        {
            if(ventana_actual.siguiente != null)
            {
                Ventana aux = ventana_actual;
                ventana_actual = ventana_actual.siguiente;
                ventana_actual.anterior = aux;
                establecerFrame(ventana_actual.ventana);
                frameCambiado(ventana_actual.ventana, 1);
            }
        }
    }
    public boolean isFrameAnterior()
    {
        if(ventana_actual != null) return (ventana_actual.anterior != null);            
        return false;
    }
    public boolean isFrameSiguiente()
    {
        if(ventana_actual != null) return (ventana_actual.siguiente != null);            
        return false;
    }
    public void setVentanaTransparente(Boolean b)
    {
        this.ventana_transparente = b;
        if(ventana_actual != null)
        {
            ventana_actual.ventana.setBackground(null);
        }
    }
    public Boolean isVentanaTransparente()
    {
        return this.ventana_transparente;
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
    }
    public JInternalFrame getSelectedFrame()
    {
        if(ventana_actual != null)
        {
            return ventana_actual.ventana;
        }
        return new JInternalFrame();
    }
    
    //</editor-fold>
    
    @Override
    public void add(Component comp, Object constraints, int index) {
        if(agregarFrame(comp)) super.add(comp, constraints, index); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public void add(Component comp, Object constraints) {
        if(agregarFrame(comp)) super.add(comp, constraints); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Component add(Component comp, int index) {
        if(agregarFrame(comp)) return super.add(comp, index); //To change body of generated methods, choose Tools | Templates.
        else return null;
    }

    @Override
    public Component add(String name, Component comp) {
        if(agregarFrame(comp)) return super.add(name, comp); //To change body of generated methods, choose Tools | Templates.
        else return null;
    }

    @Override
    public Component add(Component comp) {
        if(agregarFrame(comp)) return super.add(comp); //To change body of generated methods, choose Tools | Templates.
        else return null;
    }
    
    @Override
    public void componentResized(ComponentEvent e) {
        onResize();
        updateUI();
        //invalidate();
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

    @Override
    public void hierarchyChanged(HierarchyEvent e) {
        if(e.getComponent().getParent() != null)
        {
            e.getComponent().addComponentListener(new ComponentAdapter(){

                @Override
                public void componentResized(ComponentEvent e) {
                    if(getWidth() < e.getComponent().getWidth())
                    {
                        setSize(getWidth(), e.getComponent().getWidth());
                    }
                    if(getHeight() < e.getComponent().getHeight())
                    {
                        setSize(getWidth(), e.getComponent().getHeight());
                    }
                }
            });
        }
    }
}
