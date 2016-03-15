/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basic;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JSpinner;
import renders.ComboBoxUI;
import renders.SpinnerUI;

/**
 *
 * @author Nelson
 */
public class Spinner extends JSpinner{
    private SpinnerUI CustomUI;
    public Spinner()
    {
        Dimension dimension = new Dimension(120,30);
        setPreferredSize(dimension);
        setSize(dimension);
        setUI(CustomUI.createUI(this));                
        setVisible(true);
        setBackground(Color.white);
    }
}
