/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botones;

import utils.UtilsControls;
import utils.UtilsGraphics;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.TimerTask;
import javax.swing.*;
import utils.UtilsColor;

/**
 *
 * @author Nelson
 */
public class BotonDescriptivo extends JButton implements MouseListener, KeyListener, ComponentListener, Serializable{

    //<editor-fold defaultstate="collapsed" desc="Variables">   
    private boolean mouseEntered = false;
    private boolean clickDerecho = false;
    private boolean mousePressed = false;
    private boolean botonDesplegado = false;
    private boolean mostrarDescripcion = false;
    
    private Color background = new Color(255,255,255);
    private Color color_borde = new Color(240,240,240);
        
    private ImageIcon imagen;
    private BufferedImage captura = null;
    
    private Rectangle bounds_fondo;
    private String titulo = "";
    private String descripcion = "";
    private int posicion_animacion = 0;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constantes">
    private UtilsColor utils_color = new UtilsColor();
    
    private final int margen_texto = 5;
    private final int margen_alejar = 5;    
    private final int margen_titulo = 10;
    private final int margen_pressed = 3;
    private final int mini_icono_ancho = 15;
        
    private final UtilsGraphics utils_graphics = new UtilsGraphics();   
    private final Rectangle tamanio_titulo = new Rectangle(0,0,0,40);    
   
    private final int tamanio_flecha = 8;
    
    private final int porcentaje_variacion = 7;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Funciones Privadas">
    private String quitarHTML(String s)
    {
        String resp = s;
        resp = resp.replace("<html>","").replace("</html>", "");
        return resp;
    }
    private ImageIcon aImageIcon(Icon img)
    {
        ImageIcon res = null;
        if(img != null)
        {
            BufferedImage image = new BufferedImage(img.getIconWidth(), img.getIconHeight(), BufferedImage.TYPE_INT_ARGB);            
            Graphics2D g = (Graphics2D) image.createGraphics();
            utils_graphics.setRenderings(g);
            img.paintIcon(null, g, 0,0);
            g.dispose();            
                        
            res = new ImageIcon(image);               
        }
        return res;
    }
    private void setTamanioTitulo()
    {
       FontMetrics fm = getFontMetrics(getFont());
       int ancho_texto = fm.stringWidth(quitarHTML(getText()));
       int ancho_titulo = getWidth()-((margen_titulo*4)+(mini_icono_ancho/2));
       Double division = getCantidadFilas(ancho_texto, ancho_titulo);
       int alto = fm.getHeight();
       if(this.getText().contains(" "))
       {
            tamanio_titulo.height = (division.intValue()*alto)+14;       
       }
       else tamanio_titulo.height = (1*alto)+14;
       tamanio_titulo.y = this.getHeight()-tamanio_titulo.height;
    }
    
    public void boundsIcono(Icon img)
    {
        Double por_margen = 5.5;
        Double decima_ancho = (getWidth()*(por_margen/100));
        Double decima_alto = (getHeight()*(por_margen/100));
               
        int ancho = (getWidth()-((decima_ancho.intValue()*2)));
        int alto = (getHeight()-(56));
        int ancho_max = ancho;
        int alto_max = alto;
        Double porcentaje_an = 100.0;
        Double porcentaje_al = 100.0;
        if(img != null)
        {
            if(img.getIconHeight() > img.getIconWidth())
            {
                porcentaje_an = ((double)img.getIconWidth()/(double)img.getIconHeight())*100;
                ancho = alto * porcentaje_an.intValue()/100;
                
                if(ancho > ancho_max)
                {
                    ancho = ancho_max;
                    Double pr = (100/porcentaje_an)*ancho;
                    alto = pr.intValue();
                }
            }
            else if(img.getIconHeight() < img.getIconWidth())
            {                
                porcentaje_al = ((double)img.getIconHeight()/(double)img.getIconWidth())*100;
                alto = ancho * porcentaje_al.intValue()/100;
                
                if(alto > alto_max)
                {
                    alto = alto_max;
                    Double pr = (100/porcentaje_al)*alto;
                    ancho = pr.intValue();
                }                
            }
            else if(img.getIconHeight() == img.getIconWidth())
            {              
                if(bounds_fondo.getHeight() > bounds_fondo.getWidth())
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
        
        int x = (getWidth()-ancho)/2;
        int y = (getHeight()-alto)/3;
        bounds_fondo.setBounds(x,y, ancho,alto);
    }
    //</editor-fold>
     
    //<editor-fold defaultstate="collapsed" desc="Funciones Publicas">
    public void setIcono(Icon img)
    {    
        boundsIcono(img);
        if(img != null)
        {
            imagen = aImageIcon(img); 
        }
    }
    public void setDescripcion(String s)
    {
        this.descripcion = s;
        setTamanioTitulo();  
        this.repaint();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Funciones Sobreescritas">
    @Override
    public void setText(String s)
    {
        titulo = s;
        super.setText(s);
        setTamanioTitulo();        
        this.repaint();
    } 
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Funciones Inicializacion">
    private void inicializar()
    {        
        this.setLayout(null);
        this.addMouseListener(this);
        this.addComponentListener(this);
        bounds_fondo = new Rectangle(0,0,0,0);
        this.setPreferredSize(new Dimension(120,120));
        this.setOpaque(false);
        this.setForeground(Color.white);
        setFont(new Font("Tahoma",0,12));
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Funciones de animación">
    TimerTask tt; //Cronómetro que se dispara y ejecuta la animación de caminar
    java.util.Timer tiempo = new java.util.Timer(); //Cronómetro que administra la animación
    public void iniciar_animacion()
    {       
        tiempo = new java.util.Timer();
        tt = new TimerTask(){
            @Override
            public void run() {            
                animar();
            }
        };   
        if(tt.scheduledExecutionTime()<=0)
        {
            tiempo.scheduleAtFixedRate(tt, 1, 47);
        }        
    }   
    private void animar()
    {
        int paso = 6;
        if(posicion_animacion == 0 && mostrarDescripcion == true)
        {
            posicion_animacion -= paso;
            botonDesplegado = false;
        }
        else if((tamanio_titulo.y+posicion_animacion) == tamanio_titulo.height)
        {
            posicion_animacion += paso;
            botonDesplegado = false;
        }
        if(mostrarDescripcion == true)
        {
            if ((tamanio_titulo.y+posicion_animacion) <= tamanio_titulo.height+21) paso = 3;
            if ((tamanio_titulo.y+posicion_animacion) <= tamanio_titulo.height+9) paso = 2;
            if ((tamanio_titulo.y+posicion_animacion) <= tamanio_titulo.height+3) paso = 1;
            posicion_animacion -= paso;

            if((tamanio_titulo.y+posicion_animacion) <= tamanio_titulo.height)
            {
                posicion_animacion = -(tamanio_titulo.y-tamanio_titulo.height);
                tiempo.cancel();
                tt.cancel();
            }
        }           
        else if(mostrarDescripcion == false)
        {
            if (posicion_animacion > (-3)) paso = 1;
            if (posicion_animacion > (-9)) paso = 2;
            if (posicion_animacion > (-21)) paso = 3;
            posicion_animacion += paso;

            if(posicion_animacion >= 0)
            {
                posicion_animacion = 0;
                tiempo.cancel();
                tt.cancel();
            }
        }
        this.repaint();
    }
    
    //</editor-fold>
    
    public BotonDescriptivo(String titulo_p)
    {
        this.titulo = titulo_p;
        setText(titulo_p);
        inicializar();
    }
    public BotonDescriptivo()
    {
        inicializar();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Mouse Event">
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(this.isEnabled() == true)
        {
            if(e.getButton() != 3)
            {
                mousePressed = true;
                BufferedImage image = new BufferedImage(
                    this.getWidth(),
                    this.getHeight(),
                    BufferedImage.TYPE_INT_ARGB
                );
                Graphics2D g2 = (Graphics2D) image.getGraphics();
                utils_graphics.setRenderings(g2);
                this.paint(g2);
                captura = image;
            }
            else
            {
                clickDerecho = true;
                //seleccionado = !seleccionado;
            }
        }
        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(this.isEnabled() == true)
        {
            mousePressed = false;
            mouseEntered = false;
            captura = null;   
            
            if(clickDerecho == false)
            {                
                mostrarDescripcion = false;
                if(tiempo != null && tt != null)
                {
                    tiempo.cancel();
                    tt.cancel();
                }
                if(posicion_animacion != 0) iniciar_animacion();     
            }
            clickDerecho = false;
        }
        this.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(this.isEnabled() == true)
        {
            mouseEntered = true;
            
            if(e.getY() >= tamanio_titulo.y)
            {
                mostrarDescripcion = true;
                if(tiempo != null && tt != null)
                {
                    tiempo.cancel();
                    tt.cancel();
                }
                iniciar_animacion();
            }
        }
        this.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(this.isEnabled() == true)
        {
            mouseEntered = false;
            
            
            mostrarDescripcion = false;
            if(tiempo != null && tt != null)
            {
                tiempo.cancel();
                tt.cancel();
            }
            if(posicion_animacion != 0) iniciar_animacion();            
        }
        this.repaint();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="KeyEvent">
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Component Event">
    @Override
    public void componentResized(ComponentEvent e) {
       
       tamanio_titulo.width = this.getWidth();       
       setTamanioTitulo();
       this.boundsIcono(imagen);
       this.repaint();
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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Dibujar">
    private void dibujar_contenido(Graphics2D g2d, boolean desplegado)
    {
        //Dibuar color de fondo
        Color back = getBackground();
        if(desplegado == true)
        {
            back = utils_color.getMasObscuro(back, 5);
        }
        g2d.setColor(back);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
    private Double getCantidadFilas(int ancho_texto, int ancho_control)
    {
        Double uno = (double)ancho_texto;
        Double dos = (double)ancho_control;

        Double division = Math.ceil(uno/dos);
        return division;
    }
    private void dibujar_titulo(Graphics2D g2d, boolean desplegado)
    {
        Color back = getBackground();
        Polygon p = new Polygon();        
        int x_margin = 15;    
        int ancho_flecha = (tamanio_flecha/2)*5;
        back = utils_color.getMasObscuro(back, porcentaje_variacion);        
        g2d.setColor(back);
        g2d.fillRect(tamanio_titulo.x, tamanio_titulo.y+posicion_animacion, tamanio_titulo.width, tamanio_titulo.height-(posicion_animacion));

        if(mostrarDescripcion == false)
        {
            p.addPoint(x_margin, (tamanio_titulo.y-tamanio_flecha)+posicion_animacion);
            p.addPoint(x_margin-(ancho_flecha/2), (tamanio_titulo.y)+posicion_animacion);
            p.addPoint((x_margin-(ancho_flecha/2))+ancho_flecha, (tamanio_titulo.y)+posicion_animacion);
        }
        else if(mostrarDescripcion == true)
        {
            p.addPoint(x_margin, (tamanio_titulo.y+tamanio_flecha)+posicion_animacion);
            p.addPoint(x_margin-(ancho_flecha/2), (tamanio_titulo.y)+posicion_animacion);
            p.addPoint((x_margin-(ancho_flecha/2))+ancho_flecha, (tamanio_titulo.y)+posicion_animacion);
            g2d.setColor(getBackground());
        }
        g2d.fill(p);

        if(!titulo.equals(""))
        {
            g2d.setColor(this.getForeground());

            FontMetrics fm = g2d.getFontMetrics(g2d.getFont());
            int ancho_texto = fm.stringWidth(titulo);
            int ancho_control = getWidth()-((margen_titulo*3)+mini_icono_ancho);
            Double division = getCantidadFilas(ancho_texto, ancho_control);

            int fila = 1;
            int alto_letras = fm.getAscent()+fm.getDescent();
            if(division > 1)
            {
                String cadena_actual = "";
                String palabra = "";
                int len_titulo = titulo.length();
                for(int i = 0; i < len_titulo; i++)
                {
                    char caracter = titulo.charAt(i);
                    palabra += caracter;
                    if(caracter == ' ' || (len_titulo-1) == i)
                    {
                        cadena_actual += palabra;
                        int ancho_cadena = fm.stringWidth(cadena_actual);
                        Double filas = getCantidadFilas(ancho_cadena, ancho_control);

                        if(filas > 1)
                        {
                            if(mostrarDescripcion == false)
                            {
                                g2d.drawString(cadena_actual.replace(palabra, ""), margen_titulo, tamanio_titulo.y+(margen_titulo/2)+((alto_letras)*fila));
                            }
                            else
                            {
                                g2d.drawString(cadena_actual.replace(palabra, ""), margen_titulo, ((tamanio_titulo.y-tamanio_titulo.height)+(margen_titulo/2)+((alto_letras)*fila))+posicion_animacion);
                            }
                            if(!cadena_actual.equals(palabra))
                            {
                                fila = fila+1;
                            }
                            cadena_actual = palabra;
                        }
                        palabra = "";
                    }
                }
                if(!cadena_actual.equals(""))
                {
                    if(mostrarDescripcion == false)
                    {
                        g2d.drawString(cadena_actual, margen_titulo, tamanio_titulo.y+(margen_titulo/2)+(alto_letras*fila));
                    }
                    else
                    {
                        g2d.drawString(cadena_actual, margen_titulo,  ((tamanio_titulo.y-tamanio_titulo.height)+(margen_titulo/2)+((alto_letras)*fila))+posicion_animacion);
                    }
                }
            }
            else
            {
                if(mostrarDescripcion == false)
                    g2d.drawString(titulo, margen_titulo, tamanio_titulo.y+alto_letras+(margen_titulo/2));
                else
                    g2d.drawString(titulo, margen_titulo, (tamanio_titulo.y-((tamanio_titulo.height/2)+(alto_letras)))+posicion_animacion+alto_letras+(margen_titulo/2));
            }
        }
        if(!descripcion.equals("") && mostrarDescripcion == true)
        {
            g2d.setColor(this.getForeground());
            Font f_descripcion = new Font(this.getFont().getFontName(),0,this.getFont().getSize()-2);
            g2d.setFont(f_descripcion);
            FontMetrics fm = g2d.getFontMetrics(g2d.getFont());
            int ancho_texto = fm.stringWidth(descripcion);
            int ancho_control = getWidth()-((margen_titulo/2));
            Double division = getCantidadFilas(ancho_texto, ancho_control);

            int fila = 1;
            int alto_letras = fm.getAscent()+fm.getDescent();
            if(division > 1)
            {
                String cadena_actual = "";
                String palabra = "";
                int len_titulo = descripcion.length();
                for(int i = 0; i < len_titulo; i++)
                {
                    char caracter = descripcion.charAt(i);
                    palabra += caracter;
                    if(caracter == ' ' || (len_titulo-1) == i)
                    {
                        cadena_actual += palabra;
                        int ancho_cadena = fm.stringWidth(cadena_actual);
                        Double filas = getCantidadFilas(ancho_cadena, ancho_control);

                        if(filas > 1)
                        {
                            if(mostrarDescripcion == false)
                                g2d.drawString(cadena_actual.replace(palabra, ""), margen_titulo/2, tamanio_titulo.y+(margen_titulo/4)+((alto_letras)*fila));
                            else
                            {
                                g2d.drawString(cadena_actual.replace(palabra, ""), margen_titulo/2, ((tamanio_titulo.y)+(margen_titulo/4)+((alto_letras)*fila))+posicion_animacion);
                            }

                            fila = fila+1;
                            cadena_actual = palabra;
                        }
                        palabra = "";
                    }
                }
                if(!cadena_actual.equals(""))
                {
                    if(mostrarDescripcion == false)
                    {
                        g2d.drawString(cadena_actual, margen_titulo/2, tamanio_titulo.y+(margen_titulo/4)+(alto_letras*fila));
                    }
                    else
                    {
                        g2d.drawString(cadena_actual, margen_titulo/2,  ((tamanio_titulo.y)+(margen_titulo/4)+((alto_letras)*fila))+posicion_animacion);
                    }
                }
            }
            else
            {
                if(mostrarDescripcion == false)
                    g2d.drawString(descripcion, margen_titulo/2, tamanio_titulo.y+alto_letras+(margen_titulo/4));
                else
                    g2d.drawString(descripcion, margen_titulo/2, (tamanio_titulo.y+((tamanio_titulo.height/4)+(alto_letras)))+posicion_animacion);
            }
            g2d.setFont(this.getFont());
        }
    }
    private void dibujar_icono(Graphics2D g2d, boolean desplegado)
    {
        if(imagen != null)
        {
            if(mostrarDescripcion == false)
            {
                g2d.drawImage(imagen.getImage(), bounds_fondo.x, bounds_fondo.y, bounds_fondo.width, bounds_fondo.height, null);
            }
            else
            {
                g2d.drawImage(imagen.getImage(), getWidth()-32, (tamanio_titulo.y-((tamanio_titulo.height/2)+15))+posicion_animacion, mini_icono_ancho*2, mini_icono_ancho*2, null);
            }
        }
    }
    private void dibujar_borde(Graphics2D g2d, boolean pressed)
    {
        int stroke = 2;
        g2d.setColor(color_borde);
        g2d.setStroke(new BasicStroke(stroke));
        if(pressed == false && mouseEntered == true)
        {
            if(imagen != null && mostrarDescripcion == false)
            {
                g2d.drawImage(imagen.getImage(), bounds_fondo.x, bounds_fondo.y, bounds_fondo.width, bounds_fondo.height, null);
            }            
            g2d.setColor(color_borde);
            g2d.drawRect(stroke/2, stroke/2, this.getWidth()-(stroke), this.getHeight()-(stroke)); 
        }
        else if(pressed == true && mouseEntered == true)
        {
            g2d.setStroke(new BasicStroke(stroke));
            g2d.drawRect(margen_pressed-(stroke), margen_pressed-(stroke), this.getWidth()-((margen_pressed-(stroke))*2), this.getHeight()-((margen_pressed-(stroke))*2));
            g2d.setStroke(new BasicStroke(stroke));
        }
    }
    //</editor-fold>
    
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        utils_graphics.setRenderings(g2d);        
        
        ImageIcon imi = null;
        if(captura != null) 
        {
            imi = new ImageIcon(captura);
            g2d.drawImage(imi.getImage(), margen_pressed, margen_pressed, this.getWidth()-(margen_pressed*2), this.getHeight()-(margen_pressed*2), null);
            
        }   
        else
        {
            dibujar_contenido(g2d, botonDesplegado);
            
            dibujar_icono(g2d, botonDesplegado);
            if(!getText().equals(""))
            {
                dibujar_titulo(g2d, botonDesplegado);
            }
            dibujar_borde(g2d, mousePressed);
            
        }
    }
}
