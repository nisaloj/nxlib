/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renders;

import basic.PanelTab;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Nelson
 */
public class Closable extends JPanel
{
    private boolean closable = false;
    private String text = ""; 
    private Icon icon = null;
    public void setClosable(boolean b)
    {
        closable = b;
    }
    public boolean getClosable(){return closable;}
    public void setText(String b)
    {
        text = b;
    }
    public String getText(){return text;}

    public void setIcon(Icon i)
    {
        icon = i;
    }
    public Icon getIcon(){return icon;}

    public Closable(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public Closable(LayoutManager layout) {
        super(layout);
    }

    public Closable(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public Closable() {
    }
    @Override
    public void paint(Graphics g)
    {
      super.paint(g);
    }
}
