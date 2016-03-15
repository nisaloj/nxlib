/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renders;

import botones.Boton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author Nelson
 */
public class ComboBoxUI extends BasicComboBoxUI{
    public ComboBoxUI(){}
    public ComboBoxUI(boolean p_bool)
    {
        
    }
    private void inicializar(){}
    
    private Color background = new Color(255,255,255);
    private Color foreground = new Color(92,92,92);
    private Color backgroundFocus = new Color(86,160,245);
    private Color foregroundFocus = new Color(255,255,255);
    private int alto = 25;
    
    public static ComboBoxUI createUI(JComponent c) {
        return new ComboBoxUI();
    }
    //Se puede usar un JButton para usar un icono personalizado en lugar del arrow
    
    @Override 
    protected Boton createArrowButton() { 
        Boton button = new Boton(); 
        button.setTipoBoton(constantes.compila.TIPO_BOTON.ICONO_COMPLETO);
        button.setIconoVariable(true);
        button.setForeground(new Color(202,202,202));
        button.setBackground(background);
        //se quita el efecto 3d del boton, sombra y darkShadow no se aplican 
        button.setText("");
        button.setIcon( new ImageIcon(getClass().getResource("/imagenes/appbar.chevron.down.png")) );
        return button;
    } 
    
    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds,boolean hasFocus)
    {
        g.setColor(background);            
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
    //Pinta los items
    @Override
    protected ListCellRenderer createRenderer()
    {
        return new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList list,Object value,int index, boolean isSelected,boolean cellHasFocus) 
        {
            super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
            list.setSelectionBackground(background);
            list.setSelectionForeground(foreground);
            JComponent jcomponent = null;

            if(value instanceof String ) {
                jcomponent = new JLabel((String) value);
                ((JLabel)jcomponent).setHorizontalAlignment(SwingConstants.LEFT );
                ((JLabel)jcomponent).setSize(alto, jcomponent.getWidth());
                ((JLabel)jcomponent).setPreferredSize( new Dimension(6, jcomponent.getWidth()) );
            } 

            jcomponent.setEnabled(true);
            jcomponent.setOpaque(true);
            if (isSelected)
            {
                jcomponent.setBorder(BorderFactory.createEmptyBorder(0,7,0,0));//5 is the indent, modify to suit
                jcomponent.setBackground(backgroundFocus);
                jcomponent.setForeground(foregroundFocus);
            }
            else
            {
                jcomponent.setBorder(BorderFactory.createEmptyBorder(0,7,0,0));//5 is the indent, modify to suit
                jcomponent.setBackground( getBackground() );            
                jcomponent.setForeground( foreground);
            }            

            return jcomponent;
        }
        };
    }
}
