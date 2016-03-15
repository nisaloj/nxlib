/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botones;

import componentes.BorderCompila;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import static javax.swing.SwingConstants.LEFT;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import utils.UtilsColor;
import utils.UtilsGraphics;

/**
 *
 * @author Nelson
 */
public class Boton extends JButton implements MouseListener, ComponentListener, ActionListener, FocusListener{
    
    /*Tipo del botón*/
    private int tipo_boton = constantes.compila.TIPO_BOTON.ICONO_IZQUIERDA;
    
    /*Variables de comportamiento del botón*/
    private boolean esta_sobre = false;
    private boolean presionado = false;
    private boolean esToggle = false;
    private boolean toggled = false;
    private BorderCompila borde = null;
    private int icon_space = 5;
    /*Variables del botón*/
    private Rectangle bounds_icono;   
    private JLabel label_texto = new JLabel();;
    private ImageIcon icono = null;
    private Boolean desplegable = false;    
    private Insets margen;
    private boolean cambio_local = false;
    private boolean icono_variable = false;
    private JPopupMenu menu = new JPopupMenu();
    private boolean esCircular = false;
    private boolean esRedondeado = false;
    private int porcentaje_radio = 50;
    private int posicion_radio = -1;
    private Color backgroundFocus = null;
    private Color foregroundFocus = null;
    private boolean mostrarTexto = true;
    private int aumentar_icono = 0;
    UtilsGraphics utils_graphics = new UtilsGraphics();
    private Icon icono_ = null;
    public Boton()
    {
        Inicializar();        
    }
    public Boton(String s)
    {
        Inicializar();
        label_texto = new JLabel(s);
    }
    private void Inicializar()
    {
        setLayout(null);
        add(label_texto);
        bounds_icono = new Rectangle(0,0,20,20);
        label_texto.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
        setHorizontalTextPosition(LEFT);
        setContentAreaFilled(false);
        setMargin(new Insets(5,5,5,5));
        margen = getMargin();
        boundsIcono(getWidth(), getHeight());
        addComponentListener(this);
        addMouseListener(this);  
        addFocusListener(this);
        addActionListener(this);
         menu.addPopupMenuListener(new PopupMenuListener(){
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                presionado = true;
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                presionado = false;
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                presionado = false;
            }
        });
        setPreferredSize(new Dimension(120,40));
    }
    
    //<editor-fold defaultstate="collapsed" desc="Bounds Icono">
    /*Función para establecer posición del icono si existe*/
    private void boundsIcono(int ancho, int alto)
    {
        if(icono != null)
        {
            int tam_icono = 0;
            int tam_f = (getFontMetrics(getFont()).getAscent()+getFontMetrics(getFont()).getDescent())+aumentar_icono;
            switch(this.tipo_boton)            
            {
                case constantes.compila.TIPO_BOTON.ICONO_IZQUIERDA:                    
                    if(ancho > alto) tam_icono = alto-(margen.top+margen.bottom);
                    else tam_icono = ancho-(margen.left+margen.right);
                    tam_icono = tam_f;
                    //cambio_local = true;
                    
                    bounds_icono.setBounds(getMargin().left, ((alto/2)-(tam_f/2)), tam_icono, tam_icono);
                    label_texto.setBounds(getMargin().left+tam_icono+icon_space, getMargin().top, (getWidth()-(getMargin().left+getMargin().right+icon_space+tam_icono)), (getHeight()-(getMargin().top+getMargin().bottom)));
                    
                    /*if(ancho > alto) tam_icono = alto-(margen.top+margen.bottom);
                    else tam_icono = ancho-(margen.left+margen.right);
                    tam_icono = tam_f;
                    cambio_local = true;
                    this.setMargin(new Insets(margen.bottom, tam_icono+margen.left, margen.top,margen.right));
                    bounds_icono.setBounds(this.getMargin().left-tam_icono, ((alto/2)-(tam_f/2)), tam_icono, tam_icono);*/
                    break;
                case constantes.compila.TIPO_BOTON.ICONO_COMPLETO:
                    label_texto.setBounds(getMargin().left, getMargin().top, (getWidth()-(getMargin().left+getMargin().right)), (getHeight()-(getMargin().top+getMargin().bottom)));
                    iconoAdaptable(constantes.compila.TIPO_BOTON.ICONO_COMPLETO);
                    break;
                case constantes.compila.TIPO_BOTON.ICONO_Y_TEXTO:
                    label_texto.setBounds(getMargin().left, getMargin().top, (getWidth()-(getMargin().left+getMargin().right)), (getHeight()-(getMargin().top+getMargin().bottom)));
                    this.setHorizontalTextPosition(CENTER);
                    this.setVerticalAlignment(BOTTOM);
                    iconoAdaptable(constantes.compila.TIPO_BOTON.ICONO_Y_TEXTO);
                    break;
            }
        }
        else if(label_texto != null) label_texto.setBounds(getMargin().left, getMargin().top, (getWidth()-(getMargin().left+getMargin().right)), (getHeight()-(getMargin().top+getMargin().bottom)));
        repaint();
    }
    /*Función para adaptar el ícono dependiendo de su aspecto*/
    private void iconoAdaptable(int tipo)
    {
        int ancho = (getWidth()-(getMargin().left+getMargin().right));
        int alto = (getHeight()-(getMargin().bottom+getMargin().top));
        int ancho_max = ancho;
        int alto_max = alto;
        Double porcentaje_an = 100.0;
        Double porcentaje_al = 100.0;
        if(icono != null)
        {
            if(getHeight() > getWidth())
            {
                porcentaje_an = ((double)icono.getIconWidth()/(double)icono.getIconHeight())*100;
                ancho = alto * porcentaje_an.intValue()/100;
                
                if(ancho > ancho_max)
                {
                    ancho = ancho_max;
                    Double pr = (100/porcentaje_an)*ancho;
                    alto = pr.intValue();
                }
            }
            else if(icono.getIconHeight() < icono.getIconWidth())
            {                
                porcentaje_al = ((double)icono.getIconHeight()/(double)icono.getIconWidth())*100;
                alto = ancho * porcentaje_al.intValue()/100;
                
                if(alto > alto_max)
                {
                    alto = alto_max;
                    Double pr = (100/porcentaje_al)*alto;
                    ancho = pr.intValue();
                }                
            }
            else if(icono.getIconHeight() == icono.getIconWidth())
            {              
                if(bounds_icono.getHeight() > bounds_icono.getWidth())
                {
                    alto = ancho;
                    if(alto > alto_max)
                    {
                        alto = alto_max;
                        Double pr = (100/100.0)*alto;
                        ancho = pr.intValue();
                    }   
                }
                else
                {
                    ancho = alto;
                    if(ancho > ancho_max)
                    {
                        ancho = ancho_max;
                        Double pr = (100/100.0)*ancho;
                        alto = pr.intValue();
                    }
                }
            }
        }
        int div = (tipo == constantes.compila.TIPO_BOTON.ICONO_COMPLETO)? 2:3;
        int x = (getWidth()-ancho)/2;
        int y = (getHeight()-alto)/div;
        bounds_icono.setBounds(x,y, ancho,alto);
        repaint();
    }
    //</editor-fold>    
    
    //<editor-fold defaultstate="collapsed" desc="Métodos get y set">
    public void setIcono(Icon img)
    {          
        if(img != null)
        {
            ImageIcon ic = utils_graphics.aImageIcon(img);
            icono = ic;
        }
        boundsIcono(this.getWidth(), this.getHeight());
        repaint();
        updateUI();
    }
    public void setIconSpace(int space)
    {
        icon_space = space;
    }
    public int getIconSpace()
    {
        return icon_space;
    }
    public Icon getIcono()
    {
        return this.icono_;
    }
    public void setIconoVariable(boolean b)
    {
        this.icono_variable = b;
        boundsIcono(getWidth(), getHeight());
    }
    public void setTipoBoton(int p_tipo)
    {
        this.tipo_boton = p_tipo;
        boundsIcono(this.getWidth(), this.getHeight());
        repaint();
        updateUI();
    }
    public void setDesplegable(boolean b)
    {
        desplegable = b;
    }
    public boolean getDesplegable(){return this.desplegable;}
    public void setMostrarTexto(boolean b)
    {
        mostrarTexto = b;
        label_texto.setVisible(b);
    }
    public boolean getMostarTexto(){return this.mostrarTexto;}
    public JPopupMenu getMenu()
    {
        return menu;
    }
    public void setCircular(boolean c)
    {
        this.esCircular = c;
        this.setOpaque(false);
    }
    public void setRedondeado(boolean c)
    {
        this.esRedondeado = c;
        this.setOpaque(false);
    }
    public boolean getCircular(){return this.esCircular;}
    public boolean getRedondeado(){return this.esRedondeado;}
    public void setRedondez(int i, int posicion)
    {
        this.porcentaje_radio = i;
        this.posicion_radio = posicion;
    }
    public void setBorde(BorderCompila bc)
    {
        this.borde = bc;
    }
    public BorderCompila getBorde()
    {
        return this.borde;
    }
    public void setBackgroundFocus(Color c)
    {
        this.backgroundFocus = c;
    }
    public Color getBackgroundFocus(){return this.backgroundFocus;}
    public void setforegroundFocus(Color c)
    {
        this.foregroundFocus = c;
    }
    public Color getforegroundFocus(){return this.foregroundFocus;}
    public void setToggle(boolean b)
    {
        this.esToggle = b;
    }
    public boolean getToggle()
    {
        return this.esToggle;
    }
    public void setToggled(boolean b)
    {
        this.toggled = b;
    }
    public int getAumentarIcono()
    {
        return this.aumentar_icono;
    }
    public void setAumentarIcono(int tam)
    {
        this.aumentar_icono = tam;
        boundsIcono(getWidth(), getHeight());
    }
    public boolean getToggled()
    {
        return this.toggled;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Sobreescritos">
    
    @Override
    public void setIcon(Icon i)
    {
        setIcono(i);
    }
    @Override
    public final void setText(String s)
    {
        if(label_texto != null) label_texto.setText(s);
        this.setToolTipText(s);
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg); //To change body of generated methods, choose Tools | Templates.
        repaint();
    }

    @Override
    public void setBounds(Rectangle r) {
        super.setBounds(r); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override 
    public void setMargin(Insets i)
    {
        super.setMargin(i);
        boundsIcono(getWidth(), getHeight());
        //if(cambio_local == false) margen = i;
        //else cambio_local = false;
    }
    @Override
    public void setContentAreaFilled(boolean b)
    {
        super.setContentAreaFilled(false);
    }
    @Override
    public void setFont(Font f)
    {
        super.setFont(f);
        if(label_texto != null) label_texto.setFont(f);
    }
    @Override
    public void setForeground(Color c)
    {
        super.setForeground(c);
        if(label_texto != null) label_texto.setForeground(c);
    }
    @Override
    public void setVerticalAlignment(int v)
    {
        super.setVerticalAlignment(v);
        if(label_texto != null) label_texto.setVerticalAlignment(v);
    }
    @Override
    public void setHorizontalAlignment(int v)
    {
        super.setHorizontalAlignment(v);
        if(label_texto != null) label_texto.setHorizontalAlignment(v);
    }
    @Override
    public void setVerticalTextPosition(int v)
    {
        super.setVerticalTextPosition(v);
        if(label_texto != null) label_texto.setVerticalTextPosition(v);
    }
    @Override
    public void setHorizontalTextPosition(int v)
    {
        super.setHorizontalAlignment(v);
        if(label_texto != null) label_texto.setHorizontalAlignment(v);
    }
        
    
    private void cambiarApariencia(Graphics2D g2d, int tipo)
    {
        Color c = getBackground();
        if(c.getAlpha() == 0) c = new Color(255,255,255,90);
        
        Color color = (tipo == 0)? ((getBackgroundFocus() == null)? new UtilsColor().getMasObscuro(c,4):new UtilsColor().getMasClaro(backgroundFocus,8)):((getBackgroundFocus() == null)? new UtilsColor().getMasObscuro(c,8):backgroundFocus);
        if(getBorde() != null)
        {
            g2d.setColor(color);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.setStroke(new BasicStroke(borde.getThickness()));
            Insets ins = borde.getInsets();
            g2d.setColor(borde.getColorFocus());

            if(ins.left > 0) g2d.drawLine(ins.left, 0, ins.left, getHeight());
            if(ins.right > 0) g2d.drawLine(getWidth()-ins.right, 0, getWidth()-ins.right, getHeight());
            if(ins.bottom > 0) g2d.drawLine(ins.left, getHeight()-ins.bottom, getWidth()-ins.right, getHeight()-ins.bottom);
            if(ins.top > 0) g2d.drawLine(ins.left, ins.top, getWidth()-ins.right, ins.top);        
        }
        else
        {
            g2d.setColor(color);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        utils_graphics.setRenderings(g2d);
        
        if(getRedondeado())
        {
            utils_graphics.redondeado(g2d, this, porcentaje_radio, posicion_radio);
        }   
        
        if(getToggled() == true)
        {
            cambiarApariencia(g2d,1);
        }
        else
        {            
            if(presionado == false && esta_sobre == false)
            {
                if(getBackground() != null)
                {
                    g2d.setColor(getBackground());
                    g2d.fillRect(0, 0, getWidth(), getHeight());
                }
            }
            if(esta_sobre == true && presionado == false)
            {
                cambiarApariencia(g2d,0);
            }
            else if(presionado == true)
            {
                cambiarApariencia(g2d,1);
            }
        }        
        if(icono != null)
        {
            Image imagen =icono.getImage();
            if(icono_variable == true)
            {
                imagen = utils_graphics.cambiarColor(utils_graphics.getBufferedImage(imagen), getForeground());
            }
            g2d.drawImage(imagen,bounds_icono.x,bounds_icono.y,bounds_icono.width, bounds_icono.height, null);
        }
        
       // super.paintComponent(g2d);
        
        if(getCircular())
        {
            utils_graphics.circular(g2d, this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
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
        boundsIcono(getWidth(), getHeight());
        /*if(getWidth() <= 70)
        {
            this.setTipoBoton(3);
        }*/
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
    public void actionPerformed(ActionEvent e) {
        
        toggled = (esToggle == true)? !toggled:false;
        if(desplegable == true)
        {
            menu.show(this, 0, getHeight());
        }
    }
    //</editor-fold>

    @Override
    public void focusGained(FocusEvent e) {
        if(getforegroundFocus() != null) setForeground(alternar());
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(getforegroundFocus() != null) setForeground(alternar());
    }
    private Color alternar()
    {
        Color aux = getForeground();
        Color fg = foregroundFocus;
        foregroundFocus = aux;
        return fg;
    }
}
