/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renders;

import botones.Boton;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author Nelson
 */
public class SpinnerUI extends javax.swing.plaf.basic.BasicSpinnerUI{
    public SpinnerUI(){}
    public SpinnerUI(boolean p_bool)
    {
        
    }
    private void inicializar(){}
    
    private Color background = new Color(255,255,255);
    private Color foreground = new Color(92,92,92);
    private int alto = 25;
    
    public static SpinnerUI createUI(JComponent c) {
        return new SpinnerUI();
    }
    //Se puede usar un JButton para usar un icono personalizado en lugar del arrow
    
    @Override
    public JComponent createPreviousButton()
    {
        super.createPreviousButton();
        Boton button = new Boton(); 
        button.setTipoBoton(constantes.compila.TIPO_BOTON.ICONO_COMPLETO);
        button.setIconoVariable(true);
        button.setForeground(new Color(182,182,182));
        button.setBackground(background);
        button.setPreferredSize(new Dimension(25, 25));
        //se quita el efecto 3d del boton, sombra y darkShadow no se aplican 
        button.setText("");
        button.setIcon( new ImageIcon(getClass().getResource("/imagenes/appbar.chevron.down.png")) );
        button.setName("Spinner.previousButton");
        installPreviousButtonListeners(button);
        return button;
    }
    @Override
    public JComponent createNextButton()
    {        
        super.createNextButton();
        Boton button = new Boton(); 
        button.setTipoBoton(constantes.compila.TIPO_BOTON.ICONO_COMPLETO);
        button.setIconoVariable(true);
        button.setForeground(new Color(182,182,182));
        button.setBackground(background);
        button.setPreferredSize(new Dimension(25, 25));
        //se quita el efecto 3d del boton, sombra y darkShadow no se aplican 
        button.setText("");
        button.setIcon( new ImageIcon(getClass().getResource("/imagenes/appbar.chevron.up.png")) );
        button.setName("Spinner.nextButton");
        installNextButtonListeners(button);
        return button;
    }
}
