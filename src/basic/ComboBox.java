/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import renders.ComboBoxUI;

/**
 *
 * @author Nelson
 */
public class ComboBox extends JComboBox{
    private ComboBoxUI CustomUI;
    public ComboBox()
    {
        Dimension dimension = new Dimension(200,32);
        setPreferredSize(dimension);
        setSize(dimension);
        setUI(CustomUI.createUI(this));                
        setVisible(true);
        setBackground(Color.white);
    }
}
